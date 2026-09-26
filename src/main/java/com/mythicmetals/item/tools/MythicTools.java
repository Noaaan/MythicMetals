package com.mythicmetals.item.tools;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.api.v2.ToolSet.AttackSpeeds;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.*;
import com.mythicmetals.item.component.DrillComponent;
import com.mythicmetals.item.component.MidasGoldComponent;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.List;

import static com.mythicmetals.api.v2.ToolSet.defaultSettings;
import static com.mythicmetals.misc.RegistryHelper.itemKey;

///
/// Class which contains any extra tools which are more complex than simpler items.
/// Most are kept in here and manually added to [com.mythicmetals.item.MythicMaterials]
/// afterward
///
public class MythicTools {

    private MythicTools() {
    }

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
            .setId(MythicResourceKeys.RED_AEGIS_SWORD)
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
            .setId(MythicResourceKeys.WHITE_AEGIS_SWORD)
    );

    public static final Item ORICHALCUM_HAMMER = new HammerBase(MythicToolMaterials.ORICHALCUM, 6, -3.2f, new Item.Properties()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .setId(MythicResourceKeys.ORICHALCUM_HAMMER),
        1
    );

    public static final Item MIDAS_GOLD_DAGGER = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD,
        new Item.Properties()
            .setId(MythicResourceKeys.MIDAS_GOLD_DAGGER)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(0))
    );

    public static final Item MIDAS_GOLD_SHORTSWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD_PLUS_ONE,
        new Item.Properties()
            .setId(MythicResourceKeys.MIDAS_GOLD_SHORTSWORD)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(64))
    );

    public static final Item MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD_PLUS_TWO,
        new Item.Properties()
            .setId(MythicResourceKeys.MIDAS_GOLD_SWORD)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(128))
    );

    public static final Item SOCKETED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD_PLUS_THREE,
        new Item.Properties()
            .setId(MythicResourceKeys.SOCKETED_MIDAS_GOLD_SWORD)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(256))
    );

    public static final Item GILDED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.GILDED_MIDAS_GOLD,
        new Item.Properties()
            .setId(MythicResourceKeys.GILDED_MIDAS_GOLD_SWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(320))
    );

    public static final Item MAXED_GILDED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MAXED_GILDED_MIDAS_GOLD,
        new Item.Properties()
            .setId(MythicResourceKeys.MAXED_GILDED_MIDAS_GOLD_SWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(640))
    );

    public static final Item ROYAL_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD,
        new Item.Properties()
            .setId(MythicResourceKeys.ROYAL_MIDAS_GOLD_SWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(641))
    );

    public static final Item ROYAL_MIDAS_GOLD_BROADSWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD_PLUS_ONE,
        new Item.Properties()
            .setId(MythicResourceKeys.ROYAL_MIDAS_GOLD_BROADSWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(768))
    );

    public static final Item ROYAL_MIDAS_GOLD_LONGSWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD_PLUS_TWO,
        new Item.Properties()
            .setId(MythicResourceKeys.ROYAL_MIDAS_GOLD_LONGSWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(896))
    );

    public static final Item ROYAL_MIDAS_GOLD_GREATSWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD_PLUS_THREE,
        new Item.Properties()
            .setId(MythicResourceKeys.ROYAL_MIDAS_GOLD_GREATSWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(1024))
    );

    public static final Item TRUE_ROYAL_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD_PLUS_FOUR,
        new Item.Properties()
            .setId(MythicResourceKeys.TRUE_ROYAL_MIDAS_GOLD_SWORD)
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.MIDAS_GOLD, MidasGoldComponent.of(1280))
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
    );
    public static final Item PLATINUM_WATCH = new Item(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(RegistryHelper.itemKey("platinum_watch")));

    public static void init() {
    }

}