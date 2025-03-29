package com.mythicmetals.client.rendering;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.util.Identifier;
import com.mythicmetals.client.models.MythicModelHandler;
import com.mythicmetals.entity.PalladiumMinecartEntity;
import com.mythicmetals.misc.RegistryHelper;

public class PalladiumMinecartRenderer extends MinecartEntityRenderer<PalladiumMinecartEntity> {

    public PalladiumMinecartRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, MythicModelHandler.PALLADIUM_MINECART);
    }

    @Override
    public Identifier getTexture(PalladiumMinecartEntity abstractMinecartEntity) {
        return RegistryHelper.id("textures/models/palladium_minecart.png");
    }
}
