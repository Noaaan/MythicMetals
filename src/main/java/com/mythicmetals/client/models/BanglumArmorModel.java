package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class BanglumArmorModel {

    public static MeshDefinition getModelData() {

        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();
        var dil = new CubeDeformation(0.01F);

        root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);

        var head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)),
            PartPose.ZERO
        );
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        head.addOrReplaceChild(
            "right_horn",
            CubeListBuilder.create()
                .texOffs(8, 16)
                .addBox(2.0F, -12.0F, 0.0F, 1.0F, 3.0F, 1.0F, dil),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
        );

        head.addOrReplaceChild(
            "left_horn",
            CubeListBuilder.create()
                .texOffs(8, 16)
                .addBox(-3.0F, -12.0F, 0.0F, 1.0F, 3.0F, 1.0F, dil),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
        );

        return data;
    }
}
