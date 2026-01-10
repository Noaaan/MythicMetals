package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.block.entity.EnchantedMidasGoldBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EnchantedMidasGoldBlock extends BaseEntityBlock {

    public static final MapCodec<EnchantedMidasGoldBlock> CODEC = simpleCodec(EnchantedMidasGoldBlock::new);

    public EnchantedMidasGoldBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EnchantedMidasGoldBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
