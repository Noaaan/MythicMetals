package com.mythicmetals.client.models;

import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.mixin.client.EntityModelLayersAccessor;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.Identifier;
import java.util.function.BiConsumer;

public class MythicModelHandler {
    public static final ModelLayerLocation ADAMANTITE = model("adamantite_armor");
    public static final ModelLayerLocation BANGLUM = model("banglum_armor");
    public static final ModelLayerLocation CARMOT_SWIRL = model("carmot_swirl");
    public static final ModelLayerLocation CELESTIUM_ELYTRA = model("celestium_elytra");
    public static final ModelLayerLocation BABY_CELESTIUM_ELYTRA = model("baby_celestium_elytra");
    public static final ModelLayerLocation HALLOWED_ARMOR = model("hallowed_armor");
    public static final ModelLayerLocation METALLURGIUM = model("metallurgium_armor");
    public static final ModelLayerLocation RUNITE = model("runite_armor");
    public static final ModelLayerLocation TIDESINGER = model("tidesinger");
    public static final ModelLayerLocation BANGLUM_TNT_MINECART = model("banglum_tnt_minecart");
    public static final ModelLayerLocation PALLADIUM_MINECART = model("palladium_minecart");
    public static final ModelLayerLocation PALLADIUM = model("palladium_armor");
    public static final ModelLayerLocation STAR_PLATINUM_CLOAK = model("star_platinum_cloak");
    public static final Identifier HALLOWED_CAPE = RegistryHelper.id("textures/models/hallowed_cape.png");
    public static final Identifier STAR_PLATINUM_CLOAK_TEXTURE = RegistryHelper.id("textures/models/star_platinum_cloak.png");
    public static final Identifier PALLADIUM_MINECART_TEXTURE = RegistryHelper.id("textures/models/palladium_minecart.png");
    public static final Identifier CELESTIUM_ELYTRA_TEXTURE = RegistryHelper.id("textures/models/celestium_elytra.png");

    public static void init(BiConsumer<ModelLayerLocation, LayerDefinition> consumer) {
        consumer.accept(ADAMANTITE, LayerDefinition.create(AdamantiteArmorModel.getModelData(), 64, 32));
        consumer.accept(BANGLUM_TNT_MINECART, MinecartModel.createBodyLayer());
        consumer.accept(PALLADIUM_MINECART, MinecartModel.createBodyLayer());
        consumer.accept(CELESTIUM_ELYTRA, ElytraModel.createLayer());
        consumer.accept(BABY_CELESTIUM_ELYTRA, ElytraModel.createLayer().apply(ElytraModel.BABY_TRANSFORMER));
        consumer.accept(BANGLUM, LayerDefinition.create(BanglumArmorModel.getModelData(), 64, 32));
        consumer.accept(PALLADIUM, LayerDefinition.create(PalladiumArmorModel.getModelData(), 64, 32));
        consumer.accept(CARMOT_SWIRL, LayerDefinition.create(PlayerModel.createMesh(new CubeDeformation(1.15f), false), 64, 32));
        consumer.accept(HALLOWED_ARMOR, LayerDefinition.create(HallowedArmorModel.getModelData(), 64, 32));
        consumer.accept(METALLURGIUM, LayerDefinition.create(MetallurgiumArmorModel.getModelData(), 64, 32));
        consumer.accept(RUNITE, LayerDefinition.create(RuniteArmorModel.getModelData(), 64, 32));
        consumer.accept(TIDESINGER, LayerDefinition.create(TidesingerArmorModel.getModelData(), 128, 128));
        consumer.accept(STAR_PLATINUM_CLOAK, StarPlatCloakModel.getTexturedModelData());
    }

    /**
     * Add a custom model layer into the global map for loading<br>
     * Shoutouts to williewillus for this implementation:
     * <a href="https://github.com/VazkiiMods/Botania/blob/1.18.x-fabric/src/main/java/vazkii/botania/client/model/ModModelLayers.java">Source</a>
     *
     * @see net.minecraft.client.model.geom.ModelLayers#ALL_MODELS
     */
    public static ModelLayerLocation model(String name, String layer) {
        var result = new ModelLayerLocation(RegistryHelper.id(name), layer);
        EntityModelLayersAccessor.getALL_MODELS().add(result);
        return result;
    }

    public static ModelLayerLocation model(String name) {
        return model(name, "main");
    }
}
