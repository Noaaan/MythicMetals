package com.mythicmetals.mixin.client;

import com.mythicmetals.client.MythicMetalsPlayerRenderContext;
import com.mythicmetals.client.MythicMetalsRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntityRenderState.class)
public class PlayerEntityRenderStateMixin implements MythicMetalsRenderState {

    @Unique
    protected MythicMetalsPlayerRenderContext mythicmetals$playerRenderContext;

    @Override
    public MythicMetalsPlayerRenderContext mythicmetals$getPlayerRenderContext() {
        return mythicmetals$playerRenderContext;
    }

    @Override
    public void mythicmetals$setPlayerRenderContext(MythicMetalsPlayerRenderContext context) {
        mythicmetals$playerRenderContext = context;
    }
}
