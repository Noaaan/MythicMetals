package nourl.mythicmetals.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.text.Text;
import nourl.mythicmetals.armor.ArmorSet;
import nourl.mythicmetals.armor.MythicArmor;
import java.util.concurrent.CompletableFuture;

public class ArmorSetArgumentType implements ArgumentType<ArmorSet> {

    private final SimpleCommandExceptionType EXCEPTION = new SimpleCommandExceptionType(Text.translatable("command.mythicmetals.argument.armorset.error"));

    public static <S> ArmorSet getArmorSet(CommandContext<S> context, String name) {
        return context.getArgument(name, ArmorSet.class);
    }

    @Override
    public ArmorSet parse(StringReader reader) throws CommandSyntaxException {
        final String material = reader.readString();
        if (MythicArmor.ARMOR_MAP.containsKey(material)) {
            return MythicArmor.ARMOR_MAP.get(material);
        }
        throw EXCEPTION.create();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        MythicArmor.ARMOR_MAP.forEach((s, toolSet) -> builder.suggest(s));
        return builder.buildFuture();
    }

    public static ArmorSetArgumentType armorSet() {
        return new ArmorSetArgumentType();
    }
}
