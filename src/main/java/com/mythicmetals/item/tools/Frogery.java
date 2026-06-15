package com.mythicmetals.item.tools;

import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

public class Frogery {

    public static class Froger extends Item {

        public Froger(Properties settings) {
            super(settings);
        }

        @Override
        public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
            if (entity.getType() == EntityType.FROG && FabricLoader.getInstance().isModLoaded("delightful-froge")) {
                entity.setComponent(DataComponents.FROG_VARIANT, entity.level().registryAccess().getOrThrow(RegistryHelper.frogKey("delightful", "froge")));
                return InteractionResult.SUCCESS;
            }
            return super.interactLivingEntity(stack, user, entity, hand);
        }
    }

    public static final Item FROGE = new Froger(new Item.Properties()
        .rarity(Rarity.EPIC)
        .fireResistant()
        .equippable(EquipmentSlot.HEAD)
        .setId(RegistryHelper.itemKey("froge"))
    );
    public static final Item DOGE = new Item(new Item.Properties()
        .setId(RegistryHelper.itemKey("doge"))
        .rarity(Rarity.EPIC).fireResistant()
        .equipmentSlot((entity, stack) -> EquipmentSlot.HEAD)
        .stacksTo(1)
        .jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, RegistryHelper.id("dog4"))));

    public static void init() {
        RegistryHelper.item("doge", Frogery.DOGE);
        RegistryHelper.item("froge", Frogery.FROGE);
        Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, RegistryHelper.id("music_disc.dog4"), SoundEvent.createVariableRangeEvent(RegistryHelper.id("music_disc.dog4")));
    }
}
