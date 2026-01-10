package com.mythicmetals.client.models;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class PalladiumArmorModel {

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
                .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F))
                .texOffs(30, 59 - 32).addBox(-7.5F, -3.5F - 3.5f, -0.25F, 15.0F, 3.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(56, 52 - 32).addBox(5.5F, -7.5F - 3.5f, -0.25F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(44, 49 - 32).addBox(-7.5F, -7.5F - 3.5f, -0.25F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE),
            PartPose.ZERO
        );

        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        head.addOrReplaceChild("head_outer",
            CubeListBuilder.create()
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.ZERO);

        return data;
    }
}
