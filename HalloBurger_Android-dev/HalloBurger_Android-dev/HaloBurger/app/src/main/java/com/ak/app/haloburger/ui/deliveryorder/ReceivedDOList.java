package com.ak.app.haloburger.ui.deliveryorder;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RewardViewAdapter;
import com.ak.app.haloburger.api.DoListResponse;
import com.ak.app.haloburger.api.LocationList;
import com.ak.app.haloburger.custom.ui.DOListViewAdapter;
import com.ak.app.haloburger.util.MyTypeface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class ReceivedDOList extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse {

    private Main2Activity mActivity;
    private View rootView;
    private RecyclerView recyclerView;
    private DOListViewAdapter mAdapter;
    private FetchDataAdapter fetchDataAdapter;
    private DoListResponse mDoListResponse;
    private TextView textDoNumberTitle, textDeleveryDate, textReceivedDate, textQty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Main2Activity.getInstance().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        rootView = inflater.inflate(R.layout.fragment_received_dolist, container, false);

        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }

    private void fetchingData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("Authorization", "Bearer " + authToken);
        String serviceUrl = "/shipments/inventory_receipts";
        fetchDataAdapter.requestGET(serviceUrl,"inventory_receipts", headers);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            fetchingData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        mActivity.setPageTitle("RECEIVED DO LIST");
        textDoNumberTitle = rootView.findViewById(R.id.text_do_number_title);
        textDeleveryDate = rootView.findViewById(R.id.text_delevery_date);
        textReceivedDate =  rootView.findViewById(R.id.text_received_date);
        textQty= rootView.findViewById(R.id.text_qty);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
    }

    @Override
    public void setFontView() {
        MyTypeface.setTableTitleFont(textDoNumberTitle, mActivity);
        MyTypeface.setTableTitleFont(textDeleveryDate, mActivity);
        MyTypeface.setTableTitleFont(textReceivedDate, mActivity);
        MyTypeface.setTableTitleFont(textQty, mActivity);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void processFinish(JSONObject response) {
        Log.i("elang","elang :"+response);

        final Gson gson = new Gson();

        mDoListResponse = gson.fromJson(String.valueOf(response), DoListResponse.class);


        mAdapter = new DOListViewAdapter(mDoListResponse.getDeliveryOrders());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }
}
