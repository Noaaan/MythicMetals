package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.armor.HallowedArmor;
import nourl.mythicmetals.blocks.BanglumTntEntityRenderer;
import nourl.mythicmetals.registry.RegisterEntities;
import nourl.mythicmetals.tools.BanglumPick;
import nourl.mythicmetals.tools.BanglumShovel;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.armor.models.ModelHandler;

public class MythicMetalsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModelHandler.init((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));
        registerArmorRenderer();

        EntityRendererRegistry.register(RegisterEntities.BANGLUM_TNT_ENTITY_TYPE, BanglumTntEntityRenderer::new);
        FabricModelPredicateProviderRegistry.register(MythicTools.LEGENDARY_BANGLUM.getPickaxe(), new Identifier("is_primed"), (stack, world, entity, seed) -> BanglumPick.getCooldown(entity, stack) ? 0 : 1);
        FabricModelPredicateProviderRegistry.register(MythicTools.LEGENDARY_BANGLUM.getShovel(), new Identifier("is_primed"), (stack, world, entity, seed) -> BanglumShovel.getCooldown(entity, stack) ? 0 : 1);
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

}
