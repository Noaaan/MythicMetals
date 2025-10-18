package com.mythicmetals.item.tools;

import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.mythicmetals.component.MythicDataComponents.WAS_USED;

public class StormyxShield extends ShieldItem {

    public static final int MAGIC_DAMAGE_REDUCTION = 2;
    public static final ProjectileDeflection STORMYX_SHIELD_DEFLECTOR = (projectile, hitEntity, random) -> {
        // Shulker bullet handling
        if (projectile instanceof ShulkerBulletEntity bullet && !bullet.getWorld().isClient) {
            bullet.damage((ServerWorld) bullet.getWorld(), bullet.getWorld().getDamageSources().generic(), 1.0F);
            return;
        }

        // If the projectile is simply too fast then it isn't deflected. It can still be blocked by the shield itself
        if (projectile.getVelocity().length() <= 30.0) {
            float f = 170.0F + random.nextFloat() * 20.0F;
            projectile.setVelocity(projectile.getVelocity().multiply(-0.5));
            projectile.setYaw(projectile.getYaw() + f);
            projectile.prevYaw += f;
            projectile.velocityDirty = true;
        }
    };

    public StormyxShield(Settings settings) {
        super(settings.repairable(MythicItems.STORMYX.getIngot()));
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        disableShield(stack, world, user);
        return super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        if (remainingUseTicks % 40 == 1) {
            WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_MAINTAIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
            stack.damage(1, user, LivingEntity.getSlotForHand(user.getActiveHand()));
        }
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        stack.set(WAS_USED, true);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_BEGIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
        return ActionResult.CONSUME;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && stack.contains(WAS_USED)) {
            if (!player.getMainHandStack().equals(stack) && !player.getOffHandStack().equals(stack)) {
                stack.remove(WAS_USED);
                finishUsing(stack, world, player);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return disableShield(stack, world, user);
    }

    private ItemStack disableShield(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            stack.remove(WAS_USED);
            player.getItemCooldownManager().set(stack, 160);
        }
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundCategory.AMBIENT, 0.9F, 1.5F);
        return stack;
    }

    public static AttributeModifiersComponent createStormyxShieldAttributes() {
        var modifier = new EntityAttributeModifier(RegistryHelper.id("stormyx_shield_magic_protection"), MAGIC_DAMAGE_REDUCTION, EntityAttributeModifier.Operation.ADD_VALUE);
        return AttributeModifiersComponent.builder()
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, AttributeModifierSlot.MAINHAND)
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, AttributeModifierSlot.OFFHAND)
            .build();
    }

    // Don't update the item in hand if durability is repaired
    // Might affect mending as a side effect
    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getDamage() == newStack.getDamage();
    }
}
