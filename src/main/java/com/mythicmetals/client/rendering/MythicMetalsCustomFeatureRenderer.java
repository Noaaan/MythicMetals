package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.armor.CelestiumElytra;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.StarPlatCloakModel;
import com.mythicmetals.misc.MythicModelIdentifiers;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.model.player.PlayerCapeModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import org.jspecify.annotations.NonNull;

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
    public void submit(@NonNull PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, int light, AvatarRenderState avatarRenderState, float f, float g) {
        var chestItem = avatarRenderState.chestEquipment.getItem();
        if (!avatarRenderState.isInvisible && avatarRenderState.showCape) {
            // Respect the players capes, if they have any
            if (avatarRenderState.skin.cape() == null) {
                if (MythicMaterials.STAR_PLATINUM.armorSet() != null && chestItem == MythicMaterials.STAR_PLATINUM.armorSet().getChestplate()) {
                    renderStarPlatCape(poseStack, submitNodeCollector, light, avatarRenderState);
                }
                else if (MythicMaterials.HALLOWED.armorSet() != null && chestItem == MythicMaterials.HALLOWED.armorSet().getChestplate()) {
                    renderHallowedCape(poseStack, submitNodeCollector, light, avatarRenderState);
                }
            }

        } else if (chestItem instanceof CelestiumElytra) {
            renderCelestiumElytra(poseStack, submitNodeCollector, light, avatarRenderState);
        }
    }

    private void renderStarPlatCape(PoseStack ps, SubmitNodeCollector submitNodeCollector, int light, AvatarRenderState avatarRenderState) {
        ps.pushPose();
        this.getParentModel().copyTransforms(this.starPlatCape);
        starPlatCape.setupAnim(avatarRenderState);
        submitNodeCollector.submitModel(
            this.starPlatCape,
            avatarRenderState,
            ps,
            RenderTypes.entitySolid(MythicModelIdentifiers.STAR_PLATINUM_CLOAK),
            light,
            OverlayTexture.NO_OVERLAY,
            avatarRenderState.outlineColor,
            null
        );
        ps.popPose();
    }

    private void renderHallowedCape(PoseStack ps, SubmitNodeCollector submitNodeCollector, int light, AvatarRenderState avatarRenderState) {
        ps.pushPose();
        this.getParentModel().copyTransforms(this.hallowedCape);
        this.hallowedCape.setupAnim(avatarRenderState);
        submitNodeCollector.submitModel(
            this.hallowedCape,
            avatarRenderState,
            ps,
            RenderTypes.entitySolid(MythicModelIdentifiers.HALLOWED_CAPE),
            light,
            OverlayTexture.NO_OVERLAY,
            avatarRenderState.outlineColor,
            null
        );
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
                MythicModelIdentifiers.CELESTIUM_ELYTRA,
                renderState.lightCoords,
                renderState.outlineColor
            );
        poseStack.popPose();
    }
}
