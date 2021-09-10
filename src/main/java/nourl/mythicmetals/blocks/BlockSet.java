package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.function.Consumer;

public class BlockSet {
    private final OreBlock ore;
    private final Block storageBlock;
    private final Block oreStorageBlock;

    public FabricBlockSettings blockSettings(Material material, float hardness, float resistance, BlockSoundGroup sounds, int miningLevel) {
        return FabricBlockSettings.of(material).strength(hardness, resistance).sounds(sounds).breakByTool(FabricToolTags.PICKAXES, miningLevel).requiresTool();
    }

    private Block block(float hardness, float resistance, BlockSoundGroup sounds, int miningLevel) {
        return new Block(blockSettings(Material.METAL, hardness, resistance, sounds, miningLevel));
    }

    private static Block Block(Material material, Consumer<FabricBlockSettings> settingsProcessor) {
        final var settings = FabricBlockSettings.of(material).sounds(BlockSoundGroup.METAL);
        settingsProcessor.accept(settings);
        return new Block(settings);
    }

    private OreBlock oreBlock(float hardness, float resistance, BlockSoundGroup sounds, int miningLevel) {
        return new OreBlock(blockSettings(Material.STONE, hardness, resistance, sounds, miningLevel));
    }

    private static OreBlock OreBlock(Material material, Consumer<FabricBlockSettings> settingsProcessor) {
        final var settings = FabricBlockSettings.of(material).sounds(BlockSoundGroup.STONE);
        settingsProcessor.accept(settings);
        return new OreBlock(settings);
    }

    public BlockSet(float strength, float[] resistance, BlockSoundGroup oreSounds, BlockSoundGroup blockSounds, int[] miningLevel) {
        this.ore = oreBlock(strength, resistance[0], oreSounds, miningLevel[0]);
        this.oreStorageBlock = block(strength, resistance[1], blockSounds, miningLevel[1]);
        this.storageBlock = block(strength, resistance[2], blockSounds, miningLevel[1]);
    }

    public void register(String name) {
        RegistryHelper.block(name + "_ore", ore, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("raw_" + name + "_block", storageBlock, MythicMetals.MYTHICMETALS);
        RegistryHelper.block(name + "_block", oreStorageBlock, MythicMetals.MYTHICMETALS);
    }

    public void register(String name, boolean fireproof) {
            RegistryHelper.block(name + "_ore", ore, MythicMetals.MYTHICMETALS, fireproof);
            RegistryHelper.block("raw_" + name + "_block", storageBlock, MythicMetals.MYTHICMETALS, fireproof);
            RegistryHelper.block(name + "_block", oreStorageBlock, MythicMetals.MYTHICMETALS, fireproof);
    }

    public OreBlock getOre() {
        return ore;
    }
}
