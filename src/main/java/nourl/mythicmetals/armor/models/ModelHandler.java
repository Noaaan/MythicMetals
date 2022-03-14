package nourl.mythicmetals.armor.models;

import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.function.BiConsumer;

public class ModelHandler {
    public static final EntityModelLayer BANGLUM = RegistryHelper.model("banglum_armor");
    public static final EntityModelLayer HALLOWED = RegistryHelper.model("hallowed_armor");
    public static final EntityModelLayer METALLURGIUM = RegistryHelper.model("metallurgium_armor");

    public static void init(BiConsumer<EntityModelLayer, TexturedModelData> consumer) {
        consumer.accept(BANGLUM, TexturedModelData.of(BanglumArmorModel.getModelData(), 32, 32));
        consumer.accept(HALLOWED, TexturedModelData.of(HallowedArmorModel.getModelData(), 64, 64));
        consumer.accept(METALLURGIUM, TexturedModelData.of(MetallurgiumArmorModel.getModelData(), 32, 16));
    }
}
