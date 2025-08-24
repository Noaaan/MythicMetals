package com.mythicmetals.mixin;

import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.UpgradeComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.item.MythicItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TableBonusLootCondition.class)
public class TableBonusLootConditionMixin {

    @Shadow
    @Final
    private RegistryEntry<Enchantment> enchantment;

    @ModifyVariable(
        method = "test(Lnet/minecraft/loot/context/LootContext;)Z",
        at = @At(value = "LOAD")
    )
    private int mythicmetals$increaseFortune(int level, LootContext lootCtx) {
        var toolCtxStack = lootCtx.get(LootContextParameters.TOOL);
        if (toolCtxStack == null) {
            return level;
        }

        if (!this.enchantment.matches((enchantmentRegistryKey) -> enchantmentRegistryKey.equals(Enchantments.FORTUNE))) {
            return level;
        }

        if ((toolCtxStack.isIn(MythicTags.BONUS_FORTUNE))) {
            return level + 1;
        }

        if (toolCtxStack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2)).hasUpgrade(MythicItems.Mats.CARMOT_STONE)) {
            return level + 1;
        }
        return level;
    }
}
