package com.mythicmetals.mixin;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.CarmotShield;
import com.mythicmetals.client.rendering.StormyxShieldRenderer;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mythicmetals.client.rendering.PlayerEnergySwirlFeatureRenderer.SWIRL_TEXTURE;
import static com.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Unique
    private static final Identifier WORLD_BORDER = Identifier.of("textures/misc/forcefield.png");

    /**
     * Renders the Carmot Shield on the players arm
     */
    @Inject(method = "renderArm", at = @At("TAIL"))
    private void mythicmetals$renderShieldArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        if (player.getComponent(MythicMetals.CARMOT_SHIELD).shouldRenderShield()) {
            final var client = MinecraftClient.getInstance();
            float f = player.age + (client.isPaused() ? 0 : client.getRenderTickCounter().getTickDelta(true));

            var shield = player.getComponent(MythicMetals.CARMOT_SHIELD);

            var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));
            matrices.scale(1.0625f, 1.0625f, 1.0625f);
            if (shield.cooldown > CarmotShield.MAX_COOLDOWN - 30) {
                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, SHIELD_BREAK_COLOR);
            } else // Regular animation
                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, UsefulSingletonForColorUtil.rainbow());
        }
    }

    /**
     * Renders the Stormyx Shield around the player
     */
    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
        at = @At("TAIL"))
    private void mythicmetals$renderRainbowShield(AbstractClientPlayerEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        var stack = player.getActiveItem();
        // Only render if the shield is actively being used
        if (stack.getItem().equals(MythicTools.STORMYX_SHIELD)) {
            matrixStack.push();
            StormyxShieldRenderer.renderRainbowShield(matrixStack, vertexConsumerProvider, i);
            matrixStack.pop();
        }
    }

    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$mythrilDrillPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        var stack = player.getStackInHand(hand);
        if (stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
        }
    }
}
