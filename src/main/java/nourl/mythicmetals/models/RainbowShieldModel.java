package nourl.mythicmetals.models;

import net.minecraft.client.model.*;

public class RainbowShieldModel {

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        var root = data.getRoot();

        root.addChild("stormyx_shield", ModelPartBuilder.create()
                .cuboid("shield_cube", -8, -3, -8, 16, 16, 16, new Dilation(32)), ModelTransform.NONE);
        return TexturedModelData.of(data, 16, 16);
    }
}
