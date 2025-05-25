package com.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class RainbowShieldModel {

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        root.addChild("stormyx_shield", ModelPartBuilder.create()
            .cuboid("shield_cube", 0, 0, 0, 3, 4, 3, new Dilation(16)), ModelTransform.pivot(-0.5f, 13f, -0.5f));
        return TexturedModelData.of(data, 16, 16);
    }
}
