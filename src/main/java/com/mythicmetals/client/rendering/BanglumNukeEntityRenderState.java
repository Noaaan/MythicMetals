package com.mythicmetals.client.rendering;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class BanglumNukeEntityRenderState extends EntityRenderState {
    public float fuse;
    public final BlockModelRenderState banglum = new BlockModelRenderState();
    public final BlockModelRenderState morkite = new BlockModelRenderState();
}
