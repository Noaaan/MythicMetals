package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import nourl.mythicmetals.blocks.BanglumTntEntity;
import nourl.mythicmetals.blocks.BanglumNukeEntity;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterEntities {
    public static final EntityType<BanglumTntEntity> BANGLUM_TNT_ENTITY_TYPE;
    public static final EntityType<BanglumNukeEntity> BANGLUM_NUKE_ENTITY_TYPE;

    public static void init() {
        RegistryHelper.entityType("banglum_tnt", BANGLUM_TNT_ENTITY_TYPE);
        RegistryHelper.entityType("big_banglum_tnt", BANGLUM_NUKE_ENTITY_TYPE);

    }
    static {
       BANGLUM_TNT_ENTITY_TYPE = FabricEntityTypeBuilder.<BanglumTntEntity>create(
               SpawnGroup.MISC, BanglumTntEntity::new).dimensions(EntityDimensions.fixed(1,1)).build();

       BANGLUM_NUKE_ENTITY_TYPE = FabricEntityTypeBuilder.<BanglumNukeEntity>create(
               SpawnGroup.MISC, BanglumNukeEntity::new).dimensions(EntityDimensions.fixed(3,3)).build();
    }
}
