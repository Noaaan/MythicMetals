package nourl.mythicmetals.compat;

import com.glisco.isometricrenders.render.BatchRenderable;
import com.glisco.isometricrenders.render.EntityRenderable;
import com.glisco.isometricrenders.screen.RenderScreen;
import com.glisco.isometricrenders.screen.ScreenScheduler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import nourl.mythicmetals.armor.MythicArmor;
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
            context.getSource().sendFeedback(Text.literal("Unable to summon. Somehow the armor map is empty..."));
            return 0; // "how could this happen to me? I made my mistakes..."
        }

        List<EntityRenderable> renderables = new ArrayList<>();

        MythicArmor.ARMOR_MAP.values().forEach(armorSet -> {
            // Configure the armor stand to our liking
            var armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, context.getSource().getWorld());
            armorSet.getArmorItems().forEach(armorItem -> {
                var armorStack = new ItemStack(armorItem);
                armorStand.equipStack(armorItem.getSlotType(), armorStack);
            });
            armorStand.setHideBasePlate(true);
            armorStand.setInvisible(true);
            renderables.add(new EntityRenderable(armorStand));
        });

        var batchRender = BatchRenderable.of("mythicmetals", renderables);
        var renderScreen = new RenderScreen(batchRender);

        ScreenScheduler.schedule(renderScreen);

        return 1;
    }
}
