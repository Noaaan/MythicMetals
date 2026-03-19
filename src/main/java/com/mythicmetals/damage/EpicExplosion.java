package com.mythicmetals.damage;

import com.mojang.authlib.GameProfile;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.misc.BlockBreaker;
import com.mythicmetals.misc.MythicParticleSystem;
import eu.pb4.common.protection.api.CommonProtection;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.Block.dropResources;

public final class EpicExplosion implements Explosion {

    private final Explosion.BlockInteraction destructionType;
    private final ServerLevel world;
    private final Vec3 pos;
    @Nullable
    private final Entity entity;
    @Nullable
    private final LivingEntity cause;
    private final float radius;
    private final DamageSource damageSource;
    private final Predicate<BlockState> statePredicate;

    public EpicExplosion(BlockInteraction destructionType, ServerLevel world, Vec3 pos, @Nullable Entity entity, @Nullable LivingEntity cause, float radius, DamageSource damageSource, Predicate<BlockState> statePredicate) {

        this.destructionType = destructionType;
        this.world = world;
        this.pos = pos;
        this.entity = entity;
        this.radius = radius;
        this.damageSource = damageSource;
        this.cause = cause;
        this.statePredicate = statePredicate;
    }

    public void explode() {
        int radiusSq = (int) (this.radius * this.radius);
        var mutPos = new BlockPos.MutableBlockPos();

        MythicParticleSystem.EXPLOSIVE_EXPLOSION.spawn(world, this.pos, radius);

        for (int ox = (int) -radius; ox < radius; ox++) {
            for (int oy = (int) -radius; oy < radius; oy++) {
                for (int oz = (int) -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    mutPos.set(pos.x + ox, pos.y + oy, pos.z + oz);
                    var state = world.getBlockState(mutPos);

                    if (state.isAir() || state.getBlock().getExplosionResistance() > 10000) continue;

                    if (!statePredicate.test(state)) continue;

                    if (cause instanceof Player player) {
                        if (BlockBreaker.isProtected(world, mutPos, this, player.getGameProfile(), player)) continue;
                    } else {
                        if (BlockBreaker.isProtected(world, mutPos, CommonProtection.UNKNOWN, null)) continue;
                    }

                    world.setBlockAndUpdate(mutPos, Blocks.AIR.defaultBlockState());
                }
            }
        }

    }

    /**
     * Absorbs water around a center point in a given radius
     *
     * @param world  World where the explosion happened
     * @param x      X-cord for the center of this interaction
     * @param y      Y-cord for the center of this interaction
     * @param z      Z-cord for the center of this interaction
     * @param radius Water absorption radius
     * @param cause  PlayerEntity which triggered this, used to check against claim protection
     */
    public static void absorbWater(ServerLevel world, int x, int y, int z, int radius, @Nullable Player cause) {
        int radiusSq = radius * radius;
        var pos = new BlockPos.MutableBlockPos();

        GameProfile playerId = cause != null ? cause.getGameProfile() : CommonProtection.UNKNOWN;

        for (int ox = -radius; ox < radius; ox++) {
            for (int oy = -radius; oy < radius; oy++) {
                for (int oz = -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    pos.set(x + ox, y + oy, z + oz);

                    if (!CommonProtection.canBreakBlock(world, pos, playerId, cause)) continue;

                    var state = world.getBlockState(pos);
                    var fluidState = world.getFluidState(pos);

                    if (fluidState.is(FluidTags.WATER)) {
                        if (state.getBlock() instanceof BucketPickup drainable && drainable.pickupBlock(cause, world, pos, state).isEmpty()) {
                            world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                        } else if (state.is(MythicTags.SPONGABLES)) {
                            BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
                            dropResources(state, world, pos, blockEntity);
                            world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                        }
                    }
                }
            }
        }

    }

    @Override
    public ServerLevel level() {
        return world;
    }

    @Override
    public BlockInteraction getBlockInteraction() {
        return destructionType;
    }

    @Override
    public @Nullable LivingEntity getIndirectSourceEntity() {
        return cause;
    }

    @Override
    public @Nullable Entity getDirectSourceEntity() {
        return entity;
    }

    @Override
    public float radius() {
        return radius;
    }

    @Override
    public Vec3 center() {
        return pos;
    }

    @Override
    public boolean canTriggerBlocks() {
        return false;
    }

    @Override
    public boolean shouldAffectBlocklikeEntities() {
        return false;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }
}
