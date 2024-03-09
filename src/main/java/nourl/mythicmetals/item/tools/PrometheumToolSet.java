package nourl.mythicmetals.item.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.*;
import net.minecraft.item.*;
import net.minecraft.world.World;
import nourl.mythicmetals.misc.PrometheumHandler;
import java.util.UUID;

public class PrometheumToolSet extends ToolSet {

    public PrometheumToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumPick(material, damage, speed, settings);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumAxe(material, damage, speed, settings);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumHoe(material, damage, speed, settings);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PrometheumShovel(material, damage, speed, settings);
    }

    public static class PrometheumAxe extends AxeItem implements AutoRepairable {
        public PrometheumAxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
            if (slot != EquipmentSlot.MAINHAND) return super.getAttributeModifiers(slot);

            var modifiers = HashMultimap.create(super.getAttributeModifiers(slot));

            if (stack.has(PrometheumHandler.DURABILITY_REPAIRED) && stack.get(PrometheumHandler.DURABILITY_REPAIRED) > PrometheumHandler.OVERGROWN_THRESHOLD) {
                modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                        UUID.fromString("69def8b1-1baa-401e-a7cb-b27ab9a55558"),
                        "Overgrown Prometheum bonus",
                        1.0,
                        EntityAttributeModifier.Operation.ADDITION)
                );
            }

            return modifiers;
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
            if (!world.isClient()) PrometheumHandler.tickAutoRepair(stack, world.getRandom());
            super.inventoryTick(stack, world, entity, slot, selected);
        }
    }

    public static class PrometheumHoe extends HoeItem implements AutoRepairable {
        public PrometheumHoe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
            if (slot != EquipmentSlot.MAINHAND) return super.getAttributeModifiers(slot);

            var modifiers = HashMultimap.create(super.getAttributeModifiers(slot));

            if (stack.has(PrometheumHandler.DURABILITY_REPAIRED) && stack.get(PrometheumHandler.DURABILITY_REPAIRED) > PrometheumHandler.OVERGROWN_THRESHOLD) {
                modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                        UUID.fromString("69def8b1-1baa-401e-a7cb-b27ab9a55558"),
                        "Overgrown Prometheum bonus",
                        1.0,
                        EntityAttributeModifier.Operation.ADDITION)
                );
            }

            return modifiers;
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
            if (!world.isClient()) PrometheumHandler.tickAutoRepair(stack, world.getRandom());
            super.inventoryTick(stack, world, entity, slot, selected);
        }
    }

    public static class PrometheumPick extends PickaxeItem implements AutoRepairable {
        public PrometheumPick(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
            if (slot != EquipmentSlot.MAINHAND) return super.getAttributeModifiers(slot);

            var modifiers = HashMultimap.create(super.getAttributeModifiers(slot));

            if (stack.has(PrometheumHandler.DURABILITY_REPAIRED) && stack.get(PrometheumHandler.DURABILITY_REPAIRED) > PrometheumHandler.OVERGROWN_THRESHOLD) {
                modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                        UUID.fromString("69def8b1-1baa-401e-a7cb-b27ab9a55558"),
                        "Overgrown Prometheum bonus",
                        1.0,
                        EntityAttributeModifier.Operation.ADDITION)
                );
            }

            return modifiers;
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
            if (!world.isClient()) PrometheumHandler.tickAutoRepair(stack, world.getRandom());
            super.inventoryTick(stack, world, entity, slot, selected);
        }
    }

    public static class PrometheumShovel extends ShovelItem implements AutoRepairable {
        public PrometheumShovel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
            if (slot != EquipmentSlot.MAINHAND) return super.getAttributeModifiers(slot);

            var modifiers = HashMultimap.create(super.getAttributeModifiers(slot));

            if (stack.has(PrometheumHandler.DURABILITY_REPAIRED) && stack.get(PrometheumHandler.DURABILITY_REPAIRED) > PrometheumHandler.OVERGROWN_THRESHOLD) {
                modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                        UUID.fromString("69def8b1-1baa-401e-a7cb-b27ab9a55558"),
                        "Overgrown Prometheum bonus",
                        1.0,
                        EntityAttributeModifier.Operation.ADDITION)
                );
            }

            return modifiers;
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
            if (!world.isClient()) PrometheumHandler.tickAutoRepair(stack, world.getRandom());
            super.inventoryTick(stack, world, entity, slot, selected);
        }
    }

    public static class PrometheumSword extends SwordItem implements AutoRepairable {
        public PrometheumSword(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
        }

        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
            if (slot != EquipmentSlot.MAINHAND) return super.getAttributeModifiers(slot);

            var modifiers = HashMultimap.create(super.getAttributeModifiers(slot));

            if (stack.has(PrometheumHandler.DURABILITY_REPAIRED) && stack.get(PrometheumHandler.DURABILITY_REPAIRED) > PrometheumHandler.OVERGROWN_THRESHOLD) {
                modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(
                        UUID.fromString("69def8b1-1baa-401e-a7cb-b27ab9a55558"),
                        "Overgrown Prometheum bonus",
                        1.0,
                        EntityAttributeModifier.Operation.ADDITION)
                );
            }

            return modifiers;
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
            if (!world.isClient()) PrometheumHandler.tickAutoRepair(stack, world.getRandom());
            super.inventoryTick(stack, world, entity, slot, selected);
        }
    }

}
