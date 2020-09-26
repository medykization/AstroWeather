package com.example.astroweather.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.data.DataSettings;

import java.util.ArrayList;

public class ForecastViewAdapter extends RecyclerView.Adapter<ForecastViewAdapter.ForecastViewHolder> {

    private ArrayList<ForecastInfoItem> mExampleList;

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ForecastViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.temp);
            mTextView2 = itemView.findViewById(R.id.pressure);
        }
    }

    public ForecastViewAdapter(ArrayList<ForecastInfoItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        ForecastViewHolder evh = new ForecastViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        DataSettings dataSettings = DataSettings.getInstance();
        ForecastInfoItem currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageId());
        holder.mTextView1.setText("Temperature " + currentItem.getTemperature() + dataSettings.units);
        holder.mTextView2.setText("Pressure " + currentItem.getPressure() + "hPa");
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

}
