package com.mythicmetals.mixin;

import net.minecraft.item.ArmorItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin {
    // FIXME - Migrate to MythicArmor to be built manually
//
//    @Inject(method = "method_56689", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;ofVanilla(Ljava/lang/String;)Lnet/minecraft/util/Identifier;"), locals = LocalCapture.CAPTURE_FAILSOFT)
//    private static void constructor(RegistryEntry<?> registryEntry, EquipmentType type, CallbackInfoReturnable<AttributeModifiersComponent> cir, int i, float f, AttributeModifiersComponent.Builder builder, AttributeModifierSlot slot) {
//        var material = registryEntry.value();
//        if (material == MythicArmorMaterials.TIDESINGER) {
//            mythicmetals$armorMapBuilder(builder, "tidesinger_%s_swim_speed_bonus".formatted(type.getName()), AdditionalEntityAttributes.WATER_SPEED, 0.1F, ADD_MULTIPLIED_TOTAL, slot);
//            switch (type) {
//                case HELMET -> {
//                    mythicmetals$armorMapBuilder(builder, "tidesinger_helmet_underwater_mining_bonus", EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, 3.0f, ADD_MULTIPLIED_TOTAL, slot);
//                    mythicmetals$armorMapBuilder(builder, "tidesinger_helmet_vision_bonus", AdditionalEntityAttributes.WATER_VISIBILITY, 0.3f, ADD_MULTIPLIED_TOTAL, slot);
//                }
//                case CHESTPLATE, LEGGINGS ->
//                    mythicmetals$armorMapBuilder(builder, "tidesinger_%s_oxygen_bonus".formatted(type.getName()), EntityAttributes.GENERIC_OXYGEN_BONUS, 2.0f, ADD_VALUE, slot);
//                case BOOTS ->
//                    mythicmetals$armorMapBuilder(builder, "tidesinger_boots_depth_strider_bonus", EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 1.0f, ADD_MULTIPLIED_TOTAL, slot);
//            }
//
//        }
//        if (material.equals(MythicArmorMaterials.AQUARIUM)) {
//            switch (type) {
//                case HELMET ->
//                    mythicmetals$armorMapBuilder(builder, "aquarium_helmet_underwater_mining_bonus", EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, 1.0f, ADD_MULTIPLIED_BASE, slot);
//                case CHESTPLATE, LEGGINGS ->
//                    mythicmetals$armorMapBuilder(builder, "aquarium_%s_oxygen_bonus".formatted(type.getName()), EntityAttributes.GENERIC_OXYGEN_BONUS, 1.0f, ADD_VALUE, slot);
//                case BOOTS ->
//                    mythicmetals$armorMapBuilder(builder, "aquarium_boots_depth_strider_bonus", EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 0.5F, ADD_VALUE, slot);
//            }
//        }
//        if (material.equals(MythicArmorMaterials.CELESTIUM)) {
//            mythicmetals$armorMapBuilder(builder, "celestium_%s_speed_bonus".formatted(type.getName()), EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1F, ADD_MULTIPLIED_TOTAL, slot);
//            mythicmetals$armorMapBuilder(builder, "celestium_%s_damage_bonus".formatted(type.getName()), EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0F, ADD_VALUE, slot);
//        }
//        if (material.equals(MythicArmorMaterials.MIDAS_GOLD)) {
//            mythicmetals$armorMapBuilder(builder, "midas_%s_luck_bonus".formatted(type.getName()), EntityAttributes.GENERIC_LUCK, 1.0F, ADD_VALUE, slot);
//        }
//        if (material.equals(MythicArmorMaterials.STAR_PLATINUM)) {
//            mythicmetals$armorMapBuilder(builder, "star_platinum_%s_attack_bonus".formatted(type.getName()), EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0F, ADD_VALUE, slot);
//        }
//        if (material.equals(MythicArmorMaterials.CARMOT)) {
//            mythicmetals$armorMapBuilder(builder, "carmot_%s_carmot_shield_bonus".formatted(type.getName()), MythicEntityAttributes.CARMOT_SHIELD, 5.0F, ADD_VALUE, slot);
//            mythicmetals$armorMapBuilder(builder, "carmot_%s_health_bonus".formatted(type.getName()), EntityAttributes.GENERIC_MAX_HEALTH, 2.0F, ADD_VALUE, slot);
//        }
//        if (material.equals(MythicArmorMaterials.STORMYX)) {
//            mythicmetals$armorMapBuilder(builder, "stormyx_%s_magic_protection".formatted(type.getName()), AdditionalEntityAttributes.MAGIC_PROTECTION, 2.0f, ADD_VALUE, slot);
//        }
//        if (material.equals(MythicArmorMaterials.PALLADIUM)) {
//            if (type.getEquipmentSlot().equals(EquipmentSlot.HEAD)) {
//                mythicmetals$armorMapBuilder(builder, "palladium_%s_lava_vision_bonus".formatted(type.getName()), AdditionalEntityAttributes.LAVA_VISIBILITY, 8.0f, ADD_VALUE, slot);
//            } else {
//                mythicmetals$armorMapBuilder(builder, "palladium_%s_lava_speed_bonus".formatted(type.getName()), AdditionalEntityAttributes.LAVA_SPEED, 0.1f, ADD_MULTIPLIED_TOTAL, slot);
//            }
//            mythicmetals$armorMapBuilder(builder, "palladium_%s_burn_time_reduction_bonus".formatted(type.getName()), EntityAttributes.GENERIC_BURNING_TIME, -0.25f, ADD_MULTIPLIED_BASE, slot);
//        }
//    }
//
//    @Unique
//    private static void mythicmetals$armorMapBuilder(AttributeModifiersComponent.Builder builder, String id, RegistryEntry<EntityAttribute> attributeEntry, float value, EntityAttributeModifier.Operation operation, AttributeModifierSlot slot) {
//        builder.add(attributeEntry, new EntityAttributeModifier(RegistryHelper.id(id), value, operation), slot);
//    }

}
