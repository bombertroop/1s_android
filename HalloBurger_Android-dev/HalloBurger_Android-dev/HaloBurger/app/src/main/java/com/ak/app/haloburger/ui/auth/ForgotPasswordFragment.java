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
import com.ak.app.haloburger.api.ForgotPasswordReq;
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomEditEmail;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.JsonConverter;
import com.ak.app.haloburger.util.ViewHelper;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class ForgotPasswordFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse {

    private MainActivity mActivity;
    private TextView textPageTitle;
    private View rootView;
    private RequirementFieldAdapter RFAdapter;
    private ImageView btnForgotPassword;
    private CustomEditEmail editEmail;

    private ForgotPasswordReq mForgotPasswordReq;
    private LogInResponse mLogInResponse;

    private FetchDataAdapter fetchDataAdapter;

    public ForgotPasswordFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        Tracker mTracker = mActivity.getDefaultTracker();
        mTracker.setScreenName("Forgot Password");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        return rootView;
    }

    private void fetchingData() throws JSONException {
        String requestObj = JsonConverter.getGson().toJson(mForgotPasswordReq);
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        JSONObject obj = new JSONObject(requestObj);

        fetchDataAdapter.requestPOST("/user/forgot_password", "/user/forgot_password",  obj);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgetpassword_image_resetpassword:
                if(AppHelper.getNetworkAvailable(mActivity)) {
                    mActivity.hideSoftKeyboard();
                    String email = editEmail.getEditText().getText().toString();
                    mForgotPasswordReq.setEmail(email);
                    mForgotPasswordReq.setRegister_type(getResources().getString(R.string.register_type));
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
        editEmail = rootView.findViewById(R.id.edit_email);
        btnForgotPassword = rootView.findViewById(R.id.forgetpassword_image_resetpassword);
        btnForgotPassword.setOnClickListener(this);
        RFAdapter = new RequirementFieldAdapter(btnForgotPassword);
        RFAdapter.addRequirementField(editEmail.getEditText(),
                AppHelper.EDIT_EMAIL);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_forgot_password));

        mForgotPasswordReq = new ForgotPasswordReq();
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
