package nourl.mythicmetals.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.MagmaBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetals.armor.MythicArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MagmaBlock.class, priority = 1100)
public class MagmaBlockMixin {

    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    private void cancelBurnWithPalladiumBoots(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        for (ItemStack armorItems : entity.getArmorItems()) {
            if (armorItems.getItem().equals(MythicArmor.PALLADIUM.getBoots())) {
                ci.cancel();
                return;
            }
        }
    }
}
