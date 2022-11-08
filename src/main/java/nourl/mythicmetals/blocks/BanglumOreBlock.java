package nourl.mythicmetals.blocks;

import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class BanglumOreBlock extends OreBlock {
    public BanglumOreBlock(FabricBlockSettings settings) {
        super(settings);
    }
    
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int rng = random.nextInt(130);
        if (rng < 2 && world.getDimension().ultrawarm()) {
            ClientParticles.setParticleCount(12);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, world, pos, 3.5D);
        } else if (rng == 69) {
            ClientParticles.setParticleCount(6);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, world, pos, 2.5D);
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int chance = world.getDimension().ultrawarm() ? 35 : 6;
        Random random = Random.create();
        // Calculate explosion chance
        var stack = player.getMainHandStack();
        if (stack.hasEnchantments()) {
            chance += (EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, stack) * 5);
            chance += (EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 8);
            chance -= (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) * 40);
        }

        chance = MathHelper.clamp(chance, 0, 80);

        // Roll the dice
        if (random.nextInt(100) <= chance && !player.isCreative()) {
            if (!world.isClient) {
                world.removeBlock(pos, false);
                explode(world, pos);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    private void explode(World world, BlockPos pos) {
        if (world.getDimension().ultrawarm()) {
            world.createExplosion(null, pos.getX(), pos.getY() + 0.6, pos.getZ(), 3.2F, Explosion.DestructionType.DESTROY);
        } else {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.3F, Explosion.DestructionType.BREAK);
        }
    }
}
