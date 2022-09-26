package nourl.mythicmetals.utils;

public class UselessSingletonForColorUtil {
    public static float[] splitRGBToFloats(int rgb) {
        return new float[]{(rgb >> 16) / 255f, ((rgb >> 8) & 0xFF) / 255f, (rgb & 0xFF) / 255f};
    }
}
