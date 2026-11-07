package com.mythicmetals.data;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import net.minecraft.util.datafix.fixes.DataComponentRemainderFix;
import org.jspecify.annotations.Nullable;

public class MidasGoldComponentDataFix extends DataComponentRemainderFix {

    public MidasGoldComponentDataFix(Schema outputSchema, String name) {
        this(outputSchema, name, "mythicmetals:gold_folded", "mythicmetals:midas_gold");
    }

    public MidasGoldComponentDataFix(final Schema outputSchema, final String name, final String componentId, final String newComponentId) {
        super(outputSchema, name, componentId, newComponentId);
    }

    protected @Nullable <T> Dynamic<T> fixComponent(Dynamic<T> input) {
        var goldFolded = input.get("gold_folded").result();
        if (goldFolded.isPresent()) {
            return input
                .remove("is_royal")
                .set("folds", goldFolded.get())
                .set("legacy", input.createBoolean(true));
        }
        return input;
    }
}
