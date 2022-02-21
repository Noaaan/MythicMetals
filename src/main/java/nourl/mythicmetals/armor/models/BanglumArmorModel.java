package nourl.mythicmetals.armor.models;

import net.minecraft.client.model.*;

public class BanglumArmorModel {
    private final ModelPart head;
    private final ModelPart right_horn;
    private final ModelPart left_horn;

    public BanglumArmorModel(ModelPart root) {
        this.head = root.getChild("head");
        this.right_horn = root.getChild("right_horn");
        this.left_horn = root.getChild("left_horn");
    }

    public static ModelData getModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();
        var dil = new Dilation(0.01F);

        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.NONE);

        var head = root.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)),
                ModelTransform.NONE
        );

        head.addChild(
                "right_horn",
                ModelPartBuilder.create()
                        .uv(8, 16)
                        .cuboid(2.0F, -12.0F, 0.0F, 1.0F, 3.0F, 1.0F, dil),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
        );

        head.addChild(
                "left_horn",
                ModelPartBuilder.create()
                        .uv(8, 16)
                        .cuboid(-3.0F, -12.0F, 0.0F, 1.0F, 3.0F, 1.0F, dil),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
        );

        return data;
    }
}
