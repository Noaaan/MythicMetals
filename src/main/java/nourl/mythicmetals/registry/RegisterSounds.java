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
    public static SoundEvent EQUIP_ADAMANTITE = SoundEvent.of(RegistryHelper.id("equip_adamantite"));
    public static SoundEvent EQUIP_AQUARIUM = SoundEvent.of(RegistryHelper.id("equip_aquarium"));
    public static SoundEvent EQUIP_BANGLUM = SoundEvent.of(RegistryHelper.id("equip_banglum"));
    public static SoundEvent EQUIP_BRONZE = SoundEvent.of(RegistryHelper.id("equip_bronze"));
    public static SoundEvent EQUIP_CARMOT = SoundEvent.of(RegistryHelper.id("equip_carmot"));
    public static SoundEvent EQUIP_CELESTIUM = SoundEvent.of(RegistryHelper.id("equip_celestium"));
    public static SoundEvent EQUIP_CELESTIUM_ELYTRA = SoundEvent.of(RegistryHelper.id("equip_celestium_elytra"));
    public static SoundEvent EQUIP_COPPER = SoundEvent.of(RegistryHelper.id("equip_copper"));
    public static SoundEvent EQUIP_DURASTEEL = SoundEvent.of(RegistryHelper.id("equip_durasteel"));
    public static SoundEvent EQUIP_HALLOWED = SoundEvent.of(RegistryHelper.id("equip_hallowed"));
    public static SoundEvent EQUIP_KYBER = SoundEvent.of(RegistryHelper.id("equip_kyber"));
    public static SoundEvent EQUIP_LEGENDARY_BANGLUM = SoundEvent.of(RegistryHelper.id("equip_legendary_banglum"));
    public static SoundEvent EQUIP_METALLURGIUM = SoundEvent.of(RegistryHelper.id("equip_metallurgium"));
    public static SoundEvent EQUIP_MIDAS_GOLD = SoundEvent.of(RegistryHelper.id("equip_midas_gold"));
    public static SoundEvent EQUIP_MYTHRIL = SoundEvent.of(RegistryHelper.id("equip_mythril"));
    public static SoundEvent EQUIP_ORICHALCUM = SoundEvent.of(RegistryHelper.id("equip_orichalcum"));
    public static SoundEvent EQUIP_OSMIUM = SoundEvent.of(RegistryHelper.id("equip_osmium"));
    public static SoundEvent EQUIP_PALLADIUM = SoundEvent.of(RegistryHelper.id("equip_palladium"));
    public static SoundEvent EQUIP_PROMETHEUM = SoundEvent.of(RegistryHelper.id("equip_prometheum"));
    public static SoundEvent EQUIP_SILVER = SoundEvent.of(RegistryHelper.id("equip_silver"));
    public static SoundEvent EQUIP_STAR_PLATINUM = SoundEvent.of(RegistryHelper.id("equip_star_platinum"));
    public static SoundEvent EQUIP_STEEL = SoundEvent.of(RegistryHelper.id("equip_steel"));
    public static SoundEvent EQUIP_STORMYX = SoundEvent.of(RegistryHelper.id("equip_stormyx"));
    public static SoundEvent DOG = SoundEvent.of(RegistryHelper.id("music_disc.dog4"));
    public static SoundEvent MORKITE_ORE_BREAK = SoundEvent.of(RegistryHelper.id("morkite_ore_break"));
    public static SoundEvent DEEPSLATE_MORKITE_ORE_BREAK = SoundEvent.of(RegistryHelper.id("deepslate_morkite_ore_break"));
    public static SoundEvent CARMOT_STAFF_EMPTY = SoundEvent.of(RegistryHelper.id("carmot_staff_empty"));
    public static SoundEvent PROJECTILE_BARRIER_BEGIN = SoundEvent.of(RegistryHelper.id("projectile_barrier_begin"));
    public static SoundEvent PROJECTILE_BARRIER_MAINTAIN = SoundEvent.of(RegistryHelper.id("projectile_barrier_maintain"));
    public static SoundEvent PROJECTILE_BARRIER_END = SoundEvent.of(RegistryHelper.id("projectile_barrier_end"));
    public static SoundEvent BANGLUM_NUKE_IGNITE = SoundEvent.of(RegistryHelper.id("banglum_nuke_ignite"));
    public static SoundEvent BANGLUM_NUKE_EXPLOSION = SoundEvent.of(RegistryHelper.id("banglum_nuke_explosion"));
    public static SoundEvent MYTHRIL_DRILL_ACTIVATE = SoundEvent.of(RegistryHelper.id("mythril_drill_activate"));
    public static SoundEvent MYTHRIL_DRILL_DEACTIVATE = SoundEvent.of(RegistryHelper.id("mythril_drill_deactivate"));

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

