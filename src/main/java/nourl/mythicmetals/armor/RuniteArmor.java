package nourl.mythicmetals.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.client.models.HelmetModel;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.misc.RegistryHelper;
import org.jetbrains.annotations.NotNull;

public class RuniteArmor extends HallowedArmor {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final EquipmentSlot slot;

    public RuniteArmor(EquipmentSlot slot, Settings settings) {
        this(MythicArmorMaterials.RUNITE, slot, settings);
    }

    public RuniteArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
        this.slot = slot;
    }

    @Environment(EnvType.CLIENT)
    public BipedEntityModel<LivingEntity> getArmorModel() {
        if (model == null) {
            model = provideArmorModelForSlot(slot);
        }
        return model;
    }

    @Environment(EnvType.CLIENT)
    protected BipedEntityModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getEntityModelLoader();
        var root = models.getModelPart(MythicModelHandler.RUNITE);
        return new HelmetModel(root, slot);
    }

    @NotNull
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/runite_model.png");
    }
}
