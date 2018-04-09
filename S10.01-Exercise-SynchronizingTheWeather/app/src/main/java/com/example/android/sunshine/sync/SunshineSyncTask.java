//  DONE (1) Create a class called SunshineSyncTask
//  DONE (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      DONE (3) Within syncWeather, fetch new weather data
//      DONE (4) If we have valid results, delete the old data and insert the new
package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class SunshineSyncTask {
    synchronized public static void syncWeather(Context context) {
        Log.d("SunshineSyncTask", "About to begin syncWeather...");
        URL weatherUrl = NetworkUtils.getUrl(context);
        String jsonWeatherResponse = "";
        ContentValues[] weatherValues = null;
        try {
            jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherUrl);
            weatherValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeatherResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (weatherValues != null) {
            ContentResolver cr = context.getContentResolver();
            cr.delete(WeatherContract.WeatherEntry.CONTENT_URI,
                    null,
                    null);
            cr.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI,
                    weatherValues);
        }
    }
}