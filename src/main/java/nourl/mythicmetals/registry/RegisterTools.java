package nourl.mythicmetals.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.tools.ToolMaterials;
import nourl.mythicmetals.tools.ToolSet;
import nourl.mythicmetals.utils.RegistryHelper;

public class RegisterTools {
    // Arrays for weapon/tool damage: sword, axe, pickaxe, shovel, and hoe
    public static final int [] DEFAULT_DAMAGE = new int[]{3, 4, 2, 1, 0};
    public static final int [] BETTER_AXE_DAMAGE = new int[]{3, 5, 2, 1, 0};
    // Arrays for weapon/tool attack speed: sword, axe, pickaxe, shovel and hoe
    public static final float [] SLOW_ATTACK_SPEED = new float[]{-2.5F, -3.2F, -2.9F, -3.0F, -3.1F};
    public static final float [] DEFAULT_ATTACK_SPEED = new float[]{-2.4F, -3.1F, -2.8F, -2.9F, -3.0F};
    public static final float [] BETTER_AXE_ATTACK_SPEED = new float[]{-2.4F, -3.0F, -2.8F, -2.9F, -3.0F};
    public static final float [] FASTER_ATTACK_SPEED = new float[]{-2.2F, -3.0F, -2.7F, -2.8F, -2.8F};
    public static final float [] HIGHEST_ATTACK_SPEED = new float[]{-2.1F, -2.9F, -2.6F, -2.8F, -2.7F};

    public static final ToolSet ADAMANTITE = new ToolSet(ToolMaterials.ADAMANTITE, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet AETHERIUM = new ToolSet(ToolMaterials.AETHERIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet AQUARIUM = new ToolSet(ToolMaterials.AQUARIUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BANGLUM = new ToolSet(ToolMaterials.BANGLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BRONZE = new ToolSet(ToolMaterials.BRONZE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CARMOT = new ToolSet(ToolMaterials.CARMOT, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CELESTIUM = new ToolSet(ToolMaterials.CELESTIUM, DEFAULT_DAMAGE, HIGHEST_ATTACK_SPEED);
    public static final ToolSet COPPER = new ToolSet(ToolMaterials.COPPER, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet DISCORDIUM = new ToolSet(ToolMaterials.DISCORDIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet DURASTEEL = new ToolSet(ToolMaterials.DURASTEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final Item ETHERITE_SWORD = new SwordItem(ToolMaterials.ETHERITE, 3, -2.4F, new Item.Settings().group(MythicMetals.MYTHICMETALS_TOOLS));
    public static final ToolSet HALLOWED = new ToolSet(ToolMaterials.HALLOWED, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet KYBER = new ToolSet(ToolMaterials.KYBER, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet METALLURGIUM = new ToolSet(ToolMaterials.METALLURGIUM, BETTER_AXE_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet MYTHRIL = new ToolSet(ToolMaterials.MYTHRIL, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet ORICHALCUM = new ToolSet(ToolMaterials.ORICHALCUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED);
    public static final ToolSet OSMIUM = new ToolSet(ToolMaterials.ORICHALCUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED);
    public static final ToolSet PALLADIUM = new ToolSet(ToolMaterials.PALLADIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet PROMETHEUM = new ToolSet(ToolMaterials.PROMETHEUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet QUADRILLUM = new ToolSet(ToolMaterials.QUADRILLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet QUICKSILVER = new ToolSet(ToolMaterials.QUICKSILVER, BETTER_AXE_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet RUNITE = new ToolSet(ToolMaterials.RUNITE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet SILVER = new ToolSet(ToolMaterials.SILVER, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STAR_PLATINUM = new ToolSet(ToolMaterials.STAR_PLATINUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet STEEL = new ToolSet(ToolMaterials.STEEL, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STORMYX = new ToolSet(ToolMaterials.STORMYX, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);

    public static final Item RED_AEGIS_SWORD = new SwordItem(ToolMaterials.CELESTIUM, 5, -3.0F, new Item.Settings().group(MythicMetals.MYTHICMETALS_TOOLS));
    public static final Item WHITE_AEGIS_SWORD = new SwordItem(ToolMaterials.CELESTIUM, 3, -2.5F, new Item.Settings().group(MythicMetals.MYTHICMETALS_TOOLS));

    public static class Frogery {
        public static final Item FROGE = new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof().equipmentSlot(stack -> EquipmentSlot.HEAD));
    }

    public static void register() {
        ADAMANTITE.register("adamantite");
        AETHERIUM.register("aetherium");
        AQUARIUM.register("aquarium");
        BANGLUM.register("banglum");
        BRONZE.register("bronze");
        CARMOT.register("carmot");
        CELESTIUM.register("celestium");
        COPPER.register("copper");
        DISCORDIUM.register("discordium");
        DURASTEEL.register("durasteel");
        RegistryHelper.item("etherite_sword", ETHERITE_SWORD);
        RegistryHelper.item("froge", Frogery.FROGE);
        HALLOWED.register("hallowed");
        KYBER.register("kyber");
        METALLURGIUM.register("metallurgium");
        MYTHRIL.register("mythril");
        ORICHALCUM.register("orichalcum");
        OSMIUM.register("osmium");
        PALLADIUM.register("palladium");
        PROMETHEUM.register("prometheum");
        QUADRILLUM.register("quadrillum");
        QUICKSILVER.register("quicksilver");
        RUNITE.register("runite");
        SILVER.register("silver");
        STAR_PLATINUM.register("star_platinum");
        STEEL.register("steel");
        STORMYX.register("stormyx");
        RegistryHelper.addonItem("white_aegis_sword", WHITE_AEGIS_SWORD);
        RegistryHelper.addonItem("red_aegis_sword", RED_AEGIS_SWORD);

    }
}