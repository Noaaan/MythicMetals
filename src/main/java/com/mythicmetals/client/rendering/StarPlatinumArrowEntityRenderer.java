package com.mythicmetals.client.rendering;

import com.mythicmetals.entity.StarPlatinumArrowEntity;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class StarPlatinumArrowEntityRenderer extends ArrowRenderer<StarPlatinumArrowEntity, ArrowRenderState> {
    public static final ResourceLocation TEXTURE = RegistryHelper.id("textures/models/star_platinum_arrow.png");

    public StarPlatinumArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected ResourceLocation getTextureLocation(ArrowRenderState state) {
        return TEXTURE;
    }
}
