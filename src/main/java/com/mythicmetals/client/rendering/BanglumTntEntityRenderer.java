package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mythicmetals.entity.BanglumTntEntity;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.TntRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;

public class BanglumTntEntityRenderer extends EntityRenderer<BanglumTntEntity, TntRenderState> {

    public BanglumTntEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5F;
    }

    @Override
    public void submit(TntRenderState entityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5F, 0.0F);
        float f = entityRenderState.fuseRemainingInTicks;
        if (entityRenderState.fuseRemainingInTicks < 10.0F) {
            float g = 1.0F - entityRenderState.fuseRemainingInTicks / 10.0F;
            g = Mth.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            poseStack.scale(h, h, h);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5F, -0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        if (entityRenderState.blockState != null) {
            TntMinecartRenderer.submitWhiteSolidBlock(
                entityRenderState.blockState, poseStack, submitNodeCollector, entityRenderState.lightCoords, (int)f / 5 % 2 == 0, entityRenderState.outlineColor
            );
        }

        poseStack.popPose();
        super.submit(entityRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    public TntRenderState createRenderState() {
        return new TntRenderState();
    }

    public void extractRenderState(BanglumTntEntity tntEntity, TntRenderState tntEntityRenderState, float f) {
        super.extractRenderState(tntEntity, tntEntityRenderState, f);
        tntEntityRenderState.fuseRemainingInTicks = tntEntity.getFuse() - f + 1.0F;
        tntEntityRenderState.blockState = MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT).defaultBlockState();
    }

}
