package com.mythicmetals.block.entity;

import com.mythicmetals.block.AquariumResonatorBlock;
import com.mythicmetals.block.ConduitPowered;
import com.mythicmetals.misc.MythicParticleSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class AquariumResonatorBlockEntity extends BlockEntity implements ConduitPowered {
    public static final int MAX_RANGE = 24;
    private boolean activated = false;
    private int activeTime = 50;

    public AquariumResonatorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public AquariumResonatorBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.AQUARIUM_RESONATOR, pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, AquariumResonatorBlockEntity blockEntity) {
        if (world.isClientSide()) return;
        if (blockEntity.activated && world.getGameTime() % 40L == 0) {
            if (!state.getValue(AquariumResonatorBlock.ACTIVE)) {
                state = state.setValue(AquariumResonatorBlock.ACTIVE, Boolean.TRUE);
                world.setBlock(pos, state, Block.UPDATE_ALL);
                setChanged(world, pos, state);
            }
            MythicParticleSystem.RESONATOR_PARTICLES.spawn(world, pos.getCenter());
            empowerNearbyEntities(world, pos, state, blockEntity);
        }
        blockEntity.activeTime = Mth.clamp(blockEntity.activeTime - 1, 0, 150);
        if (blockEntity.activeTime == 0) {
            blockEntity.activated = false;
            if (state.getValue(AquariumResonatorBlock.ACTIVE)) {
                state = state.setValue(AquariumResonatorBlock.ACTIVE, Boolean.FALSE);
                world.setBlock(pos, state, Block.UPDATE_ALL);
                setChanged(world, pos, state);
            }
        }
    }

    private static AABB getEffectZone(BlockPos pos) {
        return new AABB(pos).inflate(MAX_RANGE);
    }

    private static void empowerNearbyEntities(Level world, BlockPos pos, BlockState state, AquariumResonatorBlockEntity blockEntity) {
        List<LivingEntity> list = world.getEntitiesOfClass(
                LivingEntity.class, getEffectZone(pos), entity -> entity.showVehicleHealth() && entity.isInWaterOrRain()
        );

        list.forEach(livingEntity -> {
            MythicParticleSystem.RESONATOR_POWER_PARTICLES.spawn(world, livingEntity.position());
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 160, 1, true, false, true));
        });
    }

    @Override
    public void activate() {
        this.activated = true;
        this.activeTime = 150;
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        this.activeTime = nbt.getInt("active_time");
        this.activated = nbt.getBoolean("activated");
        super.loadAdditional(nbt, registryLookup);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup) {
        nbt.putInt("active_time", activeTime);
        nbt.putBoolean("activated", activated);
        super.loadAdditional(nbt, registryLookup);
    }
}