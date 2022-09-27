package nourl.mythicmetals.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// [ConjuringCopy] https://github.com/wisp-forest/conjuring/blob/1.18/src/main/java/com/glisco/conjuring/mixin/WorldRendererInvoker.java
@Mixin(WorldRenderer.class)
public interface WorldRendererInvoker {

    @Invoker("drawShapeOutline")
    static void mythicmetals$drawShapeOutline(
            MatrixStack matrixStack,
            VertexConsumer vertexConsumer,
            VoxelShape voxelShape,
            double offsetX,
            double offsetY,
            double offsetZ,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        throw new AssertionError();
    }

}
