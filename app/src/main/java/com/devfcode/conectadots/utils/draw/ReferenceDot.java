package com.devfcode.conectadots.utils.draw;

/**
 * Created by cvelasco on 29/03/16.
 */
public class ReferenceDot {
    private float dotX;
    private float dotY;

    public ReferenceDot(float dotX, float dotY) {
        this.dotX = dotX;
        this.dotY = dotY;
    }

    public float getDotX() {
        return dotX;
    }

    public float getDotY() {
        return dotY;
    }

    public void setDotX(float dotX) {
        this.dotX = dotX;
    }

    public void setDotY(float dotY) {
        this.dotY = dotY;
    }
}
