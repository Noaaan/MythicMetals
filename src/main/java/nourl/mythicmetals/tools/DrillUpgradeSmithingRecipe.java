package nourl.mythicmetals.tools;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class DrillUpgradeSmithingRecipe extends SmithingRecipe {
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    final Identifier id;

    public DrillUpgradeSmithingRecipe(Ingredient base, Ingredient addition, ItemStack result, Identifier id) {
        super(id, base, addition, result);
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.id = id;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        var drillStack = super.craft(inventory);
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
    public boolean matches(Inventory inventory, World world) {
        if (!super.matches(inventory, world)) return false;

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

    public static class Serializer implements RecipeSerializer<DrillUpgradeSmithingRecipe> {
            public DrillUpgradeSmithingRecipe read(Identifier identifier, JsonObject jsonObject) {
                Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
                Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
                ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
                return new DrillUpgradeSmithingRecipe(ingredient, ingredient2, itemStack, identifier);
            }

            public DrillUpgradeSmithingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
                Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
                Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
                ItemStack itemStack = packetByteBuf.readItemStack();
                return new DrillUpgradeSmithingRecipe(ingredient, ingredient2, itemStack, identifier);
            }

            public void write(PacketByteBuf packetByteBuf, DrillUpgradeSmithingRecipe smithingRecipe) {
                smithingRecipe.base.write(packetByteBuf);
                smithingRecipe.addition.write(packetByteBuf);
                packetByteBuf.writeItemStack(smithingRecipe.result);
            }
    }
}
