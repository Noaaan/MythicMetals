package com.mythicmetals.api.v2;

import com.mythicmetals.item.MythicMaterials;
import io.wispforest.owo.util.ReflectionUtils;
import java.util.*;

public class MaterialHelper {
    private MaterialHelper() {
    }

    public static final Map<String, ArmorSet> ARMOR_MAP = new HashMap<>();
    public static final Map<String, ToolSet> TOOL_MAP = new HashMap<>();
    public static final Map<String, BlockSet> BLOCK_SET_MAP = new HashMap<>();

    @SuppressWarnings("UnstableApiUsage")
    public static void init() {
        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            if (material.armorSet() != null) {
                ARMOR_MAP.put(name, material.armorSet());
            }
            if (material.toolSet() != null) {
                TOOL_MAP.put(name, material.toolSet());
            }
            if (material.blockSet() != null) {
                BLOCK_SET_MAP.put(name, material.blockSet());
            }
            if (!material.extraBlocks().isEmpty()) {

            }
        });
    }
}
