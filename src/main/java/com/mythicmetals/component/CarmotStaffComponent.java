package com.mythicmetals.component;

import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import io.wispforest.owo.ui.core.Color;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import com.mythicmetals.ability.UniqueStaffBlocks;
import java.util.function.Consumer;

public record CarmotStaffComponent(Block block, boolean showTooltip) implements TooltipAppender {

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

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        if (this.block.equals(Blocks.AIR)) {
            tooltip.accept(Text.translatable("tooltip.carmot_staff.empty_staff").setStyle(Style.EMPTY.withColor(Color.ofDye(DyeColor.LIGHT_GRAY).rgb())));
        } else if (UniqueStaffBlocks.hasUniqueBlockInStaff(block)) {
            tooltip.accept(UniqueStaffBlocks.getBlockTranslationKey(block));
        }

    }

    public boolean isEmpty() {
        return this.block.equals(Blocks.AIR);
    }
}
