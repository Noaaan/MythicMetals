package nourl.mythicmetals.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import nourl.mythicmetals.abilities.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(method = "onUserDamaged", at = @At("HEAD"))
    private static void addSpikedHelm(LivingEntity user, Entity attacker, CallbackInfo ci) {

        for (ItemStack armorItems : user.getArmorItems()) {
            if (Abilities.SPIKED_HELM.getItems().contains(armorItems.getItem())) {
                if (armorItems.getItem() != null)
                    armorItems.damage(1, user, player -> player.sendEquipmentBreakStatus(EquipmentSlot.HEAD));

                if (attacker != null)
                    attacker.damage(DamageSource.thorns(user), 2.2F);
            }

        }
    }

    @Inject(method = "getDepthStrider", at = @At("HEAD"), cancellable = true)
    private static void addDepthStrider(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {

        for (ItemStack armorItems : entity.getArmorItems()) {
            if (Abilities.DEPTH_STRIDER.getItems().contains(armorItems.getItem()))
                cir.setReturnValue(Abilities.DEPTH_STRIDER.getLevel());
        }
    }

    @Inject(method = "getRespiration", at = @At("HEAD"), cancellable = true)
    private static void addRespiration(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {

        for (ItemStack armorItems : entity.getArmorItems()) {
            if (Abilities.RESPIRATION.getItems().contains(armorItems.getItem()))
                cir.setReturnValue(Abilities.RESPIRATION.getLevel());
        }
    }

    @Inject(method = "hasAquaAffinity", at = @At("HEAD"), cancellable = true)
    private static void addAquaAffinity(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {

        for (ItemStack armorItems : entity.getArmorItems()) {
            if (Abilities.AQUA_AFFINITY.getItems().contains(armorItems.getItem()))
                cir.setReturnValue(true);
        }
    }

    @Inject(method = "getFireAspect", at = @At("HEAD"), cancellable = true)
    private static void addFireAspect(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {

        for (ItemStack mainHand : entity.getItemsHand()) {
            if (Abilities.FIRE_ASPECT.getItems().contains(mainHand.getItem()))
                cir.setReturnValue(Abilities.FIRE_ASPECT.getLevel());
        }
    }

    @Inject(method = "getProtectionAmount", at = @At("TAIL"), cancellable = true)
    private static void damageReduction(Iterable<ItemStack> equipment, DamageSource source, CallbackInfoReturnable<Integer> cir) {

        var gear = equipment.iterator().next().getItem();
        var amount = cir.getReturnValue();
        int change = 0;

        if (Abilities.BLAST_PROTECTION.getItems().contains(gear) && source.isExplosive()) {
            change += Abilities.BLAST_PROTECTION.getLevel() * 2;
        }

        if (Abilities.BLAST_PADDING.getItems().contains(gear) && source.isExplosive()) {
            change += Abilities.BLAST_PROTECTION.getLevel() * 2;
        }

        if (Abilities.PROJECTILE_PROTECTION.getItems().contains(gear) && source.isProjectile()) {
            change += Abilities.PROJECTILE_PROTECTION.getLevel() * 2;
        }

        if (Abilities.FEATHER_FALLING.getItems().contains(gear) && source.isFromFalling()) {
            change += Abilities.FEATHER_FALLING.getLevel() * 3;
        }

        if (change != 0)
        cir.setReturnValue(amount + change);
    }

    @Inject(method = "getAttackDamage", at = @At("TAIL"), cancellable = true)
    private static void increaseDamage(ItemStack stack, EntityGroup group, CallbackInfoReturnable<Float> cir) {
        var amount = cir.getReturnValue();
        int change = 0;
        if (Abilities.SMITE.getItems().contains(stack.getItem()) && group == EntityGroup.UNDEAD) {
            change += Abilities.SMITE.getLevel() * 2.5f;
        }
        if (change != 0)
            cir.setReturnValue(amount + change);
    }

    @Inject(method = "getKnockback", at = @At("TAIL"), cancellable = true)
    private static void increaseKnockback(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        var amount = cir.getReturnValue();
        for (ItemStack mainHand : entity.getItemsHand()) {
            if (Abilities.KNOCKBACK.getItems().contains(mainHand.getItem()))
                cir.setReturnValue(amount + Abilities.KNOCKBACK.getLevel());
        }
    }

}
