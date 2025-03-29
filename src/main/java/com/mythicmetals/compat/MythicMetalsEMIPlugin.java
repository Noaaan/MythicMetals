package com.mythicmetals.compat;

import dev.emi.emi.api.*;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.recipe.*;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.recipe.MidasFoldingRecipe;
import com.mythicmetals.recipe.TidesingerCoralRecipe;

@EmiEntrypoint
public class MythicMetalsEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        for (RecipeEntry<SmithingRecipe> recipe : registry.getRecipeManager().listAllOfType(RecipeType.SMITHING)) {
            if (recipe.value() instanceof MidasFoldingRecipe foldingRecipe) {
                registry.addRecipe(new MidasFoldingEMIRecipe(foldingRecipe));
            }
            if (recipe.value() instanceof TidesingerCoralRecipe tidesingerCoralRecipe) {
                registry.addRecipe(new TidesingerEMIRecipe(tidesingerCoralRecipe));
            }
        }
        registry.removeRecipes(RegistryHelper.id("hoe/based"));
        registry.removeRecipes(RegistryHelper.id("hoe/blazed"));
        registry.removeEmiStacks(EmiStack.of(MythicTools.Frogery.FROGE));
        registry.removeEmiStacks(EmiStack.of(MythicTools.Frogery.DOGE));
    }
}
