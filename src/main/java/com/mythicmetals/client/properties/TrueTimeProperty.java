package com.mythicmetals.client.properties;

import com.mojang.serialization.MapCodec;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public class TrueTimeProperty implements RangeSelectItemModelProperty {

    public static final StructEndec<TrueTimeProperty> ENDEC = Endec.unit(TrueTimeProperty::new);
    public static final MapCodec<TrueTimeProperty> CODEC = CodecUtils.toMapCodec(ENDEC);

    protected TrueTimeProperty() {
        super();
    }

    @Override
    public float get(ItemStack stack, @Nullable ClientLevel world, @Nullable ItemOwner holder, int seed) {
        if (world == null) return 0;
        return getTime(world);
    }

    @Override
    public MapCodec<? extends RangeSelectItemModelProperty> type() {
        return CODEC;
    }


    private float getTime(Level level) {
        return level.getOverworldClockTime() / 24000.0f;
    }
}
