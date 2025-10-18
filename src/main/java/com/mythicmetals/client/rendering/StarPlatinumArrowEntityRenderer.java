package com.mythicmetals.client.rendering;

import com.mythicmetals.entity.StarPlatinumArrowEntity;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class StarPlatinumArrowEntityRenderer extends ProjectileEntityRenderer<StarPlatinumArrowEntity, ProjectileEntityRenderState> {
    public static final Identifier TEXTURE = RegistryHelper.id("textures/models/star_platinum_arrow.png");

    public StarPlatinumArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public ProjectileEntityRenderState createRenderState() {
        return new ProjectileEntityRenderState();
    }

    @Override
    protected Identifier getTexture(ProjectileEntityRenderState state) {
        return TEXTURE;
    }
}
