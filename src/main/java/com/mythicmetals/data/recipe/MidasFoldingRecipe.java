package com.mythicmetals.data.recipe;

import com.mythicmetals.item.component.GoldFoldedComponent;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import com.mythicmetals.item.tools.MythicTools;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import io.wispforest.owo.serialization.endec.MinecraftEndecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;

import static com.mythicmetals.item.component.MythicDataComponents.GOLD_FOLDED;

public class MidasFoldingRecipe implements SmithingRecipe {

    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Optional<Ingredient> addition;
    private final ItemStack result;
    public static final StructEndec<MidasFoldingRecipe> ENDEC = StructEndecBuilder.of(
        CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("template", MidasFoldingRecipe::templateIngredient),
        CodecUtils.toEndec(Ingredient.CODEC).fieldOf("base", MidasFoldingRecipe::baseIngredient),
        CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", MidasFoldingRecipe::additionIngredient),
        MinecraftEndecs.ITEM_STACK.fieldOf("result", recipe -> recipe.result),
        MidasFoldingRecipe::new
    );
    @Nullable
    private PlacementInfo ingredientPlacement;

    public MidasFoldingRecipe(Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, ItemStack result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(SmithingRecipeInput input, Level world) {
        if (!SmithingRecipe.super.matches(input, world)) {
            return false;
        }
        var stack = input.base();

        if (!stack.has(GOLD_FOLDED)) return false;
        int goldCount = stack.has(GOLD_FOLDED) ? stack.get(GOLD_FOLDED).goldFolded() : 0;

        if (input.template().getItem().equals(MythicMaterials.MIDAS_GOLD.extraItems().get(MythicResourceKeys.ROYAL_MIDAS_SMITHING_TEMPLATE))) {
            return goldCount >= 640;
        }

        if (stack.getItem().equals(MythicTools.ROYAL_MIDAS_GOLD_SWORD)) {
            return goldCount >= 640 && goldCount < 10000;
        }

        return goldCount < 640;
    }


    @Override
    public Optional<Ingredient> templateIngredient() {
        return template;
    }

    @Override
    public Ingredient baseIngredient() {
        return base;
    }

    @Override
    public Optional<Ingredient> additionIngredient() {
        return addition;
    }

    public ItemStack result() {
        return result;
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        var swordInputStack = input.base().copy();

        var goldComponent = swordInputStack.getOrDefault(GOLD_FOLDED, GoldFoldedComponent.of(0));
        int goldCount = goldComponent.goldFolded();
        swordInputStack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1, goldComponent.isRoyal()));

        // Gilded Midas Gold Sword handler
        if (swordInputStack.getItem().equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {

            // Transform into Royal Midas Gold Sword
            if (goldCount >= 640) {
                var swordnite = swordInputStack.transmuteCopy(MythicTools.ROYAL_MIDAS_GOLD_SWORD, 1);
                swordnite.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1, true));
                return swordnite;
            }
        }

        // Handle Midas Gold Sword, transform if you fold and it at least has 320 gold on it
        if (swordInputStack.getItem().equals(MythicTools.MIDAS_GOLD_SWORD)) {

            // Transform Midas Gold Sword into Gilded Midas Gold Sword
            if (goldCount >= 319) {
                var swordnite = swordInputStack.transmuteCopy(MythicTools.GILDED_MIDAS_GOLD_SWORD, 1);
                swordnite.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1));
                return swordnite;
            }
        }
        return swordInputStack;
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<MidasFoldingRecipe> getSerializer() {
        return MythicRecipeSerializers.MIDAS_FOLDING_RECIPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(this.template, Optional.of(this.base), this.addition));
        }

        return this.ingredientPlacement;
    }

}
