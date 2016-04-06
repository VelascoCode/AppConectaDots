package com.devfcode.conectadots.application;

import android.app.Application;

import com.devfcode.conectadots.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by cvelasco on 06/04/16.
 */
public class ConectaDotsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/LondrinaSketch-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
