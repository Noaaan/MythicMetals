package nourl.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;

// [VanillaCopy] GlazedTerracottaBlock
public class BlockWithFacing extends HorizontalFacingBlock {
    public static final MapCodec<BlockWithFacing> CODEC = createCodec(BlockWithFacing::new);

    @Override
    public MapCodec<BlockWithFacing> getCodec() {
        return CODEC;
    }

    public BlockWithFacing(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}

