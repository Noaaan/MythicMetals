package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mythicmetals.client.models.MythicModelHandler;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.entity.state.MinecartRenderState;
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
    public void render(MinecartRenderState minecartEntityRenderState, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        super.render(minecartEntityRenderState, matrixStack, vertexConsumerProvider, i);
        matrixStack.pushPose();
        long l = minecartEntityRenderState.offsetSeed;
        float f = (((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float g = (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float h = (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        matrixStack.translate(f, g, h);
        if (minecartEntityRenderState.isNewRender) {
            newRender(minecartEntityRenderState, matrixStack);
        } else {
            oldRender(minecartEntityRenderState, matrixStack);
        }

        float j = minecartEntityRenderState.hurtTime;
        if (j > 0.0F) {
            matrixStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(j) * j * minecartEntityRenderState.damageTime / 10.0F * (float)minecartEntityRenderState.hurtDir));
        }

        BlockState blockState = minecartEntityRenderState.displayBlockState;
        if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
            matrixStack.pushPose();
            float k = 0.75F;
            matrixStack.scale(0.75F, 0.75F, 0.75F);
            matrixStack.translate(-0.5F, (float)(minecartEntityRenderState.displayOffset - 8) / 16.0F, 0.5F);
            matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            this.renderMinecartContents(minecartEntityRenderState, blockState, matrixStack, vertexConsumerProvider, i);
            matrixStack.popPose();
        }

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.setupAnim(minecartEntityRenderState);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(MythicModelHandler.PALLADIUM_MINECART_TEXTURE));
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        matrixStack.popPose();
    }
}
