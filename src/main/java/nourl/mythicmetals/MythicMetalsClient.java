package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.armor.HallowedArmor;
import nourl.mythicmetals.armor.models.MythicModelHandler;
import nourl.mythicmetals.blocks.BanglumTntEntityRenderer;
import nourl.mythicmetals.registry.RegisterEntities;
import nourl.mythicmetals.tools.BanglumPick;
import nourl.mythicmetals.tools.BanglumShovel;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MythicMetalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MythicModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));
        registerArmorRenderer();
        //registerReloadListener();

        EntityRendererRegistry.register(RegisterEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        FabricModelPredicateProviderRegistry.register(MythicTools.LEGENDARY_BANGLUM.getPickaxe(), new Identifier("is_primed"), (stack, world, entity, seed) -> BanglumPick.getCooldown(entity, stack) ? 0 : 1);
        FabricModelPredicateProviderRegistry.register(MythicTools.LEGENDARY_BANGLUM.getShovel(), new Identifier("is_primed"), (stack, world, entity, seed) -> BanglumShovel.getCooldown(entity, stack) ? 0 : 1);
        FabricModelPredicateProviderRegistry.register(RegistryHelper.id("funny_day"), (stack, world, entity, seed) ->
                (Calendar.getInstance().get(Calendar.MONTH) == Calendar.APRIL && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1 && !MythicMetals.CONFIG.disableFunny) ? 1 : 0);
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

    private void registerReloadListener() {
//        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new IdentifiableResourceReloadListener() {
//            @Override
//            public Identifier getFabricId() {
//                return RegistryHelper.id("resource_listener");
//            }
//
//            @Override
//            public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
//                MinecraftClient.getInstance().getTextureManager().registerTexture(MythicModelHandler.HALLOWED_CAPE,
//                        new ResourceTexture(MythicModelHandler.HALLOWED_CAPE));
//                return CompletableFuture.completedFuture(null);
//            }
//        });
    }

}
