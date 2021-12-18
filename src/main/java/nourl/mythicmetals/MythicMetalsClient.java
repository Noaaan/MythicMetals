package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModels;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.armor.HallowedArmor;
import nourl.mythicmetals.armor.HallowedArmorModel;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.function.BiConsumer;

public class MythicMetalsClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        grabLayers();
        registerArmorRenderer();
    }

    private static void grabLayers() {
        initModelLayers((loc, def) -> EntityModelLayerRegistry.registerModelLayer(loc, () -> def));
    }

    private static void initModelLayers(BiConsumer<EntityModelLayer, TexturedModelData> consumer) {
        var modelLayer = new EntityModelLayer(RegistryHelper.id("hallowed_armor"), "model");
        EntityModels.getModels();
        consumer.accept(modelLayer, HallowedArmorModel.getTexturedModelData());
    }

    private void registerArmorRenderer() {
        Item[] armors = Registry.ITEM.stream()
                .filter(i -> i instanceof HallowedArmor
                        && Registry.ITEM.getKey(i).get().getValue().getNamespace().equals(MythicMetals.MOD_ID))
                .toArray(Item[]::new);

        ArmorRenderer renderer = (matrices, vertexConsumer, stack, entity, slot, light, realModel) -> {
            HallowedArmor armor = (HallowedArmor) stack.getItem();
            var model = armor.getArmorModel(entity, stack, slot, realModel);
            var texture = armor.getArmorTexture(stack, slot);
            realModel.copyStateTo(model);
            ArmorRenderer.renderPart(matrices, vertexConsumer, light, stack, model, texture);
        };
        ArmorRenderer.register(renderer, armors);
    }

}
