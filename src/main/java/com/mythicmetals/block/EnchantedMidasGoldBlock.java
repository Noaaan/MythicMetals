package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.block.entity.EnchantedMidasGoldBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class EnchantedMidasGoldBlock extends BlockWithEntity {

    public static final MapCodec<EnchantedMidasGoldBlock> CODEC = createCodec(EnchantedMidasGoldBlock::new);

    public EnchantedMidasGoldBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnchantedMidasGoldBlockEntity(pos, state);
    }

    // TODO - Review
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
