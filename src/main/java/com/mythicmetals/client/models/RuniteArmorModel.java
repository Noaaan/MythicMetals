package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class RuniteArmorModel {

    public static MeshDefinition getModelData() {
        MeshDefinition data = new MeshDefinition();
        var root = data.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);

        var head = root.addOrReplaceChild("head",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        head.addOrReplaceChild(
            "faceguard",
            CubeListBuilder.create()
                .texOffs(0, 21)
                .addBox(-5.0F, -3.0F, -6.0F, 10.0F, 4.0F, 7.0F, CubeDeformation.NONE),
            PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.3491F, 0.0F, 0.0F)
        );
        root.bake(32, 32);

        return data;
    }
}
