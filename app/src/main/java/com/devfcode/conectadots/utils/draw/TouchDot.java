package com.devfcode.conectadots.utils.draw;

/**
 * Created by cvelasco on 28/03/16.
 */
public class TouchDot {

    private float dotX;
    private float dotY;
    private float touchMajor;
    private float touchMinor;

    public TouchDot(float dotX, float dotY,float touchMajor,float touchMinor) {
        this.dotX = dotX;
        this.dotY = dotY;
        this.touchMajor=touchMajor;
        this.touchMinor=touchMinor;
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

    public float getTouchMajor() {return touchMajor;}

    public float getTouchMinor() {return touchMinor;}

    public void setTouchMajor(float touchMajor) {this.touchMajor = touchMajor;}

    public void setTouchMinor(float touchMinor) {this.touchMinor = touchMinor;}
}
