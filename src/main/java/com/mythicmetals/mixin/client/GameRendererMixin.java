package com.mythicmetals.mixin.client;

import com.mythicmetals.client.MythicRenderStateKeys;
import com.mythicmetals.client.rendering.OrichalcumHammerRenderState;
import com.mythicmetals.item.tools.HammerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.component.DataComponents;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(method = "renderItemInHand", at = @At("TAIL"))
    private void mythicmetals$captureHandRenderer(CameraRenderState cameraState, float deltaPartialTick, Matrix4fc modelViewMatrix, CallbackInfo ci) {
        var client = Minecraft.getInstance();
        if (client.player == null) return;
        var itemStack = client.player.getMainHandItem();
        if (itemStack.getItem() instanceof HammerBase hammer && itemStack.has(DataComponents.TOOL)) {
            var toolComponent = itemStack.get(DataComponents.TOOL);
            client.gameRenderer.getGameRenderState().levelRenderState.setData(
                MythicRenderStateKeys.ORICHALCUM_HAMMER_RENDER_STATE,
                new OrichalcumHammerRenderState(
                    hammer,
                    toolComponent
                )
            );
        }
    }
}
