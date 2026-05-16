package com.mythicmetals.compat;

import com.glisco.isometricrenders.render.BatchRenderable;
import com.glisco.isometricrenders.render.EntityRenderable;
import com.glisco.isometricrenders.screen.RenderScreen;
import com.glisco.isometricrenders.screen.ScreenScheduler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mythicmetals.api.v2.Material;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.TidesingerPatternComponent;
import com.mythicmetals.item.MythicMaterials;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class IsometricArmorStandExporter {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("mythicmetals-batch-render-armor")
            .executes(IsometricArmorStandExporter::batchRenderArmor)
        );
    }

    public static int batchRenderArmor(CommandContext<FabricClientCommandSource> context) {
        List<EntityRenderable> renderables = new ArrayList<>();

        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            // TODO - Handle Tidesinger explicitly, since I want to summon the five variants
            if (material != MythicMaterials.TIDESINGER && material.armorSet() != null) {
                var armorSet = material.armorSet();
                var armorStand = new ArmorStand(EntityType.ARMOR_STAND, context.getSource().getWorld());
                armorSet.getPlayerItems().forEach(armorItem -> {
                    var armorStack = armorItem.getDefaultInstance();
                    var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
                    armorStand.setItemSlot(equippableComponent.slot(), armorStack);
                });
                armorStand.setNoBasePlate(true);
                armorStand.setInvisible(true);
                renderables.add(new EntityRenderable(armorStand));
            }
        });

        // Handle Tidesinger specifically, since it has five distinct variants
        if (MythicMaterials.TIDESINGER.armorSet() == null) return 1;
        TidesingerPatternComponent.TIDESINGER_VARIANTS.keySet().forEach(patternItem -> {
            var armorStand = new ArmorStand(EntityType.ARMOR_STAND, context.getSource().getWorld());
            var armorSet = MythicMaterials.TIDESINGER.armorSet();
            armorSet.getPlayerItems().forEach(armorItem -> {
                var armorStack = armorItem.getDefaultInstance();
                armorStack.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(patternItem));
                var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
                armorStand.setItemSlot(equippableComponent.slot(), armorStack);
            });
            armorStand.setNoBasePlate(true);
            armorStand.setInvisible(true);
            renderables.add(new EntityRenderable(armorStand));
        });

        var batchRender = BatchRenderable.of("mythicmetals", renderables);
        var renderScreen = new RenderScreen(batchRender);

        ScreenScheduler.schedule(renderScreen);

        return 1;
    }
}
