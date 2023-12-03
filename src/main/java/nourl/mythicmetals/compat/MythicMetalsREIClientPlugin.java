package nourl.mythicmetals.compat;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import it.unimi.dsi.fastutil.objects.ReferenceSet;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.displays.crafting.DefaultCustomDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.RecipeType;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.recipe.MidasFoldingRecipe;
import nourl.mythicmetals.recipe.TidesingerCoralRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MythicMetalsREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(MidasFoldingRecipe.class, RecipeType.SMITHING, MidasFoldingDisplay::new);
        registry.registerRecipeFiller(TidesingerCoralRecipe.class, RecipeType.SMITHING, TidesingerSmithingDisplay::new);

        // Tipped Runite Arrow handling
        EntryIngredient arrowStack = EntryIngredient.of(EntryStacks.of(MythicTools.RUNITE_ARROW));
        ReferenceSet<Potion> registeredPotions = new ReferenceOpenHashSet<>();
        EntryRegistry.getInstance().getEntryStacks().filter(entry -> entry.getValueType() == ItemStack.class && entry.<ItemStack>castValue().getItem() == Items.LINGERING_POTION).forEach(entry -> {
            ItemStack itemStack = (ItemStack) entry.getValue();
            Potion potion = PotionUtil.getPotion(itemStack);
            if (registeredPotions.add(potion)) {
                List<EntryIngredient> input = new ArrayList<>();
                for (int i = 0; i < 4; i++)
                    input.add(arrowStack);
                input.add(EntryIngredients.of(itemStack));
                for (int i = 0; i < 4; i++)
                    input.add(arrowStack);
                ItemStack outputStack = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW, 8);
                PotionUtil.setPotion(outputStack, potion);
                PotionUtil.setCustomPotionEffects(outputStack, PotionUtil.getCustomPotionEffects(itemStack));
                EntryIngredient output = EntryIngredients.of(outputStack);
                registry.add(new DefaultCustomDisplay(null, input, Collections.singletonList(output)));
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
