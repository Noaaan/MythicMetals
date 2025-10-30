package com.mythicmetals.item.tools;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.component.*;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.joml.Math;
import java.util.*;

import static com.mythicmetals.component.DrillComponent.*;

public class MythrilDrill extends MiningToolItem implements AutoRepairable {

    /**
     * Map used to store the different types of drill upgrades
     * Used for handling tooltips
     */
    public static Map<Item, String> drillUpgrades = Util.make(new HashMap<>(), map -> {
        map.put(MythicItems.Mats.AQUARIUM_PEARL, "aquarium");
        map.put(MythicItems.Mats.CARMOT_STONE, "carmot");
        map.put(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM, "midas_gold");
        map.put(MythicItems.Mats.PROMETHEUM_BOUQUET, "prometheum");
        map.put(MythicItems.Mats.STORMYX_SHELL, "stormyx");
        map.put(Items.AIR, "empty");
    });

    public MythrilDrill(ToolMaterial material, float damage, float atkSpeed, Item.Settings settings) {
        super(material, MythicTags.MINEABLE_MYTHRIL_DRILL, damage, atkSpeed, settings);
    }

    @Override
    public boolean onStackClicked(ItemStack drill, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType == ClickType.RIGHT) {
            var drillComponent = drill.getOrDefault(MythicDataComponents.DRILL, DEFAULT);
            // If right-clicking Drill onto Morkite, try to fuel it
            if (slot.getStack().getItem().equals(MythicItems.Mats.MORKITE)) {
                int morkiteCount = slot.getStack().getCount();
                if (slot.tryTakeStackRange((MAX_FUEL - drillComponent.fuel()) / FUEL_CONSTANT, morkiteCount, player).isPresent()) {
                    int fuel = MathHelper.clamp(drillComponent.fuel() + (morkiteCount * FUEL_CONSTANT), 0, MAX_FUEL);
                    drill.set(MythicDataComponents.DRILL, new DrillComponent(fuel));
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean onClicked(ItemStack drill, ItemStack cursorStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            var cursorItem = cursorStack.getItem();
            // If right-clicking with Morkite on Drill, try to fuel it
            if (cursorItem.equals(MythicItems.Mats.MORKITE)) {
                var drillComponent = drill.getOrDefault(MythicDataComponents.DRILL, DEFAULT);

                // Don't bother interacting if the Drills fuel is full
                if (drillComponent.fuel() >= MAX_FUEL) return false;

                // Greedily take all the morkite if we can, otherwise calculate how much to take
                int morkiteCount = cursorStack.getCount();
                if (morkiteCount * FUEL_CONSTANT < (MAX_FUEL) - drillComponent.fuel()) {
                    int fuel = MathHelper.clamp(drillComponent.fuel() + (morkiteCount * FUEL_CONSTANT), 0, MAX_FUEL);
                    cursorStack.decrement(morkiteCount);
                    drill.set(MythicDataComponents.DRILL, new DrillComponent(fuel));
                    return true;
                }
                // Manually calculate how much Morkite to take
                if (morkiteCount * FUEL_CONSTANT >= (MAX_FUEL) - drillComponent.fuel()) {
                    int morkiteToTake = (MAX_FUEL / FUEL_CONSTANT) - (drillComponent.fuel() / FUEL_CONSTANT);
                    int fuel = MathHelper.clamp(drillComponent.fuel() + (morkiteToTake * FUEL_CONSTANT), 0, MAX_FUEL);
                    cursorStack.decrement(morkiteToTake);
                    drill.set(MythicDataComponents.DRILL, new DrillComponent(fuel));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (world.isClient() && miner instanceof PlayerEntity player) {
            player.sendMessage(Text.translatable("tooltip.mythril_drill.out_of_fuel"), true);
            player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), 0.8f, 0.5f);
        }

        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            var serverWorld = ((ServerWorld) world);
            // Randomly cancel damage while active
            var random = serverWorld.getRandom();
            var drillComponent = stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT);
            var upgradeComponent = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));

            if (!drillComponent.hasFuel()) {
                stack.damage(2, miner, EquipmentSlot.MAINHAND);
                return true;
            }

            if (random.nextInt(5) > 2) {
                stack.damage(1, miner, EquipmentSlot.MAINHAND);
            }
            stack.set(MythicDataComponents.DRILL, drillComponent.reduce(drillComponent.fuel()));

            if (state.isIn(ConventionalBlockTags.ORES)) {
                // Do not perform this if silk touch is present
                for (RegistryEntry<Enchantment> enchantment : stack.getEnchantments().getEnchantments()) {
                    if (enchantment.isIn(MythicTags.SILK_TOUCH_LIKE)) {
                        return true;
                    }
                }

                // Restore air when mining ores underwater
                if (upgradeComponent.hasUpgrade(MythicItems.Mats.AQUARIUM_PEARL)) {
                    miner.setAir(Math.min(miner.getAir() + 60, miner.getMaxAir()));
                }
                // Randomly drop gold from midas gold
                if (upgradeComponent.hasUpgrade(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem()) && random.nextInt(30) == 27) {
                    miner.dropItem(serverWorld, Items.RAW_GOLD);
                }
            }
        }

        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (stack.get(MythicDataComponents.DRILL) == null) return;
            if (stack.get(MythicDataComponents.UPGRADES) == null) return;
            var drillComponent = stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT);
            var upgradeComponent = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
            if (upgradeComponent.hasUpgrade(MythicItems.Mats.PROMETHEUM_BOUQUET)) {
                // Initialize auto repair upgrades
                if (!stack.contains(MythicDataComponents.PROMETHEUM)) {
                    stack.set(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
                }
                if (Math.floor(world.getTime() % 20) == 0.0) {
                    stack.set(MythicDataComponents.DRILL, drillComponent.increase(drillComponent.fuel()));
                }
                PrometheumComponent.tickAutoRepair(stack, world);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> lines, TooltipType type) {
        if (stack.contains(MythicDataComponents.DRILL)) {
            stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT).appendTooltip(context, lines::add, type);
        }
        if (stack.contains(MythicDataComponents.UPGRADES)) {
            var upgrades = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
            upgrades.appendTooltip(context, lines::add, type);
            for (int i = 0; i < upgrades.size(); i++) {
                var item = upgrades.items().get(i);
                lines.add(Text.translatable("tooltip.mythril_drill.upgrade_slot", i + 1, Text.translatable("tooltip.mythril_drill.upgrade." + drillUpgrades.get(item))));
            }
        }
    }

    @Override
    public boolean allowContinuingBlockBreaking(PlayerEntity player, ItemStack oldStack, ItemStack newStack) {
        // Allow you to break blocks when fuel ticks down
        return oldStack.contains(MythicDataComponents.DRILL) && newStack.contains(MythicDataComponents.DRILL) || oldStack.getDamage() != newStack.getDamage();
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        if (stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT).hasFuel()) {
            return super.getMiningSpeed(stack, state);
        }
        return 1.0f;
    }

    @Override
    public void postProcessComponents(ItemStack stack) {
        if (!stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) return;

        boolean changes = false;
        var attributes = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        assert attributes != null;
        var upgrades = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
        if (upgrades.hasUpgrade(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM)) {
            var modifier = new EntityAttributeModifier(
                RegistryHelper.id("mythril_drill_luck_bonus"),
                2.0,
                EntityAttributeModifier.Operation.ADD_VALUE
            );
            attributes = attributes.with(EntityAttributes.LUCK, modifier, AttributeModifierSlot.MAINHAND);
            changes = true;
        }
        if (upgrades.hasUpgrade(MythicItems.Mats.AQUARIUM_PEARL)) {
            var modifier = new EntityAttributeModifier(
                RegistryHelper.id("mythril_drill_underwater_mining_bonus"),
                3.0,
                EntityAttributeModifier.Operation.ADD_VALUE
            );
            attributes = attributes.with(EntityAttributes.SUBMERGED_MINING_SPEED, modifier, AttributeModifierSlot.MAINHAND);

            changes = true;
        }
        // Gives +1 level of efficiency
        for (var entry : stack.getEnchantments().getEnchantmentEntries()) {
            if (entry.getKey().matches(key -> key.equals(Enchantments.EFFICIENCY))) {
                int level = EnchantmentHelper.getLevel(entry.getKey(), stack);
                var modifier = new EntityAttributeModifier(
                    RegistryHelper.id("mythril_drill_speed_bonus"),
                    1 + (level * 2),
                    EntityAttributeModifier.Operation.ADD_VALUE
                );
                attributes = attributes.with(EntityAttributes.MINING_EFFICIENCY, modifier, AttributeModifierSlot.MAINHAND);
                changes = true;
            }
        }
        if (changes) {
            stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, attributes);
        }
    }
}
