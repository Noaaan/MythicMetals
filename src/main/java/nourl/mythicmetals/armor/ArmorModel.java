package nourl.mythicmetals.armor;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;

public class ArmorModel extends BipedEntityModel<LivingEntity> {
    final EquipmentSlot slot;

    public ArmorModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
    }

    @Override
    public void setAngles(LivingEntity entity, float f, float g, float h, float i, float j) {
        if (!(entity instanceof ArmorStandEntity stand)) {
            super.setAngles(entity, f, g, h, i, j);
            return;
        }
        this.head.pivotX = ((float) Math.PI / 180F) * stand.getHeadRotation().getRoll();
        this.head.pivotY = ((float) Math.PI / 180F) * stand.getHeadRotation().getPitch();
        this.head.pivotZ = ((float) Math.PI / 180F) * stand.getHeadRotation().getYaw();
        this.head.setPivot(0.0F, 1.0F, 0.0F);
        this.body.pivotX = ((float) Math.PI / 180F) * stand.getBodyRotation().getRoll();
        this.body.pivotY = ((float) Math.PI / 180F) * stand.getBodyRotation().getPitch();
        this.body.pivotZ = ((float) Math.PI / 180F) * stand.getBodyRotation().getYaw();
        this.leftArm.pivotX = ((float) Math.PI / 180F) * stand.getLeftArmRotation().getRoll();
        this.leftArm.pivotY = ((float) Math.PI / 180F) * stand.getLeftArmRotation().getPitch();
        this.leftArm.pivotZ = ((float) Math.PI / 180F) * stand.getLeftArmRotation().getYaw();
        this.rightArm.pivotX = ((float) Math.PI / 180F) * stand.getRightArmRotation().getRoll();
        this.rightArm.pivotY = ((float) Math.PI / 180F) * stand.getRightArmRotation().getPitch();
        this.rightArm.pivotZ = ((float) Math.PI / 180F) * stand.getRightArmRotation().getYaw();
        this.leftLeg.pivotX = ((float) Math.PI / 180F) * stand.getLeftLegRotation().getRoll();
        this.leftLeg.pivotY = ((float) Math.PI / 180F) * stand.getLeftLegRotation().getPitch();
        this.leftLeg.pivotZ = ((float) Math.PI / 180F) * stand.getLeftLegRotation().getYaw();
        this.leftLeg.setPivot(1.9F, 11.0F, 0.0F);
        this.rightLeg.pivotX = ((float) Math.PI / 180F) * stand.getRightLegRotation().getRoll();
        this.rightLeg.pivotY = ((float) Math.PI / 180F) * stand.getRightLegRotation().getPitch();
        this.rightLeg.pivotZ = ((float) Math.PI / 180F) * stand.getRightLegRotation().getYaw();
        this.rightLeg.setPivot(-1.9F, 11.0F, 0.0F);
        this.hat.copyTransform(this.head);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        setPartVisibility(slot);
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    private void setPartVisibility(EquipmentSlot slot) {
        setVisible(false);
        switch (slot) {
            case HEAD -> head.visible = true;
            case CHEST -> {
                body.visible = true;
                rightArm.visible = true;
                leftArm.visible = true;
            }
            case LEGS -> {
                rightLeg.visible = true;
                leftLeg.visible = true;
            }
        }
    }


}
