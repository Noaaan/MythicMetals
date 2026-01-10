package com.mythicmetals.item.tools;

import com.mythicmetals.MythicAttributeModifier;
import net.minecraft.core.BlockPos;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.List;

public class SteelToolSet extends ToolSet {
    public SteelToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super("steel", material, damage, speed);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new SteelShovel(material, damage, speed, settings);
    }

    public static class SteelShovel extends ShovelItem {
        public SteelShovel(ToolMaterial material, int damage, float speed, Properties settings) {
            super(material, damage, speed, settings);
        }

        @Override
        public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {

            // Special Ability - Shovel Snow for free
            if (!world.isClientSide() && state.is(BlockTags.SNOW)) {
                return true;
            }

            return super.mineBlock(stack, world, state, pos, miner);
        }
    }
}
