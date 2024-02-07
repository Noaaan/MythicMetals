package nourl.mythicmetals.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MidasGoldSword;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.registry.RegisterRecipeSerializers;

public class MidasFoldingRecipe implements SmithingRecipe {
    public final Ingredient base;
    public final Ingredient addition;
    public final ItemStack result;
    public final Ingredient template;

    public MidasFoldingRecipe(Ingredient base, Ingredient addition, Ingredient template, ItemStack result) {
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.template = template;
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
        var swordInputStack = inventory.getStack(1).copy();

        // Gilded Midas Gold Sword handler
        if (swordInputStack.getItem().equals(MythicTools.GILDED_MIDAS_GOLD_SWORD)) {
            int goldCount = swordInputStack.get(MidasGoldSword.GOLD_FOLDED);
            // Allow you to max out a Gilded Midas Sword
            if (goldCount < 640) {
                swordInputStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }

            // Transform into Royal Midas Gold Sword
            if (goldCount >= 640) {
                var swordnite = new ItemStack(MythicTools.ROYAL_MIDAS_GOLD_SWORD);
                swordInputStack.put(MidasGoldSword.IS_ROYAL, true);
                swordnite.setNbt(swordInputStack.getNbt());
                return swordnite;
            }
        }

        // Handle Midas Gold Sword, transform if you fold and it at least has 320 gold on it
        if (swordInputStack.getItem().equals(MythicTools.MIDAS_GOLD_SWORD)) {

            int goldCount = swordInputStack.get(MidasGoldSword.GOLD_FOLDED);

            if (goldCount < 640) {
                swordInputStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }

            // Transform Midas Gold Sword into Gilded Midas Gold Sword
            if (goldCount >= 319) {
                var swordnite = new ItemStack(MythicTools.GILDED_MIDAS_GOLD_SWORD);
                swordInputStack.put(MidasGoldSword.IS_GILDED, true);
                swordnite.setNbt(swordInputStack.getNbt());
                return swordnite;
            }
        }

        // Handle Royal Midas Gold Sword
        if (swordInputStack.getItem().equals(MythicTools.ROYAL_MIDAS_GOLD_SWORD)) {
            int goldCount = swordInputStack.get(MidasGoldSword.GOLD_FOLDED);
            if (goldCount < 10000) {
                swordInputStack.put(MidasGoldSword.GOLD_FOLDED, goldCount + 1);
            }
        }

        return swordInputStack;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return this.result;
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
        private static final Codec<MidasFoldingRecipe> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("base").forGetter(recipe -> recipe.base),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("addition").forGetter(recipe -> recipe.addition),
                                Ingredient.ALLOW_EMPTY_CODEC.fieldOf("template").forGetter(recipe -> recipe.template),
                                ItemStack.RECIPE_RESULT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result)
                        )
                        .apply(instance, MidasFoldingRecipe::new)
        );

        @Override
        public Codec<MidasFoldingRecipe> codec() {
            return CODEC;
        }

        @Override
        public MidasFoldingRecipe read(PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            Ingredient ingredient2 = Ingredient.fromPacket(buf);
            Ingredient ingredient3 = Ingredient.fromPacket(buf);
            ItemStack itemStack = buf.readItemStack();
            return new MidasFoldingRecipe(ingredient, ingredient2, ingredient3, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, MidasFoldingRecipe smithingRecipe) {
            smithingRecipe.base.write(packetByteBuf);
            smithingRecipe.addition.write(packetByteBuf);
            smithingRecipe.template.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingRecipe.result);
        }
    }
}
