package com.mythicmetals.compat;

import com.mythicmetals.recipe.MidasFoldingRecipe;
import dev.emi.emi.api.recipe.*;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class MidasFoldingEMIRecipe implements EmiRecipe {

    Ingredient template;
    Ingredient base;
    Ingredient addition;
    List<EmiIngredient> inputs;
    EmiStack output;
    ResourceLocation id;

    public MidasFoldingEMIRecipe(MidasFoldingRecipe recipe) {
//        this.template = recipe.template();
//        this.base = recipe.base();
//        this.addition = recipe.addition();
//        var outputStack = recipe.result();
//
//        if (this.base != null && this.addition != null && outputStack != null) {
//            var inputStack = Arrays.stream(this.base.getMatchingStacks()).findFirst().orElse(ItemStack.EMPTY).copy();
//            // Handle folding recipes, which usually follow the pattern of "input + gold block = output"
//            if (outputStack.isOf(inputStack.getItem())) {
//                if (MidasGoldSword.Type.isOfMidas(inputStack, ROYAL)) {
//                    inputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(640, true));
//                } else if (MidasGoldSword.Type.isOfMidas(inputStack, GILDED)) {
//                    inputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(320));
//                } else {
//                    inputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(16));
//                }
//                inputs = List.of(
//                    EmiIngredient.of(this.template),
//                    EmiStack.of(inputStack),
//                    EmiIngredient.of(this.addition)
//                );
//            }
//            // Handles transformation from regular midas to gilded midas
//            else if (MidasGoldSword.Type.isOfMidas(inputStack, REGULAR) && MidasGoldSword.Type.isOfMidas(outputStack, GILDED)) {
//                inputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(319));
//                inputs = List.of(
//                    EmiIngredient.of(this.template),
//                    EmiStack.of(inputStack),
//                    EmiIngredient.of(this.addition)
//                );
//            }
//            // Transformation of gilded to royal midas
//            else if (MidasGoldSword.Type.isOfMidas(inputStack, GILDED) && MidasGoldSword.Type.isOfMidas(outputStack, ROYAL)) {
//                inputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(319));
//                inputs = List.of(
//                    EmiIngredient.of(this.template),
//                    EmiStack.of(inputStack),
//                    EmiIngredient.of(this.addition)
//                );
//            }
//
//            // Handle folding recipes, which usually follow the pattern of "input + gold block = output"
//            if (outputStack.getItem().equals(inputStack.getItem())) {
//                if (MidasGoldSword.Type.isOfMidas(outputStack, ROYAL)) {
//                    outputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(641, true));
//                } else if (MidasGoldSword.Type.isOfMidas(outputStack, GILDED)) {
//                    outputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(321));
//                } else {
//                    outputStack.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(17));
//                }
//
//                output = EmiStack.of(outputStack);
//            }
//            // Royal Midas Handler
//            else if (MidasGoldSword.Type.isOfMidas(outputStack, ROYAL)) {
//                var outputCopy = outputStack.copy();
//                outputCopy.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(640, true));
//                output = EmiStack.of(outputCopy);
//
//            }
//            // Gilded Midas Handler
//            else if (MidasGoldSword.Type.isOfMidas(outputStack, GILDED)) {
//                var outputCopy = outputStack.copy();
//                outputCopy.set(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(320));
//                output = EmiStack.of(outputCopy);
//            }
//        }

    }

    @Override
    public EmiRecipeCategory getCategory() {
        return VanillaEmiRecipeCategories.SMITHING;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 112;
    }

    @Override
    public int getDisplayHeight() {
        return 18;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(EmiTexture.EMPTY_ARROW, 62, 1);
        widgets.addSlot(inputs.get(0), 0, 0);
        widgets.addSlot(inputs.get(1), 18, 0);
        widgets.addSlot(inputs.get(2), 36, 0);
        widgets.addSlot(output, 94, 0).recipeContext(this);
    }
}
