package nourl.mythicmetals.misc;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import nourl.mythicmetals.config.MythicOreConfigs;
import nourl.mythicmetals.config.OreConfig;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public final class MythicCommands {

    private MythicCommands() {}

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccessdedicated, environment) ->
            dispatcher.register(CommandManager.literal("mythicmetals")
                .requires(source -> source.hasPermissionLevel(1))
                .then(CommandManager.literal("range")
                    .then(CommandManager.argument("type", StringArgumentType.word())
                        .suggests(MythicCommands::dumpType)
                        .executes(context -> {
                            if (StringArgumentType.getString(context, "type").equals("console")) {
                                ReflectionUtils.iterateAccessibleStaticFields(MythicOreConfigs.class, OreConfig.class, (feature, name, field) -> {
                                    if (!feature.offset && !feature.trapezoid)
                                        context.getSource().sendFeedback(Text.literal(
                                                name.toUpperCase(Locale.ROOT)
                                                        + " has the range between "
                                                        + feature.bottom
                                                        + " to "
                                                        + feature.top
                                                        + ", with a discard chance of "
                                                        + feature.discardChance * 100 + "%"), false);
                                    if (feature.offset)
                                        context.getSource().sendFeedback(Text.literal(
                                                name.toUpperCase(Locale.ROOT)
                                                        + " has the range between "
                                                        + feature.bottom
                                                        + "(offset) to "
                                                        + feature.top
                                                        + ", with a discard chance of "
                                                        + feature.discardChance * 100 + "%"), false);
                                    if (feature.trapezoid)
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

                                });
                                return 1;
                            }
                            return 1;

                        })
                    )
                )
            )
        );
    }

    private static CompletableFuture<Suggestions> dumpType(CommandContext<ServerCommandSource> ctx, SuggestionsBuilder suggestion) {
        suggestion.suggest("console");
        return suggestion.buildFuture();
    }
}
