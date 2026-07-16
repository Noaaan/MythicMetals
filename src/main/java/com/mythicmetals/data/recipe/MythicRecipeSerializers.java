package com.mythicmetals.data.recipe;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class MythicRecipeSerializers {

    public static final RecipeSerializer<TippedRuniteArrowRecipe> TIPPED_RUNITE_ARROW_RECIPE = new CustomRecipe.Serializer<>(TippedRuniteArrowRecipe::new);
    public static final RecipeSerializer<MidasFoldingRecipe> MIDAS_FOLDING_RECIPE = new MidasFoldingRecipe.Serializer();
    public static final RecipeSerializer<UpgradeSmithingRecipe> UPGRADE_SMITHING_RECIPE_SERIALIZER = new UpgradeSmithingRecipe.Serializer(UpgradeSmithingRecipe.Serializer.ENDEC);

    public static void init() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("runite_tipped_arrow_recipe"), TIPPED_RUNITE_ARROW_RECIPE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_sword"), MIDAS_FOLDING_RECIPE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("upgrading"), UPGRADE_SMITHING_RECIPE_SERIALIZER);
    }
}
