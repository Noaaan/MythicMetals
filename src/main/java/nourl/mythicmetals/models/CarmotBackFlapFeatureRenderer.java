package nourl.mythicmetals.models;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class CarmotBackFlapFeatureRenderer extends FeatureRenderer<LivingEntity, CarmotArmorModel> {

    private double prevCapeX;
    private double prevCapeY;
    private double prevCapeZ;
    private double capeX;
    private double capeY;
    private double capeZ;

    public CarmotBackFlapFeatureRenderer(FeatureRendererContext<LivingEntity, CarmotArmorModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.world.isClient) {
            matrixStack.push();
            matrixStack.translate(0.0, 0.0, 0.125);
            double d = MathHelper.lerp(tickDelta, prevCapeX, capeX)
                    - MathHelper.lerp(tickDelta, entity.prevX, entity.getX());
            double e = MathHelper.lerp(tickDelta, prevCapeY, capeY)
                    - MathHelper.lerp(tickDelta, entity.prevY, entity.getY());
            double m = MathHelper.lerp(tickDelta, prevCapeZ, capeZ)
                    - MathHelper.lerp(tickDelta, entity.prevZ, entity.getZ());

            float n = entity.prevBodyYaw + (entity.bodyYaw - entity.prevBodyYaw);

            double o = MathHelper.sin(n * (float) (Math.PI / 180.0));
            double p = -MathHelper.cos(n * (float) (Math.PI / 180.0));

            float q = (float) e * 10.0F;
            float r = (float) (d * o + m * p) * 100.0F;
            float s = (float) (d * p - m * o) * 100.0F;

            q = MathHelper.clamp(q, -6.0F, 32.0F);
            r = MathHelper.clamp(r, 0.0F, 150.0F);
            s = MathHelper.clamp(s, -20.0F, 20.0F);

            if (r < 0.0F) {
                r = 0.0F;
            }

            // Note that strideDistance is removed
            q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 6.0F) * 32.0F * 0.4F;
            if (entity.isInSneakingPose()) {
                q += 25.0F;
            }

            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
            matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntitySolid(MythicModelHandler.CARMOT.getId()));
            // For a player this context model is actually a PlayerEntityModel, even if they are wearing Carmot Armor
            this.getContextModel().renderFlap(matrixStack, vertexConsumer, light, LivingEntityRenderer.getOverlay(entity, 0.0F));
            matrixStack.pop();
            updateCapeAngles(entity);
        }
    }

    private void updateCapeAngles(LivingEntity entity) {
        prevCapeX = capeX;
        prevCapeY = capeY;
        prevCapeZ = capeZ;
        double d = entity.getX() - capeX;
        double e = entity.getY() - capeY;
        double f = entity.getZ() - capeZ;
        if (d > 10.0) {
            capeX = entity.getX();
            prevCapeX = capeX;
        }

        if (f > 10.0) {
            capeZ = entity.getZ();
            prevCapeZ = capeZ;
        }

        if (e > 10.0) {
            capeY = entity.getY();
            prevCapeY = capeY;
        }

        if (d < -10.0) {
            capeX = entity.getX();
            prevCapeX = capeX;
        }

        if (f < -10.0) {
            capeZ = entity.getZ();
            prevCapeZ = capeZ;
        }

        if (e < -10.0) {
            capeY = entity.getY();
            prevCapeY = capeY;
        }

        capeX += d * 0.25;
        capeZ += f * 0.25;
        capeY += e * 0.25;
    }


}
