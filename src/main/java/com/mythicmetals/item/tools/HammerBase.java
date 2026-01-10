package com.mythicmetals.item.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;

public class HammerBase extends PickaxeItem {

    private final int depth;

    public HammerBase(ToolMaterial material, float damage, float speed, Properties settings, int depth) {
        super(material, damage, speed, settings);
        this.depth = depth;
    }

    public boolean canBreak(ItemStack stack, BlockGetter view, BlockPos pos) {
        return super.isCorrectToolForDrops(stack, view.getBlockState(pos));
    }

    public int getDepth() {
        return depth;
    }
}
