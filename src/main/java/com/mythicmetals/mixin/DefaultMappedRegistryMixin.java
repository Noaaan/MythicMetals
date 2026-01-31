package com.mythicmetals.mixin;

import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// This Mixin is a class that works as a datafixer.
// Upon loading a world it will check for missing objects in the recipe and replace them in order to
// prevent air pockets when upgrading from older worlds, as well as returning changed/removed items.
@Mixin(DefaultedMappedRegistry.class)
public class DefaultMappedRegistryMixin {

}
