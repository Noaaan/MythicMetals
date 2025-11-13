package com.mythicmetals.item;

import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import java.util.List;

public class RedAegisSword extends SwordItem {
    public RedAegisSword(ToolMaterial material, float damage, float speed, Settings settings) {
        super(material, damage, speed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireForTicks(320);
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("abilities.mythicmetals.fire_aspect").withColor(UsefulSingletonForColorUtil.MetalColors.RED_AEGIS.rgb()));
    }
}
