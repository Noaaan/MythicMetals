package com.mythicmetals.item.tools;

import com.mythicmetals.misc.BlockBreaker;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterCriteria;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;

public class BanglumShovel extends ShovelItem {

    public BanglumShovel(ToolMaterial material, int damage, float speed, Properties settings) {
        super(material, damage, speed, settings);
    }

    /**
     * Method for the legendary banglum shovel breaking ability.
     * When the tool is used on a block, it breaks a bunch of blocks in a set radius.
     */
    @Override
    public InteractionResult useOn(UseOnContext context) {
        boolean shouldPass = false;
        var world = context.getLevel();
        var player = context.getPlayer();
        var stack = context.getItemInHand();

        if (player != null && !isCoolingDown(player, stack) && !world.isClientSide()) {
            var iterator = BlockBreaker.findBlocks(context, 5);

            for (BlockPos blockPos : iterator) {
                if (BlockBreaker.isProtected(world, blockPos, player.getGameProfile(), player)) {
                    continue;
                }
                if (isCorrectToolForDrops(stack, world.getBlockState(blockPos))) {
                    WorldOps.breakBlockWithItem(world, blockPos, stack);
                    stack.hurtAndBreak(2, player, EquipmentSlot.MAINHAND);
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getClickedPos();
            var facing = context.getHorizontalDirection();
            var pos2 = context.getClickedPos().relative(facing, 5);
            MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3.atLowerCornerOf(pos), Vec3.atLowerCornerOf(pos2));
            WorldOps.playSound(world, pos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.PLAYERS);

            RegisterCriteria.USED_BLAST_MINING.trigger((ServerPlayer) player);
            player.getCooldowns().addCooldown(stack, 100);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    public static boolean isCoolingDown(LivingEntity entity, ItemStack stack) {
        if (entity != null && entity.isAlwaysTicking()) {
            return ((Player) entity).getCooldowns().isOnCooldown(stack);
        }
        return false;
    }
}
