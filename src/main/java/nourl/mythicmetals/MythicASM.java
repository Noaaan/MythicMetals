package nourl.mythicmetals;

import me.shedaniel.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;


public class MythicASM implements Runnable {

    @Override
    public void run() {
        // A little bit of ASM so that we can bring more explosives to the game
        var mapping = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_1688$class_1689");
        ClassTinkerers.enumBuilder(mapping).addEnum("BANGLUM_TNT").build();

    }
}
