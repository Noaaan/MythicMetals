package com.mythicmetals.client.rendering;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.entity.BanglumTntMinecartEntity;

import net.minecraft.client.renderer.entity.AbstractMinecartRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.MinecartTntRenderState;

public class BanglumTntMinecartEntityRenderer extends AbstractMinecartRenderer<BanglumTntMinecartEntity, MinecartTntRenderState> {

    public BanglumTntMinecartEntityRenderer(EntityRendererProvider.Context context) {
        super(context, MythicModelHandler.BANGLUM_TNT_MINECART);
    }

    @Override
    public MinecartTntRenderState createRenderState() {
        var renderState = new MinecartTntRenderState();
        renderState.displayBlockState = MythicBlocks.BANGLUM_TNT_BLOCK.defaultBlockState();
        return renderState;
    }
}
