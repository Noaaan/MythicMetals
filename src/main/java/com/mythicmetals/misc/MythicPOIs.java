package com.mythicmetals.misc;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import java.util.Set;

public class MythicPOIs {
    public static final PoiType CONDUIT_POWERED_BLOCK = new PoiType(
        Set.of(
            MythicMaterials.AQUARIUM.extraBlocks().get(MythicResourceKeys.AQUARIUM_RESONATOR).defaultBlockState()
        ),
        0,
        1
    );

    public static void init() {
        Registry.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, RegistryHelper.id("conduit_powered_block"), CONDUIT_POWERED_BLOCK);
    }
}
