package com.ak.app.haloburger.ui.sales;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.app.haloburger.activity.R;

public class ScanProductFragment extends Fragment {


    public ScanProductFragment() {
        // Required empty public constructor
    }


    public static ScanProductFragment newInstance(String param1, String param2) {
        ScanProductFragment fragment = new ScanProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan_product, container, false);
    }



}
