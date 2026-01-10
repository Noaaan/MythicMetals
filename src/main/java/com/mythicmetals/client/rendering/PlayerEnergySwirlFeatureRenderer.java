package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.MythicMetalsRenderState;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

public class PlayerEnergySwirlFeatureRenderer<S extends PlayerRenderState, M extends PlayerModel> extends RenderLayer<S, M> {

    public static final ResourceLocation SWIRL_TEXTURE = RegistryHelper.id("textures/models/carmot_shield.png");
    private final PlayerModel swirlModel;

    public PlayerEnergySwirlFeatureRenderer(
        RenderLayerParent<S, M> context,
        EntityModelSet loader) {
        super(context);
        this.swirlModel = new PlayerModel(loader.bakeLayer(MythicModelHandler.CARMOT_SWIRL), false);
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, S state, float limbAngle, float limbDistance) {
        if (state instanceof MythicMetalsRenderState mmstate && mmstate.mythicmetals$getPlayerRenderContext().carmotShield().shouldRenderShield()) {
            var shield = mmstate.mythicmetals$getPlayerRenderContext().carmotShield();
            this.swirlModel.copyPropertiesTo(this.getParentModel());
            var consumer = vertexConsumers.getBuffer(RenderType.energySwirl(SWIRL_TEXTURE, (state.ageInTicks * .005f) % 1f, state.ageInTicks * .005f % 1f));
            this.swirlModel.setupAnim(state);
            // Break animation
            if (shield.cooldown > CarmotShield.MAX_COOLDOWN - 30) {
                matrices.scale(1.125f, 1.0625f, 1.125f);
                this.swirlModel.renderToBuffer(matrices, consumer, light, OverlayTexture.NO_OVERLAY, SHIELD_BREAK_COLOR);
            } else // Regular animation
                this.swirlModel.renderToBuffer(matrices, consumer, light, OverlayTexture.NO_OVERLAY, UsefulSingletonForColorUtil.rainbow());
        }
    }
}
