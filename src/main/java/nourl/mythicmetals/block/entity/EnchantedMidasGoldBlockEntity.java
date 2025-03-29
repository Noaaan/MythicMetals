package nourl.mythicmetals.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class EnchantedMidasGoldBlockEntity extends BlockEntity {
    public EnchantedMidasGoldBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK, pos, state);
    }
}
