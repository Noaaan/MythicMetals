package nourl.mythicmetals.utils;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.config.OreConfig;
import nourl.mythicmetals.config.VariantConfig;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class MythicCommands {

    private MythicCommands() {}

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
            dispatcher.register(CommandManager.literal("mythicmetals")
                .then(CommandManager.literal("range")
                    .then(CommandManager.argument("type", StringArgumentType.word())
                        .suggests(MythicCommands::oreTypes)
                        .requires(source -> source.hasPermissionLevel(1))
                        .executes(context -> {
                            MythicConfig config = MythicMetals.CONFIG;
                            if (config == null)
                                return 1;

                            FieldIterator.iterateAccessibleFields(config, OreConfig.class, (feature, name) -> {
                                if (feature.enabled && !feature.offset && !feature.trapezoid)
                                    context.getSource().sendFeedback(new LiteralText(
                                        name.toUpperCase(Locale.ROOT)
                                        + " has the range between "
                                        + feature.bottom
                                        + " to "
                                        + feature.top
                                        + ", with a discard chance of "
                                        + feature.discardChance * 100 + "%"), false);
                                if (feature.enabled && feature.offset)
                                    context.getSource().sendFeedback(new LiteralText(
                                        name.toUpperCase(Locale.ROOT)
                                        + " has the range between "
                                        + feature.bottom
                                        + "(offset) to "
                                        + feature.top
                                        + ", with a discard chance of "
                                        + feature.discardChance * 100 + "%"), false);
                                if (feature.enabled && feature.trapezoid)
                                    context.getSource().sendFeedback(new LiteralText(
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
                                    context.getSource().sendFeedback(new LiteralText("Ore" + name + "is disabled."), false);

                            });

                            FieldIterator.iterateAccessibleFields(config, VariantConfig.class, (feature, name) -> {
                                if (feature.enabled)
                                    context.getSource().sendFeedback(new LiteralText(
                                            name.toUpperCase(Locale.ROOT)
                                                    + " has the range between "
                                                    + feature.bottom
                                                    + " to "
                                                    + feature.top
                                                    + ", while its variant has the range of "
                                                    + feature.bottomVariant
                                                    + " to "
                                                    + feature.topVariant
                                                    + ", with a discard chance of "
                                                    + feature.discardChance * 100 + "%"), false);
                                else
                                    context.getSource().sendFeedback(new LiteralText("Ore" + name + "is disabled."), false);

                            });
                            return 1;
                        })
                    )
                )
            )
        );
    }

    private static CompletableFuture<Suggestions> oreTypes(CommandContext<ServerCommandSource> ctx, SuggestionsBuilder suggestion) {
        suggestion.suggest("everything");
        return suggestion.buildFuture();
    }
}
