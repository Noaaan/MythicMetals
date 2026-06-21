package com.mythicmetals.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.*;
import org.jspecify.annotations.Nullable;
import java.util.Optional;

///  Simple interface placing lava inside a blockstate.
///
///  Basically a copy of [SimpleWaterloggedBlock], but for lava.
public interface Lavaloggable extends BucketPickup, LiquidBlockContainer {
    BooleanProperty LAVALOGGED = BooleanProperty.create("lavalogged");

    @Override
    default boolean canPlaceLiquid(@Nullable LivingEntity livingEntity, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return fluid == Fluids.LAVA;
    }

    @Override
    default boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        if (!blockState.getValue(LAVALOGGED) && fluidState.getType() == Fluids.LAVA) {
            if (!levelAccessor.isClientSide()) {
                levelAccessor.setBlock(blockPos, blockState.setValue(LAVALOGGED, true), 3);
                levelAccessor.scheduleTick(blockPos, fluidState.getType(), fluidState.getType().getTickDelay(levelAccessor));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    default ItemStack pickupBlock(@Nullable LivingEntity livingEntity, LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
        if (state.getValue(LAVALOGGED)) {
            levelAccessor.setBlock(pos, state.setValue(LAVALOGGED, Boolean.FALSE), Block.UPDATE_ALL);
            if (!state.canSurvive(levelAccessor, pos)) {
                levelAccessor.destroyBlock(pos, true);
            }

            return new ItemStack(Items.LAVA_BUCKET);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    default Optional<SoundEvent> getPickupSound() {
        return Fluids.LAVA.getPickupSound();
    }
}
