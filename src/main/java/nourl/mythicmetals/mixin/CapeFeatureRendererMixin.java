package nourl.mythicmetals.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.client.rendering.RenderingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(CapeFeatureRenderer.class)
public abstract class CapeFeatureRendererMixin extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public CapeFeatureRendererMixin(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> ctx) {
        super(ctx);
    }

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    public void render(MatrixStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayerEntity player, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (!player.isPartVisible(PlayerModelPart.CAPE) || player.getCapeTexture() != null) return;
        if (RenderingContext.elytraRendered || !LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.invoker().allowCapeRender(player)) return;
        if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == MythicArmor.HALLOWED.getChestplate().asItem()) {
            ms.push();
            ms.translate(0.0, 0.0, 0.125);
            double x = MathHelper.lerp(h, player.prevCapeX, player.capeX)
                    - MathHelper.lerp(h, player.prevX, player.getX());
            double y = MathHelper.lerp(h, player.prevCapeY, player.capeY)
                    - MathHelper.lerp(h, player.prevY, player.getY());
            double z = MathHelper.lerp(h, player.prevCapeZ, player.capeZ)
                    - MathHelper.lerp(h, player.prevZ, player.getZ());
            float yaw = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
            double o = MathHelper.sin(yaw * (float) (Math.PI / 180.0));
            double p = -MathHelper.cos(yaw * (float) (Math.PI / 180.0));
            float q = (float)y * 10.0F;
            q = MathHelper.clamp(q, -6.0F, 32.0F);
            float r = (float)(x * o + z * p) * 100.0F;
            r = MathHelper.clamp(r, 0.0F, 150.0F);
            float s = (float)(x * p - z * o) * 100.0F;
            s = MathHelper.clamp(s, -20.0F, 20.0F);
            if (r < 0.0F) {
                r = 0.0F;
            }

            float t = MathHelper.lerp(h, player.prevStrideDistance, player.strideDistance);
            q += MathHelper.sin(MathHelper.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;
            if (player.isInSneakingPose()) {
                q += 25.0F;
            }

            ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(6.0F + r / 2.0F + q));
            ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(s / 2.0F));
            ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - s / 2.0F));
            VertexConsumer vertexConsumer = vertices.getBuffer(RenderLayer.getEntitySolid(MythicModelHandler.HALLOWED_CAPE));
            this.getContextModel().renderCape(ms, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            ms.pop();
            ci.cancel();
        }
    }
}
