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
        carmotParticle();
    }

    private void carmotParticle() {
        for (ItemStack armorItems : getArmorItems()) {
            if (armorItems.getItem().isIn(RegisterTags.CARMOT_ARMOR)) {
                addParticle();
            }
        }
    }
    Random r = new Random();
    private void addParticle() {
        int g = r.nextInt(3) * 2 - 2;
        int j = r.nextInt(3) * 2 - 2;
        double x = this.getPos().getX();
        double y = this.getPos().getY();
        double z = this.getPos().getZ();
        ParticleEffect p = ParticleTypes.ENCHANT;
        this.getEntityWorld().addParticle(p, x, y + 1.2, z, r.nextDouble() * j, (0.2), r.nextDouble() * g);
    }
}
