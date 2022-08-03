package nourl.mythicmetals.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterTags;
import nourl.mythicmetals.tools.CarmotStaff;
import nourl.mythicmetals.utils.MythicParticleSystem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

    @Shadow public abstract @Nullable LivingEntity getAttacker();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    Random r = new Random();

    @Inject(method = "tick", at = @At("HEAD"))
    private void mythicmetals$tick(CallbackInfo ci) {
        if (!world.isClient()) {
            mythicmetals$prometheumRepairPassive();
        }
        mythicmetals$addArmorEffects();
    }

    @ModifyArg(method = "dropXp", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"))
    private int mythicmetals$doubleXp(int value) {
        if (this.getAttacker() != null && this.getAttacker().getMainHandStack().get(CarmotStaff.STORED_BLOCK).asItem().equals(Blocks.LAPIS_BLOCK.asItem())) {
            return value * 2;
        }
        return value;
    }

    private void mythicmetals$addArmorEffects() {
        for (ItemStack armorItems : getArmorItems()) {
            if (armorItems.isIn(RegisterTags.CARMOT_ARMOR)) {
                mythicmetals$carmotParticle();
            }

            if (armorItems.isIn(RegisterTags.PROMETHEUM_ARMOR)) {
                var dmg = armorItems.getDamage();
                var rng = r.nextInt(200);
                if (rng == 117)
                    armorItems.setDamage(dmg - 1);
            }

            if (armorItems.isIn(RegisterTags.COPPER_ARMOR) && world.isThundering()) {
                mythicmetals$copperParticle();
                int i = r.nextInt(500000);
                if (i == 666 & mythicmetals$copperParticle()) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
                    if (lightningEntity != null) {
                        lightningEntity.copyPositionAndRotation(this);
                        world.spawnEntity(lightningEntity);
                        this.damage(DamageSource.LIGHTNING_BOLT, 10);
                    }
                }
            }

            if (armorItems.isIn(RegisterTags.PALLADIUM_ARMOR)) {
                Vec3d velocity = this.getVelocity();
                if (velocity.length() >= 0.1 && r.nextInt(6) < 1) {
                    MythicParticleSystem.OVERENGINEERED_PALLADIUM_PARTICLE.spawn(world, this.getPos().add(0, 1, 0));
                }
            }
        }
    }

    private void mythicmetals$carmotParticle() {
        if (!world.isClient) return;

        Vec3d velocity = this.getVelocity();

        // Add particles around the entity when standing still
        if (velocity.length() <= 0.1 && r.nextInt(14) < 1) {
            MythicParticleSystem.CARMOT_PARTICLES.spawn(world, this.getPos());
        }
        // Particle trail if the entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 1) {
            MythicParticleSystem.CARMOT_TRAIL.spawn(world, this.getPos());
        }
    }

    private boolean mythicmetals$copperParticle() {
        Vec3d playerPos = this.getPos();

        boolean isConductive = this.getPos().y == world.getTopY(Heightmap.Type.WORLD_SURFACE, (int) playerPos.x, (int) playerPos.z);

        if (r.nextInt(40) < 1 && isConductive) {
            MythicParticleSystem.COPPER_SPARK.spawn(world, this.getPos().add(0, 1, 0));
            return true;
        }
        return isConductive;
    }

    private void mythicmetals$prometheumRepairPassive() {
        var heldItem = getMainHandStack();
        if (heldItem.isIn(RegisterTags.PROMETHEUM_TOOLS)) {
            var dmg = heldItem.getDamage();
            var rng = r.nextInt(200);
            if (rng == 117)
                heldItem.setDamage(dmg - 1);
        }
    }

}
