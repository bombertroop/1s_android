package com.ak.app.haloburger.ui.reward;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RedeemedActivityAdapter;
import com.ak.app.haloburger.adapter.RewardViewAdapter;
import com.ak.app.haloburger.api.RewardActivityList;
import com.ak.app.haloburger.api.RewardList;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RedeemActivityFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private MainActivity mActivity;
    private TextView textPageTitle;
    private View rootView;
    private TextView activityText, pointsText;
    private TextView claimedText, dateText;
    private Integer totalPoints;
    private FetchDataAdapter fetchDataAdapter;
    private RewardActivityList mRewardActivityList;
    private RedeemedActivityAdapter mAdapter;
    private RecyclerView recyclerView;

    public RedeemActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_redeem_activity, container, false);



        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("View Activity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewactivity_text_claimed:
                dateText.setText("Reward Redeemed ");


                activityText.setBackgroundResource(R.drawable.activity_btn);
                claimedText
                        .setBackgroundResource(R.drawable.claimed_btn_active);
                if (mRewardActivityList != null
                        && mRewardActivityList.getActivities().size() > 0) {
                    mAdapter = new RedeemedActivityAdapter(mRewardActivityList.getActivities());
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(mAdapter);
                    pointsText.setText(""+mAdapter.getTotalPoint());
                }
                break;
            case R.id.viewactivity_text_activity:
                break;
        }
    }

    @Override
    public void initView() {
        activityText = rootView
                .findViewById(R.id.viewactivity_text_activity);
        claimedText = rootView
                .findViewById(R.id.viewactivity_text_claimed);

        dateText = rootView
                .findViewById(R.id.viewactivity_text_date);
        pointsText = rootView
                .findViewById(R.id.viewactivity_text_points);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        activityText.setBackgroundResource(R.drawable.activity_btn_active);
        claimedText.setBackgroundResource(R.drawable.claimed_btn);

        claimedText.setOnClickListener(this);
        dateText.setText("Reward Points");
        pointsText.setText(""+totalPoints);
    }

    private void fetchingUserActivityData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("appkey", getResources().getString(R.string.appkey));
        headers.put("auth_token", authToken);
        String serviceUrl = "/user/activity";
        fetchDataAdapter.requestGET(serviceUrl,serviceUrl, headers);
    }

    private void fetchingRewardsActivityData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("appkey", getResources().getString(R.string.appkey));
        headers.put("auth_token", authToken);
        String serviceUrl = "/rewards/activity";
        fetchDataAdapter.requestGET(serviceUrl,serviceUrl, headers);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();

    }

    @Override
    public void setFontView() {

    }

    @Override
    public void processFinish(JSONObject response) {
        final Gson gson = new Gson();

        mRewardActivityList = gson.fromJson(String.valueOf(response), RewardActivityList.class);

//        setViewValue(mRewardList);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            fetchingRewardsActivityData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
