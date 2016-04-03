package com.devfcode.conectadots.models.challenge;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cvelasco on 02/04/16.
 */
public class Challenge {
    @SerializedName("name")
    public String name;
    @SerializedName("review")
    public String review;
    @SerializedName("src_img")
    public String srcImg;
    @SerializedName("type")
    public String type;
}
