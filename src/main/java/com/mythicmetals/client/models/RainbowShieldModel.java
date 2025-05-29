package com.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class RainbowShieldModel {

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        root.addChild("stormyx_shield", ModelPartBuilder.create()
            .cuboid("shield_cube", 0, 0, 0, 6, 12, 6, new Dilation(8)), ModelTransform.pivot(-2.0f, 12f, -2.0f));
        return TexturedModelData.of(data, 16, 16);
    }
}
