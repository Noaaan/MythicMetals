package com.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class HallowedArmorModel {

    public static ModelData getModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        var head = root.addChild("head",
            ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F)),
            ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        head.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);

        head.addChild(
            "wing_r",
            ModelPartBuilder.create()
                .uv(43, 2)
                .cuboid(0.0F, -2.5F, -0.5F, 0.0F, 4.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(-5.0F, -6.5F, 0.5F, 0.4363F, -0.2618F, 0.0F)
        );

        head.addChild(
            "wing_l",
            ModelPartBuilder.create()
                .uv(43, 2)
                .cuboid(0.0F, -2.5F, -0.5F, 0.0F, 4.0F, 6.0F, new Dilation(0.0F)),
            ModelTransform.of(5.0F, -6.5F, 0.5F, 0.4363F, 0.2618F, 0.0F)
        );

        head.addChild(
            "faceguard",
            ModelPartBuilder.create()
                .uv(16, 21)
                .cuboid(-5.0F, -3.0F, -6.0F, 10.0F, 4.0F, 7.0F, new Dilation(0.0F)),
            ModelTransform.of(0.0F, -5.0F, 0.0F, -0.3491F, 0.0F, 0.0F)
        );
        root.createPart(64, 32);
        return data;
    }
}
