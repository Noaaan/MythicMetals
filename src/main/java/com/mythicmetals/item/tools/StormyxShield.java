package com.mythicmetals.item.tools;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.item.tools.carmot_staff.CarmotStaffItem;
import com.mythicmetals.registry.RegisterSounds;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import java.util.List;
import java.util.UUID;

public class StormyxShield extends ShieldItem {

    public static final Identifier PROJECTILE_MODIFIED = RegistryHelper.id("projectile_is_modified");
    public static final int MAGIC_DAMAGE_REDUCTION = 2;

    public StormyxShield(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundCategory.AMBIENT, 0.9F, 1.5F);
        stack.set(MythicDataComponents.IS_USED, false);
        if (user instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(stack.getItem(), 160);
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        var blockBox = Box.of(user.getPos().add(0, 1, 0), 8, 8, 8);
        var entities = world.getOtherEntities(user, blockBox);
        stack.set(MythicDataComponents.IS_USED, true);

        if (remainingUseTicks % 40 == 1) {
            WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_MAINTAIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
        }

        for (Entity entity : entities) {
            if (entity.getCommandTags().contains(PROJECTILE_MODIFIED.toString())) {
                return;
            }

            // Setting the owner of the trident to someone else would lead to shenanigans, don't do that
            if (entity instanceof TridentEntity trident) {
                var bounceVec = trident.getVelocity().multiply(-0.25, -0.25, -0.25);
                trident.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z);
                trident.returnTimer = 0;
                trident.addCommandTag(PROJECTILE_MODIFIED.toString());
            }
            // Special handling for ExplosiveProjectileEntities, like fireballs
            if (entity instanceof ExplosiveProjectileEntity projectile) {
                var bounceVec = projectile.getVelocity().multiply(-0.25, -0.25, -0.25);
                projectile.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z, 1.05F, 0.5F);
                projectile.setOwner(user);
                projectile.addCommandTag(PROJECTILE_MODIFIED.toString());
                stack.damage(2, user, EquipmentSlot.MAINHAND);
            }
            // Shulker bullet handling
            if (entity instanceof ShulkerBulletEntity projectile) {
                projectile.damage(world.getDamageSources().generic(), 1.0F);
            }
            // Default/Arrow handling
            else if (entity instanceof ProjectileEntity projectile) {
                // Bounce the projectiles in the direction the player is looking
                var bounceVec = projectile.getVelocity().multiply(-0.25, -0.25, -0.25);
                projectile.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z, 1.05F, 0.5F);
                projectile.addCommandTag(PROJECTILE_MODIFIED.toString());
                stack.damage(1, user, EquipmentSlot.MAINHAND);
            }
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
        return super.getMaxUseTime(stack, entity);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_BEGIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
        return TypedActionResult.consume(stack);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.STORMYX.getIngot());
    }

    public static boolean isNotOnCooldown(LivingEntity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity player) {
            return !player.getItemCooldownManager().isCoolingDown(stack.getItem());
        }
        return true;
    }

    public static AttributeModifiersComponent createStormyxShieldAttributes() {
        var modifier = new EntityAttributeModifier(RegistryHelper.id("stormyx_shield_magic_protection"), MAGIC_DAMAGE_REDUCTION, EntityAttributeModifier.Operation.ADD_VALUE);
        return AttributeModifiersComponent.builder()
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, AttributeModifierSlot.MAINHAND)
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, AttributeModifierSlot.OFFHAND)
            .build();
    }
}
