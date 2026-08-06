package com.mythicmetals.compat;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mythicmetals.api.v2.Material;
import com.mythicmetals.item.component.MythicDataComponents;
import com.mythicmetals.item.component.TidesingerPatternComponent;
import com.mythicmetals.item.MythicMaterials;
import com.pigicial.wikirenderer.render.batch.BatchRenderable;
import com.pigicial.wikirenderer.render.entity.EntityRenderable;
import com.pigicial.wikirenderer.screen.RenderScreen;
import com.pigicial.wikirenderer.screen.ScreenSchedulerAndSaver;
import io.wispforest.owo.util.ReflectionUtils;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import java.util.ArrayList;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommands.literal;

public class IsometricArmorStandExporter {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(literal("mythicmetals-batch-render-armor")
            .executes(IsometricArmorStandExporter::batchRenderArmor)
        );
    }

    public static int batchRenderArmor(CommandContext<FabricClientCommandSource> context) {
        List<EntityRenderable> renderables = new ArrayList<>();

        ReflectionUtils.iterateAccessibleStaticFields(MythicMaterials.class, Material.class, (material, name, field) -> {
            if (material != MythicMaterials.TIDESINGER && material.armorSet() != null) {
                var armorSet = material.armorSet();
                var armorStand = new ArmorStand(EntityType.ARMOR_STAND, context.getSource().getLevel());
                armorSet.getPlayerItems().forEach(armorItem -> {
                    var armorStack = armorItem.getDefaultInstance();
                    var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
                    if (equippableComponent != null) {
                        armorStand.setItemSlot(equippableComponent.slot(), armorStack);
                    }
                });
                armorStand.setNoBasePlate(true);
                armorStand.setInvisible(true);
                renderables.add(EntityRenderable.fromEntity(armorStand));
            }
        });

        // Handle Tidesinger specifically, since it has five distinct variants
        if (MythicMaterials.TIDESINGER.armorSet() == null) return 1;
        TidesingerPatternComponent.TIDESINGER_VARIANTS.keySet().forEach(patternItem -> {
            var armorStand = new ArmorStand(EntityType.ARMOR_STAND, context.getSource().getLevel());
            var armorSet = MythicMaterials.TIDESINGER.armorSet();
            armorSet.getPlayerItems().forEach(armorItem -> {
                var armorStack = armorItem.getDefaultInstance();
                armorStack.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(patternItem));
                var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
                armorStand.setItemSlot(equippableComponent.slot(), armorStack);
            });
            armorStand.setNoBasePlate(true);
            armorStand.setInvisible(true);
            renderables.add(EntityRenderable.fromEntity(armorStand));
        });

        var batchRender = BatchRenderable.of("mythicmetals", renderables);
        var renderScreen = new RenderScreen(batchRender);

        ScreenSchedulerAndSaver.schedule(renderScreen);

        return 1;
    }
}
