package nourl.mythicmetals.client;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.Positioning;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfigModel;
import nourl.mythicmetals.utils.RegistryHelper;

public class ShieldHudComponent {
    public static final Identifier COMPONENT_ID = RegistryHelper.id("shield_component");
    public static final Identifier TEXTURE = RegistryHelper.id("textures/gui/shield_status.png");

    public static Positioning shieldPos = MythicConfigModel.ShieldPosition.asRelativePos(MythicMetals.CONFIG.shieldPosition());

    public static void init() {
        Hud.add(RegistryHelper.id("shield_overlay"), () -> Containers.draggable(Sizing.content(), Sizing.content(),
            Containers.verticalFlow(Sizing.content(), Sizing.content())
                .child(Components.texture(TEXTURE, 0, 0, 32, 16, 64, 64)
                        .positioning(Positioning.layout())))
                .positioning(shieldPos)
        );
    }
}
