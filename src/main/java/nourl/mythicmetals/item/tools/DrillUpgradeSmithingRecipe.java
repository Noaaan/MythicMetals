package nourl.mythicmetals.item.tools;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import nourl.mythicmetals.registry.RegisterRecipeSerializers;

public class DrillUpgradeSmithingRecipe implements SmithingRecipe {
    final Ingredient base;
    final Ingredient addition;
    final Ingredient template;
    final ItemStack result;
    final Identifier id;

    public DrillUpgradeSmithingRecipe(Ingredient base, Ingredient addition, Ingredient template, ItemStack result, Identifier id) {
        this.base = base;
        this.addition = addition;
        this.template = template;
        this.result = result;
        this.id = id;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (this.base.test(inventory.getStack(0)) && this.addition.test(inventory.getStack(1))) {
            var drill = inventory.getStack(0);

            // If both upgrade slots are filled, you are not able to upgrade
            if (drill.has(MythrilDrill.UPGRADE_SLOT_ONE) && drill.has(MythrilDrill.UPGRADE_SLOT_TWO)) return false;

            // Return if upgrade is already on drill, as they are unique
            if (MythrilDrill.hasUpgradeItem(drill, inventory.getStack(1).getItem())) return false;

            // If any slot is empty, you can upgrade the drill
            return !drill.has(MythrilDrill.UPGRADE_SLOT_ONE) || !drill.has(MythrilDrill.UPGRADE_SLOT_TWO);

        }

        return false;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        var drillStack = this.result.copy();
        var upgradeItem = inventory.getStack(1).getItem();

        if (drillStack.getItem().equals(MythicTools.MYTHRIL_DRILL)) {
            if (!drillStack.has(MythrilDrill.UPGRADE_SLOT_ONE)) {
                drillStack.put(MythrilDrill.UPGRADE_SLOT_ONE, upgradeItem);
            }
            else if (!drillStack.has(MythrilDrill.UPGRADE_SLOT_TWO)) {
                drillStack.put(MythrilDrill.UPGRADE_SLOT_TWO, upgradeItem);
            }
        }

        return drillStack;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisterRecipeSerializers.MYTHRIL_DRILL_SMITHING_RECIPE;
    }

    @Override
    public boolean testTemplate(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean testBase(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean testAddition(ItemStack stack) {
        return this.addition.test(stack);
    }

    public static class Serializer implements RecipeSerializer<DrillUpgradeSmithingRecipe> {
            public DrillUpgradeSmithingRecipe read(Identifier identifier, JsonObject jsonObject) {
                Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
                Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
                Ingredient ingredient3 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
                ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
                return new DrillUpgradeSmithingRecipe(ingredient, ingredient2, ingredient3, itemStack, identifier);
            }

            public DrillUpgradeSmithingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
                Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
                Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
                Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
                ItemStack itemStack = packetByteBuf.readItemStack();
                return new DrillUpgradeSmithingRecipe(ingredient, ingredient2, ingredient3, itemStack, identifier);
            }

            public void write(PacketByteBuf packetByteBuf, DrillUpgradeSmithingRecipe smithingRecipe) {
                smithingRecipe.base.write(packetByteBuf);
                smithingRecipe.addition.write(packetByteBuf);
                packetByteBuf.writeItemStack(smithingRecipe.result);
            }
    }
}
