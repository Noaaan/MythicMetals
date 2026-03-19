package com.mythicmetals.item;

import com.mythicmetals.api.v2.Material;
import com.mythicmetals.api.v2.MaterialType;
import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.armor.MythicArmorMaterials;
import com.mythicmetals.item.tools.MythicToolMaterials;

import static com.mythicmetals.api.v2.Material.*;

public class MythicMaterials {
    public static final Material ADAMANTITE = new Material.Builder("adamantite")
        .createBaseMaterial(MaterialType.INGOT)
        .createBlockSetFromBuilder(DIAMOND_MINING_LEVEL, blockSetBuilder -> blockSetBuilder
            .createDefaultBlocks(4.0f)
            // FIXME - Ore variants and sounds
            // .sounds(SoundType.DEEPSLATE)
            // .createOreVariant("deepslate", Material.DIAMOND_MINING_LEVEL)
            .finish()
        )
        .createDefaultTools(MythicToolMaterials.ADAMANTITE, ToolSet.AttackSpeeds.BETTER_AXE)
        .createDefaultArmor(MythicArmorMaterials.ADAMANTITE)
        .finish();
}
