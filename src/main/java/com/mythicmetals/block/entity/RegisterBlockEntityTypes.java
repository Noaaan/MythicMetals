package com.mythicmetals.block.entity;

import io.wispforest.owo.registration.reflect.BlockEntityRegistryContainer;
import net.minecraft.block.entity.BlockEntityType;
import com.mythicmetals.block.*;
import java.lang.reflect.Field;

public class RegisterBlockEntityTypes implements BlockEntityRegistryContainer {

    public static final BlockEntityType<AquariumResonatorBlockEntity> AQUARIUM_RESONATOR =
            BlockEntityType.Builder.create(AquariumResonatorBlockEntity::new, MythicBlocks.AQUARIUM_RESONATOR).build();

    public static final BlockEntityType<EnchantedMidasGoldBlockEntity> ENCHANTED_MIDAS_GOLD_BLOCK =
        BlockEntityType.Builder.create(EnchantedMidasGoldBlockEntity::new, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK).build();

    @Override
    public boolean shouldProcessField(BlockEntityType<?> value, String identifier, Field field) {
        return BlockEntityRegistryContainer.super.shouldProcessField(value, identifier, field);
    }
}
