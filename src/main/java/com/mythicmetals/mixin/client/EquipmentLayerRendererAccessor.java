package com.mythicmetals.mixin.client;

import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.function.Function;

@Mixin(EquipmentLayerRenderer.class)
public interface EquipmentLayerRendererAccessor {

    @Accessor("trimSpriteLookup")
    Function<EquipmentLayerRenderer.TrimSpriteKey, TextureAtlasSprite> mythicmetals$getTrimSprites();
}
