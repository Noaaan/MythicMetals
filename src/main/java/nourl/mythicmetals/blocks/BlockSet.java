package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("ClassCanBeRecord")
public class BlockSet {
    private final OreBlock ore;
    private final Block storageBlock;
    private final Block oreStorageBlock;
    private final AnvilBlock anvil;

    private final String name;
    private final boolean fireproof;

    private final Map<String, OreBlock> oreVariants;

    private BlockSet(String name, OreBlock ore, Block storageBlock, Block oreStorageBlock, AnvilBlock anvil, Map<String, OreBlock> oreVariants, boolean fireproof) {
        this.name = name;

        this.ore = ore;
        this.storageBlock = storageBlock;
        this.oreStorageBlock = oreStorageBlock;
        this.anvil = anvil;

        this.oreVariants = oreVariants;
        this.fireproof = fireproof;
    }

    private void register() {
        if (ore != null)
            RegistryHelper.block(name + "_ore", ore, fireproof);
        oreVariants.forEach((s, block) -> RegistryHelper.block(s + "_" + name + "_ore", block, fireproof));
        if (oreStorageBlock != null) {
            RegistryHelper.block("raw_" + name + "_block", oreStorageBlock, fireproof);
        }
        if (storageBlock != null) {
            RegistryHelper.block(name + "_block", storageBlock, fireproof);
        }
        if (anvil != null) {
            RegistryHelper.block(name + "_anvil", anvil, fireproof);
        }

    }

    public OreBlock getOre() {
        return ore;
    }

    public Block getStorageBlock() {
        return storageBlock;
    }

    public OreBlock getOreVariant(String variant) {
        return oreVariants.get(variant);
    }

    public static class Builder {

        private static final List<BlockSet> toBeRegistered = new ArrayList<>();

        private final String name;
        private final boolean fireproof;
        private final Map<String, OreBlock> oreVariants = new LinkedHashMap<>();
        private OreBlock ore = null;
        private Block storageBlock = null;
        private Block oreStorageBlock = null;
        private AnvilBlock anvil = null;
        private BlockSoundGroup currentSounds = BlockSoundGroup.STONE;
        private float currentHardness = -1;
        private float currentResistance = -1;
        private Consumer<FabricBlockSettings> settingsProcessor = fabricBlockSettings -> {
        };

        private Builder(String name, boolean fireproof) {
            this.name = name;
            this.fireproof = fireproof;
        }

        public static Builder begin(String name, boolean fireproof) {
            return new Builder(name, fireproof);
        }

        public static void register() {
            toBeRegistered.forEach(BlockSet::register);
            toBeRegistered.clear();
        }

        private static FabricBlockSettings blockSettings(Material material, float hardness, float resistance, BlockSoundGroup sounds, int miningLevel) {
            return FabricBlockSettings.of(material).strength(hardness, resistance).sounds(sounds).breakByTool(FabricToolTags.PICKAXES, miningLevel).requiresTool();
        }

        public Builder createDefaultSet(float strength, int miningLevel) {
            return strength(strength).createOre(miningLevel).strength(strength + 1.0F).createStorageBlock(miningLevel++).createOreStorageBlock(miningLevel).createAnvil(miningLevel);
        }

        public Builder createDefaultSet(float oreStrength, int oreMiningLevel, float storageStrength, int storageMiningLevel) {
            return strength(oreStrength).createOre(oreMiningLevel).strength(storageStrength).createStorageBlock(storageMiningLevel).createOreStorageBlock(storageMiningLevel);
        }

        public Builder createDefaultSet(float oreStrength, int oreMiningLevel, float storageStrength, int storageMiningLevel, int anvilMiningLevel) {
            return strength(oreStrength).createOre(oreMiningLevel).strength(storageStrength).createStorageBlock(storageMiningLevel).createOreStorageBlock(storageMiningLevel).createAnvil(anvilMiningLevel);
        }

        public Builder createAnvilSet(float strength, int miningLevel) {
            return strength(strength).createStorageBlock(miningLevel).createAnvil(miningLevel);
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

        public Builder createOre(int miningLevel) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.ore = new OreBlock(settings);
            return this;
        }

        public Builder createOre(int miningLevel, UniformIntProvider experience) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.ore = new OreBlock(settings, experience);
            return this;
        }

        public Builder createOreVariant(String name, int miningLevel) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, new OreBlock(settings));
            return this;
        }

        public Builder createOreVariant(String name, int miningLevel, UniformIntProvider experience) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, new OreBlock(settings, experience));
            return this;
        }

        public Builder createStarriteOre(int miningLevel, UniformIntProvider experience) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.ore = new StarriteOreBlock(settings, experience);
            return this;
        }

        public Builder createStarriteOreVariant(String name, int miningLevel, UniformIntProvider experience) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.oreVariants.put(name, new StarriteOreBlock(settings, experience));
            return this;
        }

        public Builder createStorageBlock(int miningLevel) {
            return createStorageBlock(Material.METAL, miningLevel);
        }

        public Builder createStorageBlock(Material material, int miningLevel) {
            final var settings = blockSettings(material, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.storageBlock = new Block(settings);
            return this;
        }

        public Builder createFallingStorageBlock(Material material, int miningLevel) {
            final var settings = blockSettings(material, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.storageBlock = new FallingBlock(settings);
            return this;
        }

        public Builder createOreStorageBlock(int miningLevel) {
            final var settings = blockSettings(Material.STONE, currentHardness, currentResistance, currentSounds, miningLevel);
            settingsProcessor.accept(settings);
            this.oreStorageBlock = new Block(settings);
            return this;
        }

        public Builder createAnvil(int miningLevel) {
            final var settings = blockSettings(Material.REPAIR_STATION, 5.0f, 15000f, BlockSoundGroup.ANVIL, miningLevel);
            settingsProcessor.accept(settings);
            this.anvil = new AnvilBlock(settings);
            return this;
        }

        public BlockSet finish() {
            final var set = new BlockSet(this.name, this.ore, this.storageBlock, this.oreStorageBlock, this.anvil, this.oreVariants, this.fireproof);
            Builder.toBeRegistered.add(set);
            return set;
        }

    }
}
