package nourl.mythicmetals.misc;

import com.mojang.authlib.GameProfile;
import eu.pb4.common.protection.api.CommonProtection;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

import static net.minecraft.block.Block.dropStacks;

public final class EpicExplosion {
    private EpicExplosion() {

    }

    /**
     * Cause a large explosion
     * @param world World where the explosion happened
     * @param x X-cord for the explosion center
     * @param y Y-cord for the explosion center
     * @param z Z-cord for the explosion center
     * @param radius Explosion radius
     * @param statePredicate Blockstate predicate for filtering out specific blocks
     * @param exploder  Entity which caused the explosion
     * @param cause PlayerEntity which triggered the explosion, used to check against claim protection
     */
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

    /**
     * Absorbs water around a center point in a given radius
     *
     * @param world World where the explosion happened
     * @param x X-cord for the center of this interaction
     * @param y Y-cord for the center of this interaction
     * @param z Z-cord for the center of this interaction
     * @param radius Water absorption radius
     * @param cause PlayerEntity which triggered this, used to check against claim protection
     */
    public static void absorbWater(ServerWorld world, int x, int y, int z, int radius, @Nullable PlayerEntity cause) {
        int radiusSq = radius * radius;
        var pos = new BlockPos.Mutable();

        GameProfile playerId = cause != null ? cause.getGameProfile() : CommonProtection.UNKNOWN;

        for (int ox = -radius; ox < radius; ox++) {
            for (int oy = -radius; oy < radius; oy++) {
                for (int oz = -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    pos.set(x + ox, y + oy, z + oz);

                    if (!CommonProtection.canBreakBlock(world, pos, playerId, cause)) continue;

                    var state = world.getBlockState(pos);
                    var fluidState = world.getFluidState(pos);
                    var material = state.getMaterial();

                    if (fluidState.isIn(FluidTags.WATER)) {
                        if (state.getBlock() instanceof FluidDrainable drainable && drainable.tryDrainFluid(world, pos, state).isEmpty()) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState());
                        } else if (material == Material.UNDERWATER_PLANT || material == Material.REPLACEABLE_UNDERWATER_PLANT) {
                            BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
                            dropStacks(state, world, pos, blockEntity);
                            world.setBlockState(pos, Blocks.AIR.getDefaultState());
                        }
                    }
                }
            }
        }

    }
}
