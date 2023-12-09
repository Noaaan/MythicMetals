package nourl.mythicmetals.registry;

import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.BlockEntityType;
import nourl.mythicmetals.blocks.*;
import java.lang.reflect.Field;

public class RegisterBlockEntityTypes implements BlockEntityRegistryContainer {

    public static final BlockEntityType<AquariumStewardBlockEntity> AQUARIUM_STEWARD =
            FabricBlockEntityTypeBuilder.create(AquariumStewardBlockEntity::new, MythicBlocks.Indev.AQUARIUM_STEWARD).build();
    public static final BlockEntityType<AquariumResonatorBlockEntity> AQUARIUM_RESONATOR =
            FabricBlockEntityTypeBuilder.create(AquariumResonatorBlockEntity::new, MythicBlocks.Indev.AQUARIUM_RESONATOR).build();

    public static final BlockEntityType<EnchantedMidasGoldBlockEntity> ENCHANTED_MIDAS_GOLD_BLOCK =
            FabricBlockEntityTypeBuilder.create(EnchantedMidasGoldBlockEntity::new, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK).build();

    @Override
    public boolean shouldProcessField(BlockEntityType<?> value, String identifier, Field field) {
        if (value.equals(AQUARIUM_STEWARD) || value.equals(AQUARIUM_RESONATOR)) {
            return FabricLoader.getInstance().isDevelopmentEnvironment();
        }
        return BlockEntityRegistryContainer.super.shouldProcessField(value, identifier, field);
    }
}
