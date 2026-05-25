package com.mythicmetals.block.entity;

import com.mythicmetals.block.CarmotBellBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

public class CarmotBellBlockEntity extends BlockEntity {

    protected int cooldown = 0;

    public CarmotBellBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntityTypes.CARMOT_BELL_BLOCK, pos, state);
    }

    public static void tick(Level world, BlockPos blockPos, BlockState blockState, CarmotBellBlockEntity be) {
        if (be.cooldown > 0) {
            be.cooldown--;
        }
    }

    public boolean canBeUsed() {
        return cooldown <= 0;
    }

    public void markUsed() {
        cooldown = CarmotBellBlock.COOLDOWN;
        setChanged();
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput valueOutput) {
        super.saveAdditional(valueOutput);
        valueOutput.putInt("cooldown", cooldown);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput valueInput) {
        super.loadAdditional(valueInput);
        this.cooldown = valueInput.getIntOr("cooldown", 0);
    }
}
