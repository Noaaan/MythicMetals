package nourl.mythicmetals.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.registry.entry.RegistryEntry;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.tools.MythrilDrill;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import java.util.Objects;

@Mixin(TableBonusLootCondition.class)
public class TableBonusLootConditionMixin {

    @Shadow
    @Final
    private RegistryEntry<Enchantment> enchantment;

    @ModifyVariable(method = "test(Lnet/minecraft/loot/context/LootContext;)Z",
            at = @At(value = "LOAD"))
    private int mythicmetals$increaseFortune(int level, LootContext lootCtx) {
        // only modify when the loot table bonus loot checks for fortune
        if (this.enchantment.value() != Enchantments.FORTUNE) {
            return level;
        }

        if (Abilities.BONUS_FORTUNE.getItems().contains(Objects.requireNonNull(lootCtx.get(LootContextParameters.TOOL)).getItem())) {
            return (level + Abilities.BONUS_FORTUNE.getLevel());
        }
        if (MythrilDrill.hasUpgradeItem(Objects.requireNonNull(lootCtx.get(LootContextParameters.TOOL)), MythicBlocks.CARMOT.getStorageBlock().asItem())) {
            return level + 1;
        }
        return level;
    }
}
