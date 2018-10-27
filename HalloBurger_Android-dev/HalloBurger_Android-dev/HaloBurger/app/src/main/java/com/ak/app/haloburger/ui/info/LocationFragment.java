package com.ak.app.haloburger.ui.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.api.LocationList;
import com.ak.app.haloburger.api.Restaurant;
import com.ak.app.haloburger.custom.ui.LocationViewAdapter;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse {

    private Main2Activity mActivity;
    private View rootView;
    private FetchDataAdapter fetchDataAdapter;
    private LocationList mLocationList;
    private RecyclerView recyclerView;
    private LinearLayout locationNearbyText;
    private LocationViewAdapter mAdapter;


    public LocationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_location, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        getActivity().setTitle("Locations");
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        locationNearbyText = rootView.findViewById(R.id.locationNearbyText);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();

    }

    @Override
    public void setFontView() {

    }

    @Override
    public void processFinish(JSONObject response) {
        Log.i("elang","elang result: "+response);

        final Gson gson = new Gson();

        mLocationList = gson.fromJson(String.valueOf(response), LocationList.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        Restaurant r = new Restaurant();
        mLocationList = new LocationList();

        r.setAddress("Pager Gunung");
        r.setName("Brocode");
        r.setAppDisplayText("Brocode");

        Restaurant r2 = new Restaurant();
        r2.setAddress("Cimahi");
        r2.setName("Brocode");
        r2.setAppDisplayText("Brocode");

        Restaurant r3 = new Restaurant();
        r3.setAddress("Jatinangor");
        r3.setName("Brocode");
        r3.setAppDisplayText("Brocode");

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(r);
        restaurants.add(r2);
        restaurants.add(r3);

        Log.i("elang array","elang size: "+restaurants.size());


        mLocationList.setRestaurants(restaurants);
        mLocationList.setStatus(true);

        mAdapter = new LocationViewAdapter(mLocationList.getRestaurants());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        recyclerView.setVisibility(View.VISIBLE);
        locationNearbyText.setVisibility(View.INVISIBLE);

    }
}
