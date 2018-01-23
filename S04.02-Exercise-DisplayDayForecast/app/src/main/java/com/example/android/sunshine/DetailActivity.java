package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView mDetailData;

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // DONE (2) Display the weather forecast that was passed from MainActivity
        mDetailData = (TextView) this.findViewById(R.id.tv_detail_data);
        Intent intentThatCalledThis = this.getIntent();
        if (intentThatCalledThis.hasExtra(Intent.EXTRA_TEXT)) {
            mDetailData.setText(intentThatCalledThis.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}