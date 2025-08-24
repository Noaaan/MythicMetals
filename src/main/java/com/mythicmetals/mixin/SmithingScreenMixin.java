package com.mythicmetals.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.screen.ingame.SmithingScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.ArrayList;
import java.util.List;

@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ForgingScreen<SmithingScreenHandler> {

    @Shadow
    @Final
    @Mutable
    private static List<Identifier> EMPTY_SLOT_TEXTURES;

    public SmithingScreenMixin(SmithingScreenHandler handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title, texture);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void mythicmetals$attachMoreTextures(CallbackInfo ci) {
        var list = new ArrayList<>(EMPTY_SLOT_TEXTURES);
        list.add(RegistryHelper.id("item/template/empty_slot_midas_template"));
        list.add(RegistryHelper.id("item/template/empty_slot_crafted_template"));
        EMPTY_SLOT_TEXTURES = list;
    }

    /**
     * Modifies the return value of the Smithing Screen Handler to show the invalid arrow for Mythril Drill Upgrade Recipes.
     * These usually do not have a smithing template, and therefore need an extra check to show the arrow
     * <br>
     * EXPERIMENTAL
     *
     * @param original the check on whether all three slots are filled
     */
    @ModifyReturnValue(method = "hasInvalidRecipe", at = @At("RETURN"))
    private boolean mythicmetals$complainAboutShortUpgradeRecipes(boolean original) {
        if (this.handler.getSlot(1).getStack().getItem().equals(MythicTools.MYTHRIL_DRILL)) {
            return this.handler.getSlot(1).hasStack() && this.handler.getSlot(2).hasStack() && !this.handler.getSlot(3).hasStack();
        }
        return original;
    }
}
