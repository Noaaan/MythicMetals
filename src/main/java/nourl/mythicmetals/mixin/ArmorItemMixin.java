package nourl.mythicmetals.mixin;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import nourl.mythicmetals.armor.MythicArmorMaterials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import java.util.UUID;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin {

    @Shadow
    @Final
    private static EnumMap<ArmorItem.Type, UUID> MODIFIERS;

    @Shadow
    @Final
    @Mutable
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void constructor(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings, CallbackInfo ci) {
        UUID uUID = MODIFIERS.get(type);
        if (material == MythicArmorMaterials.IMPROVED_AQUARIUM) {
            mythicmetals$armorMapBuilder(uUID, AdditionalEntityAttributes.WATER_SPEED, "Swim Speed bonus", 0.05F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        if (material == MythicArmorMaterials.CELESTIUM) {
            mythicmetals$armorMapBuilder(uUID, EntityAttributes.GENERIC_MOVEMENT_SPEED, "Celestium speed bonus", 0.1F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            mythicmetals$armorMapBuilder(uUID, EntityAttributes.GENERIC_ATTACK_DAMAGE, "Celestium damage bonus", 1.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicArmorMaterials.MIDAS_GOLD) {
            mythicmetals$armorMapBuilder(uUID, EntityAttributes.GENERIC_LUCK, "Luck bonus", 1.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicArmorMaterials.STAR_PLATINUM) {
            mythicmetals$armorMapBuilder(uUID, EntityAttributes.GENERIC_ATTACK_DAMAGE, "Star Platinum attack bonus", 1.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicArmorMaterials.CARMOT) {
            mythicmetals$armorMapBuilder(uUID, EntityAttributes.GENERIC_MAX_HEALTH, "Carmot health bonus", 2.0F, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicArmorMaterials.STORMYX) {
            float value = type.getEquipmentSlot().equals(EquipmentSlot.LEGS) || type.getEquipmentSlot().equals(EquipmentSlot.CHEST) ? 2.0F : 1.0F;
            mythicmetals$armorMapBuilder(uUID, AdditionalEntityAttributes.MAGIC_PROTECTION, "Stormyx magic protection", value, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == MythicArmorMaterials.PALLADIUM && type.getEquipmentSlot().equals(EquipmentSlot.HEAD)) {
            mythicmetals$armorMapBuilder(uUID, AdditionalEntityAttributes.LAVA_VISIBILITY, "Palladium lava vision bonus", 8.0f, EntityAttributeModifier.Operation.ADDITION);
        } else if (material == MythicArmorMaterials.PALLADIUM) {
            mythicmetals$armorMapBuilder(uUID, AdditionalEntityAttributes.LAVA_SPEED, "Palladium lava swim speed bonus", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
    }

    @Unique
    private void mythicmetals$armorMapBuilder(UUID uUID, EntityAttribute attributes, String name, float value, EntityAttributeModifier.Operation operation) {
        var mapnite = HashMultimap.create(this.attributeModifiers);
        mapnite.put(attributes, new EntityAttributeModifier(uUID, name, value, operation));
        this.attributeModifiers = mapnite;
    }

}
