package nourl.mythicmetals.client;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.TextureComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;

public class CarmotShieldHudHandler {
    public static final Identifier COMPONENT_ID = RegistryHelper.id("shield_overlay");
    public static final String SHIELD_BACKGROUND_ID = "shield_background";
    public static final String SHIELD_COMPONENT_ID = "shield_overlay";
    public static final Identifier TEXTURE = RegistryHelper.id("textures/gui/shield_status.png");

    public static void init() {
        /* TODO - Two main approaches here
         *  Main issue right now is the shield hud does not overlay correctly due to the texture rendered being 32x64
         *  By adding more children and attaching them to layouts, we could simply #child and #remove
         *  the components when needed. At the cost of increased state management and more components,
         *  we would solve the issue.
         *  Alternative is to do some math and offset the bars manually when needed. It requires some more math,
         *  but eliminates the need for any extra components.
         *  Find out what is the best approach.
         */
        Hud.add(COMPONENT_ID, () ->
            Containers.draggable(Sizing.content(), Sizing.content(),
                Containers.verticalFlow(Sizing.content(), Sizing.content())
                    .child(Components.texture(TEXTURE, 0, 32, 64, 32, 64, 64)
                        .id(SHIELD_BACKGROUND_ID))
                    .child(Components.texture(TEXTURE, 0, 0, 64, 32, 64, 64)
                        .id(SHIELD_COMPONENT_ID)
                        .positioning(Positioning.absolute(0, 0))
                    ))
            .positioning(MythicMetals.CONFIG.shieldPosition().asRelativePos())
        );
        MythicMetals.CONFIG.subscribeToShieldPosition(shieldPosition -> {
            var component = Hud.getComponent(COMPONENT_ID);
            if (component != null) {
                component.positioning(MythicMetals.CONFIG.shieldPosition().asRelativePos());
            }
        });
    }

    // TODO - Cleanup
    @SuppressWarnings("DataFlowIssue")
    public static void tick() {
        if (Hud.hasComponent(COMPONENT_ID) && MinecraftClient.getInstance().player != null) {
            var player = MinecraftClient.getInstance().player;
            var carmotShield = player.getComponent(MythicMetals.CARMOT_SHIELD);
            boolean isShieldHurt = player.hurtTime > 0;
            boolean isShieldBroken = carmotShield.shieldHealth == 0;
            int shieldX = MathHelper.ceil(16 + 45 * (carmotShield.shieldHealth / carmotShield.getMaxHealth()));
            int shieldY = isShieldHurt || isShieldBroken ? 16 : 0;
            var shieldBar = (TextureComponent) ((ParentComponent) Hud.getComponent(COMPONENT_ID)).childById(TextureComponent.class, SHIELD_COMPONENT_ID);
            var background = (TextureComponent) ((ParentComponent) Hud.getComponent(COMPONENT_ID)).childById(TextureComponent.class, SHIELD_BACKGROUND_ID);
            if (isShieldBroken) {
                shieldBar.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
            } else {
                shieldBar.visibleArea(PositionedRectangle.of(0, shieldY, Size.of(shieldX, 16)));
            }
            background.visibleArea(PositionedRectangle.of(0, shieldY, Size.of(64, 16)));

        }
    }
}
