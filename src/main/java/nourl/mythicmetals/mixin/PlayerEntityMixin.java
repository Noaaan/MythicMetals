package nourl.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.data.MythicTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract PlayerInventory getInventory();

    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowBreak(BlockState blockState, CallbackInfoReturnable<Float> cir) {
        var hand = getInventory().getMainHandStack();
        if (blockState.isIn(MythicTags.MYTHIC_ORES) && !hand.isSuitableFor(blockState)) {
            var speed = cir.getReturnValue();
            if (hand.hasEnchantments() && hand.getEnchantments().iterator().next().equals(Enchantments.EFFICIENCY)) {
                speed *= 0.01f;
            } else
                speed *= 0.3f;

            cir.setReturnValue(speed);
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
            float health = shield.health;
            shield.damageShield(amount);
            return amount > health ? amount - health : 0;

        }

        return amount;
    }

}
