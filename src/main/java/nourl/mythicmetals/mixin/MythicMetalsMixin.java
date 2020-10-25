package nourl.mythicmetals.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import nourl.mythicmetals.ores.OreGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class MythicMetalsMixin {
	@Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	  private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ADAMANTITE);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_AETHERIUM );
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_AQUARIUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_BANGLUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_COPPER);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_KYBER );
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_LUTETIUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_MANGANESE);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_MYTHRIL);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ORICHALCUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_OSMIUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_PLATINUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_PROMETHEUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_QUADRILLUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_RUNITE);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_SILVER);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_STARRITE);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_TANTALITE );
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_TIN);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_UNOBTAINIUM);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_VERMICULITE);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ZINC);
	  };
	  @Inject(method = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addNetherMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	  		  private static void addNetherMinables(GenerationSettings.Builder builder, CallbackInfo ci) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_MIDAS_GOLD);
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_STORMYX);
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_TRUESILVER);
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_UR);
	}
	}