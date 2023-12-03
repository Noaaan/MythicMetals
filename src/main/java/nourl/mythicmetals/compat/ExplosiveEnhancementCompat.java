package nourl.mythicmetals.compat;

import net.minecraft.world.World;
import net.superkat.explosiveenhancement.api.ExplosiveApi;

public class ExplosiveEnhancementCompat {
    public static void spawnParticles(World world, double x, double y, double z, float power) {
        ExplosiveApi.spawnParticles(world, x, y, z, power, false, true, true);
    }
}
