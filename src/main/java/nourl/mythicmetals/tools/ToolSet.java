package nourl.mythicmetals.tools;

import com.glisco.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.function.Consumer;

public class ToolSet {

    private final SwordItem sword;
    private final AxeItem axe;
    private final PickaxeItem pickaxe;
    private final ShovelItem shovel;
    private final HoeItem hoe;

    private static Item.Settings createSettings(Consumer<Item.Settings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.MAIN).tab(2);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ToolSet(ToolMaterial material, int[] damage, float[] speed) {
        this(material, damage, speed, settings -> {});
    }

    public ToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        this.sword = new SwordItem(material, damage[0], speed[0], createSettings(settingsProcessor));
        this.axe = new AxeBase(material, damage[1], speed[1], createSettings(settingsProcessor));
        this.pickaxe = new PickaxeBase(material, damage[2], speed[2], createSettings(settingsProcessor));
        this.shovel = new ShovelItem(material, damage[3], speed[3], createSettings(settingsProcessor));
        this.hoe = new HoeBase(material, damage[4], speed[4], createSettings(settingsProcessor));
    }

    public void register(String name) {
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_sword"), sword);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_axe"), axe);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_pickaxe"), pickaxe);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_shovel"), shovel);
        Registry.register(Registry.ITEM, RegistryHelper.id(name + "_hoe"), hoe);
    }

    public SwordItem getSword() {
        return sword;
    }

    public AxeItem getAxe() {
        return axe;
    }

    public PickaxeItem getPickaxe() {
        return pickaxe;
    }

    public ShovelItem getShovel() {
        return shovel;
    }

    public HoeItem getHoe() {
        return hoe;
    }
}
