package nourl.mythicmetals.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.item.tools.MythrilDrill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "onUserDamaged", at = @At("HEAD"))
    private static void mythicmetals$addSpikedHelm(LivingEntity user, Entity attacker, CallbackInfo ci) {

        for (ItemStack armorItems : user.getArmorItems()) {
            if (Abilities.SPIKED_HELM.getItems().contains(armorItems.getItem())) {
                if (armorItems.getItem() != null)
                    armorItems.damage(1, user, player -> player.sendEquipmentBreakStatus(EquipmentSlot.HEAD));

                if (attacker != null)
                    attacker.damage(user.getWorld().getDamageSources().thorns(user), 2.2F);
            }

        }
    }

    @Inject(method = "getDepthStrider", at = @At("HEAD"), cancellable = true)
    private static void mythicmetals$addDepthStrider(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {

        for (ItemStack armorItems : entity.getArmorItems()) {
            if (Abilities.DEPTH_STRIDER.getItems().contains(armorItems.getItem()))
                cir.setReturnValue(Abilities.DEPTH_STRIDER.getLevel());
        }
    }

    @Inject(method = "getRespiration", at = @At(value = "RETURN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEquipmentLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/entity/LivingEntity;)I"), cancellable = true)
    private static void mythicmetals$increaseRespiration(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int level = cir.getReturnValue();
        int change = 0;
        for (ItemStack armorItems : entity.getArmorItems()) {
            if (Abilities.RESPIRATION.getItems().contains(armorItems.getItem())) {
                change += Abilities.RESPIRATION.getLevel();
            }
            if (Abilities.BETTER_RESPIRATION.getItems().contains(armorItems.getItem())) {
                change += Abilities.BETTER_RESPIRATION.getLevel();
            }
        }
        if (change > 0) {
            cir.setReturnValue(level + change);
        }
    }

    @Inject(method = "hasAquaAffinity", at = @At("HEAD"), cancellable = true)
    private static void mythicmetals$addAquaAffinity(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {

        for (ItemStack armorItems : entity.getArmorItems()) {
            if (Abilities.AQUA_AFFINITY.getItems().contains(armorItems.getItem())) {
                cir.setReturnValue(true);
            }
        }
        for (ItemStack mainHand : entity.getHandItems()) {
            if (Abilities.AQUA_AFFINITY.getItems().contains(mainHand.getItem())) {
                cir.setReturnValue(true);
            }
            if (mainHand.getItem().equals(MythicTools.MYTHRIL_DRILL) && MythrilDrill.hasUpgradeItem(mainHand, MythicItems.Mats.AQUARIUM_PEARL)) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "getFireAspect", at = @At("HEAD"), cancellable = true)
    private static void mythicmetals$addFireAspect(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {

        for (ItemStack mainHand : entity.getHandItems()) {
            if (Abilities.FIRE_ASPECT.getItems().contains(mainHand.getItem()))
                cir.setReturnValue(Abilities.FIRE_ASPECT.getLevel());
        }
    }

    @Inject(method = "getLooting", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$increaseLooting(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int level = cir.getReturnValue();
        int change = 0;
        for (ItemStack mainHand : entity.getHandItems()) {
            if (Abilities.BONUS_LOOTING.getItems().contains(mainHand.getItem()))
                change += Abilities.BONUS_LOOTING.getLevel();
        }

        if (change > 0) {
            cir.setReturnValue(level + change);
        }
    }

    @Inject(method = "getEfficiency", at = @At("RETURN"), cancellable = true)
    private static void mythicmetals$increaseEfficiency(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int level = cir.getReturnValue();
        int change = 0;
        for (ItemStack mainHand : entity.getHandItems()) {
            if (mainHand.getItem() instanceof MythrilDrill drill && drill.hasFuel(mainHand) && drill.isActive(mainHand)) {
                change += 1;
            }
        }

        if (change > 0) {
            cir.setReturnValue(level + change);
        }
    }

    @Inject(method = "getProtectionAmount", at = @At("TAIL"), cancellable = true)
    private static void mythicmetals$damageReduction(Iterable<ItemStack> equipment, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        // Make sure that there is any gear to check
        if (!equipment.iterator().hasNext()) return;

        var amount = cir.getReturnValue();
        int change = 0;

        for (var gear : equipment) {
            if (Abilities.BLAST_PROTECTION.getItems().contains(gear.getItem()) && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
                change += Abilities.BLAST_PROTECTION.getLevel() * 2;
            }

            if (Abilities.BLAST_PADDING.getItems().contains(gear.getItem()) && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
                change += Abilities.BLAST_PADDING.getLevel() * 2;
            }

            if (Abilities.PROJECTILE_PROTECTION.getItems().contains(gear.getItem()) && source.isIn(DamageTypeTags.IS_PROJECTILE)) {
                change += Abilities.PROJECTILE_PROTECTION.getLevel() * 2;
            }

            if (Abilities.FEATHER_FALLING.getItems().contains(gear.getItem()) && source.isIn(DamageTypeTags.IS_FALL)) {
                change += Abilities.FEATHER_FALLING.getLevel() * 3;
            }

            if (Abilities.FIRE_PROTECTION.getItems().contains(gear.getItem()) && source.isIn(DamageTypeTags.IS_FIRE)) {
                change += Abilities.FIRE_PROTECTION.getLevel() * 2;
            }
        }

        if (change != 0)
            cir.setReturnValue(amount + change);
    }

    @Inject(method = "getAttackDamage", at = @At("TAIL"), cancellable = true)
    private static void mythicmetals$increaseDamage(ItemStack stack, EntityGroup group, CallbackInfoReturnable<Float> cir) {
        var amount = cir.getReturnValue();
        int change = 0;
        if (Abilities.SMITE.getItems().contains(stack.getItem()) && group == EntityGroup.UNDEAD) {
            change += Abilities.SMITE.getLevel() * 2.5f;
        }
        if (change != 0) {
            cir.setReturnValue(amount + change);
        }
    }

    @Inject(method = "getKnockback", at = @At("TAIL"), cancellable = true)
    private static void mythicmetals$increaseKnockback(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        var amount = cir.getReturnValue();
        for (ItemStack mainHand : entity.getHandItems()) {
            if (Abilities.KNOCKBACK.getItems().contains(mainHand.getItem()))
                cir.setReturnValue(amount + Abilities.KNOCKBACK.getLevel());
        }
    }

}
