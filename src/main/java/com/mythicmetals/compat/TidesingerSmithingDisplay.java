package com.mythicmetals.compat;

import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.TidesingerPatternComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.recipe.TidesingerCoralRecipe;
import java.util.Arrays;
import java.util.List;

public class TidesingerSmithingDisplay extends DefaultSmithingDisplay {
    Ingredient template;
    Ingredient base;
    Ingredient addition;
    ItemStack outputStack;

    public TidesingerSmithingDisplay(RecipeEntry<TidesingerCoralRecipe> recipe) {
        super(
            recipe.value(),
            recipe.id(),
            List.of(EntryIngredients.ofIngredient(recipe.value().template()),
                EntryIngredients.ofIngredient(recipe.value().base()),
                EntryIngredients.ofIngredient(recipe.value().addition())
            )
        );

        this.template = recipe.value().template();
        this.base = recipe.value().base();
        this.addition = recipe.value().addition();
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
        if (this.base != null && this.addition != null && this.outputStack != null) {
            var additionStack = Arrays.stream(this.addition.getMatchingStacks()).findFirst().orElse(ItemStack.EMPTY).copy();
            if (additionStack.isIn(MythicTags.TIDESINGER_CORAL)) {
                outputStack.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(additionStack.getItem()));
                return List.of(EntryIngredients.of(outputStack));
            }
        }
        return super.getOutputEntries();
    }
}

