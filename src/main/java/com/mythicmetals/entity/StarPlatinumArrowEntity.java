package com.mythicmetals.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.MythicDamageTypes;
import org.jetbrains.annotations.Nullable;

public class StarPlatinumArrowEntity extends PersistentProjectileEntity {
    public static final ItemStack STAR_PLAT_STACK = new ItemStack(MythicTools.STAR_PLATINUM_ARROW);

    public StarPlatinumArrowEntity(LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, owner, world, stack, weapon);
    }

    public StarPlatinumArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, x, y, z, world, stack, shotFrom);
    }

    public StarPlatinumArrowEntity(EntityType<StarPlatinumArrowEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected ItemStack asItemStack() {
        return STAR_PLAT_STACK;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return STAR_PLAT_STACK;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        var source = new DamageSource(
            this.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).getEntry(MythicDamageTypes.STAR_PLATINUM_ARROW).orElseThrow(),
            this,
            getOwner());
        if (target.getType().isIn(EntityTypeTags.UNDEAD)) {
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
