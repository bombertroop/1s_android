package com.ak.app.haloburger.ui.auth;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.util.CustomDatePicker;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;

public class FacebookLoginFragment extends Fragment implements FragmentInitial, View.OnClickListener {

    private MainActivity mActivity;
    private View rootView;
    private TextView textPageTitle, btnLoginFB, textDob;
    private CustomDatePicker mDatePicker;

    RelativeLayout datepickerclick;

    public FacebookLoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_facebook_login, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginoption_fb_image_facebook:
                Log.i("elang", "elang dob clicked");
                break;
            case R.id.layoutdob:
                Log.i("elang", "elang dob clicked");
                mDatePicker.showDatePickerDialog(textDob);
                break;
        }
    }

    @Override
    public void initView() {
        textDob = rootView.findViewById(R.id.textviewdob);
        btnLoginFB = rootView.findViewById(R.id.loginoption_fb_image_facebook);
        btnLoginFB.setOnClickListener(this);
        datepickerclick = rootView
                .findViewById(R.id.layoutdob);
        datepickerclick.setOnClickListener(this);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_login));
        mDatePicker = new CustomDatePicker(mActivity);
    }

    @Override
    public void setFontView() {
        MyTypeface.setButtonFont(btnLoginFB, mActivity);
        MyTypeface.setFieldFont(textDob, mActivity);
    }
}
