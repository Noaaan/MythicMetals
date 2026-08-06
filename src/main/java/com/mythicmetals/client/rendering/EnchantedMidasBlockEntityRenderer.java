package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.block.entity.EnchantedMidasGoldBlockEntity;
import com.mythicmetals.item.MythicMaterials;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class EnchantedMidasBlockEntityRenderer implements BlockEntityRenderer<EnchantedMidasGoldBlockEntity, BlockEntityRenderState> {
    final BlockModelResolver blockModelResolver;

    public EnchantedMidasBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        blockModelResolver = ctx.blockModelResolver();
    }

    @Override
    public BlockEntityRenderState createRenderState() {
        return new BlockEntityRenderState();
    }

    @Override
    public void submit(BlockEntityRenderState blockEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        // FIXME
/*        var blockModel = blockRenderManager.getBlockModel(MythicMaterials.MIDAS_GOLD.blockSet().storage().defaultBlockState());
        poseStack.pushPose();
        submitNodeCollector.submitBlockModel(
            poseStack,
            RenderTypes.glintTranslucent(),
            blockModel,
            1.0f,
            1.0f,
            1.0f,
            blockEntityRenderState.lightCoords,
            OverlayTexture.NO_OVERLAY,
            0
            );
        poseStack.popPose();*/
    }
}
