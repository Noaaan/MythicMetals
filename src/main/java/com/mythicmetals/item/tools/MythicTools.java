package com.mythicmetals.item.tools;

import com.mythicmetals.MythicAttributeModifier;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.*;
import com.mythicmetals.entity.MythicEntities;
import com.mythicmetals.entity.MythicEntityAttributes;
import com.mythicmetals.item.*;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.frog.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import java.lang.reflect.Field;
import java.util.*;

import static com.mythicmetals.misc.RegistryHelper.itemKey;

@SuppressWarnings("unused")
public class MythicTools implements SimpleFieldProcessingSubject<ToolSet> {
    public static final Map<String, ToolSet> TOOL_MAP = new HashMap<>();
    public static final Set<ToolSet> MODDED_TOOLSETS = new HashSet<>();
    // Arrays for weapon/tool damage: sword, axe, pickaxe, shovel, and hoe
    public static final int[] DEFAULT_DAMAGE = new int[]{3, 5, 2, 1, 0};
    // Arrays for weapon/tool attack speed: sword, axe, pickaxe, shovel and hoe
    public static final float[] SLOWEST_ATTACK_SPEED = new float[]{1.5F, 0.8f, 1.1f, 1.0f, 0.9f}; // -0.1 to all
    public static final float[] SLOWER_ATTACK_SPEED = new float[]{1.5f, 0.9f, 1.1f, 1.0f, 0.9f}; // -0.1 except axes
    public static final float[] DEFAULT_ATTACK_SPEED = new float[]{1.6f, 0.9f, 1.2f, 1.1f, 1.0f};
    public static final float[] BETTER_AXE_ATTACK_SPEED = new float[]{1.6f, 1.0f, 1.2f, 1.1f, 1.0f}; // +0.1 on axes
    public static final float[] FASTER_ATTACK_SPEED = new float[]{1.8f, 1.1f, 1.3f, 1.2f, 1.2f}; // +0.1-0.2 to all
    public static final float[] HIGHEST_ATTACK_SPEED = new float[]{2.0f, 1.2f, 1.4f, 1.3f, 1.4f}; // + 0.3-0.4 to all

    public static final ToolSet ADAMANTITE = new ToolSet("adamantite", MythicToolMaterials.ADAMANTITE, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet AQUARIUM = new AquariumToolSet(MythicToolMaterials.AQUARIUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BANGLUM = new ToolSet("banglum", MythicToolMaterials.BANGLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final Item BANGLUM_TNT_MINECART = new MinecartItem(MythicEntities.BANGLUM_TNT_MINECART_ENTITY_TYPE, new Item.Properties().group(MythicMetals.TABBED_GROUP).setId(RegistryHelper.itemKey("banglum_tnt_minecart")));
    public static final Item PALLADIUM_MINECART = new MinecartItem(MythicEntities.PALLADIUM_MINECART_ENTITY_TYPE, new Item.Properties().group(MythicMetals.TABBED_GROUP).setId(RegistryHelper.itemKey("palladium_minecart"))) {
    // FIXME - Tooltip
        //        @Override
//        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
//            super.appendHoverText(stack, context, tooltip, type);
//            tooltip.add(Component.translatable("item.mythicmetals.palladium_minecart.description").withColor(UsefulSingletonForColorUtil.MetalColors.PALLADIUM.rgb()));
//        }
    };
    public static final ToolSet BRONZE = new ToolSet("bronze", MythicToolMaterials.BRONZE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CARMOT = new ToolSet("carmot", MythicToolMaterials.CARMOT, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CELESTIUM = new ToolSet("celestium", MythicToolMaterials.CELESTIUM, DEFAULT_DAMAGE, HIGHEST_ATTACK_SPEED, settings -> settings.rarity(Rarity.RARE));
    public static final ToolSet COPPER = new ToolSet("copper", MythicToolMaterials.COPPER, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet DURASTEEL = new ToolSet("durasteel", MythicToolMaterials.DURASTEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet HALLOWED = new ToolSet("hallowed", MythicToolMaterials.HALLOWED, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet KYBER = new ToolSet("kyber", MythicToolMaterials.KYBER, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet LEGENDARY_BANGLUM = new BanglumToolSet(MythicToolMaterials.LEGENDARY_BANGLUM, DEFAULT_DAMAGE, SLOWER_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet METALLURGIUM = new ToolSet("metallurgium", MythicToolMaterials.METALLURGIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.fireResistant().rarity(Rarity.RARE));
    public static final ToolSet MYTHRIL = new ToolSet("mythril", MythicToolMaterials.MYTHRIL, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet ORICHALCUM = new ToolSet("orichalcum", MythicToolMaterials.ORICHALCUM, DEFAULT_DAMAGE, SLOWER_ATTACK_SPEED);
    public static final ToolSet OSMIUM = new ToolSet("osmium", MythicToolMaterials.OSMIUM, DEFAULT_DAMAGE, SLOWEST_ATTACK_SPEED);
    public static final ToolSet PALLADIUM = new ToolSet("palladium", MythicToolMaterials.PALLADIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.fireResistant().component(MythicDataComponents.BRANDING, new BrandingComponent(6)));
    public static final ToolSet PROMETHEUM = new PrometheumToolSet(MythicToolMaterials.PROMETHEUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet QUADRILLUM = new ToolSet("quadrillum", MythicToolMaterials.QUADRILLUM, DEFAULT_DAMAGE, SLOWEST_ATTACK_SPEED);
    public static final ToolSet RUNITE = new ToolSet("runite", MythicToolMaterials.RUNITE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STAR_PLATINUM = new ToolSet("star_platinum", MythicToolMaterials.STAR_PLATINUM, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet STEEL = new SteelToolSet(MythicToolMaterials.STEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STORMYX = new ToolSet("stormyx", MythicToolMaterials.STORMYX, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet TIDESINGER = new TidesingerToolSet(MythicToolMaterials.TIDESINGER, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);

    public static final Item RED_AEGIS_SWORD = new RedAegisSword(MythicToolMaterials.AEGIS_RED, 5, -3.0f, new Item.Properties()
        .fireResistant()
        .rarity(Rarity.UNCOMMON)
        .group(MythicMetals.TABBED_GROUP)
        .tab(2)
        .setId(RegistryHelper.itemKey("red_aegis_sword"))
    );

    public static final Item WHITE_AEGIS_SWORD = new ToolSet.SwordMock(MythicToolMaterials.AEGIS_WHITE, 4, -2.6f, new Item.Properties()
        .fireResistant()
        .rarity(Rarity.UNCOMMON)
        .group(MythicMetals.TABBED_GROUP)
        .tab(2)
        .setId(RegistryHelper.itemKey("white_aegis_sword")),
        List.of(new MythicAttributeModifier(MythicEntityAttributes.UNDEAD_BONUS_DAMAGE, 8.0, net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADD_VALUE, EquipmentSlotGroup.MAINHAND))
    );

    public static final Item CARMOT_BELL = new CarmotBellItem(new Item.Properties()
        .setId(RegistryHelper.itemKey("carmot_bell"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .rarity(Rarity.UNCOMMON)
        .durability(400)
    );

    public static final Item ORICHALCUM_HAMMER = new HammerBase(MythicToolMaterials.ORICHALCUM, 6, -3.2f, new Item.Properties()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .setId(RegistryHelper.itemKey("orichalcum_hammer")),
        1
    );

    public static final Item MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD,
        new Item.Properties()
            .setId(RegistryHelper.itemKey("midas_gold_sword"))
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0))
    );

    public static final Item GILDED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.GILDED_MIDAS_GOLD,
        new Item.Properties()
            .setId(RegistryHelper.itemKey("gilded_midas_gold_sword"))
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0))
    );

    public static final Item ROYAL_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD,
        new Item.Properties()
            .setId(RegistryHelper.itemKey("royal_midas_gold_sword"))
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0, true))
    );

    public static final Item RUNITE_ARROW = new RuniteArrowItem(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(itemKey("runite_arrow")));
    public static final Item TIPPED_RUNITE_ARROW = new TippedRuniteArrowItem(new Item.Properties()
        .setId(itemKey("tipped_runite_arrow"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .stackGenerator((item, stacks) -> {
            for (Potion potion : BuiltInRegistries.POTION) {
                var stack = PotionContents.createItemStack(item, RegistryHelper.getEntry(potion));
                if (!potion.getEffects().isEmpty()) {
                    stacks.accept(stack);
                }
            }
        })
        .component(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)
    );

    public static final Item STAR_PLATINUM_ARROW = new StarPlatinumArrowItem(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(itemKey("star_platinum_arrow")));
    public static final Item STORMYX_SHIELD = new StormyxShield(new Item.Properties()
        .setId(RegistryHelper.itemKey("stormyx_shield"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .durability(1680)
        .rarity(Rarity.UNCOMMON)
        .attributes(StormyxShield.createStormyxShieldAttributes())
    );
    public static final Item MYTHRIL_DRILL = new MythrilDrill(MythicToolMaterials.MYTHRIL_DRILL, 3, 1.5f, new Item.Properties()
        .setId(RegistryHelper.itemKey("mythril_drill"))
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .rarity(Rarity.UNCOMMON)
        .component(MythicDataComponents.DRILL, new DrillComponent(0))
        .component(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2))
    );
    public static final Item PLATINUM_WATCH = new Item(new Item.Properties().group(MythicMetals.TABBED_GROUP).tab(2).setId(RegistryHelper.itemKey("platinum_watch")));

    @Override
    public void processField(ToolSet toolSet, String name, Field f) {
        toolSet.register(name);
        TOOL_MAP.put(name, toolSet);
    }

    @Override
    public Class<ToolSet> getTargetFieldType() {
        return ToolSet.class;
    }

    @Override
    public void afterFieldProcessing() {
        RegistryHelper.item("banglum_tnt_minecart", BANGLUM_TNT_MINECART);
        RegistryHelper.item("carmot_bell", CARMOT_BELL);
        RegistryHelper.item("palladium_minecart", PALLADIUM_MINECART);
        RegistryHelper.item("doge", Frogery.DOGE);
        RegistryHelper.item("froge", Frogery.FROGE);
        Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, RegistryHelper.id("music_disc.dog4"), SoundEvent.createVariableRangeEvent(RegistryHelper.id("music_disc.dog4")));
        RegistryHelper.item("red_aegis_sword", RED_AEGIS_SWORD);
        RegistryHelper.item("white_aegis_sword", WHITE_AEGIS_SWORD);
        RegistryHelper.item("orichalcum_hammer", ORICHALCUM_HAMMER);
        RegistryHelper.item("midas_gold_sword", MIDAS_GOLD_SWORD);
        RegistryHelper.item("gilded_midas_gold_sword", GILDED_MIDAS_GOLD_SWORD);
        RegistryHelper.item("royal_midas_gold_sword", ROYAL_MIDAS_GOLD_SWORD);
        RegistryHelper.item("mythril_drill", MYTHRIL_DRILL);
        RegistryHelper.item("star_platinum_arrow", STAR_PLATINUM_ARROW);
        RegistryHelper.item("runite_arrow", RUNITE_ARROW);
        RegistryHelper.item("tipped_runite_arrow", TIPPED_RUNITE_ARROW);
        RegistryHelper.item("stormyx_shield", STORMYX_SHIELD);
        RegistryHelper.item("platinum_watch", PLATINUM_WATCH);

        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath("mythicaddons", "red_aegis_sword"), RegistryHelper.id("red_aegis_sword"));
        BuiltInRegistries.ITEM.addAlias(Identifier.fromNamespaceAndPath("mythicaddons", "white_aegis_sword"), RegistryHelper.id("white_aegis_sword"));
    }

    public static class Frogery {

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

        public static final Item FROGE = new Froger(new Item.Properties().rarity(Rarity.EPIC).fireResistant().equipmentSlot((entity, stack) -> EquipmentSlot.HEAD).setId(RegistryHelper.itemKey("froge")));
        public static final Item DOGE = new Item(new Item.Properties()
            .setId(RegistryHelper.itemKey("doge"))
            .rarity(Rarity.EPIC).fireResistant()
            .equipmentSlot((entity, stack) -> EquipmentSlot.HEAD)
            .stacksTo(1)
            .jukeboxPlayable(ResourceKey.create(Registries.JUKEBOX_SONG, RegistryHelper.id("dog4"))));
    }
}