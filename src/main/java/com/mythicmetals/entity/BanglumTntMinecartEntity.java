package com.mythicmetals.entity;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.minecart.MinecartTNT;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BanglumTntMinecartEntity extends MinecartTNT {

    public BanglumTntMinecartEntity(EntityType<? extends MinecartTNT> entityType, Level world) {
        super(entityType, world);
    }

    public BanglumTntMinecartEntity(Level world, double x, double y, double z) {
        this(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public BlockState getDefaultDisplayBlockState() {
        return MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT).defaultBlockState();
    }

    @Override
    protected Item getDropItem() {
        return MythicMaterials.BANGLUM.extraItems().get(MythicResourceKeys.BANGLUM_TNT_MINECART);
    }

    @Override
    public EntityType<?> getType() {
        return MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE;
    }

}
