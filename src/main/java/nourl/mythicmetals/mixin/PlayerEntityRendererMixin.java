package nourl.mythicmetals.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.models.RainbowShieldModel;
import nourl.mythicmetals.tools.CarmotStaff;
import nourl.mythicmetals.utils.UselessSingletonForColorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static nourl.mythicmetals.models.PlayerEnergySwirlFeatureRenderer.SWIRL_TEXTURE;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Unique
    private static final Identifier WORLD_BORDER = new Identifier("textures/misc/forcefield.png");

    /**
     * Renders the Carmot Shield on the players arm
     */
    @Inject(method = "renderArm", at = @At("TAIL"))
    private void mythicmetals$renderShieldArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, ModelPart sleeve, CallbackInfo ci) {
        if (player.getComponent(MythicMetals.CARMOT_SHIELD).isShieldActive()) {
            final var client = MinecraftClient.getInstance();
            float f = player.age + (client.isPaused() ? 0 : client.getTickDelta());

            var shield = player.getComponent(MythicMetals.CARMOT_SHIELD);

            int pieces = (int) (shield.getMaxHealth() % 4 + 1);
            float health = pieces < 3 ? shield.health / 80f : shield.health / 110f;

            var consumer = vertexConsumers.getBuffer(RenderLayer.getEnergySwirl(SWIRL_TEXTURE, (f * .005f) % 1f, f * .005f % 1f));

            if (shield.cooldown > 0) {
                matrices.scale(1.0625f, 1.0625f, 1.0625f);
                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, .9f, .025f, .025f, 1);
            } else // Regular animation
                sleeve.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, .8f, .1f + health, .05f, 1);
        }
    }

    /**
     * Renders the Stormyx Shield around the player
     */
    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At("TAIL"))
    private void mythicmetals$renderRainbowShield(AbstractClientPlayerEntity player, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        var stack = player.getStackInHand(Hand.MAIN_HAND);
        if (CarmotStaff.hasBlockInStaff(stack, MythicBlocks.STORMYX.getStorageBlock())) {
            if (!stack.get(CarmotStaff.IS_USED)) return; // Only render if the staff is actively being used
            matrixStack.push();
            double delta = System.currentTimeMillis() / 45.0;

            double hue = delta % 360.0;
            float saturation = 1;
            float constantvalue = 1;

            int color = MathHelper.hsvToRgb((float) (hue / 360), saturation, constantvalue);

            float[] rgbColors = UselessSingletonForColorUtil.splitRGBToFloats(color);

            var part = RainbowShieldModel.getTexturedModelData();

            part.createModel().render(
                    matrixStack,
                    vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
                    i,
                    OverlayTexture.DEFAULT_UV,
                    rgbColors[0],
                    rgbColors[1],
                    rgbColors[2],
                    0.5F);
            matrixStack.pop();
        }
    }
}
