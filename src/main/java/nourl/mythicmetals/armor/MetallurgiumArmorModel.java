// Made with Blockbench 4.0.5
// Exported for Minecraft version 1.17
// Paste this class into your mod and generate all required imports

package nourl.mythicmetals.armor;

import net.minecraft.client.model.*;

public class MetallurgiumArmorModel {

    private final ModelPart head;

    public MetallurgiumArmorModel(ModelPart root) {
        this.head = root.getChild("head");
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
                "left_horn_2",
                ModelPartBuilder.create()
                        .uv(0, 1)
                        .cuboid(-5.9F, -10.0F, -4.9F, 1.0F, 3.0F, 2.0F, dil)
                        .uv(0, 0)
                        .cuboid(-5.9F, -12.0F, -4.9F, 1.0F, 2.0F, 1.0F, dil),
                ModelTransform.NONE
        );
        head.addChild(
                "left_horn",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(4.9F, -12.0F, -4.9F, 1.0F, 2.0F, 1.0F, dil)
                        .uv(0, 0)
                        .cuboid(4.9F, -10.0F, -4.9F, 1.0F, 3.0F, 2.0F, dil),
                ModelTransform.NONE
        );

        return data;
    }


}