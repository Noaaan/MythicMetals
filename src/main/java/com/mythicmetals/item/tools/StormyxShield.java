package com.mythicmetals.item.tools;

import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.ops.LevelOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import static com.mythicmetals.component.MythicDataComponents.WAS_USED;

public class StormyxShield extends ShieldItem {

    public static final int MAGIC_DAMAGE_REDUCTION = 2;
    public static final ProjectileDeflection STORMYX_SHIELD_DEFLECTOR = (projectile, hitEntity, random) -> {
        // Shulker bullet handling
        if (projectile instanceof ShulkerBullet bullet && !bullet.level().isClientSide()) {
            bullet.hurtServer((ServerLevel) bullet.level(), bullet.level().damageSources().generic(), 1.0F);
            return;
        }

        // If the projectile is simply too fast then it isn't deflected. It can still be blocked by the shield itself
        if (projectile.getDeltaMovement().length() <= 30.0) {
            float f = 170.0F + random.nextFloat() * 20.0F;
            projectile.setDeltaMovement(projectile.getDeltaMovement().scale(-0.5));
            projectile.setYRot(projectile.getYRot() + f);
            projectile.yRotO += f;
            // TODO - Review
            projectile.needsSync = true;
        }
    };

    public StormyxShield(Properties settings) {
        super(settings.repairable(MythicItems.STORMYX.getIngot()));
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        disableShield(stack, world, user);
        return super.releaseUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.onUseTick(world, user, stack, remainingUseTicks);

        if (remainingUseTicks % 40 == 1) {
            LevelOps.playSound(world, user.blockPosition(), RegisterSounds.PROJECTILE_BARRIER_MAINTAIN, SoundSource.AMBIENT, 1.0F, 1.5F);
            stack.hurtAndBreak(1, user, user.getUsedItemHand());
        }
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        var stack = user.getItemInHand(hand);
        user.startUsingItem(hand);
        stack.set(WAS_USED, true);
        LevelOps.playSound(world, user.blockPosition(), RegisterSounds.PROJECTILE_BARRIER_BEGIN, SoundSource.AMBIENT, 1.0F, 1.5F);
        return InteractionResult.CONSUME;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel serverLevel, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
        if (entity instanceof Player player && stack.has(WAS_USED)) {
            if (!player.getMainHandItem().equals(stack) && !player.getOffhandItem().equals(stack)) {
                stack.remove(WAS_USED);
                finishUsingItem(stack, serverLevel, player);
            }
        }

        super.inventoryTick(stack, serverLevel, entity, equipmentSlot);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        return disableShield(stack, world, user);
    }

    private ItemStack disableShield(ItemStack stack, Level world, LivingEntity user) {
        if (!world.isClientSide() && user instanceof Player player) {
            stack.remove(WAS_USED);
            player.getCooldowns().addCooldown(stack, 160);
        }
        LevelOps.playSound(world, user.blockPosition(), RegisterSounds.PROJECTILE_BARRIER_END, SoundSource.AMBIENT, 0.9F, 1.5F);
        return stack;
    }

    public static ItemAttributeModifiers createStormyxShieldAttributes() {
        var modifier = new AttributeModifier(RegistryHelper.id("stormyx_shield_magic_protection"), MAGIC_DAMAGE_REDUCTION, AttributeModifier.Operation.ADD_VALUE);
        return ItemAttributeModifiers.builder()
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, EquipmentSlotGroup.MAINHAND)
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, EquipmentSlotGroup.OFFHAND)
            .build();
    }

    // Don't update the item in hand if durability is repaired
    // Might affect mending as a side effect
    @Override
    public boolean allowComponentsUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamageValue() == newStack.getDamageValue();
    }
}
