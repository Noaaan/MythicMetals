package com.mythicmetals.client.rendering;

import com.mythicmetals.entity.RuniteArrowEntity;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.TippableArrowRenderState;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class RuniteArrowEntityRenderer extends ArrowRenderer<RuniteArrowEntity, TippableArrowRenderState> {
    public static final ResourceLocation TEXTURE = RegistryHelper.id("textures/models/runite_arrow.png");
    public static final ResourceLocation TIPPED_TEXTURE = RegistryHelper.id("textures/models/tipped_runite_arrow.png");

    public RuniteArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public TippableArrowRenderState createRenderState() {
        return new TippableArrowRenderState();
    }

    @Override
    protected ResourceLocation getTextureLocation(TippableArrowRenderState state) {
        return state.isTipped ? TIPPED_TEXTURE : TEXTURE;
    }
}
