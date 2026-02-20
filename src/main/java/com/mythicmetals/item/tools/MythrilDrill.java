package com.mythicmetals.item.tools;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.component.*;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Math;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

import static com.mythicmetals.component.DrillComponent.*;

public class MythrilDrill extends Item implements AutoRepairable {

    /**
     * Map used to store the different types of drill upgrades
     * Used for handling tooltips
     */
    public static Map<Item, String> drillUpgrades = Util.make(new HashMap<>(), map -> {
        map.put(MythicItems.Mats.AQUARIUM_PEARL, "aquarium");
        map.put(MythicItems.Mats.CARMOT_STONE, "carmot");
        map.put(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM, "midas_gold");
        map.put(MythicItems.Mats.PROMETHEUM_ROSE, "prometheum");
        map.put(MythicItems.Mats.STORMYX_SHELL, "stormyx");
        map.put(Items.AIR, "empty");
    });

    public MythrilDrill(ToolMaterial material, float damage, float atkSpeed, Item.Properties settings) {
        super(material.applyToolProperties(settings, MythicTags.MINEABLE_MYTHRIL_DRILL, damage, atkSpeed, 0));
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack drill, Slot slot, ClickAction clickType, Player player) {
        if (clickType == ClickAction.SECONDARY) {
            var drillComponent = drill.getOrDefault(MythicDataComponents.DRILL, DEFAULT);
            // If right-clicking Drill onto Morkite, try to fuel it
            if (slot.getItem().getItem().equals(MythicItems.Mats.MORKITE)) {
                int morkiteCount = slot.getItem().getCount();
                if (slot.tryRemove((MAX_FUEL - drillComponent.fuel()) / FUEL_CONSTANT, morkiteCount, player).isPresent()) {
                    int fuel = Mth.clamp(drillComponent.fuel() + (morkiteCount * FUEL_CONSTANT), 0, MAX_FUEL);
                    drill.set(MythicDataComponents.DRILL, new DrillComponent(fuel));
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack drill, ItemStack cursorStack, Slot slot, ClickAction clickType, Player player, SlotAccess cursorStackReference) {
        if (clickType == ClickAction.SECONDARY) {
            var cursorItem = cursorStack.getItem();
            // If right-clicking with Morkite on Drill, try to fuel it
            if (cursorItem.equals(MythicItems.Mats.MORKITE)) {
                var drillComponent = drill.getOrDefault(MythicDataComponents.DRILL, DEFAULT);

                // Don't bother interacting if the Drills fuel is full
                if (drillComponent.fuel() >= MAX_FUEL) return false;

                // Greedily take all the morkite if we can, otherwise calculate how much to take
                int morkiteCount = cursorStack.getCount();
                if (morkiteCount * FUEL_CONSTANT < (MAX_FUEL) - drillComponent.fuel()) {
                    int fuel = Mth.clamp(drillComponent.fuel() + (morkiteCount * FUEL_CONSTANT), 0, MAX_FUEL);
                    cursorStack.shrink(morkiteCount);
                    drill.set(MythicDataComponents.DRILL, new DrillComponent(fuel));
                    return true;
                }
                // Manually calculate how much Morkite to take
                if (morkiteCount * FUEL_CONSTANT >= (MAX_FUEL) - drillComponent.fuel()) {
                    int morkiteToTake = (MAX_FUEL / FUEL_CONSTANT) - (drillComponent.fuel() / FUEL_CONSTANT);
                    int fuel = Mth.clamp(drillComponent.fuel() + (morkiteToTake * FUEL_CONSTANT), 0, MAX_FUEL);
                    cursorStack.shrink(morkiteToTake);
                    drill.set(MythicDataComponents.DRILL, new DrillComponent(fuel));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (world.isClientSide() && miner instanceof Player player) {
            player.displayClientMessage(Component.translatable("tooltip.mythril_drill.out_of_fuel"), true);
            player.playSound(SoundEvents.NOTE_BLOCK_BASS.value(), 0.8f, 0.5f);
        }

        if (!world.isClientSide() && state.getDestroySpeed(world, pos) != 0.0F) {
            var serverWorld = ((ServerLevel) world);
            // Randomly cancel damage while active
            var random = serverWorld.getRandom();
            var drillComponent = stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT);
            var upgradeComponent = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));

            if (!drillComponent.hasFuel()) {
                stack.hurtAndBreak(2, miner, EquipmentSlot.MAINHAND);
                return true;
            }

            if (random.nextInt(5) > 2) {
                stack.hurtAndBreak(1, miner, EquipmentSlot.MAINHAND);
            }
            stack.set(MythicDataComponents.DRILL, drillComponent.reduce(drillComponent.fuel()));

            if (state.is(ConventionalBlockTags.ORES)) {
                // Do not perform this if silk touch is present
                for (Holder<Enchantment> enchantment : stack.getEnchantments().keySet()) {
                    if (enchantment.is(MythicTags.SILK_TOUCH_LIKE)) {
                        return true;
                    }
                }

                // Restore air when mining ores underwater
                if (upgradeComponent.hasUpgrade(MythicItems.Mats.AQUARIUM_PEARL)) {
                    miner.setAirSupply(Math.min(miner.getAirSupply() + 60, miner.getMaxAirSupply()));
                }
                // Randomly drop gold from midas gold
                if (upgradeComponent.hasUpgrade(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem()) && random.nextInt(30) == 27) {
                    miner.spawnAtLocation(serverWorld, Items.RAW_GOLD);
                }
            }
        }

        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (stack.get(MythicDataComponents.DRILL) == null) return;
        if (stack.get(MythicDataComponents.UPGRADES) == null) return;
        var drillComponent = stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT);
        var upgradeComponent = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
        if (upgradeComponent.hasUpgrade(MythicItems.Mats.PROMETHEUM_ROSE)) {
            // Initialize auto repair upgrades
            if (!stack.has(MythicDataComponents.PROMETHEUM)) {
                stack.set(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
            }
            if (Math.floor(serverLevel.getGameTime() % 20) == 0.0) {
                stack.set(MythicDataComponents.DRILL, drillComponent.increase(drillComponent.fuel()));
            }
            PrometheumComponent.tickAutoRepair(stack, serverLevel);
        }
        super.inventoryTick(stack, serverLevel, entity, equipmentSlot);
    }
//   FIXME - Figure out new tooltips
//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> lines, TooltipFlag type) {
//        if (stack.has(MythicDataComponents.DRILL)) {
//            stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT).addToTooltip(context, lines::add, type);
//        }
//        if (stack.has(MythicDataComponents.UPGRADES)) {
//            var upgrades = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
//            upgrades.addToTooltip(context, lines::add, type);
//            for (int i = 0; i < upgrades.size(); i++) {
//                var item = upgrades.items().get(i);
//                lines.add(Component.translatable("tooltip.mythril_drill.upgrade_slot", i + 1, Component.translatable("tooltip.mythril_drill.upgrade." + drillUpgrades.get(item))));
//            }
//        }
//    }

    @Override
    public boolean allowContinuingBlockBreaking(Player player, ItemStack oldStack, ItemStack newStack) {
        // Allow you to break blocks when fuel ticks down
        return oldStack.has(MythicDataComponents.DRILL) && newStack.has(MythicDataComponents.DRILL) || oldStack.getDamageValue() != newStack.getDamageValue();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (stack.getOrDefault(MythicDataComponents.DRILL, DEFAULT).hasFuel()) {
            return super.getDestroySpeed(stack, state);
        }
        return 1.0f;
    }

    @Override
    public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
        super.deriveStackComponents(source, target);
    }

    // FIXME - Migrate to derive stack components
//    @Override
//    public void verifyComponentsAfterLoad(ItemStack stack) {
//        if (!stack.has(DataComponents.ATTRIBUTE_MODIFIERS)) return;
//
//        boolean changes = false;
//        var attributes = stack.get(DataComponents.ATTRIBUTE_MODIFIERS);
//        assert attributes != null;
//        var upgrades = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
//        if (upgrades.hasUpgrade(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK_ITEM)) {
//            var modifier = new AttributeModifier(
//                RegistryHelper.id("mythril_drill_luck_bonus"),
//                2.0,
//                AttributeModifier.Operation.ADD_VALUE
//            );
//            attributes = attributes.withModifierAdded(Attributes.LUCK, modifier, EquipmentSlotGroup.MAINHAND);
//            changes = true;
//        }
//        if (upgrades.hasUpgrade(MythicItems.Mats.AQUARIUM_PEARL)) {
//            var modifier = new AttributeModifier(
//                RegistryHelper.id("mythril_drill_underwater_mining_bonus"),
//                3.0,
//                AttributeModifier.Operation.ADD_VALUE
//            );
//            attributes = attributes.withModifierAdded(Attributes.SUBMERGED_MINING_SPEED, modifier, EquipmentSlotGroup.MAINHAND);
//
//            changes = true;
//        }
//        // Gives +1 level of efficiency
//        for (var entry : stack.getEnchantments().entrySet()) {
//            if (entry.getKey().is(key -> key.equals(Enchantments.EFFICIENCY))) {
//                int level = EnchantmentHelper.getItemEnchantmentLevel(entry.getKey(), stack);
//                var modifier = new AttributeModifier(
//                    RegistryHelper.id("mythril_drill_speed_bonus"),
//                    1 + (level * 2),
//                    AttributeModifier.Operation.ADD_VALUE
//                );
//                attributes = attributes.withModifierAdded(Attributes.MINING_EFFICIENCY, modifier, EquipmentSlotGroup.MAINHAND);
//                changes = true;
//            }
//        }
//        if (changes) {
//            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, attributes);
//        }
//    }
}
