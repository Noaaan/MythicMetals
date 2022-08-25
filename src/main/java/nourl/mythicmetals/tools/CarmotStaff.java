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
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.data.MythicTags;
import nourl.mythicmetals.registry.CustomDamageSource;
import nourl.mythicmetals.registry.RegisterEntityAttributes;
import nourl.mythicmetals.registry.RegisterSounds;
import nourl.mythicmetals.utils.MythicParticleSystem;

import java.util.List;
import java.util.UUID;

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
                if (blockItem.getRegistryEntry().isIn(MythicTags.CARMOT_STAFF_BLOCKS)) {
                    staff.put(STORED_BLOCK, blockItem.getBlock());
                    slot.takeStack(1);
                    player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), SoundCategory.PLAYERS, 0.85F, 0.5F);
                    return true;
                } else return false;
            }

            if (slot.getStack().getItem() instanceof BlockItem blockItem && blockItem.getRegistryEntry().isIn(MythicTags.CARMOT_STAFF_BLOCKS)) {
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
                if (blockItem.getRegistryEntry().isIn(MythicTags.CARMOT_STAFF_BLOCKS) && cursorStack.getCount() == 1) {
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
                if (blockItem.getRegistryEntry().isIn(MythicTags.CARMOT_STAFF_BLOCKS) && cursorStack.getCount() == 1) {
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
        if (world.isClient()) return TypedActionResult.fail(stack);
        boolean isCoolingDown = user.getItemCooldownManager().isCoolingDown(stack.getItem());

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

            return TypedActionResult.success(stack);
        }

        // Command Block - Set yourself to Creative Mode
        if (hasBlockInStaff(stack, Blocks.COMMAND_BLOCK)) {

            if (!MythicMetals.CONFIG.disableFunny) {
                ((ServerPlayerEntity) user).changeGameMode(GameMode.CREATIVE);
                user.getItemCooldownManager().set(stack.getItem(), 6000);
            } else {
                world.createExplosion(null, new CustomDamageSource("ascension"), null, user.getX(), user.getY(), user.getZ(), 20.0F, false, Explosion.DestructionType.NONE);
                user.getItemCooldownManager().set(stack.getItem(), 6000);
            }

            stack.damage(100, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            return TypedActionResult.success(stack);
        }

        // Copper - Summon a lightning bolt on yourself
        if (hasBlockInStaff(stack, Blocks.COPPER_BLOCK)) {
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
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 3, 2, 3));
            entities.forEach(entity -> {
                if (entity.isLiving()) {
                    if (((LivingEntity) entity).isUndead()) {
                        entity.damage(DamageSource.MAGIC, 5.0F);
                    } else {
                        ((LivingEntity) entity).heal(5.0F);
                    }
                }
            });
            user.heal(6.0F);
            stack.damage(8, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
            user.getItemCooldownManager().set(stack.getItem(), 700);
            return TypedActionResult.success(stack);
        }

        // Midas Gold - Luck 2 for four minutes
        if (hasBlockInStaff(stack, MythicBlocks.MIDAS_GOLD.getStorageBlock())) {
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 3, 2, 3));
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

        // Runite - Ice Barrage, AOE freeze
        if (hasBlockInStaff(stack, MythicBlocks.RUNITE.getStorageBlock())) {
            boolean succesfulFreeze = false;
            float range = 10.0F;

            Vec3d normalizedFacing = user.getRotationVec(1.0F);
            Vec3d denormalizedFacing = user.getCameraPosVec(0).add(normalizedFacing.multiply(range));

            var barrageBox = user.getBoundingBox().stretch(normalizedFacing.multiply(range)).expand(1);

            EntityHitResult res = ProjectileUtil.raycast(user, user.getCameraPosVec(0), denormalizedFacing,
                    barrageBox,
                    entity -> entity.collides() &&
                            !entity.isSpectator() &&
                            entity.isLiving() &&
                            ((LivingEntity) entity).isMobOrPlayer(),
                    range * range);

            if (res != null) {
                var target = res.getEntity();
                var entities = world.getOtherEntities(target, Box.of(target.getPos(), 5, 2, 5));
                entities.add(target);

                world.playSound(null, user.getBlockPos(), RegisterSounds.ICE_MAGIC, SoundCategory.PLAYERS, 0.8F, 1.0F);
                MythicParticleSystem.ICE_TRAIL.spawn(world, user.getCameraPosVec(0), target.getCameraPosVec(0));

                for (net.minecraft.entity.Entity e : entities) {

                    LivingEntity victim = (LivingEntity) e;

                    if (victim.isLiving()) {
                        int damageRoll = random.nextInt(4);
                        if (damageRoll > 0 || random.nextInt(10) == 0) {
                            int time = victim.canFreeze() ? 600 : 150;
                            victim.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, time, victim.canFreeze() ? 3 : 0));
                            MythicParticleSystem.ICE_BARRAGE.spawn(world, victim.getPos());
                            world.playSound(null, victim.getBlockPos(), SoundEvents.BLOCK_POWDER_SNOW_BREAK, SoundCategory.PLAYERS, 1.25F, 0.85F);
                            victim.setFrozenTicks(time);
                            victim.damage(DamageSource.FREEZE, damageRoll + 1);
                            succesfulFreeze = true;
                        } else {
                            world.playSound(null, victim.getBlockPos(), SoundEvents.ENTITY_DOLPHIN_SPLASH, SoundCategory.PLAYERS, 1.0F, 0.8F);
                        }
                    }
                }

                if (succesfulFreeze) {
                    stack.damage(12, user, e2 -> e2.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    user.getItemCooldownManager().set(stack.getItem(), 400);
                } else {
                    user.getItemCooldownManager().set(stack.getItem(), 300);
                }


                return TypedActionResult.success(stack);
            }
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int amount = 1;

        // Bronze - Smite your opponent with a lightning bolt
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

        // Iron - Flings your opponent into the air
        if (hasBlockInStaff(stack, Blocks.IRON_BLOCK) && isNotOnCooldown(attacker, stack)) {
            target.addVelocity(0, 0.64, 0);
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

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {

        var mapnite = HashMultimap.create(this.getAttributeModifiers(slot));

        var block = getBlockInStaff(stack);

        if (block != Blocks.AIR) {
            float damage = 3.0F;
            float speed = -4.0F;
            float experience = 0.0F;

            if (Blocks.IRON_BLOCK.equals(block)) {
                damage = 7.0F;
                speed += 0.9F;
            } else if (Blocks.DIAMOND_BLOCK.equals(block)) {
                damage = 9.0F;
                speed += 0.8F;
            } else if (Blocks.LAPIS_BLOCK.equals(block)) {
                damage = 3.5F;
                speed += 1.0F;
                experience = slot == EquipmentSlot.MAINHAND ? 1.0F : .25F;
            } else if (Blocks.NETHERITE_BLOCK.equals(block)) {
                damage = 11.0F;
                speed += 0.6F;
            } else if (MythicBlocks.METALLURGIUM.getStorageBlock().equals(block)) {
                damage = 14.0F;
                speed += 0.5F;
            } else if (MythicBlocks.STAR_PLATINUM.getStorageBlock().equals(block)) {
                damage = 4.0F;
                speed += 3.0F;
            } else {
                speed += 1.0F;
            }

            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);
            mapnite.removeAll(RegisterEntityAttributes.EXPERIENCE_BOOST);

            mapnite.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Item.ATTACK_DAMAGE_MODIFIER_ID, "Damage modifier", damage, EntityAttributeModifier.Operation.ADDITION));
            mapnite.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(Item.ATTACK_SPEED_MODIFIER_ID, "Attack speed modifier", speed, EntityAttributeModifier.Operation.ADDITION));
            mapnite.put(RegisterEntityAttributes.EXPERIENCE_BOOST, new EntityAttributeModifier(UUID.fromString("5a902603-f288-4a12-bf13-4e0c1a12f6cd"), "Bonus Experience", experience, EntityAttributeModifier.Operation.MULTIPLY_BASE));

            if (Blocks.LAPIS_BLOCK.equals(block) && slot == EquipmentSlot.OFFHAND) {
                mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_SPEED);
                return mapnite;
            }
        }

        return slot == EquipmentSlot.MAINHAND ? mapnite : super.getAttributeModifiers(slot);
    }

    public boolean hasBlockInStaff(ItemStack stack, Block block) {
        return stack.has(STORED_BLOCK) && stack.get(STORED_BLOCK).equals(block);
    }

    public Block getBlockInStaff(ItemStack stack) {
        return (stack.has(STORED_BLOCK) ? stack.get(STORED_BLOCK) : Blocks.AIR);
    }
}
