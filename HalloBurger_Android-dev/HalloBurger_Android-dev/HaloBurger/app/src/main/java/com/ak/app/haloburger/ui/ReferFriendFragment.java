package com.ak.app.haloburger.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.api.ReferFriendResponse;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;


public class ReferFriendFragment extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{

    private MainActivity mActivity;
    private TextView textPageTitle,tellyourfriendsText;
    private View rootView;
    private ImageView shareTwitter, shareSocial, shareMail;
    private FetchDataAdapter fetchDataAdapter;
    private ReferFriendResponse mReferFriendResponse;
    private String share = "";


    public ReferFriendFragment() {
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


        fetchDataAdapter.requestGET("/referral/email", "/referral/email",  headers);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_refer_friend, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }


    @Override
    public void initView() {
        shareTwitter = rootView
                .findViewById(R.id.shareTwitter);
        shareSocial = rootView
                .findViewById(R.id.shareSocial);
        shareMail = rootView
                .findViewById(R.id.shareMail);
        tellyourfriendsText = rootView.findViewById(R.id.tellyourfriendsText);
        shareTwitter.setOnClickListener(this);
        shareSocial.setOnClickListener(this);
        shareMail.setOnClickListener(this);

    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_refer_friend));
    }

    @Override
    public void setFontView() {
        MyTypeface.setInstructionFont(tellyourfriendsText, mActivity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shareMail:
                share = "shareMail";
                try {
                    fetchingData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.shareSocial:
                share = "shareSocial";
                break;
            case R.id.shareTwitter:
                share = "shareTwitter";
                break;
        }

    }

    @Override
    public void processFinish(JSONObject response) {
        final Gson gson = new Gson();

        mReferFriendResponse = gson.fromJson(String.valueOf(response), ReferFriendResponse.class);

        if(mReferFriendResponse.getStatus()){
            if(share.equals("shareMail")){
                AppHelper.onContactUsCreate("", mReferFriendResponse.getEmailTitle(),mReferFriendResponse.getEmailBody(), mActivity);
            }else if(share.equals("shareSocial")){
                AppHelper.shareMessage(mReferFriendResponse.getOtherMediaText(), mActivity);
            } else{
                Intent tweetIntent = new Intent(Intent.ACTION_SEND);
                tweetIntent.putExtra(Intent.EXTRA_TEXT, mReferFriendResponse.getTwitterText());
                tweetIntent.setType("text/plain");

                PackageManager packManager = MainActivity.getInstance().getPackageManager();
                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

                boolean resolved = false;
                for (ResolveInfo resolveInfo : resolvedInfoList) {
                    if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                        tweetIntent.setClassName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name);
                        resolved = true;
                        break;
                    }
                }
                if (resolved) {
                    startActivity(tweetIntent);
                } else {
                    Intent i = new Intent();
                    i.putExtra(Intent.EXTRA_TEXT, mReferFriendResponse.getTwitterText());
                    i.setAction(Intent.ACTION_VIEW);
                    String twitterText = mReferFriendResponse.getTwitterText();
                    try {
                        twitterText = URLEncoder.encode(twitterText, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                    i.setData(Uri.parse("https://twitter.com/intent/tweet?text=" + twitterText));
                    startActivity(i);
                    Toast.makeText(MainActivity.getInstance(), "Twitter app isn't found", Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
