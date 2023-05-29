package nourl.mythicmetals.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.item.tools.LegacyDrillSmithingRecipe;
import nourl.mythicmetals.item.tools.LegacyMidasFoldingRecipe;
import nourl.mythicmetals.item.tools.MidasFoldingRecipe;
import nourl.mythicmetals.item.tools.TippedRuniteArrowRecipe;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterRecipeSerializers {

    public static final RecipeSerializer<LegacyMidasFoldingRecipe> LEGACY_MIDAS_SMITHING_RECIPE = new LegacyMidasFoldingRecipe.Serializer();
    public static final RecipeSerializer<LegacyDrillSmithingRecipe> LEGACY_MYTHRIL_DRILL_SMITHING_RECIPE = new LegacyDrillSmithingRecipe.Serializer();

    public static final RecipeSerializer<TippedRuniteArrowRecipe> TIPPED_RUNITE_ARROW_RECIPE = new SpecialRecipeSerializer<>(TippedRuniteArrowRecipe::new);
    public static final RecipeSerializer<MidasFoldingRecipe> MIDAS_FOLDING_RECIPE = new MidasFoldingRecipe.Serializer();

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("legacy_fold_midas_smithing_recipe"), LEGACY_MIDAS_SMITHING_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("legacy_mythril_drill_smithing_recipe"), LEGACY_MYTHRIL_DRILL_SMITHING_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TIPPED_RUNITE_ARROW_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_sword"), MIDAS_FOLDING_RECIPE);
    }
}
