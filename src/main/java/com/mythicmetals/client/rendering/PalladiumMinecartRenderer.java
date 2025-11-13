package com.mythicmetals.client.rendering;

import com.mythicmetals.client.models.MythicModelHandler;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.state.MinecartEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class PalladiumMinecartRenderer extends MinecartEntityRenderer {

    public PalladiumMinecartRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, MythicModelHandler.PALLADIUM_MINECART);
    }

    // [VanillaCopy] texture is hardcoded, so we need to rerender all of it
    @Override
    public void render(MinecartEntityRenderState minecartEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(minecartEntityRenderState, matrixStack, vertexConsumerProvider, i);
        matrixStack.push();
        long l = minecartEntityRenderState.hash;
        float f = (((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float g = (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float h = (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        matrixStack.translate(f, g, h);
        if (minecartEntityRenderState.usesExperimentalController) {
            transformExperimentalControllerMinecart(minecartEntityRenderState, matrixStack);
        } else {
            transformDefaultControllerMinecart(minecartEntityRenderState, matrixStack);
        }

        float j = minecartEntityRenderState.damageWobbleTicks;
        if (j > 0.0F) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.sin(j) * j * minecartEntityRenderState.damageWobbleStrength / 10.0F * (float)minecartEntityRenderState.damageWobbleSide));
        }

        BlockState blockState = minecartEntityRenderState.containedBlock;
        if (blockState.getRenderType() != BlockRenderType.INVISIBLE) {
            matrixStack.push();
            float k = 0.75F;
            matrixStack.scale(0.75F, 0.75F, 0.75F);
            matrixStack.translate(-0.5F, (float)(minecartEntityRenderState.blockOffset - 8) / 16.0F, 0.5F);
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
            this.renderBlock(minecartEntityRenderState, blockState, matrixStack, vertexConsumerProvider, i);
            matrixStack.pop();
        }

        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.setAngles(minecartEntityRenderState);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(MythicModelHandler.PALLADIUM_MINECART_TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
    }
}
