package nourl.mythicmetals.armor;

import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.event.GameEvent;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.entity.MythicEntityAttributes;
import nourl.mythicmetals.registry.RegisterSounds;

public class CelestiumElytra extends ElytraItem implements FabricElytraItem {
    public CelestiumElytra(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.CELESTIUM.getIngot());
    }

    @Override
    public RegistryEntry<SoundEvent> getEquipSound() {
        return Registries.SOUND_EVENT.getEntry(RegisterSounds.EQUIP_CELESTIUM_ELYTRA);
    }

    public static boolean isWearing(LivingEntity entity) {
        var stack = entity.getEquippedStack(EquipmentSlot.CHEST);
        return stack.isOf(MythicArmor.CELESTIUM_ELYTRA) && isUsable(stack);
    }

    @Override
    public void doVanillaElytraTick(LivingEntity entity, ItemStack chestStack) {
        int nextRoll = entity.getFallFlyingTicks() + 1;

        if (!entity.getWorld().isClient && nextRoll % 10 == 0) {
            if ((nextRoll / 10) % 4 == 0) {
                chestStack.damage(1, entity, EquipmentSlot.CHEST);
            }

            entity.emitGameEvent(GameEvent.ELYTRA_GLIDE);
        }
    }

    public static AttributeModifiersComponent createDefaultAttributes() {
        var builder = AttributeModifiersComponent.builder();
        var armor = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_armor_protection"), 5.0F, EntityAttributeModifier.Operation.ADD_VALUE);
        var toughness = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_armor_toughness"), 3.0F, EntityAttributeModifier.Operation.ADD_VALUE);
        var speed = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_speed_bonus"), 0.08F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        var rocketSpeedBonus = new EntityAttributeModifier(RegistryHelper.id("celestium_elytra_rocket_speed_bonus"), 0.20F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        builder.add(EntityAttributes.GENERIC_ARMOR, armor, AttributeModifierSlot.CHEST);
        builder.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, toughness, AttributeModifierSlot.CHEST);
        builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed, AttributeModifierSlot.CHEST);
        builder.add(MythicEntityAttributes.ELYTRA_ROCKET_SPEED, rocketSpeedBonus, AttributeModifierSlot.CHEST);
        return builder.build();
    }
}
