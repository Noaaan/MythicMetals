package nourl.mythicmetals.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.client.models.HelmetModel;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.misc.RegistryHelper;
import org.jetbrains.annotations.NotNull;

public class HallowedArmor extends ArmorItem {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final ArmorItem.Type type;

    public HallowedArmor(ArmorItem.Type type, Settings settings) {
        this(MythicArmorMaterials.HALLOWED, type, settings);
    }

    public HallowedArmor(ArmorMaterial material, ArmorItem.Type slot, Settings settings) {
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
    protected BipedEntityModel<LivingEntity> provideArmorModelForSlot(EquipmentSlot slot) {
        var models = MinecraftClient.getInstance().getEntityModelLoader();
        var root = models.getModelPart(MythicModelHandler.HALLOWED_ARMOR);
        return new HelmetModel(root, slot);
    }

    @NotNull
    public Identifier getArmorTexture(ItemStack stack, EquipmentSlot slot) {
        return RegistryHelper.id("textures/models/hallowed_model.png");
    }
}
