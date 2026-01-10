package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class TidesingerArmorModel {

    @SuppressWarnings("unused")
    public static MeshDefinition getModelData() {
        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();


        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition head_armor = head.addOrReplaceChild("head_armor", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -9.0F, -4.0F, 9.0F, 9.0F, 9.0F)
            .texOffs(0, 118).addBox(-7.0F, -15.0F, -4.25F, 15.0F, 10.0F, 0.0F), PartPose.offset(-0.5F, 0.5F, -0.5F));

        var helmet_fin_front_right = head_armor.addOrReplaceChild("helmet_fin_front_right", CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(5.0F, -6.0F, -4.0F, 0.0F, -0.5236F, 0.0F));

        var helmet_fin_front_left = head_armor.addOrReplaceChild("helmet_fin_front_left", CubeListBuilder.create().texOffs(78, 32).addBox(-8.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-4.0F, -6.0F, -4.0F, 0.0F, 0.5236F, 0.0F));

        var helmet_fin_back_right = head_armor.addOrReplaceChild("helmet_fin_back_right", CubeListBuilder.create().texOffs(66, 39).addBox(0.0F, -3.0F, 0.0F, 10.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(5.0F, -6.0F, -4.0F, 0.2922F, -1.0215F, -0.339F));

        var helmet_fin_back_left = head_armor.addOrReplaceChild("helmet_fin_back_left", CubeListBuilder.create().texOffs(71, 10).addBox(-10.0F, -3.0F, 0.0F, 10.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(-4.0F, -6.0F, -4.0F, 0.2922F, 1.0215F, 0.339F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);

        var body_armor = body.addOrReplaceChild("body_armor", CubeListBuilder.create().texOffs(36, 21).addBox(-4.5F, -0.5F, -2.5F, 9.0F, 13.0F, 5.0F), PartPose.offset(0.0F, -0.5F, 0.0F));

        var body_crest = body.addOrReplaceChild("body_crest", CubeListBuilder.create().texOffs(61, 46).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 7.0F, 6.0F), PartPose.offset(0.0F, -0.5F, 0.0F));
        var body_belt = body.addOrReplaceChild("body_belt", CubeListBuilder.create().texOffs(39, 7).addBox(-5.0F, 10.0F, -3.0F, 10.0F, 3.0F, 6.0F), PartPose.offset(0.0F, -0.5F, 0.0F));
        var body_buckle = body.addOrReplaceChild("body_buckle", CubeListBuilder.create().texOffs(43, 122).addBox(-4.5F, 8.25F, -3.25F, 9.0F, 6.0F, 0.0F), PartPose.offset(0.0F, -0.5F, 0.0F));

        var body_armor_right_plate = body_armor.addOrReplaceChild("body_armor_right_plate", CubeListBuilder.create().texOffs(0, 0).addBox(-0.25F, -0.25F, -2.5F, 1.0F, 6.0F, 5.0F), PartPose.offsetAndRotation(4.5F, 12.5F, 0.0F, 0.0F, 0.0F, -0.1745F));
        var body_armor_left_plate = body_armor.addOrReplaceChild("body_armor_left_plate", CubeListBuilder.create().texOffs(0, 11).addBox(-0.75F, -0.25F, -2.5F, 1.0F, 6.0F, 5.0F), PartPose.offsetAndRotation(-4.5F, 12.5F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-4.0F, 2.0F, 0.0F));

        var armor_right_arm = right_arm.addOrReplaceChild("armor_right_arm", CubeListBuilder.create().texOffs(41, 52).addBox(-4.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F)
            .texOffs(27, 26).addBox(-8.5F, 1.5F, -0.5F, 4.0F, 9.0F, 0.0F)
            .texOffs(0, 63).addBox(-5.0F, -4.0F, -3.0F, 6.0F, 7.0F, 6.0F), PartPose.offset(1.0f, 0.0f, 0.0f));

        var right_arm_fin = armor_right_arm.addOrReplaceChild("right_arm_fin", CubeListBuilder.create().texOffs(74, 72).addBox(-4.5F, -6.5F, 0.0F, 5.0F, 13.0F, 0.0F), PartPose.offsetAndRotation(-5.0F, 4.0F, 0.5F, 0.0F, 0.2618F, 0.0F));

        PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(4.0F, 2.0F, 0.0F));

        var armor_left_arm = left_arm.addOrReplaceChild("armor_left_arm", CubeListBuilder.create().texOffs(21, 51).addBox(-0.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F)
            .texOffs(0, 22).addBox(4.5F, 1.5F, -0.5F, 4.0F, 9.0F, 0.0F)
            .texOffs(61, 59).addBox(-1.0F, -4.0F, -3.0F, 6.0F, 7.0F, 6.0F), PartPose.offset(-1.0f, 0.0f, 0.0f));

        var left_arm_fin = armor_left_arm.addOrReplaceChild("left_arm_fin", CubeListBuilder.create().texOffs(64, 72).addBox(-0.5F, -6.5F, 0.0F, 5.0F, 13.0F, 0.0F), PartPose.offsetAndRotation(5.0F, 4.0F, 0.5F, 0.0F, -0.2618F, 0.0F));

        PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));

        var left_leg_armor = left_leg.addOrReplaceChild("left_leg_armor", CubeListBuilder.create().texOffs(24, 69).addBox(1.5F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F), PartPose.offset(-4.0f, 0f, 0f));

        var left_boot = left_leg.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(44, 70).addBox(1.5F, 9.0F, -2.5F, 5.0F, 3.0F, 5.0F), PartPose.offset(-4.0f, 0f, 0f));

        var left_boot_frill = left_boot.addOrReplaceChild("left_boot_frill", CubeListBuilder.create().texOffs(16, 76).addBox(0.0F, -2.0F, 0.0F, 4.0F, 7.0F, 0.0F), PartPose.offsetAndRotation(6.5F, 6.5F, 0.0F, 0.0F, -0.3491F, 0.0F));

        PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));

        var right_leg_armor = right_leg.addOrReplaceChild("right_leg_armor", CubeListBuilder.create().texOffs(64, 16).addBox(-6.5F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F), PartPose.offset(4.0f, 0f, 0f));

        var right_boot = right_leg.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(70, 2).addBox(-6.5F, 9.0F, -2.5F, 5.0F, 3.0F, 5.0F), PartPose.offset(4.0f, 0f, 0f));

        var right_boot_frill = right_boot.addOrReplaceChild("right_boot_frill", CubeListBuilder.create().texOffs(26, 44).addBox(-4.0F, -2.0F, 0.0F, 4.0F, 7.0F, 0.0F), PartPose.offsetAndRotation(-6.5F, 6.5F, 0.0F, 0.0F, 0.3491F, 0.0F));
        return data;
    }
}