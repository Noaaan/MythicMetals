package com.mythicmetals.client.models;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;

public class HelmetModel extends BipedEntityModel<BipedEntityRenderState> implements CustomArmorModel {
    final EquipmentSlot slot;

    public HelmetModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }

    @Override
    public void setVisibility(EquipmentSlot slot) {
        this.setVisible(false);
        if (slot == EquipmentSlot.HEAD) {
            head.visible = true;
        }
    }

}
