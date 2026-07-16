package com.mythicmetals.api.v2;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.item.MythicItemAttributes;
import com.mythicmetals.item.MythicSpearStats;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

public class ToolSet {

    public final ResourceKey<Item> swordKey;
    public final ResourceKey<Item> axeKey;
    public final ResourceKey<Item> pickaxeKey;
    public final ResourceKey<Item> shovelKey;
    public final ResourceKey<Item> hoeKey;
    public final ResourceKey<Item> spearKey;
    private final ToolMaterial toolMaterial;
    private final String name;

    protected Item sword;
    protected Item axe;
    protected Item pickaxe;
    protected Item shovel;
    protected Item hoe;
    protected Item spear;

    public ToolSet(String name, ToolMaterial toolMaterial) {
        this.name = name;
        this.swordKey = itemKey(name + "_sword");
        this.axeKey = itemKey(name + "_axe");
        this.pickaxeKey = itemKey(name + "_pickaxe");
        this.shovelKey = itemKey(name + "_shovel");
        this.hoeKey = itemKey(name + "_hoe");
        this.spearKey = itemKey(name + "_spear");
        this.toolMaterial = toolMaterial;
    }

    // TODO - Is something more extendible than enum required? Maybe config?
    public ToolSet createDefault(
        UnaryOperator<Item.Properties> settingsOperator,
        AttackSpeeds attackSpeeds,
        MythicSpearStats.SpearStats spearStats,
        List<MythicAttributeModifier> extraModifiers
    ) {
        this.sword = RegistryHelper.item(swordKey, new Item(
            settingsOperator.apply(
                swordVanilla()
                    .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.SWORD, attackSpeeds, extraModifiers))
                    .setId(swordKey)
            )
        ));
        this.axe = RegistryHelper.item(axeKey, new Item(
            settingsOperator.apply(
                axeVanilla()
                    .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.AXE, attackSpeeds, extraModifiers))
                    .setId(axeKey)
            )
        ));
        this.pickaxe = RegistryHelper.item(pickaxeKey, new Item(
            settingsOperator.apply(
                pickaxeVanilla()
                    .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.PICKAXE, attackSpeeds, extraModifiers))
                    .setId(pickaxeKey)
            )
        ));
        this.shovel = RegistryHelper.item(shovelKey, new Item(
            settingsOperator.apply(
                shovelVanilla()
                    .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.SHOVEL, attackSpeeds, extraModifiers))
                    .setId(shovelKey)
            )
        ));
        this.hoe = RegistryHelper.item(hoeKey, new Item(
            settingsOperator.apply(
                hoeVanilla()
                    .component(DataComponents.ATTRIBUTE_MODIFIERS, mythicModifier(MythicItemAttributes.ToolType.HOE, attackSpeeds, extraModifiers))
                    .setId(hoeKey)
            )
        ));
        this.spear = RegistryHelper.item(spearKey, new Item(
            settingsOperator.apply(
                spearSettings(toolMaterial, spearStats)
                    .setId(spearKey)
            )
        ));
        return this;
    }

    protected Item.Properties defaultSettings() {
        return new Item.Properties()
            .durability(toolMaterial.durability())
            .stacksTo(1)
            .repairable(toolMaterial.repairItems())
            .enchantable(toolMaterial.enchantmentValue())
            .group(MythicMetals.TABBED_GROUP)
            .tab(2);
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

    protected Item.Properties spearSettings(ToolMaterial material, MythicSpearStats.SpearStats stats, List<MythicAttributeModifier> extraAttributes) {
        var attributesBuilder = ItemAttributeModifiers.builder();
        attributesBuilder
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 0.0F + toolMaterial.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, 1.0F / stats.swingDuration() - 4.0, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            );
        extraAttributes.forEach(mythicAttributeModifier -> {
            if (mythicAttributeModifier.requiredSlot() == EquipmentSlotGroup.MAINHAND) {
                attributesBuilder.add(
                    mythicAttributeModifier.attribute(),
                    new AttributeModifier(RegistryHelper.id("spear_bonus"), mythicAttributeModifier.value(), mythicAttributeModifier.operation()),
                    mythicAttributeModifier.requiredSlot()
                );
            }
        });

        return spearVanilla(
            material,
            stats.swingDuration(),
            stats.damageMultiplier(),
            stats.activationDelay(),
            stats.dismountTime(),
            stats.dismountRequirement(),
            stats.knockbackTime(),
            5.1f,
            stats.damageTime(),
            4.6f
        ).attributes(attributesBuilder.build()
        );
    }

    protected Item.Properties spearSettings(ToolMaterial material, MythicSpearStats.SpearStats stats) {
        return spearVanilla(
            material,
            stats.swingDuration(),
            stats.damageMultiplier(),
            stats.activationDelay(),
            stats.dismountTime(),
            stats.dismountRequirement(),
            stats.knockbackTime(),
            5.1f,
            stats.damageTime(),
            4.6f
        ).attributes(
            ItemAttributeModifiers.builder()
                .add(
                    Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 0.0F + toolMaterial.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
                )
                .add(
                    Attributes.ATTACK_SPEED,
                    new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, 1.0F / stats.swingDuration() - 4.0, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.MAINHAND
                )
                .build()
        );
    }

    protected Item.Properties swordVanilla() {
        HolderGetter<Block> holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return defaultSettings()
            .component(
                DataComponents.TOOL,
                new Tool(
                    List.of(
                        Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                        Tool.Rule.overrideSpeed(holderGetter.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                        Tool.Rule.overrideSpeed(holderGetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
                    ),
                    1.0F,
                    2,
                    false
                )
            )
            .component(DataComponents.WEAPON, new Weapon(1));
    }

    protected Item.Properties pickaxeVanilla() {
        return defaultSettings()
            .component(
                DataComponents.TOOL,
                vanillaToolComponent(BlockTags.MINEABLE_WITH_PICKAXE)
            )
            .component(DataComponents.WEAPON, new Weapon(2));
    }

    protected Item.Properties axeVanilla() {
        return defaultSettings()
            .component(
                DataComponents.TOOL,
                vanillaToolComponent(BlockTags.MINEABLE_WITH_AXE)
            )
            .component(DataComponents.WEAPON, new Weapon(2, 5.0f));
    }

    protected Item.Properties shovelVanilla() {
        return defaultSettings()
            .component(
                DataComponents.TOOL,
                vanillaToolComponent(BlockTags.MINEABLE_WITH_SHOVEL)
            )
            .component(DataComponents.WEAPON, new Weapon(2));
    }

    protected Item.Properties hoeVanilla() {
        return defaultSettings()
            .component(
                DataComponents.TOOL,
                vanillaToolComponent(BlockTags.MINEABLE_WITH_HOE)
            )
            .component(DataComponents.WEAPON, new Weapon(2));
    }

    private Tool vanillaToolComponent(TagKey<Block> tagKey) {
        HolderGetter<Block> holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return new Tool(
            List.of(
                Tool.Rule.deniesDrops(holderGetter.getOrThrow(toolMaterial.incorrectBlocksForDrops())),
                Tool.Rule.minesAndDrops(holderGetter.getOrThrow(tagKey), toolMaterial.speed())
            ),
            1.0F,
            1,
            true
        );
    }

    protected ItemAttributeModifiers mythicModifier(MythicItemAttributes.ToolType toolType, AttackSpeeds attackSpeeds, List<MythicAttributeModifier> extraAttributes) {
        return MythicItemAttributes.createToolModifier(name, toolType, attackSpeeds, toolMaterial, extraAttributes);

    }

    protected Item.Properties spearVanilla(
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
        return this.defaultSettings()
            .durability(toolMaterial.durability())
            .repairable(toolMaterial.repairItems())
            .enchantable(toolMaterial.enchantmentValue())
            .component(DataComponents.DAMAGE_TYPE, new EitherHolder<>(DamageTypes.SPEAR))
            .component(
                DataComponents.KINETIC_WEAPON,
                new KineticWeapon(
                    10,
                    (int) (activationDelaySeconds * 20.0F),
                    KineticWeapon.Condition.ofAttackerSpeed((int) (dismountSeconds * 20.0F), dismountSpeedRequirement),
                    KineticWeapon.Condition.ofAttackerSpeed((int) (knockbackSeconds * 20.0F), knockbackSpeedRequirement),
                    KineticWeapon.Condition.ofRelativeSpeed((int) (damageSeconds * 20.0F), damageSpeedRequirement),
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
            .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int) (swingDuration * 20.0F)))
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
