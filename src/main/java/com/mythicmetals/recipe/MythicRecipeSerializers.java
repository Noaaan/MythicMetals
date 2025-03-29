package com.mythicmetals.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import com.mythicmetals.misc.RegistryHelper;

public class MythicRecipeSerializers {

    public static final RecipeSerializer<TippedRuniteArrowRecipe> TIPPED_RUNITE_ARROW_RECIPE = new SpecialRecipeSerializer<>(TippedRuniteArrowRecipe::new);
    public static final RecipeSerializer<MidasFoldingRecipe> MIDAS_FOLDING_RECIPE = new MidasFoldingRecipe.Serializer();
    public static final RecipeSerializer<TidesingerCoralRecipe> TIDESINGER_CORAL_RECIPE = new TidesingerCoralRecipe.Serializer(TidesingerCoralRecipe.Serializer.ENDEC);
    public static final RecipeSerializer<UpgradeSmithingRecipe> UPGRADE_SMITHING_RECIPE_SERIALIZER = new UpgradeSmithingRecipe.Serializer(UpgradeSmithingRecipe.Serializer.ENDEC);

    public static void init() {
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TIPPED_RUNITE_ARROW_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_sword"), MIDAS_FOLDING_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("tidesinger_smithing_transform"), TIDESINGER_CORAL_RECIPE);
        Registry.register(Registries.RECIPE_SERIALIZER, RegistryHelper.id("upgrading"), UPGRADE_SMITHING_RECIPE_SERIALIZER);
    }
}
