package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.MythicRenderStateKeys;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class LivingEntityEnergySwirlFeatureRenderer<S extends EntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    public static final Identifier SWIRL_TEXTURE = RegistryHelper.id("textures/models/carmot_shield.png");
    private final M swirlModel;

    public LivingEntityEnergySwirlFeatureRenderer(
        RenderLayerParent<S, M> context,
        EntityModelSet loader) {
        super(context);
        swirlModel = context.getModel();
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, S entityRenderState, float f, float g) {
        // FIXME - Improve Carmot Shield animations
        var shield = entityRenderState.getDataOrDefault(MythicRenderStateKeys.CARMOT_SHIELD_STATE_KEY, CarmotShield.NONE);
        if (shield.shieldHealth() <= 0.0) return;
        if (shield.isHurt()) {
            this.swirlModel.copyTransforms(this.getParentModel());
            this.swirlModel.setupAnim(entityRenderState);
            poseStack.scale(1.125f, 1.0625f, 1.125f);
            //noinspection DataFlowIssue
            submitNodeCollector.submitModel(
                swirlModel,
                entityRenderState,
                poseStack,
                RenderTypes.energySwirl(SWIRL_TEXTURE, (entityRenderState.ageInTicks * .005f) % 1f, entityRenderState.ageInTicks * .005f % 1f),
                i,
                OverlayTexture.NO_OVERLAY,
                UsefulSingletonForColorUtil.rainbow(),
                null,
                entityRenderState.outlineColor,
                null
            );
        }
    }
}
