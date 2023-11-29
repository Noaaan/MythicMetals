package nourl.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class TidesingerArmorModel {
    //public final ModelPart head;
    //public final ModelPart body;
    //public final ModelPart rightArm;
    //public final ModelPart leftArm;
    //public final ModelPart rightLeg;
    //public final ModelPart leftLeg;

    public TidesingerArmorModel(ModelPart root) {
        //this.head = root.getChild("head");
        //this.body = root.getChild("body");
        //this.rightArm = root.getChild("right_arm");
        //this.leftArm = root.getChild("left_arm");
        //this.rightLeg = root.getChild("right_leg");
        //this.leftLeg = root.getChild("left_leg");
    }

    @SuppressWarnings("unused")
    public static ModelData getModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);

        ModelPartData head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);

        ModelPartData head_armor = head.addChild("head_armor", ModelPartBuilder.create().uv(0, 26).cuboid(-4.0F, -9.0F, -4.0F, 9.0F, 9.0F, 9.0F)
                .uv(0, 118).cuboid(-7.0F, -15.0F, -4.25F, 15.0F, 10.0F, 0.0F), ModelTransform.pivot(-0.5F, 0.5F, -0.5F));

        var helmet_fin_front_right = head_armor.addChild("helmet_fin_front_right", ModelPartBuilder.create().uv(0, 76).cuboid(0.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F), ModelTransform.of(5.0F, -6.0F, -4.0F, 0.0F, -0.5236F, 0.0F));

        var helmet_fin_front_left = head_armor.addChild("helmet_fin_front_left", ModelPartBuilder.create().uv(78, 32).cuboid(-8.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F), ModelTransform.of(-4.0F, -6.0F, -4.0F, 0.0F, 0.5236F, 0.0F));

        var helmet_fin_back_right = head_armor.addChild("helmet_fin_back_right", ModelPartBuilder.create().uv(66, 39).cuboid(0.0F, -3.0F, 0.0F, 10.0F, 6.0F, 0.0F), ModelTransform.of(5.0F, -6.0F, -4.0F, 0.2922F, -1.0215F, -0.339F));

        var helmet_fin_back_left = head_armor.addChild("helmet_fin_back_left", ModelPartBuilder.create().uv(71, 10).cuboid(-10.0F, -3.0F, 0.0F, 10.0F, 6.0F, 0.0F), ModelTransform.of(-4.0F, -6.0F, -4.0F, 0.2922F, 1.0215F, 0.339F));

        ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);

        var body_armor = body.addChild("body_armor", ModelPartBuilder.create().uv(36, 21).cuboid(-4.5F, -0.5F, -2.5F, 9.0F, 13.0F, 5.0F), ModelTransform.pivot(0.0F, -0.5F, 0.0F));

        var body_crest = body.addChild("body_crest", ModelPartBuilder.create().uv(61, 46).cuboid(-3.0F, -0.5F, -3.0F, 6.0F, 7.0F, 6.0F), ModelTransform.pivot(0.0F, -0.5F, 0.0F));
        var body_belt = body.addChild("body_belt", ModelPartBuilder.create().uv(39, 7).cuboid(-5.0F, 10.0F, -3.0F, 10.0F, 3.0F, 6.0F), ModelTransform.pivot(0.0F, -0.5F, 0.0F));
        var body_buckle = body.addChild("body_buckle", ModelPartBuilder.create().uv(43, 122).cuboid(-4.5F, 8.25F, -3.25F, 9.0F, 6.0F, 0.0F), ModelTransform.pivot(0.0F, -0.5F, 0.0F));

        var body_armor_right_plate = body_armor.addChild("body_armor_right_plate", ModelPartBuilder.create().uv(0, 0).cuboid(-0.25F, -0.25F, -2.5F, 1.0F, 6.0F, 5.0F), ModelTransform.of(4.5F, 12.5F, 0.0F, 0.0F, 0.0F, -0.1745F));
        var body_armor_left_plate = body_armor.addChild("body_armor_left_plate", ModelPartBuilder.create().uv(0, 11).cuboid(-0.75F, -0.25F, -2.5F, 1.0F, 6.0F, 5.0F), ModelTransform.of(-4.5F, 12.5F, 0.0F, 0.0F, 0.0F, 0.1745F));

        ModelPartData right_arm = root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 2.0F, 0.0F));

        var armorRightArm = right_arm.addChild("armorRightArm", ModelPartBuilder.create().uv(41, 52).cuboid(-4.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F)
                .uv(27, 26).cuboid(-8.5F, 1.5F, -0.5F, 4.0F, 9.0F, 0.0F)
                .uv(0, 63).cuboid(-5.0F, -4.0F, -3.0F, 6.0F, 7.0F, 6.0F), ModelTransform.NONE);

        var right_arm_fin = armorRightArm.addChild("right_arm_fin", ModelPartBuilder.create().uv(74, 72).cuboid(-4.5F, -6.5F, 0.0F, 5.0F, 13.0F, 0.0F), ModelTransform.of(-5.0F, 4.0F, 0.5F, 0.0F, 0.2618F, 0.0F));

        ModelPartData left_arm = root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 2.0F, 0.0F));

        var armorLeftArm = left_arm.addChild("armorLeftArm", ModelPartBuilder.create().uv(21, 51).cuboid(-0.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F)
                .uv(0, 22).cuboid(4.5F, 1.5F, -0.5F, 4.0F, 9.0F, 0.0F)
                .uv(61, 59).cuboid(-1.0F, -4.0F, -3.0F, 6.0F, 7.0F, 6.0F), ModelTransform.NONE);

        var left_arm_fin = armorLeftArm.addChild("left_arm_fin", ModelPartBuilder.create().uv(64, 72).cuboid(-0.5F, -6.5F, 0.0F, 5.0F, 13.0F, 0.0F), ModelTransform.of(5.0F, 4.0F, 0.5F, 0.0F, -0.2618F, 0.0F));

        ModelPartData left_leg = root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        var left_leg_armor = left_leg.addChild("left_leg_armor", ModelPartBuilder.create().uv(24, 69).cuboid(1.5F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F), ModelTransform.pivot(-4.0f, 0f, 0f));

        var left_boot = left_leg.addChild("left_boot", ModelPartBuilder.create().uv(44, 70).cuboid(1.5F, 9.0F, -2.5F, 5.0F, 3.0F, 5.0F), ModelTransform.pivot(-4.0f, 0f, 0f));

        var left_boot_frill = left_boot.addChild("left_boot_frill", ModelPartBuilder.create().uv(16, 76).cuboid(0.0F, -2.0F, 0.0F, 4.0F, 7.0F, 0.0F), ModelTransform.of(6.5F, 6.5F, 0.0F, 0.0F, -0.3491F, 0.0F));

        ModelPartData right_leg = root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 12.0F, 0.0F));

        var right_leg_armor = right_leg.addChild("right_leg_armor", ModelPartBuilder.create().uv(64, 16).cuboid(-6.5F, -2.0F, -2.5F, 5.0F, 11.0F, 5.0F), ModelTransform.pivot(4.0f, 0f, 0f));

        var right_boot = right_leg.addChild("right_boot", ModelPartBuilder.create().uv(70, 2).cuboid(-6.5F, 9.0F, -2.5F, 5.0F, 3.0F, 5.0F), ModelTransform.pivot(4.0f, 0f, 0f));

        var right_boot_frill = right_boot.addChild("right_boot_frill", ModelPartBuilder.create().uv(26, 44).cuboid(-4.0F, -2.0F, 0.0F, 4.0F, 7.0F, 0.0F), ModelTransform.of(-6.5F, 6.5F, 0.0F, 0.0F, 0.3491F, 0.0F));
        return data;
    }
}