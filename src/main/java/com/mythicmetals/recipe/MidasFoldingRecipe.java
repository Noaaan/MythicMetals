package com.mythicmetals.recipe;

import com.mythicmetals.component.GoldFoldedComponent;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;

import static com.mythicmetals.component.MythicDataComponents.GOLD_FOLDED;

public class MidasFoldingRecipe implements SmithingRecipe {

    private final Optional<Ingredient> template;
    private final Optional<Ingredient> base;
    private final Optional<Ingredient> addition;
    private final ItemStack result;
    @Nullable
    private IngredientPlacement ingredientPlacement;

    public MidasFoldingRecipe(Optional<Ingredient> template, Optional<Ingredient> base, Optional<Ingredient> addition, ItemStack result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(SmithingRecipeInput input, World world) {
        if (!SmithingRecipe.super.matches(input, world)) {
            return false;
        }
        var stack = input.base();

        if (!stack.contains(GOLD_FOLDED)) return false;
        int goldCount = stack.contains(GOLD_FOLDED) ? stack.get(GOLD_FOLDED).goldFolded() : 0;

        if (input.template().getItem().equals(MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE)) {
            return goldCount >= 640;
        }

        if (stack.getItem().equals(MythicTools.ROYAL_MIDAS_GOLD_SWORD)) {
            return goldCount >= 640 && goldCount < 10000;
        }

        return goldCount < 640;
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

    @Override
    public ItemStack craft(SmithingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        var swordInputStack = input.base().copy();

        int goldCount = swordInputStack.get(GOLD_FOLDED).goldFolded();
        swordInputStack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1));

        // Gilded Midas Gold Sword handler
        if (swordInputStack.getItem().equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {

            // Transform into Royal Midas Gold Sword
            if (goldCount >= 640) {
                var swordnite = swordInputStack.copyComponentsToNewStack(MythicTools.ROYAL_MIDAS_GOLD_SWORD, 1);
                swordnite.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1, true));
                return swordnite;
            }
        }

        // Handle Midas Gold Sword, transform if you fold and it at least has 320 gold on it
        if (swordInputStack.getItem().equals(MythicTools.MIDAS_GOLD_SWORD)) {

            // Transform Midas Gold Sword into Gilded Midas Gold Sword
            if (goldCount >= 319) {
                var swordnite = swordInputStack.copyComponentsToNewStack(MythicTools.GILDED_MIDAS_GOLD_SWORD, 1);
                swordnite.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1));
                return swordnite;
            }
        }

        return swordInputStack;
    }

    @Override
    public RecipeSerializer<MidasFoldingRecipe> getSerializer() {
        return MythicRecipeSerializers.MIDAS_FOLDING_RECIPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = IngredientPlacement.forMultipleSlots(List.of(this.template, this.base, this.addition));
        }

        return this.ingredientPlacement;
    }

    public static class Serializer extends EndecRecipeSerializer<MidasFoldingRecipe> {
        public static final StructEndec<MidasFoldingRecipe> ENDEC = StructEndecBuilder.of(
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("template", MidasFoldingRecipe::template),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("base", MidasFoldingRecipe::base),
            CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", MidasFoldingRecipe::addition),
            MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
            MidasFoldingRecipe::new
        );

        public Serializer() {
            super(ENDEC);
        }
    }
}
