package com.ak.app.haloburger.ui.info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.ui.auth.ChangePasswordFragment;
import com.ak.app.haloburger.ui.auth.MainSignupFragment;
import com.ak.app.haloburger.util.MyTypeface;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingInfroFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse {

    private MainActivity mActivity;
    private TextView logoutBtn, ChangePasswordText;
    private ImageView btnSetting;
    private View rootView;
    private LogInResponse mLogInResponse;

    public SettingInfroFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_setting_info, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("Setting Page");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_text_ChangePassword:
                mActivity.setDisplayView(new ChangePasswordFragment(), true);
                break;
            case R.id.settings_text_LOGOUT:
                boolean logoutStatus = mActivity.getPreference().getBoolean(
                        getString(R.string.pref_logout_button_tag), true);
                if(logoutStatus){
                    mActivity.setDisplayView(new MainSignupFragment(), false);
                }else{
                    FetchDataAdapter fetchDataAdapter = new FetchDataAdapter();
                    fetchDataAdapter.delegate = this;
                    try {
                        mActivity.logOutReq(fetchDataAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }

    @Override
    public void initView() {
         ChangePasswordText = rootView
                .findViewById(R.id.settings_text_ChangePassword);
         logoutBtn = (TextView) rootView
                .findViewById(R.id.settings_text_LOGOUT);
         ChangePasswordText.setOnClickListener(this);
         logoutBtn.setOnClickListener(this);
        boolean logoutStatus = mActivity.getPreference().getBoolean(
                getString(R.string.pref_logout_button_tag), true);
        if (logoutStatus /* && !mActivity.checkIfLogin() */) {
            logoutBtn.setText(R.string.LOGIN);

        } else {
            logoutBtn.setText(R.string.LOGOUT);
        }
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();

    }

    @Override
    public void setFontView() {
        MyTypeface.setInfoMenusFont(ChangePasswordText, mActivity);
        MyTypeface.setInfoMenusFont(logoutBtn, mActivity);
    }

    @Override
    public void processFinish(JSONObject response) {
        Log.i("elang","elang response: "+response);
        final Gson gson = new Gson();
        mLogInResponse = gson.fromJson(String.valueOf(response), LogInResponse.class);
        if (mLogInResponse.getStatus()) {
            logoutBtn.setText(R.string.LOGIN);
            mActivity.doLogOut(mLogInResponse);

        }else{
            AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), mLogInResponse.getMessage(), mActivity);
        }

    }
}
