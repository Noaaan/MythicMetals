package com.mythicmetals.entity;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BanglumTntMinecartEntity extends TntMinecartEntity {

    public BanglumTntMinecartEntity(EntityType<? extends TntMinecartEntity> entityType, World world) {
        super(entityType, world);
    }

    public BanglumTntMinecartEntity(World world, double x, double y, double z) {
        this(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public BlockState getDefaultContainedBlock() {
        return MythicBlocks.BANGLUM_TNT_BLOCK.getDefaultState();
    }

    @Override
    protected Item asItem() {
        return MythicTools.BANGLUM_TNT_MINECART;
    }

    @Override
    public EntityType<?> getType() {
        return MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE;
    }

}
