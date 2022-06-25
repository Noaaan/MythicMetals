package nourl.mythicmetals.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.wispforest.owo.nbt.NbtKey;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.registry.RegisterTags;

@SuppressWarnings("deprecation")
public class CarmotStaff extends ToolItem {

    public static final NbtKey<Block> STORED_BLOCK = new NbtKey<>("StoredBlock", NbtKey.Type.ofRegistry(Registry.BLOCK));

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public CarmotStaff(ToolMaterial material, float attackSpeed, Settings settings) {
        super(material, settings);
        float attackDamage = material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", attackDamage, EntityAttributeModifier.Operation.ADDITION)
        );
        builder.put(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION)
        );
        this.attributeModifiers = builder.build();
    }

    @Override
    public boolean onStackClicked(ItemStack staff, Slot slot, ClickType clickType, PlayerEntity player) {

        if (clickType == ClickType.RIGHT) {
            if (!staff.has(STORED_BLOCK) && !slot.getStack().isEmpty() && slot.getStack().getItem() instanceof BlockItem blockItem) {
                // Try put block in staff
                if (blockItem.getRegistryEntry().isIn(RegisterTags.CARMOT_STAFF_BLOCKS)) {
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    slot.takeStack(1);
                    player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.PLAYERS, 0.85F, 0.5F);
                    return true;
                } else return false;
            }

            if (slot.getStack().getItem() instanceof BlockItem blockItem && blockItem.getRegistryEntry().isIn(RegisterTags.CARMOT_STAFF_BLOCKS)) {
                // Try replace block in staff
                if (slot.tryTakeStackRange(1, 1, player).isPresent()) {
                    var staffBlock = staff.get(STORED_BLOCK).asItem().getDefaultStack();
                    slot.takeStack(1);
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    slot.insertStack(staffBlock, 1);
                    player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.PLAYERS, 0.85F, 0.5F);
                    return true;

                }
                return false;

            }
            // Try empty block into inventory
            if (slot.getStack().isEmpty()) {
                slot.insertStack(staff.get(STORED_BLOCK).asItem().getDefaultStack());
                staff.delete(STORED_BLOCK);
                player.playSound(SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.PLAYERS, 0.85F, 0.5F);
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
                    player.playSound(SoundEvents.ENTITY_GLOW_ITEM_FRAME_REMOVE_ITEM, SoundCategory.PLAYERS, 0.25F, 0.5F);
                    return true;
                }
                return false;
            }

            // If staff has block, and cursor has valid block, swap them
            if (staff.has(STORED_BLOCK) && cursorStackReference.get().getItem() instanceof BlockItem blockItem) {
                if (blockItem.getRegistryEntry().isIn(RegisterTags.CARMOT_STAFF_BLOCKS) && cursorStack.getCount() == 1) {
                    if (cursorStackReference.set(staff.get(STORED_BLOCK).asItem().getDefaultStack())) {
                        staff.delete(STORED_BLOCK);
                        staff.put(STORED_BLOCK, blockItem.getBlock());
                        player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.PLAYERS, 0.85F, 0.5F);
                        return true;
                    }
                    return false;

                }
            }

            // If staff is empty, but cursor has valid block, put it into staff
            if (!staff.has(STORED_BLOCK) && cursorStackReference.get().getItem() instanceof BlockItem blockItem) {
                if (blockItem.getRegistryEntry().isIn(RegisterTags.CARMOT_STAFF_BLOCKS) && cursorStack.getCount() == 1) {
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    cursorStack.decrement(1);
                    player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.PLAYERS, 0.85F, 0.5F);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        if (world.isClient()) return TypedActionResult.fail(stack);
        boolean isCoolingDown = user.getItemCooldownManager().isCoolingDown(stack.getItem());
        if (hasBlockInStaff(stack, Blocks.COPPER_BLOCK) && !isCoolingDown) {
            var lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.copyPositionAndRotation(user);
                world.spawnEntity(lightning);
                user.damage(DamageSource.LIGHTNING_BOLT, 3);
                user.getItemCooldownManager().set(stack.getItem(), 400);
            }

            stack.damage(3, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            return TypedActionResult.success(stack);
        }

        if (hasBlockInStaff(stack, Blocks.GOLD_BLOCK) && !isCoolingDown) {
            var luckStatus = new StatusEffectInstance(StatusEffects.LUCK, 2400, 0, true, false, true);
            user.addStatusEffect(luckStatus);
            stack.damage(5, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            user.getItemCooldownManager().set(stack.getItem(), 1200);
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int amount = 1;
        if (hasBlockInStaff(stack, MythicBlocks.BRONZE.getStorageBlock()) && isNotOnCooldown(attacker, stack)) {
            var world = target.getWorld();
            var lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.copyPositionAndRotation(target);
                world.spawnEntity(lightning);
                target.damage(DamageSource.LIGHTNING_BOLT, 4);
                ((PlayerEntity) attacker).getItemCooldownManager().set(stack.getItem(), 500);
                amount = 5;
            }

        }
        if (hasBlockInStaff(stack, Blocks.IRON_BLOCK) && isNotOnCooldown(attacker, stack)) {
            target.addVelocity(0, 0.64, 0);
            if (attacker.isPlayer()) {
                ((PlayerEntity) attacker).getItemCooldownManager().set(stack.getItem(), 40);
            }
            amount = 3;
        }
        stack.damage(amount, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            stack.damage(2, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    public static boolean isNotOnCooldown(LivingEntity entity, ItemStack stack) {
        if (entity != null && entity.isPlayer()) {
            return !((PlayerEntity) entity).getItemCooldownManager().isCoolingDown(stack.getItem());
        }
        return true;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {

        Multimap<EntityAttribute, EntityAttributeModifier> mapnite = this.getAttributeModifiers(slot);

        var block = getBlockInStaff(stack);

        if (block != Blocks.AIR) {
            float damage = 3.0F;
            float speed = -4.0F;

            if (Blocks.IRON_BLOCK.equals(block)) {
                damage = 7.0F;
                speed += 0.9F;
            } else if (Blocks.DIAMOND_BLOCK.equals(block)) {
                damage = 9.0F;
                speed += 0.8F;
            } else if (Blocks.NETHERITE_BLOCK.equals(block)) {
                damage = 11.0F;
                speed += 0.6F;
            } else if (MythicBlocks.METALLURGIUM.getStorageBlock().equals(block)) {
                damage = 14.0F;
                speed += 0.5F;
            }
            else {
                speed += 1.0F;
            }

            mapnite = HashMultimap.create(mapnite);

            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);

            mapnite.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Item.ATTACK_DAMAGE_MODIFIER_ID, "Damage modifier", damage, EntityAttributeModifier.Operation.ADDITION));
            mapnite.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(Item.ATTACK_SPEED_MODIFIER_ID, "Attack speed modifier", speed, EntityAttributeModifier.Operation.ADDITION));

        }
        return slot == EquipmentSlot.MAINHAND ? mapnite : super.getAttributeModifiers(slot);
    }

    private boolean hasBlockInStaff(ItemStack stack, Block block) {
        return stack.has(STORED_BLOCK) && stack.get(STORED_BLOCK).equals(block);
    }

    private Block getBlockInStaff(ItemStack stack) {
        return (stack.has(STORED_BLOCK) ? stack.get(STORED_BLOCK) : Blocks.AIR);
    }
}
