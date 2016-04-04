package com.devfcode.conectadots.models.sequential;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cvelasco on 28/03/16.
 */
public class SequentialDot {
    @SerializedName("dot_x")
    public float dotX;
    @SerializedName("dot_y")
    public float dotY;
    @SerializedName("value")
    public String value;
}
