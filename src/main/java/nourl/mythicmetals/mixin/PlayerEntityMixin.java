package nourl.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.tools.HammerBase;
import nourl.mythicmetals.tools.MythicTools;
import org.spongepowered.asm.mixin.Final;
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

    @Shadow
    public abstract void incrementStat(Stat<?> stat);

    @Shadow
    @Final
    private ItemCooldownManager itemCooldownManager;

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void slowBreak(BlockState blockState, CallbackInfoReturnable<Float> cir) {
        var mainHandStack = getInventory().getMainHandStack();
        float speedMod = 1.0f;

        // Don't do any special handling if you are not holding a tool
        if (mainHandStack.isEmpty()) return;

        // Slow down mining MM ores if you are using an item without a high enough mining level
        if (blockState.isIn(MythicTags.MYTHIC_ORES) && !mainHandStack.isSuitableFor(blockState)) {
            if (mainHandStack.hasEnchantments() && mainHandStack.getEnchantments().iterator().next().equals(Enchantments.EFFICIENCY)) {
                speedMod *= 0.01f;
            } else {
                speedMod *= 0.3f;
            }

        }

        // Slow down Hammers
        if (mainHandStack.getItem() instanceof HammerBase) {
            speedMod *= 0.9f;
            cir.setReturnValue(speed);
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

    // [VanillaCopy]
    // Slight modifications to durability reduction, to make the Stormyx Shield more durable
    @Inject(method = "damageShield", at = @At("HEAD"))
    private void mythicmetals$damageShield(float amount, CallbackInfo ci) {
        if (this.activeItemStack.isOf(MythicTools.STORMYX_SHIELD)) {
            if (!this.world.isClient) {
                incrementStat(Stats.USED.getOrCreateStat(this.activeItemStack.getItem()));
            }

            if (amount >= 4.0f) {
                int i = MathHelper.floor(amount);
                Hand hand = this.getActiveHand();
                this.activeItemStack.damage(i, this, player -> player.sendToolBreakStatus(hand));
                if (this.activeItemStack.isEmpty()) {
                    if (hand == Hand.MAIN_HAND) {
                        this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    } else {
                        this.equipStack(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                    }

                    this.activeItemStack = ItemStack.EMPTY;
                    this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + this.world.random.nextFloat() * 0.4F);
                }
            }

        }
    }

    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V"))
    private void mythicmetals$setShieldCooldown(boolean sprinting, CallbackInfo ci) {
        this.itemCooldownManager.set(MythicTools.STORMYX_SHIELD.asItem(), 80);
    }

}
