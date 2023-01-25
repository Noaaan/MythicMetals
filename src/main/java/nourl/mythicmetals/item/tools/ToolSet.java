package nourl.mythicmetals.item.tools;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;

import java.util.function.Consumer;

public class ToolSet {

    private final SwordItem sword;
    private final AxeItem axe;
    private final PickaxeItem pickaxe;
    private final ShovelItem shovel;
    private final HoeItem hoe;

    private static Item.Settings createSettings(Consumer<Item.Settings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(2);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ToolSet(ToolMaterial material, int[] damage, float[] speed) {
        this(material, damage, speed, settings -> {});
    }

    public ToolSet(ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        this.sword = this.makeSword(material, damage[0], speed[0], createSettings(settingsProcessor));
        this.axe = this.makeAxe(material, damage[1], speed[1], createSettings(settingsProcessor));
        this.pickaxe = this.makePickaxe(material, damage[2], speed[2], createSettings(settingsProcessor));
        this.shovel = this.makeShovel(material, damage[3], speed[3], createSettings(settingsProcessor));
        this.hoe = this.makeHoe(material, damage[4], speed[4], createSettings(settingsProcessor));
    }

    public void register(String name) {
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_sword"), sword);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_axe"), axe);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_pickaxe"), pickaxe);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_shovel"), shovel);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_hoe"), hoe);
    }

    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new SwordItem(material, damage, speed, settings);
    }

    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new AxeItem(material, damage, speed, settings);
    }

    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new PickaxeItem(material, damage, speed, settings);
    }

    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new ShovelItem(material, damage, speed, settings);
    }

    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings) {
        return new HoeItem(material, damage, speed, settings);
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
