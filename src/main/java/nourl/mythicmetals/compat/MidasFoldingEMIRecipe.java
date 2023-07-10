package nourl.mythicmetals.compat;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.item.tools.MidasFoldingRecipe;
import nourl.mythicmetals.item.tools.MidasGoldSword;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static nourl.mythicmetals.item.tools.MidasGoldSword.Type.*;

public class MidasFoldingEMIRecipe implements EmiRecipe {

    Ingredient template;
    Ingredient base;
    Ingredient addition;
    List<EmiIngredient> inputs;
    EmiStack output;
    Identifier id;

    public MidasFoldingEMIRecipe(MidasFoldingRecipe recipe) {
        this.template = recipe.template;
        this.base = recipe.base;
        this.addition = recipe.addition;
        var outputStack = recipe.result;
        this.id = recipe.getId();

        if (this.base != null && this.addition != null && outputStack != null) {
            var inputStack = Arrays.stream(this.base.getMatchingStacks()).findFirst().orElseGet(() -> new ItemStack(Items.AIR)).copy();
            // Handle folding recipes, which usually follow the pattern of "input + gold block = output"
            if (outputStack.isOf(inputStack.getItem())) {
                if (MidasGoldSword.Type.isOf(inputStack, ROYAL)) {
                    inputStack.put(MidasGoldSword.GOLD_FOLDED, 640);
                } else if (MidasGoldSword.Type.isOf(inputStack, GILDED)) {
                    inputStack.put(MidasGoldSword.GOLD_FOLDED, 320);
                } else {
                    inputStack.put(MidasGoldSword.GOLD_FOLDED, 16);
                }
                inputs = List.of(
                    EmiIngredient.of(this.template),
                    EmiStack.of(inputStack),
                    EmiIngredient.of(this.addition)
                );
            }
            // Handles transformation from regular midas to gilded midas
            else if (MidasGoldSword.Type.isOf(inputStack, REGULAR) && MidasGoldSword.Type.isOf(outputStack, GILDED)) {
                inputStack.put(MidasGoldSword.GOLD_FOLDED, 319);
                inputs = List.of(
                    EmiIngredient.of(this.template),
                    EmiStack.of(inputStack),
                    EmiIngredient.of(this.addition)
                );
            }
            // Transformation of gilded to royal midas
            else if (MidasGoldSword.Type.isOf(inputStack, GILDED) && MidasGoldSword.Type.isOf(outputStack, ROYAL)) {
                inputStack.put(MidasGoldSword.GOLD_FOLDED, 640);
                inputStack.put(MidasGoldSword.IS_GILDED, true);
                inputs = List.of(
                    EmiIngredient.of(this.template),
                    EmiStack.of(inputStack),
                    EmiIngredient.of(this.addition)
                );
            }
        }

        // Handle folding recipes, which usually follow the pattern of "input + gold block = output"
        var inputStack = Arrays.stream(this.base.getMatchingStacks()).findFirst().orElseGet(() -> new ItemStack(Items.AIR)).copy();
        if (outputStack.getItem().equals(inputStack.getItem())) {
            if (MidasGoldSword.Type.isOf(outputStack, ROYAL)) {
                outputStack.put(MidasGoldSword.GOLD_FOLDED, 641);
            } else if (MidasGoldSword.Type.isOf(outputStack, GILDED)) {
                outputStack.put(MidasGoldSword.GOLD_FOLDED, 321);
            } else {
                outputStack.put(MidasGoldSword.GOLD_FOLDED, 17);
            }

            output = EmiStack.of(outputStack);
        }
        // Royal Midas Handler
        else if (MidasGoldSword.Type.isOf(outputStack, ROYAL)) {
            var outputWithNbt = outputStack.copy();
            outputWithNbt.put(MidasGoldSword.GOLD_FOLDED, 640);
            outputWithNbt.put(MidasGoldSword.IS_GILDED, true);
            outputWithNbt.put(MidasGoldSword.IS_ROYAL, true);
            output = EmiStack.of(outputWithNbt);

        }
        // Gilded Midas Handler
        else if (MidasGoldSword.Type.isOf(outputStack, GILDED)) {
            var outputWithNbt = outputStack.copy();
            outputWithNbt.put(MidasGoldSword.GOLD_FOLDED, 320);
            outputWithNbt.put(MidasGoldSword.IS_GILDED, true);
            output = EmiStack.of(outputWithNbt);
        }
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return VanillaEmiRecipeCategories.SMITHING;
    }

    @Override
    public @Nullable Identifier getId() {
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
