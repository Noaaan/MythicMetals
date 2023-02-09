package nourl.mythicmetals.item;

import io.wispforest.owo.particles.systems.ParticleSystem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ParticleStick extends Item {
    private final ParticleSystem<?> particle;

    public ParticleStick(Settings settings, ParticleSystem<?> particle) {
        super(settings);
        this.particle = particle;
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
