package com.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class AdamantiteArmorModel {

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
                .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F))
                .uv(0, 16).cuboid(4.5F, -11.0f, -0.25F, 1.0F, 6.0F, 2.0F, Dilation.NONE)
                .uv(0, 16).cuboid(-5.5F, -11.0f, -0.25F, 1.0F, 6.0F, 2.0F, Dilation.NONE),
            ModelTransform.NONE
        );

        head.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        head.addChild("head_outer",
            ModelPartBuilder.create()
                .uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)), ModelTransform.NONE);

        return data;
    }
}
