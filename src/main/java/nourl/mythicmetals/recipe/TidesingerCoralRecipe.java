package nourl.mythicmetals.recipe;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;
import nourl.mythicmetals.armor.TidesingerArmor;
import nourl.mythicmetals.registry.RegisterRecipeSerializers;

public class TidesingerCoralRecipe implements SmithingRecipe {
    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;
    public final Ingredient template;
    public final Identifier id;

    public TidesingerCoralRecipe(Ingredient base, Ingredient addition, Ingredient template, ItemStack result, Identifier id) {
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.template = template;
        this.id = id;
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

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.template.test(inventory.getStack(0)) && this.base.test(inventory.getStack(1)) && this.addition.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        var armorStack = this.result.copy();
        armorStack.setNbt(inventory.getStack(1).getNbt());
        var additionStack = inventory.getStack(2);

        var path = Registries.ITEM.getId(additionStack.getItem()).getPath();

        if (path.contains("coral") && !(path.contains("block") || path.contains("dead"))) {
            armorStack.put(TidesingerArmor.CORAL_TYPE, path.split("_")[0]);
        }

        return armorStack;
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
        return RegisterRecipeSerializers.TIDESINGER_CORAL_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<TidesingerCoralRecipe> {
        public TidesingerCoralRecipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
            Ingredient ingredient3 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "template"));
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new TidesingerCoralRecipe(ingredient, ingredient2, ingredient3, itemStack, identifier);
        }

        public TidesingerCoralRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new TidesingerCoralRecipe(ingredient, ingredient2, ingredient3, itemStack, identifier);
        }

        public void write(PacketByteBuf packetByteBuf, TidesingerCoralRecipe smithingRecipe) {
            smithingRecipe.base.write(packetByteBuf);
            smithingRecipe.addition.write(packetByteBuf);
            smithingRecipe.template.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingRecipe.result);
        }
    }
}
