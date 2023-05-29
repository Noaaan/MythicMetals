package nourl.mythicmetals.item.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.nbt.NbtKey;
import io.wispforest.owo.ops.WorldOps;
import io.wispforest.owo.ui.core.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.abilities.UniqueStaffBlocks;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.client.rendering.CarmotStaffBlockRenderer;
import nourl.mythicmetals.misc.EpicExplosion;
import nourl.mythicmetals.misc.MythicDamageTypes;
import nourl.mythicmetals.misc.MythicParticleSystem;
import nourl.mythicmetals.misc.RegistryHelper;
import nourl.mythicmetals.registry.RegisterSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class CarmotStaff extends ToolItem {

    /**
     * Contains the block stored inside the staff.
     * This is rendered via the {@link CarmotStaffBlockRenderer}
     */
    public static final NbtKey<Block> STORED_BLOCK = new NbtKey<>("StoredBlock", NbtKey.Type.ofRegistry(Registries.BLOCK));

    /**
     * NBT Key that determines whether the staff is actively being used
     */
    public static final NbtKey<Boolean> IS_USED = new NbtKey<>("IsUsed", NbtKey.Type.BOOLEAN);
    /**
     * NBT Key that prevents the staff from inserting blocks
     */
    public static final NbtKey<Boolean> LOCKED = new NbtKey<>("Locked", NbtKey.Type.BOOLEAN);

    public static final Identifier PROJECTILE_MODIFIED = RegistryHelper.id("projectile_is_modified");

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
        // Check if item is a BlockItem, if not try empty
        if (clickType == ClickType.RIGHT) {
            // Validation - Cannot be used if locked
            // You cannot insert blocks with NBT, since the staff wipes it, and frankly
            // I do not want to deal with nesting staves inside shulker boxes with portable barrels inside or something
            if (staff.has(LOCKED) && staff.get(LOCKED)) return false;
            if (slot.getStack().getItem() instanceof BlockItem blockItem && !slot.getStack().hasNbt()) {

                boolean validStaffBlock = validateStaffBlock(blockItem);
                if (!staff.has(STORED_BLOCK) && !slot.getStack().isEmpty()) {

                    // Try to put block in staff
                    if (validStaffBlock) {
                        staff.put(STORED_BLOCK, blockItem.getBlock());
                        slot.takeStack(1);
                        player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.PLAYERS, 0.85F, 0.5F);
                        return true;
                    } else {
                        return false;
                    }
                }

                // If Staff is not empty, try swapping the blocks
                if (validStaffBlock) {
                    // Don't bother swapping if the block is the same, or if block is larger than 1
                    if (getBlockInStaff(staff).equals(blockItem.getBlock())) return false;
                    if (slot.getStack().getCount() > 1) return false;

                    // Try to replace block in staff
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
            }

            // Try empty block into inventory
            if (slot.getStack().isEmpty()) {
                slot.insertStack(staff.get(STORED_BLOCK).asItem().getDefaultStack());
                staff.delete(STORED_BLOCK);
                player.playSound(RegisterSounds.CARMOT_STAFF_EMPTY, SoundCategory.PLAYERS, 0.85F, 0.5F);
                return true;
            }

        }
        return false;
    }


    @Override
    public boolean onClicked(ItemStack staff, ItemStack cursorStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            if ((staff.has(LOCKED) && staff.get(LOCKED)) || cursorStack.hasNbt()) return false;
            // If cursor is empty, but staff has block, take block out of staff
            if (cursorStackReference.get().isEmpty() && staff.has(STORED_BLOCK)) {
                if (cursorStackReference.set(staff.get(STORED_BLOCK).asItem().getDefaultStack())) {
                    staff.delete(STORED_BLOCK);
                    player.playSound(RegisterSounds.CARMOT_STAFF_EMPTY, SoundCategory.PLAYERS, 0.25F, 0.5F);
                    return true;
                }
                return false;
            }

            // Check if item is a BlockItem, and if its from Minecraft or MythicMetals
            if (!(cursorStackReference.get().getItem() instanceof BlockItem blockItem)) return false;
            boolean validStaffBlock = validateStaffBlock(blockItem);

            // If staff has block, and cursor has valid block, swap them
            if (staff.has(STORED_BLOCK)) {
                if (validStaffBlock && cursorStack.getCount() == 1) {
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
            if (!staff.has(STORED_BLOCK)) {
                if (validStaffBlock && cursorStack.getCount() >= 1) {
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
        var random = Random.create();
        var stack = user.getStackInHand(hand);
        boolean isCoolingDown = user.getItemCooldownManager().isCoolingDown(stack.getItem());

        // Stormyx - Rainbow Shield that blocks projectiles around you
        if (hasBlockInStaff(stack, MythicBlocks.STORMYX.getStorageBlock()) && user.getMainHandStack().equals(stack)) {
            user.setCurrentHand(hand);
            WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_BEGIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
            return TypedActionResult.consume(stack);
        }

        if (world.isClient()) return TypedActionResult.fail(stack);


        if (isCoolingDown) return TypedActionResult.fail(stack);

        // Beacon - Give a small buff to all players around you
        if (hasBlockInStaff(stack, Blocks.BEACON)) {
            var buffList = List.of(
                    StatusEffects.SPEED,
                    StatusEffects.HASTE,
                    StatusEffects.RESISTANCE,
                    StatusEffects.JUMP_BOOST,
                    StatusEffects.STRENGTH,
                    StatusEffects.REGENERATION);
            var buff = random.nextInt(5);

            var targets = world.getOtherEntities(user, Box.of(user.getPos(), 18, 5, 18));
            targets.add(user);

            targets.forEach(entity -> {
                if (entity.isPlayer()) {
                    ((PlayerEntity) entity)
                            .addStatusEffect(new StatusEffectInstance(
                                    buffList.get(buff),
                                    600,
                                    0,
                                    true,
                                    true,
                                    true));
                }
            });

            user.getItemCooldownManager().set(stack.getItem(), 500);
            stack.damage(10, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            WorldOps.playSound(world, user.getBlockPos(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            return TypedActionResult.success(stack);
        }

        // Command Block - Set yourself to Creative Mode
        if (hasBlockInStaff(stack, Blocks.COMMAND_BLOCK)) {
            // If command blocks are disabled, then no man should have that much power
            if (world.getServer() != null && !world.getServer().areCommandBlocksEnabled()) {
                user.sendMessage(Text.translatable("advMode.notEnabled"));
                return TypedActionResult.fail(stack);
            }

            // Let modpack authors disable funny Easter eggs that should in practice NEVER occur
            if (!MythicMetals.CONFIG.disableFunny()) {

                // Overload the staff if it is unbreakable, its simply too much power
                if (stack.getNbt() != null && stack.getNbt().getBoolean("Unbreakable")) {
                    stack.getNbt().remove("Unbreakable");
                    stack.getEnchantments().clear();
                    stack.setDamage(MythicToolMaterials.CARMOT.getDurability());
                    stack.damage(99999, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    user.getItemCooldownManager().set(stack.getItem(), 6000);
                    explode(world, user);
                    return TypedActionResult.consume(stack);
                }

                stack.damage(100, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                user.getItemCooldownManager().set(stack.getItem(), 6000);
                ((ServerPlayerEntity) user).changeGameMode(GameMode.CREATIVE);
                explode(world, user);

                return TypedActionResult.success(stack);
            } else {
                user.sendMessage(Text.of("This power has been disabled"));
                return TypedActionResult.fail(stack);
            }

        }

        // Copper - Summon a lightning bolt on yourself
        if (hasBlockInStaff(stack, Blocks.COPPER_BLOCK)) {
            var lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.copyPositionAndRotation(user);
                world.spawnEntity(lightning);
                user.damage(world.getDamageSources().lightningBolt(), 3);
                user.getItemCooldownManager().set(stack.getItem(), 400);
            }

            stack.damage(3, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            return TypedActionResult.success(stack);
        }

        // Gold - Luck 1 for two minutes
        if (hasBlockInStaff(stack, Blocks.GOLD_BLOCK)) {
            var luckStatus = new StatusEffectInstance(StatusEffects.LUCK, 2400, 0, true, false, true);
            user.addStatusEffect(luckStatus);
            stack.damage(5, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            user.getItemCooldownManager().set(stack.getItem(), 1200);
            return TypedActionResult.success(stack);
        }

        // Carmot - AoE Heal
        if (hasBlockInStaff(stack, MythicBlocks.CARMOT.getStorageBlock())) {
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 4, 3, 4));
            entities.forEach(entity -> {
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity.isUndead()) {
                        entity.damage(world.getDamageSources().magic(), 10.0F);
                        MythicParticleSystem.HEALING_DAMAGE.spawn(world, livingEntity.getPos());
                    } else {
                        livingEntity.heal(10.0F);
                        MythicParticleSystem.HEALING_HEARTS.spawn(world, livingEntity.getPos());
                    }
                }
            });
            user.heal(10.0F);
            MythicParticleSystem.HEALING_HEARTS.spawn(world, user.getPos());
            stack.damage(8, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            user.getItemCooldownManager().set(stack.getItem(), 480);
            return TypedActionResult.success(stack);
        }

        // Midas Gold - Luck 2 for four minutes
        if (hasBlockInStaff(stack, MythicBlocks.MIDAS_GOLD.getStorageBlock())) {
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 4, 3, 4));
            var betterLuckStatus = new StatusEffectInstance(StatusEffects.LUCK, 4800, 1, true, false, true);
            entities.forEach(entity -> {
                if (entity.isLiving()) {
                    ((LivingEntity) entity).addStatusEffect(betterLuckStatus);
                }
            });
            user.addStatusEffect(betterLuckStatus);
            stack.damage(10, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            user.getItemCooldownManager().set(stack.getItem(), 3000);
            return TypedActionResult.success(stack);
        }

        // Sponge - Remove water
        if (hasBlockInStaff(stack, Blocks.SPONGE)) {
            if (!world.isClient) {
                EpicExplosion.absorbWater((ServerWorld) world, user.getBlockX(), user.getBlockY(), user.getBlockZ(), 12, user);
            }
            user.getItemCooldownManager().set(stack.getItem(), 138);
            stack.damage(3, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Durabilty damage amount
        int amount = 1;

        // Bronze - Smite your opponent with a lightning bolt
        if (hasBlockInStaff(stack, MythicBlocks.BRONZE.getStorageBlock()) && isNotOnCooldown(attacker, stack)) {
            var world = target.getWorld();
            var lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                lightning.copyPositionAndRotation(target);
                world.spawnEntity(lightning);
                target.damage(world.getDamageSources().lightningBolt(), 4);
                ((PlayerEntity) attacker).getItemCooldownManager().set(stack.getItem(), 500);
                amount = 5;
            }

        }

        // Iron - Flings mobs and players into the air like an Iron Golem
        if (hasBlockInStaff(stack, Blocks.IRON_BLOCK) && isNotOnCooldown(attacker, stack)) {
            double knockbackResistance = target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            double multiplier = Math.max(0.0, 1.0 - knockbackResistance);
            if (target.isPlayer()) {
                target.setVelocity(target.getVelocity().add(0.0, 0.4F * multiplier, 0.0));
            } else {
                target.addVelocity(0, 0.54 * multiplier, 0);
            }
            if (attacker.isPlayer()) {
                ((PlayerEntity) attacker).getItemCooldownManager().set(stack.getItem(), 40);
            }
            amount = 3;
        }

        // Star Platinum - eat slightly more durability at the cost of big attack speed
        if (hasBlockInStaff(stack, MythicBlocks.STAR_PLATINUM.getStorageBlock())) {
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

    /**
     * Handles the staff usage tick, which is used in conjuction with a Stormyx Block
     */
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        var blockBox = Box.of(user.getPos().add(0, 1, 0), 8, 8, 8);
        var entities = world.getOtherEntities(user, blockBox);
        if (CarmotStaff.isNotOnCooldown(user, stack)) {
            stack.put(IS_USED, true);
        }
        if (remainingUseTicks % 40 == 1) {
            WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_MAINTAIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
        }

        for (Entity entity : entities) {
            // Setting the owner of the trident to someone else would lead to shenanigans, don't do that
            if (entity instanceof TridentEntity trident) {
                var bounceVec = trident.getVelocity().multiply(-0.25, -0.25, -0.25);
                trident.setVelocity(bounceVec);
                trident.returnTimer = 0;
            }
            // Special handling for ExplosiveProjectileEntities, like fireballs
            if (entity instanceof ExplosiveProjectileEntity projectile && !projectile.getCommandTags().contains(PROJECTILE_MODIFIED.toString())) {
                var bounceVec = projectile.getVelocity().multiply(-0.25, -0.25, -0.25);
                projectile.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z, 1.05F, 0.5F);
                projectile.powerX = -projectile.powerX;
                projectile.powerY = -projectile.powerY;
                projectile.powerZ = -projectile.powerZ;
                projectile.setOwner(user);
                projectile.addCommandTag(PROJECTILE_MODIFIED.toString());
                stack.damage(2, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }
            // Shulker bullet handling
            if (entity instanceof ShulkerBulletEntity projectile && !projectile.getCommandTags().contains(PROJECTILE_MODIFIED.toString())) {
                projectile.damage(world.getDamageSources().generic(), 1.0F);
            } else if (entity instanceof ProjectileEntity projectile && !projectile.getCommandTags().contains(PROJECTILE_MODIFIED.toString())) {
                // Bounce the projectiles in the direction the player is looking
                var bounceVec = projectile.getVelocity().multiply(-0.25, -0.25, -0.25);
                projectile.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z, 1.05F, 0.5F);
                projectile.setOwner(user);
                projectile.getCommandTags().add(PROJECTILE_MODIFIED.toString());
                stack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // Handle Carmot Staff after using the Stormyx ability
        if (!world.isClient && user.isPlayer()) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack.getItem(), 240);
        }
        stack.put(IS_USED, false);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundCategory.AMBIENT, 0.9F, 1.5F);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // Handle Carmot Staff after using the Stormyx ability
        if (!world.isClient && user.isPlayer()) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack.getItem(), 320);
        }
        stack.put(IS_USED, false);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundCategory.AMBIENT, 0.9F, 1.5F);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        if (!hasBlockInStaff(stack, MythicBlocks.STORMYX.getStorageBlock())) {
            return 0;
        }
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        if (!hasBlockInStaff(stack, MythicBlocks.STORMYX.getStorageBlock())) {
            return super.getUseAction(stack);
        }
        return UseAction.BOW;
    }


    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.isOf(this);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {

        var mapnite = HashMultimap.create(this.getAttributeModifiers(slot));

        var block = getBlockInStaff(stack);

        if (block != Blocks.AIR) {
            float damage = 3.0F;
            float speed = -4.0F;
            float experience = 0.0F;

            if (Blocks.GOLD_BLOCK.equals(block)) {
                damage = 5.0F;
                speed += 1.2F;
            } else if (Blocks.IRON_BLOCK.equals(block)) {
                damage = 7.0F;
                speed += 0.9F;
            } else if (Blocks.DIAMOND_BLOCK.equals(block)) {
                damage = 9.0F;
                speed += 1.0F;
            } else if (Blocks.LAPIS_BLOCK.equals(block)) {
                damage = 4.0F;
                speed += 1.0F;
                experience = slot == EquipmentSlot.MAINHAND ? 1.0F : .25F;
            } else if (Blocks.NETHERITE_BLOCK.equals(block)) {
                damage = 11.0F;
                speed += 0.8F;
            } else if (MythicBlocks.HALLOWED.getStorageBlock().equals(block)) {
                damage = 12.0F;
                speed += 0.75F;
            } else if (MythicBlocks.ADAMANTITE.getStorageBlock().equals(block)) {
                damage = 12.0F;
                speed += 0.7F;
            } else if (MythicBlocks.METALLURGIUM.getStorageBlock().equals(block)) {
                damage = 14.0F;
                speed += 0.6F;
            } else if (MythicBlocks.STAR_PLATINUM.getStorageBlock().equals(block)) {
                damage = 4.0F;
                speed += 3.0F;
            } else {
                speed += 1.0F;
            }

            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);
            mapnite.removeAll(AdditionalEntityAttributes.DROPPED_EXPERIENCE);

            mapnite.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Item.ATTACK_DAMAGE_MODIFIER_ID, "Damage modifier", damage, EntityAttributeModifier.Operation.ADDITION));
            mapnite.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(Item.ATTACK_SPEED_MODIFIER_ID, "Attack speed modifier", speed, EntityAttributeModifier.Operation.ADDITION));
            mapnite.put(AdditionalEntityAttributes.DROPPED_EXPERIENCE, new EntityAttributeModifier(UUID.fromString("5a902603-f288-4a12-bf13-4e0c1a12f6cd"), "Bonus Experience", experience, EntityAttributeModifier.Operation.MULTIPLY_BASE));

            if (Blocks.LAPIS_BLOCK.equals(block) && slot == EquipmentSlot.OFFHAND) {
                mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);
                return mapnite;
            }
        }

        return slot == EquipmentSlot.MAINHAND ? mapnite : super.getAttributeModifiers(slot);
    }

    public static boolean hasBlockInStaff(ItemStack stack, Block block) {
        return stack.has(STORED_BLOCK) && stack.get(STORED_BLOCK).equals(block);
    }

    public Block getBlockInStaff(ItemStack stack) {
        return (stack.has(STORED_BLOCK) ? stack.get(STORED_BLOCK) : Blocks.AIR);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!((PlayerEntity) entity).getEquippedStack(EquipmentSlot.MAINHAND).equals(stack) && stack.has(IS_USED) && stack.get(IS_USED)) {
            finishUsing(stack, world, (LivingEntity) entity);
        }
    }

    @SuppressWarnings("deprecation")
    private boolean validateStaffBlock(BlockItem item) {
        if (item.getRegistryEntry().getKey().isEmpty()) {
            return false;
        }
        boolean b1 = item.getRegistryEntry().getKey().get().getValue().getNamespace().equals("minecraft");
        boolean b2 = item.getRegistryEntry().getKey().get().getValue().getNamespace().equals("mythicmetals");
        return b1 || b2;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (!stack.has(STORED_BLOCK)) {
            tooltip.add(1, Text.translatable("tooltip.carmot_staff.empty_staff").setStyle(Style.EMPTY.withColor(Color.ofDye(DyeColor.LIGHT_GRAY).rgb())));
        } else {
            int index = 1;
            if (UniqueStaffBlocks.hasUniqueBlockInStaff(stack)) {
                tooltip.add(index++, UniqueStaffBlocks.getBlockTranslationKey(stack.get(STORED_BLOCK)));
            }

            if (stack.get(LOCKED)) {
                tooltip.add(index, Text.translatable("tooltip.carmot_staff.locked").setStyle(Style.EMPTY.withColor(Formatting.YELLOW)));
            }
        }
    }

    public void explode(World world, LivingEntity user) {
        world.createExplosion(
                null,
                new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).getEntry(MythicDamageTypes.ASCENSION).orElseThrow()),
                null,
                user.getPos(),
                20.0F,
                false,
                World.ExplosionSourceType.NONE);
    }

}
