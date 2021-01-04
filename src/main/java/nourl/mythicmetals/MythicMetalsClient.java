package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import nourl.mythicmetals.blocks.MythicMetalsChains;

public class MythicMetalsClient implements ClientModInitializer {
    // Makes custom model blocks see trough, like chains
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MythicMetalsChains.ADAMANTITE_CHAIN,
                MythicMetalsChains.AETHERIUM_CHAIN,
                MythicMetalsChains.AQUARIUM_CHAIN,
                MythicMetalsChains.ARGONIUM_CHAIN,
                MythicMetalsChains.BANGLUM_CHAIN,
                MythicMetalsChains.BRASS_CHAIN,
                MythicMetalsChains.BRONZE_CHAIN,
                MythicMetalsChains.CARMOT_CHAIN,
                MythicMetalsChains.CELESTIUM_CHAIN,
                MythicMetalsChains.DISCORDIUM_CHAIN,
                MythicMetalsChains.DURASTEEL_CHAIN,
                MythicMetalsChains.ELECTRUM_CHAIN,
                MythicMetalsChains.ETHERITE_CHAIN,
                MythicMetalsChains.KYBER_CHAIN,
                MythicMetalsChains.HALLOWED_CHAIN,
                MythicMetalsChains.MANGANESE_CHAIN,
                MythicMetalsChains.METALLURGIUM_CHAIN,
                MythicMetalsChains.MIDAS_GOLD_CHAIN,
                MythicMetalsChains.MYTHRIL_CHAIN,
                MythicMetalsChains.ORICHALCUM_CHAIN,
                MythicMetalsChains.OSMIUM_CHAIN,
                MythicMetalsChains.PLATINUM_CHAIN,
                MythicMetalsChains.PROMETHEUM_CHAIN,
                MythicMetalsChains.QUADRILLUM_CHAIN,
                MythicMetalsChains.QUICKSILVER_CHAIN,
                MythicMetalsChains.RUNITE_CHAIN,
                MythicMetalsChains.SILVER_CHAIN,
                MythicMetalsChains.SLOWSILVER_CHAIN,
                MythicMetalsChains.STARRITE_CHAIN,
                MythicMetalsChains.STEEL_CHAIN,
                MythicMetalsChains.STORMYX_CHAIN,
                MythicMetalsChains.TANTALITE_CHAIN,
                MythicMetalsChains.TRUESILVER_CHAIN,
                MythicMetalsChains.UR_CHAIN
        );
    }
}
