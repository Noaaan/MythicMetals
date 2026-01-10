package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class HallowedArmorModel {

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
            "wing_r",
            CubeListBuilder.create()
                .texOffs(43, 2)
                .addBox(0.0F, -2.5F, -0.5F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-5.0F, -6.5F, 0.5F, 0.4363F, -0.2618F, 0.0F)
        );

        head.addOrReplaceChild(
            "wing_l",
            CubeListBuilder.create()
                .texOffs(43, 2)
                .addBox(0.0F, -2.5F, -0.5F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(5.0F, -6.5F, 0.5F, 0.4363F, 0.2618F, 0.0F)
        );

        head.addOrReplaceChild(
            "faceguard",
            CubeListBuilder.create()
                .texOffs(16, 21)
                .addBox(-5.0F, -3.0F, -6.0F, 10.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.3491F, 0.0F, 0.0F)
        );
        root.bake(64, 32);
        return data;
    }
}
