package nourl.mythicmetals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
//import net.fabricmc.fabric.api.client.model.ModelProviderContext;
//import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import net.minecraft.client.render.RenderLayer;
//import net.minecraft.util.Identifier;
import nourl.mythicmetals.blocks.MythicChains;
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
                MythicChains.ADAMANTITE_CHAIN,
                MythicChains.AETHERIUM_CHAIN,
                MythicChains.AQUARIUM_CHAIN,
                MythicChains.ARGONIUM_CHAIN,
                MythicChains.BANGLUM_CHAIN,
                MythicChains.BRONZE_CHAIN,
                MythicChains.CARMOT_CHAIN,
                MythicChains.CELESTIUM_CHAIN,
                MythicChains.DISCORDIUM_CHAIN,
                MythicChains.DURASTEEL_CHAIN,
                MythicChains.ELECTRUM_CHAIN,
                MythicChains.ETHERITE_CHAIN,
                MythicChains.KYBER_CHAIN,
                MythicChains.HALLOWED_CHAIN,
                MythicChains.MANGANESE_CHAIN,
                MythicChains.METALLURGIUM_CHAIN,
                MythicChains.MIDAS_GOLD_CHAIN,
                MythicChains.MYTHRIL_CHAIN,
                MythicChains.ORICHALCUM_CHAIN,
                MythicChains.OSMIUM_CHAIN,
                MythicChains.PLATINUM_CHAIN,
                MythicChains.PROMETHEUM_CHAIN,
                MythicChains.QUADRILLUM_CHAIN,
                MythicChains.QUICKSILVER_CHAIN,
                MythicChains.RUNITE_CHAIN,
                MythicChains.SILVER_CHAIN,
                MythicChains.STARRITE_CHAIN,
                MythicChains.STEEL_CHAIN,
                MythicChains.STORMYX_CHAIN,
                MythicChains.TRUESILVER_CHAIN
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
