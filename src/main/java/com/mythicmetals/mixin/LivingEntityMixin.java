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
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.mythicmetals.entity.MythicEntityAttributes.FIRE_VULNERABILITY;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Shadow
    public abstract boolean canFreeze();

    @Shadow
    public abstract int getArmor();

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow
    private @Nullable LivingEntity attacker;

    @Shadow
    public abstract boolean canHaveStatusEffect(StatusEffectInstance effect);

    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    public abstract void stopRiding();

    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow
    public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @Shadow
    public abstract @Nullable StatusEffectInstance getStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow
    public abstract boolean removeStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow
    public abstract AttributeContainer getAttributes();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    Random r = new Random();

    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void mythicmetals$addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(MythicEntityAttributes.CARMOT_SHIELD);
        info.getReturnValue().add(FIRE_VULNERABILITY);
        info.getReturnValue().add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED);
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
    private float mythicmetals$changeFireDamage(float original, ServerWorld world, DamageSource source, float amount) {
        if (!this.getAttributes().hasAttribute(FIRE_VULNERABILITY) || !source.isIn(DamageTypeTags.IS_FIRE)) {
            return original;
        }

        float baseDamage = (float) this.getAttributeValue(FIRE_VULNERABILITY);
        float modifier = this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE) ? Math.min(MathHelper.floor((baseDamage / 2.0f)), 1) : baseDamage;
        return original + modifier;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void mythicmetals$tick(CallbackInfo ci) {
        if (!getWorld().isClient()) {
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
        var entry = Registries.STATUS_EFFECT.getEntry(MythicStatusEffects.HEAT);
        if (this.isOnFire() && this.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(MythicStatusEffects.HEAT)) && component.isCombustible()) {
            var effect = this.getStatusEffect(entry);
            if (effect != null) {
                int level = effect.getAmplifier();
                int duration = effect.getDuration();
                var multiplier = new AtomicInteger(effect.getDuration());
                this.removeStatusEffect(entry);

                MythicParticleSystem.COMBUSTION_EXPLOSION.spawn(getWorld(), this.getPos());

                if (this.attacker != null && this.attacker.getMainHandStack() != null) {
                    var stack = this.attacker.getMainHandStack();
                    stack.getEnchantments().getEnchantments().forEach(enchantmentRegistryEntry -> {
                        if (enchantmentRegistryEntry.isIn(EnchantmentTags.SMELTS_LOOT)) {
                            multiplier.addAndGet(1);
                        }
                    });
                }

                this.addStatusEffect(new StatusEffectInstance(Registries.STATUS_EFFECT.getEntry(MythicStatusEffects.COMBUSTION), multiplier.get() + 40, Math.max(MathHelper.floor(level / 2.0f), 0), false, true));

                this.setOnFireForTicks((duration * multiplier.get()) + 40);
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

            if (MythicArmor.COPPER.isInArmorSet(armorStack) && getWorld().isThundering()) {
                Vec3d playerPos = this.getPos();
                boolean isConductive = playerPos.y == getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, (int) playerPos.x, (int) playerPos.z);
                int rng = r.nextInt(60000);

                // Display particles on client
                mythicmetals$copperParticle();

                // Randomly strike the player with lightning when conductive
                if (!getWorld().isClient() && rng == 666 & isConductive) {
                    var world = ((ServerWorld) getWorld());
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(getWorld(), SpawnReason.NATURAL);
                    if (lightningEntity != null) {
                        lightningEntity.copyPositionAndRotation(this);
                        world.spawnEntity(lightningEntity);
                        this.damage(world, world.getDamageSources().lightningBolt(), 10);
                    }
                }
            }
        }
    }

    @Unique
    private void mythicmetals$carmotParticle() {
        if (!this.getWorld().isClient()) return;
        Vec3d velocity = this.getVelocity();

        if (this.isPlayer() && this.getComponent(MythicMetals.CARMOT_SHIELD).shieldHealth == 0) {
            return; // If you are a player, and your shield ran out, do not display particles
        }

        // Particle trail if the entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 1) {
            MythicParticleSystem.CARMOT_TRAIL.spawn(getWorld(), this.getPos());
        }
    }

    @Unique
    private void mythicmetals$copperParticle() {
        if (this.getWorld().isClient() && r.nextInt(40) < 1) {
            MythicParticleSystem.COPPER_SPARK.spawn(getWorld(), this.getPos().add(0, 1, 0));
        }
    }

    @Unique
    private void mythicmetals$palladiumParticles() {
        var heatEntry = Registries.STATUS_EFFECT.getEntry(MythicStatusEffects.HEAT);
        if (this.hasStatusEffect(heatEntry)) {
            var status = this.getStatusEffect(heatEntry);
            if (status == null || status.getAmplifier() < 3) return;

            Vec3d velocity = this.getVelocity();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.SMOKING_PALLADIUM_PARTICLE.spawn(getWorld(), this.getPos().add(0, 0.25, 0));
            }
        }

        if (this.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(MythicStatusEffects.COMBUSTION))) {
            Vec3d velocity = this.getVelocity();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.OVERENGINEERED_PALLADIUM_PARTICLE.spawn(getWorld(), this.getPos().add(0, 0.25, 0));
            }
        }
    }

    /**
     * Bonus advancement if you combust yourself via a creeper. Good job.
     */
    @Inject(method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"))
    private void mythicmetals$grantAdvancementOnStatusEffectFromCreepers(StatusEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        if (this.getWorld().isClient() || source == null || !this.canHaveStatusEffect(effect)) return;
        if (effect.getEffectType().value().equals(MythicStatusEffects.COMBUSTION) && this.isPlayer()) {
            if (source instanceof AreaEffectCloudEntity cloudEntity && ((WasSpawnedFromCreeper) cloudEntity).mythicmetals$isSpawnedFromCreeper()) {
                //noinspection ConstantConditions
                RegisterCriteria.RECEIVED_COMBUSTION_FROM_CREEPER.trigger(((ServerPlayerEntity) (Object) this));
            }
        }
    }

    @Environment(EnvType.CLIENT)
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

    @Inject(method = "dropEquipment", at = @At(value = "HEAD"))
    private void mythicmetals$dropMidasGold(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        if (source.getAttacker() == null) return;
        if (source.getAttacker() instanceof PlayerEntity attacker1) {
            if (MythicMetals.CONFIG.midasGold() && attacker1.getMainHandStack().isIn(MythicTags.MIDAS_TOUCH)) {
                this.dropStack(world, new ItemStack(MythicItems.MIDAS_GOLD.getRawOre()));
            }
        }
    }

    @Inject(method = "tickRiding", at = @At("HEAD"))
    private void mythicmetals$tickRiding(CallbackInfo ci) {
        if (this.hasVehicle() && this.getWorld().getTime() % 40 == 1 && this.getVehicle().getType().isIn(MythicTags.GRANTS_FIRE_RES_WHILE_RIDING)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 120));
        }
    }
}
