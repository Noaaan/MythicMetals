package nourl.mythicmetals.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class AquariumStewardBlock {} /* extends BlockWithEntity {

    protected AquariumStewardBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AquariumStewardBlockEntity(RegisterBlockEntityTypes.AQUARIUM_STEWARD, pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, RegisterBlockEntityTypes.AQUARIUM_STEWARD, AquariumStewardBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
*/
