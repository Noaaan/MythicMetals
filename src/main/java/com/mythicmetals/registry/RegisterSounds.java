package com.mythicmetals.registry;

import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class RegisterSounds implements AutoRegistryContainer<SoundEvent> {

    public static final SoundEvent EQUIP_RUNITE = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_runite"));
    public static final SoundEvent EQUIP_ADAMANTITE = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_adamantite"));
    public static final SoundEvent EQUIP_AQUARIUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_aquarium"));
    public static final SoundEvent EQUIP_BANGLUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_banglum"));
    public static final SoundEvent EQUIP_BRONZE = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_bronze"));
    public static final SoundEvent EQUIP_CARMOT = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_carmot"));
    public static final SoundEvent EQUIP_CELESTIUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_celestium"));
    public static final SoundEvent EQUIP_CELESTIUM_ELYTRA = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_celestium_elytra"));
    public static final SoundEvent EQUIP_COPPER = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_copper"));
    public static final SoundEvent EQUIP_DURASTEEL = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_durasteel"));
    public static final SoundEvent EQUIP_HALLOWED = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_hallowed"));
    public static final SoundEvent EQUIP_KYBER = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_kyber"));
    public static final SoundEvent EQUIP_LEGENDARY_BANGLUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_legendary_banglum"));
    public static final SoundEvent EQUIP_METALLURGIUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_metallurgium"));
    public static final SoundEvent EQUIP_MIDAS_GOLD = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_midas_gold"));
    public static final SoundEvent EQUIP_MYTHRIL = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_mythril"));
    public static final SoundEvent EQUIP_ORICHALCUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_orichalcum"));
    public static final SoundEvent EQUIP_OSMIUM_CHAINMAIL = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_osmium_chainmail"));
    public static final SoundEvent EQUIP_OSMIUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_osmium"));
    public static final SoundEvent EQUIP_PALLADIUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_palladium"));
    public static final SoundEvent EQUIP_PROMETHEUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_prometheum"));
    public static final SoundEvent EQUIP_SILVER = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_silver"));
    public static final SoundEvent EQUIP_STAR_PLATINUM = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_star_platinum"));
    public static final SoundEvent EQUIP_STEEL = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_steel"));
    public static final SoundEvent EQUIP_STORMYX = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_stormyx"));
    public static final SoundEvent EQUIP_TIDESINGER = SoundEvent.createVariableRangeEvent(RegistryHelper.id("equip_tidesinger"));
    public static final SoundEvent MORKITE_ORE_BREAK = SoundEvent.createVariableRangeEvent(RegistryHelper.id("morkite_ore_break"));
    public static final SoundEvent DEEPSLATE_MORKITE_ORE_BREAK = SoundEvent.createVariableRangeEvent(RegistryHelper.id("deepslate_morkite_ore_break"));
    public static final SoundEvent PROJECTILE_BARRIER_BEGIN = SoundEvent.createVariableRangeEvent(RegistryHelper.id("projectile_barrier_begin"));
    public static final SoundEvent PROJECTILE_BARRIER_MAINTAIN = SoundEvent.createVariableRangeEvent(RegistryHelper.id("projectile_barrier_maintain"));
    public static final SoundEvent PROJECTILE_BARRIER_END = SoundEvent.createVariableRangeEvent(RegistryHelper.id("projectile_barrier_end"));
    public static final SoundEvent BANGLUM_NUKE_IGNITE = SoundEvent.createVariableRangeEvent(RegistryHelper.id("banglum_nuke_ignite"));
    public static final SoundEvent BANGLUM_NUKE_EXPLOSION = SoundEvent.createVariableRangeEvent(RegistryHelper.id("banglum_nuke_explosion"));
    public static final SoundEvent MELODY = SoundEvent.createVariableRangeEvent(RegistryHelper.id("melody"));
    public static final SoundEvent CARMOT_BELL_RING = SoundEvent.createVariableRangeEvent(RegistryHelper.id("carmot_bell_ring"));
    public static final SoundEvent CARMOT_BELL_DING = SoundEvent.createVariableRangeEvent(RegistryHelper.id("carmot_bell_ding"));
    public static final SoundEvent CARMOT_BELL_DING_PLAIN = SoundEvent.createVariableRangeEvent(RegistryHelper.id("carmot_bell_ding_plain"));

    public static final SoundType MORKITE_ORE = new SoundType(1.0F, 1.0F,
        MORKITE_ORE_BREAK,
        SoundEvents.DRIPSTONE_BLOCK_STEP,
        SoundEvents.DRIPSTONE_BLOCK_PLACE,
        SoundEvents.DRIPSTONE_BLOCK_HIT,
        SoundEvents.DRIPSTONE_BLOCK_FALL);
    public static final SoundType DEEPSLATE_MORKITE_ORE = new SoundType(1.0F, 1.0F,
        DEEPSLATE_MORKITE_ORE_BREAK,
        SoundEvents.DEEPSLATE_STEP,
        SoundEvents.DEEPSLATE_PLACE,
        SoundEvents.DEEPSLATE_HIT,
        SoundEvents.DEEPSLATE_FALL);

    @Override
    public Registry<SoundEvent> getRegistry() {
        return BuiltInRegistries.SOUND_EVENT;
    }

    @Override
    public Class<SoundEvent> getTargetFieldType() {
        return SoundEvent.class;
    }
}

