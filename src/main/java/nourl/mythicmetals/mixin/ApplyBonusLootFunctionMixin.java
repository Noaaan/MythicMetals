package nourl.mythicmetals.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import nourl.mythicmetals.abilities.Abilities;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.tools.MythrilDrill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Mixin(ApplyBonusLootFunction.class)
public class ApplyBonusLootFunctionMixin {

    @ModifyVariable(method = "process",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/loot/context/LootContext;getRandom()Lnet/minecraft/util/math/random/Random;", shift = At.Shift.BEFORE),
            ordinal = 0)
    private int mythicmetals$increaseFortune(int level, ItemStack drop, LootContext lootCtx) {
        if (Abilities.BONUS_FORTUNE.getItems().contains(Objects.requireNonNull(lootCtx.get(LootContextParameters.TOOL)).getItem())) {
            return level + Abilities.BONUS_FORTUNE.getLevel();
        }
        if (MythrilDrill.hasUpgradeItem(Objects.requireNonNull(lootCtx.get(LootContextParameters.TOOL)), MythicBlocks.CARMOT.getStorageBlock().asItem())) {
            return level + 1;
        }
        return level;
    }
}
