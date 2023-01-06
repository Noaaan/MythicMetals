package nourl.mythicmetals.client.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.entity.StarPlatinumArrowEntity;
import nourl.mythicmetals.misc.RegistryHelper;

@Environment(EnvType.CLIENT)
public class StarPlatinumArrowEntityRenderer extends ProjectileEntityRenderer<StarPlatinumArrowEntity> {
    public static final Identifier TEXTURE = RegistryHelper.id("textures/models/star_platinum_arrow.png");

    public StarPlatinumArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(StarPlatinumArrowEntity entity) {
        return TEXTURE;
    }
}
