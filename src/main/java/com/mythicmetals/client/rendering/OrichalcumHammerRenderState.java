package com.mythicmetals.client.rendering;

import com.mythicmetals.item.tools.HammerBase;
import net.minecraft.world.item.component.Tool;

public record OrichalcumHammerRenderState(
        HammerBase hammer,
        Tool toolComponent
) {
}
