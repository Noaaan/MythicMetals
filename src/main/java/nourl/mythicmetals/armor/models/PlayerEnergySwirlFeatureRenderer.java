package nourl.mythicmetals.armor.models;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.utils.RegistryHelper;

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
        float f = entity.age + tickDelta;

        this.swirlModel.animateModel(entity, limbAngle, limbDistance, tickDelta);
        this.getContextModel().copyStateTo(this.swirlModel);
        this.getContextModel().setAttributes(this.swirlModel);

        var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));
        this.swirlModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.swirlModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, .8f, .1f, .05f, 1);
    }
}
