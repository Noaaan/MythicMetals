package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

public class BanglumOreBlock extends OreBlock {
    public BanglumOreBlock(FabricBlockSettings settings) {
        super(settings);
    }


    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {

        super.onDestroyedByExplosion(world, pos, explosion);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }
}
