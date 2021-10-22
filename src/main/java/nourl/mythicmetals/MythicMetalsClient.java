package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import nourl.mythicmetals.blocks.MythicBlocks;

public class MythicMetalsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        makeOpaque();
    }

    // Makes custom model blocks see through, like chains
    public void makeOpaque() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MythicBlocks.ADAMANTITE_CHAIN,
                MythicBlocks.AQUARIUM_CHAIN,
                MythicBlocks.BANGLUM_CHAIN,
                MythicBlocks.BRONZE_CHAIN,
                MythicBlocks.CARMOT_CHAIN,
                MythicBlocks.CELESTIUM_CHAIN,
                MythicBlocks.DURASTEEL_CHAIN,
                MythicBlocks.KYBER_CHAIN,
                MythicBlocks.HALLOWED_CHAIN,
                MythicBlocks.MANGANESE_CHAIN,
                MythicBlocks.METALLURGIUM_CHAIN,
                MythicBlocks.MIDAS_GOLD_CHAIN,
                MythicBlocks.MYTHRIL_CHAIN,
                MythicBlocks.ORICHALCUM_CHAIN,
                MythicBlocks.OSMIUM_CHAIN,
                MythicBlocks.PLATINUM_CHAIN,
                MythicBlocks.PROMETHEUM_CHAIN,
                MythicBlocks.QUADRILLUM_CHAIN,
                MythicBlocks.RUNITE_CHAIN,
                MythicBlocks.SILVER_CHAIN,
                MythicBlocks.STAR_PLATINUM_CHAIN,
                MythicBlocks.STEEL_CHAIN,
                MythicBlocks.STORMYX_CHAIN,
                MythicBlocks.PALLADIUM_CHAIN
        );
    }

}
