package nourl.mythicmetals.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.armor.CarmotShield;
import nourl.mythicmetals.block.MythicBlocks;
import nourl.mythicmetals.client.models.RainbowShieldModel;
import nourl.mythicmetals.component.DrillComponent;
import nourl.mythicmetals.component.MythicDataComponents;
import nourl.mythicmetals.item.tools.carmot_staff.CarmotStaffItem;
import nourl.mythicmetals.misc.UsefulSingletonForColorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static nourl.mythicmetals.client.rendering.PlayerEnergySwirlFeatureRenderer.SWIRL_TEXTURE;
import static nourl.mythicmetals.misc.UsefulSingletonForColorUtil.MetalColors.SHIELD_BREAK_COLOR;

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
        var stack = player.getStackInHand(Hand.MAIN_HAND);
        if (CarmotStaffItem.hasBlockInStaff(stack, MythicBlocks.STORMYX.getStorageBlock())) {
            if (!stack.getOrDefault(MythicDataComponents.IS_USED, false))
                return; // Only render if the staff is actively being used
            matrixStack.push();
            double delta = System.currentTimeMillis() / 45.0;

            var part = RainbowShieldModel.getTexturedModelData();

            part.createModel().render(
                matrixStack,
                vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
                i,
                OverlayTexture.DEFAULT_UV,
                UsefulSingletonForColorUtil.rainbow());
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
