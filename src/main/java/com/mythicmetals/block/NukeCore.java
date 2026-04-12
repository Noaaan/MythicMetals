package com.mythicmetals.block;

import net.minecraft.world.level.block.state.BlockState;
import java.util.function.Predicate;

public interface NukeCore {
    Predicate<BlockState> getPredicate();

    float damageModifier();

    float radiusModifier();
}
