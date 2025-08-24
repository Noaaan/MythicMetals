package com.mythicmetals.entity;

import com.mythicmetals.MythicMetals;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.world.World;

public class PalladiumMinecartEntity extends MinecartEntity {

    public PalladiumMinecartEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    public PalladiumMinecartEntity(World world, double x, double y, double z) {
        this(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public Type getMinecartType() {
        return MythicMetals.PALLADIUM_MINECART;
    }
}
