package com.mythicmetals.misc;

import com.mythicmetals.api.v2.*;
import com.mythicmetals.client.properties.HasDrillFuelProperty;
import com.mythicmetals.item.MythicMaterials;
import io.wispforest.owo.util.ReflectionUtils;
import java.util.HashMap;
import java.util.Map;

public class DebugHelper {
    public static final Map<String, ArmorSet> ARMOR_MAP = new HashMap<>();
    public static final Map<String, ToolSet> TOOL_MAP = new HashMap<>();

    @SuppressWarnings("UnstableApiUsage")
    public static void init() {
        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            if (material.armorSet() != null) {
                ARMOR_MAP.put(name, material.armorSet());
            }
            if (material.toolSet() != null) {
                TOOL_MAP.put(name, material.toolSet());
            }
        });
    }
}
