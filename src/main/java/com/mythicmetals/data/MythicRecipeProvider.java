package com.mythicmetals.data;

import com.mythicmetals.conditions.NuggetsLoadedCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class MythicRecipeProvider extends FabricRecipeProvider {

    public MythicRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new MythicRecipeGenerator(registryLookup, exporter, withConditions(exporter, new NuggetsLoadedCondition()));
    }


    @Override
    public String getName() {
        return "mythicmetals";
    }
}
