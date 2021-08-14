package nourl.mythicmetals.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class RegisterSounds {

    public static SoundEvent EQUIP_RUNITE = new SoundEvent(RegistryHelper.id("equip_runite"));

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, EQUIP_RUNITE.getId(), EQUIP_RUNITE);
    }
}

