package com.mythicmetals.item.tools;

import com.mythicmetals.MythicAttributeModifier;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import java.util.List;

import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_MULTIPLIED_BASE;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super("tidesinger", material, damage, speed);
    }

    private static final MythicAttributeModifier MINING_SPEED_BONUS = new MythicAttributeModifier(
        Attributes.SUBMERGED_MINING_SPEED,
        1.5f,
        ADD_MULTIPLIED_BASE,
        EquipmentSlotGroup.MAINHAND
    );

    private static final List<MythicAttributeModifier> TIDESINGER_MODS = List.of(MINING_SPEED_BONUS);

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraMods) {
        return new TidesingerSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new TidesingerAxe(material, damage, speed, settings);
    }

    public static class TidesingerSword extends SwordMock implements RiptideTool {

        public TidesingerSword(ToolMaterial material, int damage, float speed, Properties settings) {
            super(material, damage, speed, settings, TIDESINGER_MODS);
        }

        @Override
        public InteractionResult use(Level world, Player user, InteractionHand hand) {
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
        public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
            return performRiptide(stack, world, user, remainingUseTicks);
        }
    }

    public static class TidesingerAxe extends AxeMock implements RiptideTool {

        public TidesingerAxe(ToolMaterial material, int damage, float speed, Properties settings) {
            super(material, damage, speed, settings, TIDESINGER_MODS);
        }

        @Override
        public InteractionResult use(Level world, Player user, InteractionHand hand) {
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
        public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
            return performRiptide(stack, world, user, remainingUseTicks);
        }

        @Override
        public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        }
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return super.makePickaxe(material, damage, speed, settings, TIDESINGER_MODS);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return super.makeShovel(material, damage, speed, settings, TIDESINGER_MODS);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return super.makeHoe(material, damage, speed, settings, TIDESINGER_MODS);
    }
}
