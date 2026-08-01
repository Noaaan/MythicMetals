package com.mythicmetals;

import com.mythicmetals.block.BanglumNukeHandler;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.command.MythicCommands;
import com.mythicmetals.config.MythicMetalsConfig;
import com.mythicmetals.data.MythicCriteriaTriggers;
import com.mythicmetals.data.conditions.MythicResourceConditions;
import com.mythicmetals.data.loot.MythicLootConditions;
import com.mythicmetals.data.recipe.MythicRecipeSerializers;
import com.mythicmetals.data.worldgen.MythicOreFeatures;
import com.mythicmetals.effects.MythicStatusEffects;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.*;
import com.mythicmetals.item.armor.MythicArmorSets;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.tools.Frogery;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.*;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class MythicMetals implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mythicmetals";
    public static final int CONFIG_VERSION = 15;

    public static MythicMetalsConfig CONFIG = MythicMetalsConfig.createAndLoad();

    public static final OwoItemGroup TABBED_GROUP = OwoItemGroup.builder(RegistryHelper.id("main"), () -> Icon.of(MythicMaterials.STORMYX.baseMaterial()))
        .initializer(group -> {
            group.addTab(Icon.of(MythicMaterials.ADAMANTITE.baseMaterial()), "items", TagKey.create(Registries.ITEM, RegistryHelper.id("item_tab")), false);
            group.addTab(Icon.of(MythicMaterials.ADAMANTITE.blockSet().storage()), "blocks", TagKey.create(Registries.ITEM, RegistryHelper.id("blocks")), false);
            group.addTab(Icon.of(MythicMaterials.ADAMANTITE.toolSet().getPickaxe()), "tools", TagKey.create(Registries.ITEM, RegistryHelper.id("tool_tab")), false);
            group.addTab(Icon.of(MythicMaterials.ADAMANTITE.armorSet().getChestplate()), "armor", TagKey.create(Registries.ITEM, RegistryHelper.id("armor_tab")), false);
            group.addButton(ItemGroupButton.github(group, "https://github.com/Noaaan/MythicMetals/issues"));
            group.addButton(ItemGroupButton.curseforge(group, "https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
            group.addButton(ItemGroupButton.modrinth(group, "https://modrinth.com/mod/mythicmetals"));
            group.addButton(ItemGroupButton.discord(group, "https://discord.gg/69cKvQWScC"));
        })
        .build();

    // TODO - Refactor this to allow any item, and handle tooltips more explicitly
    public static Map<Item, String> drillUpgrades = new HashMap<>();

    @Override
    public void onInitialize() {
        MythicMaterials.init();
        initDrillItems();
        MythicArmorSets.init();
        FieldRegistrationHandler.register(MythicSoundEvents.class, MOD_ID, false);
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
        MythicPOIs.init();
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
        MythicCriteriaTriggers.init();
        BlockBreaker.initHammerTime();
        MythicLootOps.init();
        LegacyIds.registerAliases();
        registerDispenserBehaviour();
        registerEvents();
        checkConfigVersion();
        logInit();
    }

    private static void logInit() {
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
        if ((FabricLoader.getInstance().isModLoaded("ftb-chunks-fabric") || FabricLoader.getInstance().isModLoaded("ftb-chunks-neoforge")) && !FabricLoader.getInstance().isModLoaded("ftb-xmod-compat-fabric")) {
            for (int i = 0; i < 3; i++) {
                LOGGER.error("[Mythic Metals] FTB Chunks is loaded but FTB XMod Compat Fabric addon is not. This means claim protection will not work for some items!");
            }
        }

        LOGGER.info("[Mythic Metals] Mythic Metals is now initialized.");
    }

    private void checkConfigVersion() {
        if (CONFIG.configVersion() < CONFIG_VERSION) {
            for (int i = 0; i < 5; i++) {
                LOGGER.warn("[Mythic Metals] Your config is outdated. Please update it manually in the file, or delete it so it can be re-generated.");
            }
        }
    }

    private void registerEvents() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            var stack = player.getItemInHand(hand);
            if (stack.has(MythicDataComponents.FIRE_ASPECT)) {
                entity.igniteForTicks(stack.getOrDefault(MythicDataComponents.FIRE_ASPECT, 0));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        });
    }

    private void registerDispenserBehaviour() {
        DispenserBlock.registerBehavior(() -> MythicTools.STAR_PLATINUM_ARROW, new ProjectileDispenseBehavior(MythicTools.STAR_PLATINUM_ARROW));
        DispenserBlock.registerBehavior(() -> MythicTools.RUNITE_ARROW, new ProjectileDispenseBehavior(MythicTools.RUNITE_ARROW));
        DispenserBlock.registerBehavior(() -> MythicTools.TIPPED_RUNITE_ARROW, new ProjectileDispenseBehavior(MythicTools.TIPPED_RUNITE_ARROW));
    }

    private void initDrillItems() {
        drillUpgrades.put(MythicMaterials.TIDESINGER.baseMaterial(), "aquarium");
        drillUpgrades.put(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE), "carmot");
        drillUpgrades.put(MythicMaterials.MIDAS_GOLD.extraBlocks().get(MythicResourceKeys.ENCHANTED_MIDAS_GOLD_BLOCK).asItem(), "midas_gold");
        drillUpgrades.put(MythicMaterials.PROMETHEUM.extraItems().get(MythicResourceKeys.PROMETHEUM_ROSE), "prometheum");
        drillUpgrades.put(MythicMaterials.STORMYX.extraItems().get(MythicResourceKeys.STORMYX_SHELL), "stormyx");
        drillUpgrades.put(Items.AIR, "empty");
    }

}
