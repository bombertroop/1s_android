package com.ak.app.haloburger.ui.sales;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ak.app.haloburger.activity.R;


public class NewSalesFragment extends Fragment {


    public NewSalesFragment() {
        // Required empty public constructor
    }

    public static NewSalesFragment newInstance(String param1, String param2) {
        NewSalesFragment fragment = new NewSalesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_sales, container, false);
    }


}
