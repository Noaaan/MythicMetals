package com.mythicmetals.mixin;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.tools.HammerBase;
import com.mythicmetals.misc.IsAttackCritical;
import net.minecraft.stats.Stat;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements IsAttackCritical {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Unique
    public boolean mythicmetals$isCritical = false;

    @Shadow
    public abstract Inventory getInventory();

    @Shadow
    public abstract Iterable<ItemStack> getArmorSlots();

    @Shadow
    public abstract void incrementStat(Stat<?> stat);

    @Shadow
    @Final
    private ItemCooldowns itemCooldownManager;

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowBreak(BlockState blockState, CallbackInfoReturnable<Float> cir) {
        var mainHandStack = getInventory().getSelected();
        float speedMod = 1.0f;

        // Don't do any special handling if you are not holding a tool
        if (mainHandStack.isEmpty()) return;

        // Slow down mining MM ores if you are using an item without a high enough mining level
        if (blockState.is(MythicTags.MYTHIC_ORES) && !mainHandStack.isCorrectToolForDrops(blockState)) {
            if (mainHandStack.isEnchanted() && mainHandStack.getEnchantments().keySet().iterator().next().equals(Enchantments.EFFICIENCY)) {
                speedMod *= 0.01f;
            } else {
                speedMod *= 0.3f;
            }

        }

        // Slow down Hammers
        if (mainHandStack.getItem() instanceof HammerBase) {
            speedMod *= 0.9f;
        }

        if (speedMod < 1.0f) {
            var speed = cir.getReturnValue();
            cir.setReturnValue(speed * speedMod);
        }

    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void tickCarmotShield(CallbackInfo ci) {
        getComponent(MythicMetals.CARMOT_SHIELD).tickShield();
    }

    @ModifyVariable(
        method = "applyDamage",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F",
            shift = At.Shift.BY, by = -2),
        ordinal = 0,
        argsOnly = true)
    public float carmotShieldCancel(float amount) {
        var shield = getComponent(MythicMetals.CARMOT_SHIELD);
        if (shield.getMaxHealth() > 0) {
            float health = shield.shieldHealth;
            shield.damageShield(amount);
            return amount > health ? amount - health : 0;

        }

        return amount;
    }

    @Inject(method = "attack", at = @At("HEAD"))
    private void setMythicmetals$resetCritical(Entity target, CallbackInfo ci) {
        mythicmetals$setCritical(false);
    }

    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"))
    private void mythicmetals$captureCritical(CallbackInfo ci) {
        mythicmetals$setCritical(true);
    }

    @Override
    public void mythicmetals$setCritical(boolean isCritical) {
        mythicmetals$isCritical = isCritical;
    }

    @Override
    public boolean mythicmetals$isCritical() {
        return mythicmetals$isCritical;
    }
}
