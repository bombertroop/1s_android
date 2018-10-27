package com.ak.app.haloburger.ui;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomProgressDialog;
import com.ak.app.haloburger.model.DeviceBean;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

@SuppressLint("ValidFragment")
public class WebViewFragment extends Fragment implements FragmentInitial, View.OnClickListener {
    Main2Activity mActivity;
    private TextView textContactUs;
    private WebView webView;
    private String loadUrl;
    private TextView faqContact;
    private LinearLayout layoutContactUs;
    private RelativeLayout  layoutWebControl;
    private View rootView;
    private String mTitle;
    private String webviewScreenName = "";
    private String mURL;
    private ImageView btnBack, btnForward, btnRefresh;



    public WebViewFragment(){

    }

    @SuppressLint("ValidFragment")
    public WebViewFragment(String URL, String title, String webviewScreenName) {
        this.mURL = URL;
        this.mTitle = title;
        this.webviewScreenName = webviewScreenName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        loadUrl = "";
        initCtrl(inflater);
        initView();
        checkView();
        setFontView();
        refreshView();
        return rootView;
    }

    private void checkView() {

        webView.setVisibility(View.INVISIBLE);


        if (!webviewScreenName.equals("faq")) {

            layoutContactUs.setVisibility(View.GONE);
        } else {

            layoutWebControl.setVisibility(View.GONE);
        }

        if (webviewScreenName.equals("Terms Of Use")
                || webviewScreenName.equals("Privacy Policy")) {
            layoutWebControl.setVisibility(View.GONE);

            layoutContactUs.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshView();
    }
    private void refreshView() {

        if (AppHelper.getNetworkAvailable(getActivity())) {
            webView.loadUrl(mURL);
            webView.setWebViewClient(new Callback());

        } else {
            AlertDialogs
                    .showMsgDialog(getResources().getString(R.string.constant_title_message), mActivity
                                    .getString(R.string.error_network_services),
                            mActivity);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.faqEmailLayout:
//                String messages = "";
//                String email = mActivity.getPreference().getString( getString(R.string.pref_login_id), "");
//                if (!email.equals(""))
//                    messages = new DeviceBean(mActivity).getDeviceName();
//                AppHelper.onContactUsCreate(getString(R.string.email_faq_contact_us), getString(R.string.email_subject_faq),messages, mActivity);
                break;
            case R.id.btn_back:
                webView.goBack();
                break;
            case R.id.btn_forward:
                webView.goForward();
                break;
            case R.id.btn_refresh:
                webView.reload();
                break;

        }
    }

    @Override
    public void initView() {
        faqContact =  rootView
                .findViewById(R.id.faqContact);
        layoutContactUs = rootView
                .findViewById(R.id.faqEmailLayout);

        webView = (WebView) rootView.findViewById(R.id.webview);


        btnBack = (ImageView) rootView.findViewById(R.id.btn_back);
        btnForward = (ImageView) rootView.findViewById(R.id.btn_forward);
        btnRefresh = (ImageView) rootView.findViewById(R.id.btn_refresh);
        layoutWebControl = (RelativeLayout) rootView
                .findViewById(R.id.layout_web_control);


        layoutContactUs.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();

    }

    @Override
    public void setFontView() {

    }

    private void enableDisableNavigationButtons(WebView mWebView) {
        // if has previous page, enable the back button
        if (mWebView.canGoBack()) {
            btnBack.setImageResource(R.drawable.social_nav_back_active);
            btnBack.setEnabled(true);
        } else {
            btnBack.setImageResource(R.drawable.social_nav_back_idle);
            btnBack.setEnabled(false);
        }
        // if has next page, enable the next button
        if (mWebView.canGoForward()) {
            btnForward.setImageResource(R.drawable.social_nav_forward_active);
            btnForward.setEnabled(true);
        } else {
            btnForward.setImageResource(R.drawable.social_nav_forward_idle);
            btnForward.setEnabled(false);
        }
    }

    public class Callback extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            try {
                mActivity.progressDialog.show();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        public void onPageFinished(WebView view, final String url) {
            super.onPageFinished(view, url);
            Log.e("URL", url);
            enableDisableNavigationButtons(view);
            webView.setVisibility(View.VISIBLE);
            mActivity.progressDialog.dismiss();


        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            mActivity.progressDialog.dismiss();
        }
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            mActivity.progressDialog.dismiss();
        }
    }

}
