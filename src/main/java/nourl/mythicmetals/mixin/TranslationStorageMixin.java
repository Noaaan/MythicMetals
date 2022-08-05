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
        if(Calendar.getInstance().get(Calendar.MONTH) != Calendar.APRIL) return;
        if(MythicMetals.CONFIG.disableFunny) return;

        var builder = new HashMap<>(translations);
        builder.put("item.mythicmetals.adamantite_ingot", "Suspicious Ingot");
        builder.put("item.mythicmetals.aquarium_ingot", "Fish Tank");
        builder.put("item.mythicmetals.durasteel_ingot", "Dura-Chan");
        builder.put("item.mythicmetals.banglum_chunk", "Windy Made This");
        builder.put("item.mythicmetals.carmot_ingot", "Jello");
        builder.put("item.mythicmetals.morkite", "Jello");
        builder.put("item.mythicmetals.midas_gold_ingot", "Butter");
        builder.put("item.mythicmetals.osmium_ingot", "Glisconium");
        builder.put("item.mythicmetals.raw_stormyx", "Bubble Gum");
        builder.put("item.mythicmetals.runite_ingot", "99 Smithing Bar");
        builder.put("item.mythicmetals.stormyx_ingot", "Candy Bar");
        builder.put("item.mythicmetals.unobtainium", "Obamium");

        this.translations = builder;

    }

}
