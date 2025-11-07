package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import com.mythicmetals.entity.MythicEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @ModifyReturnValue(method = "getDamageAgainst", at = @At("RETURN"))
    private float mythicmetals$applyBonusDamage(float original, Entity target, float baseDamage, DamageSource damageSource) {
        if (target.getType().isIn(EntityTypeTags.UNDEAD)) {
            return (float) (original + this.getAttributeValue(MythicEntityAttributes.UNDEAD_BONUS_DAMAGE));
        }
        return original;
    }
}
