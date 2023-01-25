package nourl.mythicmetals.registry;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterSounds implements AutoRegistryContainer<SoundEvent> {

    public static SoundEvent EQUIP_RUNITE = SoundEvent.of(RegistryHelper.id("equip_runite"));
    public static SoundEvent DOG = SoundEvent.of(RegistryHelper.id("music_disc.dog4"));
    public static SoundEvent MORKITE_ORE_BREAK = SoundEvent.of(RegistryHelper.id("morkite_ore_break"));
    public static SoundEvent DEEPSLATE_MORKITE_ORE_BREAK = SoundEvent.of(RegistryHelper.id("deepslate_morkite_ore_break"));

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

    @Override
    public Registry<SoundEvent> getRegistry() {
        return Registries.SOUND_EVENT;
    }

    @Override
    public Class<SoundEvent> getTargetFieldType() {
        return SoundEvent.class;
    }
}

