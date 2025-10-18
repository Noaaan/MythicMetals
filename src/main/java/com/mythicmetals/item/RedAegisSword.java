package com.mythicmetals.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;

public class RedAegisSword extends SwordItem {
    public RedAegisSword(ToolMaterial material, float damage, float speed, Settings settings) {
        super(material, damage, speed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireForTicks(320);
        return super.postHit(stack, target, attacker);
    }
}
