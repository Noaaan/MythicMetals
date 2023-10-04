package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import nourl.mythicmetals.blocks.AquariumResonatorBlockEntity;
import nourl.mythicmetals.blocks.AquariumStewardBlockEntity;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.blocks.EnchantedMidasGoldBlockEntity;
import nourl.mythicmetals.misc.RegistryHelper;

public class RegisterBlockEntityTypes {


    public static final BlockEntityType<AquariumStewardBlockEntity> AQUARIUM_STEWARD_BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(AquariumStewardBlockEntity::new, MythicBlocks.AQUARIUM_STEWARD).build();
    public static final BlockEntityType<AquariumResonatorBlockEntity> AQUARIUM_RESONATOR_BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(AquariumResonatorBlockEntity::new, MythicBlocks.AQUARIUM_RESONATOR).build();

    public static final BlockEntityType<EnchantedMidasGoldBlockEntity> ENCHANTED_MIDAS_GOLD_BLOCK_ENTITY_TYPE =
            FabricBlockEntityTypeBuilder.create(EnchantedMidasGoldBlockEntity::new, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK).build();

    public static void init() {
        RegistryHelper.blockEntity("aquarium_steward", RegisterBlockEntityTypes.AQUARIUM_STEWARD_BLOCK_ENTITY_TYPE);
        RegistryHelper.blockEntity("aquarium_resonator", RegisterBlockEntityTypes.AQUARIUM_RESONATOR_BLOCK_ENTITY_TYPE);
        RegistryHelper.blockEntity("enchanted_midas_gold_block", RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK_ENTITY_TYPE);
    }
}
