package nourl.mythicmetals.item;

import io.wispforest.owo.particles.systems.ParticleSystem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ParticleStick extends Item {
    public static ParticleSystem<?> particle = null;

    public ParticleStick(Settings settings, ParticleSystem<?> p) {
        super(settings);
        particle = p;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        particle.spawn(world, user.getPos());
        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
