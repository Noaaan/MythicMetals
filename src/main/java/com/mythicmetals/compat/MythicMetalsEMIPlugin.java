package com.mythicmetals.compat;

import dev.emi.emi.api.*;

@EmiEntrypoint
public class MythicMetalsEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
//        for (RecipeEntry<SmithingRecipe> recipe : registry.getRecipeManager().listAllOfType(RecipeType.SMITHING)) {
//            if (recipe.value() instanceof MidasFoldingRecipe foldingRecipe) {
//                registry.addRecipe(new MidasFoldingEMIRecipe(foldingRecipe));
//            }
//            if (recipe.value() instanceof TidesingerCoralRecipe tidesingerCoralRecipe) {
//                registry.addRecipe(new TidesingerEMIRecipe(tidesingerCoralRecipe, recipe.id()));
//            }
//        }
//        registry.removeRecipes(RegistryHelper.id("hoe/based"));
//        registry.removeRecipes(RegistryHelper.id("hoe/blazed"));
//        registry.removeEmiStacks(EmiStack.of(MythicTools.Frogery.FROGE));
//        registry.removeEmiStacks(EmiStack.of(MythicTools.Frogery.DOGE));
    }
}
