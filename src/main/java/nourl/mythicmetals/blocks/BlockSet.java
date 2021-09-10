package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockSet {
    private final OreBlock ore;
    private final Block storageBlock;
    private final Block oreStorageBlock;

    private final String baseName;
    private final boolean fireproof = false;

    private final Map<String, Block> oreVariants;

    private BlockSet(String baseName, OreBlock ore, Block storageBlock, Block oreStorageBlock, Map<String, Block> oreVariants, boolean fireproof) {
        this.baseName = baseName;

        this.ore = ore;
        this.oreStorageBlock = storageBlock;
        this.storageBlock = oreStorageBlock;

        this.oreVariants = oreVariants;
    }

    private void register() {
        RegistryHelper.block(baseName + "_ore", ore, MythicMetals.MYTHICMETALS, fireproof);
        RegistryHelper.block("raw_" + baseName + "_block", storageBlock, MythicMetals.MYTHICMETALS, fireproof);
        RegistryHelper.block(baseName + "_block", oreStorageBlock, MythicMetals.MYTHICMETALS, fireproof);

        oreVariants.forEach((s, block) -> RegistryHelper.block(s + "_" + baseName + "_ore", block, MythicMetals.MYTHICMETALS, fireproof));
    }

    public OreBlock getOre() {
        return ore;
    }

    public static class Builder {

        private static final List<BlockSet> toBeRegistered = new ArrayList<>();

        private final String baseName;
        private final boolean fireproof;

        private OreBlock ore = null;
        private Block storage = null;
        private Block oreStorage = null;

        private BlockSoundGroup currentSounds = BlockSoundGroup.STONE;

        private float currentHardness = -1;
        private float currentResistance = -1;

        private Consumer<FabricBlockSettings> settingsProcessor = fabricBlockSettings -> {};
        private final Map<String, Block> oreVariants = new HashMap<>();

        public static Builder begin(String baseName, boolean fireproof) {
            return new Builder(baseName, fireproof);
        }

        private Builder(String baseName, boolean fireproof) {
            this.baseName = baseName;
            this.fireproof = fireproof;
        }

        public Builder createDefaultSet(float oreStrength, int oreMiningLevel, float storageStrength, int storageMiningLevel) {
            return strength(oreStrength).createOre(oreMiningLevel).strength(storageStrength).createStorageBlock(storageMiningLevel).createOreStorageBlock(storageMiningLevel);
        }

        public Builder settings(Consumer<FabricBlockSettings> processor) {
            this.settingsProcessor = processor;
            return this;
        }

        public Builder sounds(BlockSoundGroup sounds) {
            this.currentSounds = sounds;
            return this;
        }

        public Builder strength(float strength) {
            return strength(strength, strength + 1);
        }

        public Builder strength(float hardness, float resistance) {
            this.currentHardness = hardness;
            this.currentResistance = resistance;
            return this;
        }

        public Builder createOre(int miningLevel, UniformIntProvider experience) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.ore = new OreBlock(settings, experience);
            return this;
        }

        public Builder createOre(int miningLevel) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.ore = new OreBlock(settings);
            return this;
        }

        public Builder createOreVariant(String name, int miningLevel) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, new OreBlock(settings));
            return this;
        }

        public Builder createStorageBlock(int miningLevel) {
            return createStorageBlock(Material.METAL, miningLevel);
        }

        public Builder createStorageBlock(Material material, int miningLevel) {
            final var settings = blockSettings(material, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.storage = new Block(settings);
            return this;
        }

        public Builder createOreStorageBlock(int miningLevel) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.oreStorage = new Block(settings);
            return this;
        }

        public BlockSet finish() {
            final var set = new BlockSet(baseName, ore, storage, oreStorage, oreVariants, fireproof);
            Builder.toBeRegistered.add(set);
            return set;
        }

        public static void register() {
            toBeRegistered.forEach(BlockSet::register);
            toBeRegistered.clear();
        }

        private static FabricBlockSettings blockSettings(Material material, float hardness, float resistance, BlockSoundGroup sounds, int miningLevel) {
            return FabricBlockSettings.of(material).strength(hardness, resistance).sounds(sounds).breakByTool(FabricToolTags.PICKAXES, miningLevel).requiresTool();
        }

    }
}
