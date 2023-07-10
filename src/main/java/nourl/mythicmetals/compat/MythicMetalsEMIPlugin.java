package nourl.mythicmetals.compat;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import nourl.mythicmetals.item.tools.MidasFoldingRecipe;

@EmiEntrypoint
public class MythicMetalsEMIPlugin implements EmiPlugin {

    @Override
    public void register(EmiRegistry registry) {
        for (SmithingRecipe recipe : registry.getRecipeManager().listAllOfType(RecipeType.SMITHING)) {
            if (recipe instanceof MidasFoldingRecipe foldingRecipe) {
                registry.addRecipe(new MidasFoldingEMIRecipe(foldingRecipe));
            }
        }
    }
}
