package com.devfcode.conectadots.generators;

import android.content.Context;

import com.devfcode.conectadots.models.challenge.ChallengeCollection;
import android.content.Context;
import com.devfcode.conectadots.utils.json.JSONManager;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Created by cvelasco on 02/04/16.
 */

public class ChallengeMenuGenerator {

    public static ChallengeCollection getChallengeCollection(Context context,String jsonFileName)
    {
        JSONObject jsonObject = JSONManager.loadJSONObjectFromAsset(context,jsonFileName);
        Gson gson=new Gson();
        ChallengeCollection challengeCollection = gson.fromJson(jsonObject.toString(),ChallengeCollection.class);
        return challengeCollection;
    }
}
