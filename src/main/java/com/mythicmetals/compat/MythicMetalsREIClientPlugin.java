package com.mythicmetals.compat;

import com.mythicmetals.item.tools.MythicTools;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCustomDisplay;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import java.util.*;

public class MythicMetalsREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        // Tipped Runite Arrow handling
        EntryIngredient arrowStack = EntryIngredient.of(EntryStacks.of(MythicTools.RUNITE_ARROW));
        ReferenceSet<Potion> registeredPotions = new ReferenceOpenHashSet<>();
        EntryRegistry.getInstance().getEntryStacks().filter(entry -> entry.getValueType() == ItemStack.class && entry.<ItemStack>castValue().getItem() == Items.LINGERING_POTION).forEach(entry -> {
            ItemStack itemStack = (ItemStack) entry.getValue();
            if (itemStack.contains(DataComponentTypes.POTION_CONTENTS)) {
                var potion = itemStack.get(DataComponentTypes.POTION_CONTENTS).potion().get();
                if (registeredPotions.add(potion.value())) {
                    List<EntryIngredient> input = new ArrayList<>();
                    for (int i = 0; i < 4; i++)
                        input.add(arrowStack);
                    input.add(EntryIngredients.of(itemStack));
                    for (int i = 0; i < 4; i++)
                        input.add(arrowStack);
                    var outputStack = PotionContentsComponent.createStack(MythicTools.TIPPED_RUNITE_ARROW, potion);
                    outputStack.setCount(8);
                    EntryIngredient output = EntryIngredients.of(outputStack);
                    registry.add(new DefaultCustomDisplay(input, Collections.singletonList(output), Optional.empty()));
                }
            }
        });
    }

    @Override
    public void registerEntries(EntryRegistry registry) {
        // There are many secrets in this game...
        // ... many of which that drive peeps insane
        registry.removeEntry(EntryStacks.of(MythicTools.Frogery.FROGE));
        registry.removeEntry(EntryStacks.of(MythicTools.Frogery.DOGE));
    }
}
