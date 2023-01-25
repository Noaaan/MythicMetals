package nourl.mythicmetals.blocks;

import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class StarriteOreBlock extends ExperienceDroppingBlock {
    public StarriteOreBlock(Settings settings, UniformIntProvider uniformIntProvider) {
        super(settings, uniformIntProvider);
    }

    // TODO - Migrate to particle system. Starrite RGB = 245, 108, 227
    private static final Vector3f starriteColour = new Vector3f(0xF56CE3);

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        ClientParticles.spawnCenteredOnBlock(new DustParticleEffect(starriteColour, 1F), world, pos, 2.0D);
    }
}
