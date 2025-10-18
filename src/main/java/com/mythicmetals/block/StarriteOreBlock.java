package com.mythicmetals.block;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
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

public class StarriteOreBlock extends ExperienceDroppingBlock {
    public StarriteOreBlock(Settings settings, UniformIntProvider uniformIntProvider) {
        super(uniformIntProvider, settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // TODO - Review if ARGB or RGB
        ClientParticles.spawnCenteredOnBlock(new DustParticleEffect(UsefulSingletonForColorUtil.MetalColors.STARRITE.argb(), 1F), world, pos, 2.0D);
    }
}
