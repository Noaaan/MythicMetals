package com.mythicmetals.client.rendering;

import com.mythicmetals.entity.RuniteArrowEntity;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.entity.state.ProjectileEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RuniteArrowEntityRenderer extends ProjectileEntityRenderer<RuniteArrowEntity, ProjectileEntityRenderState> {
    public static final Identifier TEXTURE = RegistryHelper.id("textures/models/runite_arrow.png");
    public static final Identifier TIPPED_TEXTURE = RegistryHelper.id("textures/models/tipped_runite_arrow.png");

    public RuniteArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public ProjectileEntityRenderState createRenderState() {
        return null;
    }

    @Override
    protected Identifier getTexture(ProjectileEntityRenderState state) {
//        return state.getColor() > 0 ? TIPPED_TEXTURE : TEXTURE;
        return TEXTURE;
    }
}
