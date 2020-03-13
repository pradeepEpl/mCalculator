package com.ap.android.navcomponent;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class MainApplication extends Application {

    private static Context context;
    SharedPreferences sharedpreferences;;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //createPref();
    }

    private void createPref() {
        sharedpreferences = getSharedPreferences("the", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("theme", "appB");
        editor.commit();
    }

    public static Context getAppContext() {
        return MainApplication.context;
    }
}
