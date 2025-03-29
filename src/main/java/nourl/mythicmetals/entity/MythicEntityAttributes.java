package nourl.mythicmetals.entity;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;
import nourl.mythicmetals.misc.RegistryHelper;

public class MythicEntityAttributes {
    public static final RegistryEntry<EntityAttribute> CARMOT_SHIELD = RegistryHelper.entityAttribute("carmot_shield", new ClampedEntityAttribute("attribute.name.generic.mythicmetals.carmot_shield", 0, 0, 2048).setTracked(true));
    public static final RegistryEntry<EntityAttribute> ELYTRA_ROCKET_SPEED = RegistryHelper.entityAttribute("elytra_rocket_speed", new ClampedEntityAttribute("attribute.name.generic.mythicmetals.elytra_rocket_speed", 1, 0, 1024).setTracked(true));
    public static final RegistryEntry<EntityAttribute> FIRE_VULNERABILITY = RegistryHelper.entityAttribute("fire_vulnerability", new ClampedEntityAttribute("attribute.name.generic.mythicmetals.fire_vulnerability", 0, 0, 2048).setTracked(true));

    public static void init() {
    }
}
