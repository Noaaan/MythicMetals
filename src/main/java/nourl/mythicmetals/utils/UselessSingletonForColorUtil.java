package nourl.mythicmetals.utils;

import io.wispforest.owo.ui.core.Color;

public class UselessSingletonForColorUtil {
    public static float[] splitRGBToFloats(int rgb) {
        return new float[]{(rgb >> 16) / 255f, ((rgb >> 8) & 0xFF) / 255f, (rgb & 0xFF) / 255f};
    }

    // thx chyzman
    public static int getSlightlyDarkerOwoBlueToRedGradient(float currentValue, float maxValue) {
        return Color.RED.interpolate(Color.ofRgb(0x3955E5), currentValue/maxValue).rgb();
    }
}
