package nourl.mythicmetals.client.rendering;

import net.minecraft.block.BlockState;
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
import nourl.mythicmetals.entity.BanglumNukeEntity;
import nourl.mythicmetals.blocks.MythicBlocks;

//VanillaCopy of the TntEntityRenderer
public class BanglumNukeEntityRenderer extends EntityRenderer<BanglumNukeEntity> {
    private final BlockRenderManager blockRenderManager;
    public BanglumNukeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius=0.5f;
        blockRenderManager = context.getBlockRenderManager();
    }

    public void render(BanglumNukeEntity nuke, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.5, 0.0);
        int j = nuke.getFuse();
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

        matrixStack.translate(-1, 0, -1);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    matrixStack.push();
                    matrixStack.translate(x, y, z);

                    BlockState neededState = (x + y + z) % 2 == 0
                        ? MythicBlocks.BANGLUM.getStorageBlock().getDefaultState()
                        : MythicBlocks.MORKITE.getStorageBlock().getDefaultState();

                    TntMinecartEntityRenderer.renderFlashingBlock(blockRenderManager, neededState, matrixStack, vertexConsumerProvider, i, j / 5 % 2 == 0);

                    matrixStack.pop();
                }
            }
        }

        matrixStack.pop();
        super.render(nuke, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(BanglumNukeEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;

    }

}
