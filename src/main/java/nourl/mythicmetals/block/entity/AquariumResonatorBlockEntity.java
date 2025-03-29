package nourl.mythicmetals.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import nourl.mythicmetals.block.AquariumResonatorBlock;
import nourl.mythicmetals.block.ConduitPowered;
import nourl.mythicmetals.misc.MythicParticleSystem;
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

    public static void tick(World world, BlockPos pos, BlockState state, AquariumResonatorBlockEntity blockEntity) {
        if (world.isClient()) return;
        if (blockEntity.activated && world.getTime() % 40L == 0) {
            if (!state.get(AquariumResonatorBlock.ACTIVE)) {
                state = state.with(AquariumResonatorBlock.ACTIVE, Boolean.TRUE);
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
                markDirty(world, pos, state);
            }
            MythicParticleSystem.RESONATOR_PARTICLES.spawn(world, pos.toCenterPos());
            empowerNearbyEntities(world, pos, state, blockEntity);
        }
        blockEntity.activeTime = MathHelper.clamp(blockEntity.activeTime - 1, 0, 150);
        if (blockEntity.activeTime == 0) {
            blockEntity.activated = false;
            if (state.get(AquariumResonatorBlock.ACTIVE)) {
                state = state.with(AquariumResonatorBlock.ACTIVE, Boolean.FALSE);
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
                markDirty(world, pos, state);
            }
        }
    }

    private static Box getEffectZone(BlockPos pos) {
        return new Box(pos).expand(MAX_RANGE);
    }

    private static void empowerNearbyEntities(World world, BlockPos pos, BlockState state, AquariumResonatorBlockEntity blockEntity) {
        List<LivingEntity> list = world.getEntitiesByClass(
                LivingEntity.class, getEffectZone(pos), entity -> entity.isLiving() && entity.isTouchingWaterOrRain()
        );

        list.forEach(livingEntity -> {
            MythicParticleSystem.RESONATOR_POWER_PARTICLES.spawn(world, livingEntity.getPos());
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 160, 1, true, false, true));
        });
    }

    @Override
    public void activate() {
        this.activated = true;
        this.activeTime = 150;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.activeTime = nbt.getInt("active_time");
        this.activated = nbt.getBoolean("activated");
        super.readNbt(nbt, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("active_time", activeTime);
        nbt.putBoolean("activated", activated);
        super.readNbt(nbt, registryLookup);
    }
}