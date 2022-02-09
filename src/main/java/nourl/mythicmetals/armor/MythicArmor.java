package nourl.mythicmetals.armor;

import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicArmor implements SimpleFieldProcessingSubject<ArmorSet> {
    public static final ArmorSet ADAMANTITE = new ArmorSet(ArmorMaterials.ADAMANTITE);
    public static final ArmorSet AQUARIUM = new ArmorSet(ArmorMaterials.AQUARIUM);
    public static final ArmorSet BANGLUM = new ArmorSet(ArmorMaterials.BANGLUM);
    public static final ArmorSet LEGENDARY_BANGLUM = new ArmorSet(ArmorMaterials.LEGENDARY_BANGLUM);
    public static final ArmorSet BRONZE = new ArmorSet(ArmorMaterials.BRONZE);
    public static final ArmorSet CARMOT = new ArmorSet(ArmorMaterials.CARMOT);
    public static final ArmorSet CELESTIUM = new ArmorSet(ArmorMaterials.CELESTIUM, settings -> settings.rarity(Rarity.RARE));
    public static final ArmorSet COPPER = new ArmorSet(ArmorMaterials.COPPER);
    public static final ArmorSet DURASTEEL = new ArmorSet(ArmorMaterials.DURASTEEL);
    public static final ArmorSet HALLOWED = new HallowedArmorSet(ArmorMaterials.HALLOWED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ArmorSet KYBER = new ArmorSet(ArmorMaterials.KYBER);
    public static final ArmorSet METALLURGIUM = new MetallurgiumArmorSet(ArmorMaterials.METALLURGIUM, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ArmorSet MIDAS_GOLD = new ArmorSet(ArmorMaterials.MIDAS_GOLD);
    public static final ArmorSet MYTHRIL = new ArmorSet(ArmorMaterials.MYTHRIL);
    public static final ArmorSet ORICHALCUM = new ArmorSet(ArmorMaterials.ORICHALCUM);
    public static final ArmorSet OSMIUM = new ArmorSet(ArmorMaterials.OSMIUM);
    public static final ArmorSet PALLADIUM = new ArmorSet(ArmorMaterials.PALLADIUM, Item.Settings::fireproof);
    public static final ArmorSet PROMETHEUM = new ArmorSet(ArmorMaterials.PROMETHEUM);
    public static final ArmorSet RUNITE = new ArmorSet(ArmorMaterials.RUNITE);
    public static final ArmorSet SILVER = new ArmorSet(ArmorMaterials.SILVER);
    public static final ArmorSet STAR_PLATINUM = new ArmorSet(ArmorMaterials.STAR_PLATINUM);
    public static final ArmorSet STORMYX = new ArmorSet(ArmorMaterials.STORMYX);

    @Override
    public void processField(ArmorSet armorSet, String name, Field f) {
        armorSet.register(name);
    }

    @Override
    public Class<ArmorSet> getTargetFieldType() {
        return ArmorSet.class;
    }
}
