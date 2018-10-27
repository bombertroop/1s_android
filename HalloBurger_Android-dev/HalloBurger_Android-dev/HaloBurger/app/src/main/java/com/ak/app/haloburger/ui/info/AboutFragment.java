package com.ak.app.haloburger.ui.info;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.ViewHelper;

public class AboutFragment extends Fragment implements FragmentInitial{

    private MainActivity mActivity;
    private TextView textPageTitle, q1Text, a1Text;
    private View rootView;
    public AboutFragment() {
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
        rootView =inflater.inflate(R.layout.fragment_about, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }


    @Override
    public void initView() {
        q1Text = rootView
                .findViewById(R.id.aboutus_text_line1);
        a1Text = rootView
                .findViewById(R.id.aboutus_text_line2);

    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_about_us));
    }

    @Override
    public void setFontView() {
        MyTypeface.setAbutUsQFont(q1Text, mActivity);
        MyTypeface.setAbutUsAFont(a1Text, mActivity);
    }
}
