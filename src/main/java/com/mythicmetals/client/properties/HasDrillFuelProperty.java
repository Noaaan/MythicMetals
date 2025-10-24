package com.mythicmetals.client.properties;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ModelTransformationMode;
import org.jetbrains.annotations.Nullable;

public class HasDrillFuelProperty implements BooleanProperty {

    public static final StructEndec<HasDrillFuelProperty> ENDEC = Endec.unit(new HasDrillFuelProperty());

    public static final MapCodec<HasDrillFuelProperty> CODEC = CodecUtils.toMapCodec(ENDEC);

    @Override
    public boolean getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ModelTransformationMode modelTransformationMode) {
        return stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel();
    }

    @Override
    public MapCodec<? extends BooleanProperty> getCodec() {
        return CODEC;
    }
}
