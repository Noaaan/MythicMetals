package nourl.mythicmetals.armor;

import net.minecraft.client.model.*;

public class HallowedArmorModel {
    public final ModelPart head;
    public final ModelPart hat;
    public final ModelPart body;
    public final ModelPart rightArm;
    public final ModelPart leftArm;

    public HallowedArmorModel(ModelPart root) {
        this.head = root.getChild("head");
        this.hat = root.getChild("hat");
        this.body = root.getChild("body");
        this.rightArm = root.getChild("right_arm");
        this.leftArm = root.getChild("left_arm");
    }

    public static ModelData getModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        ModelPartData head = root.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F))
                        .uv(32, 0)
                        .cuboid(-1.0F, -7.3F, -6.2F, 2.0F, 2.0F, 1.0F, new Dilation(0.2F)),
                ModelTransform.NONE
                );

        head.addChild(
                "right_wing",
                ModelPartBuilder.create()
                        .uv(16, 32)
                        .cuboid(-0.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.01F)),
                ModelTransform.of(-5.5269F, -6.9135F, -0.4809F, 0.1745F, -0.2182F, 0.0F)
                );

        head.addChild(
                "left_wing",
                ModelPartBuilder.create()
                        .uv(26, 39)
                        .cuboid(-0.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.01F)),
                ModelTransform.of(5.5269F, -6.9135F, 0.5191F, 0.1745F, 0.2182F, 0.0F)
                );

        var body = root.addChild(
                "body",
                ModelPartBuilder.create()
                        .uv(16, 16)
                        .cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.8F)),
                ModelTransform.NONE
                );
        body.addChild(
                "helm_crystal",
                ModelPartBuilder.create()
                .uv(32, 0)
                .cuboid(-1.0F, 4.0F, -3.1F, 2.0F, 2.0F, 1.0F, new Dilation(0.35F)),
                ModelTransform.NONE
        );

        var right_arm = root.addChild(
                "right_arm",
                ModelPartBuilder.create()
                        .uv(40, 32)
                        .cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.7F)),
                ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
                );
        var left_arm = root.addChild(
                "left_arm",
                ModelPartBuilder.create()
                        .uv(40, 16)
                        .cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(.7F)),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F)
                );
        root.createPart(64, 64);
        return data;
    }
}
