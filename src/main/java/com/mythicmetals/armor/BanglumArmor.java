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
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class BanglumArmor extends HallowedArmor {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<BipedEntityRenderState> model;
    public final EquipmentType type;

    public BanglumArmor(EquipmentType type, Settings settings) {
        this(MythicArmorMaterials.LEGENDARY_BANGLUM, type, settings);
    }

    public BanglumArmor(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(material, type, settings);
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
    protected BipedEntityModel<BipedEntityRenderState> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getLoadedEntityModels();
        var root = models.getModelPart(MythicModelHandler.BANGLUM);
        return new HelmetModel(root, slot);
    }

    @NotNull
    @Override
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/banglum_model.png");
    }
}
