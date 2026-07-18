package com.mythicmetals.client;

import com.mythicmetals.api.v2.client.CustomArmorModel;
import com.mythicmetals.api.v2.client.CustomArmorModelItem;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.client.properties.HasDrillFuelProperty;
import com.mythicmetals.client.properties.MidasGoldProperty;
import com.mythicmetals.client.properties.TrueTimeProperty;
import com.mythicmetals.client.rendering.*;
import com.mythicmetals.compat.IsometricArmorStandExporter;
import com.mythicmetals.data.MythicTags;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import com.mythicmetals.item.component.*;
import com.mythicmetals.item.tools.CarmotBellItem;
import com.mythicmetals.item.tools.HammerBase;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import com.mythicmetals.mixin.client.EquipmentLayerRendererAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.equipment.trim.ArmorTrim;

public class MythicMetalsClient implements ClientModInitializer {

    private boolean hasRegistered = false;

    @Override
    public void onInitializeClient() {
        MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        renderHammerOutline();
        registerModelPredicates();
        registerSwirlRenderer();

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof AvatarRenderer<?> playerRenderer) {
                registrationHelper.register(new MythicMetalsCustomFeatureRenderer(playerRenderer, context.getModelSet(), context.getEquipmentRenderer()));
            }
            if (entityType.equals(EntityType.PLAYER) && !hasRegistered) {
                var renderer = createCustomArmorRenderer(context);
                Item[] armors = BuiltInRegistries.ITEM.stream()
                    .filter(i -> i instanceof CustomArmorModelItem)
                    .toArray(Item[]::new);

                if (armors.length == 0) return;

                ArmorRenderer.register(renderer, armors);
                hasRegistered = true;
            }
        });
        EntityRendererRegistry.register(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, PalladiumMinecartRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, BanglumTntMinecartEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE, BanglumNukeEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, StarPlatinumArrowEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, RuniteArrowEntityRenderer::new);

        BlockEntityRenderers.register(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK, EnchantedMidasBlockEntityRenderer::new);

//        CarmotShieldHudHandler.init();

        // FIXME - Translucent blocks
        BlockRenderLayerMap.putBlock(MythicMaterials.PALLADIUM.extraBlocks().get(MythicResourceKeys.PALLADIUM_RAIL), ChunkSectionLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(MythicBlocks.CARMOT_BELL_BLOCK, ChunkSectionLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(MythicBlocks.PALLADIUM_RAIL, ChunkSectionLayer.SOLID);
        //BlockRenderLayerMap.putBlock(MythicBlocks.AQUARIUM_GLASS, ChunkSectionLayer.TRANSLUCENT);
        //BlockRenderLayerMap.putBlock(MythicBlocks.KYBER.getStorageBlock(), ChunkSectionLayer.TRANSLUCENT);

        if (FabricLoader.getInstance().isModLoaded("isometric-renders")) {
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
                IsometricArmorStandExporter.register(dispatcher);
            });
        }

        HudElementRegistry.addFirst(RegistryHelper.id("carmot_shield_hud"), (guiGraphics, tickCounter) -> {
            CarmotShieldHudHandler.render(guiGraphics);
        });

        registerTooltipCallbacks();
    }

    @SuppressWarnings("unchecked")
    private void registerSwirlRenderer() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            registrationHelper.register(new LivingEntityEnergySwirlFeatureRenderer(entityRenderer, context.getModelSet()));
        });
    }

    /**
     * Renders the outline of a {@link HammerBase hammer item.}
     */
    private void renderHammerOutline() {
        // FIXME - Use FabricRenderState to attach some hammer-specific params for rendering this.
//        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((worldRenderContext, blockOutlineContext) -> {
//            if (!blockOutlineContext.entity().isAlwaysTicking()) return true;
//            var player = (Player) blockOutlineContext.entity();
//            // Only render the outline if you are hovering over something the hammer can break
//            var stack = player.getMainHandItem();
//            if (stack.getItem() instanceof HammerBase hammer
//                && !blockOutlineContext.blockState().isAir()
//                && hammer.isCorrectToolForDrops(stack, blockOutlineContext.blockState())) {
//
//                var reach = BlockBreaker.getReachDistance(player);
//                BlockHitResult blockHitResult = (BlockHitResult) player.pick(reach, 1, false);
//
//                var facing = blockHitResult.getDirection().getOpposite();
//                var blocks = BlockBreaker.findBlocks(facing, blockOutlineContext.blockPos(), hammer.getDepth());
//                var originalPos = blockOutlineContext.blockPos();
//
//                // Create VoxelShapes out of the block positions and put them in a list
//                var voxels = new ArrayList<VoxelShape>();
//
//                for (BlockPos blockPos : blocks) {
//                    var blockState = player.level().getBlockState(blockPos);
//                    if (!blockState.isAir() && hammer.isCorrectToolForDrops(stack, blockState)) {
//                        voxels.add(blockState.getShape(
//                                worldRenderContext.world(),
//                                blockPos,
//                                CollisionContext.of(blockOutlineContext.entity())
//                            ).move(blockPos.getX() - originalPos.getX(),
//                                blockPos.getY() - originalPos.getY(),
//                                blockPos.getZ() - originalPos.getZ())
//                        );
//                    }
//                }
//
//                // Combine and render the full shape
//                var outlineOptional = voxels.stream().reduce(Shapes::or);
//                if (outlineOptional.isEmpty()) return true;
//
//                var outlineShape = outlineOptional.get();
//
//                ShapeRenderer.renderShape(
//                    worldRenderContext.matrixStack(),
//                    worldRenderContext.consumers().getBuffer(RenderType.lines()),
//                    outlineShape,
//                    originalPos.getX() - blockOutlineContext.cameraX(),
//                    originalPos.getY() - blockOutlineContext.cameraY(),
//                    originalPos.getZ() - blockOutlineContext.cameraZ(),
//                    Color.ofHsv(0, 0, 0, 0.4f).argb()
//                );
//                // Cancel the event to prevent the middle outline from rendering
//                return false;
//            }
//
//            // Keep moving along if we reach this point
//            return true;
//        });
    }

    private ArmorRenderer createCustomArmorRenderer(EntityRendererProvider.Context context) {
        return (poseStack, submitNodeCollector, stack, bipedEntityRenderState, slot, light, contextModel) -> {
            var armorItem = (CustomArmorModelItem) stack.getItem();
            var model = armorItem.getArmorModel();
            var customModelData = (CustomArmorModel) model;
            customModelData.setVisibility(slot);
            var texture = armorItem.getArmorTexture(stack, slot);
            ArmorRenderer.submitTransformCopyingModel(
                contextModel,
                bipedEntityRenderState,
                model,
                bipedEntityRenderState,
                false,
                submitNodeCollector,
                poseStack,
                RenderTypes.armorCutoutNoCull(texture),
                light,
                bipedEntityRenderState.lightCoords,
                bipedEntityRenderState.outlineColor,
                null
            );

            // Armor trim handling for custom armor models
            ArmorTrim armorTrim = stack.get(DataComponents.TRIM);
            if (armorTrim != null) {
                var layer = slot == EquipmentSlot.LEGS ? EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS : EquipmentClientInfo.LayerType.HUMANOID;
                var sheet = Sheets.armorTrimsSheet(armorTrim.pattern().value().decal());
                var equipmentLayerRenderer = context.getEquipmentRenderer();
                var trimKey = stack.get(DataComponents.EQUIPPABLE).assetId().get();
                var sprites = ((EquipmentLayerRendererAccessor) equipmentLayerRenderer).mythicmetals$getTrimSprites().apply(
                    new EquipmentLayerRenderer.TrimSpriteKey(armorTrim, layer, trimKey)
                );
                ArmorRenderer.submitTransformCopyingModel(
                    contextModel,
                    bipedEntityRenderState,
                    model,
                    bipedEntityRenderState,
                    false,
                    submitNodeCollector,
                    poseStack,
                    sheet,
                    light,
                    bipedEntityRenderState.lightCoords,
                    -1,
                    sprites,
                    bipedEntityRenderState.outlineColor,
                    null
                );
            }
        };
    }

    private void registerModelPredicates() {
        RangeSelectItemModelProperties.ID_MAPPER.put(RegistryHelper.id("time"), TrueTimeProperty.CODEC);
        RangeSelectItemModelProperties.ID_MAPPER.put(RegistryHelper.id("midas_gold"), MidasGoldProperty.CODEC);
        ConditionalItemModelProperties.ID_MAPPER.put(RegistryHelper.id("has_drill_fuel"), HasDrillFuelProperty.CODEC);
        // TODO
//        ModelPredicateProviderRegistry.register(RegistryHelper.id("funny_day"), (stack, world, entity, seed) ->
//            (StringUtilsAtHome.isFunnyDay()) ? 1 : 0);
//

    }

    public void registerTooltipCallbacks() {
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            if (stack.getItem() instanceof CarmotBellItem) {
                lines.add(1, Component.translatable("tooltip.carmot_bell.info2"));
                lines.add(1, Component.translatable("tooltip.carmot_bell.info1"));
                return;
            }

            if (stack.is(MythicTags.BONUS_FORTUNE)) {
                lines.add(1, Component.translatable("abilities.mythicmetals.bonus_fortune").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (stack.is(MythicTags.BONUS_LOOTING)) {
                lines.add(1, Component.translatable("abilities.mythicmetals.bonus_looting").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (stack.is(MythicTags.RARE_MATERIALS)) {
                lines.add(1, Component.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.computeStyleFromStack(stack)));
            }

            if (stack.is(MythicTags.MYTHRIL_DRILL_UPGRADES)) {
                lines.add(1, Component.translatable("tooltip.mythril_drill.upgrade").withColor(UsefulSingletonForColorUtil.MetalColors.MYTHRIL.rgb()));
            }

            if (stack.is(MythicTags.FIRE_RESISTANT_ARMOR)) {
                lines.add(1, Component.translatable("tooltip.mythicmetals.fire_resistant_armor").withStyle(UsefulSingletonForColorUtil.MetalColors.PALLADIUM_STYLE));
            }

            if (stack.has(MythicDataComponents.UPGRADES)) {
                var upgradeComponent = stack.getOrDefault(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2));
                upgradeComponent.addToTooltip(context, text -> lines.add(1, text), TooltipFlag.NORMAL, stack.getComponents());
            }

            if (stack.has(MythicDataComponents.DRILL)) {
                var component = stack.getOrDefault(MythicDataComponents.DRILL, DrillComponent.DEFAULT);
                component.addToTooltip(context, text -> lines.add(1, text), TooltipFlag.NORMAL, stack.getComponents());
            }

            if (stack.has(MythicDataComponents.BLAST_MINING)) {
                var component = stack.getOrDefault(MythicDataComponents.BLAST_MINING, new BlastMiningComponent(0));
                component.addToTooltip(context, text -> lines.add(1, text), TooltipFlag.NORMAL, stack.getComponents());
            }

            if (stack.has(MythicDataComponents.BRANDING)) {
                var component = stack.getOrDefault(MythicDataComponents.BRANDING, new BrandingComponent(0));
                component.addToTooltip(context, text -> {
                    lines.add(1, text);
                }, TooltipFlag.NORMAL, stack.getComponents());
            }

            if (stack.has(MythicDataComponents.PROMETHEUM)) {
                var component = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
                if (type.isAdvanced()) {
                    lines.add(1, Component.translatable("tooltip.prometheum.repaired", component.durabilityRepaired())
                        .withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb())
                    );
                }

                lines.add(1, Component.translatable("tooltip.prometheum.regrowth").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                if (component.isOvergrown()) {
                    lines.add(1, Component.translatable("tooltip.prometheum.overgrown").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
                if (EnchantmentHelper.has(stack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                    lines.add(1, Component.translatable("tooltip.prometheum.engrained").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
            }

            if (stack.has(MythicDataComponents.TIDESINGER)) {
                var component = stack.get(MythicDataComponents.TIDESINGER);
                component.addToTooltip(context, text -> lines.add(1, text), TooltipFlag.NORMAL, stack.getComponents());
            }
        });

        // TODO - Review if anything should go below enchantments, if so comment out this code
        //if (lines.size() > 2) {
        //    index += stack.getEnchantments().size();
        //}

    }

}
