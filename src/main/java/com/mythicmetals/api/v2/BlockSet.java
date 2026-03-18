package com.mythicmetals.api.v2;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.block.BanglumOreBlock;
import com.mythicmetals.block.StarriteOreBlock;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.util.Maldenhagen;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * This class is a container which is used for the creation for all the blocks in Mythic Metals.
 * For creating blocks using this you want to start with looking at the {@link Builder}, which
 * contains all the methods for creating blocks.
 *
 * @author glisco
 * @author Noaaan
 */
public class BlockSet {
    private final DropExperienceBlock ore;
    private final Block storageBlock;
    private final Block oreStorageBlock;
    private final AnvilBlock anvil;

    private final String name;
    private final boolean fireproof;

    private final Multimap<Block, Identifier> miningLevels;
    private final Multimap<AnvilBlock, Identifier> anvilMap;
    private final Map<String, DropExperienceBlock> oreVariants;
    private final Rarity rarity;

    /**
     * This constructor collects the smaller constructors from the {@link Builder} and creates a set of blocks.
     * Use {@link Builder#begin(String) BlockSet.Builder.begin} to begin,
     * and call {@link Builder#finish()} when you are done.
     *
     * @param name            Common name for the entire set of blocks, applies to every block created.
     * @param ore             Contains a vanilla {@link DropExperienceBlock}.
     * @param storageBlock    Contains a {@link Block} which is used as a storage block.
     * @param oreStorageBlock Contains a {@link Block} which is used as a ore storage block.
     * @param anvil           Contains an {@link AnvilBlock}
     * @param oreVariants     A map of a string and {@link DropExperienceBlock} which is used for variant ores.
     * @param fireproof       Boolean for creating fireproof block sets.
     * @param miningLevels    A map containing all the blocks being registered with their corresponding mining levels.
     * @param anvilMap        A map containing all anvils and their levels, so that they can be disabled.
     * @param rarity          Rarity of the block item
     */
    private BlockSet(String name,
                     DropExperienceBlock ore,
                     Block storageBlock,
                     Block oreStorageBlock,
                     AnvilBlock anvil,
                     Map<String, DropExperienceBlock> oreVariants,
                     boolean fireproof,
                     Multimap<Block, Identifier> miningLevels,
                     Multimap<AnvilBlock, Identifier> anvilMap, Rarity rarity) {

        this.name = name;
        this.fireproof = fireproof;

        this.ore = ore;
        this.storageBlock = storageBlock;
        this.oreStorageBlock = oreStorageBlock;
        this.anvil = anvil;

        this.oreVariants = oreVariants;
        this.miningLevels = miningLevels;
        this.anvilMap = anvilMap;
        this.rarity = rarity;
    }

    /**
     * @return Returns the ore block in the set
     */
    public DropExperienceBlock getOre() {
        return ore;
    }

    /**
     * @return Returns the storage block from the set
     */
    public Block getStorageBlock() {
        return storageBlock;
    }

    /**
     * @return Returns the ore storage block from the set
     */
    public Block getOreStorageBlock() {
        return oreStorageBlock;
    }

    /**
     * @param variant The string of the ore variants name
     * @return Returns the specified ore variant from the variant map in the blockset
     */
    public DropExperienceBlock getOreVariant(String variant) {
        return oreVariants.get(variant);
    }

    /**
     * @return Returns the anvil from the set
     */
    public AnvilBlock getAnvil() {
        return anvil;
    }

    public Set<Block> getOreVariants() {
        return ImmutableSet.copyOf(oreVariants.values());
    }

    public Map<String, Block> getOreVariantsMap() {
        return Map.copyOf(oreVariants);
    }

    public String getName() {
        return this.name;
    }

    /**
     * This is the BlockSet Builder, which is used for constructing new sets of blocks.
     * <p>
     * To begin creating BlockSets you want to call:
     * {@code public static final BlockSet SETNAME = }{@link Builder#begin(String) BlockSet.Builder.begin()}
     * where you provide a {@code string} for the name/key, and the {@code fireproof} boolean.
     * <p>
     * When creating blocks it's important to call {@link #strength(float)} before creating a block or any set.
     * This is because the values are grabbed from this method. You can call it multiple times if you wish to
     * specifically tailor the values for individual blocks.
     * <p>
     * When you are finished with adding your blocks to the set,
     * call {@link Builder#finish() Builder.finish} when you are done.
     *
     * @author glisco
     * @author Noaaan
     * @see Builder#begin(String)
     */
    public static class Builder {

        private final String name;
        private boolean fireproof = false;
        private final Map<String, DropExperienceBlock> oreVariants = new LinkedHashMap<>();
        private DropExperienceBlock ore = null;
        private Block storageBlock = null;
        private Block oreStorageBlock = null;
        private AnvilBlock anvil = null;
        private SoundType currentSounds = SoundType.STONE;
        private float currentHardness = -1;
        private float currentResistance = -1;
        private final Multimap<Block, Identifier> miningLevels = HashMultimap.create();
        private final Multimap<AnvilBlock, Identifier> anvilMap = HashMultimap.create();
        private final Consumer<BlockBehaviour.Properties> settingsProcessor = settings -> {
        };

        private final Identifier SHOVEL = Identifier.parse("mineable/shovel");
        private final Identifier PICKAXE = Identifier.parse("mineable/pickaxe");
        private Rarity rarity = Rarity.COMMON;

        /**
         * @see #begin(String)
         */
        private Builder(String name) {
            this.name = name;
        }

        /**
         * This method begins the creation of a block set.
         * You can add as many blocks as you want in the set
         * Call {@link Builder#finish()} when you are done.
         *
         * @param name      The name of the new block set
         */
        public static Builder begin(String name) {
            return new Builder(name);
        }

        public Builder fireproof() {
            this.fireproof = true;
            return this;
        }

        /**
         * Used internally for configuring blocks
         * This does NOT configure the registry key. Do this yourself if calling this method.
         *
         * @param hardness   Determines the breaking time of the block.
         * @param resistance Determines blast resistance of a block.
         * @param sounds     Determines the sounds that blocks play when interacted with.
         */
        public static BlockBehaviour.Properties blockSettings(float hardness, float resistance, SoundType sounds) {
            return BlockBehaviour.Properties.of()
                .strength(hardness, resistance)
                .sound(sounds)
                .forceSolidOn()
                .requiresCorrectToolForDrops();
        }

        /**
         * Applies sounds to the block(s) in the set.
         *
         * @param sounds The {@link SoundType} which should be played.
         */
        public Builder sounds(SoundType sounds) {
            this.currentSounds = sounds;
            return this;
        }

        /**
         * A simplified method to create a hardness and resistance value from a single int.
         *
         * @param strength The base int value for the blocks' strength.
         * @return hardness, resistance (strength + 1)
         */
        public Builder strength(float strength) {
            return strength(strength, strength + 1);
        }

        /**
         * Gives the block(s) in the set the specified strength.
         *
         * @param hardness   Hardness of the block, determines breaking speed.
         * @param resistance Blast resistance of the block.
         */
        public Builder strength(float hardness, float resistance) {
            this.currentHardness = hardness;
            this.currentResistance = resistance;
            return this;
        }

        public Builder rarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        /**
         * Creates an ore block.
         *
         * @param miningLevel The mining level of the ore block.
         * @see Builder
         */
        public Builder createOre(Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey(name + "_ore"));
            settingsProcessor.accept(settings);
            this.ore = new DropExperienceBlock(ConstantInt.ZERO, settings);
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * Creates an ore block, which drops experience.
         *
         * @param miningLevel The mining level of the ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         * @see Builder
         */
        public Builder createOre(Identifier miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey(name + "_ore"));
            settingsProcessor.accept(settings);
            this.ore = new DropExperienceBlock(experience, settings);
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * Creates an ore block, which drops experience.
         *
         * @param miningLevel The mining level of the ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         * @see Builder
         */
        public Builder createLuminantOre(Identifier miningLevel, UniformInt experience, int luminance) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds).lightLevel(blockState -> luminance);
            settings.setId(RegistryHelper.blockKey(name + "_ore"));
            settingsProcessor.accept(settings);
            this.ore = new DropExperienceBlock(ConstantInt.ZERO, settings);
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            Maldenhagen.injectCopium(this.ore);
            return this;
        }

        /**
         * Creates an ore variant.
         *
         * @param variantName The name of the variant, which is used as a part of the registry key.
         * @param miningLevel The mining level of the ore variant.
         * @see Builder
         */
        public Builder createOreVariant(String variantName, Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey("%s_%s_ore".formatted(variantName, this.name)));
            settingsProcessor.accept(settings);
            var variant = new DropExperienceBlock(ConstantInt.ZERO, settings);
            this.oreVariants.put(variantName, variant);
            miningLevels.put(variant, miningLevel);
            miningLevels.put(variant, PICKAXE);
            return this;
        }

        /**
         * Creates an ore variant, which drops experience.
         *
         * @param variantName The name of the variant, which is used as a part of the registry key.
         * @param miningLevel The mining level of the variant ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createOreVariant(String variantName, Identifier miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey("%s_%s_ore".formatted(variantName, this.name)));
            settingsProcessor.accept(settings);
            this.oreVariants.put(variantName, new DropExperienceBlock(experience, settings));
            miningLevels.put(oreVariants.get(variantName), miningLevel);
            miningLevels.put(oreVariants.get(variantName), PICKAXE);
            return this;
        }

        /**
         * Creates an ore variant, which drops experience.
         *
         * @param variantName The name of the variant, which is used as a part of the registry key.
         * @param miningLevel The mining level of the variant ore block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createOreVariant(String variantName, Identifier miningLevel, UniformInt experience, int luminance) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds).lightLevel(blockState -> luminance);
            settings.setId(RegistryHelper.blockKey("%s_%s_ore".formatted(variantName, this.name)));
            settingsProcessor.accept(settings);
            this.oreVariants.put(variantName, new DropExperienceBlock(experience, settings));
            miningLevels.put(oreVariants.get(variantName), miningLevel);
            miningLevels.put(oreVariants.get(variantName), PICKAXE);
            Maldenhagen.injectCopium(this.oreVariants.get(variantName));
            return this;
        }

        /**
         * A special ore creator for the creation of a {@link StarriteOreBlock}.
         *
         * @param miningLevel The mining level of the block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createStarriteOre(Identifier miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey(name + "_ore"));
            settingsProcessor.accept(settings);
            this.ore = new StarriteOreBlock(settings, experience);
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * A special ore creator for the creation of a {@link BanglumOreBlock}.
         *
         * @param miningLevel The mining level of the block.
         */
        public Builder createBanglumOre(Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey(name + "_ore"));
            settingsProcessor.accept(settings);
            this.ore = new BanglumOreBlock(settings);
            miningLevels.put(ore, miningLevel);
            miningLevels.put(ore, PICKAXE);
            return this;
        }

        /**
         * A special method for the creation of variants from {@link StarriteOreBlock}.
         *
         * @param variantName The name of the variant, which is used as a part of the registry key.
         * @param miningLevel The mining level of the block.
         * @param experience  An {@link UniformInt}, which holds the range of xp that can drop.
         */
        public Builder createStarriteOreVariant(String variantName, Identifier miningLevel, UniformInt experience) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey("%s_%s_ore".formatted(variantName, this.name)));
            settingsProcessor.accept(settings);
            this.oreVariants.put(variantName, new StarriteOreBlock(settings, experience));
            miningLevels.put(oreVariants.get(variantName), miningLevel);
            miningLevels.put(oreVariants.get(variantName), PICKAXE);
            return this;
        }

        /**
         * A special method for the creation of variants from {@link BanglumOreBlock}.
         *
         * @param name        The name/key for the variant.
         * @param miningLevel The mining level of the block.
         */
        public Builder createBanglumOreVariant(String name, Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey(name + "_ore"));
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, new BanglumOreBlock(settings));
            miningLevels.put(oreVariants.get(name), miningLevel);
            miningLevels.put(oreVariants.get(name), PICKAXE);
            return this;
        }

        /**
         * A special method for the creation of storage blocks that copies Amethyst Blocks.
         *
         * @param miningLevel The mining level of the block.
         * @see Blocks#AMETHYST_BLOCK
         * @see AmethystBlock
         */
        public Builder createAmethystStorageBlock(Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, SoundType.AMETHYST);
            settings.setId(RegistryHelper.blockKey(name + "_block"));
            this.storageBlock = new AmethystBlock(settings);
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Create a storage block, with a specific material in mind.
         *
         * @param miningLevel The mining level of the storage block.
         */
        public Builder createStorageBlock(Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey(name + "_block"));
            settingsProcessor.accept(settings);
            this.storageBlock = new Block(settings);
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Create a storage block, with a specific sound in mind.
         *
         * @param sounds      A {@link SoundType}, which determines block sounds.
         * @param miningLevel The mining level of the storage block.
         */
        public Builder createStorageBlock(SoundType sounds, Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, sounds);
            settings.setId(RegistryHelper.blockKey(name + "_block"));
            settingsProcessor.accept(settings);
            this.storageBlock = new Block(settings);
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Create a raw ore storage block.
         *
         * @param miningLevel The mining level of the raw storage block.
         */
        public Builder createOreStorageBlock(Identifier miningLevel) {
            final var settings = blockSettings(currentHardness, currentResistance, currentSounds);
            settings.setId(RegistryHelper.blockKey("raw_" + name + "_block"));
            settingsProcessor.accept(settings);
            this.oreStorageBlock = new Block(settings);
            miningLevels.put(oreStorageBlock, miningLevel);
            miningLevels.put(oreStorageBlock, PICKAXE);
            return this;
        }

        /**
         * Creates an anvil for a blockset.
         * Only requires a mining level, since hardness and resistance match vanilla values.
         *
         * @param miningLevel Mining level of the anvil.
         */
        public Builder createAnvil(Identifier miningLevel) {
            if (MythicMetals.CONFIG.enableAnvils()) {
                final var settings = blockSettings(5.0f, 15000f, SoundType.ANVIL);
                settings.setId(RegistryHelper.blockKey(name + "_anvil"));
                settingsProcessor.accept(settings);
                this.anvil = new AnvilBlock(settings);
                anvilMap.put(anvil, miningLevel);
                anvilMap.put(anvil, PICKAXE);
            }
            return this;
        }

        /**
         * Kinda manual at this point ngl
         */
        public <T extends Block> Builder createCustomStorageBlock(T block, Identifier miningLevel) {
            this.storageBlock = block;
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        public Builder createCustomStorageBlock(Identifier miningLevel, BlockBehaviour.Properties settings) {
            settingsProcessor.accept(settings);
            settings.setId(RegistryHelper.blockKey(name + "_block"));
            this.storageBlock = new Block(settings);
            miningLevels.put(storageBlock, miningLevel);
            miningLevels.put(storageBlock, PICKAXE);
            return this;
        }

        /**
         * Finishes the creation of the block set, and returns the entire set using the settings declared.
         *
         * @return BlockSet
         */
        public BlockSet finish() {
            return new BlockSet(
                this.name,
                this.ore,
                this.storageBlock,
                this.oreStorageBlock,
                this.anvil,
                this.oreVariants,
                this.fireproof,
                this.miningLevels,
                this.anvilMap,
                this.rarity
            );
        }
    }
}
