package com.mythicmetals.item.tools.carmot_staff;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

import static com.mythicmetals.component.MythicDataComponents.CARMOT_STAFF_BLOCK;
import static com.mythicmetals.component.MythicDataComponents.LOCKED;

public class CarmotStaffItem extends ToolItem {


    public CarmotStaffItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Deprecated(forRemoval = true, since = "0.23.0")
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.contains(CARMOT_STAFF_BLOCK) && !stack.getOrDefault(LOCKED, false) && entity.isPlayer()) {
            var player = (PlayerEntity) entity;
            var component = stack.get(CARMOT_STAFF_BLOCK);
            assert component != null;
            var blockItem = component.block().asItem();
            player.getInventory().offerOrDrop(new ItemStack(blockItem));
            stack.remove(CARMOT_STAFF_BLOCK);
        }
    }
}
