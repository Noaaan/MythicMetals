package com.mythicmetals.block;

import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.BanglumNukeEntity;
import com.mythicmetals.registry.RegisterSounds;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class BanglumNukeHandler {
    public static void init() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            var stack = player.getItemInHand(hand);

            if (!stack.is(Items.FLINT_AND_STEEL)) return InteractionResult.PASS;

            var targetBlock = world.getBlockState(hitResult.getBlockPos());

            if (!targetBlock.is(MythicBlocks.BANGLUM.getStorageBlock())
                && !targetBlock.is(MythicBlocks.MORKITE.getStorageBlock()))
                return InteractionResult.PASS;

            var pos = hitResult.getBlockPos();

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        if (tryLightBigTntAt(world, player, pos.getX() - x, pos.getY() - y, pos.getZ() - z)) {
                            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }

            return InteractionResult.PASS;
        });
    }

    public static boolean tryLightBigTntWithDispenser(BlockSource dispenser) {
        var world = dispenser.level();
        BlockState state = world.getBlockState(dispenser.pos().relative(dispenser.state().getValue(DispenserBlock.FACING)));
        var pos = dispenser.pos().relative(dispenser.state().getValue(DispenserBlock.FACING));

        if (!state.is(MythicBlocks.BANGLUM.getStorageBlock())
            && !state.is(MythicBlocks.MORKITE.getStorageBlock()))
            return false;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if (tryLightBigTntAt(world, null, pos.getX() - x, pos.getY() - y, pos.getZ() - z)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean tryLightBigTntAt(Level world, Player player, int x, int y, int z) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int ox = 0; ox < 2; ox++) {
            for (int oy = 0; oy < 2; oy++) {
                for (int oz = 0; oz < 2; oz++) {
                    if (ox == 1 && oy == 1 && oz == 1) continue;

                    BlockState neededState = (ox + oy + oz) % 2 == 0
                        ? MythicBlocks.BANGLUM.getStorageBlock().defaultBlockState()
                        : MythicBlocks.MORKITE.getStorageBlock().defaultBlockState();

                    mutablePos.set(x + ox, y + oy, z + oz);

                    if (world.getBlockState(mutablePos) != neededState)
                        return false;
                }
            }
        }

        mutablePos.set(x + 1, y + 1, z + 1);
        BlockState coreState = world.getBlockState(mutablePos);

        if (!coreState.is(MythicTags.NUKE_CORES)) return false;

        for (var pos : BlockPos.betweenClosed(x, y, z, x + 2, y + 2, z + 2)) {
            world.removeBlock(pos, false);
        }

        if (!world.isClientSide) {
            BanglumNukeEntity nuke = new BanglumNukeEntity(world, x + 1.5, y, z + 1.5, player, coreState.getBlock());
            world.addFreshEntity(nuke);
            world.playSound(
                null, nuke.getX(), nuke.getY(), nuke.getZ(), RegisterSounds.BANGLUM_NUKE_IGNITE, SoundSource.BLOCKS, 1.0F, 1.0F
            );
            world.gameEvent(player, GameEvent.PRIME_FUSE, new BlockPos(x, y, z));
        }

        return true;
    }
}
