package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.joml.Quaternionf;

public class StarPlatCloakModel<T extends PlayerEntityRenderState> extends BipedEntityModel<T> {

    private final ModelPart starPlatCloak = this.body.getChild("star_plat_cloak");

    public StarPlatCloakModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        var head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        head.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        var body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        body.addChild(
            "star_plat_cloak",
            ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-5.5F, 0.0F, -0.05F, 11.0F, 23.0F, 1.0F),
            ModelTransform.of(0.0F, 0.25F, 3.3F, 0.0f, ((float) Math.PI), 0f)
        );

        return TexturedModelData.of(data, 32, 32);
    }

    @Override
    public void setAngles(T playerEntityRenderState) {
        super.setAngles(playerEntityRenderState);
        this.starPlatCloak
            .rotate(
                new Quaternionf()
                    .rotateY((float) -Math.PI)
                    .rotateX((6.0F + playerEntityRenderState.field_53537 / 2.0F + playerEntityRenderState.field_53536) * (float) (Math.PI / 180.0))
                    .rotateZ(playerEntityRenderState.field_53538 / 2.0F * (float) (Math.PI / 180.0))
                    .rotateY((180.0F - playerEntityRenderState.field_53538 / 2.0F) * (float) (Math.PI / 180.0))
            );

    }
}
