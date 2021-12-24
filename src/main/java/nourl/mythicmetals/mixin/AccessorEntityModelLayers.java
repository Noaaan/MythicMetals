package nourl.mythicmetals.mixin;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(EntityModelLayers.class)
public interface AccessorEntityModelLayers {
    @Accessor("LAYERS")
    static Set<EntityModelLayer> getAllModels() {
        throw new IllegalStateException();
    }
}


