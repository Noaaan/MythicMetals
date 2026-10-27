package com.mythicmetals.client.rendering;

import net.minecraft.core.BlockPos;

import java.util.List;

public record BlockOutlineOverrideRenderState(
        BlockPos original,
        List<BlockPos> positions
) {
}
