package com.mythicmetals.data.recipe;

import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class TippedRuniteArrowRecipe extends CustomRecipe {
    public TippedRuniteArrowRecipe(CraftingBookCategory craftingRecipeCategory) {
        super(craftingRecipeCategory);
    }

    public boolean matches(CraftingInput input, Level world) {
        if (input.width() == 3 && input.height() == 3) {
            for (int i = 0; i < input.width(); ++i) {
                for (int j = 0; j < input.height(); ++j) {
                    ItemStack itemStack = input.getItem(i + j * input.width());
                    if (itemStack.isEmpty()) {
                        return false;
                    }

                    if (i == 1 && j == 1) {
                        if (!itemStack.is(Items.LINGERING_POTION)) {
                            return false;
                        }
                    } else if (!itemStack.is(MythicTools.RUNITE_ARROW)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider wrapperLookup) {
        ItemStack itemStack = input.getItem(1 + input.width());
        if (!itemStack.is(Items.LINGERING_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack2 = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW, 8);
            itemStack2.set(DataComponents.POTION_CONTENTS, itemStack.get(DataComponents.POTION_CONTENTS));
            return itemStack2;
        }
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return MythicRecipeSerializers.TIPPED_RUNITE_ARROW_RECIPE;
    }
}
