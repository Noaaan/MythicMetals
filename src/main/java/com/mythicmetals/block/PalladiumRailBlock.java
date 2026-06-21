package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import org.jspecify.annotations.Nullable;
import java.util.Optional;

public class PalladiumRailBlock extends BaseRailBlock implements Lavaloggable {

    public static final EnumProperty<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE;
    public static final MapCodec<PalladiumRailBlock> CODEC = simpleCodec(PalladiumRailBlock::new);

    @Override
    protected void updateState(BlockState state, Level world, BlockPos pos, Block neighbor) {
        if (neighbor.defaultBlockState().isSignalSource() && new RailState(world, pos, state).countPotentialConnections() == 3) {
            this.updateDir(world, pos, state, false);
        }
    }

    public PalladiumRailBlock(Properties settings) {
        super(false, settings);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(SHAPE, RailShape.NORTH_SOUTH)
            .setValue(LAVALOGGED, Boolean.FALSE)
            .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, LAVALOGGED, WATERLOGGED);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable LivingEntity livingEntity, BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return Lavaloggable.super.canPlaceLiquid(livingEntity, blockGetter, blockPos, blockState, fluid);
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return Lavaloggable.super.placeLiquid(level, pos, state, fluidState);
    }

    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity livingEntity, LevelAccessor levelAccessor, BlockPos pos, BlockState state) {
        return Lavaloggable.super.pickupBlock(livingEntity, levelAccessor, pos, state);
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Lavaloggable.super.getPickupSound();
    }

    @Override
    public MapCodec<PalladiumRailBlock> codec() {
        return CODEC;
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(LAVALOGGED) ? Fluids.LAVA.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        boolean isLavalogged = fluidState.getType() == Fluids.LAVA;
        boolean isWaterLogged = fluidState.getType() == Fluids.WATER;
        BlockState blockState = super.defaultBlockState();
        Direction direction = ctx.getHorizontalDirection();
        boolean isEastWest = direction == Direction.EAST || direction == Direction.WEST;
        if (isLavalogged && isWaterLogged) {
            return Blocks.OBSIDIAN.defaultBlockState();
        }
        return blockState
            .setValue(this.getShapeProperty(), isEastWest ? RailShape.EAST_WEST : RailShape.NORTH_SOUTH)
            .setValue(WATERLOGGED, isWaterLogged)
            .setValue(LAVALOGGED, isLavalogged);
    }

    @Override
    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        RailShape railShape = blockState.getValue(SHAPE);
        RailShape railShape2 = this.rotate(railShape, rotation);
        return blockState.setValue(SHAPE, railShape2);
    }

    @Override
    protected BlockState mirror(BlockState blockState, Mirror mirror) {
        RailShape railShape = blockState.getValue(SHAPE);
        RailShape railShape2 = this.mirror(railShape, mirror);
        return blockState.setValue(SHAPE, railShape2);
    }

    public static boolean isLavaLogged(BlockState state) {
        return state.getValue(LAVALOGGED);
    }
}
