package com.mythicmetals.component;

import com.mojang.serialization.Codec;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;

public class MythicDataComponents {
    public static final ComponentType<GoldFoldedComponent> GOLD_FOLDED = RegistryHelper.dataComponentType(
        "gold_folded", builder -> builder
            .codec(CodecUtils.toCodec(GoldFoldedComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(GoldFoldedComponent.ENDEC))
    );
    @Deprecated(forRemoval = true, since = "0.23.0")
    public static final ComponentType<Boolean> LOCKED = RegistryHelper.dataComponentType(
        "locked", builder -> builder
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOLEAN)
    );
    public static final ComponentType<Boolean> WAS_USED = RegistryHelper.dataComponentType(
        "was_used", builder ->
            builder.codec(Codec.BOOL)
                .packetCodec(PacketCodecs.BOOLEAN)
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
    public static final ComponentType<BlastMiningComponent> BLAST_MINING = RegistryHelper.dataComponentType(
        "blast_mining", builder -> builder
            .codec(CodecUtils.toCodec(BlastMiningComponent.ENDEC))
            .packetCodec(CodecUtils.toPacketCodec(BlastMiningComponent.ENDEC))
    );
    public static final ComponentType<BrandingComponent> BRANDING = RegistryHelper.dataComponentType("branding", BrandingComponent.ENDEC);

    public static void init() {
    }
}
