package com.mythicmetals.data.recipe;

import com.mythicmetals.item.component.MidasGoldComponent;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.tools.MidasGoldSword;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import java.util.List;
import java.util.Optional;

import static com.mythicmetals.item.component.MythicDataComponents.MIDAS_GOLD;

public class LegacyMidasRecipe implements SmithingRecipe {

    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Optional<Ingredient> addition;
    private final ItemStackTemplate result;

    @Nullable
    private PlacementInfo ingredientPlacement;

    public static final StructEndec<LegacyMidasRecipe> ENDEC = StructEndecBuilder.of(
        CodecUtils.toEndec(Ingredient.CODEC).optionalOf().optionalFieldOf("template", LegacyMidasRecipe::templateIngredient, Optional.empty()),
        CodecUtils.toEndec(Ingredient.CODEC).fieldOf("base", LegacyMidasRecipe::baseIngredient),
        CodecUtils.toEndec(Ingredient.CODEC).optionalOf().optionalFieldOf("addition", LegacyMidasRecipe::additionIngredient, Optional.empty()),
        CodecUtils.toEndec(ItemStackTemplate.CODEC).fieldOf("result", LegacyMidasRecipe::result),
        LegacyMidasRecipe::new
    );

    public LegacyMidasRecipe(Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, ItemStackTemplate result) {
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    @Override
    public boolean matches(SmithingRecipeInput input, Level world) {
        return input.base().has(MythicDataComponents.MIDAS_GOLD) && input.base().get(MythicDataComponents.MIDAS_GOLD).legacy();
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        var swordInputStack = input.base().copy();
        var goldComponent = swordInputStack.get(MIDAS_GOLD);
        int goldCount = goldComponent.folds();
        var swordnite = swordInputStack.transmuteCopy(MidasGoldSword.createSwordFromGold(goldCount).getItem(), 1);
        swordnite.set(MIDAS_GOLD, MidasGoldComponent.of(goldCount));
        return swordnite;
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
    public RecipeSerializer<LegacyMidasRecipe> getSerializer() {
        return MythicRecipeSerializers.LEGACY_MIDAS_RECIPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        if (this.ingredientPlacement == null) {
            this.ingredientPlacement = PlacementInfo.createFromOptionals(List.of(this.template, Optional.of(this.base), this.addition));
        }

        return this.ingredientPlacement;
    }

    @Override
    public Ingredient baseIngredient() {
        return base;
    }

    @Override
    public Optional<Ingredient> additionIngredient() {
        return addition;
    }

    @Override
    public Optional<Ingredient> templateIngredient() {
        return template;
    }

    public ItemStackTemplate result() {
        return result;
    }
}
