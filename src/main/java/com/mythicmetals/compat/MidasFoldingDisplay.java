package com.mythicmetals.compat;

import com.mythicmetals.data.recipe.MidasFoldingRecipe;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;
import java.util.Optional;

// FIXME - Update handling with new Midas Gold variants, and simplify
public class MidasFoldingDisplay extends DefaultSmithingDisplay {
    public MidasFoldingDisplay(RecipeHolder<MidasFoldingRecipe> recipe) {
        super(
            List.of(
                EntryIngredients.ofIngredient(recipe.value().templateIngredient().orElseThrow()),
                EntryIngredients.ofIngredient(recipe.value().baseIngredient()),
                EntryIngredients.ofIngredient(recipe.value().additionIngredient().orElseThrow())
            ),
            List.of(
                EntryIngredients.of(recipe.value().result())
            ),
            Optional.of(recipe.id().identifier())
        );
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
//        if (inputs.isEmpty() || outputs.isEmpty()) {
//            return super.getInputEntries();
//        }
//
//        EntryStack<ItemStack> templateEntry = inputs.getFirst().getFirst().cast();
//        var template = templateEntry.getValue();
//        EntryStack<ItemStack> baseEntry = inputs.get(1).getFirst().cast();
//        var base = baseEntry.getValue();
//        EntryStack<ItemStack> additionEntry = inputs.get(2).getFirst().cast();
//        var addition = additionEntry.getValue();
//        EntryStack<ItemStack> resultEntry = outputs.getFirst().getFirst().cast();
//        var result = resultEntry.getValue();
//
//        var inputStack = base.copy();
//
//        if (inputStack.is(result.getItem())) {
//            if (MidasGoldSword.Type.isOfMidas(inputStack, ROYAL)) {
//                inputStack.set(GOLD_FOLDED, MidasGoldComponent.of(641));
//            } else if (MidasGoldSword.Type.isOfMidas(inputStack, GILDED)) {
//                inputStack.set(GOLD_FOLDED, MidasGoldComponent.of(320));
//            } else {
//                inputStack.set(GOLD_FOLDED, MidasGoldComponent.of(16));
//            }
//            return List.of(
//                EntryIngredients.of(template),
//                EntryIngredients.of(inputStack),
//                EntryIngredients.of(addition)
//            );
//        }
//        // Handles transformation from regular midas to gilded midas
//        if (MidasGoldSword.Type.isOfMidas(inputStack, REGULAR) && MidasGoldSword.Type.isOfMidas(result, GILDED)) {
//            inputStack.set(GOLD_FOLDED, MidasGoldComponent.of(319));
//            return List.of(
//                EntryIngredients.of(template),
//                EntryIngredients.of(inputStack),
//                EntryIngredients.of(addition)
//            );
//        }
//        // Transformation of gilded to royal midas
//        if (MidasGoldSword.Type.isOfMidas(inputStack, GILDED) && MidasGoldSword.Type.isOfMidas(result, ROYAL)) {
//            inputStack.set(GOLD_FOLDED, MidasGoldComponent.of(640));
//            return List.of(
//                EntryIngredients.of(template),
//                EntryIngredients.of(inputStack),
//                EntryIngredients.of(addition)
//            );
//        }

        return super.getInputEntries();
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
//        if (inputs.isEmpty() || outputs.isEmpty()) {
//            return super.getInputEntries();
//        }
//
//        EntryStack<ItemStack> baseEntry = inputs.get(1).getFirst().cast();
//        var base = baseEntry.getValue();
//        EntryStack<ItemStack> resultEntry = outputs.getFirst().getFirst().cast();
//        var result = resultEntry.getValue();
//
//        var inputStack = base.copy();
//        var outputStack = result.copy();
//
//        // Handle folding recipes, which usually follow the pattern of "input + gold block = output"
//        if (outputStack.getItem().equals(inputStack.getItem())) {
//
//            if (MidasGoldSword.Type.isOfMidas(outputStack, ROYAL)) {
//                outputStack.set(GOLD_FOLDED, MidasGoldComponent.of(641));
//            } else if (MidasGoldSword.Type.isOfMidas(outputStack, GILDED)) {
//                outputStack.set(GOLD_FOLDED, MidasGoldComponent.of(321));
//            } else {
//                outputStack.set(GOLD_FOLDED, MidasGoldComponent.of(17));
//            }
//
//            return List.of(
//                EntryIngredients.of(outputStack)
//            );
//        }
//        // Royal Midas Handler
//        if (MidasGoldSword.Type.isOfMidas(outputStack, ROYAL)) {
//            var outputWithNbt = outputStack.copy();
//            outputWithNbt.set(GOLD_FOLDED, MidasGoldComponent.of(641));
//            return List.of(
//                EntryIngredients.of(outputWithNbt)
//            );
//
//        }
//        // Gilded Midas Handler
//        if (MidasGoldSword.Type.isOfMidas(outputStack, GILDED)) {
//            var outputWithNbt = outputStack.copy();
//            outputWithNbt.set(GOLD_FOLDED, MidasGoldComponent.of(320));
//            return List.of(
//                EntryIngredients.of(outputWithNbt)
//            );
//        }
        return super.getOutputEntries();
    }
}

