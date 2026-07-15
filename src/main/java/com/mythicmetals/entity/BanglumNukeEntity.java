package com.mythicmetals.entity;

import com.mojang.authlib.GameProfile;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.block.NukeCore;
import com.mythicmetals.damage.BanglumNukeSource;
import com.mythicmetals.damage.EpicExplosion;
import com.mythicmetals.damage.MythicDamageTypes;
import com.mythicmetals.registry.RegisterSounds;
import eu.pb4.common.protection.api.CommonProtection;
import io.wispforest.endec.impl.KeyedEndec;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import java.util.function.Predicate;

public class BanglumNukeEntity extends BanglumTntEntity {
    private static final int DEFAULT_FUSE = 200;
    private static final KeyedEndec<Block> CORE_BLOCK_KEY = MinecraftEndecs.ofRegistry(BuiltInRegistries.BLOCK).keyed("core_block", Blocks.AIR);

    private Block coreBlock = Blocks.AIR;

    public BanglumNukeEntity(EntityType<? extends BanglumNukeEntity> entityType, Level world) {
        super(entityType, world);
    }

    public BanglumNukeEntity(Level world, double x, double y, double z, @Nullable LivingEntity igniter, Block coreBlock) {
        this(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE, world);
        this.setPos(x, y, z);
        double d = world.random.nextDouble() * (float) (Math.PI * 2);
        this.setDeltaMovement(-Math.sin(d) * 0.01, 0.2F, -Math.cos(d) * 0.01);
        this.setFuse(DEFAULT_FUSE);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.causingEntity = igniter;
        this.coreBlock = coreBlock;
    }

    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        this.coreBlock = valueInput.get(CORE_BLOCK_KEY);
    }

    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.put(CORE_BLOCK_KEY, coreBlock);
    }

    @Override
    public double getSmokeParticleHeight() {
        return 2.5;
    }

    @Override
    protected void explode() {
        int radius = MythicMetals.CONFIG.banglumNukeCoreRadius();
        int baseDamage = 1;
        var world = ((ServerLevel) level());

        // Decides what blocks are ignored by the nuke
        Predicate<BlockState> statePredicate = state -> true;

        if (coreBlock instanceof NukeCore core) {
            statePredicate = core.getPredicate();
            baseDamage = (int) (baseDamage * core.damageModifier());
            radius = (int) (radius * core.radiusModifier());
        }
//        if (coreBlock == MythicBlocks.QUADRILLUM_NUKE_CORE) {
//            radius = (radius * 2) / 3;
//            baseDamage = 2;
//        }

        ServerPlayer playerCause = causingEntity instanceof ServerPlayer player ? player : null;
        GameProfile playerCauseProfile = playerCause == null ? CommonProtection.UNKNOWN : playerCause.getGameProfile();
        EpicExplosion explosion = new EpicExplosion(Explosion.BlockInteraction.DESTROY_WITH_DECAY, world, this.position(), this, playerCause, radius, world.damageSources().source(MythicDamageTypes.BANGLUM_NUKE), statePredicate);
        explosion.explode();

        int soundRadius = radius * 3;

        // TODO - Find a better way to play the sound to far-away players. Maybe use PositionedSoundInstance and the sound manager?
        for (Player player : world.players()) {
            if (player.distanceToSqr(this) > soundRadius * soundRadius) continue;

            world.playSound(this, this.blockPosition(), RegisterSounds.BANGLUM_NUKE_EXPLOSION, SoundSource.BLOCKS, 5.0F, (1.0F + (this.level().random.nextFloat() - this.level().random.nextFloat()) * 0.2F) * 0.7F);
        }

        // Handle damaging entities near the nuke explosion
        for (var entity : world.getEntities(this, AABB.ofSize(position(), radius * 2, radius * 2, radius * 2))) {
            if (entity.ignoreExplosion(explosion)) continue;
            if (!CommonProtection.canDamageEntity(level(), entity, playerCauseProfile, playerCause)) continue;

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
                        world.damageSources().damageTypes.get(MythicDamageTypes.BANGLUM_NUKE.identifier()).orElseThrow(),
                        this,
                        this.getCausingEntity());
                    entity.hurtServer(world, banglumNukeSource, Mth.floor((distanceModifier * distanceModifier + distanceModifier) * 7.0 * radius + 1.0));

                    double knockback = distanceModifier * 5;
                    if (entity instanceof LivingEntity living) {
                        knockback = distanceModifier * (5.0 - living.getAttributeValue(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE));
                    }

                    entity.push(x * knockback, y * knockback, z * knockback);
                }
            }
        }
    }
}
