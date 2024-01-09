package nourl.mythicmetals.item.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import nourl.mythicmetals.item.MythicItems;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.UUID;

public class StormyxShield extends ShieldItem {
    public static final int MAGIC_DAMAGE_REDUCTION = 2;
    public StormyxShield(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return super.getMaxUseTime(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.STORMYX.getIngot());
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {

        var mapnite = HashMultimap.create(this.getAttributeModifiers(slot));

        mapnite.put(AdditionalEntityAttributes.MAGIC_PROTECTION,
                new EntityAttributeModifier(UUID.fromString("82b91018-24a1-11ed-861d-0242ac120002"),
                        "Magic protection",
                        MAGIC_DAMAGE_REDUCTION,
                        EntityAttributeModifier.Operation.ADDITION));

        return (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) ? mapnite : super.getAttributeModifiers(slot);
    }
}
