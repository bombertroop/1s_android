package com.ak.app.haloburger.ui.stockmutation;

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

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.custom.ui.CustomEditText;
import com.ak.app.haloburger.ui.deliveryorder.ReceiveDO;
import com.ak.app.haloburger.ui.info.LocationFragment;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class ReceiveSM extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{
    private Main2Activity mActivity;
    private CustomEditText editCode;
    private RelativeLayout btnScan, btnEnter;
    private TextView txtScanBtn, txtEnterBtn, txtOr, txtInstruction;
    private FetchDataAdapter fetchDataAdapter;
    private RequirementFieldAdapter RFAdapter;
    private View rootView;

    public ReceiveSM() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_receive_do, container, false);
        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
    }


    private void fetchingData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("Authorization", "Bearer " + authToken);
        String code = editCode.getEditText().getText().toString();
        headers.put("mutation_number", code);
        String serviceUrl = "/stock_mutations/search";
        fetchDataAdapter.requestGET(serviceUrl,"stock_mutations", headers);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_enter:
                try {
                    fetchingData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mActivity.setDisplayView(new ReceiveDO(), true);
                break;
        }

    }

    @Override
    public void processFinish(JSONObject response) {
        Log.i("elang","elang response: "+response);
    }

    @Override
    public void initView() {
        mActivity.setPageTitle("SCAN STOCK MUTATION");
        txtScanBtn = rootView.findViewById(R.id.txt_scan_btn);
        txtEnterBtn = rootView.findViewById(R.id.txt_enter_btn);
        txtOr = rootView.findViewById(R.id.txt_or);
        btnEnter = rootView.findViewById(R.id.btn_enter);
        btnScan = rootView.findViewById(R.id.btn_scan);
        editCode = rootView.findViewById(R.id.edit_code);
        txtInstruction = rootView.findViewById(R.id.txt_instruction);
        editCode.getEditText().setHint("SM number");
        txtInstruction.setText("Enter SM number or scan barcode to receive SM");
        btnEnter.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        RFAdapter = new RequirementFieldAdapter(btnEnter);
        RFAdapter.addRequirementField(editCode.getEditText(),
                AppHelper.EDIT_COMMON);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
    }

    @Override
    public void setFontView() {
        MyTypeface.setButtonFont(txtScanBtn, mActivity);
        MyTypeface.setButtonFont(txtEnterBtn, mActivity);
        MyTypeface.setOrFont(txtOr, mActivity);
        MyTypeface.setInstructionFont(txtInstruction, mActivity);

    }
}
