package com.mythicmetals.registry;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.poi.PointOfInterestType;

public class RegisterPointOfInterests {
    public static final PointOfInterestType CONDUIT_POWERED_BLOCK = PointOfInterestHelper.register(
        RegistryHelper.id("conduit_powered_block"), 0, 1,
        MythicBlocks.AQUARIUM_RESONATOR
    );

    public static void init() {
    }
}
