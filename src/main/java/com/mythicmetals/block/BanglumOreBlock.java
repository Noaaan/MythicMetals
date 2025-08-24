package com.mythicmetals.block;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.UpgradeComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEnchantmentTags;
import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BanglumOreBlock extends ExperienceDroppingBlock {
    public BanglumOreBlock(AbstractBlock.Settings settings) {
        super(ConstantIntProvider.ZERO, settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int rng = random.nextInt(130);
        if (rng < 2 && world.getDimension().ultrawarm()) {
            ClientParticles.setParticleCount(12);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, world, pos, 4.5D);
        } else if (rng == 69) {
            ClientParticles.setParticleCount(6);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, world, pos, 3.5D);
        }
        super.randomDisplayTick(state, world, pos, random);
    }

    /**
     * Occasionally, or in some cases often, explode when mined
     */
    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int chance = world.getDimension().ultrawarm() ? 35 : 7;
        Random random = Random.create();
        var stack = player.getMainHandStack();
        var upgrades = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));

        // This living ore is allergic to Efficiency and Fortune, but is defused by Silk Touch
        if (stack.hasEnchantments()) {
            var enchantments = EnchantmentHelper.getEnchantments(stack);
            for (var enchantment : enchantments.getEnchantments()) {
                if (enchantment.isIn(MythicTags.SILK_TOUCH_LIKE)) {
                    chance -= 45;
                }
                if (enchantment.isIn(ConventionalEnchantmentTags.INCREASE_BLOCK_DROPS)) {
                    chance += enchantments.getLevel(enchantment) * 7;
                }
                if (enchantment.isIn(MythicTags.INCREASES_MINING_SPEED)) {
                    chance += enchantments.getLevel(enchantment) * 5;
                }
            }
        }

        // Extra fortune = more allergic
        if (upgrades.hasUpgrade(MythicItems.Mats.CARMOT_STONE)) {
            chance += 10;
        }

        // Banglum Defuser really living up to its name
        if (upgrades.hasUpgrade(MythicItems.Mats.STORMYX_SHELL)) {
            chance -= 92;
        }

        // Clamp at 80%, just so you don't use these to *reliably* blow up stuff
        chance = MathHelper.clamp(chance, 0, 80);

        // Roll the dice
        if (random.nextInt(100) <= chance && !player.isCreative()) {
            if (!world.isClient) {
                world.removeBlock(pos, false);
                explode(world, pos);
            }
        }
        return super.onBreak(world, pos, state, player);
    }

    private void explode(World world, BlockPos pos) {
        if (world.getDimension().ultrawarm()) {
            world.createExplosion(null, pos.getX(), pos.getY() + 0.6, pos.getZ(), 3.2F, World.ExplosionSourceType.BLOCK);
        } else {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0.3F, World.ExplosionSourceType.BLOCK);
        }
    }
}
