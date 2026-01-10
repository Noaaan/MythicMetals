package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.StarPlatCloakModel;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;

public class MythicMetalsCustomFeatureRenderer extends RenderLayer<PlayerRenderState, PlayerModel> {

    private final HumanoidModel<PlayerRenderState> starPlatCape;
    private final PlayerCapeModel<PlayerRenderState> hallowedCape;
    // TODO - Use custom model instead of vanilla Elytra
    private final ElytraModel celestiumElytra;
    private final ElytraModel babyCelestiumElytra;
    private final EquipmentLayerRenderer equipmentRenderer;

    public MythicMetalsCustomFeatureRenderer(RenderLayerParent<PlayerRenderState, PlayerModel> context, EntityModelSet modelLoader, EquipmentLayerRenderer equipmentRenderer) {
        super(context);

        this.starPlatCape = new StarPlatCloakModel<>(modelLoader.bakeLayer(MythicModelHandler.STAR_PLATINUM_CLOAK));
        this.hallowedCape = new PlayerCapeModel<>(modelLoader.bakeLayer(ModelLayers.PLAYER_CAPE));
        this.celestiumElytra = new ElytraModel(modelLoader.bakeLayer(MythicModelHandler.CELESTIUM_ELYTRA));
        this.babyCelestiumElytra = new ElytraModel(modelLoader.bakeLayer(MythicModelHandler.BABY_CELESTIUM_ELYTRA));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, PlayerRenderState playerRenderState, float limbAngle, float limbDistance) {
        if (!playerRenderState.isInvisible && playerRenderState.showCape) {
            var chestItem = playerRenderState.chestEquipment.getItem();
            // Respect the players capes, if they have any
            if (playerRenderState.skin.capeTexture() == null) {
                if (chestItem == MythicArmor.STAR_PLATINUM.getChestplate()) {
                    renderStarPlatCape(matrices, vertexConsumers, light, playerRenderState);
                }
                else if (chestItem == MythicArmor.HALLOWED.getChestplate()) {
                    renderHallowedCape(matrices, vertexConsumers, light, playerRenderState);
                }
            }
            if (chestItem == MythicArmor.CELESTIUM_ELYTRA) {
                renderCelestiumElytra(matrices, vertexConsumers, light, playerRenderState);
            }
        }
    }

    private void renderStarPlatCape(PoseStack ms, MultiBufferSource vertices, int light, PlayerRenderState playerEntityRenderState) {
        ms.pushPose();
        this.getParentModel().copyPropertiesTo(this.starPlatCape);
        var vertexConsumer = vertices.getBuffer(RenderType.entitySolid(MythicModelHandler.STAR_PLATINUM_CLOAK_TEXTURE));
        starPlatCape.setupAnim(playerEntityRenderState);
        starPlatCape.renderToBuffer(ms, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        ms.popPose();
    }

    private void renderHallowedCape(PoseStack ms, MultiBufferSource vertices, int light, PlayerRenderState playerEntityRenderState) {
        ms.pushPose();
        var vertexConsumer = vertices.getBuffer(RenderType.entitySolid(MythicModelHandler.HALLOWED_CAPE));
        this.getParentModel().copyPropertiesTo(this.hallowedCape);
        this.hallowedCape.setupAnim(playerEntityRenderState);
        this.hallowedCape.renderToBuffer(ms, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
        ms.popPose();
    }

    private void renderCelestiumElytra(PoseStack matrixStack, MultiBufferSource vpo, int light, HumanoidRenderState renderState) {
        ElytraModel elytraEntityModel = renderState.isBaby ? this.babyCelestiumElytra : this.celestiumElytra;
        matrixStack.pushPose();
        matrixStack.translate(0.0F, 0.0F, 0.125F);
        elytraEntityModel.setupAnim(renderState);
        this.equipmentRenderer
            .renderLayers(
                EquipmentClientInfo.LayerType.WINGS,
                RegistryHelper.equipmentAsset("celestium_elytra"),
                elytraEntityModel,
                renderState.chestEquipment,
                matrixStack,
                vpo,
                light,
                MythicModelHandler.CELESTIUM_ELYTRA_TEXTURE
            );
        matrixStack.popPose();
    }
}
