package com.mythicmetals.client.rendering;

import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.entity.BanglumTntMinecartEntity;
import com.mythicmetals.item.MythicMaterials;
import com.mythicmetals.item.MythicResourceKeys;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.AbstractMinecartRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.MinecartTntRenderState;
import net.minecraft.world.level.block.Blocks;

public class BanglumTntMinecartEntityRenderer extends AbstractMinecartRenderer<BanglumTntMinecartEntity, MinecartTntRenderState> {

    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private final BlockModelResolver blockModelResolver;

    public BanglumTntMinecartEntityRenderer(EntityRendererProvider.Context context) {
        super(context, MythicModelHandler.BANGLUM_TNT_MINECART);
        blockModelResolver = context.getBlockModelResolver();
    }

    @Override
    public MinecartTntRenderState createRenderState() {
        var renderState = new MinecartTntRenderState();
        blockModelResolver.update(
            renderState.displayBlockModel,
            MythicMaterials.BANGLUM.extraBlocks().get(MythicResourceKeys.BANGLUM_TNT).defaultBlockState(),
            BLOCK_DISPLAY_CONTEXT
            );
        return renderState;
    }
}
