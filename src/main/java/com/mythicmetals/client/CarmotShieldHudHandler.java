package com.mythicmetals.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mythicmetals.MythicMetals;
import com.mythicmetals.config.ShieldPosition;
import com.mythicmetals.data.attachments.MythicDataAttachments;
import com.mythicmetals.item.armor.CarmotShield;
import com.mythicmetals.misc.RegistryHelper;
import com.mythicmetals.misc.UsefulSingletonForColorUtil;
import io.wispforest.owo.ui.component.TextureComponent;
import io.wispforest.owo.ui.container.UIContainers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class CarmotShieldHudHandler {
    public static final Identifier COMPONENT_ID = RegistryHelper.id("shield_overlay");
    public static final String SHIELD_BACKGROUND_ID = "shield_background";
    public static final String SHIELD_COMPONENT_ID = "shield_overlay";
    public static final Identifier TEXTURE = RegistryHelper.id("textures/gui/shield_status.png");

//    public static void init() {
//        Hud.add(COMPONENT_ID, () ->
//            UIContainers.draggable(Sizing.content(), Sizing.content(),
//                    UIContainers.verticalFlow(Sizing.content(), Sizing.content())
//                        .child(new CarmotShieldComponent(TEXTURE, 0, 16, 64, 16, 64, 32)
//                            .id(SHIELD_BACKGROUND_ID))
//                        .child(new CarmotShieldComponent(TEXTURE, 0, 0, 64, 16, 64, 32)
//                            .id(SHIELD_COMPONENT_ID)
//                            .positioning(Positioning.absolute(0, 0))
//                        ))
//                .positioning(MythicMetals.CONFIG.shieldPosition().asRelativePos())
//        );
//        MythicMetals.CONFIG.subscribeToShieldPosition(shieldPosition -> {
//            var component = Hud.getComponent(COMPONENT_ID);
//            if (component != null) {
//                component.positioning(MythicMetals.CONFIG.shieldPosition().asRelativePos());
//            }
//        });
//    }
//
//    @SuppressWarnings({"DataFlowIssue", "UnstableApiUsage"})
//    public static void tick() {
//        if (Hud.hasComponent(COMPONENT_ID) && Minecraft.getInstance().player != null) {
//            var player = Minecraft.getInstance().player;
//            var shieldBar = (CarmotShieldComponent) ((ParentUIComponent) Hud.getComponent(COMPONENT_ID)).childById(TextureComponent.class, SHIELD_COMPONENT_ID);
//            var background = (CarmotShieldComponent) ((ParentUIComponent) Hud.getComponent(COMPONENT_ID)).childById(TextureComponent.class, SHIELD_BACKGROUND_ID);
//            var carmotShield = player.getAttached(MythicDataAttachments.CARMOT_SHIELD_ATTACHMENT);
//
//            double shieldhealth = carmotShield.shieldHealth();
//            // Hide Shield if it's not needed
//            if (shieldhealth == 0 || MythicMetals.CONFIG.shieldPosition().equals(ShieldPosition.DISABLED)) {
//                shieldBar.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
//                background.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
//                return;
//            }
//
//            boolean isShieldBroken = shieldhealth <= 0.0;
//            int shieldX = Mth.ceil(16 + 46 * (shieldhealth / carmotShield.getMaxHealth(player)));
//
//            CarmotShieldComponent.barShouldBeRed = player.hurtTime > 0 || isShieldBroken;
//            // Hide bar if shield is broken
//            if (isShieldBroken) {
//                shieldBar.visibleArea(PositionedRectangle.of(0, 0, 0, 0));
//            } else {
//                shieldBar.visibleArea(PositionedRectangle.of(0, 0, Size.of(shieldX, 16)));
//            }
//            background.visibleArea(PositionedRectangle.of(0, 0, Size.of(64, 16)));
//        }
//    }

    public static void render(GuiGraphics guiGraphics, DeltaTracker tickCounter) {
        var player = Minecraft.getInstance().player;
        if (player == null) return;
        if (MythicMetals.CONFIG.shieldPosition() == ShieldPosition.DISABLED) return;
        if (CarmotShield.getMaxHealth(player) > 0.0) {
            var carmotShield = player.getAttached(MythicDataAttachments.CARMOT_SHIELD_ATTACHMENT);
            var shieldPosition = MythicMetals.CONFIG.shieldPosition();

            int u = 0;
            int v = 16;
            int width = 64;
            int height = 16;
            // background
            guiGraphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TEXTURE,
                shieldPosition.calculateWidth(guiGraphics.guiWidth()),
                shieldPosition.calculateHeight(guiGraphics.guiHeight()), u, v, width, height, 64, 32
            );
            // shield health
            var shieldWidth = shieldPosition.calculateWidth(guiGraphics.guiWidth());
            var shieldHeight = shieldPosition.calculateHeight(guiGraphics.guiHeight());
            guiGraphics.fill(
                shieldWidth, shieldHeight, shieldWidth / 2, shieldHeight, UsefulSingletonForColorUtil.rainbow()
            );
        }
    }

    public static class CarmotShieldComponent extends TextureComponent {

        public static final Color HEALTHY_COLOR = Color.ofRgb(0x52CBFF);
        public static final Color DAMAGED_COLOR = Color.ofRgb(0xE0343A);
        public static boolean barShouldBeRed = false;

        protected CarmotShieldComponent(Identifier texture, int u, int v, int regionWidth, int regionHeight, int textureWidth, int textureHeight) {
            super(texture, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
        }

        // FIXME
        @Override
        public void draw(OwoUIGraphics graphics, int mouseX, int mouseY, float partialTicks, float delta) {
//            if (barShouldBeRed) {
//                RenderSystem.setShaderColor(DAMAGED_COLOR.red(), DAMAGED_COLOR.green(), DAMAGED_COLOR.blue(), 1.0f);
//            } else {
//                RenderSystem.setShaderColor(HEALTHY_COLOR.red(), HEALTHY_COLOR.green(), HEALTHY_COLOR.blue(), 1.0f);
//            }
//            graphics.guiRenderState.reset();
//            RenderSystem.setShaderColor(1, 1, 1, 1);
            super.draw(graphics, mouseX, mouseY, partialTicks, delta);
        }
    }
}
