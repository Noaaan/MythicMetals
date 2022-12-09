package nourl.mythicmetals.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nourl.mythicmetals.entity.RuniteArrowEntity;

public class RuniteArrowItem extends ArrowItem {

    public RuniteArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        var arrow = new RuniteArrowEntity(shooter, world);
        arrow.initFromStack(stack);
        return arrow;
    }

}
