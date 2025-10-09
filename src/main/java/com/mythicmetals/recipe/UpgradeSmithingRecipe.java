package com.mythicmetals.recipe;

import com.mythicmetals.MythicMetals;
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

public record UpgradeSmithingRecipe(Ingredient base, Ingredient addition, ItemStack result) implements SmithingRecipe {

    @Override
    public boolean testTemplate(ItemStack stack) {
        return false;
    }

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public boolean matches(SmithingRecipeInput input, World world) {
        boolean validRecipe = this.base.test(input.base()) && this.addition.test(input.addition());
        var addition = input.addition().getItem();
        var upgrades = input.base().get(MythicDataComponents.UPGRADES);
        if (upgrades == null) return false;

        boolean isUpgradeValid = !addition.equals(Items.AIR) && !upgrades.hasUpgrade(addition);

        return validRecipe && isUpgradeValid && upgrades.hasFreeSlots();
    }

    @Override
    public ItemStack craft(SmithingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        var stack = input.base().copy();

        // Apply drill upgrade
        stack.set(MythicDataComponents.UPGRADES, UpgradeComponent.addItem(stack.get(MythicDataComponents.UPGRADES), input.addition().getItem()));
        return stack;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MythicRecipeSerializers.UPGRADE_SMITHING_RECIPE_SERIALIZER;
    }

    public static class Serializer extends EndecRecipeSerializer<UpgradeSmithingRecipe> {

        public static final StructEndec<UpgradeSmithingRecipe> ENDEC = StructEndecBuilder.of(
            CodecUtils.toEndec(Ingredient.ALLOW_EMPTY_CODEC).fieldOf("base", recipe -> recipe.base),
            CodecUtils.toEndec(Ingredient.ALLOW_EMPTY_CODEC).fieldOf("addition", recipe -> recipe.addition),
            MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
            UpgradeSmithingRecipe::new
        );

        public Serializer(StructEndec<UpgradeSmithingRecipe> endec) {
            super(endec);
        }
    }
}
