package com.mythicmetals.compat;

import com.glisco.isometricrenders.render.BatchRenderable;
import com.glisco.isometricrenders.render.EntityRenderable;
import com.glisco.isometricrenders.screen.RenderScreen;
import com.glisco.isometricrenders.screen.ScreenScheduler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mythicmetals.armor.MythicArmor;
import com.mythicmetals.component.MythicDataComponents;
import com.mythicmetals.component.TidesingerPatternComponent;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
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
        if (MythicArmor.ARMOR_MAP.isEmpty()) {
            context.getSource().sendFeedback(Component.literal("Unable to summon. Somehow the armor map is empty..."));
            return 0; // "how could this happen to me? I made my mistakes..."
        }

        List<EntityRenderable> renderables = new ArrayList<>();

//        MythicArmor.ARMOR_MAP.values().forEach(armorSet -> {
//            if (!armorSet.equals(MythicArmor.TIDESINGER)) {
//            // Configure the armor stand to our liking
//            var armorStand = new ArmorStand(EntityType.ARMOR_STAND, context.getSource().getWorld());
//            armorSet.getArmorItems().forEach(armorItem -> {
//                var armorStack = armorItem.getDefaultInstance();
//                var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
//                armorStand.setItemSlot(equippableComponent.slot(), armorStack);
//            });
//            armorStand.setNoBasePlate(true);
//            armorStand.setInvisible(true);
//            renderables.add(new EntityRenderable(armorStand));
//            }
//        });
//
//        // Handle Tidesinger specifically, since it has five distinct variants
//        TidesingerPatternComponent.TIDESINGER_VARIANTS.keySet().forEach(patternItem -> {
//            var armorStand = new ArmorStand(EntityType.ARMOR_STAND, context.getSource().getWorld());
//            var armorSet = MythicArmor.TIDESINGER;
//            armorSet.getArmorItems().forEach(armorItem -> {
//                var armorStack = armorItem.getDefaultInstance();
//                armorStack.set(MythicDataComponents.TIDESINGER, TidesingerPatternComponent.fromItem(patternItem));
//                var equippableComponent = armorStack.get(DataComponents.EQUIPPABLE);
//                armorStand.setItemSlot(equippableComponent.slot(), armorStack);
//            });
//            armorStand.setNoBasePlate(true);
//            armorStand.setInvisible(true);
//            renderables.add(new EntityRenderable(armorStand));
//        });

        var batchRender = BatchRenderable.of("mythicmetals", renderables);
        var renderScreen = new RenderScreen(batchRender);

        ScreenScheduler.schedule(renderScreen);

        return 1;
    }
}
