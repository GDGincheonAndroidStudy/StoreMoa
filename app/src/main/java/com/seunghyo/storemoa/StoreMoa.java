package com.seunghyo.storemoa;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

public class StoreMoa extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "font.ttf"))
                .addBold(Typekit.createFromAsset(this, "font.ttf"))
                .addItalic(Typekit.createFromAsset(this, "font.ttf"))
                .addBoldItalic(Typekit.createFromAsset(this, "font.ttf"));
    }
}
