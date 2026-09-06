package com.mythicmetals.mixin;

import com.mythicmetals.block.ConduitPowered;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.MythicPOIs;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {

    @Mutable
    @Shadow
    @Final
    private static Block[] VALID_BLOCKS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void mythicmetals$extendConduitArray(CallbackInfo ci) {
        List<Block> blocks = Arrays.stream(VALID_BLOCKS).collect(Collectors.toList());
        blocks.add(MythicMaterials.AQUARIUM.blockSet().storage().block());
        blocks.add(MythicMaterials.AQUARIUM.extraBlocks().get(RegistryHelper.blockKey("aquarium_glass")));

        VALID_BLOCKS = blocks.toArray(VALID_BLOCKS);
    }

    @Inject(method = "applyEffects", at = @At("TAIL"))
    private static void mythicmetals$invokeNearbySentries(Level level, BlockPos worldPosition, List<BlockPos> effectBlocks, CallbackInfo ci) {
        if (level.isClientSide()) return;
        int radius = effectBlocks.size() / 7 * 16;
        ((ServerLevel) level).getPoiManager()
            .getInSquare(type -> type.value() == MythicPOIs.CONDUIT_POWERED_BLOCK, worldPosition, radius, PoiManager.Occupancy.ANY)
            .forEach(pointOfInterest -> {
                var blockEntity = level.getBlockEntity(pointOfInterest.getPos());
                if (blockEntity instanceof ConduitPowered conduitPowered) {
                    conduitPowered.activate();
                }
            });
    }

}
