package com.mythicmetals.misc;

import com.mythicmetals.data.damage.CarmotBellDamageSource;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class CarmotBellHandler {

    public static final double RANGE = 8.0;
    public static final int COOLDOWN_TICKS = 240;
    public static final int COOLDOWN_USE_SECONDS = 12;
    public static final Identifier COOLDOWN_GROUP = RegistryHelper.id("carmot_bell_cooldowns");

    public static void heal(Level world, Vec3 centerPos, LivingEntity user) {
        if (world.isClientSide()) return;
        var entities = world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(centerPos, RANGE * 2, RANGE, RANGE * 2));
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.is(EntityTypeTags.UNDEAD)) {
                    entity.hurtServer(((ServerLevel) world), CarmotBellDamageSource.of(world, user), Math.max(10.0f, livingEntity.getHealth() * 0.1f));
                    MythicParticleSystem.HEALING_DAMAGE.spawn(world, livingEntity.position());
                } else {
                    livingEntity.heal(Math.max(10.0f, livingEntity.getMaxHealth() * 0.1f));
                    MythicParticleSystem.HEALING_HEARTS.spawn(world, livingEntity.position());
                }
            }
        });
        MythicParticleSystem.HEALING_AREA.spawn(world, centerPos, RANGE);
        MythicParticleSystem.HEALING_HEARTS.spawn(world, centerPos);
    }

    public static boolean isCoolingDown(Player player) {
        return player.getCooldowns().isOnCooldown(new ItemStack(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_BELL_ITEM)));
    }
}
