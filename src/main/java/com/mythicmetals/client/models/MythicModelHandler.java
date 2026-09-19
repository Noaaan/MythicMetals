package com.mythicmetals.client.models;

import com.mythicmetals.misc.MythicModelIdentifiers;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.mixin.client.ModelLayersAccessor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.cart.MinecartModel;
import net.minecraft.client.model.object.equipment.ElytraModel;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.resources.Identifier;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MythicModelHandler {
    // FIXME - Rename and move armor
    public static final ModelLayerLocation ADAMANTITE_ARMOR = model("adamantite_armor");
    public static final ModelLayerLocation LEGENDARY_BANGLUM_ARMOR = model("banglum_armor");
    public static final ModelLayerLocation CARMOT_SWIRL = model("carmot_swirl");
    public static final ModelLayerLocation CELESTIUM_ELYTRA = model("celestium_elytra");
    public static final ModelLayerLocation BABY_CELESTIUM_ELYTRA = model("baby_celestium_elytra");
    public static final ModelLayerLocation HALLOWED_ARMOR = model("hallowed_armor");
    public static final ModelLayerLocation METALLURGIUM_ARMOR = model("metallurgium_armor");
    public static final ModelLayerLocation RUNITE_ARMOR = model("runite_armor");
    public static final ModelLayerLocation TIDESINGER_ARMOR = model("tidesinger_armor");
    public static final ModelLayerLocation BANGLUM_TNT_MINECART = model("banglum_tnt_minecart");
    public static final ModelLayerLocation PALLADIUM_MINECART = model("palladium_minecart");
    public static final ModelLayerLocation PALLADIUM_ARMOR = model("palladium_armor");
    public static final ModelLayerLocation STAR_PLATINUM_CLOAK = model("star_platinum_cloak");

    private static final Map<Identifier, ModelLayerLocation> MODELS = new HashMap<>();

    public static void init(BiConsumer<ModelLayerLocation, LayerDefinition> consumer) {
        consumer.accept(ADAMANTITE_ARMOR, LayerDefinition.create(AdamantiteArmorModel.getModelData(), 64, 32));
        consumer.accept(BANGLUM_TNT_MINECART, MinecartModel.createBodyLayer());
        consumer.accept(PALLADIUM_MINECART, MinecartModel.createBodyLayer());
        consumer.accept(CELESTIUM_ELYTRA, ElytraModel.createLayer());
        consumer.accept(BABY_CELESTIUM_ELYTRA, ElytraModel.createLayer().apply(ElytraModel.BABY_TRANSFORMER));
        consumer.accept(LEGENDARY_BANGLUM_ARMOR, LayerDefinition.create(BanglumArmorModel.getModelData(), 64, 32));
        consumer.accept(PALLADIUM_ARMOR, LayerDefinition.create(PalladiumArmorModel.getModelData(), 64, 32));
        consumer.accept(CARMOT_SWIRL, LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(1.15f), false), 64, 32));
        consumer.accept(HALLOWED_ARMOR, LayerDefinition.create(HallowedArmorModel.getModelData(), 64, 32));
        consumer.accept(METALLURGIUM_ARMOR, LayerDefinition.create(MetallurgiumArmorModel.getModelData(), 64, 32));
        consumer.accept(RUNITE_ARMOR, LayerDefinition.create(RuniteArmorModel.getModelData(), 64, 32));
        consumer.accept(TIDESINGER_ARMOR, LayerDefinition.create(TidesingerArmorModel.getModelData(), 128, 128));
        consumer.accept(STAR_PLATINUM_CLOAK, StarPlatCloakModel.getTexturedModelData());

        MODELS.put(MythicModelIdentifiers.ADAMANTITE_ARMOR, ADAMANTITE_ARMOR);
        MODELS.put(MythicModelIdentifiers.STAR_PLATINUM_CLOAK, STAR_PLATINUM_CLOAK);
        MODELS.put(MythicModelIdentifiers.PALLADIUM_MINECART, PALLADIUM_MINECART);
        MODELS.put(MythicModelIdentifiers.CELESTIUM_ELYTRA, CELESTIUM_ELYTRA);
        MODELS.put(MythicModelIdentifiers.HALLOWED_ARMOR, HALLOWED_ARMOR);
        MODELS.put(MythicModelIdentifiers.LEGENDARY_BANGLUM_ARMOR, LEGENDARY_BANGLUM_ARMOR);
        MODELS.put(MythicModelIdentifiers.METALLURGIUM_ARMOR, METALLURGIUM_ARMOR);
        MODELS.put(MythicModelIdentifiers.PALLADIUM_ARMOR, PALLADIUM_ARMOR);
        MODELS.put(MythicModelIdentifiers.RUNITE_ARMOR, RUNITE_ARMOR);
    }

    /**
     * Add a custom model layer into the global map for loading<br>
     * Shoutouts to williewillus for this implementation:
     * <a href="https://github.com/VazkiiMods/Botania/blob/1.18.x-fabric/src/main/java/vazkii/botania/client/model/ModModelLayers.java">Source</a>
     *
     * @see ModelLayersAccessor#getALL_MODELS()
     */
    public static ModelLayerLocation model(String name, String layer) {
        var result = new ModelLayerLocation(RegistryHelper.id(name), layer);
        ModelLayersAccessor.getALL_MODELS().add(result);
        return result;
    }

    public static ModelLayerLocation model(String name) {
        return model(name, "main");
    }

    public static void addModel(Identifier texture, String layer) {
        MODELS.put(texture, model(layer));
    }

    public static ModelLayerLocation getModel(Identifier texture) {
        return MODELS.get(texture);
    }
}
