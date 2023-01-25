package nourl.mythicmetals.client.rendering;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import nourl.mythicmetals.blocks.BanglumTntEntity;
import nourl.mythicmetals.blocks.MythicBlocks;

//VanillaCopy of the TntEntityRenderer
public class BanglumTntEntityRenderer extends EntityRenderer<BanglumTntEntity> {
    private final BlockRenderManager blockRenderManager;
    public BanglumTntEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius=0.5f;
        blockRenderManager = context.getBlockRenderManager();
    }

    public void render(BanglumTntEntity banglumTnt, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.5, 0.0);
        int j = banglumTnt.getFuse();
        if ((float)j - g + 1.0F < 10.0F) {
            float h = 1.0F - ((float)j - g + 1.0F) / 10.0F;
            h = MathHelper.clamp(h, 0.0F, 1.0F);
            h *= h;
            h *= h;
            float k = 1.0F + h * 0.3F;
            matrixStack.scale(k, k, k);
        }

        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
        matrixStack.translate(-0.5, -0.5, 0.5);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
        TntMinecartEntityRenderer.renderFlashingBlock(blockRenderManager, MythicBlocks.BANGLUM_TNT_BLOCK.getDefaultState(), matrixStack, vertexConsumerProvider, i, j / 5 % 2 == 0);
        matrixStack.pop();
        super.render(banglumTnt, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BanglumTntEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;

    }

}
