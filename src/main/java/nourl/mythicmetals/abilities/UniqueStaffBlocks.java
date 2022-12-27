package nourl.mythicmetals.abilities;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.tools.CarmotStaff;

import java.util.HashMap;
import java.util.Map;

public class UniqueStaffBlocks {
    /**
     * Map used to store the different types of Carmot Staff Blocks
     * Used for tooltip handling
     */
    public static final Map<Block, String> MAP = new HashMap<>();

    @SuppressWarnings("deprecation")
    public static boolean hasUniqueBlockInStaff(ItemStack stack) {
        if (stack.has(CarmotStaff.STORED_BLOCK)) {
            return stack.get(CarmotStaff.STORED_BLOCK).asItem().getRegistryEntry().isIn(MythicTags.CARMOT_STAFF_BLOCKS);
        }
        return false;
    }

    public static Text getBlockTranslationKey(Block block) {
        if (!MAP.containsKey(block)) {
            return Text.translatable("tooltip.carmot_staff.unique").setStyle(Style.EMPTY.withColor(0xE63E73));
        }
        return Text.translatable("tooltip.carmot_staff." + UniqueStaffBlocks.MAP.get(block)).setStyle(Style.EMPTY.withColor(0xE63E73));
    }

    public static void init() {
        MAP.put(Blocks.COPPER_BLOCK, "copper");
        MAP.put(Blocks.IRON_BLOCK, "iron");
        MAP.put(Blocks.GOLD_BLOCK, "gold");
        MAP.put(Blocks.SPONGE, "sponge");
        MAP.put(Blocks.COMMAND_BLOCK, "command_block");
        MAP.put(MythicBlocks.BRONZE.getStorageBlock(), "copper");
        MAP.put(MythicBlocks.CARMOT.getStorageBlock(), "carmot");
        MAP.put(MythicBlocks.MIDAS_GOLD.getStorageBlock(), "midas_gold");
        MAP.put(MythicBlocks.STORMYX.getStorageBlock(), "stormyx");
    }
}
