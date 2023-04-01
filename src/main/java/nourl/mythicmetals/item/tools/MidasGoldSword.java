package nourl.mythicmetals.item.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.wispforest.owo.nbt.NbtKey;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

//            if (goldCount >= 1280) {
//                bonus += 1;
//            } - What? Its like I have something planned... Just you wait :)

            mapnite.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Item.ATTACK_DAMAGE_MODIFIER_ID, "Damage modifier", baseDamage + bonus, EntityAttributeModifier.Operation.ADDITION));

        }
        return slot == EquipmentSlot.MAINHAND ? mapnite : super.getAttributeModifiers(slot);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> lines, TooltipContext context) {
        int lineIndex = 1;

        if (lines.size() > 2) {
            var enchantCount = stack.getEnchantments().size();
            lineIndex = enchantCount + 1;
        }

        int goldCount = stack.get(MidasGoldSword.GOLD_FOLDED);

        if (goldCount < 64) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.0").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 64").formatted(Formatting.GOLD));
        }
        if (goldCount >= 64 && goldCount < 128) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.1").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 128").formatted(Formatting.GOLD));
        }
        if (goldCount >= 128 && goldCount < 192) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.2").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 192").formatted(Formatting.GOLD));
        }
        if (goldCount >= 192 && goldCount < 256) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.3").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 256").formatted(Formatting.GOLD));
        }
        if (goldCount >= 256 && goldCount < 320) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.4").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 320").formatted(Formatting.GOLD));
        }
        if (goldCount >= 320 && goldCount < 640) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.5").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 320").formatted(Formatting.GOLD));
        }
        if (goldCount >= 640) {
            lines.add(lineIndex, Text.translatable("tooltip.midas_gold.level.grass").formatted(Formatting.GOLD));
            lines.add(lineIndex + 1, Text.literal(goldCount + " / 640").formatted(Formatting.GOLD));
        }
    }
}
