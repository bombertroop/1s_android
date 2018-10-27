package com.ak.app.haloburger.ui;


import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.ui.auth.LoginFragment;
import com.ak.app.haloburger.ui.auth.MainSignupFragment;
import com.ak.app.haloburger.ui.deliveryorder.ReceiveDO;
import com.ak.app.haloburger.ui.deliveryorder.ReceivedDOList;
import com.ak.app.haloburger.ui.info.LocationFragment;
import com.ak.app.haloburger.ui.stockmutation.ReceiveSM;
import com.ak.app.haloburger.util.MyTypeface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class HomeFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private Main2Activity mActivity;
    private TextView textDo, textStockMutaion, textLogout, textDoList;
    private RelativeLayout btnDo, btnStockMutation, btnLogOut, btnDoList;
    private View rootView;


    public HomeFragment getInstance() {
        return instance;
    }

    public void setInstance(HomeFragment instance) {
        this.instance = instance;
    }

    private HomeFragment instance;



    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Main2Activity.getInstance().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initCtrl(inflater);
        initView();
        setFontView();


        return rootView;
    }

    @Override
    public void initView() {
        mActivity.setPageTitle("HOME");
        textDo = rootView.findViewById(R.id.text_do_btn);
        textStockMutaion = rootView.findViewById(R.id.text_stock_mutation_button);
        textLogout = rootView.findViewById(R.id.text_logout_button);
        textDoList = rootView.findViewById(R.id.text_do_list_btn);

        btnDo =  rootView
                .findViewById(R.id.btn_do);
        btnStockMutation =  rootView
                .findViewById(R.id.btn_stock_mutation);
        btnLogOut = rootView.findViewById(R.id.btn_logout);
        btnDoList = rootView.findViewById(R.id.btn_do_list);


        boolean logoutStatus = mActivity.getPreference().getBoolean(
                getString(R.string.pref_logout_button_tag), true);

        if (logoutStatus /* && !mActivity.checkIfLogin() */) {
            textLogout.setText(R.string.LOGIN);

        } else {
            textLogout.setText(R.string.LOGOUT);
        }


        btnDo.setOnClickListener(this);
        btnStockMutation.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnDoList.setOnClickListener(this);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
        instance = this;
    }

    @Override
    public void setFontView() {
        MyTypeface.setButtonFont(textDo, mActivity);
        MyTypeface.setButtonFont(textStockMutaion, mActivity);
        MyTypeface.setButtonFont(textLogout, mActivity);
        MyTypeface.setButtonFont(textDoList, mActivity);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_do:
                mActivity.setDisplayView(new ReceiveDO(), true);
                break;
            case R.id.btn_stock_mutation:
                mActivity.setDisplayView(new ReceiveSM(), true);
                break;
            case R.id.btn_do_list:
                mActivity.setDisplayView(new ReceivedDOList(), true);
                break;
            case R.id.btn_logout:
                Log.i("elang","elang trace 1");
                boolean logoutStatus = mActivity.getPreference().getBoolean(
                        getString(R.string.pref_logout_button_tag), true);
                if(logoutStatus){
                    mActivity.setDisplayView(new LoginFragment(), false);
                }else{

                    Log.i("elang","elang trace 2");
                    SharedPreferences.Editor prefsEditor = mActivity.getPreferenceEditor();
                    prefsEditor.putString(getString(R.string.pref_login_id), "");
                    prefsEditor.putBoolean(getString(R.string.pref_logout_button_tag), true);
                    prefsEditor.putString(getString(R.string.pref_auth_token), "");
                    prefsEditor.putString(getString(R.string.pref_customer_id), "");
                    prefsEditor.commit();
                    textLogout.setText(R.string.LOGIN);
                }
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void processFinish(JSONObject response) {
        final Gson gson = new Gson();
        LogInResponse mLogInResponse = gson.fromJson(String.valueOf(response), LogInResponse.class);
        if (mLogInResponse.getStatus()) {
//            mActivity.doLogOut(mLogInResponse);
            AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), "Welcome Back!", mActivity);
        }else{
            AlertDialogs.showMsgDialog(getResources().getString(R.string.constant_title_message), mLogInResponse.getMessage(), mActivity);
        }
    }
}
