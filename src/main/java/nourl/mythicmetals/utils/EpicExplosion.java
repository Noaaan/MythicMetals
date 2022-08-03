package nourl.mythicmetals.utils;

import net.minecraft.block.Blocks;
import net.minecraft.server.ServerTask;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public final class EpicExplosion {
    private EpicExplosion() {

    }

    public static void explode(ServerWorld world, int x, int y, int z, int radius) {
        int radiusSq = radius * radius;
        var pos = new BlockPos.Mutable();

        for (int cx = -radius; cx < radius; cx += 16) {
            for (int cy = -radius; cy < radius; cy += 16) {
                for (int cz = -radius; cz < radius; cz += 16) {
                    int chunkX = cx;
                    int chunkY = cy;
                    int chunkZ = cz;

                    world.getServer().send(new ServerTask(world.getServer().getTicks(), () -> {
                        for (int ox = chunkX; ox < chunkX + 16 && ox < radius; ox++) {
                            for (int oy = chunkY; oy < chunkY + 16 && oy < radius; oy++) {
                                for (int oz = chunkZ; oz < chunkZ + 16 && oz < radius; oz++) {
                                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                                    pos.set(x + ox, y + oy, z + oz);
                                    var state = world.getBlockState(pos);

                                    if (state.isAir() || state.getHardness(world, pos) == -1) continue;

                                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                                }
                            }
                        }
                    }));
                }
            }
        }

    }
}
