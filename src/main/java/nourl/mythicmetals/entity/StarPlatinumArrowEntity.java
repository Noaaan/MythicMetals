package nourl.mythicmetals.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.CustomDamageSource;
import nourl.mythicmetals.registry.RegisterEntities;
import nourl.mythicmetals.tools.MythicTools;

public class StarPlatinumArrowEntity extends PersistentProjectileEntity {

    public StarPlatinumArrowEntity(LivingEntity owner, World world) {
        super(RegisterEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, owner, world);
    }

    public StarPlatinumArrowEntity(EntityType<StarPlatinumArrowEntity> type, World world) {
        super(RegisterEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, world);
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
        target.damage(new CustomDamageSource("star_platinum_arrow").setUsesMagic().setProjectile(), 24);
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
