package nourl.mythicmetals.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.registry.RegisterRecipeSerializers;

public class TippedRuniteArrowRecipe extends SpecialCraftingRecipe {
    public TippedRuniteArrowRecipe(Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory craftingInventory, World world) {
        if (craftingInventory.getWidth() == 3 && craftingInventory.getHeight() == 3) {
            for (int i = 0; i < craftingInventory.getWidth(); ++i) {
                for (int j = 0; j < craftingInventory.getHeight(); ++j) {
                    ItemStack itemStack = craftingInventory.getStack(i + j * craftingInventory.getWidth());
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

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack itemStack = inventory.getStack(1 + inventory.getWidth());
        if (!itemStack.isOf(Items.LINGERING_POTION)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack2 = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW, 8);
            PotionUtil.setPotion(itemStack2, PotionUtil.getPotion(itemStack));
            PotionUtil.setCustomPotionEffects(itemStack2, PotionUtil.getCustomPotionEffects(itemStack));
            return itemStack2;
        }
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisterRecipeSerializers.TIPPED_RUNITE_ARROW_RECIPE;
    }
}
