package nourl.mythicmetals.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import nourl.mythicmetals.abilities.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Mixin(ApplyBonusLootFunction.class)
public class ApplyBonusLootFunctionMixin {

    @ModifyVariable(method = "process",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/loot/function/ApplyBonusLootFunction$Formula;getValue(Ljava/util/Random;II)I", shift = At.Shift.BEFORE),
                    ordinal = 0)
    private int mythicmetals$increaseFortune(int level, ItemStack drop, LootContext lootCtx) {
        if (Abilities.BONUS_FORTUNE.getItems().contains(Objects.requireNonNull(lootCtx.get(LootContextParameters.TOOL)).getItem())) {
            return (level + Abilities.BONUS_FORTUNE.getLevel());
        }
        return level;
    }
}
