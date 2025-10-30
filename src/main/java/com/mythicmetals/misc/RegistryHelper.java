package com.mythicmetals.misc;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import java.util.function.UnaryOperator;

/**
 * A helper class containing methods for registering various blocks and items.
 *
 * @author Noaaan
 */
public class RegistryHelper {

    public static Identifier id(String path) {
        return Identifier.of(MythicMetals.MOD_ID, path);
    }

    public static void item(String path, Item item) {
        Registry.register(Registries.ITEM, id(path), item);
    }

    public static void block(String path, Block block) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(MythicMetals.TABBED_GROUP).registryKey(itemKey(path)).tab(1)));
    }

    public static void block(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(MythicMetals.TABBED_GROUP).registryKey(itemKey(path)).tab(1).fireproof()));
        } else {
            block(path, block);
        }
    }

    public static void block(String path, Block block, boolean fireproof, boolean uncommon) {
        if (uncommon) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().group(MythicMetals.TABBED_GROUP).registryKey(itemKey(path)).tab(1).rarity(Rarity.UNCOMMON)));
        } else {
            block(path, block, fireproof);
        }
    }

    public static void block(String path, Block block, OwoItemGroup group) {
        Registry.register(Registries.BLOCK, id(path), block);
        Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().registryKey(itemKey(path)).group(group)));
    }

    public static void block(String path, Block block, OwoItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(Registries.BLOCK, id(path), block);
            Registry.register(Registries.ITEM, id(path), new BlockItem(block, new Item.Settings().registryKey(itemKey(path)).group(group).fireproof()));
        } else {
            block(path, block, group);
        }
    }

    public static void blockOnly(String path, Block block) {
        Registry.register(Registries.BLOCK, id(path), block);
    }

    public static void entityType(String path, EntityType<?> type) {
        Registry.register(Registries.ENTITY_TYPE, id(path), type);
    }

    public static RegistryKey<EntityType<?>> entityType(String entityType) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, id(entityType));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String path) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, RegistryHelper.id(path));
    }

    public static LootConditionType lootConditionType(String path, MapCodec<? extends LootCondition> lootCodec) {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, RegistryHelper.id(path), new LootConditionType(lootCodec));
    }

    public static void blockEntity(String path, BlockEntityType<?> type) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, RegistryHelper.id(path), type);
    }

    public static RegistryEntry<EntityAttribute> entityAttribute(String path, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, id(path), attribute);
    }

    public static RegistryEntry<StatusEffect> getEntry(StatusEffect effect) {
        return Registries.STATUS_EFFECT.getEntry(effect);
    }

    public static RegistryEntry<SoundEvent> getEntry(SoundEvent soundEvent) {
        return Registries.SOUND_EVENT.getEntry(soundEvent);
    }

    public static RegistryKey<Item> itemKey(String item) {
        return RegistryKey.of(RegistryKeys.ITEM, id(item));
    }

    public static RegistryKey<EquipmentAsset> equipmentAsset(String equipmentasset) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, id(equipmentasset));
    }

    public static RegistryEntry<Potion> getEntry(Potion potion) {
        return Registries.POTION.getEntry(potion);
    }

    public static <T> ComponentType<T> dataComponentType(String path, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id(path), builderOperator.apply(ComponentType.builder()).build());
    }

    public static RegistryEntry<Potion> potion(String name, StatusEffectInstance statusEffectInstance) {
        return Registry.registerReference(Registries.POTION, id(name), new Potion(name, statusEffectInstance));
    }

    public static RegistryKey<Recipe<?>> recipeKey(String item) {
        return RegistryKey.of(RegistryKeys.RECIPE, id(item));
    }

    public static RegistryKey<Block> blockKey(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, id(name));
    }
}
