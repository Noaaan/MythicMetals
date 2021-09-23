package nourl.mythicmetals;

import com.glisco.owo.itemgroup.Icon;
import com.glisco.owo.itemgroup.OwoItemGroup;
import com.glisco.owo.itemgroup.gui.ItemGroupButton;
import com.glisco.owo.itemgroup.gui.ItemGroupTab;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.registry.RegisterArmor;
import nourl.mythicmetals.registry.RegisterItems;
import nourl.mythicmetals.registry.RegisterTools;
import nourl.mythicmetals.utils.RegistryHelper;

public class MythicItemGroups extends OwoItemGroup {

    protected MythicItemGroups(Identifier id) {
        super(id);
    }

    @Override
    protected void setup() {
        this.addTab(Icon.of(RegisterItems.ADAMANTITE_INGOT), "items", TagFactory.ITEM.create(RegistryHelper.id("metals")));
        this.addTab(Icon.of(MythicBlocks.ADAMANTITE.getStorageBlock()), "blocks", ItemGroupTab.EMPTY);
        this.addTab(Icon.of(RegisterTools.ADAMANTITE.getPickaxe()), "tools", TagFactory.ITEM.create(RegistryHelper.id("weapons")));
        this.addTab(Icon.of(RegisterArmor.ADAMANTITE.getChestplate()), "armor", TagFactory.ITEM.create(RegistryHelper.id("gear")));

        this.addButton(ItemGroupButton.github("https://github.com/Noaaan/MythicMetals/issues"));
        this.addButton(ItemGroupButton.curseforge("https://www.curseforge.com/minecraft/mc-mods/mythicmetals"));
        this.addButton(ItemGroupButton.modrinth("https://modrinth.com/mod/mythicmetals"));
        this.addButton(ItemGroupButton.discord("https://discord.gg/69cKvQWScC"));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegisterItems.STORMYX_INGOT);
    }
}
