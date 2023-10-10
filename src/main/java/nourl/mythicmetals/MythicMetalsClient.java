package nourl.mythicmetals;

import io.wispforest.owo.ui.util.Delta;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import nourl.mythicmetals.abilities.Ability;
import nourl.mythicmetals.armor.CelestiumElytra;
import nourl.mythicmetals.armor.HallowedArmor;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.client.CarmotShieldHudHandler;
import nourl.mythicmetals.client.models.MythicModelHandler;
import nourl.mythicmetals.client.rendering.*;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.entity.MythicEntities;
import nourl.mythicmetals.item.tools.*;
import nourl.mythicmetals.misc.BlockBreaker;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.misc.ShieldUsePredicate;
import nourl.mythicmetals.misc.UsefulSingletonForColorUtil;
import nourl.mythicmetals.mixin.WorldRendererInvoker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class MythicMetalsClient implements ClientModInitializer {
    private long lastTick;
    private float time;
    public static ModelTransformationMode mode;

    @SuppressWarnings("unchecked")
    @Override
    public void onInitializeClient() {
        MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));

        renderHammerOutline();
        registerArmorRenderer();
        registerModelPredicates();

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType != EntityType.PLAYER) return;
            registrationHelper.register(
                    new PlayerEnergySwirlFeatureRenderer(
                            (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) entityRenderer,
                            context.getModelLoader()));
        });

        ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
            if (stack.isIn(MythicTags.PROMETHEUM_TOOLS) && PrometheumToolSet.isOvergrown(stack)) {
                Ability.addTooltipOnStack(stack, lines, Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()), "tooltip.prometheum.overgrown");
            }

            if (stack.isIn(MythicTags.PROMETHEUM_ARMOR)) {
                if (PrometheumToolSet.isOvergrown(stack)) {
                    Ability.addTooltipOnStack(stack, lines, Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()), "tooltip.prometheum.overgrown");
                }

                if (EnchantmentHelper.hasBindingCurse(stack)) {
                    Ability.addTooltipOnStack(stack, lines, Style.EMPTY.withColor(UsefulSingletonForColorUtil.MetalColors.PROMETHEUM.rgb()), "tooltip.prometheum.engrained");
                }
            }
        });

        ModifyItemAttributeModifiersCallback.EVENT.register((stack, slot, attributeModifiers) -> {
            if (stack.isIn(MythicTags.PROMETHEUM_ARMOR) && ((ArmorItem) stack.getItem()).getSlotType().equals(slot)) {
                if (EnchantmentHelper.hasBindingCurse(stack)) {
                    attributeModifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
                            UUID.fromString("d42e82c8-166d-46f1-bc76-df84e91b5531"),
                            "Bound Prometheum bonus",
                            0.08,
                            EntityAttributeModifier.Operation.MULTIPLY_BASE
                    ));
                }
                if (PrometheumToolSet.isOvergrown(stack)) {
                    attributeModifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(
                            UUID.fromString("37bb6460-e896-44e2-8e71-29335d5ce709"),
                            "Prometheum bonus toughness",
                            EnchantmentHelper.hasBindingCurse(stack) ? 2 : 1,
                            EntityAttributeModifier.Operation.ADDITION
                    ));
                }
            }
        });

        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(player -> !CelestiumElytra.isWearing(player));

        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, BanglumTntMinecartEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.BANGLUM_NUKE_ENTITY_TYPE, BanglumNukeEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.STAR_PLATINUM_ARROW_ENTITY_TYPE, StarPlatinumArrowEntityRenderer::new);
        EntityRendererRegistry.register(MythicEntities.RUNITE_ARROW_ENTITY_TYPE, RuniteArrowEntityRenderer::new);

        BuiltinItemRendererRegistry.INSTANCE.register(MythicTools.CARMOT_STAFF, new CarmotStaffBlockRenderer());
        ModelLoadingRegistry.INSTANCE.registerModelProvider(new CarmotStaffBlockRenderer());
        ColorProviderRegistry.ITEM.register(UsefulSingletonForColorUtil::potionColor, MythicTools.TIPPED_RUNITE_ARROW);

        CarmotShieldHudHandler.init();
        ClientTickEvents.END_CLIENT_TICK.register(client -> CarmotShieldHudHandler.tick());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), MythicBlocks.AQUARIUM_GLASS);
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
                    var blockState = player.getWorld().getBlockState(blockPos);
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
                        0, 0, 0, 0.4F //RGBA
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
                .filter(i -> i instanceof HallowedArmor
                        && Registries.ITEM.getKey(i).get().getValue().getNamespace().equals(MythicMetals.MOD_ID))
                .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumer, stack, entity, slot, light, original) -> {

            HallowedArmor armor = (HallowedArmor) stack.getItem();
            var model = armor.getArmorModel();
            var texture = armor.getArmorTexture(stack, slot);
            original.copyBipedStateTo(model);
            ArmorRenderer.renderPart(matrices, vertexConsumer, light, stack, model, texture);

            // Armor trim time
            if (!stack.isOf(MythicArmor.HALLOWED.getHelmet())) {
                ArmorTrim.getTrim(entity.getWorld().getRegistryManager(), stack).ifPresent(trim -> {
                    var atlas = MinecraftClient.getInstance().getSpriteAtlas(TexturedRenderLayers.ARMOR_TRIMS_ATLAS_TEXTURE);
                    Sprite sprite = atlas.apply(slot == EquipmentSlot.LEGS ? trim.getLeggingsModelId(armor.getMaterial()) : trim.getGenericModelId(armor.getMaterial()));
                    VertexConsumer trimVertexConsumer = sprite.getTextureSpecificVertexConsumer(
                            ItemRenderer.getDirectItemGlintConsumer(vertexConsumer, TexturedRenderLayers.getArmorTrims(), true, stack.hasGlint())
                    );
                    model.render(matrices, trimVertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
                });
            }
        };
        ArmorRenderer.register(renderer, armors);
    }

    private void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(
                MythicTools.LEGENDARY_BANGLUM.getPickaxe(), new Identifier("is_primed"),
                (stack, world, entity, seed) -> BanglumPick.getCooldown(entity, stack) ? 0 : 1);

        ModelPredicateProviderRegistry.register(
                MythicTools.LEGENDARY_BANGLUM.getShovel(), new Identifier("is_primed"),
                (stack, world, entity, seed) -> BanglumShovel.getCooldown(entity, stack) ? 0 : 1);

        ModelPredicateProviderRegistry.register(
                MythicTools.MYTHRIL_DRILL, new Identifier("is_active"),
                (stack, world, entity, seed) -> stack.get(MythrilDrill.IS_ACTIVE) ? 0 : 1);

        registerMidasPredicates(MythicTools.MIDAS_GOLD_SWORD);
        registerMidasPredicates(MythicTools.GILDED_MIDAS_GOLD_SWORD);
        registerMidasPredicates(MythicTools.ROYAL_MIDAS_GOLD_SWORD);

        ModelPredicateProviderRegistry.register(RegistryHelper.id("in_world"), (itemStack, world, livingEntity, i) -> {
            if (mode == null) {
                return 1.0f;
            }

            return mode.equals(ModelTransformationMode.GUI) ? 0.0F : 1.0f;
        });

        ModelPredicateProviderRegistry.register(MythicTools.STORMYX_SHIELD, new Identifier("blocking"), new ShieldUsePredicate());

        ModelPredicateProviderRegistry.register(RegistryHelper.id("funny_day"), (stack, world, entity, seed) ->
                (Calendar.getInstance().get(Calendar.MONTH) == Calendar.APRIL && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1 && !MythicMetals.CONFIG.disableFunny()) ? 1 : 0);

        ModelPredicateProviderRegistry.register(MythicTools.PLATINUM_WATCH, RegistryHelper.id("time"), (stack, world, entity, seed) -> {
            if (entity == null || entity.getWorld() == null) {
                return 0.0F;
            }

            return this.getTime(entity.getWorld());
        });

    }

    private float getTime(World world) {
        if (world.getTime() != this.lastTick) {
            this.lastTick = world.getTime();
            this.time += Delta.compute(this.time, (world.getTimeOfDay()) / 24000.0f, MinecraftClient.getInstance().getLastFrameDuration() / 10.0f);
        }

        return this.time;
    }

    public void registerMidasPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("midas_gold_count"),
                (stack, world, entity, seed) -> {
                    int goldCount = stack.get(MidasGoldSword.GOLD_FOLDED);
                    return MidasGoldSword.countGold(goldCount);
                });
    }

}
