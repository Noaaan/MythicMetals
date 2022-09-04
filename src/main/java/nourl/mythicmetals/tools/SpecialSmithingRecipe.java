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

public class SpecialSmithingRecipe extends SmithingRecipe {
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    final Identifier id;

    public SpecialSmithingRecipe(Ingredient base, Ingredient addition, ItemStack result, Identifier id) {
        super(id, base, addition, result);
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.id = id;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        var itemStack = super.craft(inventory);

        if (itemStack.getItem().equals(MythicTools.MIDAS_GOLD_SWORD)) {

            int goldCount = itemStack.get(MidasGoldSword.GOLD_FOLDED);

            if (goldCount < 640) {
                itemStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }

            if (goldCount >= 319) {
                var swordnite = new ItemStack(MythicTools.GILDED_MIDAS_GOLD_SWORD);
                swordnite.setNbt(itemStack.getNbt());
                return swordnite;
            }
        }

        if (itemStack.getItem().equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {
            int goldCount = itemStack.get(MidasGoldSword.GOLD_FOLDED);
            if (goldCount < 640) {
                itemStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }
        }

        return itemStack;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (!super.matches(inventory, world)) return false;

        if (this.base.test(inventory.getStack(0)) && this.addition.test(inventory.getStack(1))) {
            var stack = inventory.getStack(0);

            int goldCount = stack.get(MidasGoldSword.GOLD_FOLDED);
            return goldCount < 640;
        }

        return false;
    }

    public static class Serializer implements RecipeSerializer<SpecialSmithingRecipe> {
            public SpecialSmithingRecipe read(Identifier identifier, JsonObject jsonObject) {
                Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
                Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
                ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
                return new SpecialSmithingRecipe(ingredient, ingredient2, itemStack, identifier);
            }

            public SpecialSmithingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
                Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
                Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
                ItemStack itemStack = packetByteBuf.readItemStack();
                return new SpecialSmithingRecipe(ingredient, ingredient2, itemStack, identifier);
            }

            public void write(PacketByteBuf packetByteBuf, SpecialSmithingRecipe smithingRecipe) {
                smithingRecipe.base.write(packetByteBuf);
                smithingRecipe.addition.write(packetByteBuf);
                packetByteBuf.writeItemStack(smithingRecipe.result);
            }
    }
}
