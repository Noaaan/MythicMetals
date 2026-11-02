package com.mythicmetals.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.data.attachments.MythicDataAttachments;
import com.mythicmetals.item.armor.CarmotShield;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.owo.braid.core.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mythicmetals.client.rendering.LivingEntityEnergySwirlFeatureRenderer.SWIRL_TEXTURE;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Shadow
    @Final
    private EntityRenderDispatcher entityRenderDispatcher;

    @Shadow
    @Final
    private Minecraft minecraft;

    // TODO - figure out issue which looks like z-fighting. it feels like culling...? prob wrong though
    @Inject(method = "renderPlayerArm", at = @At(value = "TAIL", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderPlayerArm(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IFFLnet/minecraft/world/entity/HumanoidArm;)V"))
    private void mythicmetals$renderCarmotShield(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, float inverseArmHeight, float attackValue, HumanoidArm arm, CallbackInfo ci) {
        var player = this.minecraft.player;
        if (player != null && CarmotShield.getMaxHealth(player) > 0) {
            var carmotShield = player.getAttached(MythicDataAttachments.CARMOT_SHIELD_ATTACHMENT);
            if (carmotShield == null || carmotShield.isBroken()) return;

            if (carmotShield.isHurt()) {
                boolean isRightArm = arm != HumanoidArm.LEFT;
                var avatarRenderer = entityRenderDispatcher.getPlayerRenderer(player);
                var playerModel = avatarRenderer.getModel();
                var armModel = isRightArm ? playerModel.rightArm : playerModel.leftArm;
                mythicmetals$renderCarmotShieldHand(player, avatarRenderer.getModel(), poseStack, submitNodeCollector, lightCoords, armModel);
            }
        }
    }

    @Unique
    private void mythicmetals$renderCarmotShieldHand(
        LivingEntity player,
        PlayerModel model,
        final PoseStack poseStack,
        final SubmitNodeCollector submitNodeCollector,
        final int lightCoords,
        final ModelPart arm
    ) {
        float partialTicks = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false);
        float time = player.tickCount + partialTicks;
        arm.resetPose();
        arm.visible = true;
        model.leftArm.zRot = -0.1F;
        model.rightArm.zRot = 0.1F;
        poseStack.pushPose();
        poseStack.scale(1.0625f, 1.0625f, 1.0625f);
        submitNodeCollector.submitModelPart(
            arm,
            poseStack,
            RenderTypes.energySwirl(SWIRL_TEXTURE, (time * .005f) % 1f, time * .005f % 1f),
            lightCoords,
            OverlayTexture.NO_OVERLAY,
            null,
            UsefulSingletonForColorUtil.rainbow(128, 0.85f),
            null
        );
        poseStack.popPose();
    }
}
