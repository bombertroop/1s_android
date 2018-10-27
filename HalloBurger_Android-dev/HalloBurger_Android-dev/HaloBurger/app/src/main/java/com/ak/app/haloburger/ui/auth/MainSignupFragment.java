package com.ak.app.haloburger.ui.auth;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.ui.WebViewFragment;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;


public class MainSignupFragment extends Fragment implements FragmentInitial,  View.OnClickListener{

    private MainActivity mActivity;
    private TextView textPageTitle, btnLoginFB, signuptermstextText, signuptermstextText2, signuptermstextText3, termsText, andText, privacyText, loginoptionTextReturningusers;
    private View rootView;
    private Button  btnSignup, btnLogin;

    public MainSignupFragment() {
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
        rootView=inflater.inflate(R.layout.fragment_main_signup, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginoption_image_login:
                mActivity.setDisplayView(new LoginFragment(), false);
                break;
            case R.id.loginoption_image_facebook:
                mActivity.setDisplayView(new FacebookLoginFragment(), false);
                break;

            case R.id.loginoption_text_privacy:
                mActivity.setDisplayView(new WebViewFragment(getString(R.string.url_privacy)+getString(R.string.appkey),
                        "", "Privacy Policy"), false);
                break;
            case R.id.loginoption_text_terms:
                mActivity.setDisplayView(new WebViewFragment(getString(R.string.url_term_of_use)+getString(R.string.appkey),
                        "", "Terms Of Use"), false);
                break;
        }
    }

    @Override
    public void initView() {
        loginoptionTextReturningusers = rootView.findViewById(R.id.loginoption_text_returningusers);
        signuptermstextText = rootView
                .findViewById(R.id.loginoption_text_signuptermstext);
        signuptermstextText2 = rootView
                .findViewById(R.id.loginoption_text_signuptermstext2);
        signuptermstextText3 = rootView
                .findViewById(R.id.loginoption_text_signuptermstext3);
        termsText = rootView
                .findViewById(R.id.loginoption_text_terms);
        andText = rootView
                .findViewById(R.id.loginoption_text_and);
        privacyText = rootView
                .findViewById(R.id.loginoption_text_privacy);
        btnLoginFB = rootView.findViewById(R.id.loginoption_image_facebook);
        btnSignup = rootView.findViewById(R.id.loginoption_image_signup);
        btnLogin = rootView.findViewById(R.id.loginoption_image_login);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        btnLoginFB.setOnClickListener(this);
        termsText.setOnClickListener(this);
        privacyText.setOnClickListener(this);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_main_sign_up));
    }

    @Override
    public void setFontView() {
        MyTypeface.setButtonFont(btnLoginFB, mActivity);
        MyTypeface.setToFFont(signuptermstextText, mActivity);
        MyTypeface.setToFFont(signuptermstextText2, mActivity);
        MyTypeface.setToFFont(signuptermstextText3, mActivity);
        MyTypeface.setToFFont(andText, mActivity);
        MyTypeface.setToFLinkFont(privacyText, mActivity);
        MyTypeface.setToFLinkFont(termsText, mActivity);
        MyTypeface.setReturningFont(loginoptionTextReturningusers, mActivity);
    }

}
