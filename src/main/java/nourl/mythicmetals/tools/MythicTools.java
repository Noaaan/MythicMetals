package nourl.mythicmetals.tools;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.item.StarPlatinumArrowItem;
import nourl.mythicmetals.registry.RegisterSounds;
import nourl.mythicmetals.utils.RegistryHelper;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicTools implements SimpleFieldProcessingSubject<ToolSet> {
    // Arrays for weapon/tool damage: sword, axe, pickaxe, shovel, and hoe
    public static final int[] DEFAULT_DAMAGE = new int[]{3, 4, 2, 1, 0};
    public static final int[] BETTER_AXE_DAMAGE = new int[]{3, 5, 2, 1, 0};
    // Arrays for weapon/tool attack speed: sword, axe, pickaxe, shovel and hoe
    public static final float[] SLOW_ATTACK_SPEED = new float[]{-2.5F, -3.1F, -2.9F, -3.0F, -3.1F};
    public static final float[] DEFAULT_ATTACK_SPEED = new float[]{-2.4F, -3.1F, -2.8F, -2.9F, -3.0F};
    public static final float[] BETTER_AXE_ATTACK_SPEED = new float[]{-2.4F, -3.0F, -2.8F, -2.9F, -3.0F};
    public static final float[] FASTER_ATTACK_SPEED = new float[]{-2.2F, -2.9F, -2.7F, -2.8F, -2.8F};
    public static final float[] HIGHEST_ATTACK_SPEED = new float[]{-2.0F, -2.8F, -2.6F, -2.7F, -2.6F};

    public static final ToolSet ADAMANTITE = new ToolSet(MythicToolMaterials.ADAMANTITE, BETTER_AXE_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet AQUARIUM = new ToolSet(MythicToolMaterials.AQUARIUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BANGLUM = new ToolSet(MythicToolMaterials.BANGLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BRONZE = new ToolSet(MythicToolMaterials.BRONZE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CARMOT = new ToolSet(MythicToolMaterials.CARMOT, BETTER_AXE_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CELESTIUM = new ToolSet(MythicToolMaterials.CELESTIUM, BETTER_AXE_DAMAGE, HIGHEST_ATTACK_SPEED, settings -> settings.rarity(Rarity.RARE));
    public static final ToolSet COPPER = new ToolSet(MythicToolMaterials.COPPER, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet DURASTEEL = new ToolSet(MythicToolMaterials.DURASTEEL, BETTER_AXE_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet HALLOWED = new ToolSet(MythicToolMaterials.HALLOWED, BETTER_AXE_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet KYBER = new ToolSet(MythicToolMaterials.KYBER, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet LEGENDARY_BANGLUM = new BanglumToolSet(MythicToolMaterials.LEGENDARY_BANGLUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet METALLURGIUM = new ToolSet(MythicToolMaterials.METALLURGIUM, BETTER_AXE_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ToolSet MYTHRIL = new ToolSet(MythicToolMaterials.MYTHRIL, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet ORICHALCUM = new ToolSet(MythicToolMaterials.ORICHALCUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED);
    public static final ToolSet OSMIUM = new ToolSet(MythicToolMaterials.OSMIUM, BETTER_AXE_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet PALLADIUM = new ToolSet(MythicToolMaterials.PALLADIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, Item.Settings::fireproof);
    public static final ToolSet PROMETHEUM = new PrometheumToolSet(MythicToolMaterials.PROMETHEUM, BETTER_AXE_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet QUADRILLUM = new ToolSet(MythicToolMaterials.QUADRILLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet RUNITE = new ToolSet(MythicToolMaterials.RUNITE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STAR_PLATINUM = new ToolSet(MythicToolMaterials.STAR_PLATINUM, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet STEEL = new ToolSet(MythicToolMaterials.STEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STORMYX = new ToolSet(MythicToolMaterials.STORMYX, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);

    public static final Item RED_AEGIS_SWORD = new SwordItem(MythicToolMaterials.AEGIS_RED, 5, -3.0F,
            new OwoItemSettings().fireproof().rarity(Rarity.UNCOMMON).group(MythicMetals.TABBED_GROUP).tab(2));
    public static final Item WHITE_AEGIS_SWORD = new SwordItem(MythicToolMaterials.AEGIS_WHITE, 4, -2.6F,
            new OwoItemSettings().fireproof().rarity(Rarity.UNCOMMON).group(MythicMetals.TABBED_GROUP).tab(2));
    public static final Item CARMOT_STAFF = new CarmotStaff(MythicToolMaterials.CARMOT, -3.0F,
            new OwoItemSettings().rarity(Rarity.UNCOMMON).group(MythicMetals.TABBED_GROUP).tab(2));
    public static final Item MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.MIDAS_GOLD, 3, -2.4F,
            new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(2));

    public static final Item ORICHALCUM_HAMMER = new HammerBase(MythicToolMaterials.ORICHALCUM, 3, -3.0F,
            new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(2), 1);
    public static final Item GILDED_MIDAS_GOLD_SWORD = new MidasGoldSword(MythicToolMaterials.GILDED_MIDAS_GOLD, 3, -2.4F,
            new OwoItemSettings().fireproof().rarity(Rarity.UNCOMMON).group(MythicMetals.TABBED_GROUP).tab(2));

    public static final Item STAR_PLATINUM_ARROW = new StarPlatinumArrowItem(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(2));
    public static final Item STORMYX_SHIELD = new StormyxShield(new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(2).maxDamage(1680).rarity(Rarity.UNCOMMON));
    @Override
    public void processField(ToolSet toolSet, String name, Field f) {
        toolSet.register(name);
    }

    @Override
    public Class<ToolSet> getTargetFieldType() {
        return ToolSet.class;
    }

    @Override
    public void afterFieldProcessing() {
        RegistryHelper.item("doge", Frogery.DOGE);
        RegistryHelper.item("froge", Frogery.FROGE);
        RegistryHelper.item("red_aegis_sword", RED_AEGIS_SWORD);
        RegistryHelper.item("white_aegis_sword", WHITE_AEGIS_SWORD);
        RegistryHelper.item("carmot_staff", CARMOT_STAFF);
        RegistryHelper.item("orichalcum_hammer", ORICHALCUM_HAMMER);
        RegistryHelper.item("midas_gold_sword", MIDAS_GOLD_SWORD);
        RegistryHelper.item("gilded_midas_gold_sword", GILDED_MIDAS_GOLD_SWORD);
        RegistryHelper.item("star_platinum_arrow", STAR_PLATINUM_ARROW);
        RegistryHelper.item("stormyx_shield", STORMYX_SHIELD);
    }

    public static class Frogery {

        public static class Froger extends Item {

            public Froger(Settings settings) {
                super(settings);
            }
          /*I miss him already
            @Override
            public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
                if (entity.getType() == EntityType.FROG && FabricLoader.getInstance().isModLoaded("delightful-froge")) {
                    ((FrogEntity) entity).setVariant(Registry.FROG_VARIANT.get(new Identifier("delightful", "froge")));
                    return ActionResult.SUCCESS;
                }
                return super.useOnEntity(stack, user, entity, hand);
            }
            */
        }

        public static final Item FROGE = new Froger(new FabricItemSettings().rarity(Rarity.EPIC).fireproof().equipmentSlot(stack -> EquipmentSlot.HEAD));
        public static final Item DOGE = new MythicItems.CustomMusicDiscItem(42, RegisterSounds.DOG, new FabricItemSettings().rarity(Rarity.EPIC).fireproof().equipmentSlot(stack -> EquipmentSlot.HEAD).maxCount(1));
    }

}