package com.mythicmetals.item.tools;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import com.mythicmetals.item.MythicItems;
import com.mythicmetals.misc.RegistryHelper;
import java.util.List;
import java.util.UUID;

public class StormyxShield extends ShieldItem {
    public static final UUID STORMYX_SHIELD_MAGIC_PROTECTION_ID = UUID.fromString("3d11289a-816d-41a9-88f3-f513e12f5ec9");
    public static final int MAGIC_DAMAGE_REDUCTION = 2;

    public StormyxShield(Settings settings) {
        super(settings);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity entity) {
        return super.getMaxUseTime(stack, entity);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(MythicItems.STORMYX.getIngot());
    }

    public static AttributeModifiersComponent createStormyxShieldAttributes() {
        var modifier = new EntityAttributeModifier(RegistryHelper.id("stormyx_shield_magic_protection"), MAGIC_DAMAGE_REDUCTION, EntityAttributeModifier.Operation.ADD_VALUE);
        return AttributeModifiersComponent.builder()
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, AttributeModifierSlot.MAINHAND)
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION, modifier, AttributeModifierSlot.OFFHAND)
            .build();
    }
}
