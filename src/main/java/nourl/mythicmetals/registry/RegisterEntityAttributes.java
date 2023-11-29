package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterEntityAttributes {
    public static final EntityAttribute ELYTRA_ROCKET_SPEED = new ClampedEntityAttribute("attribute.name.generic.mythicmetals.elytra_rocket_speed", 1, 0, 1024).setTracked(true);
    public static final EntityAttribute FIRE_VULNERABILITY = new ClampedEntityAttribute("attribute.name.generic.mythicmetals.fire_vulnerability", 0, 0, 2048).setTracked(true);

    public static void init() {
        Registry.register(Registries.ATTRIBUTE, RegistryHelper.id("elytra_rocket_speed"), ELYTRA_ROCKET_SPEED);
        Registry.register(Registries.ATTRIBUTE, RegistryHelper.id("fire_vulnerability"), FIRE_VULNERABILITY);

        var builder = PlayerEntity.createPlayerAttributes();
        builder.add(ELYTRA_ROCKET_SPEED).add(FIRE_VULNERABILITY);
        FabricDefaultAttributeRegistry.register(EntityType.PLAYER, builder);
    }
}
