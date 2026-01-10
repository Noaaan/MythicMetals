package com.mythicmetals.recipe;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.UpgradeComponent;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.*;

public final class UpgradeSmithingRecipe implements SmithingRecipe {
    private final Optional<Ingredient> base;
    private final Optional<Ingredient> addition;
    private final ItemStack result;

    public UpgradeSmithingRecipe(Optional<Ingredient> base, Optional<Ingredient> addition, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Nullable
    private PlacementInfo ingredientPlacement;

    @Override
    public boolean matches(SmithingRecipeInput input, Level world) {
        boolean validRecipe = Ingredient.testOptionalIngredient(this.baseIngredient(), input.base())
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

        // Apply drill upgrade
        stack.set(MythicDataComponents.UPGRADES, UpgradeComponent.addItem(stack.get(MythicDataComponents.UPGRADES), input.addition().getItem()));
        return stack;
    }

    @Override
    public RecipeSerializer<UpgradeSmithingRecipe> getSerializer() {
        return MythicRecipeSerializers.UPGRADE_SMITHING_RECIPE_SERIALIZER;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(this.base, this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public Optional<Ingredient> baseIngredient() {
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
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("base", UpgradeSmithingRecipe::baseIngredient),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", UpgradeSmithingRecipe::additionIngredient),
            MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
            UpgradeSmithingRecipe::new
        );

        public Serializer(StructEndec<UpgradeSmithingRecipe> endec) {
            super(endec);
        }
    }
}
