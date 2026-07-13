package nourl.mythicmetals.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.tools.MythrilDrill;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TableBonusLootCondition.class)
public class TableBonusLootConditionMixin {
    
    @Shadow @Final Enchantment enchantment;
    
    @ModifyVariable(method = "test(Lnet/minecraft/loot/context/LootContext;)Z",
            at = @At(value = "LOAD"))
    private int mythicmetals$increaseFortune(int level, LootContext lootCtx) {
        if (MythicMetals.CONFIG.disableAbilities()) return level;
        var toolCtxStack = lootCtx.get(LootContextParameters.TOOL);
        if (toolCtxStack == null) {
            return level;
        }
        // only modify when the loot table bonus loot checks for fortune
        if(this.enchantment != Enchantments.FORTUNE) {
            return level;
        }
        
        if (Abilities.BONUS_FORTUNE.getItems().contains(toolCtxStack.getItem())) {
            return (level + Abilities.BONUS_FORTUNE.getLevel());
        }
        if (MythrilDrill.hasUpgradeItem(toolCtxStack, MythicItems.Mats.CARMOT_STONE)) {
            return level + 1;
        }
        return level;
    }
}
