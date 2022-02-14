package nourl.mythicmetals.registry;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterSounds {

    public static SoundEvent EQUIP_RUNITE = new SoundEvent(RegistryHelper.id("equip_runite"));
    public static SoundEvent DOG = new SoundEvent(RegistryHelper.id("music_disc.dog4"));
    public static SoundEvent MORKITE_ORE_BREAK = new SoundEvent(RegistryHelper.id("morkite_ore_break"));
    public static SoundEvent DEEPSLATE_MORKITE_ORE_BREAK = new SoundEvent(RegistryHelper.id("deepslate_morkite_ore_break"));

    public static final BlockSoundGroup MORKITE_ORE = new BlockSoundGroup(1.0F, 1.0F,
            MORKITE_ORE_BREAK,
            SoundEvents.BLOCK_DRIPSTONE_BLOCK_STEP,
            SoundEvents.BLOCK_DRIPSTONE_BLOCK_PLACE,
            SoundEvents.BLOCK_DRIPSTONE_BLOCK_HIT,
            SoundEvents.BLOCK_DRIPSTONE_BLOCK_FALL);
    public static final BlockSoundGroup DEEPSLATE_MORKITE_ORE = new BlockSoundGroup(1.0F, 1.0F,
            DEEPSLATE_MORKITE_ORE_BREAK,
            SoundEvents.BLOCK_DEEPSLATE_STEP,
            SoundEvents.BLOCK_DEEPSLATE_PLACE,
            SoundEvents.BLOCK_DEEPSLATE_HIT,
            SoundEvents.BLOCK_DEEPSLATE_FALL);

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, EQUIP_RUNITE.getId(), EQUIP_RUNITE);
        Registry.register(Registry.SOUND_EVENT, DOG.getId(), DOG);
        Registry.register(Registry.SOUND_EVENT, MORKITE_ORE_BREAK.getId(), MORKITE_ORE_BREAK);
        Registry.register(Registry.SOUND_EVENT, DEEPSLATE_MORKITE_ORE_BREAK.getId(), DEEPSLATE_MORKITE_ORE_BREAK);
    }
}

