package com.mythicmetals.entity;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.item.tools.MythicTools;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.MinecartTNT;
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
        return MythicBlocks.BANGLUM_TNT_BLOCK.defaultBlockState();
    }

    @Override
    protected Item getDropItem() {
        return MythicTools.BANGLUM_TNT_MINECART;
    }

    @Override
    public EntityType<?> getType() {
        return MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE;
    }

}
