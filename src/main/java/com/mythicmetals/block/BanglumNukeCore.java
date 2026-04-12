package com.mythicmetals.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.function.Predicate;

public class BanglumNukeCore extends Block implements NukeCore {

    public BanglumNukeCore(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<BlockState> getPredicate() {
        return blockState -> true;
    }

    @Override
    public float damageModifier() {
        return 1;
    }

    @Override
    public float radiusModifier() {
        return 1;
    }
}
