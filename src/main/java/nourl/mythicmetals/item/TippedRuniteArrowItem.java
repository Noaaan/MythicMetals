package nourl.mythicmetals.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TippedArrowItem;
import net.minecraft.world.World;
import nourl.mythicmetals.entity.RuniteArrowEntity;

public class TippedRuniteArrowItem extends TippedArrowItem {

    public TippedRuniteArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        var arrow = new RuniteArrowEntity(shooter, world);
        arrow.initFromStack(stack);
        return arrow;
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey(stack);
    }
}
