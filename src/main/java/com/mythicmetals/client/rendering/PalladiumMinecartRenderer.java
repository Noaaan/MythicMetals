package com.mythicmetals.client.rendering;

import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.entity.PalladiumMinecartEntity;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.util.Identifier;

// FIXME - Texture no longer...
public class PalladiumMinecartRenderer extends MinecartEntityRenderer {

    public PalladiumMinecartRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, MythicModelHandler.PALLADIUM_MINECART);
    }
}
