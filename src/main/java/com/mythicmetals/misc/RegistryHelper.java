package com.mythicmetals.misc;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.MythicMetals;
import io.wispforest.endec.StructEndec;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import java.util.function.UnaryOperator;

/**
 * A helper class containing methods for registering various blocks and items.
 *
 * @author Noaaan
 */
public class RegistryHelper {

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MythicMetals.MOD_ID, path);
    }

    public static void item(String path, Item item) {
        Registry.register(BuiltInRegistries.ITEM, id(path), item);
    }

    public static void block(String path, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, id(path), block);
        Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(MythicMetals.TABBED_GROUP).setId(itemKey(path)).tab(1)));
    }

    public static void block(String path, Block block, boolean fireproof) {
        if (fireproof) {
            Registry.register(BuiltInRegistries.BLOCK, id(path), block);
            Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(MythicMetals.TABBED_GROUP).setId(itemKey(path)).tab(1).fireResistant()));
        } else {
            block(path, block);
        }
    }

    public static void block(String path, Block block, boolean fireproof, boolean uncommon) {
        if (uncommon) {
            Registry.register(BuiltInRegistries.BLOCK, id(path), block);
            Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().group(MythicMetals.TABBED_GROUP).setId(itemKey(path)).tab(1).rarity(Rarity.UNCOMMON)));
        } else {
            block(path, block, fireproof);
        }
    }

    public static void block(String path, Block block, OwoItemGroup group) {
        Registry.register(BuiltInRegistries.BLOCK, id(path), block);
        Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().setId(itemKey(path)).group(group)));
    }

    public static void block(String path, Block block, OwoItemGroup group, boolean fireproof) {
        if (fireproof) {
            Registry.register(BuiltInRegistries.BLOCK, id(path), block);
            Registry.register(BuiltInRegistries.ITEM, id(path), new BlockItem(block, new Item.Properties().setId(itemKey(path)).group(group).fireResistant()));
        } else {
            block(path, block, group);
        }
    }

    public static void blockOnly(String path, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, id(path), block);
    }

    public static void entityType(ResourceKey<EntityType<?>> key, EntityType<?> type) {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    public static ResourceKey<EntityType<?>> entityTypeKey(String entityType) {
        return ResourceKey.create(Registries.ENTITY_TYPE, id(entityType));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, RegistryHelper.id(path));
    }

    public static LootItemConditionType lootConditionType(String path, MapCodec<? extends LootItemCondition> lootCodec) {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, RegistryHelper.id(path), new LootItemConditionType(lootCodec));
    }

    public static void blockEntity(String path, BlockEntityType<?> type) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, RegistryHelper.id(path), type);
    }

    public static Holder<Attribute> entityAttribute(String path, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, id(path), attribute);
    }

    public static Holder<MobEffect> getEntry(MobEffect effect) {
        return BuiltInRegistries.MOB_EFFECT.wrapAsHolder(effect);
    }

    public static Holder<SoundEvent> getEntry(SoundEvent soundEvent) {
        return BuiltInRegistries.SOUND_EVENT.wrapAsHolder(soundEvent);
    }

    public static ResourceKey<Item> itemKey(String item) {
        return ResourceKey.create(Registries.ITEM, id(item));
    }

    public static ResourceKey<EquipmentAsset> equipmentAsset(String equipmentasset) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, id(equipmentasset));
    }

    public static Holder<Potion> getEntry(Potion potion) {
        return BuiltInRegistries.POTION.wrapAsHolder(potion);
    }

    public static <T> DataComponentType<T> dataComponentType(String path, StructEndec<T> endec) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id(path), DataComponentType.<T>builder()
                .persistent(CodecUtils.toCodec(endec))
                .networkSynchronized(CodecUtils.toPacketCodec(endec))
            .build()
        );
    }

    public static <T> DataComponentType<T> dataComponentType(String path, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id(path), builderOperator.apply(DataComponentType.builder()).build());
    }

    public static Holder<Potion> potion(String name, MobEffectInstance statusEffectInstance) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, id(name), new Potion(name, statusEffectInstance));
    }

    public static ResourceKey<Recipe<?>> recipeKey(String item) {
        return ResourceKey.create(Registries.RECIPE, id(item));
    }

    public static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, id(name));
    }

    public static TagKey<Item> itemTag(String path) {
        return TagKey.create(Registries.ITEM, id(path));
    }
}
