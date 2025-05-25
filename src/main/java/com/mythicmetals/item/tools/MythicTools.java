package com.mythicmetals.item.tools;

import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.potion.Potion;
import net.minecraft.registry.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.component.*;
import com.mythicmetals.item.*;
import com.mythicmetals.item.tools.carmot_staff.CarmotStaffItem;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import java.lang.reflect.Field;
import java.util.*;

import static com.mythicmetals.item.tools.ToolSet.createAttributeModifiers;

@SuppressWarnings("unused")
public class MythicTools implements SimpleFieldProcessingSubject<ToolSet> {
    public static final Map<String, ToolSet> TOOL_MAP = new HashMap<>();
    // Arrays for weapon/tool damage: sword, axe, pickaxe, shovel, and hoe
    public static final int[] DEFAULT_DAMAGE = new int[]{3, 5, 2, 1, 0};
    // Arrays for weapon/tool attack speed: sword, axe, pickaxe, shovel and hoe
    public static final float[] SLOWEST_ATTACK_SPEED = new float[]{1.5F, 0.8f, 1.1f, 1.0f, 0.9f}; // -0.1 to all
    public static final float[] SLOWER_ATTACK_SPEED = new float[]{1.5f, 0.9f, 1.1f, 1.0f, 0.9f}; // -0.1 except axes
    public static final float[] DEFAULT_ATTACK_SPEED = new float[]{1.6f, 0.9f, 1.2f, 1.1f, 1.0f};
    public static final float[] BETTER_AXE_ATTACK_SPEED = new float[]{1.6f, 1.0f, 1.2f, 1.1f, 1.0f}; // +0.1 on axes
    public static final float[] FASTER_ATTACK_SPEED = new float[]{1.8f, 1.1f, 1.3f, 1.2f, 1.2f}; // +0.1-0.2 to all
    public static final float[] HIGHEST_ATTACK_SPEED = new float[]{2.0f, 1.2f, 1.4f, 1.3f, 1.4f}; // + 0.3-0.4 to all

    public static final ToolSet ADAMANTITE = new ToolSet(MythicToolMaterials.ADAMANTITE, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet AQUARIUM = new AquariumToolSet(MythicToolMaterials.AQUARIUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BANGLUM = new ToolSet(MythicToolMaterials.BANGLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final Item BANGLUM_TNT_MINECART = new MinecartItem(MythicMetals.BANGLUM_TNT, new Item.Settings().group(MythicMetals.TABBED_GROUP));
    public static final Item PALLADIUM_MINECART = new MinecartItem(MythicMetals.PALLADIUM_MINECART, new Item.Settings().group(MythicMetals.TABBED_GROUP)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(Text.translatable("item.mythicmetals.palladium_minecart.description").withColor(UsefulSingletonForColorUtil.MetalColors.PALLADIUM.rgb()));
        }
    };
    public static final ToolSet BRONZE = new ToolSet(MythicToolMaterials.BRONZE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CARMOT = new ToolSet(MythicToolMaterials.CARMOT, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CELESTIUM = new ToolSet(MythicToolMaterials.CELESTIUM, DEFAULT_DAMAGE, HIGHEST_ATTACK_SPEED, settings -> settings.rarity(Rarity.RARE));
    public static final ToolSet COPPER = new ToolSet(MythicToolMaterials.COPPER, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet DURASTEEL = new ToolSet(MythicToolMaterials.DURASTEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet HALLOWED = new ToolSet(MythicToolMaterials.HALLOWED, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet KYBER = new ToolSet(MythicToolMaterials.KYBER, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet LEGENDARY_BANGLUM = new BanglumToolSet(MythicToolMaterials.LEGENDARY_BANGLUM, DEFAULT_DAMAGE, SLOWER_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet METALLURGIUM = new ToolSet(MythicToolMaterials.METALLURGIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ToolSet MYTHRIL = new ToolSet(MythicToolMaterials.MYTHRIL, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet ORICHALCUM = new ToolSet(MythicToolMaterials.ORICHALCUM, DEFAULT_DAMAGE, SLOWER_ATTACK_SPEED);
    public static final ToolSet OSMIUM = new ToolSet(MythicToolMaterials.OSMIUM, DEFAULT_DAMAGE, SLOWEST_ATTACK_SPEED);
    public static final ToolSet PALLADIUM = new PalladiumToolSet(MythicToolMaterials.PALLADIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, Item.Settings::fireproof);
    public static final ToolSet PROMETHEUM = new PrometheumToolSet(MythicToolMaterials.PROMETHEUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet QUADRILLUM = new ToolSet(MythicToolMaterials.QUADRILLUM, DEFAULT_DAMAGE, SLOWEST_ATTACK_SPEED);
    public static final ToolSet RUNITE = new ToolSet(MythicToolMaterials.RUNITE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STAR_PLATINUM = new ToolSet(MythicToolMaterials.STAR_PLATINUM, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet STEEL = new SteelToolSet(MythicToolMaterials.STEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STORMYX = new ToolSet(MythicToolMaterials.STORMYX, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet TIDESINGER = new TidesingerToolSet(MythicToolMaterials.TIDESINGER, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);

    public static final Item RED_AEGIS_SWORD = new RedAegisSword(MythicToolMaterials.AEGIS_RED, new Item.Settings()
        .fireproof()
        .rarity(Rarity.UNCOMMON)
        .group(MythicMetals.TABBED_GROUP)
        .tab(2)
        .attributeModifiers(SwordItem.createAttributeModifiers(MythicToolMaterials.AEGIS_RED, 5, -3.0f)));

    public static final Item WHITE_AEGIS_SWORD = new SwordItem(MythicToolMaterials.AEGIS_WHITE, new Item.Settings()
        .fireproof()
        .rarity(Rarity.UNCOMMON)
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .attributeModifiers(createAttributeModifiers(4, 1.4f))
    );

    public static final Item CARMOT_BELL = new CarmotBellItem(new Item.Settings()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .rarity(Rarity.UNCOMMON)
        .maxDamage(400)
    );

    public static final Item CARMOT_STAFF = new CarmotStaffItem(MythicToolMaterials.CARMOT_STAFF,
        new Item.Settings()
            .group(MythicMetals.TABBED_GROUP).tab(2)
    );

    public static final Item ORICHALCUM_HAMMER = new HammerBase(MythicToolMaterials.ORICHALCUM, new Item.Settings()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .attributeModifiers(MiningToolItem.createAttributeModifiers(MythicToolMaterials.ORICHALCUM, 6, -4.0f + 0.8f)),
        1
    );

    public static final Item MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD,
        new Item.Settings()
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .attributeModifiers(SwordItem.createAttributeModifiers(MythicToolMaterials.MIDAS_GOLD, 3, -4.0f + 1.6f))
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0))
    );

    public static final Item GILDED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.GILDED_MIDAS_GOLD,
        new Item.Settings()
            .fireproof()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP).tab(2)
            .attributeModifiers(SwordItem.createAttributeModifiers(MythicToolMaterials.GILDED_MIDAS_GOLD, 3, -4.0f + 1.6f))
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0))
    );

    public static final Item ROYAL_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.ROYAL_MIDAS_GOLD,
        new Item.Settings()
            .fireproof()
            .rarity(Rarity.UNCOMMON)
            .group(MythicMetals.TABBED_GROUP)
            .tab(2)
            .attributeModifiers(SwordItem.createAttributeModifiers(MythicToolMaterials.ROYAL_MIDAS_GOLD, 3, -4.0f + 1.6f))
            .component(MythicDataComponents.GOLD_FOLDED, GoldFoldedComponent.of(0, true))
    );

    public static final Item RUNITE_ARROW = new RuniteArrowItem(new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(2));
    public static final Item TIPPED_RUNITE_ARROW = new TippedRuniteArrowItem(new Item.Settings()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .stackGenerator((item, stacks) -> {
            for (Potion potion : Registries.POTION) {
                var stack = PotionContentsComponent.createStack(item, RegistryHelper.getEntry(potion));
                if (!potion.getEffects().isEmpty()) {
                    stacks.add(stack);
                }
            }
        })
        .component(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT)
    );

    public static final Item STAR_PLATINUM_ARROW = new StarPlatinumArrowItem(new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(2));
    public static final Item STORMYX_SHIELD = new StormyxShield(new Item.Settings()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .maxDamage(1680)
        .rarity(Rarity.UNCOMMON)
        .attributeModifiers(StormyxShield.createStormyxShieldAttributes())
    );
    public static final Item MYTHRIL_DRILL = new MythrilDrill(MythicToolMaterials.MYTHRIL_DRILL, new Item.Settings()
        .group(MythicMetals.TABBED_GROUP).tab(2)
        .rarity(Rarity.UNCOMMON)
        .attributeModifiers(createAttributeModifiers(3, 1.5f))
        .component(MythicDataComponents.DRILL, new DrillComponent(0))
        .component(MythicDataComponents.UPGRADES, UpgradeComponent.empty(2))
    );
    public static final Item PLATINUM_WATCH = new Item(new Item.Settings().group(MythicMetals.TABBED_GROUP).tab(2));

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
        Registry.registerReference(Registries.SOUND_EVENT, RegistryHelper.id("music_disc.dog4"), SoundEvent.of(RegistryHelper.id("music_disc.dog4")));
        RegistryHelper.item("red_aegis_sword", RED_AEGIS_SWORD);
        RegistryHelper.item("white_aegis_sword", WHITE_AEGIS_SWORD);
        RegistryHelper.item("carmot_staff", CARMOT_STAFF);
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
    }

    public static class Frogery {

        public static class Froger extends Item {

            public Froger(Settings settings) {
                super(settings);
            }

            @Override
            public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
                if (entity.getType() == EntityType.FROG && FabricLoader.getInstance().isModLoaded("delightful-froge")) {
                    ((FrogEntity) entity).setVariant(Registries.FROG_VARIANT.getEntry(Identifier.of("delightful", "froge")).orElseThrow());
                    return ActionResult.SUCCESS;
                }
                return super.useOnEntity(stack, user, entity, hand);
            }
        }

        public static final Item FROGE = new Froger(new Item.Settings().rarity(Rarity.EPIC).fireproof().equipmentSlot((entity, stack) -> EquipmentSlot.HEAD));
        public static final Item DOGE = new Item(new Item.Settings()
            .rarity(Rarity.EPIC).fireproof()
            .equipmentSlot((entity, stack) -> EquipmentSlot.HEAD)
            .maxCount(1)
            .jukeboxPlayable(RegistryKey.of(RegistryKeys.JUKEBOX_SONG, RegistryHelper.id("dog4"))));
    }
}