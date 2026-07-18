package com.mythicmetals.item;

import com.mythicmetals.api.v2.SmithingTemplateComponents;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.SmithingTemplateItem;
import java.util.ArrayList;
import java.util.List;

public class MythicSmithingTemplates {
    public static List<Identifier> UNOBTAINIUM_ALLOY_ITEMS = Util.make(new ArrayList<>(SmithingTemplateItem.createNetheriteUpgradeIconList()),
        identifiers -> identifiers.add(RegistryHelper.id("empty_slot_elytra")));
    public static List<Identifier> ARMOR_ITEMS = Util.make(new ArrayList<>(),
        identifiers -> {
            identifiers.add(Identifier.parse("item/empty_armor_slot_helmet"));
            identifiers.add(Identifier.parse("item/empty_armor_slot_chestplate"));
            identifiers.add(Identifier.parse("item/empty_armor_slot_leggings"));
            identifiers.add(Identifier.parse("item/empty_armor_slot_boots"));
        });
    public static final SmithingTemplateComponents AEGIS = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.aegis.applies_to").withStyle(ChatFormatting.BLUE),
        Component.translatable("smithing_template.mythicmetals.aegis.ingredients").withStyle(ChatFormatting.BLUE),
        Component.translatable("smithing_template.mythicmetals.aegis.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.aegis.additions_slot_description"),
        List.of(RegistryHelper.id("empty_slot_hallowed_sword"), RegistryHelper.id("empty_slot_palladium_sword")),
        SmithingTemplateItem.createNetheriteUpgradeMaterialList()
    );
    public static final SmithingTemplateComponents CARMOT = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.carmot.applies_to").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.KYBER.rgb())),
        Component.translatable("smithing_template.mythicmetals.carmot.ingredients").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb())),
        Component.translatable("smithing_template.mythicmetals.carmot.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.carmot.additions_slot_description"),
        SmithingTemplateItem.createNetheriteUpgradeIconList(),
        SmithingTemplateItem.createNetheriteUpgradeMaterialList()
    );
    public static final SmithingTemplateComponents LEGENDARY_BANGLUM = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.legendary_banglum.applies_to").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.BANGLUM.rgb())),
        Component.translatable("smithing_template.mythicmetals.legendary_banglum.ingredients").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.BANGLUM.rgb())),
        Component.translatable("smithing_template.mythicmetals.legendary_banglum.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.legendary_banglum.additions_slot_description"),
        SmithingTemplateItem.createNetheriteUpgradeIconList(),
        List.of(RegistryHelper.id("empty_slot_chunk"))
    );
    public static final SmithingTemplateComponents MIDAS_FOLDING = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.midas_folding.applies_to").withStyle(ChatFormatting.GOLD),
        Component.translatable("smithing_template.mythicmetals.midas_folding.ingredients").withStyle(ChatFormatting.GOLD),
        Component.translatable("smithing_template.mythicmetals.midas_folding.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.midas_folding.additions_slot_description"),
        List.of(
            RegistryHelper.id("empty_slot_midas_dagger"),
            RegistryHelper.id("empty_slot_midas"),
            RegistryHelper.id("empty_slot_gilded_midas"),
            RegistryHelper.id("empty_slot_royal_midas")
        ),
        List.of(RegistryHelper.id("empty_slot_block"))
    );
    public static final SmithingTemplateComponents MYTHRIL_DRILL = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.mythril_drill.applies_to").withStyle(ChatFormatting.BLUE),
        Component.translatable("smithing_template.mythicmetals.mythril_drill.ingredients").withStyle(ChatFormatting.BLUE),
        Component.translatable("smithing_template.mythicmetals.mythril_drill.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.mythril_drill.additions_slot_description"),
        List.of(RegistryHelper.id("empty_slot_mythril_pick")),
        List.of(RegistryHelper.id("empty_slot_engine"))
    );
    public static final SmithingTemplateComponents OSMIUM_CHAINMAIL = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.osmium.applies_to").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)),
        Component.translatable("smithing_template.mythicmetals.osmium.ingredients").setStyle(Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.OSMIUM.rgb())),
        Component.translatable("smithing_template.mythicmetals.osmium.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.osmium.additions_slot_description"),
        ARMOR_ITEMS,
        SmithingTemplateItem.createNetheriteUpgradeMaterialList()
    );
    public static final SmithingTemplateComponents ROYAL_MIDAS = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.royal_midas.applies_to").withStyle(ChatFormatting.GOLD),
        Component.translatable("smithing_template.mythicmetals.royal_midas.ingredients").withStyle(ChatFormatting.GOLD),
        Component.translatable("smithing_template.mythicmetals.royal_midas.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.royal_midas.additions_slot_description"),
        List.of(RegistryHelper.id("empty_slot_gilded_midas")),
        List.of(RegistryHelper.id("empty_slot_block"))
    );
    public static final SmithingTemplateComponents TIDESINGER = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.tidesinger.applies_to").setStyle(UsefulSingletonForColorUtil.MetalColors.AQUA_STYLE),
        Component.translatable("smithing_template.mythicmetals.tidesinger.ingredients").setStyle(UsefulSingletonForColorUtil.MetalColors.BUBBLE.style()),
        Component.translatable("smithing_template.mythicmetals.tidesinger.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.tidesinger.additions_slot_description"),
        SmithingTemplateItem.createNetheriteUpgradeIconList(),
        List.of(
            RegistryHelper.id("empty_slot_brain"),
            RegistryHelper.id("empty_slot_bubble"),
            RegistryHelper.id("empty_slot_fire"),
            RegistryHelper.id("empty_slot_horn"),
            RegistryHelper.id("empty_slot_tube")
        )
    );
    public static final SmithingTemplateComponents UNOBTAINIUM = new SmithingTemplateComponents(
        Component.translatable("smithing_template.mythicmetals.unobtainium.applies_to").withStyle(ChatFormatting.BLUE),
        Component.translatable("smithing_template.mythicmetals.unobtainium.ingredients").withStyle(ChatFormatting.BLUE),
        Component.translatable("smithing_template.mythicmetals.unobtainium.base_slot_description"),
        Component.translatable("smithing_template.mythicmetals.unobtainium.additions_slot_description"),
        UNOBTAINIUM_ALLOY_ITEMS,
        SmithingTemplateItem.createNetheriteUpgradeMaterialList()
    );
}
