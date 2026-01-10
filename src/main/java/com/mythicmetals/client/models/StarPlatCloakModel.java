package com.mythicmetals.client.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.joml.Quaternionf;

public class StarPlatCloakModel<T extends PlayerRenderState> extends HumanoidModel<T> {

    private final ModelPart starPlatCloak = this.body.getChild("star_plat_cloak");

    public StarPlatCloakModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition data = new MeshDefinition();
        var root = data.getRoot();

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        var body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        body.addOrReplaceChild(
            "star_plat_cloak",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-5.5F, 0.0F, -0.05F, 11.0F, 23.0F, 1.0F),
            PartPose.offsetAndRotation(0.0F, 0.25F, 3.3F, 0.0f, ((float) Math.PI), 0f)
        );

        return LayerDefinition.create(data, 32, 32);
    }

    @Override
    public void setupAnim(T playerEntityRenderState) {
        super.setupAnim(playerEntityRenderState);
        this.starPlatCloak
            .rotateBy(
                new Quaternionf()
                    .rotateY((float) -Math.PI)
                    .rotateX((6.0F + playerEntityRenderState.capeLean / 2.0F + playerEntityRenderState.capeFlap) * (float) (Math.PI / 180.0))
                    .rotateZ(playerEntityRenderState.capeLean2 / 2.0F * (float) (Math.PI / 180.0))
                    .rotateY((180.0F - playerEntityRenderState.capeLean2 / 2.0F) * (float) (Math.PI / 180.0))
            );

    }
}
