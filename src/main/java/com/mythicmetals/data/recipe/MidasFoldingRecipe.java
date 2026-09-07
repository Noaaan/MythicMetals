package com.mythicmetals.data.recipe;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import com.mythicmetals.item.component.GoldFoldedComponent;
import com.mythicmetals.item.tools.MidasGoldSword;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.endec.StructEndec;
import io.wispforest.endec.impl.StructEndecBuilder;
import io.wispforest.owo.serialization.CodecUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.mythicmetals.item.component.MythicDataComponents.GOLD_FOLDED;
import static net.minecraft.world.item.Item.BASE_ATTACK_DAMAGE_ID;
import static net.minecraft.world.item.Item.BASE_ATTACK_SPEED_ID;

public class MidasFoldingRecipe implements SmithingRecipe {

    private final Optional<Ingredient> template;
    private final Ingredient base;
    private final Optional<Ingredient> addition;
    private final ItemStackTemplate result;
    public static final StructEndec<MidasFoldingRecipe> ENDEC = StructEndecBuilder.of(
        CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("template", MidasFoldingRecipe::templateIngredient),
        CodecUtils.toEndec(Ingredient.CODEC).fieldOf("base", MidasFoldingRecipe::baseIngredient),
        CodecUtils.toEndec(Ingredient.CODEC).optionalOf().fieldOf("addition", MidasFoldingRecipe::additionIngredient),
        CodecUtils.toEndec(ItemStackTemplate.CODEC).fieldOf("result", recipe -> recipe.result),
        MidasFoldingRecipe::new
    );
    @Nullable
    private PlacementInfo ingredientPlacement;

    public MidasFoldingRecipe(Optional<Ingredient> template, Ingredient base, Optional<Ingredient> addition, ItemStackTemplate result) {
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

    public ItemStackTemplate result() {
        return result;
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input) {
        var swordInputStack = input.base().copy();
        // FIXME - I genuinely despise this, having immutable modifier maps is just annoying.
        // even if you want to "compromise" and not add a fancy green tooltip, doing anything beyond adding blue text
        // is means you HAVE to rebuild the entire component map since there is no method for it.
        // But what does the "withModifier" method do?
        // manually recompute the ENTIRE map just to add your new attribute modifier.
        // it also just does not care about adding entries with the same ID.
        // it does correctly handle attribute modifiers with duplicate IDs, so no infinite damage stacking,
        // although it just... Why isn't damage & attack speed just a simple fucking number?
        // Noaaan - 08.09.2026
        if (!swordInputStack.has(DataComponents.ATTRIBUTE_MODIFIERS)) return swordInputStack;
        var attributes = swordInputStack.get(DataComponents.ATTRIBUTE_MODIFIERS);
        assert attributes != null;

        var goldComponent = swordInputStack.getOrDefault(GOLD_FOLDED, GoldFoldedComponent.of(0));
        int goldCount = goldComponent.goldFolded();
        swordInputStack.set(GOLD_FOLDED, GoldFoldedComponent.of(goldCount + 1, goldComponent.isRoyal()));

        // FIXME - This is also copying the old damage. When sword turns into gilded/royal, increase the REAL damage by 1!
        //  or find some other solution for this mess

        // copy all existing attributes except ours
        var builder = ItemAttributeModifiers.builder();
        attributes.modifiers().forEach(entry -> {
            if (!entry.modifier().is(MidasGoldSword.MIDAS_BONUS_DAMAGE_ID)) {
                builder.add(entry.attribute(), entry.modifier(), entry.slot(), entry.display());
            }
        });
        // add bonus damage
        var bonusDamage = MidasGoldSword.computeBonusDamage(goldCount);
        builder.add(
            Attributes.ATTACK_DAMAGE,
            new AttributeModifier(
                MidasGoldSword.MIDAS_BONUS_DAMAGE_ID, bonusDamage, AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.MAINHAND,
            ItemAttributeModifiers.Display.override(Component.translatable("tooltip.midas_gold.damage", bonusDamage, I18n.get("attribute.name.attack_damage")).withStyle(ChatFormatting.GOLD))
        );

        swordInputStack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());

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
