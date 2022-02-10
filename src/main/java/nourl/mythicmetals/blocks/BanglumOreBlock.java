package nourl.mythicmetals.blocks;

import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BanglumOreBlock extends OreBlock {
    public BanglumOreBlock(FabricBlockSettings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int rng = random.nextInt(144);
        if (rng < 2 && world.getDimension().isUltrawarm()) {
            ClientParticles.setParticleCount(12);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, world, pos, 2.5D);
        }
        else if (rng == 69) {
            ClientParticles.setParticleCount(6);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, world, pos, 2.0D);
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        float chance = 10;
        Random random = new Random();
        // Calculate explosion chance
        if (stack.hasEnchantments()) {
            chance = chance + (EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, stack) * 5);
            chance = chance + (EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 10);
            chance = chance - (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) * 40);
        }
        if (world.getDimension().isUltrawarm())
            chance += 25;

        chance = MathHelper.clamp(chance, 0, 80);

        // Roll the dice
        if (random.nextFloat(100) <= chance)
            explode(world, pos);
        System.out.println("The chance of this block exploding was " + chance + "%");

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    private void explode(World world, BlockPos pos) {
        if (world.getDimension().isUltrawarm()) {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3.0F, Explosion.DestructionType.DESTROY);
        }
        else {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.3F, Explosion.DestructionType.BREAK);
        }
    }
}
