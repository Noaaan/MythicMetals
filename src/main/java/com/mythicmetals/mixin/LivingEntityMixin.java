package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.CombustionCooldown;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.misc.WasSpawnedFromCreeper;
import com.mythicmetals.registry.RegisterCriteria;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
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
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mythicmetals.entity.MythicEntityAttributes.FIRE_VULNERABILITY;
import static com.mythicmetals.entity.MythicEntityAttributes.UNDEAD_BONUS_DAMAGE;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Shadow
    public abstract boolean canFreeze();

    @Shadow
    public abstract int getArmor();

    @Shadow
    public abstract boolean addStatusEffect(MobEffectInstance effect);

    @Shadow
    private @Nullable LivingEntity attacker;

    @Shadow
    public abstract boolean canHaveStatusEffect(MobEffectInstance effect);

    @Shadow
    public abstract ItemStack getStackInHand(InteractionHand hand);

    @Shadow
    public abstract void stopRiding();

    @Shadow
    public abstract boolean hasStatusEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Shadow
    public abstract @Nullable MobEffectInstance getStatusEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract boolean removeStatusEffect(Holder<MobEffect> effect);

    @Shadow
    public abstract AttributeMap getAttributes();

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Unique
    Random r = new Random();

    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void mythicmetals$addAttributes(final CallbackInfoReturnable<AttributeSupplier.Builder> info) {
        info.getReturnValue().add(MythicEntityAttributes.CARMOT_SHIELD);
        info.getReturnValue().add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);
        info.getReturnValue().add(FIRE_VULNERABILITY);
        info.getReturnValue().add(UNDEAD_BONUS_DAMAGE);
    }

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/registry/entry/RegistryEntry;)Z"))
    private boolean mythicmetals$bypassFireResistance(boolean original) {
        // We respect Fire Invulnerability, but not Fire Resistance
        // original = source.isFire() && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)
        return original && !(this.getAttributeValue(FIRE_VULNERABILITY) > 0);
    }

    /**
     * Increase fire damage taken by 1 for each point of Fire Vulnerability
     * Fire Resistance halves this, although you will still take fire damage this way
     */
    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float mythicmetals$changeFireDamage(float original, ServerLevel world, DamageSource source, float amount) {
        if (!this.getAttributes().hasAttribute(FIRE_VULNERABILITY) || !source.is(DamageTypeTags.IS_FIRE)) {
            return original;
        }

        float baseDamage = (float) this.getAttributeValue(FIRE_VULNERABILITY);
        float modifier = this.hasStatusEffect(MobEffects.FIRE_RESISTANCE) ? Math.min(Mth.floor((baseDamage / 2.0f)), 1) : baseDamage;
        return original + modifier;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void mythicmetals$tick(CallbackInfo ci) {
        if (!level().isClientSide()) {
            mythicmetals$tickCombustion();
        }
        mythicmetals$palladiumParticles();
        mythicmetals$addArmorEffects();
    }

    @Unique
    private void mythicmetals$tickCombustion() {
        var component = getComponent(MythicMetals.COMBUSTION_COOLDOWN);
        component.tickCooldown();
        mythicmetals$handleCombustion(component);
    }

    @Unique
    private void mythicmetals$handleCombustion(CombustionCooldown component) {
        var entry = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.HEAT);
        if (this.isOnFire() && this.hasStatusEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.HEAT)) && component.isCombustible()) {
            var effect = this.getStatusEffect(entry);
            if (effect != null) {
                int level = effect.getAmplifier();
                int duration = effect.getDuration();
                var multiplier = new AtomicInteger(effect.getDuration());
                this.removeStatusEffect(entry);

                MythicParticleSystem.COMBUSTION_EXPLOSION.spawn(level(), this.position());

                if (this.attacker != null && this.attacker.getMainHandItem() != null) {
                    var stack = this.attacker.getMainHandItem();
                    stack.getEnchantments().keySet().forEach(enchantmentRegistryEntry -> {
                        if (enchantmentRegistryEntry.is(EnchantmentTags.SMELTS_LOOT)) {
                            multiplier.addAndGet(1);
                        }
                    });
                }

                this.addStatusEffect(new MobEffectInstance(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.COMBUSTION), multiplier.get() + 40, Math.max(Mth.floor(level / 2.0f), 0), false, true));

                this.igniteForTicks((duration * multiplier.get()) + 40);
                component.setCooldown(1800);
            }

        }
    }

    @Unique
    private void mythicmetals$addArmorEffects() {
        for (ItemStack armorStack : getArmorItems()) {
            // Turns out, this bug was in Minecraft itself
            // It only took a couple of years to find, and it was re-producible in vanilla context
            if (armorStack.isEmpty()) continue; // Don't get the item for an empty stack
            if (armorStack.getItem() == null) {
                MythicMetals.LOGGER.error("An ItemStack was somehow marked as not empty, but it doesn't contain an item.");
                MythicMetals.LOGGER.error("This is not caused by Mythic Metals, and it could potentially crash!");
                MythicMetals.LOGGER.error("Skipping the Armor Item query");
                continue;
            }

            if (MythicArmor.CARMOT.isInArmorSet(armorStack)) {
                mythicmetals$carmotParticle();
            }

            if (MythicArmor.COPPER.isInArmorSet(armorStack) && level().isThundering()) {
                Vec3 playerPos = this.position();
                boolean isConductive = playerPos.y == level().getHeight(Heightmap.Types.WORLD_SURFACE, (int) playerPos.x, (int) playerPos.z);
                int rng = r.nextInt(60000);

                // Display particles on client
                mythicmetals$copperParticle();

                // Randomly strike the player with lightning when conductive
                if (!level().isClientSide() && rng == 666 & isConductive) {
                    var world = ((ServerLevel) level());
                    LightningBolt lightningEntity = EntityType.LIGHTNING_BOLT.create(level(), EntitySpawnReason.NATURAL);
                    if (lightningEntity != null) {
                        lightningEntity.copyPosition(this);
                        world.addFreshEntity(lightningEntity);
                        this.hurtServer(world, world.damageSources().lightningBolt(), 10);
                    }
                }
            }
        }
    }

    @Unique
    private void mythicmetals$carmotParticle() {
        if (!this.level().isClientSide()) return;
        Vec3 velocity = this.getDeltaMovement();

        if (this.isAlwaysTicking() && this.getComponent(MythicMetals.CARMOT_SHIELD).shieldHealth == 0) {
            return; // If you are a player, and your shield ran out, do not display particles
        }

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
        if (this.hasStatusEffect(heatEntry)) {
            var status = this.getStatusEffect(heatEntry);
            if (status == null || status.getAmplifier() < 3) return;

            Vec3 velocity = this.getDeltaMovement();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.SMOKING_PALLADIUM_PARTICLE.spawn(level(), this.position().add(0, 0.25, 0));
            }
        }

        if (this.hasStatusEffect(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MythicStatusEffects.COMBUSTION))) {
            Vec3 velocity = this.getDeltaMovement();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.OVERENGINEERED_PALLADIUM_PARTICLE.spawn(level(), this.position().add(0, 0.25, 0));
            }
        }
    }

    /**
     * Bonus advancement if you combust yourself via a creeper. Good job.
     */
    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"))
    private void mythicmetals$grantAdvancementOnStatusEffectFromCreepers(MobEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        if (this.level().isClientSide() || source == null || !this.canHaveStatusEffect(effect)) return;
        if (effect.getEffect().value().equals(MythicStatusEffects.COMBUSTION) && this.isAlwaysTicking()) {
            if (source instanceof AreaEffectCloud cloudEntity && ((WasSpawnedFromCreeper) cloudEntity).mythicmetals$isSpawnedFromCreeper()) {
                //noinspection ConstantConditions
                RegisterCriteria.RECEIVED_COMBUSTION_FROM_CREEPER.trigger(((ServerPlayer) (Object) this));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At("HEAD"), cancellable = true)
    private void mythicmetals$cancelSwingOnActiveMythrilDrill(InteractionHand hand, boolean fromServerPlayer, CallbackInfo ci) {
        if (!this.level().isClientSide()) {
            return;
        }
        var stack = this.getStackInHand(hand);
        var camera = Minecraft.getInstance().getEntityRenderDispatcher().camera;
        // This can be null, according to #252
        if (camera == null) return;
        if (camera.isDetached() && stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel()) {
            ci.cancel();
        }
    }

    @Inject(method = "dropEquipment", at = @At(value = "HEAD"))
    private void mythicmetals$dropMidasGold(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        if (source.getEntity() == null) return;
        if (source.getEntity() instanceof Player attacker1) {
            if (MythicMetals.CONFIG.midasGold() && attacker1.getMainHandItem().is(MythicTags.MIDAS_TOUCH)) {
                this.spawnAtLocation(world, new ItemStack(MythicItems.MIDAS_GOLD.getRawOre()));
            }
        }
    }

    @Inject(method = "tickRiding", at = @At("HEAD"))
    private void mythicmetals$tickRiding(CallbackInfo ci) {
        if (this.isPassenger() && this.level().getGameTime() % 40 == 1 && this.getVehicle().getType().is(MythicTags.GRANTS_FIRE_RES_WHILE_RIDING)) {
            this.addStatusEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 120));
        }
    }
}
