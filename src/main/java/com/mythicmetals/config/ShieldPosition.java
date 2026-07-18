package com.mythicmetals.config;

public enum ShieldPosition {
    TOP_LEFT(2, 2),
    TOP_RIGHT(84, 2),
    BOTTOM_LEFT(2, 80),
    BOTTOM_RIGHT(84, 80),
    DISABLED(0, 0);

    private final int x;
    private final int y;

    ShieldPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int calculateWidth(float windowWidth) {
        return (int) (windowWidth / 100 * x);
    }

    public int calculateHeight(float windowHeight) {
        return (int) (windowHeight / 100 * y);
    }
}
