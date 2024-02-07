package nourl.mythicmetals.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;
import nourl.mythicmetals.armor.TidesingerArmor;
import nourl.mythicmetals.registry.RegisterRecipeSerializers;

public class TidesingerCoralRecipe implements SmithingRecipe {
    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;
    public final Ingredient template;

    public TidesingerCoralRecipe(Ingredient base, Ingredient addition, Ingredient template, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.template = template;
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
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegisterRecipeSerializers.TIDESINGER_CORAL_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<TidesingerCoralRecipe> {
        private static final Codec<TidesingerCoralRecipe> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
                                ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, TidesingerCoralRecipe::new)
        );

        @Override
        public Codec<TidesingerCoralRecipe> codec() {
            return CODEC;
        }

        @Override
        public TidesingerCoralRecipe read(PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            Ingredient ingredient2 = Ingredient.fromPacket(buf);
            Ingredient ingredient3 = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            return new TidesingerCoralRecipe(ingredient, ingredient2, ingredient3, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, TidesingerCoralRecipe smithingRecipe) {
            smithingRecipe.base.write(packetByteBuf);
            smithingRecipe.addition.write(packetByteBuf);
            smithingRecipe.template.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingRecipe.result);
        }
    }
}
