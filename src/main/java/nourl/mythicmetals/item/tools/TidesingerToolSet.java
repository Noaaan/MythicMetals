package nourl.mythicmetals.item.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class TidesingerToolSet extends ToolSet {
    public TidesingerToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new TidesingerSword(material, damage, speed, settings);
    }

    @Override
    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new TidesingerAxe(material, damage, speed, settings);
    }

    public static class TidesingerSword extends SwordItem implements RiptideTool {

        public TidesingerSword(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
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
        public int getMaxUseTime(ItemStack stack) {
            return MAX_USE_TIME;
        }

        @Override
        public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            performRiptide(stack, world, user, remainingUseTicks);
        }
    }

    public static class TidesingerAxe extends AxeItem implements RiptideTool {

        public TidesingerAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
            super(material, attackDamage, attackSpeed, settings);
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
        public int getMaxUseTime(ItemStack stack) {
            return MAX_USE_TIME;
        }

        @Override
        public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
            performRiptide(stack, world, user, remainingUseTicks);
        }
    }
}
