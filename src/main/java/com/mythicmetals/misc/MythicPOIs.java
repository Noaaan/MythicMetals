package com.mythicmetals.misc;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public class MythicPOIs {
    public static final PoiType CONDUIT_POWERED_BLOCK = PointOfInterestHelper.register(
        RegistryHelper.id("conduit_powered_block"), 0, 1,
        MythicMaterials.AQUARIUM.extraBlocks().get(MythicResourceKeys.AQUARIUM_RESONATOR)
    );

    public static void init() {
    }
}
