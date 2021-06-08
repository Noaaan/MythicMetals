package nourl.mythicmetals;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.blocks.MythicMetalsChains;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.config.MythicEnchantConfig;
import nourl.mythicmetals.config.MythicForgeConfig;
import nourl.mythicmetals.world.OreGenerator;
import nourl.mythicmetals.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MythicMetals implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final String CHAIN_ID = "mm_decorations";
    public static final MythicConfig CONFIG = AutoConfig.register(MythicConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();

    public static final ItemGroup MYTHICMETALS = FabricItemGroupBuilder.create(
            new Identifier("mythicmetals", "main")).icon(() -> new ItemStack(RegisterIngots.ADAMANTITE_INGOT)).build();
    public static final ItemGroup MYTHICMETALS_TOOLS = FabricItemGroupBuilder.create(
            new Identifier("mythicmetals", "tools")).icon(() -> new ItemStack(RegisterTools.ADAMANTITE_PICKAXE)).build();
    public static final ItemGroup MYTHICMETALS_ARMOR = FabricItemGroupBuilder.create(
            new Identifier("mythicmetals", "armor")).icon(() -> new ItemStack(RegisterArmor.ADAMANTITE_HELMET)).build();
    public static final ItemGroup MYTHICMETALS_DECOR = FabricItemGroupBuilder.create(
            new Identifier("mythicmetals", "decorations")).icon(() -> new ItemStack(MythicMetalsChains.ADAMANTITE_CHAIN)).build();


    @Override
    public void onInitialize() {
        RegisterIngots.register();
        RegisterNuggets.register();
        RegisterTools.register();
        RegisterArmor.register();
        RegisterBlocks.register();
        RegisterItems.register();
        OreGenerator.init();
        OreGenerator.generate();

        MythicEnchantConfig.appendEnchants();
        MythicForgeConfig.createAlloys();

        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized");
        if (FabricLoader.getInstance().isModLoaded("smithee")) {
            LOGGER.info("[Mythic Metals] Hey, Smithee is around. Rewrite should be coming this summer.");
        }
        if (FabricLoader.getInstance().isModLoaded("harvest_scythes")) {
            LOGGER.info("[Mythic Metals] Eyo, Harvest Scythes is enabled. Give DH a thank for the textures!");
        }
        if (FabricLoader.getInstance().isModLoaded("enhancedcraft")) {
            LOGGER.info("[Mythic Metals] Oh EnhancedCraft? If you ever see Spxtre tell him I said hi!");
        }
    }
}
