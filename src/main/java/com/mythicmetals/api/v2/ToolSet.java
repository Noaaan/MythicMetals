package com.mythicmetals.api.v2;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;

import java.util.Optional;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class ToolSet {

    public final ResourceKey<Item> swordKey;
    public final ResourceKey<Item> axeKey;
    public final ResourceKey<Item> pickaxeKey;
    public final ResourceKey<Item> shovelKey;
    public final ResourceKey<Item> hoeKey;
    public final ResourceKey<Item> spearKey;
    private final ToolMaterial toolMaterial;

    protected Item sword;
    protected Item axe;
    protected Item pickaxe;
    protected Item shovel;
    protected Item hoe;
    protected Item spear;

    public ToolSet(String name, ToolMaterial toolMaterial) {
        this.swordKey = itemKey(name + "_sword");
        this.axeKey = itemKey(name + "_axe");
        this.pickaxeKey = itemKey(name + "_pickaxe");
        this.shovelKey = itemKey(name + "_shovel");
        this.hoeKey = itemKey(name + "_hoe");
        this.spearKey = itemKey(name + "_spear");
        this.toolMaterial = toolMaterial;
    }

    /* TODO - I have to permanently maintain attributes myself, since I don't constrain myself to the vanilla ones.
     * I want tools with different attributes, dynamic attributes even.
     * The latter being fairly difficult, since there is no vanilla way to do that at all.
     * owo-lib derived stack components might work, but the idea is still sketchy
     */

    // TODO - Is something more extendible than enum required? Maybe config?
    public ToolSet createDefault(AttackSpeeds attackSpeeds) {
        this.sword = RegistryHelper.item(swordKey, new Item(
            defaultSettings()
                .sword(toolMaterial, 3.0f, attackSpeeds.sword - 4.0f)
                .setId(swordKey)
        ));
        this.axe = RegistryHelper.item(axeKey, new Item(
            defaultSettings()
                .axe(toolMaterial, 5.0f, attackSpeeds.axe - 4.0f)
                .setId(axeKey)
        ));
        this.pickaxe = RegistryHelper.item(pickaxeKey, new Item(
            defaultSettings()
                .pickaxe(toolMaterial, 2.0f, attackSpeeds.pickaxe - 4.0f)
                .setId(pickaxeKey)
        ));
        this.shovel = RegistryHelper.item(shovelKey, new Item(
            defaultSettings()
                .shovel(toolMaterial, 1.0f, attackSpeeds.shovel - 4.0f)
                .setId(shovelKey)
        ));
        this.hoe = RegistryHelper.item(hoeKey, new Item(
            defaultSettings()
                .hoe(toolMaterial, 0.0f, attackSpeeds.hoe - 4.0f)
                .setId(hoeKey)
        ));
        // TODO - Spear item settings are complex. Requires more stats
        this.spear = RegistryHelper.item(spearKey, new Item(
            defaultSettings().setId(spearKey)
        ));
        return this;
    }

    protected Item.Properties defaultSettings() {
        return new Item.Properties()
            .group(MythicMetals.TABBED_GROUP).tab(2);
    }

    public Item getSpear() {
        return this.spear;
    }

    public enum AttackSpeeds {
        DEFAULT(1.6f, 0.9f, 1.2f, 1.1f, 1.0f, 1.0f),
        BETTER_AXE(1.6f, 1.0f, 1.2f, 1.1f, 1.0f, 1.0f), // +0.1 on axes
        FASTER(1.8f, 1.1f, 1.3f, 1.2f, 1.2f, 1.0f), // +0.1-0.2 to all
        HIGHEST(2.0f, 1.2f, 1.4f, 1.3f, 1.3f, 1.0f); // +0.2-0.4 to all

        public final float sword;
        public final float axe;
        public final float pickaxe;
        public final float shovel;
        public final float hoe;
        public final float spear;

        AttackSpeeds(float sword, float axe, float pickaxe, float shovel, float hoe, float spear) {
            this.sword = sword;
            this.axe = axe;
            this.pickaxe = pickaxe;
            this.shovel = shovel;
            this.hoe = hoe;
            this.spear = spear;
        }
    }

    private Item.Properties spearVanilla(
        ToolMaterial toolMaterial,
        float swingDuration,
        float damageMultiplier,
        float activationDelaySeconds,
        float dismountSeconds,
        float dismountSpeedRequirement,
        float knockbackSeconds,
        float knockbackSpeedRequirement,
        float damageSeconds,
        float damageSpeedRequirement
    ) {
        return new Item.Properties().durability(toolMaterial.durability())
            .repairable(toolMaterial.repairItems())
            .enchantable(toolMaterial.enchantmentValue())
            .component(DataComponents.DAMAGE_TYPE, new EitherHolder<>(DamageTypes.SPEAR))
            .component(
                DataComponents.KINETIC_WEAPON,
                new KineticWeapon(
                    10,
                    (int)(activationDelaySeconds * 20.0F),
                    KineticWeapon.Condition.ofAttackerSpeed((int)(dismountSeconds * 20.0F), dismountSpeedRequirement),
                    KineticWeapon.Condition.ofAttackerSpeed((int)(knockbackSeconds * 20.0F), knockbackSpeedRequirement),
                    KineticWeapon.Condition.ofRelativeSpeed((int)(damageSeconds * 20.0F), damageSpeedRequirement),
                    0.38F,
                    damageMultiplier,
                    Optional.of(toolMaterial == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_USE : SoundEvents.SPEAR_USE),
                    Optional.of(toolMaterial == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_HIT : SoundEvents.SPEAR_HIT)
                )
            )
            .component(
                DataComponents.PIERCING_WEAPON,
                new PiercingWeapon(
                    true,
                    false,
                    Optional.of(toolMaterial == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_ATTACK : SoundEvents.SPEAR_ATTACK),
                    Optional.of(toolMaterial == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_HIT : SoundEvents.SPEAR_HIT)
                )
            )
            .component(DataComponents.ATTACK_RANGE, new AttackRange(2.0F, 4.5F, 2.0F, 6.5F, 0.125F, 0.5F))
            .component(DataComponents.MINIMUM_ATTACK_CHARGE, 1.0F)
            .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int)(swingDuration * 20.0F)))
            .attributes(
                ItemAttributeModifiers.builder()
                    .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 0.0F + toolMaterial.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                    )
                    .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, 1.0F / swingDuration - 4.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                    )
                    .build()
            )
            .component(DataComponents.USE_EFFECTS, new UseEffects(true, false, 1.0F))
            .component(DataComponents.WEAPON, new Weapon(1));
    }

    public Item getSword() {
        return sword;
    }

    public Item getAxe() {
        return axe;
    }

    public Item getPickaxe() {
        return pickaxe;
    }

    public Item getShovel() {
        return shovel;
    }

    public Item getHoe() {
        return hoe;
    }

    public Identifier getSwordId() {
        return swordKey.identifier();
    }

    public Identifier getAxeId() {
        return axeKey.identifier();
    }

    public Identifier getPickaxeId() {
        return pickaxeKey.identifier();
    }

    public Identifier getShovelId() {
        return shovelKey.identifier();
    }

    public Identifier getHoeId() {
        return hoeKey.identifier();
    }
}
