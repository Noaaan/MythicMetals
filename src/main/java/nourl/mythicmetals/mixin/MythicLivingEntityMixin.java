package nourl.mythicmetals.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
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
        int k = r.nextInt(2) * 2 - 1;
        int j = r.nextInt(2) * 2 - 1;
        double x = this.getPos().getX();
        double y = this.getPos().getY();
        double z = this.getPos().getZ();
        ParticleEffect p = ParticleTypes.ENCHANT;
        this.world.addParticle(p, x, y+1.75, z, k*1D, -0.8, j*1D);

    }
}
