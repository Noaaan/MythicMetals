package com.mythicmetals.misc.wiki;

import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.item.MythicSpearStats;
import net.minecraft.world.item.ToolMaterial;
import java.util.HashMap;
import java.util.Map;

public class WikiHelper {
    public static final Map<String, WikiToolStats> TOOL_STAT_MAP = new HashMap<>();

    public record WikiToolStats(
        ToolMaterial material,
        ToolSet.AttackSpeeds attackSpeeds,
        MythicSpearStats.SpearStats spearStats
    ) {

        public double getAttackDamage(MythicItemAttributes.ToolType toolType) {
            var damage = switch (toolType) {
                case SWORD -> MythicItemAttributes.SWORD_BASE_DAMAGE;
                case AXE -> MythicItemAttributes.AXE_BASE_DAMAGE;
                case PICKAXE -> MythicItemAttributes.PICKAXE_BASE_DAMAGE;
                case SHOVEL -> MythicItemAttributes.SHOVEL_BASE_DAMAGE;
                case HOE -> MythicItemAttributes.HOE_BASE_DAMAGE;
            };
            return 1 + material.attackDamageBonus() + damage;
        }
    }
}
