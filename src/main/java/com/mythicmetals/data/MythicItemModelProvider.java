package com.mythicmetals.data;

import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.item.ItemSet;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
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
            if (!s.equals("legendary_banglum")) {
                itemModelGenerator.register(toolSet.getSword(), Models.HANDHELD);
                itemModelGenerator.register(toolSet.getAxe(), Models.HANDHELD);
                itemModelGenerator.register(toolSet.getPickaxe(), Models.HANDHELD);
                itemModelGenerator.register(toolSet.getShovel(), Models.HANDHELD);
                itemModelGenerator.register(toolSet.getHoe(), Models.HANDHELD);
            }
        });
        itemModelGenerator.register(MythicTools.BANGLUM_TNT_MINECART, Models.GENERATED);
        itemModelGenerator.register(MythicTools.PALLADIUM_MINECART, Models.GENERATED);
        itemModelGenerator.register(MythicTools.CARMOT_BELL, Models.GENERATED);
        itemModelGenerator.register(MythicTools.STAR_PLATINUM_ARROW, Models.GENERATED);
        itemModelGenerator.register(MythicTools.RUNITE_ARROW, Models.GENERATED);
        itemModelGenerator.register(MythicTools.RED_AEGIS_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MythicTools.WHITE_AEGIS_SWORD, Models.HANDHELD);
        itemModelGenerator.register(MythicTools.ORICHALCUM_HAMMER, Models.HANDHELD);
        var tippedRuniteArrowLayers = itemModelGenerator.uploadTwoLayers(
            MythicTools.TIPPED_RUNITE_ARROW,
            RegistryHelper.id("item/weapons/tipped_runite_arrow_head"),
            RegistryHelper.id("item/weapons/tipped_runite_arrow_base")
        );
        itemModelGenerator.registerPotionTinted(MythicTools.TIPPED_RUNITE_ARROW, tippedRuniteArrowLayers);
    }
}
