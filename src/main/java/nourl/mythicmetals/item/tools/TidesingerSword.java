package nourl.mythicmetals.item.tools;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TidesingerSword extends SwordItem {
    public static final float TRIDENT_POWER = 3.0f;

    public TidesingerSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        } else if (EnchantmentHelper.getRiptide(itemStack) > 0 && !user.isTouchingWaterOrRain()) {
            return TypedActionResult.fail(itemStack);
        } else if (user.getItemCooldownManager().isCoolingDown(itemStack.getItem())) {
            return TypedActionResult.fail(itemStack);
        } else{
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int i = this.getMaxUseTime(stack) - remainingUseTicks;
        if (i >= 10 && user instanceof PlayerEntity playerEntity) {
            if (playerEntity.isTouchingWaterOrRain()) {
                if (!world.isClient) {
                    stack.damage(1, playerEntity, p -> p.sendToolBreakStatus(user.getActiveHand()));
                }

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                float yaw = playerEntity.getYaw();
                float pitch = playerEntity.getPitch();
                float h = -MathHelper.sin(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
                float k = -MathHelper.sin(pitch * (float) (Math.PI / 180.0));
                float l = MathHelper.cos(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
                float m = MathHelper.sqrt(h * h + k * k + l * l);
                h *= TRIDENT_POWER / m;
                k *= TRIDENT_POWER / m;
                l *= TRIDENT_POWER / m;
                playerEntity.addVelocity(h, k, l);
                playerEntity.useRiptide(20);
                if (playerEntity.isOnGround()) {
                    float o = 1.1999999F;
                    playerEntity.move(MovementType.SELF, new Vec3d(0.0, o, 0.0));
                }

                world.playSoundFromEntity(null, playerEntity, SoundEvents.ITEM_TRIDENT_RIPTIDE_3, SoundCategory.PLAYERS, 1.0F, 1.0F);
                playerEntity.getItemCooldownManager().set(stack.getItem(), 80);
            }
        }
    }
}
