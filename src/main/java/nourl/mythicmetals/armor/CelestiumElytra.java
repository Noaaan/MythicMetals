package nourl.mythicmetals.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.event.GameEvent;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.RegisterEntityAttributes;
import nourl.mythicmetals.registry.RegisterSounds;

import java.util.UUID;

public class CelestiumElytra extends ElytraItem implements FabricElytraItem {
    public CelestiumElytra(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.CELESTIUM.getIngot());
    }

    @Override
    public SoundEvent getEquipSound() {
        return RegisterSounds.EQUIP_CELESTIUM_ELYTRA;
    }

    public static boolean isWearing(LivingEntity entity) {
        var stack = entity.getEquippedStack(EquipmentSlot.CHEST);
        return stack.isOf(MythicArmor.CELESTIUM_ELYTRA) && isUsable(stack);
    }

    @Override
    public void doVanillaElytraTick(LivingEntity entity, ItemStack chestStack) {
        int nextRoll = entity.getRoll() + 1;

        if (!entity.getWorld().isClient && nextRoll % 10 == 0) {
            if ((nextRoll / 10) % 4 == 0) {
                chestStack.damage(1, entity, p -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
            }

            entity.emitGameEvent(GameEvent.ELYTRA_GLIDE);
        }
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        var map = super.getAttributeModifiers(slot);

        if (slot == this.getSlotType()) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            map.forEach(builder::put);

            var armor = new EntityAttributeModifier(UUID.fromString("d680f1ed-ff18-4a87-941a-dade3b331f31"),"Armor", 5.0F, EntityAttributeModifier.Operation.ADDITION);
            var toughness = new EntityAttributeModifier(UUID.fromString("a15a58bd-547d-4e2a-8af5-8e70cefbb570"),"Toughness", 3.0F, EntityAttributeModifier.Operation.ADDITION);
            var speed = new EntityAttributeModifier(UUID.fromString("cac9c98e-3724-4d7a-8b1a-239ad2439cad"),"Speed bonus", 0.08F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            var rocketSpeedBonus = new EntityAttributeModifier(UUID.fromString("9713febd-8a31-4abb-9f33-2e3da530f96e"),"Rocket speed bonus", 0.20F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            builder.put(EntityAttributes.GENERIC_ARMOR, armor);
            builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, toughness);
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed);
            builder.put(RegisterEntityAttributes.ELYTRA_ROCKET_SPEED, rocketSpeedBonus);
            return builder.build();
        }

        return map;
    }
}
