package nourl.mythicmetals.client.models;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.mixin.EntityModelLayersAccessor;
import java.util.function.BiConsumer;

public class MythicModelHandler {
    public static final EntityModelLayer BANGLUM = model("banglum_armor");
    public static final EntityModelLayer CARMOT_SWIRL = model("carmot_swirl");
    public static final EntityModelLayer HALLOWED_ARMOR = model("hallowed_armor");
    public static final EntityModelLayer METALLURGIUM = model("metallurgium_armor");
    public static final EntityModelLayer RUNITE = model("runite_armor");
    public static final EntityModelLayer TIDESINGER = model("tidesinger");
    public static final EntityModelLayer BANGLUM_TNT_MINECART = model("banglum_tnt_minecart");
    public static final Identifier HALLOWED_CAPE = RegistryHelper.id("textures/models/hallowed_cape.png");
    public static final Identifier STAR_PLATINUM_CLOAK = RegistryHelper.id("textures/models/star_platinum_cloak.png");

    public static void init(BiConsumer<EntityModelLayer, TexturedModelData> consumer) {
        consumer.accept(BANGLUM_TNT_MINECART, MinecartEntityModel.getTexturedModelData());
        consumer.accept(BANGLUM, TexturedModelData.of(BanglumArmorModel.getModelData(), 64, 32));
        consumer.accept(CARMOT_SWIRL, TexturedModelData.of(PlayerEntityModel.getTexturedModelData(new Dilation(1.15f), false), 64, 32));
        consumer.accept(HALLOWED_ARMOR, TexturedModelData.of(HallowedArmorModel.getModelData(), 64, 32));
        consumer.accept(METALLURGIUM, TexturedModelData.of(MetallurgiumArmorModel.getModelData(), 64, 32));
        consumer.accept(RUNITE, TexturedModelData.of(RuniteArmorModel.getModelData(), 64, 32));
        consumer.accept(TIDESINGER, TexturedModelData.of(TidesingerArmorModel.getModelData(), 128, 128));
    }

    /**
     * Add a custom model layer into the global map for loading<br>
     * Shoutouts to williewillus for this implementation:
     * <a href="https://github.com/VazkiiMods/Botania/blob/1.18.x-fabric/src/main/java/vazkii/botania/client/model/ModModelLayers.java">Source</a>
     * @see net.minecraft.client.render.entity.model.EntityModelLayers#LAYERS
     */
    public static EntityModelLayer model(String name, String layer) {
        var result = new EntityModelLayer(RegistryHelper.id(name), layer);
        EntityModelLayersAccessor.getLAYERS().add(result);
        return result;
    }

    public static EntityModelLayer model(String name) {
        return model(name, "main");
    }
}
