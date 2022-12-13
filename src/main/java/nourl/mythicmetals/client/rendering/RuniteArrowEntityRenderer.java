package nourl.mythicmetals.client.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.entity.RuniteArrowEntity;
import nourl.mythicmetals.utils.RegistryHelper;

@Environment(EnvType.CLIENT)
public class RuniteArrowEntityRenderer extends ProjectileEntityRenderer<RuniteArrowEntity> {
    public static final Identifier TEXTURE = RegistryHelper.id("textures/models/runite_arrow.png");
    public static final Identifier TIPPED_TEXTURE = RegistryHelper.id("textures/models/tipped_runite_arrow.png");

    public RuniteArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(RuniteArrowEntity entity) {
        return entity.getColor() > 0 ? TIPPED_TEXTURE : TEXTURE;
    }
}
