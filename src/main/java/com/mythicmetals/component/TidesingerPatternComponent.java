package com.mythicmetals.component;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.endec.Endec;
import io.wispforest.endec.impl.StructEndecBuilder;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Component used for the different armor variants of the Tidesinger Armor
 *
 * @param pattern used for tooltip handling
 * @see TidesingerPatternComponent#TIDESINGER_VARIANTS
 */
public record TidesingerPatternComponent(String pattern) implements TooltipAppender {
    /**
     * Map which holds a string used for translation keys, depending on the coral item used
     */
    public static Map<Item, String> TIDESINGER_VARIANTS = Util.make(new HashMap<>(), map -> {
        map.put(Items.BRAIN_CORAL, "brain");
        map.put(Items.BUBBLE_CORAL, "bubble");
        map.put(Items.FIRE_CORAL, "fire");
        map.put(Items.TUBE_CORAL, "tube");
        map.put(Items.HORN_CORAL, "horn");
    });
    public static final Endec<TidesingerPatternComponent> ENDEC = StructEndecBuilder.of(
        Endec.STRING.fieldOf("pattern", TidesingerPatternComponent::pattern),
        TidesingerPatternComponent::new
    );

    public static TidesingerPatternComponent fromStack(ItemStack stack) {
        return fromItem(stack.getItem());
    }

    public static TidesingerPatternComponent fromItem(Item item) {
        return new TidesingerPatternComponent(TIDESINGER_VARIANTS.getOrDefault(item, ""));
    }

    public static TidesingerPatternComponent empty() {
        return new TidesingerPatternComponent("");
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        Style style = switch (this.pattern) {
            case "brain" -> UsefulSingletonForColorUtil.MetalColors.BRAIN.style();
            case "bubble" -> UsefulSingletonForColorUtil.MetalColors.BUBBLE.style();
            case "fire" -> UsefulSingletonForColorUtil.MetalColors.FIRE.style();
            case "horn" -> UsefulSingletonForColorUtil.MetalColors.HORN.style();
            case "tube" -> UsefulSingletonForColorUtil.MetalColors.TUBE.style();
            default -> Style.EMPTY;
        };

        // Do not append tooltip if there isn't a proper coral on it
        if (style.isEmpty()) {
            return;
        }
        tooltip.accept(Text.translatable("tooltip.tidesinger.coral." + this.pattern).setStyle(style));
    }
}
