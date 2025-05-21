package com.mythicmetals.client.rendering;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import com.mythicmetals.client.models.RainbowShieldModel;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.misc.RegistryHelper;

import static net.minecraft.client.render.model.json.ModelTransformationMode.*;

public class StormyxShieldRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, ModelLoadingPlugin {
    public static final Identifier SHIELD_ID = RegistryHelper.id("item/stormyx_shield_base");
    private static final Identifier WORLD_BORDER = Identifier.of("textures/misc/forcefield.png");
    private static BakedModel shieldModel;

    @Override
    public void render(ItemStack shield, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        var client = MinecraftClient.getInstance();
        if (shieldModel == null) {
            shieldModel = client.getBakedModelManager().getModel(SHIELD_ID);
        }

        if (mode.isFirstPerson() && shield.getOrDefault(MythicDataComponents.IS_USED, false)) {
            renderRainbowShield(matrices, vertexConsumerProvider, light);
        }

        matrices.translate(.5, .5, .5);
        var lefty = mode.equals(FIRST_PERSON_LEFT_HAND) || mode.equals(THIRD_PERSON_LEFT_HAND);
        client.getItemRenderer().renderItem(shield, mode, lefty, matrices, vertexConsumerProvider, light, overlay, shieldModel);

    }

    /**
     * Renders the model of the Stormyx Rainbow Shield, a fancy localized worldborder
     */
    public static void renderRainbowShield(MatrixStack matrices, VertexConsumerProvider vcp, int light) {
        matrices.push();
        // Rainbow Handling
        double delta = System.currentTimeMillis() / 45.0;

        // Create and render rainbow shield
        var part = RainbowShieldModel.getTexturedModelData();
        part.createModel().render(
            matrices,
            vcp.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
            light,
            OverlayTexture.DEFAULT_UV,
            UsefulSingletonForColorUtil.rainbow());
        matrices.pop();
    }

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(SHIELD_ID);
    }
}
