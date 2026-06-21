package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.misc.duck.WasSpawnedFromCreeper;
import com.mythicmetals.registry.RegisterCriteria;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mythicmetals.data.attachments.MythicDataAttachments.COMBUSTION_COOLDOWN_ATTACHMENT;
import static com.mythicmetals.entity.MythicEntityAttributes.FIRE_VULNERABILITY;
import static com.mythicmetals.entity.MythicEntityAttributes.UNDEAD_BONUS_DAMAGE;

@SuppressWarnings("UnstableApiUsage")
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    public abstract boolean canFreeze();

    @Shadow
    public abstract boolean addEffect(MobEffectInstance effect);

    @Shadow
    private @Nullable EntityReference<LivingEntity> lastHurtByMob;

    @Shadow
    public abstract boolean canBeAffected(MobEffectInstance effect);

    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    @Shadow
    public abstract void stopRiding();

    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Shadow
    public abstract @Nullable MobEffectInstance getEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract boolean removeEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract AttributeMap getAttributes();

    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot equipmentSlot);

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Unique
    Random r = new Random();

    @Inject(method = "createLivingAttributes", require = 1, allow = 1, at = @At("RETURN"))
    private static void mythicmetals$addAttributes(final CallbackInfoReturnable<AttributeSupplier.Builder> info) {
        info.getReturnValue().add(MythicEntityAttributes.CARMOT_SHIELD);
        info.getReturnValue().add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);
        info.getReturnValue().add(FIRE_VULNERABILITY);
        info.getReturnValue().add(UNDEAD_BONUS_DAMAGE);
    }

    @ModifyExpressionValue(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/core/Holder;)Z"))
    private boolean mythicmetals$bypassFireResistance(boolean original) {
        // We respect Fire Invulnerability, but not Fire Resistance
        // original = source.isFire() && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)
        return original && !(this.getAttributeValue(FIRE_VULNERABILITY) > 0);
    }

    /**
     * Increase fire damage taken by 1 for each point of Fire Vulnerability
     * Fire Resistance halves this, although you will still take fire damage this way
     */
    @ModifyVariable(method = "hurtServer", at = @At(value = "HEAD"), argsOnly = true)
    private float mythicmetals$changeFireDamage(float original, ServerLevel world, DamageSource source, float amount) {
        if (!this.getAttributes().hasAttribute(FIRE_VULNERABILITY) || !source.is(DamageTypeTags.IS_FIRE)) {
            return original;
        }

        float baseDamage = (float) this.getAttributeValue(FIRE_VULNERABILITY);
        float modifier = this.hasEffect(MobEffects.FIRE_RESISTANCE) ? Math.min(Mth.floor((baseDamage / 2.0f)), 1) : baseDamage;
        return original + modifier;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void mythicmetals$tick(CallbackInfo ci) {
        if (!level().isClientSide()) {
            mythicmetals$tickCombustion();
        }
        mythicmetals$palladiumParticles();
        mythicmetals$addArmorEffects();
        mythicmetals$tickFireResWhileRiding();
    }

    @Unique
    private void mythicmetals$tickFireResWhileRiding() {
        if (!this.isPassenger()) return;
        var vehicle = this.getVehicle();
        if (vehicle == null) return;
        if (this.level().getGameTime() % 40 == 1 && vehicle.getType().is(MythicTags.GRANTS_FIRE_RES_WHILE_RIDING)) {
            this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120));
        }
    }

    @Unique
    private void mythicmetals$tickCombustion() {
        int combustionCooldown = this.getAttachedOrElse(COMBUSTION_COOLDOWN_ATTACHMENT, 0);
        if (combustionCooldown > 0) {
            combustionCooldown--;
            this.setAttached(COMBUSTION_COOLDOWN_ATTACHMENT, combustionCooldown);
        }
        mythicmetals$handleCombustion(combustionCooldown);
    }

    @Unique
    private void mythicmetals$handleCombustion(int cooldown) {
        var entry = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.HEAT);
        if (this.isOnFire() && this.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.HEAT))) {
            if (cooldown != 0) {
                return;
            }
            var effect = this.getEffect(entry);
            if (effect != null) {
                int level = effect.getAmplifier();
                int duration = effect.getDuration();
                var multiplier = new AtomicInteger(effect.getDuration());
                this.removeEffect(entry);

                MythicParticleSystem.COMBUSTION_EXPLOSION.spawn(level(), this.position());

                var enemyMob = this.lastHurtByMob.getEntity(this.level(), LivingEntity.class);
                if (enemyMob != null && !enemyMob.getMainHandItem().isEmpty()) {
                    var stack = enemyMob.getMainHandItem();
                    stack.getEnchantments().keySet().forEach(enchantmentRegistryEntry -> {
                        if (enchantmentRegistryEntry.is(EnchantmentTags.SMELTS_LOOT)) {
                            multiplier.addAndGet(1);
                        }
                    });
                }

                this.addEffect(new MobEffectInstance(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.COMBUSTION), multiplier.get() + 40, Math.max(Mth.floor(level / 2.0f), 0), false, true));

                this.igniteForTicks((duration * multiplier.get()) + 40);
                this.setAttached(COMBUSTION_COOLDOWN_ATTACHMENT, 80 * (multiplier.get() + 1));
            }
        }
    }

    @Unique
    private void mythicmetals$addArmorEffects() {
        for (var slot : EquipmentSlot.VALUES) {
            var armorStack = this.getItemBySlot(slot);
            if (armorStack.isEmpty()) continue; // Don't get the item for an empty stack

            if (armorStack.is(MythicTags.CARMOT_ARMOR)) {
                mythicmetals$carmotParticle();
            }
        }
    }

    @Unique
    private void mythicmetals$carmotParticle() {
        if (!this.level().isClientSide()) return;
        Vec3 velocity = this.getDeltaMovement();

        // FIXME
//        if (this.isAlwaysTicking() && this.getComponent(MythicMetals.CARMOT_SHIELD).shieldHealth == 0) {
//            return; // If you are a player, and your shield ran out, do not display particles
//        }

        // Particle trail if the entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 1) {
            MythicParticleSystem.CARMOT_TRAIL.spawn(level(), this.position());
        }
    }

    @Unique
    private void mythicmetals$copperParticle() {
        if (this.level().isClientSide() && r.nextInt(40) < 1) {
            MythicParticleSystem.COPPER_SPARK.spawn(level(), this.position().add(0, 1, 0));
        }
    }

    @Unique
    private void mythicmetals$palladiumParticles() {
        var heatEntry = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.HEAT);
        if (this.hasEffect(heatEntry)) {
            var status = this.getEffect(heatEntry);
            if (status == null || status.getAmplifier() < 3) return;

            Vec3 velocity = this.getDeltaMovement();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.SMOKING_PALLADIUM_PARTICLE.spawn(level(), this.position().add(0, 0.25, 0));
            }
        }

        if (this.hasEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.COMBUSTION))) {
            Vec3 velocity = this.getDeltaMovement();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.OVERENGINEERED_PALLADIUM_PARTICLE.spawn(level(), this.position().add(0, 0.25, 0));
            }
        }
    }

    /**
     * Bonus advancement if you combust yourself via a creeper. Good job.
     */
    @Inject(method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z", at = @At("HEAD"))
    private void mythicmetals$grantAdvancementOnStatusEffectFromCreepers(MobEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        if (this.level().isClientSide() || source == null || !this.canBeAffected(effect)) return;
        if (effect.getEffect().value().equals(MythicStatusEffects.COMBUSTION) && this.isAlwaysTicking()) {
            if (source instanceof AreaEffectCloud cloudEntity && ((WasSpawnedFromCreeper) cloudEntity).mythicmetals$isSpawnedFromCreeper()) {
                //noinspection ConstantConditions
                RegisterCriteria.RECEIVED_COMBUSTION_FROM_CREEPER.trigger(((ServerPlayer) (Object) this));
            }
        }
    }


    @Inject(method = "dropCustomDeathLoot", at = @At(value = "HEAD"))
    private void mythicmetals$dropMidasGold(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        if (source.getEntity() == null) return;
        if (source.getEntity() instanceof Player attacker1) {
            if (MythicMetals.CONFIG.midasGold() && attacker1.getMainHandItem().is(MythicTags.MIDAS_TOUCH)) {
                this.spawnAtLocation(world, new ItemStack(MythicMaterials.MIDAS_GOLD.rawOre()));
            }
        }
    }

}
