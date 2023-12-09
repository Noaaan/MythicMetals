package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.world.poi.PointOfInterestType;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterPointOfInterests {
    public static final PointOfInterestType CONDUIT_POWERED_BLOCK = PointOfInterestHelper.register(RegistryHelper.id("conduit_powered_block"), 0, 1, MythicBlocks.Indev.AQUARIUM_STEWARD, MythicBlocks.Indev.AQUARIUM_RESONATOR);

    public static void init() {}
}
