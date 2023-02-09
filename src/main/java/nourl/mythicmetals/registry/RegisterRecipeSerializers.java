package nourl.mythicmetals.registry;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.item.tools.DrillUpgradeSmithingRecipe;
import nourl.mythicmetals.item.tools.MidasSmithingRecipe;
import nourl.mythicmetals.item.tools.TippedRuniteArrowRecipe;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterRecipeSerializers {

    public static final RecipeSerializer<MidasSmithingRecipe> MIDAS_SMITHING_RECIPE = new MidasSmithingRecipe.Serializer();
    public static final RecipeSerializer<DrillUpgradeSmithingRecipe> MYTHRIL_DRILL_SMITHING_RECIPE = new DrillUpgradeSmithingRecipe.Serializer();

    public static final RecipeSerializer<TippedRuniteArrowRecipe> TIPPED_RUNITE_ARROW_RECIPE = new SpecialRecipeSerializer<>(TippedRuniteArrowRecipe::new);

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_smithing_recipe"), MIDAS_SMITHING_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("mythril_drill_smithing_recipe"), MYTHRIL_DRILL_SMITHING_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TIPPED_RUNITE_ARROW_RECIPE);
    }
}
