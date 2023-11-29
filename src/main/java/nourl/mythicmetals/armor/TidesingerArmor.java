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
import nourl.mythicmetals.client.models.TidesingerBipedModel;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.misc.RegistryHelper;
import org.jetbrains.annotations.NotNull;

public class TidesingerArmor extends HallowedArmor {

    @Environment(EnvType.CLIENT)
    private BipedEntityModel<LivingEntity> model;
    public final Type type;

    public TidesingerArmor(Type type, Settings settings) {
        this(MythicArmorMaterials.HALLOWED, type, settings);
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
        return RegistryHelper.id("textures/models/tidesinger_model.png");
    }
}
