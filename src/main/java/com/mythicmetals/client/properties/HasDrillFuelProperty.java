package com.mythicmetals.client.properties;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.component.DrillComponent;
import com.mythicmetals.component.MythicDataComponents;
import io.wispforest.endec.Endec;
import io.wispforest.endec.StructEndec;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class HasDrillFuelProperty implements ConditionalItemModelProperty {

    public static final StructEndec<HasDrillFuelProperty> ENDEC = Endec.unit(new HasDrillFuelProperty());

    public static final MapCodec<HasDrillFuelProperty> CODEC = CodecUtils.toMapCodec(ENDEC);

    @Override
    public boolean get(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity user, int seed, ItemDisplayContext modelTransformationMode) {
        return stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT).hasFuel();
    }

    @Override
    public MapCodec<? extends ConditionalItemModelProperty> type() {
        return CODEC;
    }
}
