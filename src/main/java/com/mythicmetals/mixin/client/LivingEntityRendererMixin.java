package com.mythicmetals.mixin.client;

import com.mythicmetals.item.armor.CarmotShield;
import com.mythicmetals.client.MythicRenderStateKeys;
import com.mythicmetals.entity.MythicEntityAttributes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> {

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("RETURN"))
    private void mythicmetals$extractRenderState(T entity, S state, float partialTicks, CallbackInfo ci) {
        var attributes = entity.getAttributes();
        if (attributes.hasAttribute(MythicEntityAttributes.CARMOT_SHIELD)) {
            state.setData(MythicRenderStateKeys.CARMOT_SHIELD_STATE_KEY, new CarmotShield(
                attributes.getValue(MythicEntityAttributes.CARMOT_SHIELD),
                entity.hurtTime > 0
            ));
        }
    }
}
