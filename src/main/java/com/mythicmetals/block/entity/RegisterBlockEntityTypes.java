package com.mythicmetals.block.entity;

import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class RegisterBlockEntityTypes implements BlockEntityRegistryContainer {

    public static final BlockEntityType<AquariumResonatorBlockEntity> AQUARIUM_RESONATOR = FabricBlockEntityTypeBuilder
        .create(AquariumResonatorBlockEntity::new, MythicMaterials.AQUARIUM.extraBlocks().get(MythicResourceKeys.AQUARIUM_RESONATOR)).build();

    public static final BlockEntityType<EnchantedMidasGoldBlockEntity> ENCHANTED_MIDAS_GOLD_BLOCK =
        FabricBlockEntityTypeBuilder.create(EnchantedMidasGoldBlockEntity::new, MythicMaterials.MIDAS_GOLD.extraBlocks().get(MythicResourceKeys.ENCHANTED_MIDAS_GOLD_BLOCK)).build();

    public static final BlockEntityType<CarmotBellBlockEntity> CARMOT_BELL_BLOCK =
        FabricBlockEntityTypeBuilder.create(CarmotBellBlockEntity::new, MythicMaterials.CARMOT.extraBlocks().get(MythicResourceKeys.CARMOT_BELL)).build();
}
