package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import nourl.mythicmetals.abilities.Ability;
import nourl.mythicmetals.armor.HallowedArmor;
import nourl.mythicmetals.blocks.BanglumNukeEntityRenderer;
import nourl.mythicmetals.blocks.BanglumTntEntityRenderer;
import nourl.mythicmetals.mixin.WorldRendererInvoker;
import nourl.mythicmetals.client.models.*;
import nourl.mythicmetals.registry.RegisterEntities;
import nourl.mythicmetals.tools.BanglumPick;
import nourl.mythicmetals.tools.BanglumShovel;
import nourl.mythicmetals.tools.HammerBase;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.utils.BlockBreaker;
import nourl.mythicmetals.utils.RegistryHelper;
import nourl.mythicmetals.utils.ShieldUsePredicate;
import nourl.mythicmetals.utils.SlowlyMoreUsefulSingletonForColorUtil;

import java.util.ArrayList;
import java.util.Calendar;

public class MythicMetalsClient implements ClientModInitializer {

    @SuppressWarnings("unchecked")
    @Override
    public void onInitializeClient() {
        Ability.initMidasGoldTooltip();
        MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        renderHammerOutline();
        registerArmorRenderer();
        registerModelPredicates();

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, entityRendererFactoryCtx) -> {
            if (entityType != EntityType.PLAYER) return;
            registrationHelper.register(
                    new PlayerEnergySwirlFeatureRenderer(
                            (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) entityRenderer,
                            entityRendererFactoryCtx.getModelLoader()));
        });


        EntityRendererRegistry.register(RegisterEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.BANGLUM_NUKE_ENTITY_TYPE, BanglumNukeEntityRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, StarPlatinumArrowEntityRenderer::new);
        EntityRendererRegistry.register(RegisterEntities.RUNITE_ARROW_ENTITY_TYPE, RuniteArrowEntityRenderer::new);

        BuiltinItemRendererRegistry.INSTANCE.register(MythicTools.CARMOT_STAFF, new CarmotStaffBlockRenderer());
        ModelLoadingRegistry.INSTANCE.registerModelProvider(new CarmotStaffBlockRenderer());
        ColorProviderRegistry.ITEM.register(SlowlyMoreUsefulSingletonForColorUtil::potionColor, MythicTools.TIPPED_RUNITE_ARROW);
    }

    /**
     * Renders the outline of a {@link HammerBase hammer item.}
     */
    private void renderHammerOutline() {
        WorldRenderEvents.BLOCK_OUTLINE.register((worldRenderContext, blockOutlineContext) -> {
            if (!blockOutlineContext.entity().isPlayer()) return true;
            var player = (PlayerEntity) blockOutlineContext.entity();

            // Only render the outline if you are hovering over something the hammer can break
            if (player.getMainHandStack().getItem() instanceof HammerBase hammer
                    && !blockOutlineContext.blockState().isAir()
                    && hammer.isSuitableFor(blockOutlineContext.blockState())) {

                var reach = BlockBreaker.getReachDistance(player);
                BlockHitResult blockHitResult = (BlockHitResult) player.raycast(reach, 1, false);

                var facing = blockHitResult.getSide().getOpposite();
                var blocks = BlockBreaker.findBlocks(facing, blockOutlineContext.blockPos(), hammer.getDepth());
                var originalPos = blockOutlineContext.blockPos();

                // Create VoxelShapes out of the block positions and put them in a list
                var voxels = new ArrayList<VoxelShape>();

                for (BlockPos blockPos : blocks) {
                    var blockState = player.world.getBlockState(blockPos);
                    if (!blockState.isAir() && hammer.isSuitableFor(blockState)) {
                        voxels.add(blockState.getOutlineShape(
                                        worldRenderContext.world(),
                                        blockPos,
                                        ShapeContext.of(blockOutlineContext.entity())
                                ).offset(blockPos.getX() - originalPos.getX(),
                                        blockPos.getY() - originalPos.getY(),
                                        blockPos.getZ() - originalPos.getZ())
                        );
                    }
                }

                // Combine and render the full shape
                var outlineOptional = voxels.stream().reduce(VoxelShapes::union);
                if (outlineOptional.isEmpty()) return true;

                var outlineShape = outlineOptional.get();

                WorldRendererInvoker.mythicmetals$drawShapeOutline(
                        worldRenderContext.matrixStack(),
                        worldRenderContext.consumers().getBuffer(RenderLayer.getLines()),
                        outlineShape,
                        originalPos.getX() - blockOutlineContext.cameraX(),
                        originalPos.getY() - blockOutlineContext.cameraY(),
                        originalPos.getZ() - blockOutlineContext.cameraZ(),
                        0,0,0, 0.4F //RGBA
                );
                // Cancel the event to prevent the middle outline from rendering
                return false;
            }

            // Keep moving along if we reach this point
            return true;
        });
    }

    private void registerArmorRenderer() {
        Item[] armors = Registry.ITEM.stream()
                .filter(i -> i instanceof HallowedArmor
                        && Registry.ITEM.getKey(i).get().getValue().getNamespace().equals(MythicMetals.MOD_ID))
                .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumer, stack, entity, slot, light, original) -> {

            HallowedArmor armor = (HallowedArmor) stack.getItem();
            var model = armor.getArmorModel();
            var texture = armor.getArmorTexture(stack, slot);
            original.setAttributes(model);
            ArmorRenderer.renderPart(matrices, vertexConsumer, light, stack, model, texture);
        };
        ArmorRenderer.register(renderer, armors);
    }

    private void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(MythicTools.LEGENDARY_BANGLUM.getPickaxe(), new Identifier("is_primed"),
                (stack, world, entity, seed) -> BanglumPick.getCooldown(entity, stack) ? 0 : 1);

        ModelPredicateProviderRegistry.register(MythicTools.LEGENDARY_BANGLUM.getShovel(), new Identifier("is_primed"),
                (stack, world, entity, seed) -> BanglumShovel.getCooldown(entity, stack) ? 0 : 1);

        ModelPredicateProviderRegistry.register(MythicTools.STORMYX_SHIELD, new Identifier("blocking"), new ShieldUsePredicate());

        ModelPredicateProviderRegistry.register(RegistryHelper.id("funny_day"), (stack, world, entity, seed) ->
                (Calendar.getInstance().get(Calendar.MONTH) == Calendar.APRIL && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1
                        && !MythicMetals.CONFIG.disableFunny()) ? 1 : 0);
    }


}
