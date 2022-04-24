package nourl.mythicmetals.tools;

import draylar.magna.api.BlockFinder;
import draylar.magna.api.BreakValidator;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import nourl.mythicmetals.utils.MythicParticleSystem;

public class BanglumPick extends PickaxeItem implements BreakValidator {

    public BanglumPick(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        boolean shouldPass = false;
        var world = context.getWorld();
        var player = context.getPlayer();

        if (player != null && !getCooldown(player, context.getStack()) && !world.isClient) {
            var finder = BlockFinder.DEFAULT.findPositions(
                    world, player, 1, 5);

            for (BlockPos blockPos : finder) {
                if (canBreak(world, blockPos)) {
                    WorldOps.breakBlockWithItem(world, blockPos, context.getStack());
                    context.getStack().damage(2, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getBlockPos();
            MythicParticleSystem.OVERENGINEERED_SINGLE_EXPLOSION_PARTICLE.spawn(world, Vec3d.of(pos));
            WorldOps.playSound(world, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS);

            player.getItemCooldownManager().set(this, 100);
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
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
