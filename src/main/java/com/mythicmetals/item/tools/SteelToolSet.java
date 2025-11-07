package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.List;

public class SteelToolSet extends ToolSet {
    public SteelToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super("steel", material, damage, speed);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new SteelShovel(material, damage, speed, settings);
    }

    public static class SteelShovel extends ShovelItem {
        public SteelShovel(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

            // Special Ability - Shovel Snow for free
            if (!world.isClient() && state.isIn(BlockTags.SNOW)) {
                return true;
            }

            return super.postMine(stack, world, state, pos, miner);
        }
    }
}
