package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.block.entity.CarmotBellBlockEntity;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.misc.CarmotBellDamageSource;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterSounds;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CarmotBellBlock extends BlockWithEntity {

    public static final double RANGE = 8.0;
    public static final int COOLDOWN = 10 * 20;
    public static final VoxelShape BELL_SHAPE = Block.createCuboidShape(3.0f, 0.0f, 3.0f, 13.0f, 9.0f, 13.0f);

    public static final MapCodec<CarmotBellBlock> CODEC = createCodec(CarmotBellBlock::new);

    public CarmotBellBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        var be = world.getBlockEntity(pos);
        if (be == null) return ActionResult.FAIL;

        if (be instanceof CarmotBellBlockEntity bell) {
            if (bell.canBeUsed()) {
                bell.markUsed();
                heal(world, be.getPos().toCenterPos(), player);
                world.playSoundAtBlockCenter(pos, RegisterSounds.CARMOT_BELL_DING, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
            } else {
                world.playSoundAtBlockCenter(pos, RegisterSounds.CARMOT_BELL_DING_PLAIN, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
            }
            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BELL_SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BELL_SHAPE;
    }

    private void heal(World world, Vec3d pos, LivingEntity user) {
        if (world.isClient()) return;
        var entities = world.getNonSpectatingEntities(LivingEntity.class, Box.of(pos, RANGE * 2, RANGE, RANGE * 2));
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getType().isIn(EntityTypeTags.UNDEAD)) {
                    entity.damage(((ServerWorld) world), CarmotBellDamageSource.of(world, user), Math.max(10.0f, livingEntity.getHealth() * 0.1f));
                    MythicParticleSystem.HEALING_DAMAGE.spawn(world, livingEntity.getPos());
                } else {
                    livingEntity.heal(Math.max(10.0f, livingEntity.getMaxHealth() * 0.1f));
                    MythicParticleSystem.HEALING_HEARTS.spawn(world, livingEntity.getPos());
                }
            }
        });
        MythicParticleSystem.HEALING_AREA.spawn(world, pos, RANGE);
        MythicParticleSystem.HEALING_HEARTS.spawn(world, pos);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, RegisterBlockEntityTypes.CARMOT_BELL_BLOCK, CarmotBellBlockEntity::tick);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }


    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CarmotBellBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
