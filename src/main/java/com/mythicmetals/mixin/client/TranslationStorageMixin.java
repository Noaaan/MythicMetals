package com.mythicmetals.mixin.client;

import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.HashMap;
import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {

    @Mutable
    @Shadow
    @Final
    private Map<String, String> translations;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addTranslations(Map<String, String> translations, boolean rightToLeft, CallbackInfo ci) {
        if (!StringUtilsAtHome.isFunnyDay()) return;
        var builder = new HashMap<>(translations);
        // probably won't change
        builder.put("item.mythicmetals.durasteel_ingot", "Dura-Chan");
        builder.put("item.mythicmetals.carmot_ingot", "Jello");
        builder.put("item.mythicmetals.morkite", "MORKIIIIITE");
        builder.put("item.mythicmetals.osmium_ingot", "glisconium");
        builder.put("item.mythicmetals.raw_carmot", "Uncooked Gelatin");
        builder.put("item.mythicmetals.runite_ingot", "99 Smithing Bar");
        builder.put("item.mythicmetals.unobtainium", "Obtainium");
        builder.put("item.mythicmetals.banglum_nugget", "Tiny Potato");
        builder.put("item.mythicmetals.raw_runite", "90 Smithing Ore");
        builder.put("block.mythicmetals.carmot_ore", "Wall Jello");
        builder.put("block.mythicmetals.deepslate_carmot_ore", "Deep Rock Wall Jello");
        builder.put("block.mythicmetals.deepslate_unobtainium_ore", "Deep Rock Obtainium Ore");
        builder.put("block.mythicmetals.deepslate_morkite_ore", "ROCK AND STONE YEAAAAAAH");
        builder.put("block.mythicmetals.deepslate_runite_ore", "Deep Rock 90 Mining Stone");
        builder.put("block.mythicmetals.runite_ore", "90 Mining Rock");
        builder.put("block.mythicmetals.banglum_nuke_core", "Banglum Nether Reactor Core");
        builder.put("block.mythicmetals.carmot_nuke_core", "Carmot Nether Reactor Core");
        builder.put("block.mythicmetals.quadrillum_nuke_core", "Quadrillum Nether Reactor Core");
        builder.put("block.mythicmetals.sponge_nuke_core", "Sponge Nether Reactor Core");
        builder.put("block.mythicmetals.unobtainium_ore", "Obtainium Ore");
        builder.put("block.mythicmetals.unobtainium_block", "Block of Obtained Obtainium");
        // just changed
        builder.put("item.mythicmetals.aquarium_pearl", "Turbo Pokeball");
        builder.put("item.mythicmetals.raw_osmium", "Two Stones");
        builder.put("item.mythicmetals.banglum_chunk", "Muddy Potato");
        builder.put("item.mythicmetals.carmot_stone", "Vampire's Delight");
        builder.put("item.mythicmetals.prometheum_bouquet", "Trumpet Rose");
        builder.put("item.mythicmetals.durasteel_engine", "Music Disc Fragment");
        builder.put("item.mythicmetals.stormyx_shell", "Primogem");
        builder.put("item.mythicmetals.palladium_minecart", "Infernal Minecart");
        builder.put("item.mythicmetals.adamantite_ingot", "Blood Pudding");
        builder.put("item.mythicmetals.aquarium_ingot", "Chair");
        builder.put("item.mythicmetals.banglum_ingot", "Moldy Bread");
        builder.put("item.mythicmetals.bronze_ingot", "Broth Ingot");
        builder.put("item.mythicmetals.celestium_ingot", "End Dipped Gum");
        builder.put("item.mythicmetals.hallowed_ingot", "Oiled Grill");
        builder.put("item.mythicmetals.kyber_ingot", "Square Grapes with Cream");
        builder.put("item.mythicmetals.metallurgium_ingot", "Shocking Brick");
        builder.put("item.mythicmetals.mythril_ingot", "Mint");
        builder.put("item.mythicmetals.orichalcum_ingot", "Eucalyptus Gum");
        builder.put("item.mythicmetals.palladium_ingot", "Spicy Butter");
        builder.put("item.mythicmetals.platinum_ingot", "Soap");
        builder.put("item.mythicmetals.prometheum_ingot", "Flower");
        builder.put("item.mythicmetals.quadrillum_ingot", "Blue Book");
        builder.put("item.mythicmetals.silver_ingot", "Refined Slab");
        builder.put("item.mythicmetals.star_platinum", "Goop Glazed Ingot");
        builder.put("item.mythicmetals.stormyx_ingot", "Alien Device");
        builder.put("item.mythicmetals.steel_ingot", "Whetstone");
        builder.put("item.mythicmetals.starrite", "Spicy Soda Mix");
        builder.put("item.mythicmetals.tin_ingot", "Angry Face");
        builder.put("item.mythicmetals.manganese_ingot", "gum");
        builder.put("item.mythicmetals.midas_gold_ingot", "Caramel");
        builder.put("item.mythicmetals.midas_gold_sword", "Caramel Sword");
        builder.put("item.mythicmetals.raw_adamantite", "Red Evil Mickey");
        builder.put("item.mythicmetals.raw_aquarium", "Blue Slime");
        builder.put("item.mythicmetals.raw_banglum", "Sock With Wings");
        builder.put("item.mythicmetals.raw_kyber", "Mushroom");
        builder.put("item.mythicmetals.raw_manganese", "Chewed Gum");
        builder.put("item.mythicmetals.raw_midas_gold", "Melted Cheese");
        builder.put("item.mythicmetals.raw_mythril", "Triple Mint");
        builder.put("item.mythicmetals.raw_orichalcum", "Uncooked Eucalyptus");
        builder.put("item.mythicmetals.raw_palladium", "Potato Chips");
        builder.put("item.mythicmetals.raw_platinum", "Used Soap");
        builder.put("item.mythicmetals.raw_prometheum", "Shard of Jade Glass");
        builder.put("item.mythicmetals.raw_quadrillum", "Death Seeds");
        builder.put("item.mythicmetals.raw_stormyx", "Secret Cola Ingredients");
        builder.put("item.mythicmetals.raw_silver", "Unrefined Slab");
        builder.put("item.mythicmetals.raw_tin", "Casper the Ghostly Tortoise");
        builder.put("item.mythicmetals.gilded_midas_gold", "Sharp Caramel Sword");
        builder.put("item.mythicmetals.royal_midas_gold", "Regal Caramel Sword");
        builder.put("block.mythicmetals.adamantite_ore", "Beryl Ore");
        builder.put("block.mythicmetals.deepslate_adamantite_ore", "Deep Rock Beryl Ore");
        builder.put("block.mythicmetals.deepslate_mythril_ore", "Deep Rock Mint Ore");
        builder.put("block.mythicmetals.deepslate_orichalcum_ore", "Deep Rock Eucalyptus Ore");
        builder.put("block.mythicmetals.prometheum_ore", "Salad Ore");
        builder.put("block.mythicmetals.deepslate_prometheum_ore", "Deep Rock Salad Ore");
        builder.put("block.mythicmetals.enchanted_midas_gold_block", "Enchanted Block of Chiseled Caramel");
        builder.put("block.mythicmetals.manganese_block", "Just a Block of Gum");
        builder.put("block.mythicmetals.starrite_block", "Spicy Soda Mix Block");
        builder.put("block.mythicmetals.manganese_anvil", "Gum Anvil");
        builder.put("block.mythicmetals.stormyx_block", "Compressed Candy");
        builder.put("block.mythicmetals.stormyx_anvil", "Incredible Amazing Rainbow Candy Anvil");
        builder.put("block.mythicmetals.raw_stormyx_block", "Block of Pink Prismarine");
        builder.put("block.mythicmetals.midas_gold_block", "Block of Caramel");
        builder.put("block.mythicmetals.midas_gold_anvil", "Caramel Anvil");
        builder.put("block.mythicmetals.midas_gold_ore", "Melted Cheese Wall");
        builder.put("block.mythicmetals.raw_midas_gold_block", "Block of Uncooked Melted Cheese?");

        this.translations = builder;

    }

}
