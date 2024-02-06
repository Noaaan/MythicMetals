package nourl.mythicmetals.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.*;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.client.models.StarPlatCloakModel;
import nourl.mythicmetals.client.rendering.RenderingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
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
        if (!player.isPartVisible(PlayerModelPart.CAPE) || player.getSkinTextures().capeTexture() != null) return;
        if (RenderingContext.elytraRendered || !LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.invoker().allowCapeRender(player)) return;

        // Custom Hallowed Cape when no other cape is present
        if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == MythicArmor.HALLOWED.getChestplate().asItem()) {
            mythicmetals$renderHallowedCape(ms, vertices, light, player, f, g, h, j, k, l);
            ci.cancel();
            return;
        }
        // Custom Star Platinum Cloak when no other cape is present
        if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == MythicArmor.STAR_PLATINUM.getChestplate().asItem()) {
            mythicmetals$renderStarPlatCape(ms, vertices, light, player, f, g, h, j, k, l);
            ci.cancel();
        }
    }

    @Unique
    private void mythicmetals$renderStarPlatCape(MatrixStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayerEntity player, float f, float g, float h, float j, float k, float l) {
        double x = MathHelper.lerpAngleDegrees(h / 2, (float) player.prevCapeX, (float) player.capeX)
                - MathHelper.lerpAngleDegrees(h / 2, (float) player.prevX, (float) player.getX());
        double y = MathHelper.lerpAngleDegrees(h / 2, (float) player.prevCapeY, (float) player.capeY)
                - MathHelper.lerpAngleDegrees(h / 2, (float) player.prevY, (float) player.getY());
        double z = MathHelper.lerpAngleDegrees(h / 2, (float) player.prevCapeZ, (float) player.capeZ)
                - MathHelper.lerpAngleDegrees(h / 2, (float) player.prevZ, (float) player.getZ());
        float yaw = player.prevBodyYaw + (player.bodyYaw - player.prevBodyYaw);
        double o = MathHelper.sin(yaw * (float) (Math.PI / 180.0));
        double p = -MathHelper.cos(yaw * (float) (Math.PI / 180.0));
        float q = (float) y * 10.0F;
        q = MathHelper.clamp(q, -6.0F, 32.0F);
        float r = (float) (x * o + z * p) * 100.0F;
        r = MathHelper.clamp(r, 0.0F, 150.0F);
        float capeZOffset = (float) (x * p - z * o) * 100.0F;
        capeZOffset = MathHelper.clamp(capeZOffset, -20.0F, 20.0F);
        if (r < 0.0F) {
            r = 0.0F;
        }


        float t = MathHelper.lerp(h, player.prevStrideDistance, player.strideDistance);
        q += MathHelper.sin(MathHelper.lerp(h, player.prevHorizontalSpeed, player.horizontalSpeed) * 6.0F) * 32.0F * t;

        if (player.isInSneakingPose()) {
            q += 25.0F;
        }

        float backCapeRotation = MathHelper.clamp(6.0F + r / 2.0F + q, -30, 60);
        VertexConsumer vertexConsumer = vertices.getBuffer(RenderLayer.getEntitySolid(MythicModelHandler.STAR_PLATINUM_CLOAK));

        // Transform and render the custom cape
        ms.push();
        ms.translate(0, -0.05, 0.0); // Push up and backwards, then rotate
        ms.multiply(RotationAxis.POSITIVE_X.rotationDegrees(backCapeRotation));
        ms.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(capeZOffset / 2.0F));
        ms.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - capeZOffset / 1.25F));
        ms.translate(0, 0.05, -0.370); // Move back down
        if (player.isInSneakingPose()) {
            ms.translate(0, 0.15, 0.125);
        }


        StarPlatCloakModel.CAPE_MODEL.render(ms, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        ms.pop();
    }

    // [VanillaCopy] render the cape
    @Unique
    private void mythicmetals$renderHallowedCape(MatrixStack ms, VertexConsumerProvider vertices, int light, AbstractClientPlayerEntity player, float f, float g, float h, float j, float k, float l) {
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
    }
}
