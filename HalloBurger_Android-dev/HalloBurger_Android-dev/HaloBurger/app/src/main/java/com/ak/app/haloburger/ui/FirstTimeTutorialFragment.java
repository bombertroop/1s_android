package com.ak.app.haloburger.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.util.MyTypeface;

public class FirstTimeTutorialFragment extends Fragment implements FragmentInitial, View.OnClickListener{

    private View rootView;
    private MainActivity mActivity;
    private TextView earn_point_guide;
    private ImageView buttonVerify;

    public FirstTimeTutorialFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_first_time_tutorial, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_verify:
                mActivity.navigation.setSelectedItemId(R.id.navigation_earn);
                break;
        }
    }

    @Override
    public void initView() {
        earn_point_guide = rootView
                .findViewById(R.id.earn_point_guide);
        buttonVerify = rootView.findViewById(R.id.button_verify);
        buttonVerify.setOnClickListener(this);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = MainActivity.getInstance();
    }

    @Override
    public void setFontView() {
        MyTypeface.setDescTutorialFont(earn_point_guide, mActivity);
    }
}
