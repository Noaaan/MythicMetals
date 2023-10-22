package nourl.mythicmetals.client.rendering;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.random.Random;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.blocks.EnchantedMidasGoldBlockEntity;

public class EnchantedMidasBlockEntityRenderer implements BlockEntityRenderer<EnchantedMidasGoldBlockEntity> {
    private final BlockRenderManager blockRenderManager;
    public EnchantedMidasBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        blockRenderManager = ctx.getRenderManager();
    }

    @Override
    public void render(EnchantedMidasGoldBlockEntity midasBlockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        blockRenderManager.renderBlock(
                MythicBlocks.MIDAS_GOLD.getStorageBlock().getDefaultState(),
                midasBlockEntity.getPos(),
                midasBlockEntity.getWorld(),
                matrices,
                ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderLayer.getCutoutMipped(), true, true),
                true,
                Random.create());
        matrices.pop();
    }
}
