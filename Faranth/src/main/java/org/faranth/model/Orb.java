package org.faranth.model;

/**
 * Created by quill on 20/11/13.
 */
public class Orb {

    private static final int FIRE_HASH    = 0x1;
    private static final int WATER_HASH   = 0x2;
    private static final int WOOD_HASH    = 0x4;
    private static final int LIGHT_HASH   = 0x8;
    private static final int DARK_HASH    = 0x10;
    private static final int HEART_HASH   = 0x20;
    private static final int JAMMER_HASH  = 0x40;
    private static final int POISON_HASH  = 0x80;

    public enum Attribute {
        FIRE(FIRE_HASH),
        WATER(WATER_HASH),
        WOOD(WOOD_HASH),
        LIGHT(LIGHT_HASH),
        DARK(DARK_HASH),
        HEART(HEART_HASH),
        JAMMER(JAMMER_HASH),
        POISON(POISON_HASH);

        private final int value;

        private Attribute(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    private Attribute attribute;
    private boolean isEnhanced;
    private int x;
    private int y;

    public Orb(Attribute type, int x, int y) {
        this.attribute = type;
        this.x = x;
        this.y = y;
    }

    public Orb(Attribute type, boolean isEnhanced, int x, int y) {
        this.isEnhanced = isEnhanced;
        this.attribute = type;
        this.x = x;
        this.y = y;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public boolean isEnhanced() {
        return isEnhanced;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
