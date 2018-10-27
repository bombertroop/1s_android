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

public class SnapStartFragment extends Fragment implements FragmentInitial{
    private MainActivity mActivity;
    private View rootView;
    private TextView textPageTitle, orderfood_title, orderfood_text, scancode_title, scancode_text, earnrewards_title, earnrewards_text;

    public SnapStartFragment() {
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
        rootView =inflater.inflate(R.layout.fragment_snap_start, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }

    @Override
    public void initView() {
        orderfood_title = rootView
                .findViewById(R.id.text_orderfood_title);
        orderfood_text = rootView
                .findViewById(R.id.text_orderfood);
        scancode_title = rootView
                .findViewById(R.id.text_scancode_title);
        scancode_text = rootView
                .findViewById(R.id.text_scancode);
        earnrewards_title = rootView
                .findViewById(R.id.text_earnrewards_title);
        earnrewards_text = rootView
                .findViewById(R.id.text_earnrewards);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
        ViewHelper.setPageTitle(textPageTitle, rootView,getResources().getString(R.string.title_snap_start));
    }

    @Override
    public void setFontView() {
        MyTypeface.setSnapStartQFont(earnrewards_title, mActivity);
        MyTypeface.setSnapStartQFont(orderfood_title, mActivity);
        MyTypeface.setSnapStartQFont(scancode_title, mActivity);
        MyTypeface.setSnaptStartAFont(orderfood_text, mActivity);
        MyTypeface.setSnaptStartAFont(scancode_text, mActivity);
        MyTypeface.setSnaptStartAFont(earnrewards_text, mActivity);
    }
}
