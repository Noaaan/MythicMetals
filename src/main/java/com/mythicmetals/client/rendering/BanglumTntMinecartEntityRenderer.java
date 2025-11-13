package com.mythicmetals.client.rendering;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.entity.BanglumTntMinecartEntity;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.state.TntMinecartEntityRenderState;

public class BanglumTntMinecartEntityRenderer extends AbstractMinecartEntityRenderer<BanglumTntMinecartEntity, TntMinecartEntityRenderState> {

    public BanglumTntMinecartEntityRenderer(EntityRendererFactory.Context context) {
        super(context, MythicModelHandler.BANGLUM_TNT_MINECART);
    }

    @Override
    public TntMinecartEntityRenderState createRenderState() {
        var renderState = new TntMinecartEntityRenderState();
        renderState.containedBlock = MythicBlocks.BANGLUM_TNT_BLOCK.getDefaultState();
        return renderState;
    }
}
