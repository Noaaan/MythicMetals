package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.block.entity.CarmotBellBlockEntity;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.misc.*;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class CarmotBellBlock extends BaseEntityBlock {

    public static final VoxelShape BELL_SHAPE = Block.box(3.0f, 0.0f, 3.0f, 13.0f, 9.0f, 13.0f);

    public static final MapCodec<CarmotBellBlock> CODEC = simpleCodec(CarmotBellBlock::new);

    public CarmotBellBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        var be = world.getBlockEntity(pos);
        if (be == null) return InteractionResult.FAIL;

        if (be instanceof CarmotBellBlockEntity bell) {
            if (bell.canBeUsed() && !CarmotBellHandler.isCoolingDown(player)) {
                bell.markUsed();
                CarmotBellHandler.heal(world, be.getBlockPos().getCenter(), player);
                world.playLocalSound(pos, MythicSoundEvents.CARMOT_BELL_DING, SoundSource.BLOCKS, 1.0f, 1.0f, true);
                player.getCooldowns().addCooldown(CarmotBellHandler.COOLDOWN_GROUP, CarmotBellHandler.COOLDOWN_TICKS);
            } else {
                world.playLocalSound(pos, MythicSoundEvents.CARMOT_BELL_DING_PLAIN, SoundSource.BLOCKS, 1.0f, 1.0f, true);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, world, pos, player, hit);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return BELL_SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return BELL_SHAPE;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, RegisterBlockEntityTypes.CARMOT_BELL_BLOCK, CarmotBellBlockEntity::tick);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CarmotBellBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
