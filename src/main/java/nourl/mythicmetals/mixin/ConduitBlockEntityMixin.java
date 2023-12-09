package nourl.mythicmetals.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.entity.ConduitBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterestStorage;
import nourl.mythicmetals.blocks.ConduitPowered;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.registry.RegisterPointOfInterests;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(ConduitBlockEntity.class)
public class ConduitBlockEntityMixin {

    @Mutable @Shadow @Final private static Block[] ACTIVATING_BLOCKS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void mythicmetals$extendConduitArray(CallbackInfo ci) {
        List<Block> blocks = Arrays.stream(ACTIVATING_BLOCKS).collect(Collectors.toList());
        blocks.add(MythicBlocks.Indev.AQUARIUM_GLASS);
        blocks.add(MythicBlocks.AQUARIUM.getStorageBlock());

        ACTIVATING_BLOCKS = blocks.toArray(ACTIVATING_BLOCKS);
    }

    @Inject(method = "givePlayersEffects", at = @At("TAIL"))
    private static void mythicmetals$invokeNearbySentries(World world, BlockPos pos, List<BlockPos> activatingBlocks, CallbackInfo ci) {
        if (world.isClient) return;
        int radius = activatingBlocks.size() / 7 * 16;
        ((ServerWorld)world).getPointOfInterestStorage()
            .getInCircle(type -> type.value() == RegisterPointOfInterests.CONDUIT_POWERED_BLOCK, pos, radius, PointOfInterestStorage.OccupationStatus.ANY)
            .forEach(pointOfInterest -> {
                var blockEntity = world.getBlockEntity(pointOfInterest.getPos());
                if (blockEntity instanceof ConduitPowered conduitPowered) {
                    conduitPowered.activate();
                }
            });
    }
}
