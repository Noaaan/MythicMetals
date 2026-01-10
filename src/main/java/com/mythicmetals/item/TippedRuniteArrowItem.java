package com.mythicmetals.item;

import com.mythicmetals.item.tools.MythicTools;
import java.util.List;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

public class TippedRuniteArrowItem extends RuniteArrowItem {

    public TippedRuniteArrowItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultInstance() {
        var stack = new ItemStack(MythicTools.TIPPED_RUNITE_ARROW);
        stack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.POISON));
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        PotionContents potionContentsComponent = stack.get(DataComponents.POTION_CONTENTS);
        if (potionContentsComponent != null) {
            potionContentsComponent.addPotionTooltip(tooltip::add, 0.125F, context.tickRate());
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        PotionContents potionContentsComponent = stack.get(DataComponents.POTION_CONTENTS);
        return potionContentsComponent != null ? potionContentsComponent.getName(this.descriptionId + ".effect.") : super.getName(stack);
    }
}
