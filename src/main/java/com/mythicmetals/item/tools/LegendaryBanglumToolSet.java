package com.mythicmetals.item.tools;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.component.BlastMiningComponent;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.item.MythicSpearStats;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.List;

public class LegendaryBanglumToolSet extends ToolSet {

    public static final List<MythicAttributeModifier> LEGENDARY_BANGLUM_MODIFIERS = List.of(new MythicAttributeModifier(
            Attributes.ATTACK_KNOCKBACK, 6.0, Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND
    ));

    public LegendaryBanglumToolSet(ToolMaterial material) {
        super("legendary_banglum", material);
    }

    public static final int BLAST_MINING_DEPTH = 8;

    public ToolSet createLegendaryBanglumTools(ToolMaterial material, AttackSpeeds attackSpeeds, MythicSpearStats.SpearStats spearStats) {
        this.sword = RegistryHelper.item(swordKey, new TidesingerToolSet.RiptideItem(
            swordVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.SWORD, attackSpeeds, LEGENDARY_BANGLUM_MODIFIERS))
                .setId(swordKey)
        ));
        this.axe = RegistryHelper.item(axeKey, new TidesingerToolSet.RiptideItem(
            axeVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.AXE, attackSpeeds, LEGENDARY_BANGLUM_MODIFIERS))
                .setId(axeKey)
        ));
        this.pickaxe = RegistryHelper.item(pickaxeKey, new Item(
            pickaxeVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.PICKAXE, attackSpeeds, List.of()))
                .component(MythicDataComponents.BLAST_MINING, new BlastMiningComponent(BLAST_MINING_DEPTH))
                .setId(pickaxeKey)
        ));
        this.shovel = RegistryHelper.item(shovelKey, new Item(
            shovelVanilla()
                .component(MythicDataComponents.BLAST_MINING, new BlastMiningComponent(BLAST_MINING_DEPTH))
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.SHOVEL, attackSpeeds, List.of()))
                .setId(shovelKey)
        ));
        this.hoe = RegistryHelper.item(hoeKey, new Item(
            hoeVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.HOE, attackSpeeds, LEGENDARY_BANGLUM_MODIFIERS))
                .setId(hoeKey)
        ));
        this.spear = RegistryHelper.item(spearKey, new Item(
            spearSettings(material, spearStats, LEGENDARY_BANGLUM_MODIFIERS)
                .setId(spearKey)
        ));
        return this;
    }

}
