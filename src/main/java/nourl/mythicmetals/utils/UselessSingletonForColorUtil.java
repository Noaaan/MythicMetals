package nourl.mythicmetals.utils;

public class UselessSingletonForColorUtil {
    public static float[] splitRGBToFloats(int rgb) {
        return new float[]{(rgb >> 16) / 255f, ((rgb >> 8) & 0xFF) / 255f, (rgb & 0xFF) / 255f};
    }

    // thx chyzman
    public static int getGreenToRedGradientByValue(float currentValue, float maxValue) {
        int r = (int) ((255 * (maxValue - currentValue)) / maxValue);
        int g = (int) ((255 * currentValue) / maxValue);
        int b = 0;
        return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
    }
}
