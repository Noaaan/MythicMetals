package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.StarPlatCloakModel;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.model.player.PlayerCapeModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;

public class MythicMetalsCustomFeatureRenderer extends RenderLayer<AvatarRenderState, PlayerModel> {

    private final HumanoidModel<AvatarRenderState> starPlatCape;
    private final PlayerCapeModel hallowedCape;
    // TODO - Use custom model instead of vanilla Elytra
    private final ElytraModel celestiumElytra;
    private final ElytraModel babyCelestiumElytra;
    private final EquipmentLayerRenderer equipmentRenderer;

    public MythicMetalsCustomFeatureRenderer(RenderLayerParent<AvatarRenderState, PlayerModel> context, EntityModelSet modelLoader, EquipmentLayerRenderer equipmentRenderer) {
        super(context);

        this.starPlatCape = new StarPlatCloakModel<>(modelLoader.bakeLayer(MythicModelHandler.STAR_PLATINUM_CLOAK));
        this.hallowedCape = new PlayerCapeModel(modelLoader.bakeLayer(ModelLayers.PLAYER_CAPE));
        this.celestiumElytra = new ElytraModel(modelLoader.bakeLayer(MythicModelHandler.CELESTIUM_ELYTRA));
        this.babyCelestiumElytra = new ElytraModel(modelLoader.bakeLayer(MythicModelHandler.BABY_CELESTIUM_ELYTRA));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, AvatarRenderState avatarRenderState, float f, float g) {
        var chestItem = avatarRenderState.chestEquipment.getItem();
        if (!avatarRenderState.isInvisible && avatarRenderState.showCape) {
//            // Respect the players capes, if they have any
//            if (avatarRenderState.skin.cape() == null) {
//                if (chestItem == MythicArmor.STAR_PLATINUM.getChestplate()) {
//                    renderStarPlatCape(poseStack, vertexConsumers, light, avatarRenderState);
//                }
//                else if (chestItem == MythicArmor.HALLOWED.getChestplate()) {
//                    renderHallowedCape(poseStack, vertexConsumers, light, avatarRenderState);
//                }
//            }

        } else if (chestItem == MythicArmor.CELESTIUM_ELYTRA) {
            renderCelestiumElytra(poseStack, submitNodeCollector, light, avatarRenderState);
        }
    }

    private void renderStarPlatCape(PoseStack ps, MultiBufferSource vertices, int light, AvatarRenderState avatarRenderState) {
        ps.pushPose();
        this.getParentModel().copyTransforms(this.starPlatCape);
        var vertexConsumer = vertices.getBuffer(RenderTypes.entitySolid(MythicModelHandler.STAR_PLATINUM_CLOAK_TEXTURE));
        starPlatCape.setupAnim(avatarRenderState);
        starPlatCape.renderToBuffer(ps, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        ps.popPose();
    }

    private void renderHallowedCape(PoseStack ps, MultiBufferSource vertices, int light, AvatarRenderState avatarRenderState) {
        ps.pushPose();
        var vertexConsumer = vertices.getBuffer(RenderTypes.entitySolid(MythicModelHandler.HALLOWED_CAPE));
        this.getParentModel().copyTransforms(this.hallowedCape);
        this.hallowedCape.setupAnim(avatarRenderState);
        this.hallowedCape.renderToBuffer(ps, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        ps.popPose();
    }

    private void renderCelestiumElytra(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, HumanoidRenderState renderState) {
        ElytraModel elytraEntityModel = renderState.isBaby ? this.babyCelestiumElytra : this.celestiumElytra;
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.0F, 0.125F);
        elytraEntityModel.setupAnim(renderState);
        this.equipmentRenderer
            .renderLayers(
                EquipmentClientInfo.LayerType.WINGS,
                RegistryHelper.equipmentAsset("celestium_elytra"),
                elytraEntityModel,
                renderState,
                renderState.chestEquipment,
                poseStack,
                submitNodeCollector,
                light,
                MythicModelHandler.CELESTIUM_ELYTRA_TEXTURE,
                renderState.lightCoords,
                renderState.outlineColor
            );
        poseStack.popPose();
    }
}
