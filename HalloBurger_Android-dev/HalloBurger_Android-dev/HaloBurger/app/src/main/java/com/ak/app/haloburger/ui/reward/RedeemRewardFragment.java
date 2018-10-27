package com.ak.app.haloburger.ui.reward;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.GPSAdapter;
import com.ak.app.haloburger.api.RedeemWarnResponse;
import com.ak.app.haloburger.api.RestaurantLocate;
import com.ak.app.haloburger.api.RestaurantLocateParent;
import com.ak.app.haloburger.api.Reward;
import com.ak.app.haloburger.api.RewardClaimReq;
import com.ak.app.haloburger.api.RewardClaimRes;
import com.ak.app.haloburger.api.RewardClaimWarnReq;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.JsonConverter;
import com.ak.app.haloburger.util.MyTypeface;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RedeemRewardFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private MainActivity mActivity;
    private View rootView;
    private FetchDataAdapter fetchDataAdapter;
    private RestaurantLocateParent mRestaurantLocateParent;
    private String rewardLocateid = "";
    private Reward mReward;
    private ImageView claim_btn;
    private RewardClaimWarnReq mRewardClaimWarnReq;
    private RedeemWarnResponse mRedeemWarnResponse;
    private RewardClaimRes mRewardClaimRes;
    private RewardClaimReq mRewardClaimReq;
    private  ImageView QRCode;
    private TextView redeemcodeTV, mRedeemtitleTV, titleTV;

    private String warn = "";
    private String rewardCode;

    @SuppressLint("ValidFragment")
    public RedeemRewardFragment(Reward mReward) {
        this.mReward = mReward;
    }

    public RedeemRewardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void fetchingData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("appkey", getResources().getString(R.string.appkey));
        headers.put("auth_token", authToken);

        String serviceUrl = "/rewards/locate";

        fetchDataAdapter.requestGET(serviceUrl,serviceUrl, headers);

    }

    private void fetchingWarningValuesData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;

        double userLatitude = GPSAdapter.getGetLatLongObj(mActivity).getLatitude();
        double userLongitude = GPSAdapter.getGetLatLongObj(mActivity).getLongitude();

        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        mRewardClaimWarnReq.setAuth_token(authToken);
        mRewardClaimWarnReq.setReward_id(String.valueOf(mReward.getId()));
        mRewardClaimWarnReq.setLat(String.valueOf(userLatitude));
        mRewardClaimWarnReq.setLat(String.valueOf(userLongitude));
        mRewardClaimWarnReq.setLocation(rewardLocateid);
        mRewardClaimWarnReq.setWarn("true");
        String requestObj = JsonConverter.getGson().toJson(mRewardClaimWarnReq);
        JSONObject obj = new JSONObject(requestObj);

        fetchDataAdapter.requestPOST("/rewards/claim", "/rewards/claim",  obj);

    }

    private void fetchingRewardClaimData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;

        double userLatitude = GPSAdapter.getGetLatLongObj(mActivity).getLatitude();
        double userLongitude = GPSAdapter.getGetLatLongObj(mActivity).getLongitude();

        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        mRewardClaimReq.setAuth_token(authToken);
        mRewardClaimReq.setReward_id(String.valueOf(mReward.getId()));
        mRewardClaimReq.setLat(String.valueOf(userLatitude));
        mRewardClaimReq.setLat(String.valueOf(userLongitude));
        mRewardClaimReq.setLocation(rewardLocateid);
        String requestObj = JsonConverter.getGson().toJson(mRewardClaimReq);
        JSONObject obj = new JSONObject(requestObj);



        fetchDataAdapter.requestPOST("/rewards/claim", "/rewards/claim",  obj);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_redeem_reward, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("Rewards Redeemed Page");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        redeemcodeTV.setVisibility(View.GONE);
        titleTV.setText(mReward.getName());
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reward_claim_image_claim_btn:
                if(AppHelper.getNetworkAvailable(mActivity)){
                    try {
                        fetchingWarningValuesData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    AlertDialogs
                            .showMsgDialog(getResources().getString(R.string.constant_title_message), mActivity
                                            .getString(R.string.error_network_services),
                                    mActivity);
                }
                break;
        }
    }

    @Override
    public void initView() {
        titleTV = (TextView) rootView
                .findViewById(R.id.reward_claim_text_title);
        mRedeemtitleTV = (TextView) rootView
                .findViewById(R.id.reward_claim_text_redeemtitle);
        claim_btn = (ImageView) rootView
                .findViewById(R.id.reward_claim_image_claim_btn);
        claim_btn.setOnClickListener(this);
        QRCode = (ImageView) rootView
                .findViewById(R.id.QRCode);
        redeemcodeTV = (TextView) rootView
                .findViewById(R.id.reward_claim_text_redeemcodevalue);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        mRewardClaimWarnReq = new RewardClaimWarnReq();
        mRewardClaimReq = new RewardClaimReq();
    }

    @Override
    public void setFontView() {
        MyTypeface.setRewardClaimTitleFont(titleTV, mActivity);
        MyTypeface.setRewardClaimDescFont(mRedeemtitleTV,mActivity);
        MyTypeface.setRewardClaimCodeFont(redeemcodeTV, mActivity);
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

    public void showRedeemConfirmMsgDialog(String title, final String message) {
        try {
            AlertDialog.Builder alt_bld = new AlertDialog.Builder(mActivity);
            alt_bld.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                    mActivity.getSupportFragmentManager().popBackStack();
                                }
                            })
                    .setNegativeButton("Confirm",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                    try {
                                        fetchingRewardClaimData();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
            AlertDialog alert = alt_bld.create();
            if (title.equals("")) {
                alert.setTitle(getString(R.string.constant_title_message));
                alert.setIcon(AlertDialog.BUTTON_NEGATIVE);
            } else {
                alert.setTitle(title);

            }
            alert.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createRedeemView() {
        // countDownTimer = null ;
        claim_btn.setVisibility(View.INVISIBLE);
        redeemcodeTV.setVisibility(View.VISIBLE);
        QRCode.setVisibility(View.VISIBLE);
        try {
        } catch (Exception e) {
        }
    }

    @Override
    public void processFinish(JSONObject response) {

        final Gson gson = new Gson();
        if (!warn.equals("") && warn!=null){
            mRewardClaimRes = gson.fromJson(String.valueOf(response), RewardClaimRes.class);
            rewardCode = mRewardClaimRes.getRewardCode();


            Drawable d = getResources().getDrawable(R.drawable.claim_btn_2);
            new AppHelper().createQRCode(rewardCode, d, QRCode, mActivity);

            createRedeemView();
            redeemcodeTV.setText("Code: " + rewardCode);

        }else if(!rewardLocateid.equals("") && rewardLocateid != null && warn.equals("")){
            mRedeemWarnResponse = gson.fromJson(String.valueOf(response), RedeemWarnResponse.class);
            warn = mRedeemWarnResponse.getWarnBody();
            showRedeemConfirmMsgDialog(mRedeemWarnResponse.getWarnTile(), mRedeemWarnResponse.getWarnBody());

        }else{
            mRestaurantLocateParent = gson.fromJson(String.valueOf(response), RestaurantLocateParent.class);
            int size = mRestaurantLocateParent.getRestaurants().size();
            RestaurantLocate mRestaurantLocate = mRestaurantLocateParent.getRestaurants().get(size-1);
            rewardLocateid = String.valueOf(mRestaurantLocate.getId());
        }

    }
}
