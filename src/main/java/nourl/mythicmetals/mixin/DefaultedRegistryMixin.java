package nourl.mythicmetals.mixin;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import nourl.mythicmetals.MythicMetals;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

    @Mixin(DefaultedRegistry.class)
    public class DefaultedRegistryMixin {
        // This Mixin is a class that works as a datafixer.
        // Upon loading a world it will check for missing objects in the recipe and replace them in order to
        // prevent air pockets when upgrading from older worlds, as well as returning changed/removed items.
        @ModifyVariable(at = @At("HEAD"), method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", ordinal = 0)
        Identifier fixMissingFromRegistry(@Nullable Identifier id) {
            Identifier mythicCopperOre = new Identifier(MythicMetals.MOD_ID, "copper_ore");
            Identifier mythicCopperIngot = new Identifier(MythicMetals.MOD_ID, "copper_ingot");
            Identifier mythicTantalite = new Identifier(MythicMetals.MOD_ID, "tantalite_ore");
            Identifier mythicTruesilverOre = new Identifier(MythicMetals.MOD_ID, "truesilver_ore");
            Identifier mythicTruesilverIngot = new Identifier(MythicMetals.MOD_ID, "truesilver_ingot");
            Identifier mythicTruesilverBlock = new Identifier(MythicMetals.MOD_ID, "truesilver_block");
            Identifier mythicUr = new Identifier(MythicMetals.MOD_ID, "ur_ore");
            Identifier mythicZinc = new Identifier(MythicMetals.MOD_ID, "zinc_ore");
            Identifier mythicRawStarrite = new Identifier(MythicMetals.MOD_ID, "raw_starrite");
            Identifier mythicStarriteIngot = new Identifier(MythicMetals.MOD_ID, "starrite_ingot");

            if(id != null) {
                if(id.getNamespace().equals("mm_decorations")) return new Identifier(MythicMetals.MOD_ID, id.getPath());
                if(id.getPath().equals("unobtainium_dust")) return new Identifier(MythicMetals.MOD_ID, "unobtainium");
                if(id.equals(mythicCopperOre)) return new Identifier("minecraft","copper_ore");
                if(id.equals(mythicCopperIngot)) return new Identifier("minecraft","copper_ingot");
                if(id.equals(mythicUr)) return new Identifier("minecraft","netherrack");
                if(id.equals(mythicZinc) || id.equals(mythicTantalite)) return new Identifier("minecraft","stone");
                if(id.equals(mythicTruesilverOre)) return new Identifier(MythicMetals.MOD_ID, "palladium_ore");
                if(id.equals(mythicTruesilverIngot)) return new Identifier(MythicMetals.MOD_ID, "palladium_ingot");
                if(id.equals(mythicTruesilverBlock)) return new Identifier(MythicMetals.MOD_ID, "palladium_block");
                if(id.equals(mythicStarriteIngot) || id.equals(mythicRawStarrite)) return new Identifier(MythicMetals.MOD_ID, "starrite");
            }
            return id;
        }
    }
