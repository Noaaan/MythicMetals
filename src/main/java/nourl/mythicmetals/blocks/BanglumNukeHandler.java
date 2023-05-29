package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.registry.RegisterSounds;

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

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        if (tryLightBigTntAt(world, player, pos.getX() - x, pos.getY() - y, pos.getZ() - z)) {
                            stack.damage(1, player, p -> p.sendToolBreakStatus(hand));

                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

            return ActionResult.PASS;
        });
    }

    public static boolean tryLightBigTntWithDispenser(BlockPointer dispenser) {
        var world = dispenser.getWorld();
        BlockState state = world.getBlockState(dispenser.getPos().offset(dispenser.getBlockState().get(DispenserBlock.FACING)));
        var pos = dispenser.getPos().offset(dispenser.getBlockState().get(DispenserBlock.FACING));

        if (!state.isOf(MythicBlocks.BANGLUM.getStorageBlock())
                && !state.isOf(MythicBlocks.MORKITE.getStorageBlock()))
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

    private static boolean tryLightBigTntAt(World world, PlayerEntity player, int x, int y, int z) {
        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        for (int ox = 0; ox < 2; ox++) {
            for (int oy = 0; oy < 2; oy++) {
                for (int oz = 0; oz < 2; oz++) {
                    if (ox == 1 && oy == 1 && oz == 1) continue;

                    BlockState neededState = (ox + oy + oz) % 2 == 0
                        ? MythicBlocks.BANGLUM.getStorageBlock().getDefaultState()
                        : MythicBlocks.MORKITE.getStorageBlock().getDefaultState();

                    mutablePos.set(x + ox, y + oy, z + oz);

                    if (world.getBlockState(mutablePos) != neededState)
                        return false;
                }
            }
        }

        mutablePos.set(x + 1, y + 1, z + 1);
        BlockState coreState = world.getBlockState(mutablePos);

        if (!coreState.isIn(MythicTags.NUKE_CORES))
            return false;

        for (var pos : BlockPos.iterate(x, y, z, x + 2, y + 2, z + 2)) {
            world.removeBlock(pos, false);
        }

        if (!world.isClient) {
            BanglumNukeEntity nuke = new BanglumNukeEntity(world, x + 1.5, y, z + 1.5, player, coreState.getBlock());
            world.spawnEntity(nuke);
            world.playSound(
                null, nuke.getX(), nuke.getY(), nuke.getZ(), RegisterSounds.BANGLUM_NUKE_IGNITE, SoundCategory.BLOCKS, 1.0F, 1.0F
            );
            world.emitGameEvent(player, GameEvent.PRIME_FUSE, new BlockPos(x, y, z));
        }

        return true;
    }
}
