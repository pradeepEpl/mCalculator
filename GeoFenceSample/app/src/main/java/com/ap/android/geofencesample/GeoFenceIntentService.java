package com.ap.android.geofencesample;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class GeoFenceIntentService extends IntentService {

    String TAG = "GeoFenceIntentService";

    public GeoFenceIntentService() {
        super("GeoFenceIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
