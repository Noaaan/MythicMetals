package nourl.mythicmetals.item;

import com.google.common.collect.ImmutableSet;
import io.wispforest.owo.nbt.NbtKey;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import net.minecraft.util.registry.Registry;
import nourl.mythicmetals.blocks.MythicBlocks;

import java.util.Set;

public class CarmotStaff extends ToolItem {

    public static final NbtKey<Block> STORED_BLOCK = new NbtKey<>("stored_block", NbtKey.Type.ofRegistry(Registry.BLOCK));
    public static final Set<Block> LEGAL_BLOCKS = Set.of(
            Blocks.NETHERITE_BLOCK,
            Blocks.DIAMOND_BLOCK,
            Blocks.EMERALD_BLOCK,
            Blocks.GOLD_BLOCK,
            MythicBlocks.UNOBTAINIUM.getStorageBlock());

    public CarmotStaff(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public boolean onStackClicked(ItemStack staff, Slot slot, ClickType clickType, PlayerEntity player) {

        if (clickType == ClickType.RIGHT) {
            if (!staff.has(STORED_BLOCK) && !slot.getStack().isEmpty() && slot.getStack().getItem() instanceof BlockItem blockItem) {
                // Try put block in staff
                if (LEGAL_BLOCKS.contains(blockItem.getBlock())) {
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    slot.takeStack(1);
                    return true;
                } else return false;
            }

            if (slot.getStack().getItem() instanceof BlockItem blockItem && LEGAL_BLOCKS.contains(blockItem.getBlock())) {
                // Try replace block in staff
                if (slot.tryTakeStackRange(1, 1, player).isPresent()) {
                    var staffBlock = staff.get(STORED_BLOCK).asItem().getDefaultStack();
                    slot.takeStack(1);
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    slot.insertStack(staffBlock, 1);
                    return true;

                }
                return false;

            }
            // Try empty block into inventory
            if (slot.getStack().isEmpty()) {
                slot.insertStack(staff.get(STORED_BLOCK).asItem().getDefaultStack());
                staff.delete(STORED_BLOCK);
                return true;
            }

        }
        return false;
    }


    @Override
    public boolean onClicked(ItemStack staff, ItemStack cursorStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            // If cursor is empty, but staff has block, take block out of staff
            if (cursorStackReference.get().isEmpty() && staff.has(STORED_BLOCK)) {
                if (cursorStackReference.set(staff.get(STORED_BLOCK).asItem().getDefaultStack())) {
                    staff.delete(STORED_BLOCK);
                    return true;
                }
                return false;
            }

            // If staff has block, and cursor has valid block, swap them
            if (staff.has(STORED_BLOCK) && cursorStackReference.get().getItem() instanceof BlockItem blockItem) {
                if (LEGAL_BLOCKS.contains(blockItem.getBlock()) && cursorStack.getCount() == 1) {
                    if (cursorStackReference.set(staff.get(STORED_BLOCK).asItem().getDefaultStack())) {
                        staff.delete(STORED_BLOCK);
                        staff.put(STORED_BLOCK, blockItem.getBlock());
                        return true;
                    }
                    return false;

                }
            }

            // If staff is empty, but cursor has valid block, put it into staff
            if (!staff.has(STORED_BLOCK) && cursorStackReference.get().getItem() instanceof BlockItem blockItem) {
                if (LEGAL_BLOCKS.contains(blockItem.getBlock()) && cursorStack.getCount() == 1) {
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    cursorStack.decrement(1);
                    return true;
                }
            }
        }
        return false;
    }
}
