package nourl.mythicmetals.item;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.item.tools.MythicTools;
import nourl.mythicmetals.misc.RegistryHelper;

public class MythicItemGroups extends OwoItemGroup {

    public MythicItemGroups(Identifier id) {
        super(id);
    }

    @Override
    protected void setup() {
        this.addTab(Icon.of(MythicItems.Ingots.ADAMANTITE_INGOT), "items", TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("item_tab")));
        this.addTab(Icon.of(MythicBlocks.ADAMANTITE.getStorageBlock()), "blocks", TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("blocks")));
        this.addTab(Icon.of(MythicTools.ADAMANTITE.getPickaxe()), "tools", TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("tool_tab")));
        this.addTab(Icon.of(MythicArmor.ADAMANTITE.getChestplate()), "armor", TagKey.of(Registry.ITEM_KEY, RegistryHelper.id("armor_tab")));

        this.addButton(ItemGroupButton.github("https://github.com/Noaaan/MythicMetals/issues"));
        this.addButton(ItemGroupButton.curseforge("https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
        this.addButton(ItemGroupButton.modrinth("https://modrinth.com/mod/mythicmetals"));
        this.addButton(ItemGroupButton.discord("https://discord.gg/69cKvQWScC"));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(MythicItems.Ingots.STORMYX_INGOT);
    }
}
