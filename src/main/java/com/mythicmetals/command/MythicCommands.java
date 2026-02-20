package com.mythicmetals.command;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.ArmorSet;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.block.BlockSet;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.config.MythicOreConfigs;
import com.mythicmetals.config.OreConfig;
import com.mythicmetals.item.tools.*;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.*;
import net.minecraft.commands.arguments.ResourceOrIdArgument;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.LootCommand;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.trim.ArmorTrim;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraft.server.permissions.Permissions.COMMANDS_ADMIN;

@SuppressWarnings({"UnstableApiUsage", "CodeBlock2Expr"})
public final class MythicCommands {

    public static BiMap<String, OreConfig> ORE_CONFIG = HashBiMap.create();

    private MythicCommands() {
    }

    @SuppressWarnings("UnreachableCode")
    public static void init() {
        ReflectionUtils.iterateAccessibleStaticFields(MythicOreConfigs.class, OreConfig.class, (value, name, field) -> {
            ORE_CONFIG.put(name, value);
        });
        ArgumentTypeRegistry.registerArgumentType(RegistryHelper.id("toolset"), ToolSetArgumentType.class, SingletonArgumentInfo.contextFree(ToolSetArgumentType::toolSet));
        ArgumentTypeRegistry.registerArgumentType(RegistryHelper.id("armorset"), ArmorSetArgumentType.class, SingletonArgumentInfo.contextFree(ArmorSetArgumentType::armorSet));
        ArgumentTypeRegistry.registerArgumentType(RegistryHelper.id("ore-config"), OreConfigArgumentType.class, SingletonArgumentInfo.contextFree(OreConfigArgumentType::oreConfig));
        ArgumentTypeRegistry.registerArgumentType(RegistryHelper.id("blockset"), BlockSetArgumentType.class, SingletonArgumentInfo.contextFree(BlockSetArgumentType::blockSet));
    }

    // TODO - Add new command for grabbing the data-generated ore features, and create a datapack skeleton
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, env) -> {
            var mythicRoot = Commands.literal("mythicmetals").requires(src -> src.permissions().hasPermission(COMMANDS_ADMIN)).build();
            var range = Commands.literal("range").build();
            var tools = Commands.literal("tools").build();
            var allTools = Commands.literal("tools-all").executes(MythicCommands::exportAllTools).build();
            var allArmor = Commands.literal("armor-all").executes(MythicCommands::exportAllArmor).build();
            var ores = Commands.literal("ores").build();
            var armor = Commands.literal("armor").build();
            var midas = Commands.literal("give-midas").build();
            var wiki = Commands.literal("wiki").build();
            var armorStand = Commands.literal("armor-stand").build();
            var loot = Commands.literal("test-loot-table").build();
            var display = Commands.literal("place-display").build();
            var placeBlocks = Commands.literal("place-all-blocks").executes(context -> placeAllBlocksets(context, Map.of()))
                .build();

            // TODO - Make this useful command more useful for current use-cases:
            // TODO -- Allow dumping output in a spreadsheet friendly format
            var rangeType = Commands.argument("type", StringArgumentType.word())
                .suggests(MythicCommands::dumpType)
                .executes(MythicCommands::dumpAllOreConfigs)
                .build();

            var exportOres = Commands.argument("ore-config", OreConfigArgumentType.oreConfig())
                .executes(MythicCommands::exportOreData)
                .build();

            var giveMidas = Commands.argument("folds", IntegerArgumentType.integer(0, 10000))
                .executes(MythicCommands::giveMidasSword)
                .build();

            var exportTools = Commands.argument("toolset", ToolSetArgumentType.toolSet())
                .executes(MythicCommands::exportTools)
                .build();

            var exportArmor = Commands.argument("armorset", ArmorSetArgumentType.armorSet())
                .executes(MythicCommands::exportArmor)
                .build();

            var placeDisplay = Commands.argument("material", StringArgumentType.word())
                .suggests(MythicCommands::material)
                .executes(MythicCommands::placeMythicDisplay)
                .build();

            var lootTables = Commands.argument("loot_table", ResourceOrIdArgument.LootTableArgument.lootTable(buildContext))
                .suggests((context, builder) -> {
                    var lootTableRegistry = context.getSource().getServer().registryAccess().get(Registries.LOOT_TABLE).orElseThrow();
                    return SharedSuggestionProvider.suggestResource(lootTableRegistry.value().keySet(), builder);
                })
                .then(Commands.argument("rolls", IntegerArgumentType.integer())
                    .executes(MythicCommands::testLootTable))
                .build();

            var trimPattern = Commands.argument("trim_pattern", StringArgumentType.word())
                .suggests(MythicCommands::trimTypes)
                .executes(context -> {
                    String matQuery = StringArgumentType.getString(context, "material");
                    String trimQuery = StringArgumentType.getString(context, "trim_pattern");
                    return armorStandCommand(context, matQuery, trimQuery);
                });

            var summonTrims = Commands.argument("material", StringArgumentType.word())
                .suggests(MythicCommands::armorMaterial)
                .executes(context -> {
                    String mat = StringArgumentType.getString(context, "material");
                    return armorStandCommand(context, mat, null);
                })
                .then(trimPattern)
                .build();

            // Wiki nodes
            ores.addChild(exportOres);
            tools.addChild(exportTools);
            armor.addChild(exportArmor);
            wiki.addChild(ores);
            wiki.addChild(tools);
            wiki.addChild(allTools);
            wiki.addChild(armor);
            wiki.addChild(allArmor);

            // Misc nodes
            range.addChild(rangeType);
            loot.addChild(lootTables);
            armorStand.addChild(summonTrims);
            display.addChild(placeDisplay);
            midas.addChild(giveMidas);

            // Add commands to root
            mythicRoot.addChild(range);
            mythicRoot.addChild(wiki);
            mythicRoot.addChild(armorStand);
            mythicRoot.addChild(loot);
            mythicRoot.addChild(placeBlocks);
            mythicRoot.addChild(display);
            mythicRoot.addChild(midas);

            dispatcher.getRoot().addChild(mythicRoot);
        });
    }

    private static int giveMidasSword(CommandContext<CommandSourceStack> context) {
        int goldCount = IntegerArgumentType.getInteger(context, "folds");
        var player = context.getSource().getPlayer();
        if (player == null) {
            context.getSource().sendFailure(Component.literal("player required"));
            return -1;
        }

        player.getInventory().placeItemBackInInventory(MidasGoldSword.createSwordFromGold(goldCount));
        context.getSource().sendSuccess(() -> Component.literal("Gave sword with %d folds".formatted(goldCount)), true);
        return 0;
    }

    private static int exportAllArmor(CommandContext<CommandSourceStack> context) {
        var folder = Path.of(FabricLoader.getInstance().getConfigDir() + "/mythicmetals");
        try {
            Files.createDirectory(folder);
        } catch (FileAlreadyExistsException ignored) {
            MythicMetals.LOGGER.debug("Folder already exists");
        } catch (IOException e) {
            MythicMetals.LOGGER.error("Failed to create folder", e);
        }
        MythicArmor.ARMOR_MAP.forEach((name, armorSet) -> {
            var file = Path.of(FabricLoader.getInstance().getConfigDir() + "/mythicmetals/" + name.toLowerCase(Locale.ROOT) + ".md");
            try {
                Files.createFile(file);
            } catch (FileAlreadyExistsException ignored) {
                // no-op
            }
            catch (IOException e) {
                MythicMetals.LOGGER.error("Failed to write wiki data");
                context.getSource().sendSuccess(() -> Component.literal("Failed to %s wiki data to disk!".formatted(name)), false);
                return;
            }
            try {
                Files.writeString(file, WikiExporter.computeArmorSet(armorSet));
                var logString = "Successfully exported equipment/%s-tools".formatted(name.toLowerCase(Locale.ROOT));
                MythicMetals.LOGGER.info(logString);
            } catch (IOException e) {
                MythicMetals.LOGGER.error("Failed to write wiki data");
                context.getSource().sendSuccess(() -> Component.literal("Failed to %s wiki data to disk!".formatted(name)), false);
            }
        });

        context.getSource().sendSuccess(() -> Component.literal("Exported all the armor to wiki format into the config folder"), false);
        return 0;
    }

    private static int exportAllTools(CommandContext<CommandSourceStack> context) {
        var folder = Path.of(FabricLoader.getInstance().getConfigDir() + "/mythicmetals");
        try {
            Files.createDirectory(folder);
        } catch (FileAlreadyExistsException ignored) {
            MythicMetals.LOGGER.debug("Folder already exists");
        } catch (IOException e) {
            MythicMetals.LOGGER.error("Failed to create folder", e);
        }
        ReflectionUtils.iterateAccessibleStaticFields(MythicTools.class, ToolSet.class, (value, name, field) -> {
            var file = Path.of(FabricLoader.getInstance().getConfigDir() + "/mythicmetals/" + name.toLowerCase(Locale.ROOT) + "-tools.md");
            try {
                Files.deleteIfExists(file);
                Files.createFile(file);
            } catch (IOException e) {
                MythicMetals.LOGGER.error("Failed to write wiki data");
                context.getSource().sendSuccess(() -> Component.literal("Failed to %s wiki data to disk!".formatted(name)), false);
                return;
            }
            try {
                Files.writeString(file, WikiExporter.computeToolset(name, value));
                var logString = "Successfully exported equipment/%s-tools".formatted(name.toLowerCase(Locale.ROOT));
                MythicMetals.LOGGER.info(logString);
            } catch (IOException e) {
                MythicMetals.LOGGER.error("Failed to write wiki data");
                context.getSource().sendSuccess(() -> Component.literal("Failed to %s wiki data to disk!".formatted(name)), false);
            }
        });

        context.getSource().sendSuccess(() -> Component.literal("Exported all the tools (in the shed) to wiki format into the config folder"), false);
        return 0;
    }

    /**
     * Place every block set from {@link MythicBlocks} across the YZ axis
     *
     * @param context     ServerCommandSource Command Context
     * @param extraBlocks Map which can be used to insert extra blocks for a specific block set
     */
    public static int placeAllBlocksets(CommandContext<CommandSourceStack> context, Map<String, ArrayList<Block>> extraBlocks) {
        var source = context.getSource();
        var world = source.getLevel();
        AtomicInteger x = new AtomicInteger(((int) source.getPosition().x));
        AtomicInteger y = new AtomicInteger(((int) source.getPosition().y));
        int z = ((int) source.getPosition().z);
        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            y.set(((int) source.getPosition().y));
            if (blockSet.getOre() != null) {
                world.setBlockAndUpdate(BlockPos.containing(x.get(), y.getAndIncrement(), z), blockSet.getOre().defaultBlockState());
            }
            blockSet.getOreVariants().forEach(block -> {
                world.setBlockAndUpdate(BlockPos.containing(x.get(), y.getAndIncrement(), z), block.defaultBlockState());
            });
            if (blockSet.getOreStorageBlock() != null) {
                world.setBlockAndUpdate(BlockPos.containing(x.get(), y.getAndIncrement(), z), blockSet.getOreStorageBlock().defaultBlockState());
            }
            if (blockSet.getStorageBlock() != null) {
                world.setBlockAndUpdate(BlockPos.containing(x.get(), y.getAndIncrement(), z), blockSet.getStorageBlock().defaultBlockState());
            }
            if (blockSet.getAnvil() != null) {
                world.setBlockAndUpdate(BlockPos.containing(x.get(), y.getAndIncrement(), z), blockSet.getAnvil().defaultBlockState());
            }
            if (extraBlocks.containsKey(name)) {
                extraBlocks.get(name).forEach(extraBlock -> {
                    world.setBlockAndUpdate(BlockPos.containing(x.get(), y.getAndIncrement(), z), extraBlock.defaultBlockState());
                });
            }
            x.incrementAndGet();
        });
        source.sendSuccess(() -> Component.literal("Placed all blocksets starting at %s,%s,%s".formatted(source.getPosition().x, source.getPosition().y, source.getPosition().z)), true);
        return 0;
    }

    public static int placeMythicDisplay(CommandContext<CommandSourceStack> context) {
        int placements = -1;
        var material = StringArgumentType.getString(context, "material");

        if (!MythicTools.TOOL_MAP.containsKey(material) && !MythicArmor.ARMOR_MAP.containsKey(material) && MythicBlocks.BLOCKSET_MAP.containsKey(material)) {
            MythicMetals.LOGGER.error("Failed to find material: {}", material);
            context.getSource().sendSuccess(() -> Component.literal("Could not find any items for the material %s".formatted(material)), false);
            return -1;
        }

        // place the base structure
        var world = context.getSource().getLevel();
        var startPos = context.getSource().getEntity().blockPosition();

        placeStructure(world, startPos);

        if (material.equals("all")) {
            // oh dear god
        }

        if (MythicTools.TOOL_MAP.containsKey(material)) {
            // TODO - I like item frames more, unfortunately...
//            var toolSet = MythicTools.TOOL_MAP.get(material);
//            var displayEntitySword = new DisplayEntity.ItemDisplayEntity(EntityType.ITEM_DISPLAY, world);
//            var displayEntityPickaxe = new DisplayEntity.ItemDisplayEntity(EntityType.ITEM_DISPLAY, world);
//            var displayEntityAxe = new DisplayEntity.ItemDisplayEntity(EntityType.ITEM_DISPLAY, world);
//            var displayEntityShovel = new DisplayEntity.ItemDisplayEntity(EntityType.ITEM_DISPLAY, world);
//            var displayEntityHoe = new DisplayEntity.ItemDisplayEntity(EntityType.ITEM_DISPLAY, world);
//
//            displayEntitySword.setItemStack(toolSet.getSword().getDefaultStack());
//            displayEntityPickaxe.setItemStack(toolSet.getPickaxe().getDefaultStack());
//            displayEntityAxe.setItemStack(toolSet.getAxe().getDefaultStack());
//            displayEntityShovel.setItemStack(toolSet.getShovel().getDefaultStack());
//            displayEntityHoe.setItemStack(toolSet.getHoe().getDefaultStack());
//
//            displayEntitySword.setPos(startPos.getX() + 1.25, startPos.getY() + 3.5, startPos.getZ() + 1.2);
//            displayEntityPickaxe.setPos(startPos.getX() + 2.5, startPos.getY() + 3.5, startPos.getZ() + 1.2);
//            displayEntityAxe.setPos(startPos.getX() + 3.75, startPos.getY() + 3.5, startPos.getZ() + 1.2);
//            displayEntityShovel.setPos(startPos.getX() + 5, startPos.getY() + 3.5, startPos.getZ() + 1.2);
//            displayEntityHoe.setPos(startPos.getX() + 6.25, startPos.getY() + 3.5, startPos.getZ() + 1.2);
//
//            displayEntitySword.setYaw(180);
//            displayEntityPickaxe.setYaw(180);
//            displayEntityAxe.setYaw(180);
//            displayEntityShovel.setYaw(180);
//            displayEntityHoe.setYaw(180);
//
//            world.spawnEntity(displayEntitySword);
//            world.spawnEntity(displayEntityPickaxe);
//            world.spawnEntity(displayEntityAxe);
//            world.spawnEntity(displayEntityShovel);
//            world.spawnEntity(displayEntityHoe);
        }

        if (MythicArmor.ARMOR_MAP.containsKey(material)) {

        }

        if (MythicBlocks.BLOCKSET_MAP.containsKey(material)) {

        }

        return placements;
    }

    private static void placeStructure(Level world, BlockPos start) {
        // floor
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                world.setBlockAndUpdate(BlockPos.containing(start.getX() + i, start.getY(), start.getZ() + j), Blocks.WHITE_CONCRETE.defaultBlockState());

                if (j == 0) {
                    for (int y = 0; y < 6; y++) {
                        world.setBlockAndUpdate(BlockPos.containing(start.getX() + i, start.getY() + y, start.getZ() + j), Blocks.WHITE_CONCRETE.defaultBlockState());
                    }
                }
            }
        }
    }

    /**
     * Command which generates loot from a loot table X amount of times, and prints the output to the console
     */
    private static int testLootTable(CommandContext<CommandSourceStack> ctx) {
        var source = ctx.getSource();
        try {
            var lootTable = ResourceOrIdArgument.LootTableArgument.getLootTable(ctx, "loot_table");

            int rolls = IntegerArgumentType.getInteger(ctx, "rolls");

            LootParams lootContextParameterSet = new LootParams.Builder(ctx.getSource().getLevel())
                .withOptionalParameter(LootContextParams.THIS_ENTITY, source.getEntity())
                .withParameter(LootContextParams.ORIGIN, source.getPosition())
                .create(LootContextParamSets.CHEST);

            HashMap<Item, Integer> map = new HashMap<>();

            for (int i = 0; i < rolls; i++) {
                List<ItemStack> list = lootTable.value().getRandomItems(lootContextParameterSet);
                list.forEach(itemStack -> {
                    int count = map.getOrDefault(itemStack.getItem(), 0);
                    map.put(itemStack.getItem(), itemStack.getCount() + count);
                });
            }

            map.forEach((item, integer) -> {
                source.sendSuccess(() -> (Component.literal(item + ": " + integer.toString())), false);
            });
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    /**
     * ArmorSet exporter for the Mythic Metals Wiki
     * <br><br>
     * Note that it does not handle abilities or custom attributes, like Lava Swim Speed
     */
    private static int exportArmor(CommandContext<CommandSourceStack> context) {
        var armorSet = ArmorSetArgumentType.getArmorSet(context, "armorset");
        var source = context.getSource();
        var output = WikiExporter.computeArmorSet(armorSet);

        MythicMetals.LOGGER.info(output);
        source.sendSuccess(() -> Component.literal("Exported armor to wiki format in logs"), false);
        return 0;
    }

    /**
     * Ore/Material exporter for the Mythic Metals Wiki
     */
    private static int exportOreData(CommandContext<CommandSourceStack> context) {
        var oreConfig = OreConfigArgumentType.getOreConfig(context, "ore-config");
        var source = context.getSource();
        var blockSet = MythicBlocks.BLOCKSET_MAP.get(ORE_CONFIG.inverse().get(oreConfig));

        String oreName = StringUtilsAtHome.toTitleCase(blockSet.getName() + " Ores");

        String template = WikiExporter.createOreTemplate(oreName, blockSet, oreConfig);

        source.sendSuccess(() -> Component.literal("Exported ore stats for %s to wiki format".formatted(oreName)), false);
        MythicMetals.LOGGER.info(template);

        return 2;
    }

    /**
     * Tool exporter for the Mythic Metals Wiki
     */
    private static int exportTools(CommandContext<CommandSourceStack> context) {
        var toolset = ToolSetArgumentType.getToolSet(context, "toolset");

        var source = context.getSource();
        MythicMetals.LOGGER.info(WikiExporter.computeToolset(toolset.getName(), toolset));
        source.sendSuccess(() -> Component.literal("Exported tools to wiki format"), false);
        return 0;
    }

    /**
     * Summons an armor stand with a specific armor set and trim on top of the world <br>
     * Create the {@link ArmorTrim} using a pattern from {@link net.minecraft.world.item.equipment.trim.TrimPatterns}
     * and a material from {@link net.minecraft.world.item.equipment.trim.TrimMaterials}
     *
     * @param world    The world where you want to summon the armor stand, needs to be on the server
     * @param trim     {@link ArmorTrim} you wish to use on the armor.
     * @param armorSet {@link ArmorSet} that you wish to equip on the armor stand.
     * @param x        x-coordinate where the armor stand should spawn
     * @param z        z-coordinate where the armor stand should spawn
     * @return Returns whether the armor set was successfully created and summoned
     * @see ArmorTrim
     * @see MythicArmor
     * @see ArmorSet
     */
    public static boolean summonArmorStandWithTrim(Level world, @Nullable ArmorTrim trim, ArmorSet armorSet, float x, float z) {
        if (world.isClientSide()) return false;
        if (armorSet.equals(MythicArmor.TIDESINGER)) return false; // This has custom "trims", ignore it
        AtomicBoolean success = new AtomicBoolean(true);

        var armorStand = new ArmorStand(world, x, world.getMaxY() - 50, z);
        armorStand.setNoBasePlate(true);
        armorSet.getArmorItems().forEach(armorItem -> {
            var armorStack = new ItemStack(armorItem);
            if (!armorStack.is(ItemTags.TRIMMABLE_ARMOR)) {
                MythicMetals.LOGGER.debug("Armor Item %s is not trimmable".formatted(armorStack.getHoverName()));
            }
            if (trim != null) {
                armorStack.set(DataComponents.TRIM, trim);
            }
            if (success.get()) {
                var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
                armorStand.setItemSlot(equippableComponent.slot(), armorStack);
            }
        });
        world.addFreshEntity(armorStand);
        return success.get();
    }

    /**
     * @param world The world to get the DynamicRegistryManager from. This should only happen on the server
     * @return Returns all armor trims in a sorted ArrayList
     */
    public static ArrayList<ArmorTrim> getAllArmorTrims(Level world) {
        if (world.isClientSide()) return new ArrayList<>();

        var list = new ArrayList<ArmorTrim>();
        world.registryAccess().lookupOrThrow(Registries.TRIM_MATERIAL).listElements().forEach(armorMaterialEntry -> {
            world.registryAccess().lookupOrThrow(Registries.TRIM_PATTERN).listElements().forEach(armorTrimEntry -> {
                list.add(new ArmorTrim(armorMaterialEntry, armorTrimEntry));
            });
        });
        return list;
    }

    /**
     * @param world The world to get the DynamicRegistryManager from. This should only happen on the server
     * @return Returns all the trim patterns in the registry as a list of strings
     */
    public static ArrayList<String> getAllTrimPatternStrs(Level world) {
        if (world.isClientSide()) return new ArrayList<>();

        var list = new ArrayList<String>();
        world.registryAccess().lookupOrThrow(Registries.TRIM_PATTERN).listElements().forEach(armorTrimEntry -> list.add(armorTrimEntry.value().assetId().getPath()));
        return list;
    }

    /**
     * Suggest which format/location to use when dumping all the ore configs in {@link MythicOreConfigs}
     */
    private static CompletableFuture<Suggestions> dumpType(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder suggestion) {
        suggestion.suggest("console");
        return suggestion.buildFuture();
    }

    /**
     * Suggests armor materials from all the armor sets defined in {@link MythicArmor}
     * Includes one extra suggestion for "all"
     */
    private static CompletableFuture<Suggestions> armorMaterial(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder suggestion) {
        MythicArmor.ARMOR_MAP.forEach((s, armorSet) -> suggestion.suggest(s));
        suggestion.suggest("all");
        return suggestion.buildFuture();
    }

    private static CompletableFuture<Suggestions> material(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder suggestion) {
        var placeableMaterials = new HashSet<String>();
        placeableMaterials.addAll(MythicTools.TOOL_MAP.keySet());
        placeableMaterials.addAll(MythicArmor.ARMOR_MAP.keySet());
        placeableMaterials.add("all");
        placeableMaterials.forEach(suggestion::suggest);
        return suggestion.buildFuture();
    }

    /**
     * Suggests all the armor trim types available in the dynamic registry
     * Includes two extra suggestions for "all" and "none"
     */
    private static CompletableFuture<Suggestions> trimTypes(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder suggestion) {
        var list = new ArrayList<TrimPattern>();
        ctx.getSource().registryAccess().lookupOrThrow(Registries.TRIM_PATTERN).listElements().forEach(armorTrimEntry -> list.add(armorTrimEntry.value()));
        list.forEach(trimPattern -> suggestion.suggest(trimPattern.assetId().getPath()));
        suggestion.suggest("all").suggest("none");
        return suggestion.buildFuture();
    }

    private static int dumpAllOreConfigs(CommandContext<CommandSourceStack> context) {
        if (StringArgumentType.getString(context, "type").equals("console")) {
            ReflectionUtils.iterateAccessibleStaticFields(MythicOreConfigs.class, OreConfig.class, (feature, name, field) -> {
                if (!feature.offset && !feature.trapezoid) {
                    context.getSource().sendSuccess(() -> Component.literal(
                        name.toUpperCase(Locale.ROOT)
                            + " has the range between "
                            + feature.bottom
                            + " to "
                            + feature.top
                            + ", with a discard chance of "
                            + feature.discardChance * 100 + "%"), false);
                }
                if (feature.offset) {
                    context.getSource().sendSuccess(() -> Component.literal(
                        name.toUpperCase(Locale.ROOT)
                            + " has the range between "
                            + feature.bottom
                            + "(offset) to "
                            + feature.top
                            + ", with a discard chance of "
                            + feature.discardChance * 100 + "%"), false);
                }
                if (feature.trapezoid) {
                    context.getSource().sendSuccess(() -> Component.literal(
                        name.toUpperCase(Locale.ROOT)
                            + " has a triangle range between "
                            + feature.bottom
                            + " to "
                            + feature.top
                            + ", where the sweet spot is at Y = "
                            + ((feature.bottom + feature.top) / 2)
                            + " with a discard chance of "
                            + feature.discardChance * 100 + "%"), false);
                }

            });
            return 1;
        }
        return -1;
    }

    private static int armorStandCommand(CommandContext<CommandSourceStack> context, @NotNull String material, @Nullable String rawTrim) {
        var armorTrims = new ArrayList<ArmorTrim>();
        var world = context.getSource().getLevel();
        var pos = context.getSource().getPosition();
        float x = (int) pos.x + 0.5f;
        float z = (int) pos.z + 0.5f;

        final String trimQuery = rawTrim == null ? "none" : rawTrim;

        if (trimQuery.equals("none")) {
            // Summon all permutations of the armor at once. Not recommended due to the sheer density of them
            if (material.equals("all")) {

                int count = 0;
                var armorSetStrings = new TreeSet<>(MythicArmor.ARMOR_MAP.keySet());
                for (var armorSetName : armorSetStrings) {
                    if (summonArmorStandWithTrim(world, null, MythicArmor.ARMOR_MAP.get(armorSetName), x, z)) {
                        x++;
                        count++;
                    }
                }
                int finalCount = count;
                context.getSource().sendSuccess(() -> Component.literal("Summoned and dropping %d armorstands".formatted(finalCount)), true);
                return finalCount;
            } else {
                if (summonArmorStandWithTrim(world, null, MythicArmor.ARMOR_MAP.get(material), x, z)) {
                    context.getSource().sendSuccess(() -> Component.literal("Summoned and dropping one armorstand"), true);
                    return 1;
                } else {
                    context.getSource().sendSuccess(() -> Component.literal("Unable to summon the armor stand. It might be untrimmable"), false);
                }
            }
            return -1;
        }

        if (material.equals("all")) {
            if (MythicArmor.ARMOR_MAP.isEmpty()) {
                context.getSource().sendSuccess(() -> Component.literal("Unable to summon. Somehow the armor map is empty..."), false);
                return -1; // "how did this happen?" "a long time ago, actually never..."
            }

            if (trimQuery.equals("all")) {
                armorTrims.addAll(
                    getAllArmorTrims(world).stream().toList()
                );
            } else if (getAllTrimPatternStrs(world).contains(trimQuery)) {
                armorTrims.addAll(getAllArmorTrims(world).stream().filter(trim -> trim.pattern().value().assetId().getPath().equals(trimQuery)).toList());
            }

            // lambda moment
            MutableInt mutX = new MutableInt(pos.x);
            MutableInt mutZ = new MutableInt(pos.z);
            MutableInt count = new MutableInt(0);

            var armorSetStrings = new TreeSet<>(MythicArmor.ARMOR_MAP.keySet());
            for (var armorSetName : armorSetStrings) {
                armorTrims.forEach(armorTrim -> {
                    if (summonArmorStandWithTrim(world, armorTrim, MythicArmor.ARMOR_MAP.get(armorSetName), mutX.getValue(), mutZ.getValue())) {
                        mutX.add(2);
                        count.increment();
                    }
                });
                mutZ.add(2);
                mutX.setValue(pos.x);
            }
            context.getSource().sendSuccess(() -> Component.literal("Summoned and dropping %d armorstands with trims".formatted(count.getValue())), true);

            return count.getValue();
        } else if (MythicArmor.ARMOR_MAP.get(material) != null) {
            if (trimQuery.equals("all")) {
                armorTrims = getAllArmorTrims(world);
            } else {
                var trims = getAllArmorTrims(world).stream().filter(trim -> trim.pattern().value().assetId().getPath().equals(trimQuery)).toList();
                armorTrims.addAll(trims);
            }

            // Split the armor stands into groups using these offsets
            int splitPoint = armorTrims.size() / world.registryAccess().lookupOrThrow(Registries.TRIM_MATERIAL).size();

            // flip-flop
            int xOffset = 0;
            int zOffset = 0;
            int count = 0;

            for (int i = 0; i < armorTrims.size(); i++) {
                if (i % splitPoint == 0) {
                    xOffset += 2;
                    zOffset = 0;
                }
                var armorSet = MythicArmor.ARMOR_MAP.get(material);
                if (summonArmorStandWithTrim(world, armorTrims.get(i), armorSet, (int) pos.x + xOffset, (int) pos.z + zOffset)) {
                    count++;
                    zOffset += 2;
                } else {
                    xOffset -= 2;
                }
            }
            String feedback = "Summoned and dropping %d armorstands".formatted(count);
            context.getSource().sendSuccess(() -> Component.literal(feedback), true);
            return count;
        }
        return -1;
    }
}
