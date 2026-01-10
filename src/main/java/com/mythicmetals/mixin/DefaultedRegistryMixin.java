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
public class DefaultedRegistryMixin {

    @ModifyVariable(at = @At("HEAD"), method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", ordinal = 0, argsOnly = true)
    ResourceLocation fixMissingFromRegistry(@Nullable ResourceLocation id) {
        if (id != null) {
            // TODO - Migrate these to registry aliases within Mythic Metals Decorations
            // Various MOD_ID renames across mod versions, including Mythic Metals Decorations
            if (id.getNamespace().equals("mm_decorations"))
                return ResourceLocation.fromNamespaceAndPath("mythicmetals_decorations", id.getPath());
            if (id.getNamespace().equals("mythicaddons") && !id.getPath().contains("aegis"))
                return ResourceLocation.fromNamespaceAndPath("mythicmetals_decorations", id.getPath());
        }
        return id;
    }
}
