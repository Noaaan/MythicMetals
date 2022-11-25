package nourl.mythicmetals.client.models;

import net.minecraft.client.model.*;

public class RuniteArmorModel {
    public final ModelPart head;
    public final ModelPart faceguard;

    public RuniteArmorModel(ModelPart root) {
        this.head = root.getChild("head");
        this.faceguard = root.getChild("faceguard");
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
                "faceguard",
                ModelPartBuilder.create()
                        .uv(0, 21)
                        .cuboid(-5.0F, -3.0F, -6.0F, 10.0F, 4.0F, 7.0F, Dilation.NONE),
                ModelTransform.of(0.0F, -5.0F, 0.0F, -0.3491F, 0.0F, 0.0F)
        );
        root.createPart(32, 32);

        return data;
    }
}
