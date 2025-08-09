package com.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class MetallurgiumArmorModel {

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();
        var head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        head.addChild("head", ModelPartBuilder.create()
                .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F))
                .uv(24, 0).cuboid(4.75F, -12.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 0).mirrored().cuboid(-6.75F, -12.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .mirrored(false),
            ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        var hat = root.addChild("hat", ModelPartBuilder.create().uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)), ModelTransform.NONE);
        var body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        var body_armor = body.addChild("body_armor", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.752F)), ModelTransform.NONE);
        var waist = body.addChild("waist", ModelPartBuilder.create().uv(16, 48).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.7F)), ModelTransform.NONE);
        var left_arm = root.addChild("left_arm", ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.75F)).mirrored(false), ModelTransform.NONE);
        var right_arm = root.addChild("right_arm", ModelPartBuilder.create().uv(40, 16).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.75F)), ModelTransform.NONE);
        var left_leg = root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        var right_leg = root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        var left_leg_armor = left_leg.addChild("left_leg_armor", ModelPartBuilder.create().uv(0, 48).mirrored().cuboid(-2.0F, -0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.601F)).mirrored(false), ModelTransform.NONE);
        var left_boot = left_leg.addChild("left_boot", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, -0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.75F)).mirrored(false), ModelTransform.NONE);
        var right_leg_armor = right_leg.addChild("right_leg_armor", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, -0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.6F)), ModelTransform.NONE);
        var right_boot = right_leg.addChild("right_boot", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, -0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.751F)), ModelTransform.NONE);

        return modelData;
    }


}