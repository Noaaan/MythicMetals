package nourl.mythicmetals.block;

import blue.endless.jankson.annotation.Nullable;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetals.block.entity.AquariumResonatorBlockEntity;
import nourl.mythicmetals.block.entity.RegisterBlockEntityTypes;

public class AquariumResonatorBlock extends BlockWithEntity {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    public static final MapCodec<AquariumResonatorBlock> CODEC = createCodec(AquariumResonatorBlock::new);

    protected AquariumResonatorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ACTIVE, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
        super.appendProperties(builder);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AquariumResonatorBlockEntity(RegisterBlockEntityTypes.AQUARIUM_RESONATOR, pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, RegisterBlockEntityTypes.AQUARIUM_RESONATOR, AquariumResonatorBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
