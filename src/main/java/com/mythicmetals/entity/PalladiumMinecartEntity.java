package com.mythicmetals.entity;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

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

    @Override
    protected @NonNull Item getDropItem() {
        return MythicMaterials.PALLADIUM.extraItems().get(MythicResourceKeys.PALLADIUM_MINECART);
    }
}
