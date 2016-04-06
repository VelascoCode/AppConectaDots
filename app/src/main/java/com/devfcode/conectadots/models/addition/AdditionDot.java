package com.devfcode.conectadots.models.addition;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cvelasco on 04/04/16.
 */
public class AdditionDot {
    @SerializedName("dot_x")
    public float dotX;
    @SerializedName("dot_y")
    public float dotY;
    @SerializedName("first_number_place")
    public int firstNumberPlace;
    @SerializedName("first_symbol_operation")
    public String firstSymbolOperation;
    @SerializedName("second_number_place")
    public int secondNumberPlace;
    @SerializedName("second_symbol_operation")
    public String secondSymbolOperation;
    @SerializedName("result_operation")
    public int resultOperation;
}
