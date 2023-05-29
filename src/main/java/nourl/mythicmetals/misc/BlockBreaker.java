package nourl.mythicmetals.misc;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nourl.mythicmetals.item.tools.HammerBase;

public class BlockBreaker {

    public static Iterable<BlockPos> findBlocks(ItemUsageContext context, int depth) {

        Iterable<BlockPos> iterator;

        var facing = context.getSide().getOpposite();
        var pos = context.getBlockPos();
        var pos2 = context.getBlockPos().offset(facing, depth);

        if (facing.equals(Direction.DOWN) || facing.equals(Direction.UP)) {
            iterator = BlockPos.iterate(
                    pos.east().offset(Direction.NORTH),
                    pos2.west().offset(Direction.SOUTH)
            );
        } else {
            iterator = BlockPos.iterate(
                    pos.down().offset(facing.rotateCounterclockwise(Direction.Axis.Y)),
                    pos2.up().offset(facing.rotateClockwise(Direction.Axis.Y))
            );
        }


        return iterator;
    }

    public static Iterable<BlockPos> findBlocks(Direction facing, BlockPos pos, int depth) {
        Iterable<BlockPos> iterator;

        var pos2 = pos.offset(facing, depth);

        if (facing.equals(Direction.DOWN) || facing.equals(Direction.UP)) {
            iterator = BlockPos.iterate(
                    pos.east().offset(Direction.NORTH),
                    pos2.west().offset(Direction.SOUTH)
            );
        } else {
            iterator = BlockPos.iterate(
                    pos.down().offset(facing.rotateCounterclockwise(Direction.Axis.Y)),
                    pos2.up().offset(facing.rotateClockwise(Direction.Axis.Y))
            );
        }

        return iterator;
    }

    public static double getReachDistance(PlayerEntity playerEntity) {
        double base = playerEntity.isCreative() ? 5.0F : 4.5F;
        if (FabricLoader.getInstance().isModLoaded("reach-entity-attributes")) {
            return ReachEntityAttributes.getReachDistance(playerEntity, base);
        }

        return base;
    }

    public static void initHammerTime() {
        // Original Block Pos is always the center block of where the hammer hits
        PlayerBlockBreakEvents.BEFORE.register((world, player, originalBlockPos, state, blockEntity) -> {
            var stack = player.getMainHandStack();

            if (!(stack.getItem() instanceof HammerBase hammer)) return true; // don't do this for non-hammers
            if (!hammer.isSuitableFor(state)) return true; // don't break anything extra if you are not mining rocks or stones
            var reach = BlockBreaker.getReachDistance(player);

            BlockHitResult blockHitResult = (BlockHitResult) player.raycast(reach, 1, false);

            var facing = blockHitResult.getSide().getOpposite();
            var blocks = BlockBreaker.findBlocks(facing, originalBlockPos, hammer.getDepth());

            boolean hasMined = false;
            for (BlockPos pos : blocks) {
                // Ignore the center block, to prevent an edge case where the middle block is broken thrice
                if (pos.equals(originalBlockPos)) {
                    continue;
                }
                if (hammer.canBreak(world, pos) && !player.isCreative()) {
                    // Call Block.onBreak here, to allow interactions when a player breaks blocks
                    // Note that the center block still calls onBreak twice
                    world.getBlockState(pos).getBlock().onBreak(world, pos, state, player);
                    BlockEntity breakEntity = world.getBlockState(pos).getBlock() instanceof BlockEntityProvider ? world.getBlockEntity(pos) : null;
                    Block.dropStacks(world.getBlockState(pos), world, originalBlockPos, breakEntity, player, stack);
                    world.breakBlock(pos, false, player);
                    hasMined = true;
                } else if (player.isCreative()) {
                    world.breakBlock(pos, false, null);
                }
            }
            if (hasMined) stack.damage(2, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

            return true;
        });
    }

    public static float calculateHardestDelta(BlockHitResult blockHitResult, PlayerEntity player, HammerBase hammer) {
        // The hardest, and slowest, delta
        // This speed is how much progress you are making each tick (I think...)
        float hardestDelta = 1.0F;

        // Create an iterator around the blocks that are about to be broken
        var hammeredBlocks = BlockBreaker.findBlocks(
                blockHitResult.getSide().getOpposite(), blockHitResult.getBlockPos(), hammer.getDepth());

        for (BlockPos pos : hammeredBlocks) {
            var state = player.world.getBlockState(pos);
            // Ignore any blocks that are not minable
            if (!state.isAir() && hammer.isSuitableFor(state)) {
                // Set the current delta to the lowest value in the block iterator
                var delta = player.getBlockBreakingSpeed(state) / 30 / state.getHardness(player.world, pos);
                if (hardestDelta > delta) {
                    hardestDelta = delta;
                }
            }
        }

        return hardestDelta;
    }
}
