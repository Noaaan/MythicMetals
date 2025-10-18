package com.mythicmetals.client.rendering;

import com.mythicmetals.block.MythicBlocks;
import com.mythicmetals.entity.BanglumTntEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.TntEntityRenderState;

public class BanglumTntEntityRenderer extends EntityRenderer<BanglumTntEntity, TntEntityRenderState> {

    public BanglumTntEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
    }

    @Override
    public TntEntityRenderState createRenderState() {
        var state = new TntEntityRenderState();
        state.blockState = MythicBlocks.BANGLUM_TNT_BLOCK.getDefaultState();
        return state;
    }

}
