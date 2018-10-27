package com.ak.app.haloburger.custom.ui;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.GPSAdapter;
import com.ak.app.haloburger.api.LocationList;
import com.ak.app.haloburger.api.Restaurant;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by el on 05/06/18.
 */

public class LocationViewAdapter extends RecyclerView.Adapter<LocationViewAdapter.MyViewHolder> {

    private List<Restaurant> restaurants;
    private double offerLatitude, offerLongitude, userLatitude, userLongitude;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, addressTextView, attributeTextView;
        public TextView btnTxtMap, btnTxtCall;

        public LinearLayout locationLayout;

        public MyViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.findzoes_list_text_name);
            addressTextView = (TextView) view.findViewById(R.id.findzoes_list_text_address);
            attributeTextView = (TextView) view.findViewById(R.id.findzoes_list_text_attribute);
            btnTxtMap = (TextView) view.findViewById(R.id.btn_txt_map);
            btnTxtCall= (TextView) view.findViewById(R.id.btn_txt_call);

            MyTypeface.setButtonFont(btnTxtMap, Main2Activity.getInstance());
            MyTypeface.setButtonFont(btnTxtCall, Main2Activity.getInstance());

            nameTextView.setText("");
            addressTextView.setText("");
            attributeTextView.setText("");


            MyTypeface.setLocNameFont(nameTextView, MainActivity.getInstance());
            MyTypeface.setLocAddrFont(addressTextView, MainActivity.getInstance());
            MyTypeface.setLocAddrFont(attributeTextView, MainActivity.getInstance());



        }


    }

    public LocationViewAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_location_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Restaurant restaurant = restaurants.get(position);
        Log.i("elang","elang position :"+position+" - "+restaurant.getName());

        holder.nameTextView.setText(restaurant.getName());
        holder.addressTextView.setText(restaurant.getAddress());
        holder.attributeTextView.setText(restaurant.getAppDisplayText());

    }


    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
