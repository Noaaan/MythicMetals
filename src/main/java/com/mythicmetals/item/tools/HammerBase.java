package com.mythicmetals.item.tools;

import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class HammerBase extends PickaxeItem {

    private final int depth;

    public HammerBase(ToolMaterial material, float damage, float speed, Settings settings, int depth) {
        super(material, damage, speed, settings);
        this.depth = depth;
    }

    public boolean canBreak(ItemStack stack, BlockView view, BlockPos pos) {
        return super.isCorrectForDrops(stack, view.getBlockState(pos));
    }

    public int getDepth() {
        return depth;
    }
}
