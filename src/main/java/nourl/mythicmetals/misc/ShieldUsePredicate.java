package nourl.mythicmetals.misc;

import blue.endless.jankson.annotation.Nullable;
import net.minecraft.client.item.ClampedModelPredicateProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * @author Draylar
 */
public class ShieldUsePredicate implements ClampedModelPredicateProvider {

    @Override
    public float unclampedCall(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed) {
        return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
    }
}
