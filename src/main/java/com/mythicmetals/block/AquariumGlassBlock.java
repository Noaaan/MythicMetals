package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class AquariumGlassBlock extends TransparentBlock {
    public static final MapCodec<AquariumGlassBlock> CODEC = simpleCodec(AquariumGlassBlock::new);
    public static final BooleanProperty POPPED = BooleanProperty.create("popped");

    public AquariumGlassBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(POPPED, Boolean.FALSE)
        );
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (player.mayBuild() && player.getMainHandItem().isEmpty()) {
            BlockState blockState = state.cycle(POPPED);
            world.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POPPED);
    }

    @Override
    protected MapCodec<? extends TransparentBlock> codec() {
        return CODEC;
    }
}
