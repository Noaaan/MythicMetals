package com.mythicmetals.client.properties;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.item.component.MidasGoldComponent;
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
        return stack.getOrDefault(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(0)).folds();
    }

    @Override
    public MapCodec<? extends RangeSelectItemModelProperty> type() {
        return CODEC;
    }
}
