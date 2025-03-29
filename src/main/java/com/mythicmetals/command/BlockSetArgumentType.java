package com.mythicmetals.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.text.Text;
import com.mythicmetals.block.BlockSet;
import com.mythicmetals.block.MythicBlocks;
import java.util.concurrent.CompletableFuture;

public class BlockSetArgumentType implements ArgumentType<BlockSet> {
    private final SimpleCommandExceptionType EXCEPTION = new SimpleCommandExceptionType(
        Text.translatable("command.mythicmetals.argument.blockset.error")
    );

    public static <S> BlockSet getBlockSet(CommandContext<S> context, String name) {
        return context.getArgument(name, BlockSet.class);
    }

    @Override
    public BlockSet parse(StringReader reader) throws CommandSyntaxException {
        final String material = reader.readString();
        if (MythicBlocks.BLOCKSET_MAP.containsKey(material)) {
            return MythicBlocks.BLOCKSET_MAP.get(material);
        }
        throw EXCEPTION.create();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        MythicBlocks.BLOCKSET_MAP.forEach((s, blockset) -> builder.suggest(s));
        return builder.buildFuture();
    }

    public static BlockSetArgumentType blockSet() {
        return new BlockSetArgumentType();
    }
}
