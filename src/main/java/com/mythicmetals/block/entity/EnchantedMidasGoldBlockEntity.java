package com.mythicmetals.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EnchantedMidasGoldBlockEntity extends BlockEntity {
    public EnchantedMidasGoldBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK, pos, state);
    }
}
