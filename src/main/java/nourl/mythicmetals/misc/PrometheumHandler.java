package nourl.mythicmetals.misc;

import io.wispforest.owo.serialization.Endec;
import io.wispforest.owo.serialization.endec.KeyedEndec;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import nourl.mythicmetals.data.MythicTags;
import java.util.UUID;

public class PrometheumHandler {
    /**
     * Used to track how much durability is repaired
     * @deprecated will be replaced by "mm_durability_repaired"
     */
    public static final KeyedEndec<Integer> DURABILITY_REPAIRED = new KeyedEndec<>("DurabilityRepaired", Endec.INT, 0);
    public static final int OVERGROWN_THRESHOLD = 1200;

    /**
     * Applies auto repair onto the item in question
     * Only call this on the server, not on the client!
     *
     * @param stack ItemStack to repair
     * @param r     Any Minecraft Math {@link Random}
     */
    public static void tickAutoRepair(ItemStack stack, Random r) {
        if (!stack.isDamaged()) return; // Don't handle auto repair if item is fully repaired

        if (!stack.has(DURABILITY_REPAIRED)) {
            stack.put(DURABILITY_REPAIRED, 0);
        }

        var dmg = stack.getDamage();
        var rng = r.nextInt(200);

        if (rng != 177) return; // Roll for repair, ignore if roll fails. Number is arbitrary

        // Overgrown Items repair faster
        int damageToRepair = isOvergrown(stack) ? 2 : 1;

        // Extra repair speed if bound
        if (stack.isIn(MythicTags.PROMETHEUM_ARMOR) && EnchantmentHelper.hasBindingCurse(stack)) {
            damageToRepair += 1;
        }

        int newDamage = MathHelper.clamp(dmg - damageToRepair, 0, Integer.MAX_VALUE);
        stack.setDamage(newDamage);

        incrementRepairCounter(stack, damageToRepair);
    }

    public static void incrementRepairCounter(ItemStack stack, int value) {
        int counter = stack.get(DURABILITY_REPAIRED);
        if (counter < (Integer.MAX_VALUE / 2)) {
            stack.put(DURABILITY_REPAIRED, counter + value);
        }
    }

    public static boolean isOvergrown(ItemStack stack) {
        return stack.has(DURABILITY_REPAIRED) && stack.get(DURABILITY_REPAIRED) > OVERGROWN_THRESHOLD;
    }

    /**
     * Registers an event that modifies all armor items in the tag with bonus attributes when bound
     */
    public static void registerPrometheumAttributeEvent() {
        ModifyItemAttributeModifiersCallback.EVENT.register((stack, slot, attributeModifiers) -> {
            if (stack.isIn(MythicTags.PROMETHEUM_ARMOR) && ((ArmorItem) stack.getItem()).getSlotType().equals(slot)) {
                if (EnchantmentHelper.hasBindingCurse(stack)) {
                    attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
                        UUID.fromString("d42e82c8-166d-46f1-bc76-df84e91b5531"),
                        "Bound Prometheum bonus",
                        0.08,
                        EntityAttributeModifier.Operation.MULTIPLY_BASE
                    ));
                }
                if (isOvergrown(stack)) {
                    attributeModifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(
                        UUID.fromString("37bb6460-e896-44e2-8e71-29335d5ce709"),
                        "Prometheum bonus toughness",
                        EnchantmentHelper.hasBindingCurse(stack) ? 2 : 1,
                        EntityAttributeModifier.Operation.ADDITION
                    ));
                }
            }
        });
    }
}
