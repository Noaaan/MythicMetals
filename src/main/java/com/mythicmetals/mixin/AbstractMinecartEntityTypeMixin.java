package com.mythicmetals.mixin;

import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import java.util.ArrayList;
import java.util.Arrays;

@Mixin(AbstractMinecartEntity.Type.class)
public abstract class AbstractMinecartEntityTypeMixin {
    @Shadow
    @Final
    @Mutable
    private static AbstractMinecartEntity.Type[] field_7673;

    @Unique
    private static final AbstractMinecartEntity.Type BANGLUM_TNT = mythicmetals$addType("BANGLUM_TNT");

    @Unique
    private static final AbstractMinecartEntity.Type PALLADIUM_MINECART = mythicmetals$addType("PALLADIUM_MINECART");

    @Invoker("<init>")
    public static AbstractMinecartEntity.Type mythicmetals$init(String internalName, int internalId) {
        throw new AssertionError();
    }

    @Unique
    private static AbstractMinecartEntity.Type mythicmetals$addType(String internalName) {
        ArrayList<AbstractMinecartEntity.Type> types = new ArrayList<>(Arrays.asList(AbstractMinecartEntityTypeMixin.field_7673));
        AbstractMinecartEntity.Type type = mythicmetals$init(internalName, types.get(types.size() - 1).ordinal() + 1);
        types.add(type);
        AbstractMinecartEntityTypeMixin.field_7673 = types.toArray(new AbstractMinecartEntity.Type[0]);
        return type;
    }
}
