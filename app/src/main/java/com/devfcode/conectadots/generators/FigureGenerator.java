package com.devfcode.conectadots.generators;

import android.content.Context;

import com.google.gson.Gson;
import com.devfcode.conectadots.models.sequential.SequentialDotCollection;
import com.devfcode.conectadots.utils.json.JSONManager;

import org.json.JSONObject;


/**
 * Created by cvelasco on 28/03/16.
 */


public class FigureGenerator {

    public static SequentialDotCollection getSequentialDotCollection(Context context, String jsonFileName){

        JSONObject jsonObject = JSONManager.loadJSONObjectFromAsset(context, jsonFileName);
        Gson gson = new Gson();
        SequentialDotCollection sequentialDotCollection = gson.fromJson(jsonObject.toString(), SequentialDotCollection.class);
        return sequentialDotCollection;

    }
}
