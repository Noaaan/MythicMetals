package nourl.mythicmetals;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetals.armor.CarmotShield;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.BanglumNukeHandler;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.MythicMetalsConfig;
import nourl.mythicmetals.item.MythicItemGroups;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.*;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.utils.MythicParticleSystem;
import nourl.mythicmetals.utils.RegistryHelper;
import nourl.mythicmetals.world.MythicOreFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MythicMetals implements ModInitializer, EntityComponentInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final int CONFIG_VERSION = 7;

    public static MythicMetalsConfig CONFIG = MythicMetalsConfig.createAndLoad();

    public static final OwoItemGroup TABBED_GROUP = new MythicItemGroups(RegistryHelper.id("main"));

    public static final ComponentKey<CarmotShield> CARMOT_SHIELD = ComponentRegistry.getOrCreate(RegistryHelper.id("carmot_shield"), CarmotShield.class);

    @Override
    public void onInitialize() {
        RegisterSounds.register();
        FieldRegistrationHandler.register(MythicItems.Ingots.class, MOD_ID, false);
        FieldRegistrationHandler.register(MythicItems.RawOres.class, MOD_ID, false);
        if (CONFIG.enableNuggets()) {
            FieldRegistrationHandler.register(MythicItems.Nuggets.class, MOD_ID, false);
        }
        if (CONFIG.enableDusts()) {
            FieldRegistrationHandler.register(MythicItems.Dusts.class, MOD_ID, false);
        }
        FieldRegistrationHandler.register(MythicItems.class, MOD_ID, false);
        FieldRegistrationHandler.processSimple(MythicTools.class, true);
        FieldRegistrationHandler.processSimple(MythicArmor.class, false);
        MythicParticleSystem.init();
        MythicBlocks.init();
        BanglumNukeHandler.init();
        MythicOreFeatures.init();
        MythicCommands.register();
        Abilities.init();
        RegisterEntities.init();
        TABBED_GROUP.initialize();
        FuelRegistry.INSTANCE.add(MythicItems.Ingots.MORKITE, 1200);
        FuelRegistry.INSTANCE.add(MythicBlocks.MORKITE.getStorageBlock(), 12800);
        RegisterResourceConditions.init();
        RegisterRecipeSerializers.init();
        RegisterEntityAttributes.init();

        if (CONFIG.configVersion() < CONFIG_VERSION) {
            LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete the file so it can be re-generated.");
            LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete the file so it can be re-generated.");
            LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete the file so it can be re-generated.");
            LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete the file so it can be re-generated.");
            LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete the file so it can be re-generated.");
        }

        if (FabricLoader.getInstance().isModLoaded("harvest_scythes")) {
            LOGGER.info("[Mythic Metals] I see HarvestScythes. I'll take care of DH so you don't have to");
        }
        if (FabricLoader.getInstance().isModLoaded("enhancedcraft")) {
            LOGGER.info("[Mythic Metals] Oh EnhancedCraft? If you ever see Spxctre tell him I said hi!");
        }
        if (FabricLoader.getInstance().isModLoaded("origins")) {
            LOGGER.info("[Mythic Metals] Have fun using Origins!");
        }
        if (FabricLoader.getInstance().isModLoaded("spectrum")) {
            LOGGER.info("[Mythic Metals] Spectrum is loaded! Good luck on finding all of its secrets...");
        }
        if (FabricLoader.getInstance().isModLoaded("jello")) {
            LOGGER.info("[Mythic Metals] Here comes the colors, weeeeeee!");
        }
        if (FabricLoader.getInstance().isModLoaded("terralith")) {
            LOGGER.info("[Mythic Metals] Terralith detected. Please go over the config and disable Overworld Nether Ores");
            LOGGER.info("[Mythic Metals] Many ores spawn in unexpected ways due to the new overworld. Modpack devs, take note of this");
        }
        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized.");
    }


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(CARMOT_SHIELD, CarmotShield::new, RespawnCopyStrategy.INVENTORY);
    }
}
