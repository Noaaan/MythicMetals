package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.EnchantedMidasGoldBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;

import java.util.List;

public class EnchantedMidasBlockEntityRenderer implements BlockEntityRenderer<EnchantedMidasGoldBlockEntity, BlockEntityRenderState> {
    private final BlockRenderDispatcher blockRenderManager;

    public EnchantedMidasBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        blockRenderManager = ctx.blockRenderDispatcher();
    }

    @Override
    public BlockEntityRenderState createRenderState() {
        return null;
    }

    @Override
    public void submit(BlockEntityRenderState blockEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        // FIXME
//        poseStack.pushPose();
//        poseStack.translate(-0.001, -0.001, -0.001);
//        poseStack.scale(1.002f, 1.002f, 1.002f);
//        blockRenderManager.renderBatched(
//            MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.defaultBlockState(),
//            blockEntityRenderState.blockPos,
//            ,
//            poseStack,
//            ItemRenderer.getFoilBuffer(cameraRenderState., RenderTypes.cutoutMovingBlock(), true, true),
//            true,
//            List.of());
//        poseStack.popPose();
    }
}
