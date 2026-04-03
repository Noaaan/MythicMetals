package com.mythicmetals.item;

import com.mythicmetals.api.v2.Material;
import com.mythicmetals.api.v2.MaterialType;
import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.armor.MythicArmorMaterials;
import com.mythicmetals.item.tools.MythicToolMaterials;

import static com.mythicmetals.api.v2.Material.*;

public class MythicMaterials {
    private MythicMaterials() {
    }

    public static final Material ADAMANTITE = Material.Builder.create("adamantite", MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(4.0f)
            // FIXME - Sounds
            // .sounds(SoundType.DEEPSLATE)
            .createOreVariant("deepslate", 5.0f, 6.5f)
            .finish()
        )
        .createDefaultTools(MythicToolMaterials.ADAMANTITE, ToolSet.AttackSpeeds.BETTER_AXE)
        .createDefaultArmor(MythicArmorMaterials.ADAMANTITE)
        .finish();

    public static final Material AQUARIUM = Material.Builder.create("aquarium", MaterialType.INGOT)
        .finish();

    public static final Material BANGLUM = Material.Builder.create("banglum", MaterialType.INGOT)
        .finish();

    public static final Material BRONZE = Material.Builder.create("bronze", MaterialType.ALLOY)
        .finish();

    public static final Material CARMOT = Material.Builder.create("carmot", MaterialType.INGOT)
        .finish();

    public static final Material CELESTIUM = Material.Builder.create("celestium", MaterialType.RARE_ALLOY)
        .finish();

    public static final Material DURASTEEL = Material.Builder.create("durasteel", MaterialType.ALLOY)
        .finish();

    public static final Material HALLOWED = Material.Builder.create("hallowed", MaterialType.ALLOY)
        .finish();

    public static final Material KYBER = Material.Builder.create("kyber", MaterialType.INGOT)
        .finish();

    public static final Material MANGANESE = Material.Builder.create("manganese", MaterialType.INGOT)
        .finish();

    public static final Material METALLURGIUM = Material.Builder.create("metallurgium", MaterialType.RARE_ALLOY)
        .finish();

    public static final Material MIDAS_GOLD = Material.Builder.create("midas_gold", MaterialType.INGOT)
        .finish();

    public static final Material MORKITE = Material.Builder.create("morkite", MaterialType.BASIC)
        .finish();

    public static final Material MYTHRIL = Material.Builder.create("mythril", MaterialType.INGOT)
        .finish();

    public static final Material ORICHALCUM = Material.Builder.create("orichalcum", MaterialType.INGOT)
        .finish();

    public static final Material OSMIUM = Material.Builder.create("osmium", MaterialType.INGOT)
        .finish();

    public static final Material PALLADIUM = Material.Builder.create("palladium", MaterialType.INGOT)
        .finish();

    public static final Material PLATINUM = Material.Builder.create("platinum", MaterialType.INGOT)
        .finish();

    public static final Material PROMETHEUM = Material.Builder.create("prometheum", MaterialType.INGOT)
        .finish();

    public static final Material QUADRILLUM = Material.Builder.create("quadrillum", MaterialType.INGOT)
        .finish();

    public static final Material RUNITE = Material.Builder.create("runite", MaterialType.INGOT)
        .finish();

    public static final Material SILVER = Material.Builder.create("silver", MaterialType.INGOT)
        .finish();

    public static final Material STAR_PLATINUM = Material.Builder.create("star_platinum", MaterialType.ALLOY)
        .finish();

    public static final Material STARRITE = Material.Builder.create("starrite", MaterialType.INGOT)
        .finish();

    public static final Material STEEL = Material.Builder.create("steel", MaterialType.ALLOY)
        .finish();

    public static final Material STORMYX = Material.Builder.create("stormyx", MaterialType.INGOT)
        .finish();

    public static final Material TIN = Material.Builder.create("tin", MaterialType.INGOT)
        .finish();

    public static final Material UNOBTAINIUM = Material.Builder.create("unobtainium", MaterialType.SPECIAL)
        .finish();

    public static void init() {
        // no-op
    }
}
