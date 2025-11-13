package com.mythicmetals.entity;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;

public class MythicEntities {
    public static final RegistryKey<EntityType<?>> BANGLUM_TNT_ENTITY_REGISTRY_KEY;
    public static final RegistryKey<EntityType<?>> BANGLUM_NUKE_ENTITY_REGISTRY_KEY;
    public static final RegistryKey<EntityType<?>> STAR_PLATINUM_ARROW_ENTITY_REGISTRY_KEY;
    public static final RegistryKey<EntityType<?>> RUNITE_ARROW_REGISTRY_KEY;
    public static final RegistryKey<EntityType<?>> BANGLUM_TNT_MINECART_ENTITY_REGISTRY_KEY;
    public static final RegistryKey<EntityType<?>> PALLADIUM_MINECART_ENTITY_REGISTRY_KEY;

    public static final EntityType<BanglumTntEntity> BANGLUM_TNT_ENTITY_TYPE;
    public static final EntityType<BanglumNukeEntity> BANGLUM_NUKE_ENTITY_TYPE;
    public static final EntityType<StarPlatinumArrowEntity> STAR_PLATINUM_ARROW_ENTITY_TYPE;
    public static final EntityType<RuniteArrowEntity> RUNITE_ARROW_ENTITY_TYPE;
    public static final EntityType<BanglumTntMinecartEntity> BANGLUM_TNT_MINECART_ENTITY_TYPE;
    public static final EntityType<PalladiumMinecartEntity> PALLADIUM_MINECART_ENTITY_TYPE;

    static {
       BANGLUM_TNT_ENTITY_REGISTRY_KEY = RegistryHelper.entityTypeKey("banglum_tnt");
       BANGLUM_NUKE_ENTITY_REGISTRY_KEY = RegistryHelper.entityTypeKey("banglum_nuke");
       STAR_PLATINUM_ARROW_ENTITY_REGISTRY_KEY = RegistryHelper.entityTypeKey("star_platinum_arrow");
       RUNITE_ARROW_REGISTRY_KEY = RegistryHelper.entityTypeKey("runite_arrow");
       BANGLUM_TNT_MINECART_ENTITY_REGISTRY_KEY = RegistryHelper.entityTypeKey("banglum_tnt_minecart");
       PALLADIUM_MINECART_ENTITY_REGISTRY_KEY = RegistryHelper.entityTypeKey("palladium_minecart");

        BANGLUM_TNT_ENTITY_TYPE = EntityType.Builder.<BanglumTntEntity>create(
            BanglumTntEntity::new, SpawnGroup.MISC).dimensions(1f, 1f).build(BANGLUM_TNT_ENTITY_REGISTRY_KEY);
        BANGLUM_NUKE_ENTITY_TYPE = EntityType.Builder.<BanglumNukeEntity>create(
            BanglumNukeEntity::new, SpawnGroup.MISC).dimensions(3f, 3f).build(BANGLUM_NUKE_ENTITY_REGISTRY_KEY);
        STAR_PLATINUM_ARROW_ENTITY_TYPE = EntityType.Builder.<StarPlatinumArrowEntity>create(
                StarPlatinumArrowEntity::new, SpawnGroup.MISC)
            .dimensions(.5f, .5f)
            .maxTrackingRange(4)
            .trackingTickInterval(20)
            .build(STAR_PLATINUM_ARROW_ENTITY_REGISTRY_KEY);
        RUNITE_ARROW_ENTITY_TYPE = EntityType.Builder.<RuniteArrowEntity>create(
                RuniteArrowEntity::new, SpawnGroup.MISC)
            .dimensions(0.5F, 0.5F)
            .maxTrackingRange(4)
            .trackingTickInterval(20)
            .build(RUNITE_ARROW_REGISTRY_KEY);
        BANGLUM_TNT_MINECART_ENTITY_TYPE = EntityType.Builder.<BanglumTntMinecartEntity>create(
                BanglumTntMinecartEntity::new, SpawnGroup.MISC)
            .dimensions(0.98f, 0.7f)
            .maxTrackingRange(8)
            .build(BANGLUM_TNT_MINECART_ENTITY_REGISTRY_KEY);
        PALLADIUM_MINECART_ENTITY_TYPE = EntityType.Builder.<PalladiumMinecartEntity>create(
                PalladiumMinecartEntity::new, SpawnGroup.MISC)
            .dimensions(0.98f, 0.7f)
            .maxTrackingRange(8)
            .makeFireImmune()
            .build(PALLADIUM_MINECART_ENTITY_REGISTRY_KEY);
    }

    public static void init() {
        RegistryHelper.entityType(BANGLUM_TNT_MINECART_ENTITY_REGISTRY_KEY, BANGLUM_TNT_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType(PALLADIUM_MINECART_ENTITY_REGISTRY_KEY, PALLADIUM_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType(BANGLUM_TNT_ENTITY_REGISTRY_KEY, BANGLUM_TNT_ENTITY_TYPE);
        RegistryHelper.entityType(BANGLUM_NUKE_ENTITY_REGISTRY_KEY, BANGLUM_NUKE_ENTITY_TYPE);
        RegistryHelper.entityType(STAR_PLATINUM_ARROW_ENTITY_REGISTRY_KEY, STAR_PLATINUM_ARROW_ENTITY_TYPE);
        RegistryHelper.entityType(RUNITE_ARROW_REGISTRY_KEY, RUNITE_ARROW_ENTITY_TYPE);
    }
}
