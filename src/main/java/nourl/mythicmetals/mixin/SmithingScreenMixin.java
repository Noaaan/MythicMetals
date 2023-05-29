package nourl.mythicmetals.mixin;

import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.misc.RegistryHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(SmithingScreen.class)
public class SmithingScreenMixin {

    @Shadow
    @Final
    @Mutable
    private static List<Identifier> EMPTY_SLOT_TEXTURES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void mythicmetals$attachMoreTextures(CallbackInfo ci) {
        var list = new ArrayList<>(EMPTY_SLOT_TEXTURES);
        list.add(RegistryHelper.id("item/template/empty_slot_midas_template"));
        EMPTY_SLOT_TEXTURES = list;
    }
}
