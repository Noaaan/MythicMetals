package com.mythicmetals.entity;

import com.mojang.authlib.GameProfile;
import eu.pb4.common.protection.api.CommonProtection;
import io.wispforest.endec.impl.KeyedEndec;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.misc.*;
import com.mythicmetals.registry.RegisterSounds;
import org.jetbrains.annotations.Nullable;
import java.util.function.Predicate;

public class BanglumNukeEntity extends BanglumTntEntity {
    private static final int DEFAULT_FUSE = 200;
    private static final KeyedEndec<Block> CORE_BLOCK_KEY = MinecraftEndecs.ofRegistry(Registries.BLOCK).keyed("core_block", MythicBlocks.BANGLUM_NUKE_CORE);

    private Block coreBlock = MythicBlocks.BANGLUM_NUKE_CORE;

    public BanglumNukeEntity(EntityType<? extends BanglumNukeEntity> entityType, World world) {
        super(entityType, world);
    }

    public BanglumNukeEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter, Block coreBlock) {
        this(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setVelocity(-Math.sin(d) * 0.01, 0.2F, -Math.cos(d) * 0.01);
        this.setFuse(DEFAULT_FUSE);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
        this.coreBlock = coreBlock;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        this.coreBlock = nbt.get(CORE_BLOCK_KEY);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        nbt.put(CORE_BLOCK_KEY, coreBlock);
    }

    @Override
    public double getSmokeParticleHeight() {
        return 2.5;
    }

    @Override
    protected void explode() {
        int radius = MythicMetals.CONFIG.banglumNukeCoreRadius();
        int baseDamage = 1;

        // Decides what blocks are ignored by the nuke
        Predicate<BlockState> statePredicate;

        // Carmot core - Do not destroy ores
        if (coreBlock == MythicBlocks.CARMOT_NUKE_CORE) {
            statePredicate = state -> !state.isIn(MythicTags.CARMOT_NUKE_IGNORED);
        } else if (coreBlock == MythicBlocks.SPONGE_NUKE_CORE) {
            statePredicate = state -> !state.getFluidState().isEmpty();
        } else {
            statePredicate = ignored -> true;
        }

        // Quadrillum core - Double damage, half range
        if (coreBlock == MythicBlocks.QUADRILLUM_NUKE_CORE) {
            radius = (radius * 2) / 3;
            baseDamage = 2;
        }

        ServerPlayerEntity playerCause = causingEntity instanceof ServerPlayerEntity player ? player : null;
        GameProfile playerCauseProfile = playerCause == null ? CommonProtection.UNKNOWN : playerCause.getGameProfile();
        EpicExplosion.explode((ServerWorld) getWorld(), (int) this.getX(), (int) this.getY(), (int) this.getZ(), radius, statePredicate,
            this, playerCause);
        Explosion explosion = new Explosion(this.getWorld(), playerCause, (int) this.getX(), (int) this.getY(), (int) this.getZ(), radius, false, Explosion.DestructionType.DESTROY_WITH_DECAY);

        int soundRadius = radius * 3;

        // FIXME - Find a better way to play the sound to far-away players. Maybe use PositionedSoundInstance and the sound manager?
        for (PlayerEntity player : getWorld().getPlayers()) {
            if (player.squaredDistanceTo(this) > soundRadius * soundRadius) continue;

            player.getWorld().playSound(this, this.getBlockPos(), RegisterSounds.BANGLUM_NUKE_EXPLOSION, SoundCategory.BLOCKS, 5.0F, (1.0F + (this.getWorld().random.nextFloat() - this.getWorld().random.nextFloat()) * 0.2F) * 0.7F);
        }

        // Handle damaging entities near the nuke explosion
        for (var entity : getWorld().getOtherEntities(this, Box.of(getPos(), radius * 2, radius * 2, radius * 2))) {
            if (entity.isImmuneToExplosion(explosion)) continue;
            if (!CommonProtection.canDamageEntity(getWorld(), entity, playerCauseProfile, playerCause)) continue;

            double distanceModifier = baseDamage - entity.distanceTo(this) / (double) radius;
            if (distanceModifier >= 0) {
                double x = entity.getX() - this.getX();
                double y = (entity instanceof BanglumTntEntity ? entity.getY() : entity.getEyeY()) - this.getY();
                double z = entity.getZ() - this.getZ();
                double dist = Math.sqrt(x * x + y * y + z * z);
                if (dist != 0.0) {
                    x /= dist;
                    y /= dist;
                    z /= dist;
                    var banglumNukeSource = new BanglumNukeSource(
                        getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).getEntry(MythicDamageTypes.BANGLUM_NUKE).orElseThrow(),
                        this,
                        this.getCausingEntity());
                    entity.damage(banglumNukeSource, MathHelper.floor((distanceModifier * distanceModifier + distanceModifier) * 7.0 * radius + 1.0));

                    double knockback = distanceModifier * 5;
                    if (entity instanceof LivingEntity living) {
                        knockback = distanceModifier * (5.0 - living.getAttributeValue(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE));
                    }

                    entity.addVelocity(x * knockback, y * knockback, z * knockback);
                }
            }
        }
    }
}
