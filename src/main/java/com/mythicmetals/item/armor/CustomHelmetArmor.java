package com.mythicmetals.item.armor;

import com.mythicmetals.api.v2.client.CustomArmorModelItem;
import com.mythicmetals.client.models.HelmetModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.ArmorType;
import org.jetbrains.annotations.NotNull;

public class CustomHelmetArmor extends Item implements CustomArmorModelItem {

    private final ModelLayerLocation modelLocation;
    private final Identifier texture;
    @Environment(EnvType.CLIENT)
    private HumanoidModel<HumanoidRenderState> model;
    public final ArmorType type;

    public CustomHelmetArmor(ArmorType type, Properties properties, ModelLayerLocation modelLocation, Identifier texture) {
        super(properties);
        this.type = type;
        this.modelLocation = modelLocation;
        this.texture = texture;
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
        var root = models.bakeLayer(modelLocation);
        return new HelmetModel(root, slot);
    }

    @Override
    public @NotNull Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return texture;
    }
}
