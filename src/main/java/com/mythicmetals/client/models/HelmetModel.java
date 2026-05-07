package com.mythicmetals.client.models;

import com.mythicmetals.api.v2.client.CustomArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;

public class HelmetModel extends HumanoidModel<HumanoidRenderState> implements CustomArmorModel {
    final EquipmentSlot slot;

    public HelmetModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }

    @Override
    public void setVisibility(EquipmentSlot slot) {
        this.setAllVisible(false);
        if (slot == EquipmentSlot.HEAD) {
            head.visible = true;
        }
    }

}
