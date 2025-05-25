package com.mythicmetals.item.tools;

import com.mythicmetals.misc.MythicParticleSystem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import java.util.List;

public class CarmotBellItem extends Item {

    public static final double RANGE = 6.0;

    public CarmotBellItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        var entities = world.getOtherEntities(user, Box.of(user.getPos(), RANGE * 2, RANGE, RANGE * 2));
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getType().isIn(EntityTypeTags.UNDEAD)) {
                    entity.damage(world.getDamageSources().magic(), Math.max(10.0f, livingEntity.getHealth() * 0.1f));
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
        user.getItemCooldownManager().set(this, 480);
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("tooltip.carmot_bell.info"));
    }
}
