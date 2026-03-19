package com.mythicmetals.data;

import com.mythicmetals.conditions.NuggetsLoadedCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import java.util.concurrent.CompletableFuture;

public class MythicRecipeProvider extends FabricRecipeProvider {

    public MythicRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new MythicRecipeGenerator(registryLookup, exporter, withConditions(exporter, new NuggetsLoadedCondition()));
    }

    @Override
    public String getName() {
        return "mythicmetals";
    }
}
