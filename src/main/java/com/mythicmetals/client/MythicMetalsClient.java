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
import com.mythicmetals.component.*;
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
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;
import java.util.ArrayList;

public class MythicMetalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        renderHammerOutline();
        registerArmorRenderer();
        registerModelPredicates();
        registerSwirlRenderer();

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof PlayerRenderer playerRenderer) {
                registrationHelper.register(new MythicMetalsCustomFeatureRenderer(playerRenderer, context.getModelSet(), context.getEquipmentRenderer()));
            }
        });
        EntityRendererRegistry.register(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, PalladiumMinecartRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, BanglumTntMinecartEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE, BanglumNukeEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, StarPlatinumArrowEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, RuniteArrowEntityRenderer::new);

        BlockEntityRenderers.register(RegisterBlockEntityTypes.ENCHANTED_MIDAS_GOLD_BLOCK, EnchantedMidasBlockEntityRenderer::new);

        CarmotShieldHudHandler.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> CarmotShieldHudHandler.tick());

        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.CARMOT_BELL_BLOCK, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.PALLADIUM_RAIL, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(MythicBlocks.AQUARIUM_GLASS, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), MythicBlocks.KYBER.getStorageBlock());

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
            registrationHelper.register(new PlayerEnergySwirlFeatureRenderer(entityRenderer, context.getModelSet()));
        });
    }

    /**
     * Renders the outline of a {@link HammerBase hammer item.}
     */
    private void renderHammerOutline() {
        WorldRenderEvents.BLOCK_OUTLINE.register((worldRenderContext, blockOutlineContext) -> {
            if (!blockOutlineContext.entity().isAlwaysTicking()) return true;
            var player = (Player) blockOutlineContext.entity();

            // Only render the outline if you are hovering over something the hammer can break
            var stack = player.getMainHandItem();
            if (stack.getItem() instanceof HammerBase hammer
                && !blockOutlineContext.blockState().isAir()
                && hammer.isCorrectToolForDrops(stack, blockOutlineContext.blockState())) {

                var reach = BlockBreaker.getReachDistance(player);
                BlockHitResult blockHitResult = (BlockHitResult) player.pick(reach, 1, false);

                var facing = blockHitResult.getDirection().getOpposite();
                var blocks = BlockBreaker.findBlocks(facing, blockOutlineContext.blockPos(), hammer.getDepth());
                var originalPos = blockOutlineContext.blockPos();

                // Create VoxelShapes out of the block positions and put them in a list
                var voxels = new ArrayList<VoxelShape>();

                for (BlockPos blockPos : blocks) {
                    var blockState = player.level().getBlockState(blockPos);
                    if (!blockState.isAir() && hammer.isCorrectToolForDrops(stack, blockState)) {
                        voxels.add(blockState.getShape(
                                worldRenderContext.world(),
                                blockPos,
                                CollisionContext.of(blockOutlineContext.entity())
                            ).move(blockPos.getX() - originalPos.getX(),
                                blockPos.getY() - originalPos.getY(),
                                blockPos.getZ() - originalPos.getZ())
                        );
                    }
                }

                // Combine and render the full shape
                var outlineOptional = voxels.stream().reduce(Shapes::or);
                if (outlineOptional.isEmpty()) return true;

                var outlineShape = outlineOptional.get();

                ShapeRenderer.renderShape(
                    worldRenderContext.matrixStack(),
                    worldRenderContext.consumers().getBuffer(RenderType.lines()),
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
        Item[] armors = BuiltInRegistries.ITEM.stream()
            .filter(i -> i instanceof CustomArmorModelItem
                && BuiltInRegistries.ITEM.getResourceKey(i).get().identifier().getNamespace().equals(MythicMetals.MOD_ID))
            .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumerProvider, stack, bipedEntityRenderState, slot, light, contextModel) -> {
            var trimAtlas = Minecraft.getInstance().getTextureAtlas(Sheets.ARMOR_TRIMS_SHEET);
            var armorItem = (CustomArmorModelItem) stack.getItem();
            var model = armorItem.getArmorModel();
            var customModelData = (CustomArmorModel) model;
            customModelData.setVisibility(slot);
            var texture = armorItem.getArmorTexture(stack, slot);
            contextModel.copyPropertiesTo(model);
            ArmorRenderer.renderPart(matrices, vertexConsumerProvider, light, stack, model, texture);

            // Armor trim handling for custom armor models
            var armorTrim = stack.get(DataComponents.TRIM);
            if (armorTrim != null) {
                var layer = slot == EquipmentSlot.LEGS ? EquipmentClientInfo.LayerType.HUMANOID_LEGGINGS : EquipmentClientInfo.LayerType.HUMANOID;
                var assetId = armorTrim.pattern().value().assetId();
                var assetName = armorTrim.material().value().assetName();
                var trimTexture = assetId.withPath(path -> "trims/entity/" + layer.getSerializedName() + "/" + path + "_" + assetName);
                var sprite = trimAtlas.apply(trimTexture);
                var trimVertexConsumer = sprite.wrap(
                    vertexConsumerProvider.getBuffer(Sheets.armorTrimsSheet(armorTrim.pattern().value().decal()))
                );
                model.renderToBuffer(matrices, trimVertexConsumer, light, OverlayTexture.NO_OVERLAY);
            }
        };
        ArmorRenderer.register(renderer, armors);
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
            var item = stack.getItem();
            int index = 1;

            if (stack.is(MythicTags.BONUS_FORTUNE)) {
                lines.add(index, Component.translatable("abilities.mythicmetals.bonus_fortune").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (stack.is(MythicTags.BONUS_LOOTING)) {
                lines.add(index, Component.translatable("abilities.mythicmetals.bonus_looting").withColor(UsefulSingletonForColorUtil.MetalColors.CARMOT.rgb()));
            }

            if (item.equals(MythicItems.Mats.BANGLUM_CHUNK) || item.equals(MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK.asItem())) {
                lines.add(index, Component.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.GOLD_STYLE));
            }
            if (item.equals(MythicItems.Mats.AQUARIUM_PEARL)) {
                lines.add(index, Component.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.AQUA_STYLE));
            }
            if (item.equals(MythicItems.Mats.CARMOT_STONE)) {
                lines.add(index, Component.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").setStyle(UsefulSingletonForColorUtil.MetalColors.CARMOT_STYLE));
            }
            if (item.equals(MythicItems.Mats.STORMYX_SHELL)) {
                lines.add(index, Component.translatable("tooltip.mythicmetals.rare_crafting_material_tooltip").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
            if (MythrilDrill.drillUpgrades.containsKey(stack.getItem())) {
                lines.add(index, Component.translatable("tooltip.mythril_drill.upgrade").withColor(UsefulSingletonForColorUtil.MetalColors.MYTHRIL.rgb()));
            }

            if (stack.has(MythicDataComponents.BRANDING)) {
                var component = stack.getOrDefault(MythicDataComponents.BRANDING, new BrandingComponent(0));
                int finalIndex = index;
                component.addToTooltip(context, text -> {
                    lines.add(finalIndex, text);
                }, TooltipFlag.NORMAL);
            }

            if (lines.size() > 2) {
                index += stack.getEnchantments().size();
            }

            if (stack.has(MythicDataComponents.PROMETHEUM)) {
                var component = stack.getOrDefault(MythicDataComponents.PROMETHEUM, PrometheumComponent.DEFAULT);
                if (type.isAdvanced()) {
                    lines.add(index, Component.translatable("tooltip.prometheum.repaired", component.durabilityRepaired())
                        .withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb())
                    );
                }

                lines.add(index, Component.translatable("tooltip.prometheum.regrowth").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                if (component.isOvergrown()) {
                    lines.add(index, Component.translatable("tooltip.prometheum.overgrown").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
                if (EnchantmentHelper.has(stack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                    lines.add(index, Component.translatable("tooltip.prometheum.engrained").withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()));
                }
            }
        });

    }

}
