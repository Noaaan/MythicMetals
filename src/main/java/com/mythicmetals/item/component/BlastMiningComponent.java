package com.mythicmetals.item.component;

import com.mythicmetals.misc.*;
import com.mythicmetals.data.MythicCriteriaTriggers;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.ops.LevelOps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.NameAndId;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import java.util.function.Consumer;

public record BlastMiningComponent(int depth) implements TooltipProvider {

    public static final StructEndec<BlastMiningComponent> ENDEC = StructEndecBuilder.of(
        StructEndec.INT.fieldOf("depth", BlastMiningComponent::depth),
        BlastMiningComponent::new
    );

    public InteractionResult trigger(UseOnContext context) {
        boolean shouldPass = false;
        var world = context.getLevel();
        var player = context.getPlayer();
        var stack = context.getItemInHand();

        if (player != null && !isCoolingDown(player, stack) && !world.isClientSide()) {

            var iterator = BlockBreaker.findBlocks(context, depth);
            for (BlockPos blockPos : iterator) {
                if (BlockBreaker.isProtected(world, blockPos, new NameAndId(player.getGameProfile()), player)) {
                    continue;
                }
                if (isCorrectForDrops(stack, world.getBlockState(blockPos))) {
                    LevelOps.breakBlockWithItem(world, blockPos, stack, player);
                    stack.hurtAndBreak(2, player, EquipmentSlot.MAINHAND);
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getClickedPos();
            var facing = context.getClickedFace().getOpposite();
            var pos2 = context.getClickedPos().relative(facing, depth);

            MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3.atLowerCornerOf(pos), Vec3.atLowerCornerOf(pos2));
            LevelOps.playSound(world, pos, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.PLAYERS);

            MythicCriteriaTriggers.USED_BLAST_MINING.trigger((ServerPlayer) player);
            player.getCooldowns().addCooldown(stack, 100);
            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.FAIL;
    }

    public static boolean isCoolingDown(LivingEntity entity, ItemStack stack) {
        if (entity instanceof Player player) {
            return player.getCooldowns().isOnCooldown(stack);
        }
        return false;
    }

    public static boolean isCorrectForDrops(ItemStack stack, BlockState state) {
        Tool toolComponent = stack.get(DataComponents.TOOL);
        return toolComponent != null && toolComponent.isCorrectForDrops(state);
    }

    @Override
    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> tooltip, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
        tooltip.accept(Component.translatable("abilities.mythicmetals.blast_mining").setStyle(UsefulSingletonForColorUtil.MetalColors.GOLD_STYLE));
    }
}
