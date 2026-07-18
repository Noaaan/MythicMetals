package com.mythicmetals.config;

public class ShieldPosition {
    public boolean enabled = true;
    public int x = 2;
    public int y = 2;

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public static int calculateWidth(float windowWidth, int x) {
        return (int) (windowWidth / 100 * x);
    }

    public static int calculateHeight(float windowHeight, int y) {
        return (int) (windowHeight / 100 * y);
    }

    public boolean isDisabled() {
        return !enabled;
    }
}
