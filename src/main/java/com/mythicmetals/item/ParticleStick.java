package com.mythicmetals.item;

import io.wispforest.owo.particles.systems.ParticleSystem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ParticleStick<T> extends Item {
    private final ParticleSystem<T> particle;
    private final T extraData;

    public ParticleStick(Settings settings, ParticleSystem<T> particle) {
        super(settings);
        this.particle = particle;
        this.extraData = null;
    }

    public ParticleStick(Settings settings, ParticleSystem<T> particle, T data) {
        super(settings);
        this.particle = particle;
        this.extraData = data;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        if (this.extraData != null) {
            particle.spawn(world, user.getPos(), extraData);
        } else {
            particle.spawn(world, user.getPos());
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
