package com.mythicmetals.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;

public class PalladiumMinecartEntity extends Minecart {

    public PalladiumMinecartEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
    }

    public PalladiumMinecartEntity(Level world, double x, double y, double z) {
        this(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }
}
