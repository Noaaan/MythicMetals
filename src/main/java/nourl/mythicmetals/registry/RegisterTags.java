package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterTags {
    public static final Tag<Item> CARMOT_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("carmot_armor"));
    public static final Tag<Item> COPPER_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("copper_armor"));
    public static final Tag<Item> PALLADIUM_ARMOR = TagFactory.ITEM.create(RegistryHelper.id("palladium_armor"));

    public static final Tag<Block> WOOD_MINING_LEVEL = TagFactory.BLOCK.create(RegistryHelper.id("wood_mining_level"));
    public static final Tag<Block> STONE_MINING_LEVEL = TagFactory.BLOCK.create(RegistryHelper.id("stone_mining_level"));
    public static final Tag<Block> IRON_MINING_LEVEL = TagFactory.BLOCK.create(RegistryHelper.id("iron_mining_level"));
    public static final Tag<Block> DIAMOND_MINING_LEVEL = TagFactory.BLOCK.create(RegistryHelper.id("diamond_mining_level"));
    public static final Tag<Block> NETHERITE_MINING_LEVEL = TagFactory.BLOCK.create(RegistryHelper.id("netherite_mining_level"));
    public static final Tag<Block> MYTHIC_METAL_MINING_LEVEL = TagFactory.BLOCK.create(RegistryHelper.id("mythic_mining_level"));

}
