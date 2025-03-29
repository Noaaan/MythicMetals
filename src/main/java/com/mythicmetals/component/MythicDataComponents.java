package com.mythicmetals.component;

import com.mojang.serialization.Codec;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import com.mythicmetals.misc.RegistryHelper;

// TODO - Migrate to AutoRegistry
public class MythicDataComponents {
    public static final ComponentType<GoldFoldedComponent> GOLD_FOLDED = RegistryHelper.dataComponentType(
        "gold_folded", builder -> builder
            .codec(CodecUtils.toCodec(GoldFoldedComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(GoldFoldedComponent.ENDEC))
    );
    public static final ComponentType<Boolean> LOCKED = RegistryHelper.dataComponentType(
        "locked", builder -> builder
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOL)
    );
    public static final ComponentType<Boolean> ENCORE = RegistryHelper.dataComponentType(
        "encore", builder -> builder
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOL)
    );
    public static final ComponentType<Boolean> IS_USED = RegistryHelper.dataComponentType(
        "is_used", builder -> builder
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOL)
    );
    public static final ComponentType<CarmotStaffComponent> CARMOT_STAFF_BLOCK = RegistryHelper.dataComponentType(
        "carmot_staff_block", builder -> builder
            .codec(CodecUtils.toCodec(CarmotStaffComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(CarmotStaffComponent.ENDEC))
    );
    public static final ComponentType<TidesingerPatternComponent> TIDESINGER = RegistryHelper.dataComponentType(
        "tidesinger", builder -> builder
            .codec(CodecUtils.toCodec(TidesingerPatternComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(TidesingerPatternComponent.ENDEC))
    );
    public static final ComponentType<DrillComponent> DRILL = RegistryHelper.dataComponentType(
        "drill", builder -> builder
            .codec(CodecUtils.toCodec(DrillComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(DrillComponent.ENDEC))
    );
    public static final ComponentType<UpgradeComponent> UPGRADES = RegistryHelper.dataComponentType(
        "upgrades", builder -> builder
            .codec(CodecUtils.toCodec(UpgradeComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(UpgradeComponent.ENDEC))
    );
    public static final ComponentType<PrometheumComponent> PROMETHEUM = RegistryHelper.dataComponentType(
        "prometheum", builder -> builder
            .codec(CodecUtils.toCodec(PrometheumComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(PrometheumComponent.ENDEC))
    );

    public static void init() {
    }
}
