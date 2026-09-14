package com.mythicmetals.api.v2.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public interface CustomArmorModelItem {
    @Environment(EnvType.CLIENT)
    HumanoidModel<HumanoidRenderState> getArmorModel();

    @Environment(EnvType.CLIENT)
    HumanoidModel<HumanoidRenderState> provideArmorModelForSlot(EquipmentSlot slot);

    @NonNull Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot);
}
