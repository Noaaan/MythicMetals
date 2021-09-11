package nourl.mythicmetals.registry;

import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.BlockSet;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterBlocks {

    public static void register() {
        // Storage Blocks
        RegistryHelper.block("bronze_block", MythicBlocks.BRONZE_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("celestium_block", MythicBlocks.CELESTIUM_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("discordium_block", MythicBlocks.DISCORDIUM_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("durasteel_block", MythicBlocks.DURASTEEL_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("etherite_block", MythicBlocks.ETHERITE_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("hallowed_block", MythicBlocks.HALLOWED_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("metallurgium_block", MythicBlocks.METALLURGIUM_BLOCK, MythicMetals.MYTHICMETALS, true);
        RegistryHelper.block("quicksilver_block", MythicBlocks.QUICKSILVER_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("star_platinum_block", MythicBlocks.STAR_PLATINUM_BLOCK, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("steel_block", MythicBlocks.STEEL_BLOCK, MythicMetals.MYTHICMETALS);

        // Anvils
        RegistryHelper.block("adamantite_anvil", MythicBlocks.ADAMANTITE_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("aetherium_anvil", MythicBlocks.AETHERIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("aquarium_anvil", MythicBlocks.AQUARIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("banglum_anvil", MythicBlocks.BANGLUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("bronze_anvil", MythicBlocks.BRONZE_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("carmot_anvil", MythicBlocks.CARMOT_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("celestium_anvil", MythicBlocks.CELESTIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("discordium_anvil", MythicBlocks.DISCORDIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("durasteel_anvil", MythicBlocks.DURASTEEL_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("etherite_anvil", MythicBlocks.ETHERITE_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("hallowed_anvil", MythicBlocks.HALLOWED_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("kyber_anvil", MythicBlocks.KYBER_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("manganese_anvil", MythicBlocks.MANGANESE_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("metallurgium_anvil", MythicBlocks.METALLURGIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR, true);
        RegistryHelper.block("midas_gold_anvil", MythicBlocks.MIDAS_GOLD_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("mythril_anvil", MythicBlocks.MYTHRIL_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("orichalcum_anvil", MythicBlocks.ORICHALCUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("osmium_anvil", MythicBlocks.OSMIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("palladium_anvil", MythicBlocks.PALLADIUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR, true);
        RegistryHelper.block("platinum_anvil", MythicBlocks.PLATINUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("prometheum_anvil", MythicBlocks.PROMETHEUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("quadrillum_anvil", MythicBlocks.QUADRILLUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("quicksilver_anvil", MythicBlocks.QUICKSILVER_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("runite_anvil", MythicBlocks.RUNITE_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("silver_anvil", MythicBlocks.SILVER_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("star_platinum_anvil", MythicBlocks.STAR_PLATINUM_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("steel_anvil", MythicBlocks.STEEL_ANVIL, MythicMetals.MYTHICMETALS_DECOR);
        RegistryHelper.block("stormyx_anvil", MythicBlocks.STORMYX_ANVIL, MythicMetals.MYTHICMETALS_DECOR);

        MythicBlocks.ANVILS.add(MythicBlocks.ADAMANTITE_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.AETHERIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.AQUARIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.BANGLUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.BRONZE_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.CARMOT_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.CELESTIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.DISCORDIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.DURASTEEL_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.ETHERITE_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.HALLOWED_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.KYBER_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.MANGANESE_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.METALLURGIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.MIDAS_GOLD_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.MYTHRIL_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.ORICHALCUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.OSMIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.PALLADIUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.PLATINUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.PROMETHEUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.QUADRILLUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.QUICKSILVER_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.RUNITE_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.SILVER_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.STAR_PLATINUM_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.STEEL_ANVIL);
        MythicBlocks.ANVILS.add(MythicBlocks.STORMYX_ANVIL);

        // Chains
        RegistryHelper.chain("adamantite_chain", MythicBlocks.ADAMANTITE_CHAIN);
        RegistryHelper.chain("aetherium_chain", MythicBlocks.AETHERIUM_CHAIN);
        RegistryHelper.chain("aquarium_chain", MythicBlocks.AQUARIUM_CHAIN);
        RegistryHelper.chain("banglum_chain", MythicBlocks.BANGLUM_CHAIN);
        RegistryHelper.chain("bronze_chain", MythicBlocks.BRONZE_CHAIN);
        RegistryHelper.chain("carmot_chain", MythicBlocks.CARMOT_CHAIN);
        RegistryHelper.chain("celestium_chain", MythicBlocks.CELESTIUM_CHAIN);
        RegistryHelper.chain("discordium_chain", MythicBlocks.DISCORDIUM_CHAIN);
        RegistryHelper.chain("durasteel_chain", MythicBlocks.DURASTEEL_CHAIN);
        RegistryHelper.chain("etherite_chain", MythicBlocks.ETHERITE_CHAIN);
        RegistryHelper.chain("hallowed_chain", MythicBlocks.HALLOWED_CHAIN);
        RegistryHelper.chain("kyber_chain", MythicBlocks.KYBER_CHAIN);
        RegistryHelper.chain("manganese_chain", MythicBlocks.MANGANESE_CHAIN);
        RegistryHelper.chain("metallurgium_chain", MythicBlocks.METALLURGIUM_CHAIN, true);
        RegistryHelper.chain("midas_gold_chain", MythicBlocks.MIDAS_GOLD_CHAIN);
        RegistryHelper.chain("mythril_chain", MythicBlocks.MYTHRIL_CHAIN);
        RegistryHelper.chain("orichalcum_chain", MythicBlocks.ORICHALCUM_CHAIN);
        RegistryHelper.chain("osmium_chain", MythicBlocks.OSMIUM_CHAIN);
        RegistryHelper.chain("palladium_chain", MythicBlocks.PALLADIUM_CHAIN, true);
        RegistryHelper.chain("platinum_chain", MythicBlocks.PLATINUM_CHAIN);
        RegistryHelper.chain("prometheum_chain", MythicBlocks.PROMETHEUM_CHAIN);
        RegistryHelper.chain("quadrillum_chain", MythicBlocks.QUADRILLUM_CHAIN);
        RegistryHelper.chain("quicksilver_chain", MythicBlocks.QUICKSILVER_CHAIN);
        RegistryHelper.chain("runite_chain", MythicBlocks.RUNITE_CHAIN);
        RegistryHelper.chain("silver_chain", MythicBlocks.SILVER_CHAIN);
        RegistryHelper.chain("star_platinum_chain", MythicBlocks.STAR_PLATINUM_CHAIN);
        RegistryHelper.chain("steel_chain", MythicBlocks.STEEL_CHAIN);
        RegistryHelper.chain("stormyx_chain", MythicBlocks.STORMYX_CHAIN);
    }

}
