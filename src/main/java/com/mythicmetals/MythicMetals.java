package com.mythicmetals;

import com.mythicmetals.ability.Abilities;
import com.mythicmetals.block.BanglumNukeHandler;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.command.MythicCommands;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.conditions.MythicResourceConditions;
import com.mythicmetals.config.MythicMetalsConfig;
import com.mythicmetals.data.loot.MythicLootConditions;
import com.mythicmetals.data.worldgen.MythicOreFeatures;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.*;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicPotions;
import com.mythicmetals.item.tools.Frogery;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.*;
import com.mythicmetals.recipe.MythicRecipeSerializers;
import com.mythicmetals.registry.*;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MythicMetals implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final int CONFIG_VERSION = 14;

    public static MythicMetalsConfig CONFIG = MythicMetalsConfig.createAndLoad();

    public static final OwoItemGroup TABBED_GROUP = OwoItemGroup.builder(RegistryHelper.id("main"), () -> Icon.of(MythicMaterials.STORMYX.baseMaterial()))
        .initializer(group -> {
            group.addTab(Icon.of(Items.BRICK), "items", TagKey.create(Registries.ITEM, RegistryHelper.id("item_tab")), false);
            group.addTab(Icon.of(Blocks.BRICKS), "blocks", TagKey.create(Registries.ITEM, RegistryHelper.id("blocks")), false);
            group.addTab(Icon.of(Items.BRUSH), "tools", TagKey.create(Registries.ITEM, RegistryHelper.id("tool_tab")), false);
            group.addTab(Icon.of(Items.TURTLE_HELMET), "armor", TagKey.create(Registries.ITEM, RegistryHelper.id("armor_tab")), false);
            group.addButton(ItemGroupButton.github(group, "https://github.com/Noaaan/MythicMetals/issues"));
            group.addButton(ItemGroupButton.curseforge(group, "https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
            group.addButton(ItemGroupButton.modrinth(group, "https://modrinth.com/mod/mythicmetals"));
            group.addButton(ItemGroupButton.discord(group, "https://discord.gg/69cKvQWScC"));
        })
        .build();

    @Override
    public void onInitialize() {
        MythicMaterials.init();
        FieldRegistrationHandler.register(RegisterSounds.class, MOD_ID, false);
//        FieldRegistrationHandler.processSimple(MythicTools.class, true);
        FieldRegistrationHandler.register(RegisterBlockEntityTypes.class, MOD_ID, false);
        MythicParticleSystem.init();
        MythicDataComponents.init();
        MythicPotions.init();
        BanglumNukeHandler.init();
        MythicOreFeatures.init();
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            DebugHelper.init();
        }
        Frogery.init();
        MythicCommands.init();
        MythicCommands.registerCommands();
        Abilities.init();
        RegisterPointOfInterests.init();
        MythicEntityAttributes.init();
        MythicEntities.init();
        TABBED_GROUP.initialize();
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(MythicMaterials.MORKITE.baseMaterial(), 1200);
            builder.add(MythicMaterials.MORKITE.blockSet().storage(), 12000);
        });
        MythicResourceConditions.init();
        MythicLootConditions.init();
        MythicStatusEffects.init();
        MythicRecipeSerializers.init();
        FieldRegistrationHandler.processSimple(RegisterCriteria.class, false);
        BlockBreaker.initHammerTime();
        MythicLootOps.init();
//        registerDispenserBehaviour();
        LegacyIds.registerAliases();

        if (CONFIG.configVersion() < CONFIG_VERSION) {
            for (int i = 0; i < 5; i++) {
                LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete it so it can be re-generated.");
            }
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
            LOGGER.info("[Mythic Metals] Terralith detected. Many ores can spawn in unexpected ways due to the new overworld. Modpack devs, take note of this");
        }
        if (FabricLoader.getInstance().isModLoaded("ftb-chunks-fabric") || FabricLoader.getInstance().isModLoaded("ftb-chunks-neoforge")) {
            if (!FabricLoader.getInstance().isModLoaded("ftb-xmod-compat-fabric")) {
                for (int i = 0; i < 3; i++) {
                    LOGGER.error("[Mythic Metals] FTB Chunks is loaded but FTB XMod Compat Fabric addon is not. This means claim protection will not work for some items!");
                }
            }
        }
        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized.");
    }

    private void registerDispenserBehaviour() {
        DispenserBlock.registerBehavior(() -> MythicTools.STAR_PLATINUM_ARROW, new ProjectileDispenseBehavior(MythicTools.STAR_PLATINUM_ARROW));
        DispenserBlock.registerBehavior(() -> MythicTools.RUNITE_ARROW, new ProjectileDispenseBehavior(MythicTools.RUNITE_ARROW));
        DispenserBlock.registerBehavior(() -> MythicTools.TIPPED_RUNITE_ARROW, new ProjectileDispenseBehavior(MythicTools.TIPPED_RUNITE_ARROW));
    }

}
