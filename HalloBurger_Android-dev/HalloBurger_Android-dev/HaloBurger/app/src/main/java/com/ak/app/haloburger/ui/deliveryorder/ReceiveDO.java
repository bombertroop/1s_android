package com.ak.app.haloburger.ui.deliveryorder;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.activity.ZBarActivity;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.api.ReceiveDoResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomEditEmail;
import com.ak.app.haloburger.custom.ui.CustomEditText;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;


public class ReceiveDO extends Fragment implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{
    private static ReceiveDO mReceiveDO;
    private Main2Activity mActivity;
    private CustomEditText editCode;
    private RelativeLayout btnScan, btnEnter;
    private TextView txtScanBtn, txtEnterBtn, txtOr, txtInstruction;
    private FetchDataAdapter fetchDataAdapter;
    private RequirementFieldAdapter RFAdapter;
    private View rootView;
    private ReceiveDoResponse mReceiveDOResponse;
    private Calendar myCalendar = Calendar.getInstance();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public ReceiveDO() {
        // Required empty public constructor
    }

    public static ReceiveDO getInstance(){
        return mReceiveDO;
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


    public void fetchingData() throws JSONException {
        fetchDataAdapter = new FetchDataAdapter();
        fetchDataAdapter.delegate = this;
        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");
        HashMap<String,String> headers = new HashMap();
        headers.put("Authorization", "Bearer " + authToken);
        Log.i("elang","elang token: "+authToken);


        headers.put("do_number", getCode());
        String serviceUrl = "/shipments/search_do?do_number="+getCode();
        fetchDataAdapter.requestGET(serviceUrl,"shipments", headers);
//        txtInstruction.setText(authToken);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                code = editCode.getEditText().getText().toString();

                try {
                    fetchingData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mActivity.setDisplayView(new ReceiveDO(), true);
                break;
            case R.id.btn_scan:
                Intent intent = new Intent(getActivity(), ZBarActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    public void processFinish(JSONObject response) {

        final Gson gson = new Gson();

        mReceiveDOResponse = gson.fromJson(String.valueOf(response), ReceiveDoResponse.class);
//        mActivity.setDisplayView(new ReceiveDateDialog().newInstance(editCode.getEditText().getText().toString()), true);
        if (mReceiveDOResponse.getStatus()){
            mActivity.setDisplayView(new ReceiveDateDialog().newInstance(mReceiveDOResponse.getDeliveryOrder()), true);
        }else{
            AlertDialogs.showMsgDialog(
                    getResources().getString(R.string.constant_title_message),
                    mReceiveDOResponse.getMessage(), mActivity);

        }


    }

    @Override
    public void initView() {
        mActivity.setPageTitle("SCAN DELIVERY ORDER");
        txtScanBtn = rootView.findViewById(R.id.txt_scan_btn);
        txtEnterBtn = rootView.findViewById(R.id.txt_enter_btn);
        txtOr = rootView.findViewById(R.id.txt_or);
        btnEnter = rootView.findViewById(R.id.btn_enter);
        btnScan = rootView.findViewById(R.id.btn_scan);
        editCode = rootView.findViewById(R.id.edit_code);
        txtInstruction = rootView.findViewById(R.id.txt_instruction);
        editCode.getEditText().setHint("DO number");
        btnEnter.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        RFAdapter = new RequirementFieldAdapter(btnEnter);
        RFAdapter.addRequirementField(editCode.getEditText(),
                AppHelper.EDIT_COMMON);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
        mReceiveDO = this;
    }

    @Override
    public void setFontView() {
        MyTypeface.setButtonFont(txtScanBtn, mActivity);
        MyTypeface.setButtonFont(txtEnterBtn, mActivity);
        MyTypeface.setOrFont(txtOr, mActivity);
        MyTypeface.setInstructionFont(txtInstruction, mActivity);

    }
}
