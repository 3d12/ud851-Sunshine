package com.example.android.sunshine.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

// DONE (5) Create a new class called SunshineSyncIntentService that extends IntentService
//  DONE (6) Create a constructor that calls super and passes the name of this class
//  DONE (7) Override onHandleIntent, and within it, call SunshineSyncTask.syncWeather
public class SunshineSyncIntentService extends IntentService {
    public static final String ACTION_REFRESH_DATA_FROM_SOURCE = "action-refresh-data-from-source";

    public SunshineSyncIntentService() {
        super("SunshineSyncIntentService");
        Log.d("SunshineIntentService", "Service was successfully created via default constructor");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("SunshineIntentService", "About to handle incoming intent...");
        if (intent != null) {
            String action = intent.getAction();
            if (action != null && action.equals(ACTION_REFRESH_DATA_FROM_SOURCE)) {
                Log.d("SunshineIntentService", "Intent action is to refresh data! Sending command to refresh...");
                SunshineSyncTask.syncWeather(this.getBaseContext());
            }
        }
        Log.d("SunshineIntentService", "Incoming intent has been handled, service is now stopping...");
    }
}