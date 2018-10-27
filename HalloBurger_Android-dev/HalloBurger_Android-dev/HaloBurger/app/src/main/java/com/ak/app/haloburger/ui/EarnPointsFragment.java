package com.ak.app.haloburger.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.api.UserCodeRes;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class EarnPointsFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private MainActivity mActivity;
    private TextView textPageTitle, userCode, footerText;
    private View rootView;
    private Button redirectRewards;
    private FetchDataAdapter fetchDataAdapter;
    private UserCodeRes mUserCodeRes;
    private ImageView QRCode;

    public EarnPointsFragment() {
        // Required empty public constructor
    }

    private void fetchingData() throws JSONException {

        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("appkey", getResources().getString(R.string.appkey));
        headers.put("auth_token", authToken);


        fetchDataAdapter.requestGET("/user/code", "/user/code",  headers);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_earn_points, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("Earn Points");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        try {
            fetchingData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.redirect_rewards:
                mActivity.navigation.setSelectedItemId(R.id.navigation_rewards);
                break;
        }
    }

    @Override
    public void initView() {
        userCode = rootView.findViewById(R.id.userCode);
        footerText = rootView
                .findViewById(R.id.footer_text);
        redirectRewards = rootView
                .findViewById(R.id.redirect_rewards);
        redirectRewards.setOnClickListener(this);
        QRCode = rootView.findViewById(R.id.QRCode);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_earn));
    }

    @Override
    public void setFontView() {
        MyTypeface.setFooterFont(footerText, mActivity);
        MyTypeface.setUserCodeFont(userCode, mActivity);
        MyTypeface.setButtonFont(redirectRewards, mActivity);
    }

    @Override
    public void processFinish(JSONObject response) {

        final Gson gson = new Gson();

        mUserCodeRes = gson.fromJson(String.valueOf(response), UserCodeRes.class);

        if(mUserCodeRes.getStatus()){
            Drawable d = getResources().getDrawable(R.drawable.tap_to_reveal_blured);
            new AppHelper().createQRCode(mUserCodeRes.getUsercode(), d, QRCode, mActivity);
            userCode.setText(mUserCodeRes.getUsercode());
        }

    }
}
