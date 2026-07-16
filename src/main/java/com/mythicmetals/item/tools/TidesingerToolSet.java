package com.mythicmetals.item.tools;

import com.mythicmetals.item.MythicAttributeModifier;
import com.mythicmetals.api.v2.ToolSet;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.item.MythicSpearStats;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import java.util.List;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_BASE;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(ToolMaterial material) {
        super("tidesinger", material);
    }

    private static final MythicAttributeModifier MINING_SPEED_BONUS = new MythicAttributeModifier(
        Attributes.SUBMERGED_MINING_SPEED,
        1.5f,
        ADD_MULTIPLIED_BASE,
        EquipmentSlotGroup.MAINHAND
    );

    private static final List<MythicAttributeModifier> TIDESINGER_MODS = List.of(MINING_SPEED_BONUS);

    public ToolSet createTidesingerTools(ToolMaterial toolMaterial, AttackSpeeds attackSpeeds, MythicSpearStats.SpearStats spearStats) {
        this.sword = RegistryHelper.item(swordKey, new RiptideItem(
            swordVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.SWORD, attackSpeeds, TIDESINGER_MODS))
                .setId(swordKey)
        ));
        this.axe = RegistryHelper.item(axeKey, new RiptideItem(
            axeVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.AXE, attackSpeeds, TIDESINGER_MODS))
                .setId(axeKey)
        ));
        this.pickaxe = RegistryHelper.item(pickaxeKey, new Item(
            pickaxeVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.PICKAXE, attackSpeeds, TIDESINGER_MODS))
                .setId(pickaxeKey)
        ));
        this.shovel = RegistryHelper.item(shovelKey, new Item(
            shovelVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.SHOVEL, attackSpeeds, TIDESINGER_MODS))
                .setId(shovelKey)
        ));
        this.hoe = RegistryHelper.item(hoeKey, new Item(
            hoeVanilla()
                .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.HOE, attackSpeeds, TIDESINGER_MODS))
                .setId(hoeKey)
        ));
        this.spear = RegistryHelper.item(spearKey, new Item(
            spearSettings(toolMaterial, spearStats)
                .setId(spearKey)
        ));
        return this;
    }

    public static class RiptideItem extends Item implements RiptideTool {

        public RiptideItem(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResult use(Level level, Player user, InteractionHand hand) {
            return activateRiptide(user, hand);
        }

        @Override
        public ItemUseAnimation getUseAnimation(ItemStack stack) {
            return ItemUseAnimation.SPEAR;
        }

        @Override
        public int getUseDuration(ItemStack stack, LivingEntity user) {
            return RiptideTool.MAX_USE_TIME;
        }


        @Override
        public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseTicks) {
            return performRiptide(stack, level, user, remainingUseTicks);
        }
    }
}
