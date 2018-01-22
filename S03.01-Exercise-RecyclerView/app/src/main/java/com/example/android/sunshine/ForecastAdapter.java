package com.example.android.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Nick on 1/21/2018.
 */

class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {
    private String[] mWeatherData;
    ForecastAdapter(String[] startData) {
        if (startData != null) {
            this.mWeatherData = startData;
        } else {
            this.mWeatherData = new String[]{"Error."};
        }
    }

    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View returnView = inflater.inflate(R.layout.forecast_list_item, parent, false);
        ForecastAdapterViewHolder returnVH = new ForecastAdapterViewHolder(returnView);
        return returnVH;
    }

    @Override
    public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        } else {
            return mWeatherData.length;
        }
    }

    void setmWeatherData(String[] mWeatherData) {
        if (mWeatherData != null) {
            this.mWeatherData = mWeatherData;
        } else {
            this.mWeatherData = new String[]{"Loading..."};
        }
        this.notifyDataSetChanged();
    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {
        final TextView mWeatherTextView;
        ForecastAdapterViewHolder(View view) {
            super(view);
            this.mWeatherTextView = (TextView) view.findViewById(R.id.tv_weather_data);
        }
    }
}
