package nourl.mythicmetals.item.tools;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import io.wispforest.owo.ops.WorldOps;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.blocks.MythicBlocks;
import nourl.mythicmetals.component.CarmotStaffComponent;
import nourl.mythicmetals.component.MythicDataComponents;
import nourl.mythicmetals.misc.*;
import nourl.mythicmetals.registry.RegisterCriteria;
import nourl.mythicmetals.registry.RegisterSounds;
import java.util.List;

import static nourl.mythicmetals.component.MythicDataComponents.*;

public class CarmotStaff extends ToolItem {

    public static final Identifier PROJECTILE_MODIFIED = RegistryHelper.id("projectile_is_modified");

    public CarmotStaff(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public static AttributeModifiersComponent createDefaultAttributes(double damage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, damage, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .add(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -4.0f + attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .build();
    }

    public static AttributeModifiersComponent createAttributesWithXp(double damage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
            .add(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, damage, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .add(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -4.0f + attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
            .add(AdditionalEntityAttributes.DROPPED_EXPERIENCE,
                new EntityAttributeModifier(RegistryHelper.id("carmot_staff_xp_bonus"), 1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                AttributeModifierSlot.MAINHAND
            )
            .add(AdditionalEntityAttributes.DROPPED_EXPERIENCE,
                new EntityAttributeModifier(RegistryHelper.id("carmot_staff_offhand_xp_bonus"), 0.3, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                AttributeModifierSlot.OFFHAND
            )
            .build();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        if (stack.contains(CARMOT_STAFF_BLOCK)) {
            stack.get(CARMOT_STAFF_BLOCK).appendTooltip(context, tooltip::add, type);
        }

        if (stack.getOrDefault(LOCKED, false)) {
            tooltip.add(Text.translatable("tooltip.carmot_staff.locked").formatted(Formatting.YELLOW));
        }
    }

    @Override
    public boolean onStackClicked(ItemStack staff, Slot slot, ClickType clickType, PlayerEntity player) {
        // Validation - Cannot be used if locked
        // You cannot insert blocks with NBT, since the staff wipes it, and frankly
        // I do not want to deal with nesting staves inside shulker boxes with portable barrels inside or something
        if (staff.getOrDefault(MythicDataComponents.LOCKED, false)) return false;
        if (slot.getStack().contains(DataComponentTypes.CONTAINER)) return false;

        if (clickType == ClickType.RIGHT) {
            if (staff.getOrDefault(MythicDataComponents.LOCKED, false)) return false;
            if (slot.getStack().getItem() instanceof BlockItem blockItem) {

                boolean validStaffBlock = validateStaffBlock(blockItem);
                if (!staff.contains(CARMOT_STAFF_BLOCK) && !slot.getStack().isEmpty()) {

                    // Try to put block in staff
                    if (validStaffBlock) {
                        staff.set(CARMOT_STAFF_BLOCK, new CarmotStaffComponent(blockItem.getBlock()));
                        slot.takeStack(1);
                        player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), 0.85F, 0.5F);
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
                        var staffBlock = getBlockInStaff(staff).asItem().getDefaultStack();
                        slot.takeStack(1);
                        staff.set(CARMOT_STAFF_BLOCK, new CarmotStaffComponent(blockItem.getBlock()));
                        slot.insertStack(staffBlock, 1);
                        player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), 0.85F, 0.5F);
                        return true;

                    }
                    return false;

                }
            }

            // Try empty block into inventory
            if (slot.getStack().isEmpty() && !staff.getOrDefault(CARMOT_STAFF_BLOCK, CarmotStaffComponent.DEFAULT).isEmpty()) {
                slot.insertStack(getBlockInStaff(staff).asItem().getDefaultStack());
                staff.remove(CARMOT_STAFF_BLOCK);
                player.playSound(RegisterSounds.CARMOT_STAFF_EMPTY, 0.85F, 0.5F);
                return true;
            }

        }
        return false;
    }


    @Override
    public boolean onClicked(ItemStack staff, ItemStack cursorStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        // Containers with items or locked staffs can not be changed
        if (staff.getOrDefault(MythicDataComponents.LOCKED, false)) return false;
        if (cursorStack.contains(DataComponentTypes.CONTAINER)) return false;

        if (clickType == ClickType.RIGHT) {
            // If cursor is empty, but staff has block, take block out of staff
            if (cursorStackReference.get().isEmpty() && staff.contains(CARMOT_STAFF_BLOCK)) {
                if (cursorStackReference.set(getBlockInStaff(staff).asItem().getDefaultStack())) {
                    staff.remove(CARMOT_STAFF_BLOCK);
                    player.playSound(RegisterSounds.CARMOT_STAFF_EMPTY, 0.25F, 0.5F);
                    return true;
                }
                return false;
            }

            // Check if item is a BlockItem, and if its from Minecraft or MythicMetals
            if (!(cursorStackReference.get().getItem() instanceof BlockItem blockItem)) return false;
            boolean validStaffBlock = validateStaffBlock(blockItem);

            // If staff has block, and cursor has valid block, swap them
            if (staff.contains(CARMOT_STAFF_BLOCK)) {
                if (validStaffBlock && cursorStack.getCount() == 1) {
                    if (cursorStackReference.set(getBlockInStaff(staff).asItem().getDefaultStack())) {
                        staff.remove(CARMOT_STAFF_BLOCK);
                        staff.set(CARMOT_STAFF_BLOCK, new CarmotStaffComponent(blockItem.getBlock()));
                        player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), 0.85F, 0.5F);
                        return true;
                    }
                    return false;

                }
            }

            // If staff is empty, but cursor has valid block, put it into staff
            if (!staff.contains(CARMOT_STAFF_BLOCK)) {
                if (validStaffBlock && cursorStack.getCount() >= 1) {
                    staff.set(CARMOT_STAFF_BLOCK, new CarmotStaffComponent(blockItem.getBlock()));
                    cursorStack.decrement(1);
                    player.playSound(blockItem.getBlock().getDefaultState().getSoundGroup().getPlaceSound(), 0.85F, 0.5F);
                    return true;
                }
            }
        }
        return false;
    }

    // TODO - Refactor this to be data driven
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

            var targets = world.getOtherEntities(user, Box.of(user.getPos(), 32, 10, 32));
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
                            true)
                        );
                }
            });

            user.getItemCooldownManager().set(stack.getItem(), 500);
            stack.damage(10, user, EquipmentSlot.MAINHAND);
            WorldOps.playSound(world, user.getBlockPos(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            return TypedActionResult.success(stack);
        }

        // Command Block - Set yourself to Creative Mode
        if (hasBlockInStaff(stack, Blocks.COMMAND_BLOCK) && !MythicMetals.CONFIG.disableCommandBlockInStaff()) {
            // If command blocks are disabled, then no man should have that much power
            if (world.getServer() != null && !world.getServer().areCommandBlocksEnabled()) {
                user.sendMessage(Text.translatable("advMode.notEnabled"));
                return TypedActionResult.fail(stack);
            }

            // Let modpack authors disable funny Easter eggs that should in practice NEVER occur
            if (!MythicMetals.CONFIG.disableFunny()) {

                // Overload the staff if it is unbreakable, its simply too much power
                if (stack.contains(DataComponentTypes.UNBREAKABLE)) {
                    stack.remove(DataComponentTypes.UNBREAKABLE);
                    stack.remove(DataComponentTypes.ENCHANTMENTS);
                    stack.setDamage(MythicToolMaterials.CARMOT_STAFF.getDurability());
                    stack.damage(99999, user, EquipmentSlot.MAINHAND);
                    user.getItemCooldownManager().set(stack.getItem(), 6000);
                    if (user.isCreative()) {
                        stack = ItemStack.EMPTY;
                    }
                    explode(world, user);
                    return TypedActionResult.success(stack);
                }

                stack.setDamage(stack.getMaxDamage());
                stack.damage(stack.getMaxDamage(), user, EquipmentSlot.MAINHAND);
                user.getItemCooldownManager().set(stack.getItem(), 6000);
                ((ServerPlayerEntity) user).changeGameMode(GameMode.CREATIVE);
                explode(world, user);

                return TypedActionResult.success(stack);
            } else {
                user.sendMessage(Text.translatable("tooltip.carmot_staff.disabled"));
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

            stack.damage(3, user, EquipmentSlot.MAINHAND);
            return TypedActionResult.success(stack);
        }

        // Gold - Luck 1 for two minutes
        if (hasBlockInStaff(stack, Blocks.GOLD_BLOCK)) {
            var luckStatus = new StatusEffectInstance(StatusEffects.LUCK, 2400, 0, true, false, true);
            user.addStatusEffect(luckStatus);
            stack.damage(5, user, EquipmentSlot.MAINHAND);
            user.getItemCooldownManager().set(stack.getItem(), 1200);
            return TypedActionResult.success(stack);
        }

        // Carmot - AoE Heal
        if (hasBlockInStaff(stack, MythicBlocks.CARMOT.getStorageBlock())) {
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 8, 5, 8));
            entities.forEach(entity -> {
                if (entity instanceof LivingEntity livingEntity) {
                    if (livingEntity.getType().isIn(EntityTypeTags.UNDEAD)) {
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
            stack.damage(8, user, EquipmentSlot.MAINHAND);
            user.getItemCooldownManager().set(stack.getItem(), 480);
            return TypedActionResult.success(stack);
        }

        // Midas Gold - Luck 2 for four minutes
        if (hasBlockInStaff(stack, MythicBlocks.MIDAS_GOLD.getStorageBlock())) {
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 8, 5, 8));
            var betterLuckStatus = new StatusEffectInstance(StatusEffects.LUCK, 4800, 1, true, false, true);
            entities.forEach(entity -> {
                if (entity.isLiving()) {
                    ((LivingEntity) entity).addStatusEffect(betterLuckStatus);
                }
            });
            user.addStatusEffect(betterLuckStatus);
            stack.damage(10, user, EquipmentSlot.MAINHAND);
            user.getItemCooldownManager().set(stack.getItem(), 3000);
            return TypedActionResult.success(stack);
        }

        // Enchanted Midas Gold - Luck 5 for twenty minutes. Consumes the enchantment
        if (hasBlockInStaff(stack, MythicBlocks.ENCHANTED_MIDAS_GOLD_BLOCK)) {
            var entities = world.getOtherEntities(user, Box.of(user.getPos(), 32, 24, 32));
            var bestLuckStat = new StatusEffectInstance(StatusEffects.LUCK, 24000, 4, true, false, true);
            entities.forEach(entity -> {
                if (entity.isLiving()) {
                    ((LivingEntity) entity).addStatusEffect(bestLuckStat);
                }
            });
            user.addStatusEffect(bestLuckStat);
            stack.damage(20, user, EquipmentSlot.MAINHAND);
            user.getItemCooldownManager().set(stack.getItem(), 5000);
            stack.set(CARMOT_STAFF_BLOCK, new CarmotStaffComponent(MythicBlocks.MIDAS_GOLD.getStorageBlock()));
            if (!world.isClient()) {
                RegisterCriteria.USE_ENCHANTED_MIDAS_IN_CARMOT_STAFF.trigger(((ServerPlayerEntity) user));
            }
            return TypedActionResult.success(stack);
        }

        // Sponge - Remove water
        if (hasBlockInStaff(stack, Blocks.SPONGE)) {
            if (!world.isClient) {
                EpicExplosion.absorbWater((ServerWorld) world, user.getBlockX(), user.getBlockY(), user.getBlockZ(), 12, user);
            }
            user.getItemCooldownManager().set(stack.getItem(), 138);
            stack.damage(3, user, EquipmentSlot.MAINHAND);
            return TypedActionResult.success(stack);
        }

        // Note Block - Play a tune!
        if (hasBlockInStaff(stack, Blocks.NOTE_BLOCK)) {
            if (random.nextInt(500) >= 321) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(), RegisterSounds.MELODY, SoundCategory.PLAYERS, 1.0f, 1.0f, 1);
                stack.damage(60, user, EquipmentSlot.MAINHAND);
                stack.set(MythicDataComponents.ENCORE, true);
                user.getItemCooldownManager().set(stack.getItem(), 1500);
                return TypedActionResult.success(stack);
            }
            world.emitGameEvent(user, GameEvent.NOTE_BLOCK_PLAY, user.getPos());
            MythicParticleSystem.COLORED_NOTE.spawn(world, user.getPos().add(0, 2.35, 0));
            playRandomSound(random, user, world);
            user.getItemCooldownManager().set(stack.getItem(), 15);
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random r = Random.create();
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

        // Note Block - Instruments famously make noise when hitting people
        if (hasBlockInStaff(stack, Blocks.NOTE_BLOCK)) {
            amount = 1;
            MythicParticleSystem.NOTE_EXPLOSION.spawn(target.getWorld(), target.getPos());
            for (int i = 0; i <= r.nextInt(5); i++) {
                playRandomSound(r, target, target.getWorld());
            }
        }

        stack.damage(amount, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            stack.damage(2, miner, EquipmentSlot.MAINHAND);
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
            stack.set(MythicDataComponents.IS_USED, true);
        }
        if (remainingUseTicks % 40 == 1) {
            WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_MAINTAIN, SoundCategory.AMBIENT, 1.0F, 1.5F);
        }

        for (Entity entity : entities) {
            if (entity.getCommandTags().contains(PROJECTILE_MODIFIED.toString())) {
                return;
            }

            // Setting the owner of the trident to someone else would lead to shenanigans, don't do that
            if (entity instanceof TridentEntity trident) {
                var bounceVec = trident.getVelocity().multiply(-0.25, -0.25, -0.25);
                trident.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z);
                trident.returnTimer = 0;
                trident.addCommandTag(PROJECTILE_MODIFIED.toString());
            }
            // Special handling for ExplosiveProjectileEntities, like fireballs
            if (entity instanceof ExplosiveProjectileEntity projectile) {
                var bounceVec = projectile.getVelocity().multiply(-0.25, -0.25, -0.25);
                projectile.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z, 1.05F, 0.5F);
                projectile.setOwner(user);
                projectile.addCommandTag(PROJECTILE_MODIFIED.toString());
                stack.damage(2, user, EquipmentSlot.MAINHAND);
            }
            // Shulker bullet handling
            if (entity instanceof ShulkerBulletEntity projectile) {
                projectile.damage(world.getDamageSources().generic(), 1.0F);
            }
            // Default/Arrow handling
            else if (entity instanceof ProjectileEntity projectile) {
                // Bounce the projectiles in the direction the player is looking
                var bounceVec = projectile.getVelocity().multiply(-0.25, -0.25, -0.25);
                projectile.setVelocity(bounceVec.x, bounceVec.y, bounceVec.z, 1.05F, 0.5F);
                projectile.addCommandTag(PROJECTILE_MODIFIED.toString());
                stack.damage(1, user, EquipmentSlot.MAINHAND);
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // Try to cancel an encore in a rare edge case
        if (endEncore(stack, world)) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack.getItem(), 400);
            return;
        }

        // Handle Carmot Staff after using the Stormyx ability
        if (!world.isClient && user.isPlayer()) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack.getItem(), 240);
        }
        stack.set(MythicDataComponents.IS_USED, false);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundCategory.AMBIENT, 0.9F, 1.5F);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // Try to cancel an encore in a rare edge case
        if (endEncore(stack, world)) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack.getItem(), 400);
            return stack;
        }

        // Handle Carmot Staff after using the Stormyx ability
        if (!world.isClient && user.isPlayer()) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack.getItem(), 320);
        }
        stack.set(MythicDataComponents.IS_USED, false);
        WorldOps.playSound(world, user.getBlockPos(), RegisterSounds.PROJECTILE_BARRIER_END, SoundCategory.AMBIENT, 0.9F, 1.5F);
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
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

    // TODO - Data driven at some point?
    @Override
    public void postProcessComponents(ItemStack stack) {
        var block = stack.getOrDefault(CARMOT_STAFF_BLOCK, CarmotStaffComponent.DEFAULT).block();

        double damage = 0.0;
        float speed = 0.0f;

        if (Blocks.GOLD_BLOCK.equals(block)) {
            damage = 5.0;
            speed += 1.2f;
        } else if (Blocks.IRON_BLOCK.equals(block)) {
            damage = 7.0;
            speed += 0.9f;
        } else if (Blocks.DIAMOND_BLOCK.equals(block)) {
            damage = 9.0;
            speed += 1.0f;
        } else if (Blocks.LAPIS_BLOCK.equals(block)) {
            damage = 4.0;
            speed += 1.0f;
        } else if (Blocks.NETHERITE_BLOCK.equals(block)) {
            damage = 11.0;
            speed += 0.8f;
        } else if (MythicBlocks.HALLOWED.getStorageBlock().equals(block)) {
            damage = 12.0;
            speed += 0.75f;
        } else if (MythicBlocks.ADAMANTITE.getStorageBlock().equals(block)) {
            damage = 12.0;
            speed += 0.7f;
        } else if (MythicBlocks.METALLURGIUM.getStorageBlock().equals(block)) {
            damage = 14.0;
            speed += 0.6f;
        } else if (MythicBlocks.STAR_PLATINUM.getStorageBlock().equals(block)) {
            damage = 4.0;
            speed += 3.0f;
        }

        var attributes = stack.getDefaultComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS);

        // TODO - Refactor?
        if (speed > 0.0f || damage > 0) {
            attributes = createDefaultAttributes(damage, speed);
        }
        if (block.equals(Blocks.LAPIS_BLOCK)) {
            attributes = createAttributesWithXp(damage, speed);
        }

        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, attributes);
    }

    public static boolean hasBlockInStaff(ItemStack stack, Block block) {
        return stack.getOrDefault(CARMOT_STAFF_BLOCK, CarmotStaffComponent.DEFAULT).getBlock().equals(block);
    }

    public Block getBlockInStaff(ItemStack stack) {
        return stack.getOrDefault(CARMOT_STAFF_BLOCK, CarmotStaffComponent.DEFAULT).getBlock();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        Item item = stack.getItem();
        if (entity instanceof PlayerEntity user) {
            if (!user.getEquippedStack(EquipmentSlot.MAINHAND).equals(stack) && stack.getOrDefault(MythicDataComponents.IS_USED, false)) {
                finishUsing(stack, world, (LivingEntity) entity);
                return;
            }

            // Trigger Sculk Sensors every second while jamming out
            if (world.getTime() % 20 == (world.isClient() ? 0 : 10) && hasBlockInStaff(stack, Blocks.NOTE_BLOCK) && encore(stack, world)) {
                MythicParticleSystem.COLORED_NOTE.spawn(world, user.getPos().add(0, 2.35, 0));
                world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, user.getPos(), GameEvent.Emitter.of(user));
            }

            // Remove encore if you finished cooling down
            if (stack.getOrDefault(MythicDataComponents.ENCORE, false) && isNotOnCooldown(user, stack)) {
                stack.set(MythicDataComponents.ENCORE, false);
            }
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

    private void playRandomSound(Random random, LivingEntity user, World world) {
        float pitch = MathHelper.clamp(random.nextInt(20) / 10f, 0.1f, 2.0f);
        var instrumentList = Registries.INSTRUMENT.stream().toList();
        int instrument = random.nextInt(instrumentList.size());
        world.playSound(null, user.getX(), user.getY(), user.getZ(), instrumentList.get(instrument).soundEvent().value(), SoundCategory.PLAYERS, 1.0f, pitch, random.nextLong());
    }

    /**
     * Returns whether the player goes on an encore, playing a really sick melody
     */
    public static boolean encore(ItemStack stack, World world) {
        return stack.getOrDefault(MythicDataComponents.ENCORE, false) && world.getTime() % 20 == (world.isClient() ? 0 : 10);
    }

    /**
     * Call this method to end the encore, which attempts to cancel the sound from playing as well
     *
     * @return whether the encore was ended or not
     */
    public boolean endEncore(ItemStack stack, World world) {
        if (stack.contains(ENCORE)) {
            stack.remove(ENCORE);
            if (world.isClient()) {
                MinecraftClient.getInstance().getSoundManager().stopSounds(RegistryHelper.id("melody"), SoundCategory.PLAYERS);
                return true; // attempt was made
            }
        }
        return false;
    }
}
