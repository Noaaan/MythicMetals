package com.mythicmetals.client;

import com.mythicmetals.client.rendering.BlockOutlineOverrideRenderState;
import com.mythicmetals.client.rendering.OrichalcumHammerRenderState;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;

public class MythicRenderStateKeys {
    public static final RenderStateDataKey<CarmotShieldRenderState> CARMOT_SHIELD_STATE_KEY = RenderStateDataKey.create();
    public static final RenderStateDataKey<OrichalcumHammerRenderState> ORICHALCUM_HAMMER_RENDER_STATE = RenderStateDataKey.create();
    public static final RenderStateDataKey<BlockOutlineOverrideRenderState> OUTLINE_OVERRIDE_RENDER_STATE_KEY = RenderStateDataKey.create();
}
