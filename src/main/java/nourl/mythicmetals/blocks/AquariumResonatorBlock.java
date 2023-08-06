package nourl.mythicmetals.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class AquariumResonatorBlock extends BlockWithEntity {

    protected AquariumResonatorBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AquariumResonatorBlockEntity(RegisterBlockEntityTypes.AQUARIUM_RESONATOR_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, RegisterBlockEntityTypes.AQUARIUM_RESONATOR_BLOCK_ENTITY_TYPE, AquariumResonatorBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
