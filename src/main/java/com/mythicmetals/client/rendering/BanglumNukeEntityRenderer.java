package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mythicmetals.entity.BanglumNukeEntity;
import com.mythicmetals.item.MythicMaterials;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;

//VanillaCopy of the TntEntityRenderer
public class BanglumNukeEntityRenderer extends EntityRenderer<BanglumNukeEntity, BanglumNukeEntityRenderState> {
    private final BlockModelResolver blockModelResolver;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    public BanglumNukeEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
        blockModelResolver = context.getBlockModelResolver();
    }

    @Override
    public BanglumNukeEntityRenderState createRenderState() {
        return new BanglumNukeEntityRenderState();
    }

    @Override
    public void submit(BanglumNukeEntityRenderState nukeRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.5, 0.0);
        int fuse = (int) nukeRenderState.fuse;
        if (fuse < 10.0F) {
            float g = 1.0F - fuse / 10.0F;
            g = Mth.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            poseStack.scale(h, h, h);
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        poseStack.translate(-0.5, -0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));

        poseStack.translate(-1, 0, -1);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    poseStack.pushPose();
                    poseStack.translate(x, y, z);

                    var neededState = (x + y + z) % 2 == 0
                        ? nukeRenderState.banglum
                        : nukeRenderState.morkite;
                    TntMinecartRenderer.submitWhiteSolidBlock(
                        neededState, poseStack, submitNodeCollector, nukeRenderState.lightCoords, fuse / 5 % 2 == 0, nukeRenderState.outlineColor
                    );

                    poseStack.popPose();
                }
            }
        }

        poseStack.popPose();
        super.submit(nukeRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public void extractRenderState(BanglumNukeEntity entity, BanglumNukeEntityRenderState state, float tickDelta) {
        super.extractRenderState(entity, state, tickDelta);
        state.fuse = entity.getFuse();
        this.blockModelResolver.update(state.banglum, MythicMaterials.BANGLUM.blockSet().storage().defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
        this.blockModelResolver.update(state.morkite, MythicMaterials.MORKITE.blockSet().storage().defaultBlockState(), BLOCK_DISPLAY_CONTEXT);
    }
}
