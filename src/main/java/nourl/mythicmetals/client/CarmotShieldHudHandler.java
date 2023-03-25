package nourl.mythicmetals.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.wispforest.owo.ui.component.TextureComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.config.MythicConfigModel;
import nourl.mythicmetals.misc.RegistryHelper;

public class CarmotShieldHudHandler {
    public static final Identifier COMPONENT_ID = RegistryHelper.id("shield_overlay");
    public static final String SHIELD_BACKGROUND_ID = "shield_background";
    public static final String SHIELD_COMPONENT_ID = "shield_overlay";
    public static final Identifier TEXTURE = RegistryHelper.id("textures/gui/shield_status.png");

    public static void init() {
        Hud.add(COMPONENT_ID, () ->
            Containers.draggable(Sizing.content(), Sizing.content(),
                Containers.verticalFlow(Sizing.content(), Sizing.content())
                    .child(new CarmotShieldComponent(TEXTURE, 0, 16, 64, 16, 64, 32)
                        .id(SHIELD_BACKGROUND_ID))
                    .child(new CarmotShieldComponent(TEXTURE, 0, 0, 64, 16, 64, 32)
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

    @SuppressWarnings("DataFlowIssue")
    public static void tick() {
        if (Hud.hasComponent(COMPONENT_ID) && MinecraftClient.getInstance().player != null) {
            var player = MinecraftClient.getInstance().player;
            var carmotShield = player.getComponent(MythicMetals.CARMOT_SHIELD);
            var shieldBar = (CarmotShieldComponent) ((ParentComponent) Hud.getComponent(COMPONENT_ID)).childById(TextureComponent.class, SHIELD_COMPONENT_ID);
            var background = (CarmotShieldComponent) ((ParentComponent) Hud.getComponent(COMPONENT_ID)).childById(TextureComponent.class, SHIELD_BACKGROUND_ID);

            // Hide Shield if it's not needed
            if (carmotShield.getMaxHealth() == 0 || MythicMetals.CONFIG.shieldPosition().equals(MythicConfigModel.ShieldPosition.DISABLED)) {
                shieldBar.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
                background.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
                return;
            }

            boolean isShieldBroken = carmotShield.shieldHealth == 0;
            int shieldX = MathHelper.ceil(16 + 46 * (carmotShield.shieldHealth / carmotShield.getMaxHealth()));

            CarmotShieldComponent.barShouldBeRed = player.hurtTime > 0 || isShieldBroken;
            // Hide bar if shield is broken
            if (isShieldBroken) {
                shieldBar.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
            } else {
                shieldBar.visibleArea(PositionedRectangle.of(0, 0, Size.of(shieldX, 16)));
            }
            background.visibleArea(PositionedRectangle.of(0, 0, Size.of(64, 16)));
        }
    }

    public static class CarmotShieldComponent extends TextureComponent {

        public static final Color HEALTHY_COLOR = Color.ofRgb(0x52CBFF);
        public static final Color DAMAGED_COLOR = Color.ofRgb(0xE0343A);
        public static boolean barShouldBeRed = false;

        protected CarmotShieldComponent(Identifier texture, int u, int v, int regionWidth, int regionHeight, int textureWidth, int textureHeight) {
            super(texture, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
        }

        @Override
        public void draw(MatrixStack matrices, int mouseX, int mouseY, float partialTicks, float delta) {
            if (barShouldBeRed) {
                RenderSystem.setShaderColor(DAMAGED_COLOR.red(), DAMAGED_COLOR.green(), DAMAGED_COLOR.blue(), 1.0f);
            } else {
                RenderSystem.setShaderColor(HEALTHY_COLOR.red(), HEALTHY_COLOR.green(), HEALTHY_COLOR.blue(), 1.0f);
            }
            super.draw(matrices, mouseX, mouseY, partialTicks, delta);
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }
    }
}
