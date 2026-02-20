package com.mythicmetals.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

public class RedAegisSword extends Item {
    public RedAegisSword(ToolMaterial material, float damage, float speed, Properties settings) {
        super(material.applySwordProperties(settings, damage, speed));
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForTicks(320);
        super.hurtEnemy(stack, target, attacker);
    }

//    @Override
//    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
//        super.appendHoverText(stack, context, tooltip, type);
//        tooltip.add(Component.translatable("abilities.mythicmetals.fire_aspect").withColor(UsefulSingletonForColorUtil.MetalColors.RED_AEGIS.rgb()));
//    }
}
