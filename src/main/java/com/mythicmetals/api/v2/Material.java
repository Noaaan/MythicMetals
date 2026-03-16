package com.mythicmetals.api.v2;

import com.google.common.collect.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.util.function.Consumer;

public class Material {

    @NonNull
    public final Item material;
    @Nullable
    public final ToolSet toolSet;
    @Nullable
    public final ArmorSet armorSet;
    public final BiMap<ResourceKey<Item>, Item> extraItems;
    public final BiMap<ResourceKey<Block>, Block> extraBlocks;

    public Material(@NonNull Item material, @Nullable ToolSet toolSet, @Nullable ArmorSet armorSet, BiMap<ResourceKey<Item>, Item> extraItems, BiMap<ResourceKey<Block>, Block> extraBlocks) {
        this.material = material;
        this.toolSet = toolSet;
        this.armorSet = armorSet;
        this.extraItems = extraItems;
        this.extraBlocks = extraBlocks;
    }

    static class Builder {
        private final String name;
        private @NonNull Item material;
        private ToolSet toolSet = null;
        private ArmorSet armorSet = null;
        private final BiMap<ResourceKey<Item>, Item> extraItems = HashBiMap.create();
        private final BiMap<ResourceKey<Block>, Block> extraBlocks = HashBiMap.create();

        public Builder(String materialName) {
            this.name = materialName;
        }

        public Builder create(String materialName) {
            return new Builder(materialName);
        }

        public Builder createMaterial(Consumer<Item.Properties> propsConsumer) {
            var props = new Item.Properties();
            propsConsumer.accept(props);
            this.material = new Item(props);
            return this;
        }

        public Builder createDefaultTools(ToolMaterial material, ToolSet.AttackSpeeds attackSpeeds) {
            this.toolSet = new ToolSet(name, material).createDefault(attackSpeeds);
            return this;
        }

        public Builder createDefaultArmor(ArmorMaterial material) {
            this.armorSet = new ArmorSet(name, material).createDefault();
            return this;
        }

        public Builder addExtraItem(ResourceKey<Item> itemKey, Item item) {
            extraItems.put(itemKey, item);
            return this;
        }

        /**
         * Registers and returns the finished Material
         */
        public Material finish() {
            // TODO - Register stuff
            return new Material(material, toolSet, armorSet, extraItems, extraBlocks);
        }
    }
}
