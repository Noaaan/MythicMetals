package nourl.mythicmetals.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.misc.MythicDamageTypes;

public class StarPlatinumArrowEntity extends PersistentProjectileEntity {

    public StarPlatinumArrowEntity(LivingEntity owner, World world) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, owner, world);
    }

    public StarPlatinumArrowEntity(EntityType<StarPlatinumArrowEntity> type, World world) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(MythicTools.STAR_PLATINUM_ARROW);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        var source = new DamageSource(
                this.world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).getEntry(MythicDamageTypes.STAR_PLATINUM_ARROW).orElseThrow(),
                this,
                getOwner());
        if (target.isUndead()) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 3));
        } else {
            target.damage(source, 24);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }
}
