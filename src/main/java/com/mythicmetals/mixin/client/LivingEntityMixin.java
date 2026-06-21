package com.mythicmetals.mixin.client;

import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand interactionHand);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "swing(Lnet/minecraft/world/InteractionHand;)V", at = @At("HEAD"), cancellable = true)
    private void mythicmetals$cancelSwingOnActiveMythrilDrill(InteractionHand interactionHand, CallbackInfo ci) {
        if (!this.level().isClientSide()) {
            return;
        }
        var stack = this.getItemInHand(interactionHand);
        var camera = Minecraft.getInstance().getEntityRenderDispatcher().camera;
        // This can be null, according to #252
        if (camera == null) return;
        if (camera.isDetached() && stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
            ci.cancel();
        }
    }
}
