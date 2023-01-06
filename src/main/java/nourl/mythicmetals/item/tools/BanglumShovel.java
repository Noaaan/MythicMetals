package nourl.mythicmetals.item.tools;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import nourl.mythicmetals.misc.BlockBreaker;
import nourl.mythicmetals.misc.MythicParticleSystem;

public class BanglumShovel extends ShovelItem {

    public BanglumShovel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Method for the legendary banglum shovel breaking ability.
     * When the tool is used on a block, it breaks a bunch of blocks in a set radius.
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        boolean shouldPass = false;
        var world = context.getWorld();
        var player = context.getPlayer();

        if (player != null && !getCooldown(player, context.getStack()) && !world.isClient) {
            var iterator = BlockBreaker.findBlocks(context, 5);

            for (BlockPos blockPos : iterator) {
                if (canBreak(world, blockPos)) {
                    WorldOps.breakBlockWithItem(world, blockPos, context.getStack());
                    context.getStack().damage(2, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getBlockPos();
            var facing = context.getPlayerFacing();
            var pos2 = context.getBlockPos().offset(facing, 5);
            MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3d.of(pos), Vec3d.of(pos2));
            WorldOps.playSound(world, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS);

            player.getItemCooldownManager().set(this, 100);
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    public boolean canBreak(BlockView view, BlockPos pos) {
        return super.isSuitableFor(view.getBlockState(pos));
    }

    public static boolean getCooldown(LivingEntity entity, ItemStack stack) {
        if (entity != null && entity.isPlayer()) {
            return ((PlayerEntity) entity).getItemCooldownManager().isCoolingDown(stack.getItem());
        }
        return false;
    }
}
