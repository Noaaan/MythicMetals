package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.entity.BanglumTntEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.util.Mth;

public class BanglumTntEntityRenderer extends EntityRenderer<BanglumTntEntity, TntRenderState> {
    private final BlockRenderDispatcher blockRenderManager;

    public BanglumTntEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
        this.blockRenderManager = context.getBlockRenderDispatcher();
    }

    public void render(TntRenderState tntEntityRenderState, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        float f = tntEntityRenderState.fuseRemainingInTicks;
        if (tntEntityRenderState.fuseRemainingInTicks < 10.0F) {
            float g = 1.0F - tntEntityRenderState.fuseRemainingInTicks / 10.0F;
            g = Mth.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            matrixStack.scale(h, h, h);
        }

        matrixStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        matrixStack.translate(-0.5F, -0.5F, 0.5F);
        matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        if (tntEntityRenderState.blockState != null) {
            TntMinecartRenderer.renderWhiteSolidBlock(
                this.blockRenderManager, tntEntityRenderState.blockState, matrixStack, vertexConsumerProvider, i, (int)f / 5 % 2 == 0
            );
        }

        matrixStack.popPose();
        super.render(tntEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void extractRenderState(BanglumTntEntity tntEntity, TntRenderState tntEntityRenderState, float f) {
        super.extractRenderState(tntEntity, tntEntityRenderState, f);
        tntEntityRenderState.fuseRemainingInTicks = tntEntity.getFuse() - f + 1.0F;
        tntEntityRenderState.blockState = MythicBlocks.BANGLUM_TNT_BLOCK.defaultBlockState();
    }

}
