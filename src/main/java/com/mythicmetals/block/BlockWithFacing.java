package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

// [VanillaCopy] GlazedTerracottaBlock
public class BlockWithFacing extends HorizontalDirectionalBlock {
    public static final MapCodec<BlockWithFacing> CODEC = simpleCodec(BlockWithFacing::new);

    @Override
    public MapCodec<BlockWithFacing> codec() {
        return CODEC;
    }

    public BlockWithFacing(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }
}

