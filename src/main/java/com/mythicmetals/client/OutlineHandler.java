package com.mythicmetals.client;

import com.mythicmetals.client.rendering.BlockOutlineOverrideRenderState;
import com.mythicmetals.item.tools.HammerBase;
import com.mythicmetals.misc.BlockBreaker;
import io.wispforest.owo.ui.core.Color;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;

public class OutlineHandler {
    /**
     * Registers events required for rendering the outline of a {@link HammerBase hammer item.}
     */
    static void renderHammerOutline() {
        LevelRenderEvents.AFTER_BLOCK_OUTLINE_EXTRACTION.register((context, hitResult) -> {
            if (hitResult == null) return;
            if (!(hitResult instanceof BlockHitResult blockHitResult)) return;
            if (!hitResult.getType().equals(HitResult.Type.MISS)) {
                var hammerRenderState = context.levelState().getData(MythicRenderStateKeys.ORICHALCUM_HAMMER_RENDER_STATE);
                if (hammerRenderState == null) return;

                var direction = blockHitResult.getDirection().getOpposite();
                var middleBlockPos = blockHitResult.getBlockPos();
                if (context.level().getBlockState(middleBlockPos).isAir()) return;
                var blockPositions = BlockBreaker.findBlocks(direction, middleBlockPos, 1);
                var outlinePositions = new ArrayList<BlockPos>();
                for (var pos : blockPositions) {
                    if (HammerBase.canBreakBlock(hammerRenderState.toolComponent(), context.level(), pos)) {
                        outlinePositions.add(pos.immutable());
                    }
                }
                context.levelState().setData(MythicRenderStateKeys.OUTLINE_OVERRIDE_RENDER_STATE_KEY, new BlockOutlineOverrideRenderState(
                    middleBlockPos,
                    outlinePositions
                ));
            }
        });
        LevelRenderEvents.BEFORE_BLOCK_OUTLINE.register((levelRenderContext, _) -> {
            var outlineOverrideState = levelRenderContext.levelState().getData(MythicRenderStateKeys.OUTLINE_OVERRIDE_RENDER_STATE_KEY);
            if (outlineOverrideState == null || outlineOverrideState.positions().isEmpty()) return true;

            var clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null) return true;
            if (Minecraft.getInstance().getCameraEntity() == null) return true;

            // Create VoxelShapes out of the block positions and put them in a list
            var voxels = new ArrayList<VoxelShape>();
            final var originalPos = outlineOverrideState.original();
            for (var blockPos : outlineOverrideState.positions()) {
                var blockState = clientLevel.getBlockState(blockPos);
                voxels.add(
                    blockState
                        .getShape(clientLevel, blockPos)
                        .move(
                            blockPos.getX() - originalPos.getX(),
                            blockPos.getY() - originalPos.getY(),
                            blockPos.getZ() - originalPos.getZ()
                        )
                );
            }

            // Combine and render the full shape
            var outlineOptional = voxels.stream().reduce(Shapes::or);
            var outlineShape = outlineOptional.get();

            var cameraPos = levelRenderContext.levelState().cameraRenderState.pos;
            ShapeRenderer.renderShape(
                levelRenderContext.poseStack(),
                levelRenderContext.bufferSource().getBuffer(RenderTypes.lines()),
                outlineShape,
                originalPos.getX() - cameraPos.x(),
                originalPos.getY() - cameraPos.y(),
                originalPos.getZ() - cameraPos.z(),
                Color.ofHsv(0, 0, 0, 0.4f).argb(),
                7.0f
            );
            // Cancel the event to prevent the middle outline from rendering
            return false;
        });
    }
}
