package nourl.mythicmetals.mixin;

import io.wispforest.owo.particles.ClientParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
    Random r = new Random();

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        addArmorEffects();
    }

    private void addArmorEffects() {
        for (ItemStack armorItems : getArmorItems()) {
            if (RegisterTags.CARMOT_ARMOR.contains(armorItems.getItem())) {
                carmotParticle();
            }

            if (RegisterTags.COPPER_ARMOR.contains(armorItems.getItem()) && world.isThundering()) {
                copperParticle();
                int i = r.nextInt(500000);
                if (i == 666 & copperParticle()) {
                    LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
                    if (lightningEntity != null) {
                        lightningEntity.copyPositionAndRotation(this);
                        world.spawnEntity(lightningEntity);
                    }
                }
            }

            if (RegisterTags.PALLADIUM_ARMOR.contains(armorItems.getItem())) {
//                this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 4, 0, true, false, true));
                Vec3d velocity = this.getVelocity();
                if (velocity.length() >= 0.1 && r.nextInt(5) < 1) {
                    ClientParticles.setParticleCount(1);
                    ClientParticles.spawn(ParticleTypes.LAVA, world, getPos(), 0.5D);
                }
            }
        }
    }

    private void carmotParticle() {
        // Random ints which cycle between negative and positive
        int i = r.nextInt(2) * 2 - 1;
        int j = r.nextInt(2) * 2 - 1;
        int k = r.nextInt(2) * 2 - 1;

        // Doubles that return between 0 and 1
        double l = r.nextDouble();
        double m = r.nextDouble();

        // Get entity position
        double x = this.getPos().getX();
        double y = this.getPos().getY();
        double z = this.getPos().getZ();
        Vec3d velocity = this.getVelocity();
        Vec3f carmot_colour = new Vec3f(Vec3d.unpackRgb(0xE63E73));
        ParticleEffect p = new DustParticleEffect(carmot_colour, 1.0F);
        ParticleEffect p2 = ParticleTypes.END_ROD;

        // Add particles around the entity when standing still
        if (velocity.length() <= 0.1 && r.nextInt(10) < 5) {
            this.world.addParticle(p, x + i * (l - 0.1D), y + 1D + (k * l * 0.75D), z + k * (m - 0.1D), 0.1D * i, 0.1D * j, 0.1D * k);
        }
        // Particle trail if the entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 7) {
            this.world.addParticle(p2, x + l, y, z + m, 0.1 * k, 0.05, 0.1 * j);
        }
    }
    private boolean copperParticle() {
        double x = this.getPos().getX();
        double y = this.getPos().getY();
        double z = this.getPos().getZ();

        int i = r.nextInt(2) * 2 - 1;
        int j = r.nextInt(2) * 2 - 1;
        int k = r.nextInt(2) * 2 - 1;

        double l = r.nextDouble();
        double m = r.nextDouble();

        ParticleEffect p = ParticleTypes.ELECTRIC_SPARK;

        boolean isConductive = y == world.getTopY(Heightmap.Type.WORLD_SURFACE, (int) x, (int) z - 1);

        if (world.isThundering() && r.nextInt(40) < 1 && isConductive) {
            this.world.addParticle(p, x + i * (l - 0.1D), y + 1.0D + (k * l * 0.75D), z + k * (m - 0.1D), 0.1D * i, 0.1D * j, 0.1D * k);
            return true;
        }
        return isConductive;
    }


}
