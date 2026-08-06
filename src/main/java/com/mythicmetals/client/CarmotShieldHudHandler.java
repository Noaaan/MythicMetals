package com.mythicmetals.client;

import com.mythicmetals.MythicMetals;
import com.mythicmetals.config.ShieldPosition;
import com.mythicmetals.data.attachments.MythicDataAttachments;
import com.mythicmetals.item.armor.CarmotShield;
import com.mythicmetals.misc.RegistryHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

@SuppressWarnings("UnstableApiUsage")
public class CarmotShieldHudHandler {
    private CarmotShieldHudHandler() {}

    static final int LOGO_SIZE = 16;
    static final int PIP_U = 16;
    static final int START_PIP_U = 32;
    static final int START_PIP_WIDTH = 11;
    static final int START_PIP_HEIGHT = 16;
    static final int END_PIP_U = 22;
    static final int END_PIP_WIDTH = 6;
    static final int PIPS_WIDTH = 5;
    static final int PIPS_HEIGHT = 16;
    static final int PIPS_V = 0;
    static final int FILLED_PIPS_V = 16;

    public static final Identifier TEXTURE = RegistryHelper.id("textures/gui/shield_status.png");

    public static void render(GuiGraphicsExtractor guiGraphics) {
        var player = Minecraft.getInstance().player;
        if (player == null) return;
        if (!MythicMetals.CONFIG.shieldPosition.enabled()) return;
        var maxShield = CarmotShield.getMaxHealth(player);
        if (maxShield > 0.0) {
            var carmotShield = player.getAttached(MythicDataAttachments.CARMOT_SHIELD_ATTACHMENT);
            if (carmotShield == null) return;

            var pips = Mth.floor(maxShield / 2);
            var filledPips = Mth.ceil(carmotShield.shieldHealth() / 2);

            int xStart = ShieldPosition.calculateWidth(guiGraphics.guiWidth(), MythicMetals.CONFIG.shieldPosition.x());
            int yStart = ShieldPosition.calculateHeight(guiGraphics.guiWidth(), MythicMetals.CONFIG.shieldPosition.y());
            renderOutline(guiGraphics, pips, xStart, yStart);
            renderShieldHealth(guiGraphics, filledPips, xStart, yStart);
        }
    }

    public static void renderOutline(GuiGraphicsExtractor guiGraphics, int pips, int xStart, int yStart) {
        // logo
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart, yStart, 0, 0, LOGO_SIZE, LOGO_SIZE, 64, 32);
        // outline
        for (int i = 0; i < pips; i++) {
            if (i == 0) {
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart + LOGO_SIZE, yStart, START_PIP_U, PIPS_V, START_PIP_WIDTH, START_PIP_HEIGHT, 64, 32);
            } else if (i == pips - 1) {
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart + LOGO_SIZE + PIPS_WIDTH * (i - 1) + START_PIP_WIDTH, yStart, END_PIP_U, PIPS_V, END_PIP_WIDTH, PIPS_HEIGHT, 64, 32);
            } else {
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart + LOGO_SIZE + PIPS_WIDTH * (i - 1) + START_PIP_WIDTH, yStart, PIP_U, PIPS_V, PIPS_WIDTH, PIPS_HEIGHT, 64, 32);
            }
        }
    }

    public static void renderShieldHealth(GuiGraphicsExtractor guiGraphics, int pips, int xStart, int yStart) {
        // logo
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart, yStart, 0, FILLED_PIPS_V, LOGO_SIZE, LOGO_SIZE, 64, 32);
        // bar
        for (int i = 0; i < pips; i++) {
            if (i == 0) {
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart + LOGO_SIZE, yStart, START_PIP_U, FILLED_PIPS_V, START_PIP_WIDTH, START_PIP_HEIGHT, 64, 32);
            } else {
                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, xStart + LOGO_SIZE + PIPS_WIDTH * (i - 1) + START_PIP_WIDTH, yStart, PIP_U, FILLED_PIPS_V, PIPS_WIDTH, PIPS_HEIGHT, 64, 32);
            }
        }
    }
}
