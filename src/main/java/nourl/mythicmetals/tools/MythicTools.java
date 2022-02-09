package nourl.mythicmetals.tools;

import io.wispforest.owo.registration.reflect.SimpleFieldProcessingSubject;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Rarity;
import nourl.mythicmetals.item.MythicItems;
import nourl.mythicmetals.registry.RegisterSounds;
import nourl.mythicmetals.utils.RegistryHelper;

import java.lang.reflect.Field;

@SuppressWarnings("unused")
public class MythicTools implements SimpleFieldProcessingSubject<ToolSet> {
    // Arrays for weapon/tool damage: sword, axe, pickaxe, shovel, and hoe
    public static final int [] DEFAULT_DAMAGE = new int[]{3, 4, 2, 1, 0};
    public static final int [] BETTER_AXE_DAMAGE = new int[]{3, 5, 2, 1, 0};
    // Arrays for weapon/tool attack speed: sword, axe, pickaxe, shovel and hoe
    public static final float [] SLOW_ATTACK_SPEED = new float[]{-2.5F, -3.2F, -2.9F, -3.0F, -3.1F};
    public static final float [] DEFAULT_ATTACK_SPEED = new float[]{-2.4F, -3.1F, -2.8F, -2.9F, -3.0F};
    public static final float [] BETTER_AXE_ATTACK_SPEED = new float[]{-2.4F, -3.0F, -2.8F, -2.9F, -3.0F};
    public static final float [] FASTER_ATTACK_SPEED = new float[]{-2.2F, -2.9F, -2.7F, -2.8F, -2.8F};
    public static final float [] HIGHEST_ATTACK_SPEED = new float[]{-2.1F, -2.9F, -2.6F, -2.8F, -2.7F};

    public static final ToolSet ADAMANTITE = new ToolSet(ToolMaterials.ADAMANTITE, BETTER_AXE_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet AQUARIUM = new ToolSet(ToolMaterials.AQUARIUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet BANGLUM = new ToolSet(ToolMaterials.BANGLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet LEGENDARY_BANGLUM = new ToolSet(ToolMaterials.LEGENDARY_BANGLUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED);
    public static final ToolSet BRONZE = new ToolSet(ToolMaterials.BRONZE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CARMOT = new ToolSet(ToolMaterials.CARMOT, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet CELESTIUM = new ToolSet(ToolMaterials.CELESTIUM, DEFAULT_DAMAGE, HIGHEST_ATTACK_SPEED, settings -> settings.rarity(Rarity.RARE));
    public static final ToolSet COPPER = new ToolSet(ToolMaterials.COPPER, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet DURASTEEL = new ToolSet(ToolMaterials.DURASTEEL, BETTER_AXE_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet HALLOWED = new ToolSet(ToolMaterials.HALLOWED, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.rarity(Rarity.UNCOMMON));
    public static final ToolSet KYBER = new ToolSet(ToolMaterials.KYBER, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED);
    public static final ToolSet METALLURGIUM = new ToolSet(ToolMaterials.METALLURGIUM, BETTER_AXE_DAMAGE, BETTER_AXE_ATTACK_SPEED, settings -> settings.fireproof().rarity(Rarity.RARE));
    public static final ToolSet MYTHRIL = new ToolSet(ToolMaterials.MYTHRIL, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet ORICHALCUM = new ToolSet(ToolMaterials.ORICHALCUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED);
    public static final ToolSet OSMIUM = new ToolSet(ToolMaterials.OSMIUM, BETTER_AXE_DAMAGE, SLOW_ATTACK_SPEED);
    public static final ToolSet PALLADIUM = new ToolSet(ToolMaterials.PALLADIUM, DEFAULT_DAMAGE, BETTER_AXE_ATTACK_SPEED, Item.Settings::fireproof);
    public static final ToolSet PROMETHEUM = new ToolSet(ToolMaterials.PROMETHEUM, BETTER_AXE_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet QUADRILLUM = new ToolSet(ToolMaterials.QUADRILLUM, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet RUNITE = new ToolSet(ToolMaterials.RUNITE, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);
    public static final ToolSet STAR_PLATINUM = new ToolSet(ToolMaterials.STAR_PLATINUM, DEFAULT_DAMAGE, FASTER_ATTACK_SPEED);
    public static final ToolSet STORMYX = new ToolSet(ToolMaterials.STORMYX, DEFAULT_DAMAGE, DEFAULT_ATTACK_SPEED);

    public static final Item RED_AEGIS_SWORD = new SwordItem(ToolMaterials.CELESTIUM, 5, -3.0F, new Item.Settings().fireproof().rarity(Rarity.UNCOMMON));
    public static final Item WHITE_AEGIS_SWORD = new SwordItem(ToolMaterials.CELESTIUM, 3, -2.5F, new Item.Settings().fireproof().rarity(Rarity.UNCOMMON));

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
    }

    public static class Frogery {
        public static final Item FROGE = new Item(new FabricItemSettings().rarity(Rarity.EPIC).fireproof().equipmentSlot(stack -> EquipmentSlot.HEAD));
        public static final Item DOGE = new MythicItems.CustomMusicDiscItem(42, RegisterSounds.DOG, new FabricItemSettings().rarity(Rarity.EPIC).fireproof().equipmentSlot(stack -> EquipmentSlot.HEAD).maxCount(1));
    }

}