package com.mythicmetals.block;

import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.BanglumNukeEntity;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.misc.MythicSoundEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.gameevent.GameEvent;

public class BanglumNukeHandler {
    public static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var stack = player.getItemInHand(hand);

            if (!stack.is(Items.FLINT_AND_STEEL)) return InteractionResult.PASS;

            var targetBlock = world.getBlockState(hitResult.getBlockPos());

            if (!targetBlock.is(MythicMaterials.BANGLUM.blockSet().storage().block())
                && !targetBlock.is(MythicMaterials.MORKITE.blockSet().storage().block()))
                return InteractionResult.PASS;

            var pos = hitResult.getBlockPos();

            if (tryLightBigTntAt(world, player, pos.getX(), pos.getY(), pos.getZ())) {
                stack.hurtAndBreak(1, player, hand);
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        });
    }

    public static boolean tryLightBigTntWithDispenser(BlockSource dispenser) {
        var world = dispenser.level();
        BlockState state = world.getBlockState(dispenser.pos().relative(dispenser.state().getValue(DispenserBlock.FACING)));
        var pos = dispenser.pos().relative(dispenser.state().getValue(DispenserBlock.FACING));

        if (!state.is(MythicMaterials.BANGLUM.blockSet().storage().block())
            && !state.is(MythicMaterials.MORKITE.blockSet().storage().block()))
            return false;

        return tryLightBigTntAt(world, null, pos.getX(), pos.getY(), pos.getZ());
    }

    private static boolean tryLightBigTntAt(Level level, Player player, int x, int y, int z) {
        if (level.isClientSide()) {
            return false;
        }
        var match = getBanglumNukePattern().find(level, new BlockPos(x, y, z));
        if (match != null) {
            var startPos = match.getBlock(0, 0, 0);
            var endPos = match.getBlock(2, 2, 2);
            for (BlockPos pos : BlockPos.betweenClosed(startPos.getPos(), endPos.getPos())) {
                level.removeBlock(pos, false);
                level.updateNeighborsAt(pos, Blocks.AIR);
            }

            BanglumNukeEntity nuke = new BanglumNukeEntity(level, x, y + 0.5f, z, player, match.getBlock(1, 1, 1).getState().getBlock());
            level.addFreshEntity(nuke);
            level.playSound(
                null, nuke.getX(), nuke.getY(), nuke.getZ(), MythicSoundEvents.BANGLUM_NUKE_IGNITE, SoundSource.BLOCKS, 1.0F, 1.0F
            );
            CarvedPumpkinBlock.updatePatternBlocks(level, match);
            level.gameEvent(player, GameEvent.PRIME_FUSE, new BlockPos(x, y, z));
            return true;
        }

        return false;
    }

    public static BlockPattern getBanglumNukePattern() {
        assert MythicMaterials.BANGLUM.blockSet() != null;
        assert MythicMaterials.MORKITE.blockSet() != null;
        return BlockPatternBuilder.start()
            .aisle("BMB", "MBM", "BMB")
            .aisle("MBM", "BCB", "MBM")
            .aisle("BMB", "MBM", "BMB")
            .where('B', blockInWorld -> {
                return blockInWorld != null && blockInWorld.getState().equals(MythicMaterials.BANGLUM.blockSet().storage().block().defaultBlockState());
            })
            .where('M', blockInWorld -> {
                return blockInWorld != null && blockInWorld.getState().equals(MythicMaterials.MORKITE.blockSet().storage().block().defaultBlockState());
            })
            .where('C', blockInWorld -> blockInWorld != null && blockInWorld.getState().is(MythicTags.NUKE_CORES))
            .build();
    }
}
