package com.mythicmetals.component;

import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;

@Deprecated(since = "0.23.0", forRemoval = true)
public record CarmotStaffComponent(Block block, boolean showTooltip) {

    public static final StructEndec<CarmotStaffComponent> ENDEC = StructEndecBuilder.of(
        MinecraftEndecs.ofRegistry(Registries.BLOCK).fieldOf("block", CarmotStaffComponent::getBlock),
        Endec.BOOLEAN.fieldOf("show_tooltip", carmotStaffComponent -> carmotStaffComponent.showTooltip),
        CarmotStaffComponent::new
    );

    public static final CarmotStaffComponent DEFAULT = new CarmotStaffComponent(Blocks.AIR, true);

    public CarmotStaffComponent(Block block) {
        this(block, true);
    }

    public Block getBlock() {
        return block;
    }

    public boolean isEmpty() {
        return this.block.equals(Blocks.AIR);
    }
}
