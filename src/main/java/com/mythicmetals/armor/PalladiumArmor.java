package com.mythicmetals.armor;

import com.mythicmetals.client.models.HelmetModel;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class PalladiumArmor extends Item implements CustomArmorModelItem {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<BipedEntityRenderState> model;
    public final EquipmentType type;

    public PalladiumArmor(EquipmentType type, Settings settings) {
        super(settings);
        this.type = type;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<BipedEntityRenderState> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(type.getEquipmentSlot());
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<BipedEntityRenderState> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getLoadedEntityModels();
        var root = models.getModelPart(MythicModelHandler.PALLADIUM);
        return new HelmetModel(root, slot);
    }

    @NotNull
    @Override
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/palladium_model.png");
    }
}
