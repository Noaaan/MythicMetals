package nourl.mythicmetals.armor;

import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final ArmorSet ADAMANTITE = new ArmorSet(MythicArmorMaterials.ADAMANTITE);
    public static final ArmorSet AQUARIUM = new ArmorSet(MythicArmorMaterials.AQUARIUM);
    public static final ArmorSet BANGLUM = new ArmorSet(MythicArmorMaterials.BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet(MythicArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new CarmotArmorSet(MythicArmorMaterials.CARMOT, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet CELESTIUM = new ArmorSet(MythicArmorMaterials.CELESTIUM, settings -> settings.rarity(Rarity.RARE));
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
    public static final ArmorSet PALLADIUM = new ArmorSet(MythicArmorMaterials.PALLADIUM, Item.Settings::fireproof);
    public static final ArmorSet PROMETHEUM = new ArmorSet(MythicArmorMaterials.PROMETHEUM);
    public static final ArmorSet RUNITE = new RuniteArmorSet(MythicArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet(MythicArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(MythicArmorMaterials.STAR_PLATINUM);
    public static final ArmorSet STEEL = new ArmorSet(MythicArmorMaterials.STEEL);
    public static final ArmorSet STORMYX = new ArmorSet(MythicArmorMaterials.STORMYX);

    @Override
    public void processField(ArmorSet armorSet, String name, Field f) {
        armorSet.register(name);
    }

    @Override
    public Class<ArmorSet> getTargetFieldType() {
        return ArmorSet.class;
    }
}
