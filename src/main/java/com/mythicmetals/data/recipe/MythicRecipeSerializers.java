package com.mythicmetals.data.recipe;

import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.serialization.EndecRecipeSerializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class MythicRecipeSerializers {

    public static final RecipeSerializer<MidasFoldingRecipe> MIDAS_FOLDING_RECIPE = EndecRecipeSerializer.create(MidasFoldingRecipe.ENDEC);
    public static final RecipeSerializer<UpgradeSmithingRecipe> UPGRADE_SMITHING_RECIPE_SERIALIZER = EndecRecipeSerializer.create(UpgradeSmithingRecipe.ENDEC);

    public static void init() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("fold_midas_sword"), MIDAS_FOLDING_RECIPE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, RegistryHelper.id("upgrading"), UPGRADE_SMITHING_RECIPE_SERIALIZER);
    }
}
