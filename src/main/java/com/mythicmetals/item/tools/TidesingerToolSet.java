package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import java.util.List;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super("tidesinger", material, damage, speed);
    }

    private static final AttributeModifier MINING_SPEED_BONUS = new AttributeModifier(
        EntityAttributes.SUBMERGED_MINING_SPEED,
        1.5f,
        ADD_MULTIPLIED_BASE,
        AttributeModifierSlot.MAINHAND
    );

    private static final List<AttributeModifier> TIDESINGER_MODS = List.of(MINING_SPEED_BONUS);

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraMods) {
        return new TidesingerSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new TidesingerAxe(material, damage, speed, settings);
    }

    public static class TidesingerSword extends SwordMock implements RiptideTool {

        public TidesingerSword(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings, TIDESINGER_MODS);
        }

        @Override
        public ActionResult use(World world, PlayerEntity user, Hand hand) {
            return activateRiptide(user, hand);
        }

        @Override
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.SPEAR;
        }

        @Override
        public int getMaxUseTime(ItemStack stack, LivingEntity user) {
            return RiptideTool.MAX_USE_TIME;
        }


        @Override
        public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            return performRiptide(stack, world, user, remainingUseTicks);
        }
    }

    public static class TidesingerAxe extends AxeMock implements RiptideTool {

        public TidesingerAxe(ToolMaterial material, int damage, float speed, Settings settings) {
            super(material, damage, speed, settings, TIDESINGER_MODS);
        }

        @Override
        public ActionResult use(World world, PlayerEntity user, Hand hand) {
            return activateRiptide(user, hand);
        }

        @Override
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.SPEAR;
        }

        @Override
        public int getMaxUseTime(ItemStack stack, LivingEntity user) {
            return RiptideTool.MAX_USE_TIME;
        }

        @Override
        public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            return performRiptide(stack, world, user, remainingUseTicks);
        }

        @Override
        public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        }
    }

    @Override
    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return super.makePickaxe(material, damage, speed, settings, TIDESINGER_MODS);
    }

    @Override
    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return super.makeShovel(material, damage, speed, settings, TIDESINGER_MODS);
    }

    @Override
    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return super.makeHoe(material, damage, speed, settings, TIDESINGER_MODS);
    }
}
