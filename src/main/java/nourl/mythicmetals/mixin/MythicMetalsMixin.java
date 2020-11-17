package nourl.mythicmetals.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.ores.OreGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class MythicMetalsMixin {
	@Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
		if(MythicConfig.adamantiteGeneration) {
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ADAMANTITE); }
		if(MythicConfig.aquariumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_AQUARIUM); }
		if(MythicConfig.banglumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_BANGLUM); }
		if(MythicConfig.copperGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_COPPER); }
		if(MythicConfig.kyberGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_KYBER ); }
		if(MythicConfig.lutetiumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_LUTETIUM); }
		if(MythicConfig.manganeseGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_MANGANESE); }
		if(MythicConfig.mythrilGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_MYTHRIL); }
		if(MythicConfig.orichalcumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ORICHALCUM); }
		if(MythicConfig.osmiumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_OSMIUM); }
		if(MythicConfig.platinumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_PLATINUM); }
		if(MythicConfig.prometheumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_PROMETHEUM); }
		if(MythicConfig.quadrillumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_QUADRILLUM); }
		if(MythicConfig.runiteGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_RUNITE); }
		if(MythicConfig.silverGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_SILVER); }
		if(MythicConfig.tantaliteGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_TANTALITE ); }
		if(MythicConfig.tinGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_TIN); }
		if(MythicConfig.unobtainiumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_UNOBTAINIUM); }
		if(MythicConfig.vermiculiteGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_VERMICULITE); }
		if(MythicConfig.zincGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ZINC); }
	  };
	  @Inject(method = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addNetherMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	  		  private static void addNetherMinables(GenerationSettings.Builder builder, CallbackInfo ci) {
		  		if(MythicConfig.midasgoldGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_MIDAS_GOLD); }
			  	if(MythicConfig.stormyxGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_STORMYX); }
			    if(MythicConfig.truesilverGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_TRUESILVER); }
			    if(MythicConfig.urGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_UR); }
	};
	@Inject(method = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addEmeraldOre(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	private static void addEmeraldOre(GenerationSettings.Builder builder, CallbackInfo ci) {
		if(MythicConfig.aetheriumGeneration) {
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_AETHERIUM); }
		if(MythicConfig.starriteGeneration) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_STARRITE); }
	}
	}