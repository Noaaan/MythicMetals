package com.mythicmetals.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mythicmetals.item.tools.MythicTools;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SmithingMenu;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.ArrayList;
import java.util.List;

@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ItemCombinerScreen<SmithingMenu> {

    @Shadow
    @Final
    @Mutable
    private static List<ResourceLocation> EMPTY_SLOT_TEXTURES;

    public SmithingScreenMixin(SmithingMenu handler, Inventory playerInventory, Component title, ResourceLocation texture) {
        super(handler, playerInventory, title, texture);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void mythicmetals$attachMoreTextures(CallbackInfo ci) {
        var list = new ArrayList<>(EMPTY_SLOT_TEXTURES);
        list.add(RegistryHelper.id("empty_slot_midas_template"));
        list.add(RegistryHelper.id("empty_slot_crafted_template"));
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
        if (this.menu.getSlot(1).getItem().getItem().equals(MythicTools.MYTHRIL_DRILL)) {
            return this.menu.getSlot(1).hasItem() && this.menu.getSlot(2).hasItem() && !this.menu.getSlot(3).hasItem();
        }
        return original;
    }
}
