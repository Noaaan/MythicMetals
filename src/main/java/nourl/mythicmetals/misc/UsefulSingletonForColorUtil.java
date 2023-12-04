package nourl.mythicmetals.misc;

import io.wispforest.owo.ui.core.Color;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import java.util.List;

public class UsefulSingletonForColorUtil {
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

    /**
     * Contains constants for all the colors used for various text and particles
     */
    public static class MetalColors {
        public static final Color BANGLUM = Color.ofFormatting(Formatting.GOLD);
        public static final Color CARMOT = Color.ofRgb(0xE63E73);
        public static final Color OSMIUM = Color.ofRgb(0x4C63A5);
        public static final Style CARMOT_STYLE = Style.EMPTY.withColor(CARMOT.rgb());
        public static final Color MORKITE = Color.ofRgb(0x00E3D6);
        public static final Color MYTHRIL = Color.ofRgb(0x3485C8);
        public static final Color RED_AEGIS = Color.ofRgb(0xDA1F00);
        public static final Color STARRITE = Color.ofRgb(0xF56CE3);
        public static final Color PALLADIUM = Color.ofRgb(0xDF7323);
        public static final Color PROMETHEUM = Color.ofRgb(0x3A6A56);
        public static final Color KYBER = Color.ofRgb(0xDEA5FF);
        public static final Style AQUA_STYLE = Style.EMPTY.withColor(Formatting.AQUA);
        public static final Style GOLD_STYLE = Style.EMPTY.withColor(Formatting.GOLD);
        public static final Style PALLADIUM_STYLE = Style.EMPTY.withColor(MetalColors.PALLADIUM.rgb());
        public static final Style TIDESINGER_BLUE = Style.EMPTY.withColor(0x2F88FB);

        public static final CoralColor BRAIN = new CoralColor("brain", Style.EMPTY.withColor(0xE17DB7));
        public static final CoralColor BUBBLE = new CoralColor("bubble", Style.EMPTY.withColor(0xCB44BD));
        public static final CoralColor FIRE = new CoralColor("fire", Style.EMPTY.withColor(0xA2222E));
        public static final CoralColor HORN = new CoralColor("horn", Style.EMPTY.withColor(0xEAE94B));
        public static final CoralColor TUBE = new CoralColor("tube", Style.EMPTY.withColor(0x3F5BDF));
    }

    public record CoralColor(String name, Style style) {}
}
