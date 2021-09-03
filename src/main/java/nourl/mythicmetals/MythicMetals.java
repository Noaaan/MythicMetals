package nourl.mythicmetals;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.EnchantConfig;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.registry.*;
import nourl.mythicmetals.utils.RegistryHelper;
import nourl.mythicmetals.world.MythicOreFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MythicMetals implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final String ADDON_ID = "mythicaddons";
    public static final MythicConfig CONFIG = AutoConfig.register(MythicConfig.class, JanksonConfigSerializer::new).getConfig();

    public static final ItemGroup MYTHICMETALS = FabricItemGroupBuilder.create(
            RegistryHelper.id("main")).icon(() -> new ItemStack(RegisterItems.ADAMANTITE_INGOT)).build();
    public static final ItemGroup MYTHICMETALS_TOOLS = FabricItemGroupBuilder.create(
            RegistryHelper.id("tools")).icon(() -> new ItemStack(RegisterTools.ADAMANTITE.getPickaxe())).build();
    public static final ItemGroup MYTHICMETALS_ARMOR = FabricItemGroupBuilder.create(
            RegistryHelper.id("armor")).icon(() -> new ItemStack(RegisterArmor.ADAMANTITE.getHelmet())).build();
    public static final ItemGroup MYTHICMETALS_DECOR = FabricItemGroupBuilder.create(
            RegistryHelper.id("decorations")).icon(() -> new ItemStack(MythicBlocks.ADAMANTITE_CHAIN)).build();


    @Override
    public void onInitialize() {
        RegisterItems.register();
        if (CONFIG.enableNuggets) {
            RegisterItems.registerNuggets();
        }
        if (CONFIG.enableDusts) {
            RegisterItems.registerDusts();
        }
        RegisterBlocks.register();
        RegisterTools.register();
        RegisterSounds.register();
        RegisterArmor.register();
        MythicOreFeatures.init();
        EnchantConfig.appendEnchants();

        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized");

        if (FabricLoader.getInstance().isModLoaded("harvest_scythes")) {
            LOGGER.info("[Mythic Metals] Eyo, Harvest Scythes is enabled. Punish DH for being extremely based!");
        }
        if (FabricLoader.getInstance().isModLoaded("enhancedcraft")) {
            LOGGER.info("[Mythic Metals] Oh EnhancedCraft? If you ever see Spxctre tell him I said hi!");
        }
        if (FabricLoader.getInstance().isModLoaded("origins")) {
            LOGGER.info("[Mythic Metals] Have fun using Origins!");
            EnchantConfig.appendWaterProt();
        }
    }
}
