package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.UpgradeComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BonusLevelTableCondition.class)
public class TableBonusLootConditionMixin {

    @Shadow
    @Final
    private Holder<Enchantment> enchantment;

    @ModifyVariable(
        method = "test(Lnet/minecraft/loot/context/LootContext;)Z",
        at = @At(value = "LOAD")
    )
    private int mythicmetals$increaseFortune(int level, LootContext lootCtx) {
        var toolCtxStack = lootCtx.getOptionalParameter(LootContextParams.TOOL);
        if (toolCtxStack == null) {
            return level;
        }

        if (!this.enchantment.is((enchantmentRegistryKey) -> enchantmentRegistryKey.equals(Enchantments.FORTUNE))) {
            return level;
        }

        if ((toolCtxStack.is(MythicTags.BONUS_FORTUNE))) {
            return level + 1;
        }

        if (toolCtxStack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2)).hasUpgrade(MythicItems.Mats.CARMOT_STONE)) {
            return level + 1;
        }
        return level;
    }
}
