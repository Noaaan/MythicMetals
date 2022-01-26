package nourl.mythicmetals.item;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.itemgroup.gui.ItemGroupTab;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.armor.MythicArmor;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.tools.MythicTools;
import nourl.mythicmetals.utils.RegistryHelper;

public class MythicItemGroups extends OwoItemGroup {

    public MythicItemGroups(Identifier id) {
        super(id);
    }

    @Override
    protected void setup() {
        this.addTab(Icon.of(MythicItems.ADAMANTITE_INGOT), "items", TagFactory.ITEM.create(RegistryHelper.id("metals")));
        this.addTab(Icon.of(MythicBlocks.ADAMANTITE.getStorageBlock()), "blocks", ItemGroupTab.EMPTY);
        this.addTab(Icon.of(MythicTools.ADAMANTITE.getPickaxe()), "tools", TagFactory.ITEM.create(RegistryHelper.id("weapons")));
        this.addTab(Icon.of(MythicArmor.ADAMANTITE.getChestplate()), "armor", TagFactory.ITEM.create(RegistryHelper.id("gear")));

        this.addButton(ItemGroupButton.github("https://github.com/Noaaan/MythicMetals/issues"));
        this.addButton(ItemGroupButton.curseforge("https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
        this.addButton(ItemGroupButton.modrinth("https://modrinth.com/mod/mythicmetals"));
        this.addButton(ItemGroupButton.discord("https://discord.gg/69cKvQWScC"));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(MythicItems.STORMYX_INGOT);
    }
}
