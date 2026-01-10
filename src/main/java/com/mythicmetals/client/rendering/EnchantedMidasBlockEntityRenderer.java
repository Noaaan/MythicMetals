package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.EnchantedMidasGoldBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.RandomSource;

public class EnchantedMidasBlockEntityRenderer implements BlockEntityRenderer<EnchantedMidasGoldBlockEntity> {
    private final BlockRenderDispatcher blockRenderManager;

    public EnchantedMidasBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        blockRenderManager = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(EnchantedMidasGoldBlockEntity midasBlockEntity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        matrices.pushPose();
        matrices.translate(-0.001, -0.001, -0.001);
        matrices.scale(1.002f, 1.002f, 1.002f);
        blockRenderManager.renderBatched(
            MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.defaultBlockState(),
            midasBlockEntity.getBlockPos(),
            midasBlockEntity.getLevel(),
            matrices,
            ItemRenderer.getFoilBuffer(vertexConsumers, RenderType.cutoutMipped(), true, true),
            true,
            RandomSource.create());
        matrices.popPose();
    }
}
