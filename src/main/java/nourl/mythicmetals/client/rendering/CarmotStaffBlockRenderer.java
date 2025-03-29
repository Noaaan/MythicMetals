package nourl.mythicmetals.client.rendering;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import nourl.mythicmetals.block.MythicBlocks;
import nourl.mythicmetals.client.models.RainbowShieldModel;
import nourl.mythicmetals.component.MythicDataComponents;
import nourl.mythicmetals.item.tools.carmot_staff.CarmotStaffItem;
import nourl.mythicmetals.misc.RegistryHelper;

public class CarmotStaffBlockRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, ModelLoadingPlugin {
    public static final Identifier CARMOT_STAFF_ID = RegistryHelper.id("item/carmot_staff_base");
    private static final Identifier WORLD_BORDER = Identifier.of("textures/misc/forcefield.png");

    @Override
    public void render(ItemStack staff, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        // TODO - Small optimization: Staff Model could be static? Investigate.
        var client = MinecraftClient.getInstance();
        boolean shouldRenderRainbowShield = CarmotStaffItem.hasBlockInStaff(staff, MythicBlocks.STORMYX.getStorageBlock());
        boolean isEnchantedMidas = CarmotStaffItem.hasBlockInStaff(staff, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK);
        //noinspection DataFlowIssue
        Block block = staff.contains(MythicDataComponents.CARMOT_STAFF_BLOCK) ? (staff.get(MythicDataComponents.CARMOT_STAFF_BLOCK).getBlock()) : Blocks.AIR;
        BakedModel staffModel = client.getBakedModelManager().getModel(CARMOT_STAFF_ID);
        BakedModel blockModel = client.getBakedModelManager().getBlockModels().getModel(block.getDefaultState());

        if (shouldRenderRainbowShield && mode.isFirstPerson() && staff.getOrDefault(MythicDataComponents.IS_USED, false)) {
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

        int color = MathHelper.hsvToArgb((float) (hue / 360), saturation, constantvalue, 128);

        //float[] rgbColors = UsefulSingletonForColorUtil.splitRGBToFloats(color);

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
            color);
        matrices.pop();
    }

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.addModels(CARMOT_STAFF_ID);
    }
}
