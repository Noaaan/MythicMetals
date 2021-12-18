package nourl.mythicmetals.armor;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class HallowedArmorModel extends BipedEntityModel<LivingEntity> {

    private final ModelPart ROOT;

    private final ModelPart HEAD;
    private final ModelPart RIGHTWING;
    private final ModelPart LEFTWING;
    private final ModelPart BODY;
    private final ModelPart RIGHTARM;
    private final ModelPart LEFTARM;
    private final ModelPart RIGHTLEG;
    private final ModelPart LEFTLEG;
    private final ModelPart LBOOT;
    private final ModelPart RBOOT;

    public HallowedArmorModel(ModelPart root) {
        super(root);
        this.ROOT = root;
        this.HEAD = root.getChild("head");
        this.RIGHTWING = root.getChild("rightWing");
        this.LEFTWING = root.getChild("leftWing");
        this.BODY = root.getChild("body");
        this.RIGHTARM = root.getChild("right_arm");
        this.LEFTARM = root.getChild("left_arm");
        this.RIGHTLEG = root.getChild("right_leg");
        this.LEFTLEG = root.getChild("left_leg");
        this.LBOOT = root.getChild("lboot");
        this.RBOOT = root.getChild("rboot");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();
        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);

        ModelPartData HEAD = root.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .mirrored(false)
                        .cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.75F))
                        .uv(32, 0)
                        .mirrored(false)
                        .cuboid(-1.0F, -7.3F, -6.2F, 2.0F, 2.0F, 1.0F, new Dilation(0.2F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData RightWing = HEAD.addChild(
                "rightWing",
                ModelPartBuilder.create()
                        .uv(16, 32)
                        .mirrored(false)
                        .cuboid(-0.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(-5.5269F, -6.9135F, -0.4809F, 0.1745F, -0.2182F, 0.0F)
                );

        ModelPartData LeftWing = HEAD.addChild(
                "leftWing",
                ModelPartBuilder.create()
                        .uv(26, 39)
                        .mirrored(false)
                        .cuboid(-0.5F, -1.5F, -3.0F, 1.0F, 3.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(5.5269F, -6.9135F, 0.5191F, 0.1745F, 0.2182F, 0.0F)
                );

        ModelPartData BODY = root.addChild(
                "body",
                ModelPartBuilder.create()
                        .uv(16, 16)
                        .mirrored(false)
                        .cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.5F))
                        .uv(32, 0)
                        .mirrored(false)
                        .cuboid(-1.0F, 4.0F, -3.1F, 2.0F, 2.0F, 1.0F, new Dilation(0.2F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData RIGHTARM = root.addChild(
                "right_arm",
                ModelPartBuilder.create()
                        .uv(40, 32)
                        .mirrored(false)
                        .cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.52F)),
                ModelTransform.of(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData LEFTARM = root.addChild(
                "left_arm",
                ModelPartBuilder.create()
                        .uv(40, 16)
                        .mirrored(false)
                        .cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.52F)),
                ModelTransform.of(5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData RIGHTLEG = root.addChild(
                "right_leg",
                ModelPartBuilder.create()
                        .uv(0, 48)
                        .mirrored(true)
                        .cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)),
                ModelTransform.of(-1.9F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData LEFTLEG = root.addChild(
                "left_leg",
                ModelPartBuilder.create()
                        .uv(16, 48)
                        .mirrored(false)
                        .cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)),
                ModelTransform.of(1.9F, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData LBOOT = root.addChild(
                "lboot",
                ModelPartBuilder.create()
                        .uv(0, 16)
                        .mirrored(false)
                        .cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.52F)),
                ModelTransform.of(1.9F, 18.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        ModelPartData RBOOT = root.addChild(
                "rboot",
                ModelPartBuilder.create()
                        .uv(0, 32)
                        .mirrored(false)
                        .cuboid(-2.0F, -6.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.51F)),
                ModelTransform.of(-1.9F, 18.0F, 0.0F, 0.0F, 0.0F, 0.0F)
                );

        return TexturedModelData.of(data, 64, 64);
    }
}
