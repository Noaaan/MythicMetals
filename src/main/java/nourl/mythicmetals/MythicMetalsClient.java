package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
//import net.fabricmc.fabric.api.client.model.ModelProviderContext;
//import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import net.minecraft.client.render.RenderLayer;
//import net.minecraft.util.Identifier;
import nourl.mythicmetals.blocks.MythicBlocks;
//import nourl.mythicmetals.armor.HallowedArmor;
//import nourl.mythicmetals.armor.MetallurgiumArmor;
//import nourl.mythicmetals.registry.RegisterArmor;

public class MythicMetalsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        makeOpaque();
        //renderHallowedArmorModel();
        //renderMetallurgiumArmorModel();
    }

    // Makes custom model blocks see trough, like chains
    public void makeOpaque() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                MythicBlocks.ADAMANTITE_CHAIN,
                MythicBlocks.AETHERIUM_CHAIN,
                MythicBlocks.AQUARIUM_CHAIN,
                MythicBlocks.BANGLUM_CHAIN,
                MythicBlocks.BRONZE_CHAIN,
                MythicBlocks.CARMOT_CHAIN,
                MythicBlocks.CELESTIUM_CHAIN,
                MythicBlocks.DISCORDIUM_CHAIN,
                MythicBlocks.DURASTEEL_CHAIN,
                MythicBlocks.ETHERITE_CHAIN,
                MythicBlocks.KYBER_CHAIN,
                MythicBlocks.HALLOWED_CHAIN,
                MythicBlocks.MANGANESE_CHAIN,
                MythicBlocks.METALLURGIUM_CHAIN,
                MythicBlocks.MIDAS_GOLD_CHAIN,
                MythicBlocks.MYTHRIL_CHAIN,
                MythicBlocks.ORICHALCUM_CHAIN,
                MythicBlocks.OSMIUM_CHAIN,
                MythicBlocks.PLATINUM_CHAIN,
                MythicBlocks.PROMETHEUM_CHAIN,
                MythicBlocks.QUADRILLUM_CHAIN,
                MythicBlocks.QUICKSILVER_CHAIN,
                MythicBlocks.RUNITE_CHAIN,
                MythicBlocks.SILVER_CHAIN,
                MythicBlocks.STAR_PLATINUM_CHAIN,
                MythicBlocks.STEEL_CHAIN,
                MythicBlocks.STORMYX_CHAIN,
                MythicBlocks.PALLADIUM_CHAIN
        );
    }
    // Adds custom entity models to hallowed and metallurgium armor, currently deprecated
//    public void renderHallowedArmorModel() {
//        ArmorRenderingRegistry.ModelProvider modelProvider = (entity, stack, slot, original) -> ((HallowedArmor) stack.getItem()).getArmorModel(entity, stack, slot, original);
//        ArmorRenderingRegistry.TextureProvider textureProvider = (entity, stack, slot, secondLayer, suffix, original) -> new Identifier(((HallowedArmor) stack.getItem()).getArmorTexture(stack, slot));
//        ArmorRenderingRegistry.registerModel(modelProvider, RegisterArmor.HALLOWED_HELMET);
//        ArmorRenderingRegistry.registerTexture(textureProvider, RegisterArmor.HALLOWED_HELMET);
//        ArmorRenderingRegistry.registerModel(modelProvider, RegisterArmor.HALLOWED_CHESTPLATE);
//        ArmorRenderingRegistry.registerTexture(textureProvider, RegisterArmor.HALLOWED_CHESTPLATE);
//        ArmorRenderingRegistry.registerModel(modelProvider, RegisterArmor.HALLOWED_LEGGINGS);
//        ArmorRenderingRegistry.registerTexture(textureProvider, RegisterArmor.HALLOWED_LEGGINGS);
//    }
//    ModelProviderContext
//    public void renderMetallurgiumArmorModel() {
//        ArmorRenderingRegistry.ModelProvider modelProvider = (entity, stack, slot, original) -> ((MetallurgiumArmor) stack.getItem()).getArmorModel(entity, stack, slot, original);
//        ArmorRenderingRegistry.TextureProvider textureProvider = (entity, stack, slot, secondLayer, suffix, original) -> new Identifier(((MetallurgiumArmor) stack.getItem()).getArmorTexture(stack, slot));
//        ArmorRenderingRegistry.registerModel(modelProvider, RegisterArmor.METALLURGIUM_HELMET);
//        ArmorRenderingRegistry.registerTexture(textureProvider, RegisterArmor.METALLURGIUM_HELMET);
//    }

}
