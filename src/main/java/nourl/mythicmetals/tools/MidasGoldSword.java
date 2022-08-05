package nourl.mythicmetals.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.wispforest.owo.nbt.NbtKey;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.MathHelper;

public class MidasGoldSword extends SwordItem {

    public static final NbtKey<Integer> GOLD_FOLDED = new NbtKey<>("GoldFolded", NbtKey.Type.INT);

    public MidasGoldSword(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {

        Multimap<EntityAttribute, EntityAttributeModifier> mapnite = this.getAttributeModifiers(slot);

        int goldCount = stack.get(GOLD_FOLDED);
        if (goldCount > 0) {

            mapnite = HashMultimap.create(mapnite);

            mapnite.removeAll(EntityAttributes.GENERIC_ATTACK_DAMAGE);

            float baseDamage = getAttackDamage();

            int bonus = MathHelper.clamp(MathHelper.floor((float) goldCount / 64), 0, 5);

            if (goldCount >= 640) {
                bonus += 1;
            }

            mapnite.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Item.ATTACK_DAMAGE_MODIFIER_ID, "Damage modifier", baseDamage + bonus, EntityAttributeModifier.Operation.ADDITION));

        }
        return slot == EquipmentSlot.MAINHAND ? mapnite : super.getAttributeModifiers(slot);
    }

}
