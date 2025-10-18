package com.mythicmetals.item;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.*;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.*;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

@SuppressWarnings("unused")
public class MythicItems implements SimpleFieldProcessingSubject<ItemSet> {

    public static final ItemSet ADAMANTITE = new ItemSet("adamantite", 1.5f, true);
    public static final ItemSet AQUARIUM = new ItemSet("aquarium", 0.7f);
    public static final ItemSet BANGLUM = new ItemSet("banglum", 0.7f);
    public static final ItemSet BRONZE = new ItemSet("bronze", true);
    public static final ItemSet CARMOT = new ItemSet("carmot", 1.2f);
    public static final ItemSet CELESTIUM = new ItemSet("celestium", true, true, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ItemSet DURASTEEL = new ItemSet("durasteel", true);
    public static final ItemSet HALLOWED = new ItemSet("hallowed", true, true, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ItemSet KYBER = new ItemSet("kyber", 0.7f);
    public static final ItemSet MANGANESE = new ItemSet("manganese", 0.2f);
    public static final ItemSet METALLURGIUM = new ItemSet("metallurgium", true, true, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ItemSet MIDAS_GOLD = new ItemSet("midas_gold", 1.0f);
    public static final ItemSet MYTHRIL = new ItemSet("mythril", 1.5f, true);
    public static final ItemSet ORICHALCUM = new ItemSet("orichalcum", 1.5f, true);
    public static final ItemSet OSMIUM = new ItemSet("osmium", 0.8f);
    public static final ItemSet PALLADIUM = new ItemSet("palladium", false, true, 1.5f, Item.Settings::fireproof);
    public static final ItemSet PLATINUM = new ItemSet("platinum", 0.7f);
    public static final ItemSet PROMETHEUM = new ItemSet("prometheum", 0.7f);
    public static final ItemSet QUADRILLUM = new ItemSet("quadrillum", 0.7f);
    public static final ItemSet RUNITE = new ItemSet("runite", 1.0f, true);
    public static final ItemSet SILVER = new ItemSet("silver", 0.7f);
    public static final ItemSet STAR_PLATINUM = new ItemSet("star_platinum", true);
    public static final ItemSet STEEL = new ItemSet("steel", true);
    public static final ItemSet STORMYX = new ItemSet("stormyx", 1.0f, true);
    public static final ItemSet TIN = new ItemSet("tin", 0.2f);

    @Override
    public void processField(ItemSet value, String name, Field field) {
        value.register(name, value.equals(STAR_PLATINUM));
    }

    @Override
    public Class<ItemSet> getTargetFieldType() {
        return ItemSet.class;
    }

    public static class Mats implements SimpleFieldProcessingSubject<Item> {
        public static final Item AQUARIUM_PEARL = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("aquarium_pearl")));
        public static final Item BANGLUM_CHUNK = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("banglum_chunk")));
        public static final Item CARMOT_STONE = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("carmot_stone")));
        public static final Item PROMETHEUM_BOUQUET = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("prometheum_bouquet")));
        public static final Item DURASTEEL_ENGINE = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("durasteel_engine")));
        public static final Item MORKITE = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).registryKey(itemKey("morkite")));
        public static final Item STARRITE = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("starrite")));
        public static final Item STORMYX_SHELL = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).registryKey(itemKey("stormyx_shell")));
        public static final Item UNOBTAINIUM = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).fireproof().registryKey(itemKey("unobtainium")));

        @Override
        public void processField(Item value, String identifier, Field field) {
            RegistryHelper.item(identifier, value);
        }

        @Override
        public Class<Item> getTargetFieldType() {
            return Item.class;
        }
    }

    public static class Copper implements SimpleFieldProcessingSubject<CopperSet> {
        public static final CopperSet COPPER = new CopperSet();

        @Override
        public void processField(CopperSet value, String name, Field field) {
            value.register(name);
        }

        @Override
        public Class<CopperSet> getTargetFieldType() {
            return CopperSet.class;
        }
    }

    public static class Templates {
        public static final List<Identifier> UNOBTAINIUM_ALLOY_ITEMS = Util.make(new ArrayList<>(SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures()),
            identifiers -> identifiers.add(RegistryHelper.id("item/template/empty_slot_elytra")));

        public static final List<Identifier> ARMOR_ITEMS = Util.make(new ArrayList<>(),
            identifiers -> {
                identifiers.add(Identifier.of("item/empty_armor_slot_helmet"));
                identifiers.add(Identifier.of("item/empty_armor_slot_chestplate"));
                identifiers.add(Identifier.of("item/empty_armor_slot_leggings"));
                identifiers.add(Identifier.of("item/empty_armor_slot_boots"));
            });

        public static final Item UNOBTAINIUM_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.unobtainium.applies_to").formatted(Formatting.BLUE),
            Text.translatable("smithing_template.mythicmetals.unobtainium.ingredients").formatted(Formatting.BLUE),
            Text.translatable("smithing_template.mythicmetals.unobtainium.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.unobtainium.additions_slot_description"),
            UNOBTAINIUM_ALLOY_ITEMS,
            SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
            "unobtainium_smithing_template"
        );

        public static final Item MYTHRIL_DRILL_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.mythril_drill.applies_to").formatted(Formatting.BLUE),
            Text.translatable("smithing_template.mythicmetals.mythril_drill.ingredients").formatted(Formatting.BLUE),
            Text.translatable("smithing_template.mythicmetals.mythril_drill.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.mythril_drill.additions_slot_description"),
            List.of(RegistryHelper.id("item/template/empty_slot_mythril_pick")),
            List.of(RegistryHelper.id("item/template/empty_slot_engine")),
            "mythril_drill_smithing_template"
        );

        public static final Item MIDAS_FOLDING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.midas_folding.applies_to").formatted(Formatting.GOLD),
            Text.translatable("smithing_template.mythicmetals.midas_folding.ingredients").formatted(Formatting.GOLD),
            Text.translatable("smithing_template.mythicmetals.midas_folding.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.midas_folding.additions_slot_description"),
            List.of(
                RegistryHelper.id("item/template/empty_slot_midas_dagger"),
                RegistryHelper.id("item/template/empty_slot_midas"),
                RegistryHelper.id("item/template/empty_slot_gilded_midas"),
                RegistryHelper.id("item/template/empty_slot_royal_midas")
            ),
            List.of(RegistryHelper.id("item/template/empty_slot_block")),
            "midas_folding_template"
        );

        public static final Item ROYAL_MIDAS_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.royal_midas.applies_to").formatted(Formatting.GOLD),
            Text.translatable("smithing_template.mythicmetals.royal_midas.ingredients").formatted(Formatting.GOLD),
            Text.translatable("smithing_template.mythicmetals.royal_midas.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.royal_midas.additions_slot_description"),
            List.of(RegistryHelper.id("item/template/empty_slot_gilded_midas")),
            List.of(RegistryHelper.id("item/template/empty_slot_block")),
            "royal_midas_smithing_template"
        );

        public static final Item AEGIS_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.aegis.applies_to").formatted(Formatting.BLUE),
            Text.translatable("smithing_template.mythicmetals.aegis.ingredients").formatted(Formatting.BLUE),
            Text.translatable("smithing_template.mythicmetals.aegis.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.aegis.additions_slot_description"),
            List.of(RegistryHelper.id("item/template/empty_slot_hallowed_sword"), RegistryHelper.id("item/template/empty_slot_palladium_sword")),
            SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
            "aegis_template"
        );

        public static final Item CARMOT_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.carmot.applies_to").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.KYBER.rgb())),
            Text.translatable("smithing_template.mythicmetals.carmot.ingredients").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb())),
            Text.translatable("smithing_template.mythicmetals.carmot.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.carmot.additions_slot_description"),
            SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
            SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
            "carmot_smithing_template"
        );

        public static final Item OSMIUM_CHAINMAIL_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.osmium.applies_to").setStyle(Style.EMPTY.withColor(Formatting.GRAY)),
            Text.translatable("smithing_template.mythicmetals.osmium.ingredients").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.OSMIUM.rgb())),
            Text.translatable("smithing_template.mythicmetals.osmium.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.osmium.additions_slot_description"),
            ARMOR_ITEMS,
            SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures(),
            "osmium_chainmail_smtihing_template"
        );

        public static final Item TIDESINGER_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.tidesinger.applies_to").setStyle(UsefulSingletonForColorUtil.MetalColors.AQUA_STYLE),
            Text.translatable("smithing_template.mythicmetals.tidesinger.ingredients").setStyle(UsefulSingletonForColorUtil.MetalColors.BUBBLE.style()),
            Text.translatable("smithing_template.mythicmetals.tidesinger.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.tidesinger.additions_slot_description"),
            SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
            List.of(
                RegistryHelper.id("item/template/empty_slot_brain"),
                RegistryHelper.id("item/template/empty_slot_bubble"),
                RegistryHelper.id("item/template/empty_slot_fire"),
                RegistryHelper.id("item/template/empty_slot_horn"),
                RegistryHelper.id("item/template/empty_slot_tube")
            ),
            "tidesinger_smithing_template"
        );

        public static final Item LEGENDARY_BANGLUM_SMITHING_TEMPLATE = createSmithingTemplate(
            Text.translatable("smithing_template.mythicmetals.legendary_banglum.applies_to").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.BANGLUM.rgb())),
            Text.translatable("smithing_template.mythicmetals.legendary_banglum.ingredients").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.BANGLUM.rgb())),
            Text.translatable("smithing_template.mythicmetals.legendary_banglum.base_slot_description"),
            Text.translatable("smithing_template.mythicmetals.legendary_banglum.additions_slot_description"),
            SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
            List.of(RegistryHelper.id("item/template/empty_slot_chunk")),
            "legendary_banglum_smithing_template"
        );


        public static void init() {}
    }

    private static SmithingTemplateItem createSmithingTemplate(Text appliesTo, Text ingredients, Text description, Text additionalDesc, List<Identifier> baseSlotTextures, List<Identifier> additionsTextures, String id) {
        var key = RegistryKey.of(RegistryKeys.ITEM, RegistryHelper.id(id));
        var template = new SmithingTemplateItem(
            appliesTo,
            ingredients,
            description,
            additionalDesc,
            baseSlotTextures,
            additionsTextures,
            new Item.Settings()
                .registryKey(key)
        );
        return Registry.register(Registries.ITEM, key, template);
    }


}
