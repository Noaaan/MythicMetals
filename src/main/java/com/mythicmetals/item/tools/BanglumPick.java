package com.mythicmetals.item.tools;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import com.mythicmetals.misc.BlockBreaker;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterCriteria;

public class BanglumPick extends PickaxeItem {

    public BanglumPick(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    /**
     * Method for the legendary banglum pickaxe breaking ability.
     * When the tool is used on a block, it breaks a bunch of blocks in a set radius.
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        boolean shouldPass = false;
        var world = context.getWorld();
        var player = context.getPlayer();

        if (player != null && !isCoolingDown(player, context.getStack()) && !world.isClient()) {

            var iterator = BlockBreaker.findBlocks(context, 5);

            for (BlockPos blockPos : iterator) {
                if (isCorrectForDrops(context.getStack(), world.getBlockState(blockPos))) {
                    WorldOps.breakBlockWithItem(world, blockPos, context.getStack());
                    context.getStack().damage(2, player, EquipmentSlot.MAINHAND);
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getBlockPos();
            var facing = context.getSide().getOpposite();
            var pos2 = context.getBlockPos().offset(facing, 5);

            MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3d.of(pos), Vec3d.of(pos2));
            WorldOps.playSound(world, pos, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.PLAYERS);

            RegisterCriteria.USED_BLAST_MINING.trigger((ServerPlayerEntity) player);
            player.getItemCooldownManager().set(this, 100);
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    public static boolean isCoolingDown(LivingEntity entity, ItemStack stack) {
        if (entity != null && entity.isPlayer()) {
            return ((PlayerEntity) entity).getItemCooldownManager().isCoolingDown(stack.getItem());
        }
        return false;
    }
}
