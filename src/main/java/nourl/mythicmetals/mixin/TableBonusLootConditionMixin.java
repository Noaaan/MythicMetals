package nourl.mythicmetals.mixin;

import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import nourl.mythicmetals.abilities.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Mixin(TableBonusLootCondition.class)
public class TableBonusLootConditionMixin {
    @ModifyVariable(method = "test(Lnet/minecraft/loot/context/LootContext;)Z",
            at = @At(value = "LOAD"))
    private int mythicmetals$increaseFortuneOnLeavesAndGravel(int level, LootContext lootCtx) {
        if (Abilities.BONUS_FORTUNE.getItems().contains(Objects.requireNonNull(lootCtx.get(LootContextParameters.TOOL)).getItem())) {
            return (level + Abilities.BONUS_FORTUNE.getLevel());
        }
        return level;
    }
}
