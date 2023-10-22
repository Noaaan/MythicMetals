package nourl.mythicmetals.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import nourl.mythicmetals.registry.RegisterBlockEntityTypes;

public class EnchantedMidasGoldBlockEntity extends BlockEntity {
    public EnchantedMidasGoldBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK_ENTITY_TYPE, pos, state);
    }
}
