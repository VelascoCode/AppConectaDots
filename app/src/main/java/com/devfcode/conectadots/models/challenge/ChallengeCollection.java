package com.devfcode.conectadots.models.challenge;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cvelasco on 02/04/16.
 */
public class ChallengeCollection {
    @SerializedName("challenges")
    public List<Challenge> challenges;
}
