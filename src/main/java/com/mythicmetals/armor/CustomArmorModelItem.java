package com.mythicmetals.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface CustomArmorModelItem {
    @Environment(EnvType.CLIENT)
    HumanoidModel<HumanoidRenderState> getArmorModel();

    @Environment(EnvType.CLIENT)
    HumanoidModel<HumanoidRenderState> provideArmorModelForSlot(EquipmentSlot slot);

    @NotNull ResourceLocation getArmorTexture(ItemStack stack, EquipmentSlot slot);
}
