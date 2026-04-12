package com.mythicmetals.block;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StarriteOreBlock extends DropExperienceBlock {
    public StarriteOreBlock(Properties properties, UniformInt uniformIntProvider) {
        super(uniformIntProvider, properties);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        ClientParticles.spawnCenteredOnBlock(new DustParticleOptions(UsefulSingletonForColorUtil.MetalColors.STARRITE.argb(), 1F), world, pos, 2.0D);
    }
}
