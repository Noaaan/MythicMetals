package com.mythicmetals.block.entity;

import com.mythicmetals.block.MythicBlocks;
import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import java.lang.reflect.Field;

public class RegisterBlockEntityTypes implements BlockEntityRegistryContainer {

    public static final BlockEntityType<AquariumResonatorBlockEntity> AQUARIUM_RESONATOR = FabricBlockEntityTypeBuilder
        .create(AquariumResonatorBlockEntity::new, MythicBlocks.AQUARIUM_RESONATOR).build();

    public static final BlockEntityType<EnchantedMidasGoldBlockEntity> ENCHANTED_MIDAS_GOLD_BLOCK =
        FabricBlockEntityTypeBuilder.create(EnchantedMidasGoldBlockEntity::new, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK).build();

    public static final BlockEntityType<CarmotBellBlockEntity> CARMOT_BELL_BLOCK =
        FabricBlockEntityTypeBuilder.create(CarmotBellBlockEntity::new, MythicBlocks.CARMOT_BELL_BLOCK).build();

    @Override
    public boolean shouldProcessField(BlockEntityType<?> value, String identifier, Field field) {
        return BlockEntityRegistryContainer.super.shouldProcessField(value, identifier, field);
    }
}
