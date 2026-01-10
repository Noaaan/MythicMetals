package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.entity.BanglumNukeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

//VanillaCopy of the TntEntityRenderer
public class BanglumNukeEntityRenderer extends EntityRenderer<BanglumNukeEntity, BanglumNukeEntityState> {
    private final BlockRenderDispatcher blockRenderManager;

    public BanglumNukeEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
        blockRenderManager = context.getBlockRenderDispatcher();
    }

    @Override
    public BanglumNukeEntityState createRenderState() {
        return new BanglumNukeEntityState();
    }

    @Override
    public void render(BanglumNukeEntityState nuke, PoseStack matrices, MultiBufferSource vertexConsumers, int light) {
        matrices.pushPose();
        matrices.translate(0.0, 0.5, 0.0);
        int fuse = (int) nuke.fuse;
        if (fuse < 10.0F) {
            float g = 1.0F - fuse / 10.0F;
            g = Mth.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            matrices.scale(h, h, h);
        }

        matrices.mulPose(Axis.YP.rotationDegrees(-90.0F));
        matrices.translate(-0.5, -0.5, 0.5);
        matrices.mulPose(Axis.YP.rotationDegrees(90.0F));

        matrices.translate(-1, 0, -1);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    matrices.pushPose();
                    matrices.translate(x, y, z);

                    BlockState neededState = (x + y + z) % 2 == 0
                        ? MythicBlocks.BANGLUM.getStorageBlock().defaultBlockState()
                        : MythicBlocks.MORKITE.getStorageBlock().defaultBlockState();
                    TntMinecartRenderer.renderWhiteSolidBlock(blockRenderManager, neededState, matrices, vertexConsumers, light, fuse / 5 % 4 == 0);

                    matrices.popPose();
                }
            }
        }

        matrices.popPose();
        super.render(nuke, matrices, vertexConsumers, light);
    }

    @Override
    public void extractRenderState(BanglumNukeEntity entity, BanglumNukeEntityState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.fuse = entity.getFuse();
    }
}
