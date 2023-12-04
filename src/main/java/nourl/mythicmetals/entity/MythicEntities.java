package nourl.mythicmetals.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import nourl.mythicmetals.misc.RegistryHelper;

public class MythicEntities {
    public static final EntityType<BanglumTntEntity> BANGLUM_TNT_ENTITY_TYPE;
    public static final EntityType<BanglumNukeEntity> BANGLUM_NUKE_ENTITY_TYPE;
    public static final EntityType<StarPlatinumArrowEntity> STAR_PLATINUM_ARROW_ENTITY_TYPE;
    public static final EntityType<RuniteArrowEntity> RUNITE_ARROW_ENTITY_TYPE;
    public static final EntityType<BanglumTntMinecartEntity> BANGLUM_TNT_MINECART_ENTITY_TYPE;

    public static void init() {
        RegistryHelper.entityType("banglum_tnt_minecart", BANGLUM_TNT_MINECART_ENTITY_TYPE);
        RegistryHelper.entityType("banglum_tnt", BANGLUM_TNT_ENTITY_TYPE);
        RegistryHelper.entityType("banglum_nuke", BANGLUM_NUKE_ENTITY_TYPE);
        RegistryHelper.entityType("star_platinum_arrow", STAR_PLATINUM_ARROW_ENTITY_TYPE);
        RegistryHelper.entityType("runite_arrow", RUNITE_ARROW_ENTITY_TYPE);

    }
    static {
        BANGLUM_TNT_MINECART_ENTITY_TYPE = FabricEntityTypeBuilder.<BanglumTntMinecartEntity>create(
                SpawnGroup.MISC, BanglumTntMinecartEntity::new).dimensions(new EntityDimensions(0.98F, 0.7F, false)).trackRangeBlocks(8).build();
       BANGLUM_TNT_ENTITY_TYPE = FabricEntityTypeBuilder.<BanglumTntEntity>create(
               SpawnGroup.MISC, BanglumTntEntity::new).dimensions(EntityDimensions.fixed(1,1)).build();

       BANGLUM_NUKE_ENTITY_TYPE = FabricEntityTypeBuilder.<BanglumNukeEntity>create(
               SpawnGroup.MISC, BanglumNukeEntity::new).dimensions(EntityDimensions.fixed(3,3)).build();

       STAR_PLATINUM_ARROW_ENTITY_TYPE = FabricEntityTypeBuilder.<StarPlatinumArrowEntity>create(
               SpawnGroup.MISC, StarPlatinumArrowEntity::new)
                       .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
               .trackRangeBlocks(4)
               .trackedUpdateRate(20).build();

        RUNITE_ARROW_ENTITY_TYPE = FabricEntityTypeBuilder.<RuniteArrowEntity>create(
                        SpawnGroup.MISC, RuniteArrowEntity::new)
                .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                .trackRangeBlocks(4)
                .trackedUpdateRate(20).build();
    }
}
