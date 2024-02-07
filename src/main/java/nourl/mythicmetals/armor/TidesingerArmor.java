package nourl.mythicmetals.armor;

import io.wispforest.owo.serialization.Endec;
import io.wispforest.owo.serialization.endec.KeyedEndec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.client.models.TidesingerBipedModel;
import nourl.mythicmetals.misc.RegistryHelper;
import org.jetbrains.annotations.NotNull;

public class TidesingerArmor extends HallowedArmor {
    // TODO - Minecraft finally unified their style somewhat - NBT fields are all PascalCase
    public static KeyedEndec<String> CORAL_TYPE = new KeyedEndec<>("mm_coral_type", Endec.STRING, "none");

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final Type type;

    public TidesingerArmor(Type type, Settings settings) {
        this(MythicArmorMaterials.TIDESINGER, type, settings);
    }

    public TidesingerArmor(ArmorMaterial material, Type slot, Settings settings) {
        super(material, slot, settings);
        this.type = slot;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(type.getEquipmentSlot());
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    @Override
    protected BipedEntityModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getEntityModelLoader();
        var root = models.getModelPart(MythicModelHandler.TIDESINGER);
        return new TidesingerBipedModel(root, slot);
    }

    @NotNull
    @Override
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        String model = switch (stack.get(CORAL_TYPE)) {
            case "brain" -> "textures/models/tidesinger_model_brain.png";
            case "bubble" -> "textures/models/tidesinger_model_bubble.png";
            case "fire" -> "textures/models/tidesinger_model_fire.png";
            case "horn" -> "textures/models/tidesinger_model_horn.png";
            case "tube" -> "textures/models/tidesinger_model_tube.png";
            default -> "textures/models/tidesinger_model.png";
        };
        return RegistryHelper.id(model);
    }
}
