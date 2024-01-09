package nourl.mythicmetals.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.item.tools.ToolSet;
import java.util.concurrent.CompletableFuture;

public class ToolSetArgumentType implements ArgumentType<ToolSet> {

    private final SimpleCommandExceptionType EXCEPTION = new SimpleCommandExceptionType(Text.translatable("command.mythicmetals.argument.toolset.error"));

    public static ToolSetArgumentType toolSet() {
        return new ToolSetArgumentType();
    }

    public static <S> ToolSet getToolSet(CommandContext<S> context, String name) {
        return context.getArgument(name, ToolSet.class);
    }

    @Override
    public ToolSet parse(StringReader reader) throws CommandSyntaxException {
        final String material = reader.readString();
        if (MythicTools.TOOL_MAP.containsKey(material)) {
            return MythicTools.TOOL_MAP.get(material);
        }
        throw EXCEPTION.create();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return CommandSource.suggestMatching(MythicTools.TOOL_MAP.keySet(), builder);
    }
}
