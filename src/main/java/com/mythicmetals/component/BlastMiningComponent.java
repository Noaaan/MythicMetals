package com.mythicmetals.component;

import com.mythicmetals.misc.*;
import com.mythicmetals.registry.RegisterCriteria;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import java.util.function.Consumer;

public record BlastMiningComponent(int depth) implements TooltipAppender {

    public static final StructEndec<BlastMiningComponent> ENDEC = StructEndecBuilder.of(
        StructEndec.INT.fieldOf("depth", BlastMiningComponent::depth),
        BlastMiningComponent::new
    );

    public ActionResult trigger(ItemUsageContext context) {
        boolean shouldPass = false;
        var world = context.getWorld();
        var player = context.getPlayer();
        var stack = context.getStack();

        if (player != null && !isCoolingDown(player, stack) && !world.isClient()) {

            var iterator = BlockBreaker.findBlocks(context, depth);
            for (BlockPos blockPos : iterator) {
                if (BlockBreaker.isProtected(world, blockPos, player.getGameProfile(), player)) {
                    continue;
                }
                if (isCorrectForDrops(stack, world.getBlockState(blockPos))) {
                    WorldOps.breakBlockWithItem(world, blockPos, stack, player);
                    stack.damage(2, player, EquipmentSlot.MAINHAND);
                    shouldPass = true;
                }
            }

        }

        if (shouldPass) {
            var pos = context.getBlockPos();
            var facing = context.getSide().getOpposite();
            var pos2 = context.getBlockPos().offset(facing, depth);

            MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3d.of(pos), Vec3d.of(pos2));
            WorldOps.playSound(world, pos, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.PLAYERS);

            RegisterCriteria.USED_BLAST_MINING.trigger((ServerPlayerEntity) player);
            player.getItemCooldownManager().set(stack, 100);
            return ActionResult.SUCCESS_SERVER;
        }

        return ActionResult.FAIL;
    }

    public static boolean isCoolingDown(LivingEntity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity player) {
            return player.getItemCooldownManager().isCoolingDown(stack);
        }
        return false;
    }

    public static boolean isCorrectForDrops(ItemStack stack, BlockState state) {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        return toolComponent != null && toolComponent.isCorrectForDrops(state);
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        tooltip.accept(Text.translatable("abilities.mythicmetals.blast_mining").setStyle(UsefulSingletonForColorUtil.MetalColors.GOLD_STYLE));
    }
}
