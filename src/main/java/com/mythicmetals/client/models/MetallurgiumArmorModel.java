package com.mythicmetals.client.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;

public class MetallurgiumArmorModel extends BipedEntityModel<BipedEntityRenderState> {

    public MetallurgiumArmorModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        var head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        head.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        head.addChild("head", ModelPartBuilder.create()
                .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F))
                .uv(24, 0).cuboid(4.75F, -12.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 0).mirrored().cuboid(-6.75F, -12.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .mirrored(false),
            ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
        head.addChild("details", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)), ModelTransform.NONE);

        return modelData;
    }


}