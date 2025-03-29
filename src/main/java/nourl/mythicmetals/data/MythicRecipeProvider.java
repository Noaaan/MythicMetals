package nourl.mythicmetals.data;

import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import nourl.mythicmetals.armor.ArmorSet;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.block.BlockSet;
import nourl.mythicmetals.block.MythicBlocks;
import nourl.mythicmetals.conditions.DustLoadedCondition;
import nourl.mythicmetals.conditions.NuggetsLoadedCondition;
import nourl.mythicmetals.item.ItemSet;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.misc.RegistryHelper;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class MythicRecipeProvider extends FabricRecipeProvider {

    public MythicRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void generate(RecipeExporter exporter) {
        var itemSets = new HashMap<String, ItemSet>();
        var blockSets = new HashMap<String, BlockSet>();

        var dustExporter = withConditions(exporter, new DustLoadedCondition());
        var nuggetExporter = withConditions(exporter, new NuggetsLoadedCondition());

        // Handle items first, as they store whether the items need blasting to be smelted
        ReflectionUtils.iterateAccessibleStaticFields(MythicItems.class, ItemSet.class, (itemSet, name, field) -> {
            itemSets.put(name, itemSet);
        });

        ReflectionUtils.iterateAccessibleStaticFields(MythicBlocks.class, BlockSet.class, (blockSet, name, field) -> {
            blockSets.put(name, blockSet);
        });

        // Smelting armor into nuggets
        ReflectionUtils.iterateAccessibleStaticFields(MythicArmor.class, ArmorSet.class, (armorSet, name, field) -> {
            if (itemSets.containsKey(name) && itemSets.get(name).getNugget() != null) {
                var itemSet = itemSets.get(name);
                boolean requiresBlasting = itemSet.requiresBlasting();
                var nugget = itemSet.getNugget();
                ItemConvertible[] armorItems = new ItemConvertible[0];
                armorItems = armorSet.getArmorItems().toArray(armorItems);

                if (!requiresBlasting) {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(armorItems), RecipeCategory.MISC, nugget, 0.1f, 200)
                        .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("nuggets/" + name))))
                        .offerTo(nuggetExporter, RegistryHelper.id("smelting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_armor"));
                }
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(armorItems), RecipeCategory.MISC, nugget, 0.1f, 100)
                    .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("nuggets/" + name))))
                    .offerTo(nuggetExporter, RegistryHelper.id("blasting/" + name.toLowerCase(Locale.ROOT) + "_nugget_from_armor"));
            }
        });

        // Smelting ore blocks into ingots
        itemSets.forEach((name, itemSet) -> {
            // Smelting Ore Blocks into ingots
            if (blockSets.containsKey(name) && blockSets.get(name).getOre() != null) {
                var blockSet = blockSets.get(name);
                var oreList = new ArrayList<>(blockSet.getOreVariants());
                oreList.add(blockSet.getOre());
                var items = oreList.stream().map(Block::asItem).toList().toArray(new Item[0]);

                var ingot = itemSet.getIngot();
                var xp = itemSet.getXp();
                boolean requiresBlasting = itemSet.requiresBlasting();
                var critera = conditionsFromItemPredicates(ItemPredicate.Builder.create().items(items).build());

                if (!requiresBlasting) {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(items), RecipeCategory.MISC, ingot, xp, 200)
                        .criterion("has_material", critera)
                        .offerTo(exporter, RegistryHelper.id("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_ores"));
                }
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(items), RecipeCategory.MISC, ingot, xp, 100)
                    .criterion("has_material", critera)
                    .offerTo(exporter, RegistryHelper.id("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_ores"));
            }

            // Smelting Raw Ores into ingots
            if (itemSet.getRawOre() != null) {
                if (!itemSet.requiresBlasting()) {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(itemSet.getRawOre()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 200)
                        .criterion("has_material", conditionsFromItem(itemSet.getRawOre()))
                        .offerTo(exporter, RegistryHelper.id("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_raw_ore"));
                }
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(itemSet.getRawOre()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 100)
                    .criterion("has_material", conditionsFromItem(itemSet.getRawOre()))
                    .offerTo(exporter, RegistryHelper.id("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_raw_ore"));
            }
            // Smelting dusts into ingots
            if (itemSet.getDust() != null) {
                if (!itemSet.requiresBlasting()) {
                    CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(itemSet.getDust()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 200)
                        .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("dusts/" + name))))
                        .offerTo(dustExporter, RegistryHelper.id("smelting/" + name.toLowerCase(Locale.ROOT) + "_from_dust"));
                }
                CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(itemSet.getDust()), RecipeCategory.MISC, itemSet.getIngot(), itemSet.getXp(), 100)
                    .criterion("has_material", conditionsFromTag(TagKey.of(RegistryKeys.ITEM, RegistryHelper.id("dusts/" + name))))
                    .offerTo(dustExporter, RegistryHelper.id("blasting/" + name.toLowerCase(Locale.ROOT) + "_from_dust"));
            }
        });


    }
}
