package com.mythicmetals.block.entity;

import com.mythicmetals.block.CarmotBellBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CarmotBellBlockEntity extends BlockEntity {

    protected int cooldown = 0;

    public CarmotBellBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.CARMOT_BELL_BLOCK, pos, state);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, CarmotBellBlockEntity be) {
        if (be.cooldown > 0) {
            be.cooldown--;
        }
    }

    public boolean canBeUsed() {
        return cooldown == 0;
    }

    public void markUsed() {
        cooldown = CarmotBellBlock.COOLDOWN;
        setChanged();
    }
}
