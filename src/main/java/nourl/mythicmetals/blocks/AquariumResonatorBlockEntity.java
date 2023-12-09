package nourl.mythicmetals.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterBlockEntityTypes;
import java.util.List;

public class AquariumResonatorBlockEntity extends BlockEntity implements ConduitPowered {
    /**
     * The range of the Aquarium Resonators buff
     */
    private static final int MAX_RANGE = 24;
    private boolean activated = false;
    private int activeTime = 50;

    public AquariumResonatorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public AquariumResonatorBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.AQUARIUM_RESONATOR, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, AquariumResonatorBlockEntity blockEntity) {
        if (!world.isClient() && world.getTime() % 40L == 0 && blockEntity.activated) {
            empowerNearbyEntities(world, pos, state, blockEntity);
        }
        blockEntity.activeTime = MathHelper.clamp(blockEntity.activeTime - 1, 0, 50);
        if (blockEntity.activeTime == 0) {
            blockEntity.activated = false;
        }
    }

    private static Box getEffectZone(BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        return new Box(i, j, k, (i + 1), (j + 1), (k + 1)).expand(MAX_RANGE);
    }

    private static void empowerNearbyEntities(World world, BlockPos pos, BlockState state, AquariumResonatorBlockEntity blockEntity) {
        List<LivingEntity> list = world.getEntitiesByClass(
                LivingEntity.class, getEffectZone(pos), entity -> entity.isLiving() && entity.isTouchingWaterOrRain()
        );

        list.forEach(livingEntity -> livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 160, 1, true, false, true)));
    }

    @Override
    public void activate() {
        activated = true;
        activeTime = 50;
    }
}
