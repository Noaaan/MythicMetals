package com.mythicmetals.api.v2;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import java.util.List;

public record SmithingTemplateComponents(
    Component appliesTo,
    Component ingredients,
    Component description,
    Component additionalDesc,
    List<Identifier> baseSlotTextures,
    List<Identifier> additionsTextures
) {
    public Item toItem(Item.Properties properties) {
        return new SmithingTemplateItem(
            appliesTo,
            ingredients,
            description,
            additionalDesc,
            baseSlotTextures,
            additionsTextures,
            properties
        );
    }
}
