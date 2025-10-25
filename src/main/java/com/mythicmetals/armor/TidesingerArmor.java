package com.mythicmetals.armor;

import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.models.TidesingerBipedModel;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.TidesingerPatternComponent;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class TidesingerArmor extends Item implements CustomArmorModelItem {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<BipedEntityRenderState> model;
    public final EquipmentType type;

    public TidesingerArmor(EquipmentType slot, Settings settings) {
        super(settings.component(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.empty()));
        this.type = slot;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<BipedEntityRenderState> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(type.getEquipmentSlot());
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BipedEntityModel<BipedEntityRenderState> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getLoadedEntityModels();
        var root = models.getModelPart(MythicModelHandler.TIDESINGER);
        return new TidesingerBipedModel(root, slot);
    }

    @NotNull
    @Override
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        var component = stack.getOrDefault(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.empty());
        String model = switch (component.pattern()) {
            case "brain" -> "textures/models/tidesinger_model_brain.png";
            case "bubble" -> "textures/models/tidesinger_model_bubble.png";
            case "fire" -> "textures/models/tidesinger_model_fire.png";
            case "horn" -> "textures/models/tidesinger_model_horn.png";
            case "tube" -> "textures/models/tidesinger_model_tube.png";
            default -> "textures/models/tidesinger_model.png";
        };
        return RegistryHelper.id(model);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> lines, TooltipType type) {
        if (stack.contains(MythicDataComponents.TIDESINGER)) {
            stack.get(MythicDataComponents.TIDESINGER).appendTooltip(context, lines::add, type);
        }
    }
}
