package nourl.mythicmetals.mixin;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.registry.RegistryHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
    // This Mixin is a class that works as a datafixer.
    // Upon loading a world it will check for missing objects in the recipe and replace them in order to
    // prevent air pockets when upgrading from older worlds, as well as returning changed/removed items.
    @Mixin(DefaultedRegistry.class)
    public class DefaultedRegistryMixin {
        // All the identifiers of removed stuff
        Identifier mythicCopperOre = RegistryHelper.id("copper_ore");
        Identifier mythicCopperIngot = RegistryHelper.id( "copper_ingot");
        Identifier mythicTantalite = RegistryHelper.id( "tantalite_ore");
        Identifier mythicTruesilverOre = RegistryHelper.id( "truesilver_ore");
        Identifier mythicTruesilverIngot = RegistryHelper.id( "truesilver_ingot");
        Identifier mythicTruesilverBlock = RegistryHelper.id( "truesilver_block");
        Identifier mythicUr = RegistryHelper.id( "ur_ore");
        Identifier mythicZinc = RegistryHelper.id( "zinc_ore");
        Identifier mythicRawStarrite = RegistryHelper.id( "raw_starrite");
        Identifier mythicStarriteIngot = RegistryHelper.id( "starrite_ingot");

        @ModifyVariable(at = @At("HEAD"), method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", ordinal = 0)
        Identifier fixMissingFromRegistry(@Nullable Identifier id) {
            if(id != null) {
                if(id.getNamespace().equals("mm_decorations")) return new Identifier(MythicMetals.CHAIN_ID, id.getPath());
                if(id.getPath().equals("unobtainium_dust")) return RegistryHelper.id( "unobtainium");
                if(id.equals(mythicCopperOre)) return new Identifier("minecraft","copper_ore");
                if(id.equals(mythicCopperIngot)) return new Identifier("minecraft","copper_ingot");
                if(id.equals(mythicUr)) return new Identifier("minecraft","netherrack");
                if(id.equals(mythicZinc) || id.equals(mythicTantalite)) return new Identifier("minecraft","stone");
                if(id.equals(mythicTruesilverOre)) return RegistryHelper.id( "palladium_ore");
                if(id.equals(mythicTruesilverIngot)) return RegistryHelper.id( "palladium_ingot");
                if(id.equals(mythicTruesilverBlock)) return RegistryHelper.id( "palladium_block");
                if(id.equals(mythicStarriteIngot) || id.equals(mythicRawStarrite)) return RegistryHelper.id( "starrite");
            }
            return id;
        }
    }
