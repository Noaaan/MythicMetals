package com.mythicmetals.item.tools;

import com.mythicmetals.AttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ToolSet {

    private final String name;
    private final SwordItem sword;
    private final AxeItem axe;
    private final PickaxeItem pickaxe;
    private final ShovelItem shovel;
    private final HoeItem hoe;
    private final ToolMaterial material;
    private final List<Float> attackSpeed = new ArrayList<>();
    private List<AttributeModifier> extraModifiers = List.of();

    private static Item.Settings createSettings(String name, Consumer<Item.Settings> settingsProcessor) {
        final var key = RegistryHelper.itemKey(name);
        final var settings = new Item.Settings().registryKey(key).group(MythicMetals.TABBED_GROUP).tab(2);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed, List<AttributeModifier> extraModifiers) {
        this(name, material, damage, speed, settings -> {
        });
        this.extraModifiers = extraModifiers;
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed) {
        this(name, material, damage, speed, settings -> {
        });
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Settings> settingsProcessor) {
        this.name = name;
        this.material = material;
        this.sword = this.makeSword(material, damage[0], speed[0] - 4.0f, createSettings(name + "_sword", settingsProcessor), extraModifiers);
        this.axe = this.makeAxe(material, damage[1], speed[1] - 4.0f, createSettings(name + "_axe", settingsProcessor), extraModifiers);
        this.pickaxe = this.makePickaxe(material, damage[2], speed[2] - 4.0f, createSettings(name + "_pickaxe", settingsProcessor), extraModifiers);
        this.shovel = this.makeShovel(material, damage[3], speed[3] - 4.0f, createSettings(name + "_shovel", settingsProcessor), extraModifiers);
        this.hoe = this.makeHoe(material, damage[4], speed[4] - 4.0f, createSettings(name + "_hoe", settingsProcessor), extraModifiers);
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

    protected SwordItem makeSword(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new SwordMock(material, damage, speed, settings, extraModifiers);
    }

    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new AxeMock(material, damage, speed, settings, extraModifiers);
    }

    protected PickaxeItem makePickaxe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new PickaxeMock(material, damage, speed, settings, extraModifiers);
    }

    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new ShovelMock(material, damage, speed, settings, extraModifiers);
    }

    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Settings settings, List<AttributeModifier> extraModifiers) {
        return new HoeMock(material, damage, speed, settings, extraModifiers);
    }

    static class SwordMock extends SwordItem {

        final List<AttributeModifier> extraModifiers;

        public SwordMock(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings, List<AttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void postProcessComponents(ItemStack stack) {
            super.postProcessComponents(stack);
            applyChungusModifiers(stack, extraModifiers);
        }
    }

    static class PickaxeMock extends PickaxeItem {

        final List<AttributeModifier> extraModifiers;

        public PickaxeMock(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings, List<AttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void postProcessComponents(ItemStack stack) {
            super.postProcessComponents(stack);
            applyChungusModifiers(stack, extraModifiers);
        }
    }

    static class ShovelMock extends ShovelItem {

        final List<AttributeModifier> extraModifiers;

        public ShovelMock(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings, List<AttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void postProcessComponents(ItemStack stack) {
            super.postProcessComponents(stack);
            applyChungusModifiers(stack, extraModifiers);
        }
    }

    static class AxeMock extends AxeItem {

        final List<AttributeModifier> extraModifiers;

        public AxeMock(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings, List<AttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void postProcessComponents(ItemStack stack) {
            super.postProcessComponents(stack);
            applyChungusModifiers(stack, extraModifiers);
        }
    }

    static class HoeMock extends HoeItem {

        final List<AttributeModifier> extraModifiers;

        public HoeMock(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings, List<AttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void postProcessComponents(ItemStack stack) {
            super.postProcessComponents(stack);
            applyChungusModifiers(stack, extraModifiers);
        }
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

    public String getName() {
        return name;
    }

    public List<AttributeModifier> getExtraModifiers() {
        return extraModifiers;
    }

    /**
     * TODO - I severely loathe this code and all of its associates. Replace it ASAP.
     */
    protected static void applyChungusModifiers(ItemStack stack, List<AttributeModifier> extraModifiers) {
        if (extraModifiers.isEmpty()) {
            return;
        }
        if (stack.contains(DataComponentTypes.ATTRIBUTE_MODIFIERS)) {
            var attributes = stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
            assert attributes != null;

            extraModifiers.forEach(modifier -> {
                var id = RegistryHelper.id("sword_" + modifier.attribute().getKey().orElseThrow().getValue().getPath());
                var newAttributes = attributes.with(modifier.attribute(), new EntityAttributeModifier(id, modifier.value(), modifier.operation()), modifier.requiredSlot());
                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, newAttributes);
            });
        }
    }
}
