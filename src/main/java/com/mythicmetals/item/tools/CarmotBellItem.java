package com.mythicmetals.item.tools;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.misc.CarmotBellDamageSource;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import java.util.List;

public class CarmotBellItem extends BlockItem {

    public static final double RANGE = 6.0;

    public CarmotBellItem(Settings settings) {
        super(MythicBlocks.CARMOT_BELL_BLOCK, settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        var entities = world.getOtherEntities(user, Box.of(user.getPos(), RANGE * 2, RANGE, RANGE * 2));
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getType().isIn(EntityTypeTags.UNDEAD)) {
                    var damageSource = CarmotBellDamageSource.of(world, user);
                    entity.damage(((ServerWorld) world), damageSource, Math.max(10.0f, livingEntity.getHealth() * 0.1f));
                    MythicParticleSystem.HEALING_DAMAGE.spawn(world, livingEntity.getPos());
                } else {
                    livingEntity.heal(Math.max(10.0f, livingEntity.getMaxHealth() * 0.1f));
                    MythicParticleSystem.HEALING_HEARTS.spawn(world, livingEntity.getPos());
                }
                stack.damage(1, user, PlayerEntity.getSlotForHand(hand));
            }
        });
        user.heal(Math.max(10.0f, user.getMaxHealth() * 0.1f));
        stack.damage(1, user, PlayerEntity.getSlotForHand(hand));
        MythicParticleSystem.HEALING_AREA.spawn(world, user.getPos(), RANGE);
        MythicParticleSystem.HEALING_HEARTS.spawn(world, user.getPos());
        user.getItemCooldownManager().set(stack, 480);
        world.playSound(user, user.getBlockPos(), RegisterSounds.CARMOT_BELL_RING, SoundCategory.PLAYERS);
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() != null && context.getPlayer().isSneaking()) {
            return super.useOnBlock(context);
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("tooltip.carmot_bell.info1"));
        tooltip.add(Text.translatable("tooltip.carmot_bell.info2"));
    }
}
