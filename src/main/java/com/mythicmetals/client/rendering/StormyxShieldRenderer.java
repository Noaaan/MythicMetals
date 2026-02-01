package com.mythicmetals.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mythicmetals.client.models.RainbowShieldModel;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.joml.Vector3f;

public class StormyxShieldRenderer {
    private static final Identifier WORLD_BORDER = Identifier.parse("textures/misc/forcefield.png");

    /**
     * Renders the model of the Stormyx Rainbow Shield, a fancy localized worldborder
     */
    public static void renderRainbowShield(PoseStack matrices, MultiBufferSource vcp, int light, AbstractClientPlayer player) {
        matrices.pushPose();
        // Rainbow Handling
        double delta = System.currentTimeMillis() / 45.0;

        // Create and render rainbow shield
        var part = RainbowShieldModel.getTexturedModelData().bakeRoot();
        part.offsetScale(new Vector3f(player.getScale() - 1, player.getScale() - 1, player.getScale() - 1));
        part.render(
            matrices,
            vcp.getBuffer(RenderType.energySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
            light,
            OverlayTexture.NO_OVERLAY,
            UsefulSingletonForColorUtil.rainbow());
        matrices.popPose();
    }
}
