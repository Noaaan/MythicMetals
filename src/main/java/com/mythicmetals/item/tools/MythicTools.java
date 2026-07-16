package com.mythicmetals.item.tools;

import com.mythicmetals.item.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.api.v2.ToolSet.AttackSpeeds;
import com.mythicmetals.item.component.DrillComponent;
import com.mythicmetals.item.component.GoldFoldedComponent;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.UpgradeComponent;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.item.RuniteArrowItem;
import com.mythicmetals.item.StarPlatinumArrowItem;
import com.mythicmetals.item.TippedRuniteArrowItem;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.mythicmetals.api.v2.ToolSet.defaultSettings;
import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class MythicTools {

    private MythicTools() {
    }

    public static final Map<String, ToolSet> TOOL_MAP = new HashMap<>();

    public static final Item RED_AEGIS_SWORD = new Item(
        defaultSettings(MythicToolMaterials.AEGIS_RED)
            .attributes(
                MythicItemAttributes.createToolModifier(
                    "aegis",
                    MythicItemAttributes.ToolType.SWORD,
                    AttackSpeeds.SLOWER,
                    MythicToolMaterials.AEGIS_RED,
                    List.of()
                )
            )
            .component(MythicDataComponents.FIRE_ASPECT, 320)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .setId(RegistryHelper.itemKey("red_aegis_sword"))
    );

    public static final Item WHITE_AEGIS_SWORD = new Item(
        defaultSettings(MythicToolMaterials.AEGIS_WHITE)
            .attributes(
                MythicItemAttributes.createToolModifier(
                    "aegis",
                    MythicItemAttributes.ToolType.SWORD,
                    AttackSpeeds.SLOWER,
                    MythicToolMaterials.AEGIS_RED,
                    List.of(new MythicAttributeModifier(MythicEntityAttributes.UNDEAD_BONUS_DAMAGE, 8.0, Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND))
                )
            )
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .setId(RegistryHelper.itemKey("white_aegis_sword"))
    );

    public static final Item ORICHALCUM_HAMMER = new HammerBase(MythicToolMaterials.ORICHALCUM, 6, -3.2f, new Item.Properties()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .setId(RegistryHelper.itemKey("orichalcum_hammer")),
        1
    );

    public static final Item MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD,
        new Item.Properties()
            .setId(RegistryHelper.itemKey("midas_gold_sword"))
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0))
    );

    public static final Item GILDED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.GILDED_MIDAS_GOLD,
        new Item.Properties()
            .setId(RegistryHelper.itemKey("gilded_midas_gold_sword"))
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0))
    );

    public static final Item ROYAL_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD,
        new Item.Properties()
            .setId(RegistryHelper.itemKey("royal_midas_gold_sword"))
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0, true))
    );

    public static final Item RUNITE_ARROW = new RuniteArrowItem(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(itemKey("runite_arrow")));
    public static final Item TIPPED_RUNITE_ARROW = new TippedRuniteArrowItem(new Item.Properties()
        .setId(itemKey("tipped_runite_arrow"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .stackGenerator((item, stacks) -> {
            for (Potion potion : BuiltInRegistries.POTION) {
                var stack = PotionContents.createItemStack(item, RegistryHelper.getEntry(potion));
                if (!potion.getEffects().isEmpty()) {
                    stacks.accept(stack);
                }
            }
        })
        .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
    );

    public static final Item STAR_PLATINUM_ARROW = new StarPlatinumArrowItem(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(itemKey("star_platinum_arrow")));
    public static final Item STORMYX_SHIELD = new StormyxShield(new Item.Properties()
        .setId(RegistryHelper.itemKey("stormyx_shield"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .durability(1680)
        .rarity(Rarity.UNCOMMON)
        .attributes(StormyxShield.createStormyxShieldAttributes())
    );
    public static final Item MYTHRIL_DRILL = new MythrilDrill(MythicToolMaterials.MYTHRIL_DRILL, 3, 1.5f, new Item.Properties()
        .setId(RegistryHelper.itemKey("mythril_drill"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .rarity(Rarity.UNCOMMON)
        .component(MythicDataComponents.DRILL, new DrillComponent(0))
        .component(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2))
    );
    public static final Item PLATINUM_WATCH = new Item(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(RegistryHelper.itemKey("platinum_watch")));

    public static void init() {
        RegistryHelper.item("red_aegis_sword", RED_AEGIS_SWORD);
        RegistryHelper.item("white_aegis_sword", WHITE_AEGIS_SWORD);
        RegistryHelper.item("orichalcum_hammer", ORICHALCUM_HAMMER);
        RegistryHelper.item("midas_gold_sword", MIDAS_GOLD_SWORD);
        RegistryHelper.item("gilded_midas_gold_sword", GILDED_MIDAS_GOLD_SWORD);
        RegistryHelper.item("royal_midas_gold_sword", ROYAL_MIDAS_GOLD_SWORD);
        RegistryHelper.item("mythril_drill", MYTHRIL_DRILL);
        RegistryHelper.item("star_platinum_arrow", STAR_PLATINUM_ARROW);
        RegistryHelper.item("runite_arrow", RUNITE_ARROW);
        RegistryHelper.item("tipped_runite_arrow", TIPPED_RUNITE_ARROW);
        RegistryHelper.item("stormyx_shield", STORMYX_SHIELD);
        RegistryHelper.item("platinum_watch", PLATINUM_WATCH);

        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath("mythicaddons", "red_aegis_sword"), RegistryHelper.id("red_aegis_sword"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath("mythicaddons", "white_aegis_sword"), RegistryHelper.id("white_aegis_sword"));
    }

}