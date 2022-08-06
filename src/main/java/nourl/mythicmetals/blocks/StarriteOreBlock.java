package nourl.mythicmetals.blocks;

import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;

import java.util.Random;

public class StarriteOreBlock extends OreBlock {
    public StarriteOreBlock(Settings settings, UniformIntProvider uniformIntProvider) {
        super(settings, uniformIntProvider);
    }

    // Add dust particle. Starrite RGB = 245, 108, 227
    private static final Vec3f starriteColour = new Vec3f(Vec3d.unpackRgb(0xF56CE3));

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        ClientParticles.spawnCenteredOnBlock(new DustParticleEffect(starriteColour, 1F), world, pos, 1.5D);
    }
}
