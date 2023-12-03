package nourl.mythicmetals.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.recipe.MidasFoldingRecipe;
//import nourl.mythicmetals.recipe.TidesingerCoralRecipe;
import nourl.mythicmetals.recipe.TippedRuniteArrowRecipe;

public class RegisterRecipeSerializers {

    public static final RecipeSerializer<TippedRuniteArrowRecipe> TIPPED_RUNITE_ARROW_RECIPE = new SpecialRecipeSerializer<>(TippedRuniteArrowRecipe::new);
    public static final RecipeSerializer<MidasFoldingRecipe> MIDAS_FOLDING_RECIPE = new MidasFoldingRecipe.Serializer();

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TIPPED_RUNITE_ARROW_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_sword"), MIDAS_FOLDING_RECIPE);
    }
}
