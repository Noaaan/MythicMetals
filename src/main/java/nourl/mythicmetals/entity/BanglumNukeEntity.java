package nourl.mythicmetals.entity;

import com.mojang.authlib.GameProfile;
import eu.pb4.common.protection.api.CommonProtection;
import io.wispforest.owo.nbt.NbtKey;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.misc.BanglumNukeSource;
import nourl.mythicmetals.misc.EpicExplosion;
import nourl.mythicmetals.misc.MythicDamageTypes;
import nourl.mythicmetals.registry.RegisterSounds;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class BanglumNukeEntity extends BanglumTntEntity {
    private static final int DEFAULT_FUSE = 200;
    private static final NbtKey<Block> CORE_BLOCK_KEY = new NbtKey<>("CoreBlock", NbtKey.Type.ofRegistry(Registries.BLOCK));

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

        this.coreBlock = nbt.getOr(CORE_BLOCK_KEY, MythicBlocks.BANGLUM_NUKE_CORE);
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

        // Handle different cores
        if (coreBlock == MythicBlocks.CARMOT_NUKE_CORE) {
            statePredicate = state -> !state.isIn(MythicTags.CARMOT_NUKE_IGNORED);
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
        EpicExplosion.explode((ServerWorld) getWorld(), (int) getX(), (int) getY(), (int) getZ(), radius, statePredicate,
                              this, playerCause);

        int soundRadius = radius * 3;

        for (PlayerEntity player : getWorld().getPlayers()) {
            if (player.squaredDistanceTo(this) > soundRadius * soundRadius) continue;

            player.playSound(RegisterSounds.BANGLUM_NUKE_EXPLOSION, SoundCategory.BLOCKS, 5.0F, (1.0F + (this.getWorld().random.nextFloat() - this.getWorld().random.nextFloat()) * 0.2F) * 0.7F);
        }

        for (var entity : getWorld().getOtherEntities(this, Box.of(getPos(), radius * 2, radius * 2, radius * 2))) {
            if (entity.isImmuneToExplosion()) continue;
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
                        knockback = ProtectionEnchantment.transformExplosionKnockback(living, knockback);
                    }

                    entity.addVelocity(x * knockback, y * knockback, z * knockback);
                }
            }
        }
    }
}
