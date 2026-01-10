package com.mythicmetals.block;

import com.mojang.serialization.MapCodec;
import com.mythicmetals.block.entity.CarmotBellBlockEntity;
import com.mythicmetals.block.entity.RegisterBlockEntityTypes;
import com.mythicmetals.misc.CarmotBellDamageSource;
import com.mythicmetals.misc.MythicParticleSystem;
import com.mythicmetals.registry.RegisterSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CarmotBellBlock extends BaseEntityBlock {

    public static final double RANGE = 8.0;
    public static final int COOLDOWN = 10 * 20;
    public static final VoxelShape BELL_SHAPE = Block.box(3.0f, 0.0f, 3.0f, 13.0f, 9.0f, 13.0f);

    public static final MapCodec<CarmotBellBlock> CODEC = simpleCodec(CarmotBellBlock::new);

    public CarmotBellBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        var be = world.getBlockEntity(pos);
        if (be == null) return InteractionResult.FAIL;

        if (be instanceof CarmotBellBlockEntity bell) {
            if (bell.canBeUsed()) {
                bell.markUsed();
                heal(world, be.getBlockPos().getCenter(), player);
                world.playLocalSound(pos, RegisterSounds.CARMOT_BELL_DING, SoundSource.BLOCKS, 1.0f, 1.0f, true);
            } else {
                world.playLocalSound(pos, RegisterSounds.CARMOT_BELL_DING_PLAIN, SoundSource.BLOCKS, 1.0f, 1.0f, true);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, world, pos, player, hit);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return BELL_SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return BELL_SHAPE;
    }

    private void heal(Level world, Vec3 pos, LivingEntity user) {
        if (world.isClientSide()) return;
        var entities = world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(pos, RANGE * 2, RANGE, RANGE * 2));
        entities.forEach(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.getType().is(EntityTypeTags.UNDEAD)) {
                    entity.hurtServer(((ServerLevel) world), CarmotBellDamageSource.of(world, user), Math.max(10.0f, livingEntity.getHealth() * 0.1f));
                    MythicParticleSystem.HEALING_DAMAGE.spawn(world, livingEntity.position());
                } else {
                    livingEntity.heal(Math.max(10.0f, livingEntity.getMaxHealth() * 0.1f));
                    MythicParticleSystem.HEALING_HEARTS.spawn(world, livingEntity.position());
                }
            }
        });
        MythicParticleSystem.HEALING_AREA.spawn(world, pos, RANGE);
        MythicParticleSystem.HEALING_HEARTS.spawn(world, pos);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, RegisterBlockEntityTypes.CARMOT_BELL_BLOCK, CarmotBellBlockEntity::tick);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CarmotBellBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
