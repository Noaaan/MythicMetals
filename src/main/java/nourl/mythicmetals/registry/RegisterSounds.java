package nourl.mythicmetals.registry;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterSounds implements AutoRegistryContainer<SoundEvent> {


    public static final SoundEvent EQUIP_RUNITE = SoundEvent.of(RegistryHelper.id("equip_runite"));
    public static final SoundEvent EQUIP_ADAMANTITE = SoundEvent.of(RegistryHelper.id("equip_adamantite"));
    public static final SoundEvent EQUIP_AQUARIUM = SoundEvent.of(RegistryHelper.id("equip_aquarium"));
    public static final SoundEvent EQUIP_BANGLUM = SoundEvent.of(RegistryHelper.id("equip_banglum"));
    public static final SoundEvent EQUIP_BRONZE = SoundEvent.of(RegistryHelper.id("equip_bronze"));
    public static final SoundEvent EQUIP_CARMOT = SoundEvent.of(RegistryHelper.id("equip_carmot"));
    public static final SoundEvent EQUIP_CELESTIUM = SoundEvent.of(RegistryHelper.id("equip_celestium"));
    public static final SoundEvent EQUIP_CELESTIUM_ELYTRA = SoundEvent.of(RegistryHelper.id("equip_celestium_elytra"));
    public static final SoundEvent EQUIP_COPPER = SoundEvent.of(RegistryHelper.id("equip_copper"));
    public static final SoundEvent EQUIP_DURASTEEL = SoundEvent.of(RegistryHelper.id("equip_durasteel"));
    public static final SoundEvent EQUIP_HALLOWED = SoundEvent.of(RegistryHelper.id("equip_hallowed"));
    public static final SoundEvent EQUIP_KYBER = SoundEvent.of(RegistryHelper.id("equip_kyber"));
    public static final SoundEvent EQUIP_LEGENDARY_BANGLUM = SoundEvent.of(RegistryHelper.id("equip_legendary_banglum"));
    public static final SoundEvent EQUIP_METALLURGIUM = SoundEvent.of(RegistryHelper.id("equip_metallurgium"));
    public static final SoundEvent EQUIP_MIDAS_GOLD = SoundEvent.of(RegistryHelper.id("equip_midas_gold"));
    public static final SoundEvent EQUIP_MYTHRIL = SoundEvent.of(RegistryHelper.id("equip_mythril"));
    public static final SoundEvent EQUIP_ORICHALCUM = SoundEvent.of(RegistryHelper.id("equip_orichalcum"));
    public static final SoundEvent EQUIP_OSMIUM = SoundEvent.of(RegistryHelper.id("equip_osmium"));
    public static final SoundEvent EQUIP_PALLADIUM = SoundEvent.of(RegistryHelper.id("equip_palladium"));
    public static final SoundEvent EQUIP_PROMETHEUM = SoundEvent.of(RegistryHelper.id("equip_prometheum"));
    public static final SoundEvent EQUIP_SILVER = SoundEvent.of(RegistryHelper.id("equip_silver"));
    public static final SoundEvent EQUIP_STAR_PLATINUM = SoundEvent.of(RegistryHelper.id("equip_star_platinum"));
    public static final SoundEvent EQUIP_STEEL = SoundEvent.of(RegistryHelper.id("equip_steel"));
    public static final SoundEvent EQUIP_STORMYX = SoundEvent.of(RegistryHelper.id("equip_stormyx"));
    public static final SoundEvent DOG = SoundEvent.of(RegistryHelper.id("music_disc.dog4"));
    public static final SoundEvent MORKITE_ORE_BREAK = SoundEvent.of(RegistryHelper.id("morkite_ore_break"));
    public static final SoundEvent DEEPSLATE_MORKITE_ORE_BREAK = SoundEvent.of(RegistryHelper.id("deepslate_morkite_ore_break"));
    public static final SoundEvent CARMOT_STAFF_EMPTY = SoundEvent.of(RegistryHelper.id("carmot_staff_empty"));
    public static final SoundEvent PROJECTILE_BARRIER_BEGIN = SoundEvent.of(RegistryHelper.id("projectile_barrier_begin"));
    public static final SoundEvent PROJECTILE_BARRIER_MAINTAIN = SoundEvent.of(RegistryHelper.id("projectile_barrier_maintain"));
    public static final SoundEvent PROJECTILE_BARRIER_END = SoundEvent.of(RegistryHelper.id("projectile_barrier_end"));
    public static final SoundEvent BANGLUM_NUKE_IGNITE = SoundEvent.of(RegistryHelper.id("banglum_nuke_ignite"));
    public static final SoundEvent BANGLUM_NUKE_EXPLOSION = SoundEvent.of(RegistryHelper.id("banglum_nuke_explosion"));
    public static final SoundEvent MYTHRIL_DRILL_ACTIVATE = SoundEvent.of(RegistryHelper.id("mythril_drill_activate"));
    public static final SoundEvent MYTHRIL_DRILL_DEACTIVATE = SoundEvent.of(RegistryHelper.id("mythril_drill_deactivate"));
    public static final SoundEvent MELODY = SoundEvent.of(RegistryHelper.id("melody"));

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

