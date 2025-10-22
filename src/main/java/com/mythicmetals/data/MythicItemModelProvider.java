package com.mythicmetals.data;

import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.item.ItemSet;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;

public class MythicItemModelProvider extends FabricModelProvider {

    public MythicItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // TODO
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Items
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            itemModelGenerator.register(itemSet.getIngot(), Models.GENERATED);
            if (itemSet.getRawOre() != null) {
                itemModelGenerator.register(itemSet.getRawOre(), Models.GENERATED);
            }
            if (itemSet.getNugget() != null) {
                itemModelGenerator.register(itemSet.getNugget(), Models.GENERATED);
            }
            if (itemSet.getDust() != null) {
                itemModelGenerator.register(itemSet.getDust(), Models.GENERATED);
            }
        });
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Mats.class, Item.class, (value, name, field) -> {
            itemModelGenerator.register(value, Models.GENERATED);
        });
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.Templates.class, Item.class, (value, name, field) -> {
            itemModelGenerator.register(value, Models.GENERATED);
        });
        // Armor
        MythicArmor.ARMOR_MAP.forEach((s, armorSet) -> {
            itemModelGenerator.register(armorSet.getHelmet(), Models.GENERATED);
            itemModelGenerator.register(armorSet.getChestplate(), Models.GENERATED);
            itemModelGenerator.register(armorSet.getLeggings(), Models.GENERATED);
            itemModelGenerator.register(armorSet.getBoots(), Models.GENERATED);
        });
        itemModelGenerator.register(MythicArmor.CELESTIUM_ELYTRA, Models.GENERATED);

        // Tools
        MythicTools.TOOL_MAP.forEach((s, toolSet) -> {
            itemModelGenerator.register(toolSet.getSword(), Models.HANDHELD);
            itemModelGenerator.register(toolSet.getAxe(), Models.HANDHELD);
            itemModelGenerator.register(toolSet.getPickaxe(), Models.HANDHELD);
            itemModelGenerator.register(toolSet.getShovel(), Models.HANDHELD);
            itemModelGenerator.register(toolSet.getHoe(), Models.HANDHELD);
        });
        itemModelGenerator.register(MythicTools.BANGLUM_TNT_MINECART, Models.GENERATED);
        itemModelGenerator.register(MythicTools.PALLADIUM_MINECART, Models.GENERATED);
        itemModelGenerator.register(MythicTools.CARMOT_BELL, Models.GENERATED);
        itemModelGenerator.register(MythicTools.STAR_PLATINUM_ARROW, Models.GENERATED);
        itemModelGenerator.register(MythicTools.RUNITE_ARROW, Models.GENERATED);
        itemModelGenerator.register(MythicTools.RED_AEGIS_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MythicTools.WHITE_AEGIS_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MythicTools.ORICHALCUM_HAMMER, Models.HANDHELD);
        itemModelGenerator.registerTippedArrow(MythicTools.TIPPED_RUNITE_ARROW);
        // TODO - Handle manually, as they are exquisite
        //MythicTools.MIDAS_GOLD_SWORD
        //MythicTools.GILDED_MIDAS_GOLD_SWORD
        //MythicTools.ROYAL_MIDAS_GOLD_SWORD
        //MythicTools.MYTHRIL_DRILL
        //MythicTools.STORMYX_SHIELD
        //MythicTools.PLATINUM_WATCH
    }
}
