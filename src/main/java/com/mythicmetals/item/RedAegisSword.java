package com.mythicmetals.item;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import java.util.List;

public class RedAegisSword extends SwordItem {
    public RedAegisSword(ToolMaterial material, float damage, float speed, Properties settings) {
        super(material, damage, speed, settings);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForTicks(320);
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        super.appendHoverText(stack, context, tooltip, type);
        tooltip.add(Component.translatable("abilities.mythicmetals.fire_aspect").withColor(UsefulSingletonForColorUtil.MetalColors.RED_AEGIS.rgb()));
    }
}
