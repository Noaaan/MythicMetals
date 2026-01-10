package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RainbowShieldModel {

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition data = new MeshDefinition();
        var root = data.getRoot();

        root.addOrReplaceChild("stormyx_shield", CubeListBuilder.create()
            .addBox("shield_cube", 0, 0, 0, 6, 12, 6, new CubeDeformation(8)), PartPose.offset(-2.0f, 12f, -2.0f));
        return LayerDefinition.create(data, 16, 16);
    }
}
