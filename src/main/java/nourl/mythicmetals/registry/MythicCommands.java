package nourl.mythicmetals.registry;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.config.OreConfig;
import nourl.mythicmetals.utils.FieldIterator;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class MythicCommands {

    private MythicCommands() {}

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccessdedicated, environment) ->
            dispatcher.register(CommandManager.literal("mythicmetals")
                .then(CommandManager.literal("range")
                    .then(CommandManager.argument("type", StringArgumentType.word())
                        .suggests(MythicCommands::dumpType)
                        .requires(source -> source.hasPermissionLevel(1))
                        .executes(context -> {
                            MythicConfig config = MythicMetals.CONFIG;
                            if (config == null)
                                return 1;
                            switch (StringArgumentType.getString(context, "type")) {
                                case "console" -> {
                                    FieldIterator.iterateAccessibleFields(config, OreConfig.class, (feature, name) -> {
                                        if (feature.enabled && !feature.offset && !feature.trapezoid)
                                            context.getSource().sendFeedback(Text.literal(
                                                name.toUpperCase(Locale.ROOT)
                                                    + " has the range between "
                                                    + feature.bottom
                                                    + " to "
                                                    + feature.top
                                                    + ", with a discard chance of "
                                                    + feature.discardChance * 100 + "%"), false);
                                        if (feature.enabled && feature.offset)
                                            context.getSource().sendFeedback(Text.literal(
                                                name.toUpperCase(Locale.ROOT)
                                                    + " has the range between "
                                                    + feature.bottom
                                                    + "(offset) to "
                                                    + feature.top
                                                    + ", with a discard chance of "
                                                    + feature.discardChance * 100 + "%"), false);
                                        if (feature.enabled && feature.trapezoid)
                                            context.getSource().sendFeedback(Text.literal(
                                                name.toUpperCase(Locale.ROOT)
                                                    + " has a triangle range between "
                                                    + feature.bottom
                                                    + " to "
                                                    + feature.top
                                                    + ", where the sweet spot is at Y = "
                                                    + ((feature.bottom + feature.top) / 2)
                                                    + " with a discard chance of "
                                                    + feature.discardChance * 100 + "%"), false);
                                        if (!feature.enabled)
                                            context.getSource().sendFeedback(Text.literal("Ore" + name + "is disabled."), false);

                                    });
                                    return 1;
                                }
                                case "file" -> {
                                    try (var file = Files.newOutputStream(FabricLoader.getInstance().getConfigDir().resolve("debug/mythicmetals.csv"))) {
                                        var contents = new StringBuilder();
                                        FieldIterator.iterateAccessibleFields(config, OreConfig.class, (feature, name) -> {
                                        if (feature.enabled) {
                                                contents.append(name).append(",")
                                                .append(feature.bottom).append(",")
                                                .append(feature.top).append(",")
                                                .append(feature.perChunk).append(",")
                                                .append(feature.veinSize).append(",")
                                                .append(feature.offset).append(",")
                                                .append(feature.trapezoid).append("\n");
                                        }
                                    });
                                    new OutputStreamWriter(file).write(contents.toString());
                                    return 1;
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }}
                            }
                            return 1;

                        })
                    )
                )
            )
        );
    }

    private static CompletableFuture<Suggestions> dumpType(CommandContext<ServerCommandSource> ctx, SuggestionsBuilder suggestion) {
        suggestion.suggest("console").suggest("file");
        return suggestion.buildFuture();
    }
}
