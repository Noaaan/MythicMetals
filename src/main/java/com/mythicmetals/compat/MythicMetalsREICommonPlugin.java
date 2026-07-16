package com.mythicmetals.compat;

import com.mythicmetals.data.recipe.MidasFoldingRecipe;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;

public class MythicMetalsREICommonPlugin implements REICommonPlugin {

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(MidasFoldingRecipe.class)
            .fill(MidasFoldingDisplay::new);
    }
}
