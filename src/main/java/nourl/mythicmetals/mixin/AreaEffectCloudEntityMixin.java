package nourl.mythicmetals.mixin;

import net.minecraft.entity.AreaEffectCloudEntity;
import nourl.mythicmetals.misc.WasSpawnedFromCreeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AreaEffectCloudEntity.class)
public abstract class AreaEffectCloudEntityMixin implements WasSpawnedFromCreeper {

    @Unique
    private boolean mythicmetals$ownerWasCreeper = false;

    @Override
    public void mythicmetals$setSpawnedFromCreeper(boolean isCreeper) {
        mythicmetals$ownerWasCreeper = isCreeper;
    }

    @Override
    public boolean mythicmetals$isSpawnedFromCreeper() {
        return mythicmetals$ownerWasCreeper;
    }
}
