package com.mythicmetals.recipe;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.UpgradeComponent;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
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
    private IngredientPlacement ingredientPlacement;

    @Override
    public boolean matches(SmithingRecipeInput input, World world) {
        boolean validRecipe = Ingredient.matches(this.base(), input.base())
            && Ingredient.matches(this.addition(), input.addition());

        if (!validRecipe) return false;

        var addition = input.addition().getItem();
        var upgrades = input.base().get(MythicDataComponents.UPGRADES);
        if (upgrades == null) return false;

        boolean isUpgradeValid = !addition.equals(Items.AIR) && !upgrades.hasUpgrade(addition);
        return isUpgradeValid && upgrades.hasFreeSlots();
    }

    @Override
    public Optional<Ingredient> template() {
        return Optional.empty();
    }

    @Override
    public ItemStack craft(SmithingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        var stack = input.base().copy();

        // Apply drill upgrade
        stack.set(MythicDataComponents.UPGRADES, UpgradeComponent.addItem(stack.get(MythicDataComponents.UPGRADES), input.addition().getItem()));
        return stack;
    }

    @Override
    public RecipeSerializer<UpgradeSmithingRecipe> getSerializer() {
        return MythicRecipeSerializers.UPGRADE_SMITHING_RECIPE_SERIALIZER;
    }

    // TODO - Verify this does not break with a missing slot
    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(this.base, this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public Optional<Ingredient> base() {
        return base;
    }

    @Override
    public Optional<Ingredient> addition() {
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
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("base", UpgradeSmithingRecipe::base),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", UpgradeSmithingRecipe::addition),
            MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
            UpgradeSmithingRecipe::new
        );

        public Serializer(StructEndec<UpgradeSmithingRecipe> endec) {
            super(endec);
        }
    }
}
