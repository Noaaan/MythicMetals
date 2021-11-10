package nourl.mythicmetals;

import com.glisco.owo.itemgroup.OwoItemGroup;
import com.glisco.owo.registration.reflect.FieldRegistrationHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.EnchantConfig;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.item.MythicItemGroups;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.RegisterSounds;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.utils.RegistryHelper;
import nourl.mythicmetals.world.MythicOreFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MythicMetals implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final String ADDON_ID = "mythicaddons";
    public static MythicConfig CONFIG = AutoConfig.register(MythicConfig.class, GsonConfigSerializer::new).getConfig();

    public static final OwoItemGroup TABBED_GROUP = new MythicItemGroups(RegistryHelper.id("main"));
    public static final ItemGroup MYTHICMETALS_DECOR = FabricItemGroupBuilder.create(
            new Identifier(ADDON_ID, "decorations")).icon(() -> new ItemStack(MythicBlocks.ADAMANTITE_CHAIN)).build();

    @Override
    public void onInitialize() {
        RegisterSounds.register();
        FieldRegistrationHandler.processSimple(MythicTools.class, false);
        FieldRegistrationHandler.register(MythicItems.class, MOD_ID, false);
        FieldRegistrationHandler.processSimple(MythicArmor.class, false);
        FieldRegistrationHandler.processSimple(MythicBlocks.class, true);
        MythicOreFeatures.init();
        EnchantConfig.appendEnchants();
        TABBED_GROUP.initialize();

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
