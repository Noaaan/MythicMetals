package com.mythicmetals.misc;

import com.mythicmetals.api.v2.ArmorSet;
import com.mythicmetals.api.v2.Material;
import com.mythicmetals.item.MythicMaterials;
import io.wispforest.owo.util.ReflectionUtils;
import java.util.HashMap;
import java.util.Map;

public class DebugHelper {
    public static final Map<String, ArmorSet> ARMOR_MAP = new HashMap<>();

    @SuppressWarnings("UnstableApiUsage")
    public static void init() {
        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            if (material.armorSet() != null) {
                var armorSet = material.armorSet();
                ARMOR_MAP.put(name, armorSet);
            }
        });
    }
}
