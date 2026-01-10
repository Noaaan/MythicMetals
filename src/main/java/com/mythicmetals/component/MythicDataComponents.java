package com.mythicmetals.component;

import com.mojang.serialization.Codec;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class MythicDataComponents {
    public static final DataComponentType<GoldFoldedComponent> GOLD_FOLDED = RegistryHelper.dataComponentType(
        "gold_folded", builder -> builder
            .persistent(CodecUtils.toCodec(GoldFoldedComponent.ENDEC))
            .networkSynchronized(CodecUtils.toPacketCodec(GoldFoldedComponent.ENDEC))
    );
    public static final DataComponentType<Boolean> WAS_USED = RegistryHelper.dataComponentType(
        "was_used", builder ->
            builder.persistent(Codec.BOOL)
                .networkSynchronized(ByteBufCodecs.BOOL)
    );
    public static final DataComponentType<TidesingerPatternComponent> TIDESINGER = RegistryHelper.dataComponentType(
        "tidesinger", builder -> builder
            .persistent(CodecUtils.toCodec(TidesingerPatternComponent.ENDEC))
            .networkSynchronized(CodecUtils.toPacketCodec(TidesingerPatternComponent.ENDEC))
    );
    public static final DataComponentType<DrillComponent> DRILL = RegistryHelper.dataComponentType(
        "drill", builder -> builder
            .persistent(CodecUtils.toCodec(DrillComponent.ENDEC))
            .networkSynchronized(CodecUtils.toPacketCodec(DrillComponent.ENDEC))
    );
    public static final DataComponentType<UpgradeComponent> UPGRADES = RegistryHelper.dataComponentType(
        "upgrades", builder -> builder
            .persistent(CodecUtils.toCodec(UpgradeComponent.ENDEC))
            .networkSynchronized(CodecUtils.toPacketCodec(UpgradeComponent.ENDEC))
    );
    public static final DataComponentType<PrometheumComponent> PROMETHEUM = RegistryHelper.dataComponentType(
        "prometheum", builder -> builder
            .persistent(CodecUtils.toCodec(PrometheumComponent.ENDEC))
            .networkSynchronized(CodecUtils.toPacketCodec(PrometheumComponent.ENDEC))
    );
    public static final DataComponentType<BlastMiningComponent> BLAST_MINING = RegistryHelper.dataComponentType(
        "blast_mining", builder -> builder
            .persistent(CodecUtils.toCodec(BlastMiningComponent.ENDEC))
            .networkSynchronized(CodecUtils.toPacketCodec(BlastMiningComponent.ENDEC))
    );
    public static final DataComponentType<BrandingComponent> BRANDING = RegistryHelper.dataComponentType("branding", BrandingComponent.ENDEC);

    public static void init() {
    }
}
