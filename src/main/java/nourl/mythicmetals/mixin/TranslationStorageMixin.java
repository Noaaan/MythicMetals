package nourl.mythicmetals.mixin;

import net.minecraft.client.resource.language.TranslationStorage;
import nourl.mythicmetals.MythicMetals;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Calendar;
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
        if(!(Calendar.getInstance().get(Calendar.MONTH) == Calendar.APRIL && Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) == 1)) return;
        if(MythicMetals.CONFIG.disableFunny()) return;

        var builder = new HashMap<>(translations);
        builder.put("item.mythicmetals.aquarium_ingot", "Fish Tank");
        builder.put("item.mythicmetals.durasteel_ingot", "Dura-Chan");
        builder.put("item.mythicmetals.banglum_chunk", "Windy Made This");
        builder.put("item.mythicmetals.banglum_ingot", "Shaped Sweet Potato");
        builder.put("item.mythicmetals.banglum_nugget", "Tiny Potato");
        builder.put("item.mythicmetals.carmot_ingot", "Jello");
        builder.put("block.mythicmetals.carmot_ore", "Wall Jello");
        builder.put("block.mythicmetals.deepslate_carmot_ore", "Deep Rock Wall Jello");
        builder.put("block.mythicmetals.deepslate_unobtainium_ore", "Deep Rock Obtainium Ore");
        builder.put("block.mythicmetals.deepslate_adamantite_ore", "Deep Rock Adamantite Ore");
        builder.put("block.mythicmetals.deepslate_morkite_ore", "ROCK AND STONE YEAAAAAAH");
        builder.put("block.mythicmetals.deepslate_mythril_ore", "Deep Rock Mythril Ore");
        builder.put("block.mythicmetals.deepslate_orichalcum_ore", "Deep Rock Orichalcum Ore");
        builder.put("block.mythicmetals.deepslate_prometheum_ore", "Deep Rock Prometheum Ore");
        builder.put("block.mythicmetals.deepslate_runite_ore", "Deep Rock 90 Mining Stone");
        builder.put("block.mythicmetals.runite_ore", "90 Mining Rock");
        builder.put("item.mythicmetals.morkite", "MORKIIIIITE");
        builder.put("item.mythicmetals.midas_gold_ingot", "Butter");
        builder.put("item.mythicmetals.midas_gold_sword", "Butter Sword");
        builder.put("item.mythicmetals.gilded_midas_gold", "Royal Butter Sword");
        builder.put("block.mythicmetals.midas_gold_block", "Block of Butter");
        builder.put("block.mythicmetals.midas_gold_anvil", "Butter Anvil");
        builder.put("block.mythicmetals.midas_gold_ore", "Cheese In Wall");
        builder.put("block.mythicmetals.raw_midas_gold_block", "Uncooked Butter");
        builder.put("item.mythicmetals.osmium_ingot", "glisconium");
        builder.put("item.mythicmetals.raw_adamantite", "Uncooked Adamantite");
        builder.put("item.mythicmetals.raw_aquarium", "Uncooked Fish Tank");
        builder.put("item.mythicmetals.raw_banglum", "Uncooked Sweet Potatoes");
        builder.put("item.mythicmetals.raw_carmot", "Uncooked Gelatin");
        builder.put("item.mythicmetals.raw_kyber", "Uncooked Kyber");
        builder.put("item.mythicmetals.raw_manganese", "Uncooked Mayonnaise");
        builder.put("item.mythicmetals.manganese_ingot", "Mayonnaise Ingot");
        builder.put("block.mythicmetals.manganese_block", "Block of Mayonnaise");
        builder.put("block.mythicmetals.manganese_anvil", "Mayonnaise Anvil");
        builder.put("item.mythicmetals.raw_midas_gold", "Uncooked Butter");
        builder.put("item.mythicmetals.raw_mythril", "Uncooked Mythril");
        builder.put("item.mythicmetals.raw_orichalcum", "Uncooked Orichalcum");
        builder.put("item.mythicmetals.raw_osmium", "Uncooked glisconium");
        builder.put("item.mythicmetals.raw_palladium", "Uncooked Palladium");
        builder.put("item.mythicmetals.raw_platinum", "Uncooked Platinum");
        builder.put("item.mythicmetals.raw_prometheum", "Uncooked Prometheum");
        builder.put("item.mythicmetals.raw_quadrillum", "Uncooked Quadrillum");
        builder.put("item.mythicmetals.raw_runite", "90 Smithing Ore");
        builder.put("item.mythicmetals.raw_stormyx", "Bubble Gum");
        builder.put("item.mythicmetals.raw_silver", "Bubble Gum");
        builder.put("item.mythicmetals.raw_tin", "Unmelted Tin Can");
        builder.put("item.mythicmetals.runite_ingot", "99 Smithing Bar");
        builder.put("item.mythicmetals.stormyx_ingot", "Candy Bar");
        builder.put("block.mythicmetals.stormyx_block", "Compressed Candy");
        builder.put("block.mythicmetals.stormyx_anvil", "Incredible Amazing Rainbow Candy Anvil");
        builder.put("block.mythicmetals.raw_stormyx_block", "Candy Block");
        builder.put("block.mythicmetals.unobtainium_ore", "Obtainium Ore");
        builder.put("block.mythicmetals.unobtainium_block", "Obtainium Block");
        builder.put("item.mythicmetals.unobtainium", "Obtainium");
        builder.put("block.mythicmetals.banglum_nuke_core", "Banglum Nether Reactor Core");
        builder.put("block.mythicmetals.carmot_nuke_core", "Carmot Nether Reactor Core");
        builder.put("block.mythicmetals.quadrillum_nuke_core", "Quadrillum Nether Reactor Core");
        builder.put("item.mythicmetals.adamantite_nugget", "Adamantite Nuggie");
        builder.put("item.mythicmetals.aquarium_nugget", "Fish Tank Corner");
        builder.put("item.mythicmetals.bronze_nugget", "Bronze Nuggie");
        builder.put("item.mythicmetals.carmot_nugget", "Jello Nuggie");
        builder.put("item.mythicmetals.celestium_nugget", "Celestium Nuggie");
        builder.put("item.mythicmetals.copper_nugget", "Copper Nuggie");
        builder.put("item.mythicmetals.durasteel_nugget", "Dura-Chan Nuggie");
        builder.put("item.mythicmetals.hallowed_nugget", "Hallowed Nuggie");
        builder.put("item.mythicmetals.kyber_nugget", "Kyber Nuggie");
        builder.put("item.mythicmetals.manganese_nugget", "Mayonnaise Nuggie");
        builder.put("item.mythicmetals.metallurgium_nugget", "Metallurgium Nuggie");
        builder.put("item.mythicmetals.midas_gold_nugget", "Pat Of Butter");
        builder.put("item.mythicmetals.mythril_nugget", "Mythril Nuggie");
        builder.put("item.mythicmetals.orichalcum_nugget", "Orichalcum Nuggie");
        builder.put("item.mythicmetals.osmium_nugget", "glisconium Nuggie");
        builder.put("item.mythicmetals.palladium_nugget", "Palladium Nuggie");
        builder.put("item.mythicmetals.platinum_nugget", "Platinum Nuggie");
        builder.put("item.mythicmetals.prometheum_nugget", "Prometheum Nuggie");
        builder.put("item.mythicmetals.quadrillum_nugget", "X Nuggie");
        builder.put("item.mythicmetals.runite_nugget", "99/9 Smithing Nuggie");
        builder.put("item.mythicmetals.silver_nugget", "Silver Nuggie");
        builder.put("item.mythicmetals.star_platinum_nugget", "Star Platinum Nuggie");
        builder.put("item.mythicmetals.steel_nugget", "Steel Nuggie");
        builder.put("item.mythicmetals.stormyx_nugget", "Mentos");
        builder.put("item.mythicmetals.tin_nugget", "Tin Can Shard");

        this.translations = builder;

    }

}
