package com.mythicmetals.client.rendering;

import com.mythicmetals.client.models.RainbowShieldModel;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

public class StormyxShieldRenderer {
    private static final Identifier WORLD_BORDER = Identifier.of("textures/misc/forcefield.png");

    /**
     * Renders the model of the Stormyx Rainbow Shield, a fancy localized worldborder
     */
    public static void renderRainbowShield(MatrixStack matrices, VertexConsumerProvider vcp, int light, AbstractClientPlayerEntity player) {
        matrices.push();
        // Rainbow Handling
        double delta = System.currentTimeMillis() / 45.0;

        // Create and render rainbow shield
        var part = RainbowShieldModel.getTexturedModelData().createModel();
        part.scale(new Vector3f(player.getScale() - 1, player.getScale() - 1, player.getScale() - 1));
        part.render(
            matrices,
            vcp.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
            light,
            OverlayTexture.DEFAULT_UV,
            UsefulSingletonForColorUtil.rainbow());
        matrices.pop();
    }
}
