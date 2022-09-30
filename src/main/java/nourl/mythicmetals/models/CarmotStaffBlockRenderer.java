package nourl.mythicmetals.models;

import net.fabricmc.fabric.api.client.model.ExtraModelProvider;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.tools.CarmotStaff;
import nourl.mythicmetals.utils.RegistryHelper;
import nourl.mythicmetals.utils.UselessSingletonForColorUtil;

import java.util.function.Consumer;

public class CarmotStaffBlockRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, ExtraModelProvider {
    public static final ModelIdentifier CARMOT_STAFF_ID = new ModelIdentifier(RegistryHelper.id("carmot_staff_base"), "inventory");
    private static final Identifier WORLD_BORDER = new Identifier("textures/misc/forcefield.png");
    @Override
    public void render(ItemStack staff, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        var block = staff.has(CarmotStaff.STORED_BLOCK) ? (staff.get(CarmotStaff.STORED_BLOCK)) : Blocks.AIR;
        var instance = MinecraftClient.getInstance();
        var model = instance.getBakedModelManager().getModel(CARMOT_STAFF_ID);
        var blockModel = instance.getBakedModelManager().getBlockModels().getModel(block.getDefaultState());

        boolean shouldRenderRainbowShield = CarmotStaff.hasBlockInStaff(staff, MythicBlocks.STORMYX.getStorageBlock());

        if (shouldRenderRainbowShield && mode.isFirstPerson() && staff.get(CarmotStaff.IS_USED)) {
            matrices.push();
            // Rainbow Handling
            double delta = System.currentTimeMillis() / 45.0;

            double hue = delta % 360.0;
            float saturation = 1;
            float constantvalue = 1;

            int color = MathHelper.hsvToRgb((float) (hue / 360), saturation, constantvalue);

            float[] rgbColors = UselessSingletonForColorUtil.splitRGBToFloats(color);

            // Model handling, tries to reverse the rotation of the shield from the Bow UseAction

            if (mode.equals(ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND)) {
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(13.935F * 3));
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-35.3F * 2));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(9.785F * 2));
            } else {
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(13.935F * 1.5F));
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(35.3F * 1.85F));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(9.785F * 1));
            }

            // Create and render model
            var part = RainbowShieldModel.getTexturedModelData();
            part.createModel().render(
                    matrices,
                    vertexConsumerProvider.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
                    light,
                    OverlayTexture.DEFAULT_UV,
                    rgbColors[0],
                    rgbColors[1],
                    rgbColors[2],
                    0.5F);
            matrices.pop();
        }

        matrices.translate(.5, .5, .5);
        instance.getItemRenderer().renderItem(staff, mode, false, matrices, vertexConsumerProvider, light, overlay, model);

        model.getTransformation().getTransformation(mode).apply(false, matrices);
        matrices.scale(0.25F, 0.25F, 0.25F);
        matrices.translate(-.5F, 2F, 0F);

        instance.getBlockRenderManager().renderBlockAsEntity(block.getDefaultState(), matrices, vertexConsumerProvider, light, overlay);
        instance.getBlockRenderManager().getModelRenderer().render(matrices.peek(), ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, RenderLayer.getCutoutMipped(), true, staff.hasGlint()), block.getDefaultState(), blockModel, 0, 0, 0, light, overlay);
    }

    @Override
    public void provideExtraModels(ResourceManager manager, Consumer<Identifier> out) {
        out.accept(CARMOT_STAFF_ID);
    }
}
