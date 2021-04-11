package nourl.mythicmetals.ores;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class StarriteOreBlock extends Block {
    public StarriteOreBlock(Settings settings) {
        super(settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Random ints which cycle between negative and positive
        int k = random.nextInt(2) * 2 - 1;
        int j = random.nextInt(2) * 2 - 1;
        int l = random.nextInt(2) * 2 - 1;
        // Get block pos
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        // Add dust particle. Starrite RGB = 245, 108, 227
        world.addParticle(new DustParticleEffect(245.0F / 256.0F, 108.0F / 256.0F, 227.0F / 256.0F, 1F), x + 0.25D + (j * 0.75D), y + 0.5D + (0.5D * l), z + 0.25D + (k * 0.75D), 1.5D * k, 0.25D * l, 1.5D * j);
    }
}
