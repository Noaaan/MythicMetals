package com.mythicmetals.misc;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.data.loot.RandomChanceWithLuckCondition;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import io.wispforest.owo.ops.LootOps;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import java.util.List;

public class MythicLootOps {
    private MythicLootOps() {
    }

    public static final Identifier BETTER_PIGLIN_BARTERING = RegistryHelper.id("gameplay/better_piglin_bartering");
    public static final Identifier CUSTOM_PIGLIN_BARTERING = Identifier.fromNamespaceAndPath("custom_piglin_bartering", "mythicmetals/midas_gold_ingot");
    public static List<Identifier> EMERALD_CRYSTAL_TARGETS = List.of(
        Identifier.withDefaultNamespace("blocks/deepslate_emerald_ore"),
        Identifier.withDefaultNamespace("blocks/emerald_ore")
    );

    public static void init() {
        if (MythicMetals.CONFIG.unobtainium()) {
            LootOps.injectItem(MythicMaterials.UNOBTAINIUM.baseMaterial(), 0.01F, BuiltInLootTables.ANCIENT_CITY.identifier());
            LootOps.injectItem(MythicMaterials.UNOBTAINIUM.baseMaterial(), 0.00042F, BETTER_PIGLIN_BARTERING);
            if (FabricLoader.getInstance().isModLoaded("custom_piglin_bartering")) {
                LootOps.injectItem(MythicMaterials.UNOBTAINIUM.baseMaterial(), 0.00042F, CUSTOM_PIGLIN_BARTERING);
            }
        }
        LootTableEvents.MODIFY.register((key, tableBuilder, source, provider) -> {
            if (source.isBuiltin() && EMERALD_CRYSTAL_TARGETS.contains(key.identifier())) {
                tableBuilder.withPool(LootPool.lootPool().add(
                    LootItem.lootTableItem(MythicMaterials.AEGIS.baseMaterial()).setWeight(1).when(RandomChanceWithLuckCondition.builder(0.02f)).build()
                ));
            }
        });
        LootOps.injectItem(MythicMaterials.UNOBTAINIUM.extraItems().get(MythicResourceKeys.UNOBTAINIUM_SMITHING_TEMPLATE), MythicMetals.CONFIG.unobtainiumTemplateChance(), BuiltInLootTables.ANCIENT_CITY.identifier());
        LootOps.injectItem(MythicMaterials.MYTHRIL.extraItems().get(MythicResourceKeys.MYTHRIL_DRILL_SMITHING_TEMPLATE), MythicMetals.CONFIG.mythrilDrillTemplateChance(), BuiltInLootTables.ABANDONED_MINESHAFT.identifier());
    }
}
