package com.mythicmetals.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public interface CustomArmorModelItem {
    @Environment(EnvType.CLIENT)
    BipedEntityModel<BipedEntityRenderState> getArmorModel();

    @Environment(EnvType.CLIENT)
    BipedEntityModel<BipedEntityRenderState> provideArmorModelForSlot(EquipmentSlot slot);

    @NotNull Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot);
}
