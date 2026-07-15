package com.mythicmetals.block;

import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.BanglumNukeEntity;
import com.mythicmetals.registry.RegisterSounds;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class BanglumNukeHandler {
    public static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var stack = player.getStackInHand(hand);

            if (!stack.isOf(Items.FLINT_AND_STEEL)) return ActionResult.PASS;

            var targetBlock = world.getBlockState(hitResult.getBlockPos());

            if (!targetBlock.isOf(MythicBlocks.BANGLUM.getStorageBlock())
                && !targetBlock.isOf(MythicBlocks.MORKITE.getStorageBlock()))
                return ActionResult.PASS;

            var pos = hitResult.getBlockPos();

            if (tryLightBigTntAt(world, player, pos.getX(), pos.getY(), pos.getZ())) {
                stack.damage(1, player, LivingEntity.getSlotForHand(hand));

                return ActionResult.SUCCESS;
            }

            return ActionResult.PASS;
        });
    }

    public static boolean tryLightBigTntWithDispenser(BlockPointer dispenser) {
        var world = dispenser.world();
        BlockState state = world.getBlockState(dispenser.pos().offset(dispenser.state().get(DispenserBlock.FACING)));
        var pos = dispenser.pos().offset(dispenser.state().get(DispenserBlock.FACING));

        if (!state.isOf(MythicBlocks.BANGLUM.getStorageBlock())
            && !state.isOf(MythicBlocks.MORKITE.getStorageBlock()))
            return false;

        return tryLightBigTntAt(world, null, pos.getX(), pos.getY(), pos.getZ());
    }

    private static boolean tryLightBigTntAt(World world, PlayerEntity player, int x, int y, int z) {
        if (world.isClient()) {
            return false;
        }
        var match = getBanglumNukePattern().searchAround(world, new BlockPos(x, y, z));
        if (match != null) {
            var startPos = match.translate(0, 0, 0);
            var endPos = match.translate(2, 2, 2);
            for (BlockPos pos : BlockPos.iterate(startPos.getBlockPos(), endPos.getBlockPos())) {
                world.removeBlock(pos, false);
                world.updateNeighbors(pos, Blocks.AIR);
            }

            BanglumNukeEntity nuke = new BanglumNukeEntity(world, x + 1.5, y, z + 1.5, player, match.translate(1, 1, 1).getBlockState().getBlock());
            world.spawnEntity(nuke);
            world.playSound(
                null, nuke.getX(), nuke.getY(), nuke.getZ(), RegisterSounds.BANGLUM_NUKE_IGNITE, SoundCategory.BLOCKS, 1.0F, 1.0F
            );
            world.emitGameEvent(player, GameEvent.PRIME_FUSE, new BlockPos(x, y, z));
            return true;
        }

        return false;
    }

    public static BlockPattern getBanglumNukePattern() {
        return BlockPatternBuilder.start()
            .aisle("BMB", "MBM", "BMB")
            .aisle("MBM", "BCB", "MBM")
            .aisle("BMB", "MBM", "BMB")
            .where('B', cachedBlockPos -> {
                return cachedBlockPos != null && cachedBlockPos.getBlockState().equals(MythicBlocks.BANGLUM.getStorageBlock().getDefaultState());
            })
            .where('M', cachedBlockPos -> {
                return cachedBlockPos != null && cachedBlockPos.getBlockState().equals(MythicBlocks.MORKITE.getStorageBlock().getDefaultState());
            })
            .where('C', cachedBlockPosition -> cachedBlockPosition != null && cachedBlockPosition.getBlockState().isIn(MythicTags.NUKE_CORES))
            .build();
    }
}
