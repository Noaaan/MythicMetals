package nourl.mythicmetals;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.ops.LootOps;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.OneTwentyBuiltinRegistries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetals.armor.CarmotShield;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.BanglumNukeHandler;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.config.MythicMetalsConfig;
import nourl.mythicmetals.data.MythicOreKeys;
import nourl.mythicmetals.effects.MythicStatusEffects;
import nourl.mythicmetals.entity.CombustionCooldown;
import nourl.mythicmetals.entity.MythicEntities;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.misc.BlockBreaker;
import nourl.mythicmetals.misc.MythicCommands;
import nourl.mythicmetals.misc.MythicParticleSystem;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.registry.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MythicMetals implements ModInitializer, EntityComponentInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final int CONFIG_VERSION = 10;

    public static MythicMetalsConfig CONFIG = MythicMetalsConfig.createAndLoad();

    public static final OwoItemGroup TABBED_GROUP = OwoItemGroup.builder(RegistryHelper.id("main"),
                    () -> Icon.of(MythicItems.STORMYX.getIngot())).initializer(group -> {
                group.addTab(Icon.of(MythicItems.ADAMANTITE.getIngot()), "items", TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("item_tab")), false);
                group.addTab(Icon.of(MythicBlocks.ADAMANTITE.getStorageBlock()), "blocks", TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("blocks")), false);
                group.addTab(Icon.of(MythicTools.ADAMANTITE.getPickaxe()), "tools", TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("tool_tab")), false);
                group.addTab(Icon.of(MythicArmor.ADAMANTITE.getChestplate()), "armor", TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("armor_tab")), false);
                group.addButton(ItemGroupButton.github(group, "https://github.com/Noaaan/MythicMetals/issues"));
                group.addButton(ItemGroupButton.curseforge(group, "https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
                group.addButton(ItemGroupButton.modrinth(group, "https://modrinth.com/mod/mythicmetals"));
                group.addButton(ItemGroupButton.discord(group, "https://discord.gg/69cKvQWScC"));
            })
            .build();

    public static final ComponentKey<CarmotShield> CARMOT_SHIELD = ComponentRegistry.getOrCreate(RegistryHelper.id("carmot_shield"), CarmotShield.class);
    public static final ComponentKey<CombustionCooldown> COMBUSTION_COOLDOWN = ComponentRegistry.getOrCreate(RegistryHelper.id("combustion_cooldown"), CombustionCooldown.class);

    @Override
    public void onInitialize() {
        FieldRegistrationHandler.register(RegisterSounds.class, MOD_ID, false);
        FieldRegistrationHandler.processSimple(MythicItems.class, false);
        FieldRegistrationHandler.register(MythicItems.Mats.class, MOD_ID, false);
        FieldRegistrationHandler.register(MythicItems.Templates.class, MOD_ID, false);
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            FieldRegistrationHandler.register(MythicItems.ParticleSticks.class, MOD_ID, false);
        }
        FieldRegistrationHandler.processSimple(MythicItems.Copper.class, false);
        FieldRegistrationHandler.processSimple(MythicTools.class, true);
        FieldRegistrationHandler.processSimple(MythicArmor.class, false);
        MythicParticleSystem.init();
        MythicBlocks.init();
        BanglumNukeHandler.init();
        MythicOreKeys.init();
        MythicCommands.register();
        Abilities.init();
        RegisterEntityAttributes.init();
        MythicEntities.init();
        TABBED_GROUP.initialize();
        FuelRegistry.INSTANCE.add(MythicItems.Mats.MORKITE, 1200);
        FuelRegistry.INSTANCE.add(MythicBlocks.MORKITE.getStorageBlock(), 12800);
        RegisterResourceConditions.init();
        RegisterLootConditions.init();
        MythicStatusEffects.init();
        RegisterRecipeSerializers.init();
        RegisterCriteria.init();
        BlockBreaker.initHammerTime();
        LootOps.injectItem(MythicItems.Mats.UNOBTAINIUM, 0.01F, LootTables.ANCIENT_CITY_CHEST);

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
            LOGGER.info("[Mythic Metals] Is that Jello? Here comes the colors, weeeeeee!");
        }
        if (FabricLoader.getInstance().isModLoaded("terralith")) {
            LOGGER.info("[Mythic Metals] Terralith detected. Please go over the config and disable Overworld Nether Ores");
            LOGGER.info("[Mythic Metals] Many ores spawn in unexpected ways due to the new overworld. Modpack devs, take note of this");
        }
        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized.");
    }


    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, COMBUSTION_COOLDOWN, CombustionCooldown::new);
        registry.registerForPlayers(CARMOT_SHIELD, CarmotShield::new, RespawnCopyStrategy.INVENTORY);
    }
}
