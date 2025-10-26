package com.mythicmetals.item.tools;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.StringUtilsAtHome;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_VALUE;

public class ToolSet {

    private final String name;
    private final SwordItem sword;
    private final AxeItem axe;
    private final PickaxeItem pickaxe;
    private final ShovelItem shovel;
    private final HoeItem hoe;
    private final ToolMaterial material;

    private final List<Float> attackSpeed = new ArrayList<>();

    private static Item.Settings createSettings(String name, Consumer<Item.Settings> settingsProcessor) {
        final var key = RegistryHelper.itemKey(name);
        final var settings = new Item.Settings().registryKey(key).group(MythicMetals.TABBED_GROUP).tab(2);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed) {
        this(name, material, damage, speed, settings -> {});
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.sword = this.makeSword(material, damage[0], speed[0] - 4.0f, createSettings(name + "_sword", settingsProcessor));
        this.axe = this.makeAxe(material, damage[1], speed[1] - 4.0f, createSettings(name + "_axe", settingsProcessor));
        this.pickaxe = this.makePickaxe(material, damage[2], speed[2] - 4.0f, createSettings(name + "_pickaxe", settingsProcessor));
        this.shovel = this.makeShovel(material, damage[3], speed[3] - 4.0f, createSettings(name + "_shovel", settingsProcessor));
        this.hoe = this.makeHoe(material, damage[4], speed[4] - 4.0f, createSettings(name + "_hoe", settingsProcessor));
        attackSpeed.add(speed[4]);
        attackSpeed.add(speed[3]);
        attackSpeed.add(speed[2]);
        attackSpeed.add(speed[1]);
        attackSpeed.add(speed[0]);
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

    /**
     * Returns a set of all the ToolItems that make this toolset
     *
     * @return List of ToolItems in order: Sword, Axe, Pickaxe, Shovel, Hoe
     */
    public List<Item> get() {
        return List.of(sword, axe, pickaxe, shovel, hoe);
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

    public List<Float> getAttackSpeed() {
        return attackSpeed;
    }

    public ToolMaterial getMaterial() {
        return material;
    }

    public static AttributeModifiersComponent createAttributeModifiers(double damage, float speed) {
        if (speed < 0.0f) {
            speed = 0;
        }
        return AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.ATTACK_DAMAGE,
                new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, damage, ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .add(
                EntityAttributes.ATTACK_SPEED,
                new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -4.0 + speed, ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .build();
    }


    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, double damage, float speed) {
        return createAttributeModifiers(material.attackDamageBonus() + damage, speed);
    }

    public AttributeModifiersComponent.Builder createAttributeBuilder(ToolMaterial material, double damage, float speed) {
        if (speed < 0.0f) {
            speed = 0;
        }
        return AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.ATTACK_DAMAGE,
                new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, material.attackDamageBonus() + damage, ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .add(
                EntityAttributes.ATTACK_SPEED,
                new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, -4.0 + speed, ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            );
    }

    public AttributeModifiersComponent createAttributes(ToolMaterial material, double damage, float speed) {
        return this.createAttributeBuilder(material, damage, speed).build();
    }

    public String getName() {
        return name;
    }
}
