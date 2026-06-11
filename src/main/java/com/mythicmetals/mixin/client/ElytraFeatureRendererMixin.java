package com.mythicmetals.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mythicmetals.armor.CelestiumElytra;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin {

    @ModifyExpressionValue(
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private boolean mythicmetals$canRenderCelestiumElytra(boolean original, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LivingEntity entity) {
        return original || CelestiumElytra.isWearing(entity);
    }

    @ModifyVariable(
        method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V"))
    private Identifier mythicmetals$replaceElytraTexture(Identifier value, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, LivingEntity living) {
        var stack = living.getEquippedStack(EquipmentSlot.CHEST);
        if (!stack.isOf(MythicArmor.CELESTIUM_ELYTRA)) {
            return value;
        }
        return RegistryHelper.id("textures/models/celestium_elytra.png");
    }
}
