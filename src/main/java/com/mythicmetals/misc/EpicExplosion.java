package com.mythicmetals.misc;

import com.mojang.authlib.GameProfile;
import com.mythicmetals.data.MythicTags;
import eu.pb4.common.protection.api.CommonProtection;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import java.util.function.Predicate;

import static net.minecraft.block.Block.dropStacks;

public final class EpicExplosion implements Explosion {

    private final Explosion.DestructionType destructionType;
    private final ServerWorld world;
    private final Vec3d pos;
    @Nullable
    private final Entity entity;
    @Nullable
    private final LivingEntity cause;
    private final float radius;
    private final DamageSource damageSource;
    private final Predicate<BlockState> statePredicate;

    public EpicExplosion(DestructionType destructionType, ServerWorld world, Vec3d pos, @Nullable Entity entity, @Nullable LivingEntity cause, float radius, DamageSource damageSource, Predicate<BlockState> statePredicate) {

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
        var mutPos = new BlockPos.Mutable();

        MythicParticleSystem.EXPLOSIVE_EXPLOSION.spawn(world, this.pos, radius);

        for (int ox = (int) -radius; ox < radius; ox++) {
            for (int oy = (int) -radius; oy < radius; oy++) {
                for (int oz = (int) -radius; oz < radius; oz++) {
                    if (ox * ox + oy * oy + oz * oz > radiusSq) continue;

                    mutPos.set(pos.x + ox, pos.y + oy, pos.z + oz);
                    var state = world.getBlockState(mutPos);

                    if (state.isAir() || state.getBlock().getBlastResistance() > 10000) continue;

                    if (!statePredicate.test(state)) continue;

                    if (cause instanceof PlayerEntity player) {
                        if (BlockBreaker.isProtected(world, mutPos, this, player.getGameProfile(), player)) continue;
                    } else {
                        if (BlockBreaker.isProtected(world, mutPos, CommonProtection.UNKNOWN, null)) continue;
                    }

                    world.setBlockState(mutPos, Blocks.AIR.getDefaultState());
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

                    if (fluidState.isIn(FluidTags.WATER)) {
                        if (state.getBlock() instanceof FluidDrainable drainable && drainable.tryDrainFluid(cause, world, pos, state).isEmpty()) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                        } else if (state.isIn(MythicTags.SPONGABLES)) {
                            BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
                            dropStacks(state, world, pos, blockEntity);
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                        }
                    }
                }
            }
        }

    }

    @Override
    public ServerWorld getWorld() {
        return world;
    }

    @Override
    public DestructionType getDestructionType() {
        return destructionType;
    }

    @Override
    public @Nullable LivingEntity getCausingEntity() {
        return cause;
    }

    @Override
    public @Nullable Entity getEntity() {
        return entity;
    }

    @Override
    public float getPower() {
        return radius;
    }

    @Override
    public Vec3d getPosition() {
        return pos;
    }

    @Override
    public boolean canTriggerBlocks() {
        return false;
    }

    @Override
    public boolean preservesDecorativeEntities() {
        return false;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }
}
