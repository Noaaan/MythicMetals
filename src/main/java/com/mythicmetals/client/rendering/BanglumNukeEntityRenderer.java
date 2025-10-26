package com.mythicmetals.client.rendering;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.entity.BanglumNukeEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

//VanillaCopy of the TntEntityRenderer
public class BanglumNukeEntityRenderer extends EntityRenderer<BanglumNukeEntity, BanglumNukeEntityState> {
    private final BlockRenderManager blockRenderManager;

    public BanglumNukeEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
        blockRenderManager = context.getBlockRenderManager();
    }

    @Override
    public BanglumNukeEntityState createRenderState() {
        return new BanglumNukeEntityState();
    }

    @Override
    public void render(BanglumNukeEntityState nuke, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.translate(0.0, 0.5, 0.0);
        int fuse = (int) nuke.fuse;
        if (fuse < 10.0F) {
            float g = 1.0F - fuse / 10.0F;
            g = MathHelper.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            matrices.scale(h, h, h);
        }

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
        matrices.translate(-0.5, -0.5, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));

        matrices.translate(-1, 0, -1);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    matrices.push();
                    matrices.translate(x, y, z);

                    BlockState neededState = (x + y + z) % 2 == 0
                        ? MythicBlocks.BANGLUM.getStorageBlock().getDefaultState()
                        : MythicBlocks.MORKITE.getStorageBlock().getDefaultState();
                    TntMinecartEntityRenderer.renderFlashingBlock(blockRenderManager, neededState, matrices, vertexConsumers, light, fuse / 5 % 4 == 0);

                    matrices.pop();
                }
            }
        }

        matrices.pop();
        super.render(nuke, matrices, vertexConsumers, light);
    }

    @Override
    public void updateRenderState(BanglumNukeEntity entity, BanglumNukeEntityState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.fuse = entity.getFuse();
    }
}
