package com.mythicmetals.compat;

import com.mythicmetals.recipe.TidesingerCoralRecipe;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import java.util.List;
import java.util.Optional;

public class TidesingerSmithingDisplay extends DefaultSmithingDisplay {
    Ingredient template;
    Ingredient base;
    Ingredient addition;
    ItemStack outputStack;

    public TidesingerSmithingDisplay(RecipeEntry<TidesingerCoralRecipe> recipe) {
        super(
            List.of(EntryIngredients.ofIngredient(recipe.value().template().get()),
                EntryIngredients.ofIngredient(recipe.value().base().get()),
                EntryIngredients.ofIngredient(recipe.value().addition().get())
            ),
            List.of(EntryIngredients.of(recipe.value().result())),
            Optional.of(recipe.id().getValue())
        );

        this.template = recipe.value().template().get();
        this.base = recipe.value().base().get();
        this.addition = recipe.value().addition().get();
        this.outputStack = recipe.value().result();

    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        if (this.base != null && this.addition != null && outputStack != null) {
            return List.of(EntryIngredients.ofIngredient(this.template), EntryIngredients.ofIngredient(this.base), EntryIngredients.ofIngredient(this.addition));
        }
        return super.getInputEntries();
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        // FIXME
//        if (this.base != null && this.addition != null && this.outputStack != null) {
//            var additionStack = Arrays.stream(this.addition.getMatchingItems().toArray()).findFirst().orElse(ItemStack.EMPTY);
//            if (additionStack.isIn(MythicTags.TIDESINGER_CORAL)) {
//                outputStack.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(additionStack.getItem()));
//                return List.of(EntryIngredients.of(outputStack));
//            }
//        }
        return super.getOutputEntries();
    }
}

