package nourl.mythicmetals.blocks;

import java.util.Optional;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class PalladiumRailBlock extends AbstractRailBlock implements Lavaloggable {

    public static final EnumProperty<RailShape> SHAPE = Properties.RAIL_SHAPE;
    public static final MapCodec<PalladiumRailBlock> CODEC = createCodec(PalladiumRailBlock::new);
    public static final BooleanProperty LAVALOGGED = BooleanProperty.of("lavalogged");

    @Override
    protected void updateBlockState(BlockState state, World world, BlockPos pos, Block neighbor) {
        if (neighbor.getDefaultState().emitsRedstonePower() && new RailPlacementHelper(world, pos, state).getNeighborCount() == 3) {
            this.updateBlockState(world, pos, state, false);
        }
    }

    public PalladiumRailBlock(Settings settings) {
        super(false, settings);
        this.setDefaultState(this.stateManager.getDefaultState()
            .with(SHAPE, RailShape.NORTH_SOUTH)
            .with(LAVALOGGED, Boolean.FALSE)
            .with(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        RailShape railShape = state.get(SHAPE);

        return state.with(SHAPE, switch (rotation) {
            case CLOCKWISE_180 -> {
                switch (railShape) {
                    case NORTH_SOUTH:
                        yield RailShape.NORTH_SOUTH;
                    case EAST_WEST:
                        yield RailShape.EAST_WEST;
                    case ASCENDING_EAST:
                        yield RailShape.ASCENDING_WEST;
                    case ASCENDING_WEST:
                        yield RailShape.ASCENDING_EAST;
                    case ASCENDING_NORTH:
                        yield RailShape.ASCENDING_SOUTH;
                    case ASCENDING_SOUTH:
                        yield RailShape.ASCENDING_NORTH;
                    case SOUTH_EAST:
                        yield RailShape.NORTH_WEST;
                    case SOUTH_WEST:
                        yield RailShape.NORTH_EAST;
                    case NORTH_WEST:
                        yield RailShape.SOUTH_EAST;
                    case NORTH_EAST:
                        yield RailShape.SOUTH_WEST;
                    default:
                        throw new MatchException(null, null);
                }
            }
            case COUNTERCLOCKWISE_90 -> {
                switch (railShape) {
                    case NORTH_SOUTH:
                        yield RailShape.EAST_WEST;
                    case EAST_WEST:
                        yield RailShape.NORTH_SOUTH;
                    case ASCENDING_EAST:
                        yield RailShape.ASCENDING_NORTH;
                    case ASCENDING_WEST:
                        yield RailShape.ASCENDING_SOUTH;
                    case ASCENDING_NORTH:
                        yield RailShape.ASCENDING_WEST;
                    case ASCENDING_SOUTH:
                        yield RailShape.ASCENDING_EAST;
                    case SOUTH_EAST:
                        yield RailShape.NORTH_EAST;
                    case SOUTH_WEST:
                        yield RailShape.SOUTH_EAST;
                    case NORTH_WEST:
                        yield RailShape.SOUTH_WEST;
                    case NORTH_EAST:
                        yield RailShape.NORTH_WEST;
                    default:
                        throw new MatchException(null, null);
                }
            }
            case CLOCKWISE_90 -> {
                switch (railShape) {
                    case NORTH_SOUTH:
                        yield RailShape.EAST_WEST;
                    case EAST_WEST:
                        yield RailShape.NORTH_SOUTH;
                    case ASCENDING_EAST:
                        yield RailShape.ASCENDING_SOUTH;
                    case ASCENDING_WEST:
                        yield RailShape.ASCENDING_NORTH;
                    case ASCENDING_NORTH:
                        yield RailShape.ASCENDING_EAST;
                    case ASCENDING_SOUTH:
                        yield RailShape.ASCENDING_WEST;
                    case SOUTH_EAST:
                        yield RailShape.SOUTH_WEST;
                    case SOUTH_WEST:
                        yield RailShape.NORTH_WEST;
                    case NORTH_WEST:
                        yield RailShape.NORTH_EAST;
                    case NORTH_EAST:
                        yield RailShape.SOUTH_EAST;
                    default:
                        throw new MatchException(null, null);
                }
            }
            default -> railShape;
        });
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        RailShape railShape = state.get(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                return switch (railShape) {
                    case ASCENDING_NORTH -> state.with(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH -> state.with(SHAPE, RailShape.ASCENDING_NORTH);
                    case SOUTH_EAST -> state.with(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST -> state.with(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST -> state.with(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST -> state.with(SHAPE, RailShape.SOUTH_EAST);
                    default -> super.mirror(state, mirror);
                };
            case FRONT_BACK:
                switch (railShape) {
                    case ASCENDING_EAST: return state.with(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, RailShape.ASCENDING_EAST);
                    case SOUTH_EAST:
                        return state.with(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, RailShape.NORTH_WEST);
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                }
        }

        return super.mirror(state, mirror);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, LAVALOGGED, WATERLOGGED);
    }

    @Override
    public boolean canFillWithFluid(@Nullable PlayerEntity player, BlockView world, BlockPos pos, BlockState state, Fluid fluid) {
        return fluid == Fluids.LAVA;
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!state.get(LAVALOGGED) && fluidState.getFluid() == Fluids.LAVA) {
            if (!world.isClient()) {
                world.setBlockState(pos, state.with(LAVALOGGED, Boolean.TRUE), Block.NOTIFY_ALL);
                world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack tryDrainFluid(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos, BlockState state) {
        if (state.get(LAVALOGGED)) {
            world.setBlockState(pos, state.with(LAVALOGGED, Boolean.FALSE), Block.NOTIFY_ALL);
            if (!state.canPlaceAt(world, pos)) {
                world.breakBlock(pos, true);
            }

            return new ItemStack(Items.LAVA_BUCKET);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Fluids.LAVA.getBucketFillSound();
    }

    @Override
    public MapCodec<PalladiumRailBlock> getCodec() {
        return CODEC;
    }

    @Override
    public Property<RailShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(LAVALOGGED) ? Fluids.LAVA.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.LAVA;
        BlockState blockState = super.getDefaultState();
        Direction direction = ctx.getHorizontalPlayerFacing();
        boolean bl2 = direction == Direction.EAST || direction == Direction.WEST;
        return blockState.with(this.getShapeProperty(), bl2 ? RailShape.EAST_WEST : RailShape.NORTH_SOUTH).with(LAVALOGGED, bl);
    }

    // TODO - Move this somewhere nicer
    public static boolean isLavaLogged(BlockState state) {
        return state.get(LAVALOGGED);
    }
}
