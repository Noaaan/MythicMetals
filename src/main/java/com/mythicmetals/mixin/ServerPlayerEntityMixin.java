package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import com.mythicmetals.entity.MythicEntityAttributes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerEntityMixin extends Player {

    public ServerPlayerEntityMixin(Level world, GameProfile gameProfile) {
        super(world, gameProfile);
    }

    @ModifyReturnValue(method = "getEnchantedDamage", at = @At("RETURN"))
    private float mythicmetals$applyBonusDamage(float original, Entity target, float baseDamage, DamageSource damageSource) {
        if (target.getType().is(EntityTypeTags.UNDEAD)) {
            return (float) (original + this.getAttributeValue(MythicEntityAttributes.UNDEAD_BONUS_DAMAGE));
        }
        return original;
    }
}
