package com.mythicmetals.client.rendering;

import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.StarPlatCloakModel;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

public class MythicMetalsCustomFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {

    private final BipedEntityModel<PlayerEntityRenderState> starPlatCape;
    private final PlayerCapeModel<PlayerEntityRenderState> hallowedCape;

    public MythicMetalsCustomFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context, LoadedEntityModels modelLoader) {
        super(context);

        this.starPlatCape = new StarPlatCloakModel<>(modelLoader.getModelPart(MythicModelHandler.STAR_PLATINUM_CLOAK));
        this.hallowedCape = new PlayerCapeModel<>(modelLoader.getModelPart(EntityModelLayers.PLAYER_CAPE));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState playerRenderState, float limbAngle, float limbDistance) {
        if (!playerRenderState.invisible && playerRenderState.capeVisible) {
            var chestItem = playerRenderState.equippedChestStack.getItem();
            // Respect the players capes, if they have any
            if (playerRenderState.skinTextures.capeTexture() == null) {
                if (chestItem == MythicArmor.STAR_PLATINUM.getChestplate()) {
                    renderStarPlatCape(matrices, vertexConsumers, light, playerRenderState);
                }
                else if (chestItem == MythicArmor.HALLOWED.getChestplate()) {
                    renderHallowedCape(matrices, vertexConsumers, light, playerRenderState);
                }
            } else if (chestItem == MythicArmor.CELESTIUM_ELYTRA) {
                // TODO - Render Celestium Elytra
            }
        }
    }

    private void renderStarPlatCape(MatrixStack ms, VertexConsumerProvider vertices, int light, PlayerEntityRenderState playerEntityRenderState) {
        ms.push();
        this.getContextModel().copyTransforms(this.starPlatCape);
        var vertexConsumer = vertices.getBuffer(RenderLayer.getEntitySolid(MythicModelHandler.STAR_PLATINUM_CLOAK_TEXTURE));
        starPlatCape.setAngles(playerEntityRenderState);
        starPlatCape.render(ms, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        ms.pop();
    }

    private void renderHallowedCape(MatrixStack ms, VertexConsumerProvider vertices, int light, PlayerEntityRenderState playerEntityRenderState) {
        ms.push();
        var vertexConsumer = vertices.getBuffer(RenderLayer.getEntitySolid(MythicModelHandler.HALLOWED_CAPE));
        this.getContextModel().copyTransforms(this.hallowedCape);
        this.hallowedCape.setAngles(playerEntityRenderState);
        this.hallowedCape.render(ms, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        ms.pop();
    }
}
