package com.mythicmetals.mixin.client;

import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	@Shadow
	public abstract ItemStack getStackInHand(Hand hand);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At("HEAD"), cancellable = true)
	private void mythicmetals$cancelSwingOnActiveMythrilDrill(Hand hand, boolean fromServerPlayer, CallbackInfo ci) {
		if (!this.getWorld().isClient()) {
			return;
		}
		var stack = this.getStackInHand(hand);
		var camera = MinecraftClient.getInstance().getEntityRenderDispatcher().camera;
		// This can be null, according to #252
		if (camera == null) return;
		if (camera.isThirdPerson() && stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
			ci.cancel();
		}
	}
}
