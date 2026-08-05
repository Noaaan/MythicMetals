package com.mythicmetals.data.recipe;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.UpgradeComponent;
import com.mythicmetals.item.tools.MythrilDrill;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.*;

public final class UpgradeSmithingRecipe implements SmithingRecipe {
    private final Ingredient base;
    private final Optional<Ingredient> addition;
    private final ItemStack result;

    public UpgradeSmithingRecipe(Ingredient base, Optional<Ingredient> addition, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Nullable
    private PlacementInfo ingredientPlacement;

    @Override
    public boolean matches(SmithingRecipeInput input, Level level) {
        boolean validRecipe = this.baseIngredient().test(input.base())
            && Ingredient.testOptionalIngredient(this.additionIngredient(), input.addition());

        if (!validRecipe) return false;

        var addition = input.addition().getItem();
        var upgrades = input.base().get(MythicDataComponents.UPGRADES);
        if (upgrades == null) return false;

        boolean isUpgradeValid = !addition.equals(Items.AIR) && !upgrades.hasUpgrade(addition);
        return isUpgradeValid && upgrades.hasFreeSlots();
    }

    @Override
    public Optional<Ingredient> templateIngredient() {
        return Optional.empty();
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider lookup) {
        var stack = input.base().copy();
        var addition = input.addition().getItem();
        var attributes = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        boolean changes = false;

        // Apply drill upgrade
        stack.set(MythicDataComponents.UPGRADES, UpgradeComponent.addItem(stack.getOrDefault(MythicDataComponents.UPGRADES, MythrilDrill.DEFAULT_DRILL_UPGRADES), input.addition().getItem()));

        if (addition.equals(MythicMaterials.MIDAS_GOLD.extraBlocks().get(MythicResourceKeys.ENCHANTED_MIDAS_GOLD_BLOCK).asItem())) {
            var modifier = new AttributeModifier(
                RegistryHelper.id("mythril_drill_luck_bonus"),
                2.0,
                AttributeModifier.Operation.ADD_VALUE
            );
            attributes = attributes.withModifierAdded(Attributes.LUCK, modifier, EquipmentSlotGroup.MAINHAND);
        }
        if (addition.equals(MythicMaterials.AQUARIUM.extraItems().get(MythicResourceKeys.AQUARIUM_PEARL))) {
            var modifier = new AttributeModifier(
                RegistryHelper.id("mythril_drill_underwater_mining_bonus"),
                3.0,
                AttributeModifier.Operation.ADD_VALUE
            );
            attributes = attributes.withModifierAdded(Attributes.SUBMERGED_MINING_SPEED, modifier, EquipmentSlotGroup.MAINHAND);
        }
        int upgrades = stack.get(MythicDataComponents.UPGRADES).countRealUpgrades();
        if (upgrades > 0) {
            attributes = attributes.withModifierAdded(
                Attributes.MINING_EFFICIENCY,
                new AttributeModifier(
                    RegistryHelper.id("mythril_drill_upgrade_mining_speed_bonus"),
                    0.1 * upgrades,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                ),
                EquipmentSlotGroup.MAINHAND
            );
        }
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, attributes);
        return stack;
    }

    @Override
    public RecipeSerializer<UpgradeSmithingRecipe> getSerializer() {
        return MythicRecipeSerializers.UPGRADE_SMITHING_RECIPE_SERIALIZER;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(Optional.of(this.base), this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public Ingredient baseIngredient() {
        return base;
    }

    @Override
    public Optional<Ingredient> additionIngredient() {
        return addition;
    }

    public ItemStack result() {
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UpgradeSmithingRecipe) obj;
        return Objects.equals(this.base, that.base) &&
            Objects.equals(this.addition, that.addition) &&
            Objects.equals(this.result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, addition, result);
    }

    @Override
    public String toString() {
        return "UpgradeSmithingRecipe[" +
            "base=" + base + ", " +
            "addition=" + addition + ", " +
            "result=" + result + ']';
    }


    public static class Serializer extends EndecRecipeSerializer<UpgradeSmithingRecipe> {

        public static final StructEndec<UpgradeSmithingRecipe> ENDEC = StructEndecBuilder.of(
            CodecUtils.toEndec(Ingredient.CODEC).fieldOf("base", UpgradeSmithingRecipe::baseIngredient),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", UpgradeSmithingRecipe::additionIngredient),
            MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
            UpgradeSmithingRecipe::new
        );

        public Serializer(StructEndec<UpgradeSmithingRecipe> endec) {
            super(endec);
        }
    }
}
