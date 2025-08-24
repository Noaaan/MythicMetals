package com.mythicmetals.client.rendering;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

public class PlayerEnergySwirlFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public static final Identifier SWIRL_TEXTURE = RegistryHelper.id("textures/models/carmot_shield.png");

    private final PlayerEntityModel<AbstractClientPlayerEntity> swirlModel;

    public PlayerEnergySwirlFeatureRenderer(
        FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context,
        EntityModelLoader loader) {
        super(context);
        this.swirlModel = new PlayerEntityModel<>(loader.getModelPart(MythicModelHandler.CARMOT_SWIRL), false);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getComponent(MythicMetals.CARMOT_SHIELD).shouldRenderShield()) {
            var shield = entity.getComponent(MythicMetals.CARMOT_SHIELD);
            float f = entity.age + tickDelta;

            this.swirlModel.animateModel(entity, limbAngle, limbDistance, tickDelta);
            this.getContextModel().copyStateTo(this.swirlModel);
            this.getContextModel().copyBipedStateTo(this.swirlModel);

            var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));
            this.swirlModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            // Break animation
            if (shield.cooldown > CarmotShield.MAX_COOLDOWN - 30) {
                matrices.scale(1.125f, 1.0625f, 1.125f);
                this.swirlModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, SHIELD_BREAK_COLOR);
            } else // Regular animation
                this.swirlModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, UsefulSingletonForColorUtil.rainbow());
        }
    }
}
