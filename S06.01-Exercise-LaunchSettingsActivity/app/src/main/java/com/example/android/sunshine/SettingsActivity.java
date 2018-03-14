package com.example.android.sunshine;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {
    private ComponentName mReturnActivity;

    @Override
    @TargetApi(16)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ComponentName tempCallingActivity = this.getCallingActivity();
        if (tempCallingActivity != null) {
            mReturnActivity = tempCallingActivity;
        } else {
            Toast.makeText(this, "Calling Activity was null!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, mReturnActivity.getClass()));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
