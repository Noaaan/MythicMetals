package com.mythicmetals.item.tools;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.damage.CarmotBellDamageSource;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class CarmotBellItem extends BlockItem {

    public static final double RANGE = 6.0;

    public CarmotBellItem(Properties settings) {
        super(MythicBlocks.CARMOT_BELL_BLOCK, settings);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        var entities = world.getEntities(user, AABB.ofSize(user.position(), RANGE * 2, RANGE, RANGE * 2));
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getType().is(EntityTypeTags.UNDEAD)) {
                    var damageSource = CarmotBellDamageSource.of(world, user);
                    entity.hurtServer(((ServerLevel) world), damageSource, Math.max(10.0f, livingEntity.getHealth() * 0.1f));
                    MythicParticleSystem.HEALING_DAMAGE.spawn(world, livingEntity.position());
                } else {
                    livingEntity.heal(Math.max(10.0f, livingEntity.getMaxHealth() * 0.1f));
                    MythicParticleSystem.HEALING_HEARTS.spawn(world, livingEntity.position());
                }
                stack.hurtAndBreak(1, user, hand);
            }
        });
        user.heal(Math.max(10.0f, user.getMaxHealth() * 0.1f));
        stack.hurtAndBreak(1, user, hand);
        MythicParticleSystem.HEALING_AREA.spawn(world, user.position(), RANGE);
        MythicParticleSystem.HEALING_HEARTS.spawn(world, user.position());
        user.getCooldowns().addCooldown(stack, 480);
        world.playSound(user, user.blockPosition(), RegisterSounds.CARMOT_BELL_RING, SoundSource.PLAYERS);
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        if (player != null && player.isShiftKeyDown()) {
            return super.useOn(context);
        }
        return InteractionResult.PASS;
    }
// FIXME - Tooltips
//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
//        super.appendHoverText(stack, context, tooltip, type);
//        tooltip.add(Component.translatable("tooltip.carmot_bell.info1"));
//        tooltip.add(Component.translatable("tooltip.carmot_bell.info2"));
//    }
}
