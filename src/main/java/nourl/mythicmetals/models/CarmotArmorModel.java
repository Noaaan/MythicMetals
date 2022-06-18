package nourl.mythicmetals.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;

public class CarmotArmorModel extends ArmorModel {
    private final ModelPart robe;
    private final ModelPart robe_left;
    private final ModelPart robe_back;
    private final ModelPart robe_right;
    private final ModelPart left_leg;
    private final ModelPart right_leg;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart body;
    private final ModelPart head;

    public CarmotArmorModel(ModelPart root, EquipmentSlot slot) {
        super(root, slot);
        this.robe = root.getChild("robe");
        this.robe_left = root.getChild("robe_left");
        this.robe_back = root.getChild("robe_back");
        this.robe_right = root.getChild("robe_right");
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

        var robe_back = body.addChild(
                "robe_back",
                ModelPartBuilder.create()
                        .uv(19, 40)
                        .cuboid(-5.0F, 0.0F, 3.0F, 10.0F, 10.0F, 1.0F),
                ModelTransform.of(0.0F, 11.0F, 0.0F, 0.0873F, 0.0F, 0.0F)
        );

        var robe_left = body.addChild(
                "robe_left",
                ModelPartBuilder.create()
                        .uv(41, 35)
                        .cuboid(-4.0F, -14.0F, -3.0F, 1.0F, 10.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(9.5F, 24.0F, 0.0F, 0.0F, 0.0F, -0.0873F)
        );

        var robe_right = body.addChild(
                "robe_right",
                ModelPartBuilder.create()
                        .uv(41, 35)
                        .cuboid(-6.0F, -13.0F, -3.0F, 1.0F, 10.0F, 6.0F),
                ModelTransform.of(-0.5F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0873F)
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

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

    }

    public void renderFlap(MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        this.robe_back.render(matrices, vertices, light, overlay);
    }


}