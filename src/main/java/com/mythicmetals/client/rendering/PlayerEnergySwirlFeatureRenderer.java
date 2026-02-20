package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.MythicMetalsRenderState;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

public class PlayerEnergySwirlFeatureRenderer<S extends AvatarRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {

    public static final Identifier SWIRL_TEXTURE = RegistryHelper.id("textures/models/carmot_shield.png");
    private final PlayerModel swirlModel;

    public PlayerEnergySwirlFeatureRenderer(
        RenderLayerParent<S, M> context,
        EntityModelSet loader) {
        super(context);
        this.swirlModel = new PlayerModel(loader.bakeLayer(MythicModelHandler.CARMOT_SWIRL), false);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, S entityRenderState, float f, float g) {
        if (entityRenderState instanceof MythicMetalsRenderState mmstate && mmstate.mythicmetals$getPlayerRenderContext().carmotShield().shouldRenderShield()) {
            var shield = mmstate.mythicmetals$getPlayerRenderContext().carmotShield();
            this.swirlModel.copyTransforms(this.getParentModel());
            this.swirlModel.setupAnim(entityRenderState);
            // Break animation
            if (shield.cooldown() > CarmotShield.MAX_COOLDOWN - 30) {
                poseStack.scale(1.125f, 1.0625f, 1.125f);
                submitNodeCollector.submitModel(
                    swirlModel,
                    entityRenderState,
                    poseStack,
                    RenderTypes.energySwirl(SWIRL_TEXTURE, (entityRenderState.ageInTicks * .005f) % 1f, entityRenderState.ageInTicks * .005f % 1f),
                    i,
                    OverlayTexture.NO_OVERLAY,
                    SHIELD_BREAK_COLOR,
                    null,
                    entityRenderState.outlineColor,
                    null
                    );
            } else // Regular animation
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
