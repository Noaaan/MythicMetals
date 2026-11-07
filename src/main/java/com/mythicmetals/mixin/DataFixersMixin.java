package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.DataFixerBuilder;
import com.mojang.datafixers.schemas.Schema;
import com.mythicmetals.data.MidasGoldComponentDataFix;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.filefix.FileFixerUpper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DataFixers.class)
public abstract class DataFixersMixin {

    @Inject(method = "addFixers", at = @At("TAIL"))
    private static void mythicmetals$addDataFixers(DataFixerBuilder fixerUpper, FileFixerUpper.Builder fileFixerUpper, CallbackInfo ci,  @Local(name = "v4772") Schema v4772, @Local(name = "v4773") Schema v4773) {
        fixerUpper.addFixer(new MidasGoldComponentDataFix(v4772, "mythic metals midas gold component fix"));
    }
}
