package com.mythicmetals.block;

import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.UpgradeComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import io.wispforest.owo.particles.ClientParticles;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEnchantmentTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BanglumOreBlock extends DropExperienceBlock {
    public BanglumOreBlock(BlockBehaviour.Properties settings) {
        super(ConstantInt.ZERO, settings);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        int rng = random.nextInt(130);
        if (rng < 2 && isUltrawarm(level)) {
            ClientParticles.setParticleCount(12);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, level, pos, 4.5D);
        } else if (rng == 69) {
            ClientParticles.setParticleCount(6);
            ClientParticles.spawnCenteredOnBlock(ParticleTypes.LARGE_SMOKE, level, pos, 3.5D);
        }
        super.animateTick(state, level, pos, random);
    }

    /**
     * Occasionally, or in some cases often, explode when mined
     */
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        int chance = isUltrawarm(level) ? 35 : 7;
        RandomSource random = RandomSource.create();
        var stack = player.getMainHandItem();
        var upgrades = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));

        // This living ore is allergic to Efficiency and Fortune, but is defused by Silk Touch
        if (stack.isEnchanted()) {
            var enchantments = EnchantmentHelper.getEnchantmentsForCrafting(stack);
            for (var enchantment : enchantments.keySet()) {
                if (enchantment.is(MythicTags.SILK_TOUCH_LIKE)) {
                    chance -= 45;
                }
                if (enchantment.is(ConventionalEnchantmentTags.INCREASE_BLOCK_DROPS)) {
                    chance += enchantments.getLevel(enchantment) * 7;
                }
                if (enchantment.is(MythicTags.INCREASES_MINING_SPEED)) {
                    chance += enchantments.getLevel(enchantment) * 5;
                }
            }
        }

        // Extra fortune = more allergic
        if (upgrades.hasUpgrade(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE))) {
            chance += 10;
        }

        // Banglum Defuser really living up to its name
        if (upgrades.hasUpgrade(MythicMaterials.STORMYX.extraItems().get(MythicResourceKeys.STORMYX_SHELL))) {
            chance -= 92;
        }

        // Clamp at 80%, just so you don't use these to *reliably* blow up stuff
        chance = Mth.clamp(chance, 0, 80);

        // Roll the dice
        if (random.nextInt(100) <= chance && !player.isCreative()) {
            if (!level.isClientSide()) {
                level.removeBlock(pos, false);
                explode(level, pos);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    private void explode(Level level, BlockPos pos) {
        if (isUltrawarm(level)) {
            level.explode(null, pos.getX(), pos.getY() + 0.6, pos.getZ(), 3.2F, Level.ExplosionInteraction.BLOCK);
        } else {
            level.explode(null, pos.getX(), pos.getY(), pos.getZ(), 0.3F, Level.ExplosionInteraction.BLOCK);
        }
    }

    public boolean isUltrawarm(Level level) {
        return level.dimensionType().attributes().contains(EnvironmentAttributes.WATER_EVAPORATES);
    }
}
