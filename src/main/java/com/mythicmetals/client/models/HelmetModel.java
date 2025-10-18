package com.mythicmetals.client.models;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;

public class HelmetModel extends BipedEntityModel<BipedEntityRenderState> {
    final EquipmentSlot slot;

    public HelmetModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }
//
//    @Override
//    public void render(MatrixStack ms, VertexConsumer buffer, int light, int overlay, int color) {
//        renderArmorPart(slot);
//        super.render(ms, buffer, light, overlay, color);
//    }

    private void renderArmorPart(EquipmentSlot slot) {
        setVisible(false);
        if (slot == EquipmentSlot.HEAD) {
            head.visible = true;
        }
    }

}
