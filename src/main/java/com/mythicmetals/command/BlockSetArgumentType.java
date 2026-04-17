package com.mythicmetals.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mythicmetals.api.v2.BlockSet;
import com.mythicmetals.api.v2.Material;
import com.mythicmetals.item.MythicMaterials;
import io.wispforest.owo.util.ReflectionUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class BlockSetArgumentType implements ArgumentType<BlockSet> {

    public static Map<String, BlockSet> BLOCKSET_MAP = Util.make(() -> {
        var map = new HashMap<String, BlockSet>();
        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            if (material.blockSet() != null) {
                map.put(material.name(), material.blockSet());
            }
        });
        return map;
    });

    private final SimpleCommandExceptionType EXCEPTION = new SimpleCommandExceptionType(
        Component.translatable("command.mythicmetals.argument.blockset.error")
    );

    public static <S> BlockSet getBlockSet(CommandContext<S> context, String name) {
        return context.getArgument(name, BlockSet.class);
    }

    @Override
    public BlockSet parse(StringReader reader) throws CommandSyntaxException {
        final String material = reader.readString();
        if (BLOCKSET_MAP.containsKey(material)) {
            return BLOCKSET_MAP.get(material);
        }
        throw EXCEPTION.create();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        BLOCKSET_MAP.forEach((s, blockset) -> builder.suggest(s));
        return builder.buildFuture();
    }

    public static BlockSetArgumentType blockSet() {
        return new BlockSetArgumentType();
    }
}
