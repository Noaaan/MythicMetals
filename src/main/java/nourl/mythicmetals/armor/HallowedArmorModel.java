package nourl.mythicmetals.armor;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;

public class HallowedArmorModel /*extends ArmorModel*/ {
//    private final ModelPart HEAD;
//    private final ModelPart BODY;
//    private final ModelPart RIGHTARM;
//    private final ModelPart LEFTARM;
//    private final ModelPart RIGHTLEG;
//    private final ModelPart LEFTLEG;
//
//
//    public HallowedArmorModel(EquipmentSlot slot) {
//        super(slot);
//
//        textureWidth = 64;
//        textureHeight = 64;
//
//        // Head
//        HEAD = new ModelPart(this);
//        setRotationAngle(HEAD, 0.0F, 0.0F, 0.0F);
//        HEAD.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.75F, false);
//        HEAD.setTextureOffset(32, 0).addCuboid(-1.0F, -7.3F, -5.2F, 2.0F, 2.0F, 1.0F, 0.2F, false);
//
//        // Wings
//        ModelPart rightWing_r1 = new ModelPart(this);
//        setRotationAngle(rightWing_r1, 0.1745F, 0.2182F, 0.0F);
//        rightWing_r1.setTextureOffset(16, 32).addCuboid(5.0F, -6.0F, -2.5F, 1.0F, 3.5F, 6.0F, 0.0F, false);
//
//        ModelPart leftWing_r1 = new ModelPart(this);
//        setRotationAngle(leftWing_r1, 0.1745F, -0.2182F, 0.0F);
//        leftWing_r1.setTextureOffset(26, 39).addCuboid(-6.0F, -6.0F, -2.5F, 1.0F, 3.5F, 6.0F, 0.0F, false);
//
//        HEAD.addChild(rightWing_r1);
//        HEAD.addChild(leftWing_r1);
//
//        // Body
//        BODY = new ModelPart(this);
//        setRotationAngle(BODY,0.0F, 0.0F, 0.0F);
//        BODY.setTextureOffset(16, 16).addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.9F, false);
//        BODY.setTextureOffset(32, 0).addCuboid(-1.0F, 4.0F, -3.6F, 2.0F, 2.0F, 1.0F, 0.4F, false);
//
//        // Arms
//        RIGHTARM = new ModelPart(this);
//        setRotationAngle(RIGHTARM,-5.0F, 2.0F, 0.0F);
//        RIGHTARM.setTextureOffset(40, 32).addCuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.75F, false);
//
//        LEFTARM = new ModelPart(this);
//        setRotationAngle(LEFTARM,5.0F, 2.0F, 0.0F);
//        LEFTARM.setTextureOffset(40, 16).addCuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.75F, false);
//
//        // Legs
//        RIGHTLEG = new ModelPart(this);
//        setRotationAngle(RIGHTLEG, -1.9F, 12.0F, 0.0F);
//        RIGHTLEG.setTextureOffset(0, 48).addCuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.5F, true);
//
//        LEFTLEG = new ModelPart(this);
//        setRotationAngle(LEFTLEG, 1.9F, 12.0F, 0.0F);
//        LEFTLEG.setTextureOffset(16, 48).addCuboid(-2.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.5F, false);
//
//    }
//    @Override
//    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
//
//        head = HEAD;
//        body = BODY;
//        leftArm = LEFTARM;
//        rightArm = RIGHTARM;
//        leftLeg = LEFTLEG;
//        rightLeg = RIGHTLEG;
//
//        head.visible = slot == EquipmentSlot.HEAD;
//        body.visible = slot == EquipmentSlot.CHEST;
//        leftArm.visible = slot == EquipmentSlot.CHEST;
//        rightArm.visible = slot == EquipmentSlot.CHEST;
//        leftLeg.visible = slot == EquipmentSlot.LEGS;
//        rightLeg.visible = slot == EquipmentSlot.LEGS;
//
//        HEAD.render(matrixStack, buffer, packedLight, packedOverlay);
//        BODY.render(matrixStack, buffer, packedLight, packedOverlay);
//        RIGHTARM.render(matrixStack, buffer, packedLight, packedOverlay);
//        LEFTARM.render(matrixStack, buffer, packedLight, packedOverlay);
//        RIGHTLEG.render(matrixStack, buffer, packedLight, packedOverlay);
//        LEFTLEG.render(matrixStack, buffer, packedLight, packedOverlay);
//    }
}
