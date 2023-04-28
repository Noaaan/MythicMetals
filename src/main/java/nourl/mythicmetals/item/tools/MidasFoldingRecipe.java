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
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.RegisterRecipeSerializers;

public class MidasFoldingRecipe implements SmithingRecipe {
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    final Ingredient template;
    final Identifier id;

    public MidasFoldingRecipe(Ingredient base, Ingredient addition, Ingredient template, ItemStack result, Identifier id) {
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.template = template;
        this.id = id;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2))) {
            var stack = inventory.getStack(1);

            int goldCount = stack.get(MidasGoldSword.GOLD_FOLDED);

            if (inventory.getStack(0).getItem().equals(MythicItems.Templates.ROYAL_MIDAS_SMITHING_TEMPLATE)) {
                return goldCount >= 640;
            }

            if (stack.getItem().equals(MythicTools.ROYAL_MIDAS_GOLD_SWORD)) {
                return goldCount >= 640 && goldCount < 10000;
            }

            return goldCount < 640;
        }

        return false;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        var itemStack = inventory.getStack(1).copy();

        // Gilded Midas Gold Sword handler
        if (itemStack.getItem().equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {
            int goldCount = itemStack.get(MidasGoldSword.GOLD_FOLDED);
            // Allow you to max out a Gilded Midas Sword
            if (goldCount < 640) {
                itemStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }

            // Transform into Royal Midas Gold Sword
            if (goldCount >= 640) {
                var swordnite = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
                itemStack.put(MidasGoldSword.IS_ROYAL, true);
                swordnite.setNbt(itemStack.getNbt());
                return swordnite;
            }
        }


        // Handle Midas Gold Sword, transform if you fold and it at least has 320 gold on it
        if (itemStack.getItem().equals(MythicTools.MIDAS_GOLD_SWORD)) {

            int goldCount = itemStack.get(MidasGoldSword.GOLD_FOLDED);

            if (goldCount < 640) {
                itemStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);

            }

            // Transform Midas Gold Sword into Gilded Midas Gold Sword
            if (goldCount >= 319) {
                var swordnite = new ItemStack(MythicTools.GILDED_MIDAS_GOLD_SWORD);
                itemStack.put(MidasGoldSword.IS_GILDED, true);
                swordnite.setNbt(itemStack.getNbt());
                return swordnite;
            }
        }

        // Handle Royal Midas Gold Sword
        if (itemStack.getItem().equals(MythicTools.ROYAL_MIDAS_GOLD_SWORD)) {
            int goldCount = itemStack.get(MidasGoldSword.GOLD_FOLDED);
            if (goldCount < 10000) {
                itemStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }
        }

        return itemStack;
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
        return RegisterRecipeSerializers.MIDAS_FOLDING_RECIPE;
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

    public static class Serializer implements RecipeSerializer<MidasFoldingRecipe> {
            public MidasFoldingRecipe read(Identifier identifier, JsonObject jsonObject) {
                Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
                Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
                Ingredient ingredient3 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "template"));
                ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
                return new MidasFoldingRecipe(ingredient, ingredient2, ingredient3, itemStack, identifier);
            }

            public MidasFoldingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
                Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
                Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
                Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
                ItemStack itemStack = packetByteBuf.readItemStack();
                return new MidasFoldingRecipe(ingredient, ingredient2, ingredient3, itemStack, identifier);
            }

            public void write(PacketByteBuf packetByteBuf, MidasFoldingRecipe smithingRecipe) {
                smithingRecipe.base.write(packetByteBuf);
                smithingRecipe.addition.write(packetByteBuf);
                smithingRecipe.template.write(packetByteBuf);
                packetByteBuf.writeItemStack(smithingRecipe.result);
            }
    }
}
