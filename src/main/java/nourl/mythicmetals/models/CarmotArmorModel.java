package nourl.mythicmetals.models;

import net.minecraft.client.model.*;

public class CarmotArmorModel {
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart body;
    private final ModelPart head;

    public CarmotArmorModel(ModelPart root) {
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.body = root.getChild("body");
        this.head = root.getChild("head");

    }

    public static ModelData getModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_boot", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_boot", ModelPartBuilder.create(), ModelTransform.NONE);

        var left_leg = root.addChild(
                "left_leg",
                ModelPartBuilder.create()
                        .uv(0, 35)
                        .cuboid(-2.0F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F)),
                ModelTransform.NONE
        );

        var right_leg = root.addChild(
                "right_leg",
                ModelPartBuilder.create()
                        .uv(0, 35)
                        .mirrored(true)
                        .cuboid(-2.5F, -1.5F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.5F)),
                ModelTransform.NONE
        );

        var right_arm = root.addChild(
                "right_arm",
                ModelPartBuilder.create()
                        .uv(40, 16)
                        .cuboid(-4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)),
                ModelTransform.NONE
        );

        var left_arm = root.addChild(
                "left_arm",
                ModelPartBuilder.create()
                        .uv(40, 16)
                        .mirrored(true)
                        .cuboid(0.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(1.0F)),
                ModelTransform.NONE
        );

        var body = root.addChild(
                "body",
                ModelPartBuilder.create()
                        .uv(16, 16)
                        .cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(1.01F)),
                ModelTransform.NONE
        );

        var head = root.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1.0F)),
                ModelTransform.NONE
        );

        return data;
    }



}