package com.mythicmetals.mixin;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.misc.MythicLootOps;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinBrainMixin {

    @Unique
    private static ItemStack mythicmetals$cachedBarterItem;

    @Inject(method = "isBarterCurrency", at = @At("HEAD"), cancellable = true)
    private static void acceptMidasGold(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.is(MythicMaterials.MIDAS_GOLD.baseMaterial())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "stopHoldingOffHandItem", at = @At("HEAD"))
    private static void mythicmetals$grabBarteredItem(ServerLevel level, Piglin body, boolean barteringEnabled, CallbackInfo ci) {
        mythicmetals$cachedBarterItem = body.getOffhandItem();
    }

    @ModifyVariable(method = "getBarterResponseItems", at = @At(value = "LOAD"))
    private static LootTable giveLootForMidasGold(LootTable lootTable, Piglin body) {
        var level = body.level();
        if (mythicmetals$cachedBarterItem.is(MythicMaterials.MIDAS_GOLD.baseMaterial()) && level.getServer() != null) {
            return level.getServer().reloadableRegistries().getLootTable(ResourceKey.create(Registries.LOOT_TABLE, MythicLootOps.BETTER_PIGLIN_BARTERING));
        }
        return lootTable;
    }

}
