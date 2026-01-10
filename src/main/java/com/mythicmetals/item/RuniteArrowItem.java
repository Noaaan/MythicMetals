package com.mythicmetals.item;

import com.mythicmetals.entity.RuniteArrowEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class RuniteArrowItem extends ArrowItem {

    public RuniteArrowItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return new RuniteArrowEntity(world, shooter, stack, shotFrom);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
        var entity = new RuniteArrowEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        entity.pickup = AbstractArrow.Pickup.ALLOWED;
        return entity;
    }

    @Override
    public ProjectileItem.DispenseConfig createDispenseConfig() {
        return new ProjectileItem.DispenseConfig.Builder()
            .power(1.4f)
            .uncertainty(5.0f)
            .build();
    }
}
