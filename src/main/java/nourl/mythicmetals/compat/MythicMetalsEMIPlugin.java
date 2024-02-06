package nourl.mythicmetals.compat;

import dev.emi.emi.api.*;
import net.minecraft.recipe.*;
import nourl.mythicmetals.recipe.MidasFoldingRecipe;
import nourl.mythicmetals.recipe.TidesingerCoralRecipe;

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
    }
}
