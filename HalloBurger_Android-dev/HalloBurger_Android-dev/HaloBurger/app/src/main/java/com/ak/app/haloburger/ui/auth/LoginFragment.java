package com.ak.app.haloburger.ui.auth;


import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.api.LoginReq;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomEditEmail;
import com.ak.app.haloburger.custom.ui.CustomEditPassword;

import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.JsonConverter;
import com.ak.app.haloburger.util.MyTypeface;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{
    private Main2Activity mActivity;
    private View rootView;
    private CustomEditEmail editEmail;
    private CustomEditPassword editPassword;

    private RequirementFieldAdapter RFAdapter;

    private FetchDataAdapter fetchDataAdapter;

    private LoginReq mLoginReq;
    private LogInResponse mLogInResponse;
    private RelativeLayout btnLogin;

//    LoginButton loginButton;



    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        initCtrl(inflater);
        initView();
        setFontView();

        return rootView;
    }



    private void fetchingData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;

        Log.i("elang","elang login: "+mLoginReq.getLogin());
        Log.i("elang","elang password: "+mLoginReq.getPassword());
//        mLoginReq.setLogin("RINA");
//        mLoginReq.setPassword("12345678");
        String requestObj = JsonConverter.getGson().toJson(mLoginReq);
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        JSONObject obj = new JSONObject(requestObj);

        fetchDataAdapter.requestPOST("/users/sign_in", "/users/sign_in", obj);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_login:
                if(AppHelper.getNetworkAvailable(mActivity)){
                    mActivity.hideSoftKeyboard();

                    try {
                        mLoginReq.setLogin(editEmail.getEditText().getText().toString());
                        mLoginReq.setPassword(editPassword.getEditText().getText().toString());
                        fetchingData();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
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
        mActivity.setPageTitle("LOGIN");
        editEmail = rootView.findViewById(R.id.edit_email);
        editPassword =  rootView
                .findViewById(R.id.edit_password);
        btnLogin = rootView.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        RFAdapter = new RequirementFieldAdapter(btnLogin);
        RFAdapter.addRequirementField(editEmail.getEditText(),
                AppHelper.EDIT_EMAIL);
        RFAdapter.addRequirementField(editPassword.getEditText(),
                AppHelper.EDIT_PASSWORD);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
//        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_login));
        mLoginReq = new LoginReq();
    }

    @Override
    public void setFontView() {


        TextView txtLoginBtn = rootView.findViewById(R.id.txt_login_btn);
        MyTypeface.setButtonFont(txtLoginBtn, mActivity);

    }

    @Override
    public void processFinish(JSONObject response) {

        final Gson gson = new Gson();

        mLogInResponse = gson.fromJson(String.valueOf(response), LogInResponse.class);
        if (mLogInResponse.getStatus()) {

            mActivity.doAuth(mLoginReq.getLogin(), mLogInResponse);
            AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), "Welcome Back", mActivity);
        }else{

            AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), mLogInResponse.getMessage(), mActivity);
        }

    }
}
