package com.mythicmetals.mixin.client;

import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin<T extends Avatar & ClientAvatarEntity> extends LivingEntityRenderer<@NonNull T, AvatarRenderState, PlayerModel> {

    public AvatarRendererMixin(EntityRendererProvider.Context context, PlayerModel model, float shadow) {
        super(context, model, shadow);
    }

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
//    @Inject(method = "getArmPose(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", at = @At("RETURN"), cancellable = true)
//    private static void mythicmetals$mythrilDrillPose(Player player, ItemStack stack, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
//        if (stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
//            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
//        }
//    }
}
