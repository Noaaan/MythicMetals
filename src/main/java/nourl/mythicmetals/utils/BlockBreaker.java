package nourl.mythicmetals.utils;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

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
}
