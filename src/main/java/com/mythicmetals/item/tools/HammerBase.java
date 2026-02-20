package com.mythicmetals.item.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;

public class HammerBase extends Item {

    private final int depth;

    public HammerBase(ToolMaterial material, float damage, float speed, Properties settings, int depth) {
        super(material.applyToolProperties(settings, BlockTags.MINEABLE_WITH_PICKAXE, damage, speed, 0));
        this.depth = depth;
    }

    public boolean canBreak(ItemStack stack, BlockGetter view, BlockPos pos) {
        return super.isCorrectToolForDrops(stack, view.getBlockState(pos));
    }

    public int getDepth() {
        return depth;
    }
}
