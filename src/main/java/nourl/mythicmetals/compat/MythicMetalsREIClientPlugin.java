package nourl.mythicmetals.compat;


import dev.architectury.event.EventResult;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import nourl.mythicmetals.tools.MythicTools;

import java.util.List;

public class MythicMetalsREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerVisibilityPredicate((category, display) -> {
            if (display.getOutputEntries().stream().flatMap(List::stream)
                    .anyMatch(entryStack -> entryStack.getValue() instanceof ItemStack stack
                            && (stack.getItem() == MythicTools.Frogery.FROGE
                            || stack.getItem() == MythicTools.Frogery.DOGE
                            || stack.getItem() == MythicTools.GILDED_MIDAS_GOLD_SWORD
                    )))
                return EventResult.interruptFalse();
            else return EventResult.pass();
        });
    }

    @Override
    public void registerEntries(EntryRegistry registry) {
        registry.removeEntry(EntryStacks.of(MythicTools.Frogery.FROGE));
        registry.removeEntry(EntryStacks.of(MythicTools.Frogery.DOGE));
        registry.removeEntry(EntryStacks.of(MythicTools.GILDED_MIDAS_GOLD_SWORD));
    }
}
