package nourl.mythicmetals.mixin;

import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DamageSource.class)
public interface DamageSourceAccessor {
    @Invoker("<init>")
    static DamageSource createDamageSource(String name) {
        throw new UnsupportedOperationException();
    }
}
