package nourl.mythicmetals.utils;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.function.Predicate;

public final class EpicExplosion {
    private EpicExplosion() {

    }

    public static void explode(ServerWorld world, int x, int y, int z, int radius, Predicate<BlockState> statePredicate) {
        int radiusSq = radius * radius;
        var pos = new BlockPos.Mutable();

        for (int ox = -radius; ox < radius; ox++) {
            for (int oy = -radius; oy < radius; oy++) {
                for (int oz = -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    pos.set(x + ox, y + oy, z + oz);
                    var state = world.getBlockState(pos);

                    if (state.isAir() || state.getBlock().getBlastResistance() > 10000) continue;

                    if (!statePredicate.test(state)) continue;

                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }

    }
}
