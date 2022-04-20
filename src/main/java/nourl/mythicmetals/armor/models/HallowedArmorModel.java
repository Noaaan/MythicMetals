package nourl.mythicmetals.armor.models;

import net.minecraft.client.model.*;

public class HallowedArmorModel {
    public final ModelPart head;
    public final ModelPart faceguard;
    public final ModelPart wing_r;
    public final ModelPart wing_l;

    public HallowedArmorModel(ModelPart root) {
        this.head = root.getChild("head");
        this.faceguard = root.getChild("faceguard");
        this.wing_r = root.getChild("wing_r");
        this.wing_l = root.getChild("wing_l");
    }

    public static ModelData getModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        var head = root.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        head.addChild(
                "wing_r",
                ModelPartBuilder.create()
                        .uv(43, 50)
                        .cuboid(0.0F, -2.5F, -0.5F, 0.0F, 4.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(-5.0F, -6.5F, 0.5F, 0.4363F, -0.2618F, 0.0F)
        );

        head.addChild(
                "wing_l",
                ModelPartBuilder.create()
                        .uv(43, 50)
                        .cuboid(0.0F, -2.5F, -0.5F, 0.0F, 4.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(5.0F, -6.5F, 0.5F, 0.4363F, 0.2618F, 0.0F)
        );

        head.addChild(
                "faceguard",
                ModelPartBuilder.create()
                        .uv(16, 53)
                        .cuboid(-5.0F, -3.0F, -6.0F, 10.0F, 4.0F, 7.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -5.0F, 0.0F, -0.3491F, 0.0F, 0.0F)
        );
        root.createPart(64, 64);
        return data;
    }
}
