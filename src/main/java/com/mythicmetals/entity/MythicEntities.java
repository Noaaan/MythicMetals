package com.mythicmetals.entity;

import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class MythicEntities {
    public static final ResourceKey<EntityType<?>> BANGLUM_TNT_ENTITY_REGISTRY_KEY;
    public static final ResourceKey<EntityType<?>> BANGLUM_NUKE_ENTITY_REGISTRY_KEY;
    public static final ResourceKey<EntityType<?>> STAR_PLATINUM_ARROW_ENTITY_REGISTRY_KEY;
    public static final ResourceKey<EntityType<?>> RUNITE_ARROW_REGISTRY_KEY;
    public static final ResourceKey<EntityType<?>> BANGLUM_TNT_MINECART_ENTITY_REGISTRY_KEY;
    public static final ResourceKey<EntityType<?>> PALLADIUM_MINECART_ENTITY_REGISTRY_KEY;

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

        BANGLUM_TNT_ENTITY_TYPE = EntityType.Builder.<BanglumTntEntity>of(
            BanglumTntEntity::new, MobCategory.MISC)
            .sized(1f, 1f)
            .noLootTable()
            .fireImmune()
            .build(BANGLUM_TNT_ENTITY_REGISTRY_KEY);
        BANGLUM_NUKE_ENTITY_TYPE = EntityType.Builder.<BanglumNukeEntity>of(
            BanglumNukeEntity::new, MobCategory.MISC)
            .sized(3f, 3f)
            .noLootTable()
            .fireImmune()
            .build(BANGLUM_NUKE_ENTITY_REGISTRY_KEY);
        STAR_PLATINUM_ARROW_ENTITY_TYPE = EntityType.Builder.<StarPlatinumArrowEntity>of(
                StarPlatinumArrowEntity::new, MobCategory.MISC)
            .sized(.5f, .5f)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(STAR_PLATINUM_ARROW_ENTITY_REGISTRY_KEY);
        RUNITE_ARROW_ENTITY_TYPE = EntityType.Builder.<RuniteArrowEntity>of(
                RuniteArrowEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(RUNITE_ARROW_REGISTRY_KEY);
        BANGLUM_TNT_MINECART_ENTITY_TYPE = EntityType.Builder.<BanglumTntMinecartEntity>of(
                BanglumTntMinecartEntity::new, MobCategory.MISC)
            .sized(0.98f, 0.7f)
            .clientTrackingRange(8)
            .build(BANGLUM_TNT_MINECART_ENTITY_REGISTRY_KEY);
        PALLADIUM_MINECART_ENTITY_TYPE = EntityType.Builder.<PalladiumMinecartEntity>of(
                PalladiumMinecartEntity::new, MobCategory.MISC)
            .sized(0.98f, 0.7f)
            .clientTrackingRange(8)
            .fireImmune()
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
