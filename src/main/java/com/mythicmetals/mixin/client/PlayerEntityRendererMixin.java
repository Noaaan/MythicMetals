package com.mythicmetals.mixin.client;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.client.MythicMetalsPlayerRenderContext;
import com.mythicmetals.client.MythicMetalsRenderState;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "updateRenderState(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;F)V", at = @At("TAIL"))
    private void mythicmetals$updatePlayerRenderState(AbstractClientPlayerEntity abstractClientPlayerEntity, PlayerEntityRenderState playerEntityRenderState, float f, CallbackInfo ci) {
        var carmotShield = abstractClientPlayerEntity.getComponent(MythicMetals.CARMOT_SHIELD);
        ((MythicMetalsRenderState) playerEntityRenderState).mythicmetals$setPlayerRenderContext(new MythicMetalsPlayerRenderContext(carmotShield));
    }
//    /**
//     * Renders the Carmot Shield on the players arm
//     */
//    @Inject(method = "renderArm", at = @At("TAIL"))
//    private void mythicmetals$renderShieldArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
//        if (player.getComponent(MythicMetals.CARMOT_SHIELD).shouldRenderShield()) {
//            final var client = MinecraftClient.getInstance();
//            float f = player.age + (client.isPaused() ? 0 : client.getRenderTickCounter().getTickDelta(true));
//
//            var shield = player.getComponent(MythicMetals.CARMOT_SHIELD);
//
//            var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));
//            matrices.scale(1.0625f, 1.0625f, 1.0625f);
//            if (shield.cooldown > CarmotShield.MAX_COOLDOWN - 30) {
//                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, SHIELD_BREAK_COLOR);
//            } else // Regular animation
//                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, UsefulSingletonForColorUtil.rainbow());
//        }
//    }
//
//    /**
//     * Renders the Stormyx Shield around the player
//     */
//    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
//        at = @At("TAIL"))
//    private void mythicmetals$renderRainbowShield(AbstractClientPlayerEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
//        var stack = player.getActiveItem();
//        // Only render if the shield is actively being used
//        if (stack.getItem().equals(MythicTools.STORMYX_SHIELD)) {
//            matrixStack.push();
//            StormyxShieldRenderer.renderRainbowShield(matrixStack, vertexConsumerProvider, i, player);
//            matrixStack.pop();
//        }
//    }
//
    @Inject(method = "getArmPose(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$mythrilDrillPose(PlayerEntity player, ItemStack stack, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
        }
    }
}
