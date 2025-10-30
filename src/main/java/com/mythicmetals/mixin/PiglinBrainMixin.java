package com.mythicmetals.mixin;

import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.MythicLootOps;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Unique
    private static ItemStack mythicmetals$cachedBarterItem;

    @Inject(method = "acceptsForBarter", at = @At("HEAD"), cancellable = true)
    private static void acceptMidasGold(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(MythicItems.MIDAS_GOLD.getIngot())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "consumeOffHandItem", at = @At("HEAD"))
    private static void mythicmetals$grabBarteredItem(ServerWorld world, PiglinEntity piglin, boolean barter, CallbackInfo ci) {
        mythicmetals$cachedBarterItem = piglin.getOffHandStack();
    }

    @ModifyVariable(method = "getBarteredItem", at = @At(value = "LOAD"))
    private static LootTable giveLootForMidasGold(LootTable table, PiglinEntity piglin) {
        if (mythicmetals$cachedBarterItem.isOf(MythicItems.MIDAS_GOLD.getIngot()) && piglin.getWorld().getServer() != null) {
            return piglin.getWorld().getServer().getReloadableRegistries().getLootTable(RegistryKey.of(RegistryKeys.LOOT_TABLE, MythicLootOps.BETTER_PIGLIN_BARTERING));
        }
        return table;
    }

}
