package nourl.mythicmetals.registry;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterEntityAttributes {
    public static final EntityAttribute FIRE_VULNERABILITY = new ClampedEntityAttribute("attribute.name.generic.mythicmetals.fire_vulnerability", 0, 0, 2048);

    public static void init() {
        Registry.register(Registries.ATTRIBUTE, RegistryHelper.id("fire_vulnerability"), FIRE_VULNERABILITY);
    }
}
