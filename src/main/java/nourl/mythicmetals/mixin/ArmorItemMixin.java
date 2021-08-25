package nourl.mythicmetals.mixin;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import nourl.mythicmetals.armor.ArmorMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin {

    @Shadow
    @Final
    private static UUID[] MODIFIERS;
    @Shadow
    @Final
    protected float knockbackResistance;
    @Shadow
    @Final
    @Mutable
    private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void constructor(ArmorMaterial material, EquipmentSlot slot, Item.Settings settings, CallbackInfo ci) {
        UUID uUID = MODIFIERS[slot.getEntitySlotId()];

        if (ArmorMaterials.KNOCKBACKABLE_ARMOR_MATERIALS.contains(material)) {
            armorMapBuilder(uUID, EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, "Knockback resistance", this.knockbackResistance, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == ArmorMaterials.CELESTIUM) {
            armorMapBuilder(uUID, EntityAttributes.GENERIC_MOVEMENT_SPEED, "Speed bonus", 0.05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        if (material == ArmorMaterials.ETHERITE) {
            armorMapBuilder(uUID, EntityAttributes.GENERIC_ATTACK_DAMAGE, "Attack damage bonus", 0.5f, EntityAttributeModifier.Operation.ADDITION);
        }
        if (material == ArmorMaterials.MIDAS_GOLD) {
            armorMapBuilder(uUID, EntityAttributes.GENERIC_LUCK, "Luck bonus", 1.0f, EntityAttributeModifier.Operation.ADDITION);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void armorMapBuilder(UUID uUID, EntityAttribute attributes, String name, float value, EntityAttributeModifier.Operation operation) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        this.attributeModifiers.forEach(builder::put);
        builder.put(attributes,
                new EntityAttributeModifier(uUID, name, value, operation));
        this.attributeModifiers = builder.build();
    }

}
