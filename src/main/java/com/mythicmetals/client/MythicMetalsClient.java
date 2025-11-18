package com.mythicmetals.client;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.armor.CustomArmorModelItem;
import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.client.models.CustomArmorModel;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.properties.*;
import com.mythicmetals.client.rendering.*;
import com.mythicmetals.compat.IsometricArmorStandExporter;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.PrometheumComponent;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.item.tools.HammerBase;
import com.mythicmetals.item.tools.MythrilDrill;
import com.mythicmetals.misc.*;
import io.wispforest.owo.ui.core.Color;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.minecraft.client.render.item.property.numeric.NumericProperties;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import java.util.ArrayList;

public class MythicMetalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        renderHammerOutline();
        registerArmorRenderer();
        registerModelPredicates();
        registerSwirlRenderer();

        // FIXME
//        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(player -> !CelestiumElytra.isWearing(player.skinTextures.elytraTexture()));

        EntityRendererRegistry.register(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, PalladiumMinecartRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, BanglumTntMinecartEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE, BanglumNukeEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, StarPlatinumArrowEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, RuniteArrowEntityRenderer::new);

        BlockEntityRendererFactories.register(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK, EnchantedMidasBlockEntityRenderer::new);

        CarmotShieldHudHandler.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> CarmotShieldHudHandler.tick());

        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.CARMOT_BELL_BLOCK, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.PALLADIUM_RAIL, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.AQUARIUM_GLASS, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), MythicBlocks.KYBER.getStorageBlock());

        if (FabricLoader.getInstance().isModLoaded("isometric-renders")) {
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
                IsometricArmorStandExporter.register(dispatcher);
            });
        }

        registerTooltipCallbacks();
    }

    @SuppressWarnings("unchecked")
    private void registerSwirlRenderer() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType != EntityType.PLAYER) return;
            registrationHelper.register(new PlayerEnergySwirlFeatureRenderer(entityRenderer, context.getEntityModels()));
        });
    }

    /**
     * Renders the outline of a {@link HammerBase hammer item.}
     */
    private void renderHammerOutline() {
        WorldRenderEvents.BLOCK_OUTLINE.register((worldRenderContext, blockOutlineContext) -> {
            if (!blockOutlineContext.entity().isPlayer()) return true;
            var player = (PlayerEntity) blockOutlineContext.entity();

            // Only render the outline if you are hovering over something the hammer can break
            var stack = player.getMainHandStack();
            if (stack.getItem() instanceof HammerBase hammer
                && !blockOutlineContext.blockState().isAir()
                && hammer.isCorrectForDrops(stack, blockOutlineContext.blockState())) {

                var reach = BlockBreaker.getReachDistance(player);
                BlockHitResult blockHitResult = (BlockHitResult) player.raycast(reach, 1, false);

                var facing = blockHitResult.getSide().getOpposite();
                var blocks = BlockBreaker.findBlocks(facing, blockOutlineContext.blockPos(), hammer.getDepth());
                var originalPos = blockOutlineContext.blockPos();

                // Create VoxelShapes out of the block positions and put them in a list
                var voxels = new ArrayList<VoxelShape>();

                for (BlockPos blockPos : blocks) {
                    var blockState = player.getWorld().getBlockState(blockPos);
                    if (!blockState.isAir() && hammer.isCorrectForDrops(stack, blockState)) {
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

                VertexRendering.drawOutline(
                    worldRenderContext.matrixStack(),
                    worldRenderContext.consumers().getBuffer(RenderLayer.getLines()),
                    outlineShape,
                    originalPos.getX() - blockOutlineContext.cameraX(),
                    originalPos.getY() - blockOutlineContext.cameraY(),
                    originalPos.getZ() - blockOutlineContext.cameraZ(),
                    Color.ofHsv(0, 0, 0, 0.4f).argb()
                );
                // Cancel the event to prevent the middle outline from rendering
                return false;
            }

            // Keep moving along if we reach this point
            return true;
        });
    }

    private void registerArmorRenderer() {
        Item[] armors = Registries.ITEM.stream()
            .filter(i -> i instanceof CustomArmorModelItem
                && Registries.ITEM.getKey(i).get().getValue().getNamespace().equals(MythicMetals.MOD_ID))
            .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumerProvider, stack, bipedEntityRenderState, slot, light, contextModel) -> {
            var trimAtlas = MinecraftClient.getInstance().getSpriteAtlas(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE);
            var armorItem = (CustomArmorModelItem) stack.getItem();
            var model = armorItem.getArmorModel();
            var customModelData = (CustomArmorModel) model;
            customModelData.setVisibility(slot);
            var texture = armorItem.getArmorTexture(stack, slot);
            contextModel.copyTransforms(model);
            ArmorRenderer.renderPart(matrices, vertexConsumerProvider, light, stack, model, texture);

            // Armor trim handling for custom armor models
            var armorTrim = stack.get(DataComponentTypes.TRIM);
            if (armorTrim != null) {
                var layer = slot == EquipmentSlot.LEGS ? EquipmentModel.LayerType.HUMANOID_LEGGINGS : EquipmentModel.LayerType.HUMANOID;
                var assetId = armorTrim.pattern().value().assetId();
                var assetName = armorTrim.material().value().assetName();
                var trimTexture = assetId.withPath(path -> "trims/entity/" + layer.asString() + "/" + path + "_" + assetName);
                var sprite = trimAtlas.apply(trimTexture);
                var trimVertexConsumer = sprite.getTextureSpecificVertexConsumer(
                    vertexConsumerProvider.getBuffer(TexturedRenderLayers.getArmorTrims(armorTrim.pattern().value().decal()))
                );
                model.render(matrices, trimVertexConsumer, light, OverlayTexture.DEFAULT_UV);
            }
        };
        ArmorRenderer.register(renderer, armors);
    }

    private void registerModelPredicates() {
        NumericProperties.ID_MAPPER.put(RegistryHelper.id("time"), TrueTimeProperty.CODEC);
        NumericProperties.ID_MAPPER.put(RegistryHelper.id("midas_gold"), MidasGoldProperty.CODEC);
        BooleanProperties.ID_MAPPER.put(RegistryHelper.id("has_drill_fuel"), HasDrillFuelProperty.CODEC);
        // TODO
//        ModelPredicateProviderRegistry.register(RegistryHelper.id("funny_day"), (stack, world, entity, seed) ->
//            (StringUtilsAtHome.isFunnyDay()) ? 1 : 0);
//

    }

    public void registerTooltipCallbacks() {
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            var item = stack.getItem();
            int index = 1;

            if (stack.isIn(MythicTags.BONUS_FORTUNE)) {
                lines.add(index, Text.translatable("abilities.mythicmetals.bonus_fortune").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (stack.isIn(MythicTags.BONUS_LOOTING)) {
                lines.add(index, Text.translatable("abilities.mythicmetals.bonus_looting").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (item.equals(MythicItems.Mats.BANGLUM_CHUNK) || item.equals(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem())) {
                lines.add(index, Text.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.GOLD_STYLE));
            }
            if (item.equals(MythicItems.Mats.AQUARIUM_PEARL)) {
                lines.add(index, Text.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.AQUA_STYLE));
            }
            if (item.equals(MythicItems.Mats.CARMOT_STONE)) {
                lines.add(index, Text.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.CARMOT_STYLE));
            }
            if (item.equals(MythicItems.Mats.STORMYX_SHELL)) {
                lines.add(index, Text.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").formatted(Formatting.LIGHT_PURPLE));
            }
            if (MythrilDrill.drillUpgrades.containsKey(stack.getItem())) {
                lines.add(index, Text.translatable("tooltip.mythril_drill.upgrade").withColor(UsefulSingletonForColorUtil.MetalColors.MYTHRIL.rgb()));
            }

            if (lines.size() > 2) {
                index += stack.getEnchantments().getSize();
            }

            if (stack.contains(MythicDataComponents.PROMETHEUM)) {
                var component = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
                if (type.isAdvanced()) {
                    lines.add(index, Text.translatable("tooltip.prometheum.repaired", component.durabilityRepaired())
                        .withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb())
                    );
                }

                lines.add(index, Text.translatable("tooltip.prometheum.regrowth").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                if (component.isOvergrown()) {
                    lines.add(index, Text.translatable("tooltip.prometheum.overgrown").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
                if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, EnchantmentEffectComponentTypes.PREVENT_ARMOR_CHANGE)) {
                    lines.add(index, Text.translatable("tooltip.prometheum.engrained").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
            }
        });

    }

}
