package com.mythicmetals.item.armor;

import com.mythicmetals.api.v2.client.CustomArmorModelItem;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.TidesingerBipedModel;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.TidesingerPatternComponent;
import com.mythicmetals.misc.MythicModelIdentifiers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import org.jspecify.annotations.NonNull;

public class TidesingerArmor extends Item implements CustomArmorModelItem {

    @Environment(EnvType.CLIENT)
    private HumanoidModel<HumanoidRenderState> model;
    public final ArmorType type;

    public TidesingerArmor(ArmorType slot, Properties settings) {
        super(settings.component(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.empty()));
        this.type = slot;
    }

    @Environment(EnvType.CLIENT)
    public HumanoidModel<HumanoidRenderState> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(type.getSlot());
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public HumanoidModel<HumanoidRenderState> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = Minecraft.getInstance().getEntityModels();
        var root = models.bakeLayer(MythicModelHandler.TIDESINGER_ARMOR);
        return new TidesingerBipedModel(root, slot);
    }

    @NonNull
    @Override
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        var component = stack.getOrDefault(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.empty());
        return switch (component.pattern()) {
            case "brain" -> MythicModelIdentifiers.TIDESINGER_BRAIN;
            case "bubble" -> MythicModelIdentifiers.TIDESINGER_BUBBLE;
            case "fire" -> MythicModelIdentifiers.TIDESINGER_FIRE;
            case "horn" -> MythicModelIdentifiers.TIDESINGER_HORN;
            case "tube" -> MythicModelIdentifiers.TIDESINGER_TUBE;
            default -> MythicModelIdentifiers.TIDESINGER;
        };
    }
}
