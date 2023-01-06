package nourl.mythicmetals.misc;

import io.wispforest.owo.ui.core.Color;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.potion.PotionUtil;

import java.util.List;

public class SlowlyMoreUsefulSingletonForColorUtil {
    public static float[] splitRGBToFloats(int rgb) {
        return new float[]{(rgb >> 16) / 255f, ((rgb >> 8) & 0xFF) / 255f, (rgb & 0xFF) / 255f};
    }

    // thx chyzman
    public static int getSlightlyDarkerOwoBlueToRedGradient(float currentValue, float maxValue) {
        return Color.RED.interpolate(Color.ofRgb(0x3955E5), currentValue/maxValue).rgb();
    }

    /**
     * Calculates a tint-index from a potion color
     * Borrowed from <a href="https://github.com/DaFuqs/Spectrum/blob/227502412912cb94f8d1d6335c94a00a91a1fe84/src/main/java/de/dafuqs/spectrum/registries/client/SpectrumColorProviders.java#L169">...</a>
     * @author DaFuqs
     * @param stack         Stack with Potion Effect
     * @param tintIndex     Tint index
     * @return Color as int
     */
    public static int potionColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 1) {
            NbtCompound nbtCompound = stack.getNbt();
            if (nbtCompound != null && nbtCompound.contains("CustomPotionColor", NbtElement.INT_TYPE)) {
                return nbtCompound.getInt("CustomPotionColor");
            }

            List<StatusEffectInstance> effects = PotionUtil.getPotionEffects(stack);
            if (effects.size() > 0) {
                return PotionUtil.getColor(effects);
            }
        }
        return -1;
    }
}
