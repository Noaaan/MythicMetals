package com.mythicmetals.client.properties;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.item.component.GoldFoldedComponent;
import com.mythicmetals.item.component.MythicDataComponents;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class MidasGoldProperty implements RangeSelectItemModelProperty {

    public static final StructEndec<MidasGoldProperty> ENDEC = Endec.unit(MidasGoldProperty::new);
    public static final MapCodec<MidasGoldProperty> CODEC = CodecUtils.toMapCodec(ENDEC);

    protected MidasGoldProperty() {
        super();
    }

    @Override
    public float get(ItemStack stack, @Nullable ClientLevel world, @Nullable ItemOwner holder, int seed) {
        return stack.getOrDefault(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0)).goldFolded();
    }

    @Override
    public MapCodec<? extends RangeSelectItemModelProperty> type() {
        return CODEC;
    }

    public float toModelThreshold(int goldCount) {
        if (goldCount >= 1280) return 1.0f;
        return switch (goldCount / 64) {
            case 1 -> 0.1f;
            case 2, 3 -> 0.2f;
            case 4 -> 0.3f;
            case 5, 6, 7, 8, 9 -> 0.4f;
            case 10, 11 -> 0.5f;
            case 12, 13 -> 0.6f;
            case 14, 15 -> 0.7f;
            case 16, 17 -> 0.8f;
            case 18 -> 0.9f;
            case 19 -> 1.0f;
            default -> 0.0f;
        };
    }
}
