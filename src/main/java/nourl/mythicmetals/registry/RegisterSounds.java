package nourl.mythicmetals.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterSounds {

    public static SoundEvent EQUIP_RUNITE = new SoundEvent(RegistryHelper.id("equip_runite"));
    public static SoundEvent DOG = new SoundEvent(RegistryHelper.id("music_disc.dog4"));

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, EQUIP_RUNITE.getId(), EQUIP_RUNITE);
        Registry.register(Registry.SOUND_EVENT, DOG.getId(), DOG);
    }
}

