package nourl.mythicmetals.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class MythicLivingEntityMixin extends Entity {

    public MythicLivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo info) {
        addCarmotParticle();
    }

    private void addCarmotParticle() {
        for (ItemStack armorItems : getArmorItems()) {
            if (armorItems.getItem().isIn(RegisterTags.CARMOT_ARMOR)) {
                carmotParticle();
            }
        }
    }

    Random r = new Random();

    private void carmotParticle() {
        // Random ints which cycle between negative and positive
        int i = r.nextInt(2) * 2 - 1;
        int k = r.nextInt(2) * 2 - 1;
        int j = r.nextInt(2) * 2 - 1;

        // Doubles that return between 0 and 1
        double l = r.nextDouble();
        double m = r.nextDouble();

        // Get the entity position
        double x = this.getPos().getX();
        double y = this.getPos().getY();
        double z = this.getPos().getZ();
        Vec3d velocity = this.getVelocity();

        ParticleEffect p = new DustParticleEffect(230.0F / 256.0F, 62.0F / 256.0F, 115.0F / 256.0F, 1F);
        ParticleEffect p2 = ParticleTypes.END_ROD;

        // Add particles around the entity when standing still
        if (velocity.length() <= 0.1 && r.nextInt(10) < 5) {
            this.world.addParticle(p, x + i * (l - 0.1D), y + 1D + (k * l * 0.75D), z + k * (m - 0.1D), 0.1D * i, 0.1D * j, 0.1D * j);
        }
        // Particle trail if entity is moving
        if (velocity.length() >= 0.1 && r.nextInt(10) < 7) {
            this.world.addParticle(p2, x + l, y, z + m, 0.1 * k, 0.05, 0.1 * j);
        }
    }
}
