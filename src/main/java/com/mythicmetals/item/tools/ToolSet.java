package com.mythicmetals.item.tools;

import com.mythicmetals.item.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ToolSet {

    private final String name;
    private final Item sword;
    private final AxeItem axe;
    private final Item pickaxe;
    private final ShovelItem shovel;
    private final HoeItem hoe;
    private final ToolMaterial material;
    private final List<Float> attackSpeed = new ArrayList<>();
    private List<MythicAttributeModifier> extraModifiers = List.of();

    private static Item.Properties createSettings(String name, Consumer<Item.Properties> settingsProcessor) {
        final var key = RegistryHelper.itemKey(name);
        final var settings = new Item.Properties().setId(key).group(MythicMetals.TABBED_GROUP).tab(2);
        settingsProcessor.accept(settings);
        return settings;
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed, List<MythicAttributeModifier> extraModifiers) {
        this(name, material, damage, speed, settings -> {
        });
        this.extraModifiers = extraModifiers;
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed) {
        this(name, material, damage, speed, settings -> {
        });
    }

    public ToolSet(String name, ToolMaterial material, int[] damage, float[] speed, Consumer<Item.Properties> settingsProcessor) {
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
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_sword"), sword);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_axe"), axe);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_pickaxe"), pickaxe);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_shovel"), shovel);
        Registry.register(BuiltInRegistries.ITEM, RegistryHelper.id(name + "_hoe"), hoe);
    }

    protected Item makeSword(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new SwordMock(material, damage, speed, settings, extraModifiers);
    }

    protected AxeItem makeAxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new AxeMock(material, damage, speed, settings, extraModifiers);
    }

    protected Item makePickaxe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new PickaxeMock(material, damage, speed, settings, extraModifiers);
    }

    protected ShovelItem makeShovel(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new ShovelMock(material, damage, speed, settings, extraModifiers);
    }

    protected HoeItem makeHoe(ToolMaterial material, int damage, float speed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
        return new HoeMock(material, damage, speed, settings, extraModifiers);
    }

    static class SwordMock extends Item {

        final List<MythicAttributeModifier> extraModifiers;

        public SwordMock(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
            // FIXME
            super(settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
            applyExtraModifiers(source, target, extraModifiers);
        }
    }

    static class PickaxeMock extends Item {

        final List<MythicAttributeModifier> extraModifiers;

        public PickaxeMock(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties settings, List<MythicAttributeModifier> extraModifiers) {
            // FIXME
            super(settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
            applyExtraModifiers(source, target, extraModifiers);
        }
    }

    static class ShovelMock extends ShovelItem {

        final List<MythicAttributeModifier> extraModifiers;

        public ShovelMock(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings, List<MythicAttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
            applyExtraModifiers(source, target, extraModifiers);
        }
    }

    static class AxeMock extends AxeItem {

        final List<MythicAttributeModifier> extraModifiers;

        public AxeMock(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings, List<MythicAttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
            applyExtraModifiers(source, target, extraModifiers);
        }
    }

    static class HoeMock extends HoeItem {

        final List<MythicAttributeModifier> extraModifiers;

        public HoeMock(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings, List<MythicAttributeModifier> extraModifiers) {
            super(material, attackDamage, attackSpeed, settings);
            this.extraModifiers = extraModifiers;
        }

        @Override
        public void deriveStackComponents(DataComponentMap source, DataComponentPatch.Builder target) {
            applyExtraModifiers(source, target, extraModifiers);
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

    public Item getSword() {
        return sword;
    }

    public AxeItem getAxe() {
        return axe;
    }

    public Item getPickaxe() {
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

    public List<MythicAttributeModifier> getExtraModifiers() {
        return extraModifiers;
    }

    protected static void applyExtraModifiers(DataComponentMap components, DataComponentPatch.Builder builder, List<MythicAttributeModifier> extraModifiers) {
        if (extraModifiers.isEmpty()) {
            return;
        }
        if (components.has(DataComponents.ATTRIBUTE_MODIFIERS)) {
            var attributes = components.get(DataComponents.ATTRIBUTE_MODIFIERS);
            assert attributes != null;

            extraModifiers.forEach(modifier -> {
                var key = modifier.attribute().unwrapKey();
                var id = key
                    .map(
                        attributeResourceKey -> RegistryHelper.id(attributeResourceKey.identifier().getPath())
                    )
                    .orElseGet(
                        () -> RegistryHelper.id("unknown_modifier")
                    );
                var newAttributes = attributes.withModifierAdded(modifier.attribute(), new AttributeModifier(id, modifier.value(), modifier.operation()), modifier.requiredSlot());

                builder.set(DataComponents.ATTRIBUTE_MODIFIERS, newAttributes);
            });
        }
    }
}
