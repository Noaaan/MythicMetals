package nourl.mythicmetals.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import nourl.mythicmetals.MythicMetalsMain;
import nourl.mythicmetals.config.MythicConfig;
import nourl.mythicmetals.ores.OreGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class MythicMetalsMixin {
	public static final MythicConfig.MythicOreConfig CONFIG2 = MythicMetalsMain.CONFIG.mythores;
	@Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
		if(CONFIG2.adamantiteGeneration) {
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ADAMANTITE); }
		if(CONFIG2.aquariumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_AQUARIUM); }
		if(CONFIG2.banglumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_BANGLUM); }
		if(CONFIG2.carmotGeneration) {
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_CARMOT); }
		if(CONFIG2.copperGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_COPPER); }
		if(CONFIG2.kyberGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_KYBER ); }
		if(CONFIG2.lutetiumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_LUTETIUM); }
		if(CONFIG2.manganeseGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_MANGANESE); }
		if(CONFIG2.mythrilGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_MYTHRIL); }
		if(CONFIG2.orichalcumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ORICHALCUM); }
		if(CONFIG2.osmiumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_OSMIUM); }
		if(CONFIG2.platinumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_PLATINUM); }
		if(CONFIG2.prometheumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_PROMETHEUM); }
		if(CONFIG2.quadrillumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_QUADRILLUM); }
		if(CONFIG2.runiteGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_RUNITE); }
		if(CONFIG2.silverGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_SILVER); }
		if(CONFIG2.tantaliteGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_TANTALITE ); }
		if(CONFIG2.tinGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_TIN); }
		if(CONFIG2.unobtainiumGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_UNOBTAINIUM); }
		if(CONFIG2.vermiculiteGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_VERMICULITE); }
		if(CONFIG2.zincGeneration) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_ZINC); }
	  };
	  @Inject(method = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addNetherMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	  		  private static void addNetherMinables(GenerationSettings.Builder builder, CallbackInfo ci) {
		  		if(CONFIG2.midasgoldGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_MIDAS_GOLD); }
			  	if(CONFIG2.stormyxGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_STORMYX); }
			    if(CONFIG2.truesilverGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_TRUESILVER); }
			    if(CONFIG2.urGeneration) {
			    builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OreGenerator.ORE_UR); }
	};
	@Inject(method = "Lnet/minecraft/world/gen/feature/DefaultBiomeFeatures;addEmeraldOre(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	private static void addEmeraldOre(GenerationSettings.Builder builder, CallbackInfo ci) {
		if(CONFIG2.aetheriumGeneration) {
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_AETHERIUM); }
		if(CONFIG2.starriteGeneration) {
			builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OreGenerator.ORE_STARRITE); }
	}
	}