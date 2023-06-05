package nourl.mythicmetals.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.armor.MythicArmorMaterials;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.misc.RegistryHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    @Unique
    private static ItemStack mythicmetals$cachedBarterItem;

    private static final Identifier mythicmetals$BETTER_PIGLIN_BARTERING = RegistryHelper.id("gameplay/better_piglin_bartering");

    @Inject(method = "acceptsForBarter", at = @At("HEAD"), cancellable = true)
    private static void acceptMidasGold(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(MythicItems.MIDAS_GOLD.getIngot())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
    private static void checkForMidasGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        for(ItemStack itemStack : entity.getArmorItems()) {
            Item item = itemStack.getItem();
            if (item instanceof ArmorItem armorItem && armorItem.getMaterial() == MythicArmorMaterials.MIDAS_GOLD) {
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "consumeOffHandItem", at = @At("HEAD"))
    private static void mythicmetals$grabBarteredItem(PiglinEntity piglin, boolean barter, CallbackInfo ci) {
        mythicmetals$cachedBarterItem = piglin.getOffHandStack();
    }

    @ModifyVariable(method = "getBarteredItem", at = @At(value = "LOAD"))
    private static LootTable giveLootForMidasGold(LootTable table, PiglinEntity piglin) {
        if (mythicmetals$cachedBarterItem.isOf(MythicItems.MIDAS_GOLD.getIngot()) && piglin.getWorld().getServer() != null) {
            return piglin.getWorld().getServer().getLootManager().getLootTable(mythicmetals$BETTER_PIGLIN_BARTERING);
        }
        return table;
    }

}
