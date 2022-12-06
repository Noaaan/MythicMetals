package nourl.mythicmetals.tools;

import io.wispforest.owo.nbt.NbtKey;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.utils.UselessSingletonForColorUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MythrilDrill extends PickaxeItem {
    /**
     * NbtKey that stores the amount of fuel inside the drill
     */
    public static final NbtKey<Integer> FUEL = new NbtKey<>("Fuel", NbtKey.Type.INT);
    /**
     * NbtKey that determines whether or not the drill should consume fuel and mine faster
     */
    public static final NbtKey<Boolean> IS_ACTIVE = new NbtKey<>("IsActive", NbtKey.Type.BOOLEAN);
    /**
     * A fully fueled drill should last 30 minutes
     */
    public static final int MAX_FUEL = 1000;

    public MythrilDrill(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        // Put the bool in there for the first time
        if (!stack.has(IS_ACTIVE)) {
            stack.put(IS_ACTIVE, false);
        }
        // If you have fuel, toggle the state of the drill
        if (hasFuel(stack)) {
            var sound = stack.get(IS_ACTIVE) ? SoundEvents.BLOCK_CONDUIT_DEACTIVATE : SoundEvents.BLOCK_CONDUIT_ACTIVATE;
            user.playSound(sound, SoundCategory.PLAYERS, 1.0f, 1.0f);
            stack.put(IS_ACTIVE, !stack.get(IS_ACTIVE));
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean onStackClicked(ItemStack drill, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType == ClickType.RIGHT) {
            // If right-clicking Drill onto Morkite, try to fuel it
            if (slot.getStack().getItem().equals(MythicItems.Ingots.MORKITE)) {
                int morkiteCount = slot.getStack().getCount();
                if (slot.tryTakeStackRange(MAX_FUEL - drill.get(FUEL), morkiteCount, player).isPresent()) {
                    int fuel = MathHelper.clamp(drill.get(FUEL) + (morkiteCount * 10), 0, MAX_FUEL);
                    drill.put(FUEL, fuel);
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean onClicked(ItemStack drill, ItemStack cursorStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            // If right-clicking with Morkite on Drill, try to fuel it
            if (cursorStack.getItem().equals(MythicItems.Ingots.MORKITE)) {
                // Dont bother interacting if the Drills fuel is full
                if (drill.get(FUEL).equals(MAX_FUEL)) return false;

                int morkiteCount = cursorStack.getCount();
                // Greedily take all the morkite if we can, otherwise calculate how much to take
                if (morkiteCount * 10 < (MAX_FUEL) - drill.get(FUEL)) {
                    int fuel = MathHelper.clamp(drill.get(FUEL) + (morkiteCount * 10), 0, MAX_FUEL);
                    cursorStack.decrement(morkiteCount);
                    drill.put(FUEL, fuel);
                    return true;
                }
                // Manually calculate how much Morkite to take
                if (morkiteCount * 10 >= (MAX_FUEL) - drill.get(FUEL)) {
                    int morkiteToTake = (MAX_FUEL / 10) - (drill.get(FUEL) / 10);
                    int fuel = MathHelper.clamp(drill.get(FUEL) + (morkiteToTake * 10), 0, MAX_FUEL);
                    cursorStack.decrement(morkiteToTake);
                    drill.put(FUEL, fuel);
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            // Randomly cancel damage while active
            if (isActive(stack) && world.getRandom().nextInt(10) > 5) return true;
            stack.damage(1, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    public boolean hasFuel(ItemStack stack) {
        return stack.has(FUEL) && stack.get(FUEL) > 0;
    }

    public boolean isActive(ItemStack stack) {
        return stack.has(IS_ACTIVE) && stack.get(IS_ACTIVE);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        int fuel = stack.has(FUEL) ? stack.get(FUEL) : 0;
        if (isActive(stack)) {
            tooltip.add(1, Text.translatable("tooltip.mythril_drill.activated").formatted(Formatting.BLUE));
        } else {
            tooltip.add(1, Text.translatable("tooltip.mythril_drill.deactivated").formatted(Formatting.GRAY, Formatting.ITALIC));
        }
        tooltip.add(1, Text.translatable("tooltip.mythril_drill.fuel", fuel, MAX_FUEL)
                .fillStyle(Style.EMPTY.withColor(UselessSingletonForColorUtil.getSlightlyDarkerOwoBlueToRedGradient(fuel, MAX_FUEL))));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && hasFuel(stack) && isActive(stack) && MathHelper.floor(world.getTime()) % 36 == 1) {
            stack.put(FUEL, stack.get(FUEL) - 1);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }


    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.get(FUEL).equals(newStack.get(FUEL));
    }

    @Override
    public boolean allowContinuingBlockBreaking(PlayerEntity player, ItemStack oldStack, ItemStack newStack) {
        return !oldStack.get(FUEL).equals(newStack.get(FUEL));
    }

    @Override
    public boolean isSuitableFor(ItemStack stack, BlockState state) {
        if (isActive(stack)) {
            if (state.isIn(BlockTags.SHOVEL_MINEABLE) && this.getMaterial().getMiningLevel() >= MiningLevelManager.getRequiredMiningLevel(state))
                return true;
        }
        return super.isSuitableFor(stack, state);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (isActive(stack)) {
            if (state.isIn(BlockTags.SHOVEL_MINEABLE) && this.getMaterial().getMiningLevel() >= MiningLevelManager.getRequiredMiningLevel(state))
                return this.miningSpeed;
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }
}
