package com.mythicmetals.misc;



import com.mojang.authlib.GameProfile;
import com.mythicmetals.item.tools.HammerBase;
import eu.pb4.common.protection.api.CommonProtection;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockBreaker {

    public static boolean isProtected(Level world, BlockPos blockPos, GameProfile profile, @Nullable Player player) {
        return !CommonProtection.canBreakBlock(world, blockPos, profile, player);
    }

    public static boolean isProtected(Level world, BlockPos blockPos, Explosion explosion, GameProfile profile, @Nullable Player player) {
        return !CommonProtection.canExplodeBlock(world, blockPos, explosion, profile, player);
    }

    public static Iterable<BlockPos> findBlocks(UseOnContext context, int depth) {

        Iterable<BlockPos> iterator;

        var facing = context.getClickedFace().getOpposite();
        var pos = context.getClickedPos();
        var pos2 = context.getClickedPos().relative(facing, depth);

        if (facing.equals(Direction.DOWN) || facing.equals(Direction.UP)) {
            iterator = BlockPos.betweenClosed(
                pos.east().relative(Direction.NORTH),
                pos2.west().relative(Direction.SOUTH)
            );
        } else {
            iterator = BlockPos.betweenClosed(
                pos.below().relative(facing.getCounterClockWise(Direction.Axis.Y)),
                pos2.above().relative(facing.getClockWise(Direction.Axis.Y))
            );
        }


        return iterator;
    }

    public static Iterable<BlockPos> findBlocks(Direction facing, BlockPos pos, int depth) {
        Iterable<BlockPos> iterator;

        var pos2 = pos.relative(facing, depth);

        if (facing.equals(Direction.DOWN) || facing.equals(Direction.UP)) {
            iterator = BlockPos.betweenClosed(
                pos.east().relative(Direction.NORTH),
                pos2.west().relative(Direction.SOUTH)
            );
        } else {
            iterator = BlockPos.betweenClosed(
                pos.below().relative(facing.getCounterClockWise(Direction.Axis.Y)),
                pos2.above().relative(facing.getClockWise(Direction.Axis.Y))
            );
        }

        return iterator;
    }

    public static double getReachDistance(Player playerEntity) {
        return playerEntity.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);
    }

    public static void initHammerTime() {
        // Original Block Pos is always the center block of where the hammer hits
        PlayerBlockBreakEvents.BEFORE.register((world, player, originalBlockPos, state, blockEntity) -> {
            var stack = player.getMainHandItem();

            if (!(stack.getItem() instanceof HammerBase hammer)) {
                return true; // don't do this for non-hammers
            }
            if (!hammer.isCorrectToolForDrops(stack, state)) {
                return true; // don't break anything extra if you are not mining rocks or stones
            }
            if (isProtected(world, originalBlockPos, player.getGameProfile(), player)) {
                return false;
            }
            var reach = BlockBreaker.getReachDistance(player);

            BlockHitResult blockHitResult = (BlockHitResult) player.pick(reach, 1, false);

            var facing = blockHitResult.getDirection().getOpposite();
            var blocks = BlockBreaker.findBlocks(facing, originalBlockPos, hammer.getDepth());

            boolean hasMined = false;
            for (BlockPos pos : blocks) {
                // Ignore the center block, to prevent an edge case where the middle block is broken thrice
                if (pos.equals(originalBlockPos)) {
                    continue;
                }
                if (isProtected(world, pos, player.getGameProfile(), player)) continue;
                if (hammer.canBreak(stack, world, pos) && !player.isCreative()) {
                    // Call Block.onBreak here, to allow interactions when a player breaks blocks
                    // Note that the center block still calls onBreak twice
                    world.getBlockState(pos).getBlock().playerWillDestroy(world, pos, state, player);
                    BlockEntity breakEntity = world.getBlockState(pos).getBlock() instanceof EntityBlock ? world.getBlockEntity(pos) : null;
                    Block.dropResources(world.getBlockState(pos), world, originalBlockPos, breakEntity, player, stack);
                    world.destroyBlock(pos, false, player);
                    hasMined = true;
                } else if (player.isCreative()) {
                    world.destroyBlock(pos, false, null);
                }
            }
            if (hasMined) stack.hurtAndBreak(2, player, EquipmentSlot.MAINHAND);

            return true;
        });
    }

    public static float calculateHardestDelta(BlockHitResult blockHitResult, Player player, HammerBase hammer) {
        // The hardest, and slowest, delta
        // This speed is how much progress you are making each tick (I think...)
        float hardestDelta = 1.0F;

        // Create an iterator around the blocks that are about to be broken
        var hammeredBlocks = BlockBreaker.findBlocks(
            blockHitResult.getDirection().getOpposite(), blockHitResult.getBlockPos(), hammer.getDepth());

        for (BlockPos pos : hammeredBlocks) {
            var state = player.level().getBlockState(pos);
            // Ignore any blocks that are not minable
            if (!state.isAir() && hammer.isCorrectToolForDrops(hammer.getDefaultInstance(), state)) {
                // Set the current delta to the lowest value in the block iterator
                var delta = player.getDestroySpeed(state) / 30 / state.getDestroySpeed(player.level(), pos);
                if (hardestDelta > delta) {
                    hardestDelta = delta;
                }
            }
        }

        return hardestDelta;
    }
}
