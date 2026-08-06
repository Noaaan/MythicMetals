package com.mythicmetals.client.models;

import com.mythicmetals.api.v2.client.CustomArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;

public class TidesingerBipedModel extends HumanoidModel<HumanoidRenderState> implements CustomArmorModel {
    final EquipmentSlot slot;

    public TidesingerBipedModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }

    @Override
    public void setVisibility(EquipmentSlot slot) {
        this.setAllVisible(false);
        // Note - These are custom parts. By extending this you need these in your model,
        // otherwise you guarantee a crash.
        this.body.getChild("body_belt").visible = false;
        this.body.getChild("body_buckle").visible = false;
        this.body.getChild("body_crest").visible = false;
        this.body.getChild("body_armor").visible = false;

        this.rightLeg.getChild("right_boot").visible = false;
        this.rightLeg.getChild("right_leg_armor").visible = false;

        this.leftLeg.getChild("left_boot").visible = false;
        this.leftLeg.getChild("left_leg_armor").visible = false;


        switch (slot) {
            case HEAD -> head.visible = true;
            case CHEST -> {
                this.body.visible = true;
                this.rightArm.visible = true;
                this.leftArm.visible = true;
                this.body.getChild("body_crest").visible = true;
                this.body.getChild("body_armor").visible = true;
            }
            case LEGS -> {
                this.body.visible = true;
                this.body.getChild("body_belt").visible = true;
                this.body.getChild("body_buckle").visible = true;
                this.rightLeg.visible = true;
                this.leftLeg.visible = true;
                this.leftLeg.getChild("left_leg_armor").visible = true;
                this.rightLeg.getChild("right_leg_armor").visible = true;
            }
            case FEET -> {
                this.rightLeg.visible = true;
                this.leftLeg.visible = true;
                this.leftLeg.getChild("left_boot").visible = true;
                this.rightLeg.getChild("right_boot").visible = true;
            }
        }
    }

    // TODO - Review whether this actually works, or if this just breaks in latest
    public void setAllVisible(boolean bl) {
        this.head.visible = bl;
        this.hat.visible = bl;
        this.body.visible = bl;
        this.rightArm.visible = bl;
        this.leftArm.visible = bl;
        this.rightLeg.visible = bl;
        this.leftLeg.visible = bl;
    }
}
