package com.ak.app.haloburger.ui.reward;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RewardViewAdapter;
import com.ak.app.haloburger.api.LocationList;
import com.ak.app.haloburger.api.RewardList;
import com.ak.app.haloburger.custom.ui.LocationViewAdapter;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RewardFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private MainActivity mActivity;
    private TextView textPageTitle;
    private View rootView;
    private TextView rewardpointsTextView, rewardpointsvalueTextView;
    private RelativeLayout viewactivityLayoutView, layoutRewardMain;
    private FetchDataAdapter fetchDataAdapter;
    private RewardList mRewardList;
    private RecyclerView recyclerView;
    private RewardViewAdapter mAdapter;


    public RewardFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_reward, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enjoy_mygoodies_layout_viewactivity:
                mActivity.setDisplayView(new RedeemActivityFragment(), true);
            break;
        }
    }

    private void fetchingData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("appkey", getResources().getString(R.string.appkey));
        headers.put("auth_token", authToken);
        String serviceUrl = "/rewards";
        layoutRewardMain.setVisibility(View.INVISIBLE);
        fetchDataAdapter.requestGET(serviceUrl,"locationRequest", headers);

    }

    @Override
    public void initView() {
        rewardpointsTextView = rootView
                .findViewById(R.id.enjoy_mygoodies_text_rewardpoints);
        rewardpointsvalueTextView = rootView
                .findViewById(R.id.enjoy_mygoodies_text_rewardpointsvalue);
        viewactivityLayoutView = rootView
                .findViewById(R.id.enjoy_mygoodies_layout_viewactivity);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        layoutRewardMain = (RelativeLayout) rootView
                .findViewById(R.id.layout_reward_main);
        viewactivityLayoutView.setOnClickListener(this);

    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_reward));
    }

    @Override
    public void setFontView() {
        MyTypeface.setRewardPointTitleFont(rewardpointsTextView, mActivity);
        MyTypeface.setRewardPointValueFont(rewardpointsvalueTextView,mActivity);

    }

    private void setViewValue(RewardList mRewardList) {

        String balancePoint = String.valueOf(mRewardList.getBalance().getPoints());
        rewardpointsvalueTextView.setText(balancePoint);
        layoutRewardMain.setVisibility(View.VISIBLE);
        mAdapter = new RewardViewAdapter(mRewardList.getRewards(), mRewardList.getBalance().getPoints());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void processFinish(JSONObject response) {


        final Gson gson = new Gson();

        mRewardList = gson.fromJson(String.valueOf(response), RewardList.class);

        try{
            if(mRewardList.getStatus()) {
                setViewValue(mRewardList);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
