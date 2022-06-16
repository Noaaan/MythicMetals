package nourl.mythicmetals.models;

import net.fabricmc.fabric.api.client.model.ExtraModelProvider;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import nourl.mythicmetals.item.CarmotStaff;
import nourl.mythicmetals.utils.RegistryHelper;

import java.util.Random;
import java.util.function.Consumer;

public class CarmotStaffBlockRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer, ExtraModelProvider {
    public static final ModelIdentifier CARMOT_STAFF_ID = new ModelIdentifier(RegistryHelper.id("carmot_staff_base"), "inventory");

    @Override
    public void render(ItemStack staff, ModelTransformation.Mode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var block = staff.has(CarmotStaff.STORED_BLOCK) ? (staff.get(CarmotStaff.STORED_BLOCK)) : Blocks.AIR;
        var instance = MinecraftClient.getInstance();
        var model = instance.getBakedModelManager().getModel(CARMOT_STAFF_ID);

        matrices.translate(.5, .5, .5);
        instance.getItemRenderer().renderItem(staff, mode, false, matrices, vertexConsumers, light, overlay, model);

        model.getTransformation().getTransformation(mode).apply(false, matrices);
        matrices.scale(0.25F, 0.25F, 0.25F);
        matrices.translate(-.5F, 2F, 0F);

        instance.getBlockRenderManager().renderBlock(block.getDefaultState(), BlockPos.ORIGIN, instance.world, matrices, vertexConsumers.getBuffer(RenderLayer.getSolid()), false, new Random());
        instance.getBlockRenderManager().renderBlock(block.getDefaultState(), BlockPos.ORIGIN, instance.world, matrices, ItemRenderer.getItemGlintConsumer(vertexConsumers, RenderLayer.getSolid(), true, true), false, new Random());
    }

    @Override
    public void provideExtraModels(ResourceManager manager, Consumer<Identifier> out) {
        out.accept(CARMOT_STAFF_ID);
    }
}