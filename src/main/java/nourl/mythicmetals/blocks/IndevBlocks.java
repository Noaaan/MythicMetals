package nourl.mythicmetals.blocks;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;

import java.lang.reflect.Field;

public class IndevBlocks implements BlockRegistryContainer {

    public static final Block AQUARIUM_GLASS = new GlassBlock(FabricBlockSettings.copyOf(Blocks.BLUE_STAINED_GLASS));
    public static final AquariumResonatorBlock AQUARIUM_RESONATOR = new AquariumResonatorBlock(FabricBlockSettings.copyOf(Blocks.CONDUIT));
    public static final AquariumStewardBlock AQUARIUM_STEWARD = new AquariumStewardBlock(FabricBlockSettings.copyOf(Blocks.CONDUIT));

    @Override
    public boolean shouldProcessField(Block value, String identifier, Field field) {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
