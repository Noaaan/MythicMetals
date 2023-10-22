package nourl.mythicmetals.client.rendering;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.client.models.RainbowShieldModel;
import nourl.mythicmetals.item.tools.CarmotStaff;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.misc.UsefulSingletonForColorUtil;

public class CarmotStaffBlockRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, ModelLoadingPlugin {
    public static final ModelIdentifier CARMOT_STAFF_ID = new ModelIdentifier(RegistryHelper.id("carmot_staff_base"), "inventory");
    private static final Identifier WORLD_BORDER = new Identifier("textures/misc/forcefield.png");

    @Override
    public void render(ItemStack staff, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        // TODO - Small optimization: Staff Model could be static? Investigate.
        var client = MinecraftClient.getInstance();
        boolean shouldRenderRainbowShield = CarmotStaff.hasBlockInStaff(staff, MythicBlocks.STORMYX.getStorageBlock());
        boolean isEnchantedMidas = CarmotStaff.hasBlockInStaff(staff, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK);
        Block block = staff.has(CarmotStaff.STORED_BLOCK) ? (staff.get(CarmotStaff.STORED_BLOCK)) : Blocks.AIR;
        BakedModel staffModel = client.getBakedModelManager().getModel(CARMOT_STAFF_ID);
        BakedModel blockModel = client.getBakedModelManager().getBlockModels().getModel(block.getDefaultState());

        if (shouldRenderRainbowShield && mode.isFirstPerson() && staff.get(CarmotStaff.IS_USED)) {
            renderRainbowShield(mode, matrices, vertexConsumerProvider, light);
        }

        // Render Staff itself
        matrices.translate(.5, .5, .5);
        client.getItemRenderer().renderItem(staff, mode, false, matrices, vertexConsumerProvider, light, overlay, staffModel);

        staffModel.getTransformation().getTransformation(mode).apply(false, matrices);
        matrices.scale(0.25F, 0.25F, 0.25F);
        matrices.translate(-.5F, 2F, 0F);

        // First render call fixes light, second renders the actual block
        client.getBlockRenderManager().renderBlockAsEntity(block.getDefaultState(), matrices, vertexConsumerProvider, light, overlay);
        client.getBlockRenderManager().getModelRenderer().render(
                matrices.peek(),
                ItemRenderer.getItemGlintConsumer(vertexConsumerProvider, RenderLayer.getCutoutMipped(), true, isEnchantedMidas || staff.hasGlint()),
                block.getDefaultState(),
                blockModel,
                0,
                0,
                0,
                light,
                overlay);
    }

    /**
     * Renders the model of the Stormyx Rainbow Shield, a fancy localized worldborder
     */
    private void renderRainbowShield(ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vcp, int light) {
        matrices.push();
        // Rainbow Handling
        double delta = System.currentTimeMillis() / 45.0;

        double hue = delta % 360.0;
        float saturation = 1;
        float constantvalue = 1;

        int color = MathHelper.hsvToRgb((float) (hue / 360), saturation, constantvalue);

        float[] rgbColors = UsefulSingletonForColorUtil.splitRGBToFloats(color);

        // Model handling, tries to reverse the rotation of the shield from the Bow UseAction
        if (mode.equals(ModelTransformationMode.FIRST_PERSON_RIGHT_HAND)) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(13.935F * 3));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-35.3F * 2));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(9.785F * 2));
        } else {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(13.935F * 1.5F));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(35.3F * 1.85F));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(9.785F * 1));
        }

        // Create and render rainbow shield
        var part = RainbowShieldModel.getTexturedModelData();
        part.createModel().render(
                matrices,
                vcp.getBuffer(RenderLayer.getEnergySwirl(WORLD_BORDER, (float) ((delta * .005f) % 1f), (float) (delta * .005f % 1f))),
                light,
                OverlayTexture.DEFAULT_UV,
                rgbColors[0],
                rgbColors[1],
                rgbColors[2],
                0.5F);
        matrices.pop();
    }

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(CARMOT_STAFF_ID);
    }
}
