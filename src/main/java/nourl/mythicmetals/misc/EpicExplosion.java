package nourl.mythicmetals.misc;

import com.mojang.authlib.GameProfile;
import eu.pb4.common.protection.api.CommonProtection;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public final class EpicExplosion {
    private EpicExplosion() {

    }

    public static void explode(ServerWorld world, int x, int y, int z, int radius, Predicate<BlockState> statePredicate,
                               @Nullable Entity exploder, @Nullable PlayerEntity cause) {
        int radiusSq = radius * radius;
        var pos = new BlockPos.Mutable();
        Explosion explosion = null;

        if (exploder != null) {
            explosion = new Explosion(world, exploder, x, y, z, radius, false, Explosion.DestructionType.DESTROY_WITH_DECAY);
        }

        GameProfile playerId = cause != null ? cause.getGameProfile() : CommonProtection.UNKNOWN;

        for (int ox = -radius; ox < radius; ox++) {
            for (int oy = -radius; oy < radius; oy++) {
                for (int oz = -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    pos.set(x + ox, y + oy, z + oz);
                    var state = world.getBlockState(pos);

                    if (state.isAir() || state.getBlock().getBlastResistance() > 10000) continue;

                    if (!statePredicate.test(state)) continue;

                    if (explosion != null) {
                        if (!CommonProtection.canExplodeBlock(world, pos, explosion, playerId, cause)) continue;
                    } else {
                        if (!CommonProtection.canBreakBlock(world, pos, playerId, cause)) continue;
                    }

                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
            }
        }

    }
}
