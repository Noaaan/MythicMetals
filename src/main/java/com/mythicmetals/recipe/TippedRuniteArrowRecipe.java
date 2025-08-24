package com.mythicmetals.recipe;

import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class TippedRuniteArrowRecipe extends SpecialCraftingRecipe {
    public TippedRuniteArrowRecipe(CraftingRecipeCategory craftingRecipeCategory) {
        super(craftingRecipeCategory);
    }

    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getWidth() == 3 && input.getHeight() == 3) {
            for (int i = 0; i < input.getWidth(); ++i) {
                for (int j = 0; j < input.getHeight(); ++j) {
                    ItemStack itemStack = input.getStackInSlot(i + j * input.getWidth());
                    if (itemStack.isEmpty()) {
                        return false;
                    }

                    if (i == 1 && j == 1) {
                        if (!itemStack.isOf(Items.LINGERING_POTION)) {
                            return false;
                        }
                    } else if (!itemStack.isOf(MythicTools.RUNITE_ARROW)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack itemStack = input.getStackInSlot(1 + input.getWidth());
        if (!itemStack.isOf(Items.LINGERING_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack2 = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW, 8);
            itemStack2.set(DataComponentTypes.POTION_CONTENTS, itemStack.get(DataComponentTypes.POTION_CONTENTS));
            return itemStack2;
        }
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MythicRecipeSerializers.TIPPED_RUNITE_ARROW_RECIPE;
    }
}
