package nourl.mythicmetals.utils;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BlockBreaker {

    public static Iterable<BlockPos> findBlocks(ItemUsageContext context) {

        Iterable<BlockPos> iterator;

        var facing = context.getSide().getOpposite();
        var pos = context.getBlockPos();
        var pos2 = context.getBlockPos().offset(facing, 5);

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
}
