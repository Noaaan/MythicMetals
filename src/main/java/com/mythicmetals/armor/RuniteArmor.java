package com.mythicmetals.armor;

import com.mythicmetals.client.models.HelmetModel;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.NotNull;

public class RuniteArmor extends ArmorItem implements CustomArmorModelItem {

    @Environment(EnvType.CLIENT)
    private HumanoidModel<HumanoidRenderState> model;
    public final ArmorType type;

    public RuniteArmor(ArmorType type, Properties settings) {
        this(MythicArmorMaterials.RUNITE, type, settings);
    }

    public RuniteArmor(ArmorMaterial material, ArmorType type, Properties settings) {
        super(material, type, settings);
        this.type = type;
    }

    @Environment(EnvType.CLIENT)
    public HumanoidModel<HumanoidRenderState> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(type.getSlot());
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    public HumanoidModel<HumanoidRenderState> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = Minecraft.getInstance().getEntityModels();
        var root = models.bakeLayer(MythicModelHandler.RUNITE);
        return new HelmetModel(root, slot);
    }

    @Override
    @NotNull
    public ResourceLocation getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/runite_model.png");
    }
}
