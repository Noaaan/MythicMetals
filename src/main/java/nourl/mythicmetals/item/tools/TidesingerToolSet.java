package nourl.mythicmetals.item.tools;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import nourl.mythicmetals.misc.RegistryHelper;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new TidesingerSword(material, settings.attributeModifiers(createAttributeModifiers(material, damage, speed)));
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new TidesingerAxe(material, settings.attributeModifiers(createAttributeModifiers(material, damage, speed)));
    }

    public static class TidesingerSword extends SwordItem implements RiptideTool {

        public TidesingerSword(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
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
        public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            performRiptide(stack, world, user, remainingUseTicks);
        }
    }

    public static class TidesingerAxe extends AxeItem implements RiptideTool {

        public TidesingerAxe(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
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
        public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            performRiptide(stack, world, user, remainingUseTicks);
        }

        @Override
        public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        }
    }

    @Override
    public AttributeModifiersComponent.Builder createAttributeBuilder(ToolMaterial material, double damage, float speed) {
        return super.createAttributeBuilder(material, damage, speed).add(EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED,
            new EntityAttributeModifier(RegistryHelper.id("tidesinger_tool_bonus"), 1.5f, ADD_MULTIPLIED_BASE),
            AttributeModifierSlot.MAINHAND
        );
    }
}
