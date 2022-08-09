package nourl.mythicmetals.utils;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.server.ServerTask;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;

public final class EpicExplosion {
    private EpicExplosion() {

    }

    public static void explode(ServerWorld world, int x, int y, int z, int radius) {
        int radiusSq = radius * radius;
        var pos = new BlockPos.Mutable();

        for (int ox = -radius; ox < radius; ox++) {
            for (int oy = -radius; oy < radius; oy++) {
                for (int oz = -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    pos.set(x + ox, y + oy, z + oz);
                    var state = world.getBlockState(pos);

                    if (state.isAir() || state.getHardness(world, pos) == -1) continue;

                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }

    }
}
