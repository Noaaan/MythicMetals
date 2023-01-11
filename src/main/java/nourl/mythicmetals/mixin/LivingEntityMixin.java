package nourl.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.effects.MythicStatusEffects;
import nourl.mythicmetals.entity.CombustionCooldown;
import nourl.mythicmetals.item.tools.CarmotStaff;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.item.tools.MythrilDrill;
import nourl.mythicmetals.misc.MythicParticleSystem;
import nourl.mythicmetals.registry.RegisterCriteria;
import nourl.mythicmetals.registry.RegisterEntityAttributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Shadow
    public abstract boolean canFreeze();

    @Shadow
    public abstract int getArmor();

    @Shadow
    public abstract ItemStack getMainHandStack();

    @Shadow
    public abstract boolean damage(DamageSource source, float amount);

    @Shadow
    public abstract @Nullable LivingEntity getAttacker();

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow
    public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow
    public abstract @Nullable StatusEffectInstance getStatusEffect(StatusEffect effect);

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow
    public abstract double getAttributeValue(EntityAttribute attribute);

    @Shadow
    private @Nullable LivingEntity attacker;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    Random r = new Random();

    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void additionalEntityAttributes$addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(RegisterEntityAttributes.FIRE_VULNERABILITY);
    }

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    private boolean mythicmetals$bypassFireResistance(boolean original) {
        // We respect Fire Invulnerability, but not Fire Resistance
        // original = source.isFire() && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)
        return original && !(this.getAttributeValue(RegisterEntityAttributes.FIRE_VULNERABILITY) > 0);
    }

    /**
     * Increase fire damage taken by 1 for each point of Fire Vulnerability
     * Fire Resistance halves this, although you will still take fire damage this way
     */
    @ModifyVariable(method = "damage", at = @At(value = "HEAD"), argsOnly = true)
    private float mythicmetals$changeFireDamage(float original, DamageSource source) {
        if (source.isFire() && this.getAttributeValue(RegisterEntityAttributes.FIRE_VULNERABILITY) > 0) {
            float modifier = (this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE) ?
                    Math.min(MathHelper.floor(((float) this.getAttributeValue(RegisterEntityAttributes.FIRE_VULNERABILITY) / 2.0f)), 1)
                    :
                    ((float) this.getAttributeValue(RegisterEntityAttributes.FIRE_VULNERABILITY)));
            return original + modifier;
        }
        return original;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void mythicmetals$tick(CallbackInfo ci) {
        if (!world.isClient()) {
            mythicmetals$prometheumRepairPassive();
            mythicmetals$tickCombustion();
        }
        mythicmetals$palladiumParticles();
        mythicmetals$addArmorEffects();
    }

    private void mythicmetals$tickCombustion() {
        var component = getComponent(MythicMetals.COMBUSTION_COOLDOWN);
        component.tickCooldown();
        mythicmetals$handleCombustion(component);
    }

    private void mythicmetals$handleCombustion(CombustionCooldown component) {
        if (this.isOnFire() && this.hasStatusEffect(MythicStatusEffects.HEAT) && component.isCombustible()) {
            var effect = this.getStatusEffect(MythicStatusEffects.HEAT);
            if (effect != null) {
                int level = effect.getAmplifier();
                int duration = effect.getDuration();
                this.removeStatusEffect(MythicStatusEffects.HEAT);

                MythicParticleSystem.COMBUSTION_EXPLOSION.spawn(world, this.getPos());

                if (this.attacker != null && this.attacker.getMainHandStack() != null && EnchantmentHelper.get(this.attacker.getMainHandStack()).containsKey(Enchantments.FIRE_ASPECT)) {
                    duration *= 2;
                }

                this.addStatusEffect(new StatusEffectInstance(MythicStatusEffects.COMBUSTION, duration + 40, Math.max(MathHelper.floor(level / 2.0f), 0), false, true));

                this.setFireTicks(duration + 40);
                component.setCooldown(1800);
            }

        }
    }

    private void mythicmetals$prometheumRepairPassive() {
        var handStack = getMainHandStack();
        // Handle Prometheum Tools
        if (handStack.isIn(MythicTags.PROMETHEUM_TOOLS)) {
            var dmg = handStack.getDamage();
            var rng = r.nextInt(200);
            if (rng == 117 && dmg > 0) handStack.setDamage(MathHelper.clamp(dmg - 1, 0, Integer.MAX_VALUE));
        }

        // Handle Mythril Drill with Prometheum Upgrade
        if (handStack.getItem().equals(MythicTools.MYTHRIL_DRILL) && MythrilDrill.hasUpgradeItem(handStack, MythicBlocks.PROMETHEUM.getStorageBlock().asItem())) {
            var dmg = handStack.getDamage();
            var rng = r.nextInt(200);
            if (rng == 33 && dmg > 0) handStack.setDamage(MathHelper.clamp(dmg - 1, 0, Integer.MAX_VALUE));
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"))
    private void mythicmetals$damageCarmotStaffOnXpDrop(CallbackInfo ci) {
        var attacker = this.getAttacker();

        if (attacker == null && this.isPlayer()) return; // Return immediately there is no attacker or you are a player

        // If the user has a Carmot Staff in the offhand, damage their tool
        if (attacker.getStackInHand(Hand.OFF_HAND).getItem().equals(MythicTools.CARMOT_STAFF)) {
            var staff = attacker.getStackInHand(Hand.OFF_HAND);
            if (CarmotStaff.hasBlockInStaff(staff, Blocks.LAPIS_BLOCK)) {
                staff.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
            }
        }

    }

    private void mythicmetals$addArmorEffects() {
        for (ItemStack armorItems : getArmorItems()) {
            if (armorItems.isEmpty()) continue; // Dont get the item for an empty stack

            if (armorItems.getItem() == null) {
                MythicMetals.LOGGER.error("An ItemStack was somehow marked as empty, but does contain an item.");
                MythicMetals.LOGGER.error("This is not caused by Mythic Metals, and it could potentially crash!");
                MythicMetals.LOGGER.error("Skipping the Armor Item query");
                continue;
            }

            if (MythicArmor.CARMOT.isInArmorSet(armorItems)) {
                mythicmetals$carmotParticle();
            }

            if (!world.isClient && armorItems.isIn(MythicTags.PROMETHEUM_ARMOR)) {
                // Repair Prometheum Armor server-side
                var dmg = armorItems.getDamage();
                var rng = r.nextInt(200);
                if (rng == 117)
                    armorItems.setDamage(dmg - 1);
            }

            if (MythicArmor.COPPER.isInArmorSet(armorItems) && world.isThundering()) {
                Vec3d playerPos = this.getPos();
                boolean isConductive = playerPos.y == world.getTopY(Heightmap.Type.WORLD_SURFACE, (int) playerPos.x, (int) playerPos.z);
                int rng = r.nextInt(60000);

                // Display particles on client
                mythicmetals$copperParticle();

                // Randomly strike the player with lightning when conductive
                if (rng == 666 & isConductive) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
                    if (lightningEntity != null) {
                        lightningEntity.copyPositionAndRotation(this);
                        world.spawnEntity(lightningEntity);
                        this.damage(DamageSource.LIGHTNING_BOLT, 10);
                    }
                }
            }
        }
    }

    private void mythicmetals$carmotParticle() {
        if (!world.isClient) return;
        Vec3d velocity = this.getVelocity();

        if (this.isPlayer() && this.getComponent(MythicMetals.CARMOT_SHIELD).shieldHealth == 0) {
            return; // If you are a player, and your shield ran out, do not display particles
        }

        // Add particles around the entity when standing still
        if (velocity.length() <= 0.1 && r.nextInt(14) < 1) {
            MythicParticleSystem.CARMOT_PARTICLES.spawn(world, this.getPos());
        }
        // Particle trail if the entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 1) {
            MythicParticleSystem.CARMOT_TRAIL.spawn(world, this.getPos());
        }
    }

    private void mythicmetals$copperParticle() {
        if (world.isClient && r.nextInt(40) < 1) {
            MythicParticleSystem.COPPER_SPARK.spawn(world, this.getPos().add(0, 1, 0));
        }
    }

    private void mythicmetals$palladiumParticles() {
        if (this.hasStatusEffect(MythicStatusEffects.HEAT)) {
            var status = this.getStatusEffect(MythicStatusEffects.HEAT);
            if (status == null) return;
            if (status.getAmplifier() < 3) return;
            Vec3d velocity = this.getVelocity();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.SMOKING_PALLADIUM_PARTICLE.spawn(world, this.getPos().add(0, 0.25, 0));
            }
        }

        if (this.hasStatusEffect(MythicStatusEffects.COMBUSTION)) {
            Vec3d velocity = this.getVelocity();
            if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                MythicParticleSystem.OVERENGINEERED_PALLADIUM_PARTICLE.spawn(world, this.getPos().add(0, 0.25, 0));
            }
        }
    }
}
