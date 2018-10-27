package com.ak.app.haloburger.ui.auth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.api.ChangePasswordReq;
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomEditPassword;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.JsonConverter;
import com.ak.app.haloburger.util.ViewHelper;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private MainActivity mActivity;
    private TextView textPageTitle;
    private View rootView;
    private RequirementFieldAdapter RFAdapter;
    private ImageView btnSubmitPassword;
    private CustomEditPassword editOldPassword, editNewPassword, editRepeatPassword;

    private ChangePasswordReq mChangePasswordReq;
    private LogInResponse mLogInResponse;

    private FetchDataAdapter fetchDataAdapter;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    private void fetchingData() throws JSONException {
        String requestObj = JsonConverter.getGson().toJson(mChangePasswordReq);
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        JSONObject obj = new JSONObject(requestObj);

        fetchDataAdapter.requestPOST("/user/update_password", "/user/update_password",  obj);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_change_password, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("Reset Password");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_password:
                if(AppHelper.getNetworkAvailable(mActivity)){
                    mActivity.hideSoftKeyboard();
                    String oldpwd = editOldPassword.getEditText().getText().toString();
                    String newpwd = editNewPassword.getEditText().getText().toString();
                    String repwd = editRepeatPassword.getEditText().getText().toString();

                    String authToken = mActivity.getPreference().getString(
                            getString(R.string.pref_auth_token), "");

                    mChangePasswordReq.setAuth_token(authToken);
                    mChangePasswordReq.setPassword(newpwd);
                    mChangePasswordReq.setCurrent_password(oldpwd);
                    mChangePasswordReq.setPassword_confirmation(repwd);

                    try {
                        fetchingData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    AlertDialogs.showMsgDialog(
                            getResources().getString(R.string.constant_title_message),
                            mActivity
                                    .getString(R.string.error_network_services), mActivity);
                }
                break;
        }

    }

    @Override
    public void initView() {
        editNewPassword = rootView.findViewById(R.id.edit_new_password);
        editOldPassword = rootView.findViewById(R.id.edit_old_password);
        editRepeatPassword = rootView.findViewById(R.id.edit_repeat_password);
        btnSubmitPassword = rootView.findViewById(R.id.submit_password);
        btnSubmitPassword.setOnClickListener(this);

        editNewPassword.getEditText().setHint("New Password");
        editOldPassword.getEditText().setHint("Old Password");
        editRepeatPassword.getEditText().setHint("Repeat Password");
        RFAdapter = new RequirementFieldAdapter(btnSubmitPassword);
        RFAdapter.addRequirementField(editNewPassword.getEditText(),
                AppHelper.EDIT_PASSWORD);
        RFAdapter.addRequirementField(editOldPassword.getEditText(),
                AppHelper.EDIT_PASSWORD);
        RFAdapter.addRequirementField(editRepeatPassword.getEditText(),
                AppHelper.EDIT_PASSWORD);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_change_password));
        mChangePasswordReq = new ChangePasswordReq();
    }

    @Override
    public void setFontView() {

    }

    @Override
    public void processFinish(JSONObject response) {

        final Gson gson = new Gson();

        mLogInResponse = gson.fromJson(String.valueOf(response), LogInResponse.class);


//        AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), mLogInResponse.getNotice(), mActivity);


        mActivity.getSupportFragmentManager().popBackStack();

    }
}
