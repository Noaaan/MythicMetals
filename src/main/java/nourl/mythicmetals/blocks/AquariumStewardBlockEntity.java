package nourl.mythicmetals.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterBlockEntityTypes;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.UUID;

public class AquariumStewardBlockEntity extends BlockEntity implements ConduitPowered {
    /**
     * The range of the Aquarium Steward's attack
     */
    private static final int MAX_RANGE = 16;
    private static final float DAMAGE = 5.0f;
    private boolean activated = false;
    private int activeTime = 50;
    @Nullable
    private LivingEntity targetEntity;
    @Nullable
    private UUID targetUuid;

    public AquariumStewardBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public AquariumStewardBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.AQUARIUM_STEWARD, pos, state);
    }


    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.containsUuid("Target")) {
            this.targetUuid = nbt.getUuid("Target");
        } else {
            this.targetUuid = null;
        }

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (this.targetEntity != null) {
            nbt.putUuid("Target", this.targetEntity.getUuid());
        }

    }

    public static void tick(World world, BlockPos pos, BlockState state, AquariumStewardBlockEntity blockEntity) {
        if (!world.isClient() && world.getTime() % 40L == 0 && blockEntity.activated) {
            attackHostileEntity(world, pos, state, blockEntity);
        }
        blockEntity.activeTime = MathHelper.clamp(blockEntity.activeTime - 1, 0, 50);
        if (blockEntity.activeTime == 0) {
            blockEntity.activated = false;
        }
    }

    private static Box getAttackZone(BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        return new Box(i, j, k, (i + 1), (j + 1), (k + 1)).expand(MAX_RANGE);
    }

    @Nullable
    private static LivingEntity findTargetEntity(World world, BlockPos pos, UUID uuid) {
        List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class, getAttackZone(pos), entity -> entity.getUuid().equals(uuid));
        return list.size() == 1 ? list.get(0) : null;
    }

    // [VanillaCopy] Conduit Block Entity's attack method, with changes to range and damage
    private static void attackHostileEntity(World world, BlockPos pos, BlockState state, AquariumStewardBlockEntity blockEntity) {
        LivingEntity livingEntity = blockEntity.targetEntity;
        if (!blockEntity.activated) {
            blockEntity.targetEntity = null;
        } else if (blockEntity.targetEntity == null && blockEntity.targetUuid != null) {
            blockEntity.targetEntity = findTargetEntity(world, pos, blockEntity.targetUuid);
            blockEntity.targetUuid = null;
        } else if (blockEntity.targetEntity == null) {
            List<LivingEntity> list = world.getEntitiesByClass(
                    LivingEntity.class, getAttackZone(pos), entity -> entity instanceof Monster && entity.isTouchingWaterOrRain()
            );
            if (!list.isEmpty()) {
                blockEntity.targetEntity = list.get(world.random.nextInt(list.size()));
            }
        } else if (!blockEntity.targetEntity.isAlive() || !pos.isWithinDistance(blockEntity.targetEntity.getBlockPos(), MAX_RANGE)) {
            blockEntity.targetEntity = null;
        }

        if (blockEntity.targetEntity != null) {
            world.playSound(
                    null,
                    blockEntity.targetEntity.getX(),
                    blockEntity.targetEntity.getY(),
                    blockEntity.targetEntity.getZ(),
                    SoundEvents.BLOCK_CONDUIT_ATTACK_TARGET,
                    SoundCategory.BLOCKS,
                    1.0F,
                    1.0F
            );
            blockEntity.targetEntity.damage(blockEntity.targetEntity.getWorld().getDamageSources().magic(), DAMAGE);
        }

        if (livingEntity != blockEntity.targetEntity) {
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        }

    }

    @Override
    public void activate() {
        activated = true;
        activeTime = 50;
    }
}
