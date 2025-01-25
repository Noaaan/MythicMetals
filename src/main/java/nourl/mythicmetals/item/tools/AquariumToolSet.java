package nourl.mythicmetals.item.tools;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import nourl.mythicmetals.misc.RegistryHelper;

import static net.minecraft.entity.attribute.EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE;

public class AquariumToolSet extends ToolSet {
    public static final String ABILITY_MODIFIER = "aquarium_tool_underwater_mining_speed_bonus";
    public AquariumToolSet(ToolMaterial material, int[] damage, float[] speed) {
        super(material, damage, speed);
    }

    @Override
    public AttributeModifiersComponent.Builder createAttributeBuilder(ToolMaterial material, double damage, float speed) {
        return super.createAttributeBuilder(material, damage, speed)
            .add(EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, new EntityAttributeModifier(RegistryHelper.id(ABILITY_MODIFIER), 1.0f, ADD_MULTIPLIED_BASE), AttributeModifierSlot.MAINHAND);
    }
}
