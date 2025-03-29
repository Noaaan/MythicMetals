package com.mythicmetals.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import com.mythicmetals.misc.RegistryHelper;

public class MythicEntities {
    public static final EntityType<BanglumTntEntity> BANGLUM_TNT_ENTITY_TYPE;
    public static final EntityType<BanglumNukeEntity> BANGLUM_NUKE_ENTITY_TYPE;
    public static final EntityType<StarPlatinumArrowEntity> STAR_PLATINUM_ARROW_ENTITY_TYPE;
    public static final EntityType<RuniteArrowEntity> RUNITE_ARROW_ENTITY_TYPE;
    public static final EntityType<BanglumTntMinecartEntity> BANGLUM_TNT_MINECART_ENTITY_TYPE;
    public static final EntityType<PalladiumMinecartEntity> PALLADIUM_MINECART_ENTITY_TYPE;

    public static void init() {
        RegistryHelper.entityType("banglum_tnt_minecart", BANGLUM_TNT_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType("palladium_minecart", PALLADIUM_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType("banglum_tnt", BANGLUM_TNT_ENTITY_TYPE);
        RegistryHelper.entityType("banglum_nuke", BANGLUM_NUKE_ENTITY_TYPE);
        RegistryHelper.entityType("star_platinum_arrow", STAR_PLATINUM_ARROW_ENTITY_TYPE);
        RegistryHelper.entityType("runite_arrow", RUNITE_ARROW_ENTITY_TYPE);

    }

    static {
        BANGLUM_TNT_MINECART_ENTITY_TYPE = EntityType.Builder.<BanglumTntMinecartEntity>create(
                BanglumTntMinecartEntity::new, SpawnGroup.MISC)
            .dimensions(0.98f, 0.7f)
            .maxTrackingRange(8)
            .build();

        PALLADIUM_MINECART_ENTITY_TYPE = EntityType.Builder.<PalladiumMinecartEntity>create(
                PalladiumMinecartEntity::new, SpawnGroup.MISC)
            .dimensions(0.98f, 0.7f)
            .maxTrackingRange(8)
            .makeFireImmune()
            .build();

        BANGLUM_TNT_ENTITY_TYPE = EntityType.Builder.<BanglumTntEntity>create(
            BanglumTntEntity::new, SpawnGroup.MISC).dimensions(1f, 1f).build();

        BANGLUM_NUKE_ENTITY_TYPE = EntityType.Builder.<BanglumNukeEntity>create(
            BanglumNukeEntity::new, SpawnGroup.MISC).dimensions(3f, 3f).build();

        STAR_PLATINUM_ARROW_ENTITY_TYPE = EntityType.Builder.<StarPlatinumArrowEntity>create(
                StarPlatinumArrowEntity::new, SpawnGroup.MISC)
            .dimensions(.5f, .5f)
            .maxTrackingRange(4)
            .trackingTickInterval(20)
            .build();

        RUNITE_ARROW_ENTITY_TYPE = EntityType.Builder.<RuniteArrowEntity>create(
                RuniteArrowEntity::new, SpawnGroup.MISC)
            .dimensions(0.5F, 0.5F)
            .maxTrackingRange(4)
            .trackingTickInterval(20)
            .build();
    }
}
