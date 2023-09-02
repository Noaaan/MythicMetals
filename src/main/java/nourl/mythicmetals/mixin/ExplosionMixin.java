package nourl.mythicmetals.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.explosion.Explosion;
import nourl.mythicmetals.entity.BanglumTntEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Explosion.class)
public class ExplosionMixin {

    @Shadow
    @Nullable
    public Entity entity;

    @Inject(method = "getCausingEntity", at = @At("HEAD"), cancellable = true)
    public void superBang(CallbackInfoReturnable<LivingEntity> cir) {
        if (this.entity != null && this.entity instanceof BanglumTntEntity){
            cir.setReturnValue(((BanglumTntEntity) this.entity).getCausingEntity());
        }
    }
}
