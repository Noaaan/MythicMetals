package com.mythicmetals.recipe;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.TidesingerPatternComponent;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;

public final class TidesingerCoralRecipe implements SmithingRecipe {

    private final Optional<Ingredient> template;
    private final Optional<Ingredient> base;
    private final Optional<Ingredient> addition;
    private final ItemStack result;
    @Nullable
    private IngredientPlacement ingredientPlacement;

    public TidesingerCoralRecipe(Optional<Ingredient> template, Optional<Ingredient> base, Optional<Ingredient> addition, ItemStack result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public ItemStack craft(SmithingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        var armorStack = input.base().copyComponentsToNewStack(this.result.getItem(), 1);
        armorStack.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromStack(input.addition()));
        return armorStack;
    }

    @Override
    public RecipeSerializer<TidesingerCoralRecipe> getSerializer() {
        return MythicRecipeSerializers.TIDESINGER_CORAL_RECIPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(this.template, this.base, this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public Optional<Ingredient> template() {
        return template;
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

    public static class Serializer extends EndecRecipeSerializer<TidesingerCoralRecipe> {

        public static final StructEndec<TidesingerCoralRecipe> ENDEC = StructEndecBuilder.of(
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("base", TidesingerCoralRecipe::base),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", TidesingerCoralRecipe::addition),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("template", TidesingerCoralRecipe::template),
            MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
            TidesingerCoralRecipe::new
        );

        public Serializer(StructEndec<TidesingerCoralRecipe> endec) {
            super(endec);
        }
    }
}
