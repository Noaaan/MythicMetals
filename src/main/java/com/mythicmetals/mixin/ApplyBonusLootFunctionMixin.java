package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.UpgradeComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ApplyBonusCount.class)
public class ApplyBonusLootFunctionMixin {

    @Shadow
    @Final
    private Holder<Enchantment> enchantment;

    @ModifyVariable(method = "run",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/storage/loot/LootContext;getRandom()Lnet/minecraft/util/RandomSource;",
            shift = At.Shift.BEFORE),
        ordinal = 0
    )
    private int mythicmetals$increaseFortune(int level, ItemStack drop, LootContext lootCtx) {
        // Only increase drops from Fortune
        if (!this.enchantment.is((enchantmentRegistryKey) -> enchantmentRegistryKey.equals(Enchantments.FORTUNE))) {
            return level;
        }

        // Return early if there is no item
        var toolCtxStack = lootCtx.getOptionalParameter(LootContextParams.TOOL);
        if (toolCtxStack == null) {
            return level;
        }

        if (toolCtxStack.is(MythicTags.BONUS_FORTUNE)) {
            return level + 1;
        }

        if (toolCtxStack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2)).hasUpgrade(MythicMaterials.CARMOT.extraItems().get(MythicResourceKeys.CARMOT_STONE))) {
            return level + 1;
        }
        return level;
    }
}
