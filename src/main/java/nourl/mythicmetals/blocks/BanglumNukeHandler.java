package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
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

    private static boolean tryLightBigTntAt(World world, PlayerEntity player, int x, int y, int z) {
        for (var checkPos : BlockPos.iterate(x, y, z, x + 2, y + 2, z + 2)) {
            BlockState neededState = (checkPos.getX() - x + checkPos.getY() - y + checkPos.getZ() - z) % 2 == 0
                ? MythicBlocks.BANGLUM.getStorageBlock().getDefaultState()
                : MythicBlocks.MORKITE.getStorageBlock().getDefaultState();

            if (world.getBlockState(checkPos) != neededState) return false;
        }

        for (var pos : BlockPos.iterate(x, y, z, x + 2, y + 2, z + 2)) {
            world.removeBlock(pos, false);
        }

        if (!world.isClient) {
            BanglumNukeEntity nuke = new BanglumNukeEntity(world, x + 1.5, y, z + 1.5, player);
            world.spawnEntity(nuke);
            world.playSound(
                null, nuke.getX(), nuke.getY(), nuke.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F
            );
            world.emitGameEvent(player, GameEvent.PRIME_FUSE, new BlockPos(x, y, z));
        }

        return true;
    }
}
