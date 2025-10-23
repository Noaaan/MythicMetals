package com.mythicmetals.client.properties;

import com.mojang.serialization.MapCodec;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrueTimeProperty implements NumericProperty {

    public static final StructEndec<TrueTimeProperty> ENDEC = Endec.unit(TrueTimeProperty::new);
    public static final MapCodec<TrueTimeProperty> CODEC = CodecUtils.toMapCodec(ENDEC);

    protected TrueTimeProperty() {
        super();
    }

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity holder, int seed) {
        if (world == null) return 0;
        return getTime(world);
    }

    @Override
    public MapCodec<? extends NumericProperty> getCodec() {
        return CODEC;
    }


    private float getTime(World world) {
        return world.getTimeOfDay() / 24000.0f;
    }
}
