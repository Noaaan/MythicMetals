package nourl.mythicmetals.item;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.MythicParticleSystem;
import nourl.mythicmetals.misc.RegistryHelper;

import java.lang.reflect.Field;
import java.util.List;

@SuppressWarnings("unused")
public class MythicItems implements SimpleFieldProcessingSubject<ItemSet> {

    public static final ItemSet ADAMANTITE = new ItemSet();
    public static final ItemSet AQUARIUM = new ItemSet();
    public static final ItemSet BANGLUM = new ItemSet();
    public static final ItemSet BRONZE = new ItemSet(true);
    public static final ItemSet CARMOT = new ItemSet();
    public static final ItemSet CELESTIUM = new ItemSet(true, settings -> settings.rarity(Rarity.RARE));
    public static final ItemSet DURASTEEL = new ItemSet(true);
    public static final ItemSet HALLOWED = new ItemSet(true, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ItemSet KYBER = new ItemSet();
    public static final ItemSet MANGANESE = new ItemSet();
    public static final ItemSet METALLURGIUM = new ItemSet(true, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ItemSet MIDAS_GOLD = new ItemSet();
    public static final ItemSet MYTHRIL = new ItemSet();
    public static final ItemSet ORICHALCUM = new ItemSet();
    public static final ItemSet OSMIUM = new ItemSet();
    public static final ItemSet PALLADIUM = new ItemSet(false, Item.Settings::fireproof);
    public static final ItemSet PLATINUM = new ItemSet();
    public static final ItemSet PROMETHEUM = new ItemSet();
    public static final ItemSet QUADRILLUM = new ItemSet();
    public static final ItemSet RUNITE = new ItemSet();
    public static final ItemSet SILVER = new ItemSet();
    public static final ItemSet STAR_PLATINUM = new ItemSet(true);
    public static final ItemSet STEEL = new ItemSet(true);
    public static final ItemSet STORMYX = new ItemSet();
    public static final ItemSet TIN = new ItemSet();

    @Override
    public void processField(ItemSet value, String name, Field field) {
        value.register(name, value.equals(STAR_PLATINUM));
    }

    @Override
    public Class<ItemSet> getTargetFieldType() {
        return ItemSet.class;
    }

    public static class Mats implements ItemRegistryContainer {
        public static final Item MORKITE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP));
        public static final Item STARRITE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
        public static final Item UNOBTAINIUM = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON).fireproof());
        public static final Item AQUARIUM_PEARL = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
        public static final Item BANGLUM_CHUNK = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
        public static final Item DURASTEEL_ENGINE = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
        public static final Item STORMYX_SHELL = new Item(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).rarity(Rarity.UNCOMMON));
    }

    public static class ParticleSticks implements ItemRegistryContainer {
        public static final Item COMBUSTION_STICK = new ParticleStick(new OwoItemSettings(), MythicParticleSystem.COMBUSTION_EXPLOSION);
        public static final Item SPARK_STICK = new ParticleStick(new OwoItemSettings(), MythicParticleSystem.COPPER_SPARK);
        public static final Item HEART_STICK = new ParticleStick(new OwoItemSettings(), MythicParticleSystem.HEALING_HEARTS);
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

    public static class Templates implements ItemRegistryContainer {
        public static final Item UNOBTAINIUM_SMITHING_TEMPLATE = new SmithingTemplateItem(
                Text.translatable("smithing_template.mythicmetals.unobtainium.applies_to").formatted(Formatting.BLUE),
                Text.translatable("smithing_template.mythicmetals.unobtainium.ingredients").formatted(Formatting.BLUE),
                Text.translatable("smithing_template.mythicmetals.unobtainium.title").formatted(Formatting.GRAY),
                Text.translatable("smithing_template.mythicmetals.unobtainium.base_slot_description"),
                Text.translatable("smithing_template.mythicmetals.unobtainium.additions_slot_description"),
                SmithingTemplateItem.getNetheriteUpgradeEmptyBaseSlotTextures(),
                SmithingTemplateItem.getNetheriteUpgradeEmptyAdditionsSlotTextures()
        );

        public static final Item MYTHRIL_DRILL_SMITHING_TEMPLATE = new SmithingTemplateItem(
                Text.translatable("smithing_template.mythicmetals.mythril_drill.applies_to").formatted(Formatting.BLUE),
                Text.translatable("smithing_template.mythicmetals.mythril_drill.ingredients").formatted(Formatting.BLUE),
                Text.translatable("smithing_template.mythicmetals.mythril_drill.title").formatted(Formatting.GRAY),
                Text.translatable("smithing_template.mythicmetals.mythril_drill.base_slot_description"),
                Text.translatable("smithing_template.mythicmetals.mythril_drill.additions_slot_description"),
                List.of(RegistryHelper.id("item/template/empty_slot_mythril_pick")),
                List.of(RegistryHelper.id("item/template/empty_slot_engine"))
        );

        public static final Item MIDAS_FOLDING_TEMPLATE = new SmithingTemplateItem(
                Text.translatable("smithing_template.mythicmetals.midas_folding.applies_to").formatted(Formatting.GOLD),
                Text.translatable("smithing_template.mythicmetals.midas_folding.ingredients").formatted(Formatting.GOLD),
                Text.translatable("smithing_template.mythicmetals.midas_folding.title").formatted(Formatting.GRAY),
                Text.translatable("smithing_template.mythicmetals.midas_folding.base_slot_description"),
                Text.translatable("smithing_template.mythicmetals.midas_folding.additions_slot_description"),
                List.of(RegistryHelper.id("item/template/empty_slot_mythril_pick")),
                List.of(RegistryHelper.id("item/template/empty_slot_engine"))
        );

        public static final Item ROYAL_MIDAS_SMITHING_TEMPLATE = new SmithingTemplateItem(
                Text.translatable("smithing_template.mythicmetals.royal_midas.applies_to").formatted(Formatting.GOLD),
                Text.translatable("smithing_template.mythicmetals.royal_midas.ingredients").formatted(Formatting.GOLD),
                Text.translatable("smithing_template.mythicmetals.royal_midas.title").formatted(Formatting.GRAY),
                Text.translatable("smithing_template.mythicmetals.royal_midas.base_slot_description"),
                Text.translatable("smithing_template.mythicmetals.royal_midas.additions_slot_description"),
                List.of(RegistryHelper.id("item/template/empty_slot_mythril_pick")),
                List.of(RegistryHelper.id("item/template/empty_slot_engine"))
        );
    }

}
