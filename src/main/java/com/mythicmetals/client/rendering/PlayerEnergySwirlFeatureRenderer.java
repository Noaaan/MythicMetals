package com.mythicmetals.client.rendering;

import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.MythicMetalsRenderState;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

public class PlayerEnergySwirlFeatureRenderer<S extends PlayerEntityRenderState, M extends PlayerEntityModel> extends FeatureRenderer<S, M> {

    public static final Identifier SWIRL_TEXTURE = RegistryHelper.id("textures/models/carmot_shield.png");
    private final PlayerEntityModel swirlModel;

    public PlayerEnergySwirlFeatureRenderer(
        FeatureRendererContext<S, M> context,
        LoadedEntityModels loader,
        EquipmentRenderer equipmentRenderer) {
        super(context);
        this.swirlModel = new PlayerEntityModel(loader.getModelPart(MythicModelHandler.CARMOT_SWIRL), false);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, S state, float limbAngle, float limbDistance) {
        if (state instanceof MythicMetalsRenderState mmstate && mmstate.mythicmetals$getPlayerRenderContext().carmotShield().shouldRenderShield()) {
            var shield = mmstate.mythicmetals$getPlayerRenderContext().carmotShield();
            float f = state.age ; // TODO - Add tickdelta?

            this.swirlModel.copyTransforms(this.getContextModel());

            var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));
            this.swirlModel.setAngles(state);
            // Break animation
            if (shield.cooldown > CarmotShield.MAX_COOLDOWN - 30) {
                matrices.scale(1.125f, 1.0625f, 1.125f);
                this.swirlModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, SHIELD_BREAK_COLOR);
            } else // Regular animation
                this.swirlModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, UsefulSingletonForColorUtil.rainbow());
        }
    }
}
