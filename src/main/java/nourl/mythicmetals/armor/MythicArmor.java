package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final Map<String, ArmorSet> ARMOR_MAP = new HashMap<>();
    public static final ArmorSet ADAMANTITE = new ArmorSet(MythicArmorMaterials.ADAMANTITE);
    public static final ArmorSet AQUARIUM = new ArmorSet(MythicArmorMaterials.AQUARIUM);
    public static final ArmorSet BANGLUM = new ArmorSet(MythicArmorMaterials.BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet(MythicArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new ArmorSet(MythicArmorMaterials.CARMOT, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet CELESTIUM = new CelestiumArmorSet(MythicArmorMaterials.CELESTIUM, settings -> settings.rarity(Rarity.RARE).fireproof());
    public static final Item CELESTIUM_ELYTRA = new CelestiumElytra(new OwoItemSettings().rarity(Rarity.RARE).group(MythicMetals.TABBED_GROUP).tab(3).maxDamage(832));
    public static final ArmorSet COPPER = new ArmorSet(MythicArmorMaterials.COPPER);
    public static final ArmorSet DURASTEEL = new ArmorSet(MythicArmorMaterials.DURASTEEL);
    public static final ArmorSet HALLOWED = new HallowedArmorSet(MythicArmorMaterials.HALLOWED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet KYBER = new ArmorSet(MythicArmorMaterials.KYBER);
    public static final ArmorSet LEGENDARY_BANGLUM = new BanglumArmorSet(MythicArmorMaterials.LEGENDARY_BANGLUM, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet(MythicArmorMaterials.METALLURGIUM, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet(MythicArmorMaterials.MIDAS_GOLD);
    public static final ArmorSet MYTHRIL = new ArmorSet(MythicArmorMaterials.MYTHRIL);
    public static final ArmorSet ORICHALCUM = new ArmorSet(MythicArmorMaterials.ORICHALCUM);
    public static final ArmorSet OSMIUM = new ArmorSet(MythicArmorMaterials.OSMIUM);
    public static final ArmorSet OSMIUM_CHAINMAIL = new ArmorSet(MythicArmorMaterials.OSMIUM_CHAINMAIL);
    public static final ArmorSet PALLADIUM = new ArmorSet(MythicArmorMaterials.PALLADIUM, OwoItemSettings::fireproof);
    public static final ArmorSet PROMETHEUM = new PrometheumArmorSet(MythicArmorMaterials.PROMETHEUM);
    public static final ArmorSet RUNITE = new RuniteArmorSet(MythicArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet(MythicArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(MythicArmorMaterials.STAR_PLATINUM);
    public static final ArmorSet STEEL = new ArmorSet(MythicArmorMaterials.STEEL);
    public static final ArmorSet STORMYX = new ArmorSet(MythicArmorMaterials.STORMYX);
    public static final ArmorSet TIDESINGER = new TidesingerArmorSet(MythicArmorMaterials.TIDESINGER, settings -> settings.rarity(Rarity.UNCOMMON));

    @Override
    public void processField(ArmorSet armorSet, String name, Field f) {
        armorSet.register(name);
        ARMOR_MAP.put(name, armorSet);
    }

    @Override
    public void afterFieldProcessing() {
        RegistryHelper.item("celestium_elytra", CELESTIUM_ELYTRA);
    }

    @Override
    public Class<ArmorSet> getTargetFieldType() {
        return ArmorSet.class;
    }
}
