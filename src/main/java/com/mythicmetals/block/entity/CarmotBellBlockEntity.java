package com.mythicmetals.block.entity;

import com.mythicmetals.block.CarmotBellBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CarmotBellBlockEntity extends BlockEntity {

    protected int cooldown = 0;

    public CarmotBellBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.CARMOT_BELL_BLOCK, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, CarmotBellBlockEntity be) {
        if (be.cooldown > 0) {
            be.cooldown--;
        }
    }

    public boolean canBeUsed() {
        return cooldown == 0;
    }

    public void markUsed() {
        cooldown = CarmotBellBlock.COOLDOWN;
        markDirty();
    }
}
