package com.mythicmetals.item.tools;

import com.mythicmetals.misc.CarmotBellHandler;
import com.mythicmetals.misc.MythicSoundEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.component.UseCooldown;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import java.util.Optional;

public class CarmotBellItem extends BlockItem {

    public CarmotBellItem(Block block, Properties settings) {
        super(block, settings.component(
            DataComponents.USE_COOLDOWN,
            new UseCooldown(CarmotBellHandler.COOLDOWN_USE_SECONDS, Optional.of(CarmotBellHandler.COOLDOWN_GROUP))
        ));
    }

    @Override
    public @NonNull InteractionResult use(@NonNull Level level, Player user, @NonNull InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        CarmotBellHandler.heal(level, user.position(), user);
        stack.hurtAndBreak(1, user, hand);
        user.getCooldowns().addCooldown(CarmotBellHandler.COOLDOWN_GROUP, CarmotBellHandler.COOLDOWN_TICKS);
        level.playSound(user, user.blockPosition(), MythicSoundEvents.CARMOT_BELL_RING, SoundSource.PLAYERS);
        return InteractionResult.SUCCESS;
    }

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        var player = context.getPlayer();
        if (player != null && player.isShiftKeyDown()) {
            return super.useOn(context);
        }
        return InteractionResult.PASS;
    }
}
