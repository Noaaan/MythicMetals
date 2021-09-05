package nourl.mythicmetals.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.utils.RegistryHelper;

public class BlockSet {

    private final OreBlock ore;
    private final Block storageBlock;
    private final Block rawOreBlock;

    private Block baseBlock(float strength, float resistance, BlockSoundGroup sounds, int miningLevel) {
        return new Block(FabricBlockSettings.of(Material.METAL).strength(strength, resistance).sounds(sounds).breakByTool(FabricToolTags.PICKAXES, miningLevel).requiresTool());
    }

    private OreBlock oreBlock(float strength, float resistance, BlockSoundGroup sounds, int miningLevel) {
        return new OreBlock(FabricBlockSettings.of(Material.STONE).strength(strength, resistance).sounds(sounds).breakByTool(FabricToolTags.PICKAXES, miningLevel).requiresTool());
    }

    public BlockSet(float strength, float[] resistance, BlockSoundGroup oreSounds, BlockSoundGroup blockSounds, int[] miningLevel) {
        this.ore = oreBlock(strength, resistance[0], oreSounds, miningLevel[0]);
        this.rawOreBlock = baseBlock(strength, resistance[1], blockSounds, miningLevel[1]);
        this.storageBlock = baseBlock(strength, resistance[2], blockSounds, miningLevel[1]);
    }

    public void register(String name) {
        RegistryHelper.block(name + "_ore", ore, MythicMetals.MYTHICMETALS);
        RegistryHelper.block("raw_" + name + "_block", storageBlock, MythicMetals.MYTHICMETALS);
        RegistryHelper.block(name + "_block", rawOreBlock, MythicMetals.MYTHICMETALS);
    }

    public void register(String name, boolean fireproof) {
        if (fireproof) {
            RegistryHelper.block(name + "_ore", ore, MythicMetals.MYTHICMETALS);
            RegistryHelper.block("raw_" + name + "_block", storageBlock, MythicMetals.MYTHICMETALS);
            RegistryHelper.block(name + "_block", rawOreBlock, MythicMetals.MYTHICMETALS);
        }
        else
            register(name);
    }

    public OreBlock getOre() {
        return ore;
    }
}
