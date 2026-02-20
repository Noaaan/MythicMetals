package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mythicmetals.client.models.MythicModelHandler;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.entity.state.MinecartRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public class PalladiumMinecartRenderer extends MinecartRenderer {

    public PalladiumMinecartRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, MythicModelHandler.PALLADIUM_MINECART);
    }

    // [VanillaCopy] texture is hardcoded, so we need to rerender all of it
    @Override
    public void submit(MinecartRenderState minecartRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        super.submit(minecartRenderState, poseStack, submitNodeCollector, cameraRenderState);
        poseStack.pushPose();
        long l = minecartRenderState.offsetSeed;
        float f = (((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float g = (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float h = (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        poseStack.translate(f, g, h);
        if (minecartRenderState.isNewRender) {
            newRender(minecartRenderState, poseStack);
        } else {
            oldRender(minecartRenderState, poseStack);
        }

        float i = minecartRenderState.hurtTime;
        if (i > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(i) * i * minecartRenderState.damageTime / 10.0F * minecartRenderState.hurtDir));
        }

        BlockState blockState = minecartRenderState.displayBlockState;
        if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
            poseStack.pushPose();
            poseStack.scale(0.75F, 0.75F, 0.75F);
            poseStack.translate(-0.5F, (minecartRenderState.displayOffset - 8) / 16.0F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            this.submitMinecartContents(minecartRenderState, blockState, poseStack, submitNodeCollector, minecartRenderState.lightCoords);
            poseStack.popPose();
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        submitNodeCollector.submitModel(
            this.model,
            minecartRenderState,
            poseStack,
            this.model.renderType(MythicModelHandler.PALLADIUM_MINECART_TEXTURE),
            minecartRenderState.lightCoords,
            OverlayTexture.NO_OVERLAY,
            minecartRenderState.outlineColor,
            null
        );
        poseStack.popPose();
    }
}
