package com.ak.app.haloburger.ui.deliveryorder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.api.DeliveryOrderResponse;
import com.ak.app.haloburger.api.ReceiveDoResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomEditText;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class ReceiveDateDialog extends Fragment  implements FragmentInitial, View.OnClickListener, FetchDataAdapter.AsyncResponse{


    private String mParamDoNumber;
    private String mParamId;
    private static final String DO_NUMBER = "DO_NUMBER";
    private static final String ID = "ID";

    private Main2Activity mActivity;
    private EditText editCode;
    private RelativeLayout  btnEnter;
    private TextView  txtEnterBtn,  txtInstruction;
    private FetchDataAdapter fetchDataAdapter;
    private RequirementFieldAdapter RFAdapter;
    private View rootView;
    private ReceiveDoResponse mReceiveDOResponse;
    private Calendar myCalendar = Calendar.getInstance();
    private DeliveryOrderResponse mDeliveryOrder;


    public ReceiveDateDialog(){}

    public static ReceiveDateDialog newInstance(DeliveryOrderResponse mDeliveryOrder) {
        ReceiveDateDialog fragment = new ReceiveDateDialog();
        Bundle args = new Bundle();



        args.putString(DO_NUMBER, mDeliveryOrder.getDelivaryOrderNumber());
        args.putString(ID, mDeliveryOrder.getId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamDoNumber = getArguments().getString(DO_NUMBER);
            mParamId= getArguments().getString(ID);


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_receive_date_dialog, container, false);
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


        String receiveDate  = editCode.getText().toString();
        Log.i("elang","elang receiveDate"+receiveDate);

        String serviceUrl = "/shipments/"+mParamId+"/receive?receive_date="+receiveDate;
        fetchDataAdapter.requestGET(serviceUrl,"shipments", headers);
//        txtInstruction.setText(authToken);
    }



    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        editCode.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_code:
                new DatePickerDialog(mActivity, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_enter:

                AlertDialog show = new AlertDialog.Builder(mActivity)
                        .setTitle(getResources().getString(R.string.constant_title_message))
                        .setMessage("Be careful! This cannot be canceled")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                try {
                                    fetchingData();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();



                break;
        }
    }

    @Override
    public void processFinish(JSONObject response) {
        final Gson gson = new Gson();

        mReceiveDOResponse = gson.fromJson(String.valueOf(response), ReceiveDoResponse.class);

        AlertDialogs.showMsgDialog(
                    getResources().getString(R.string.constant_title_message),
                    mReceiveDOResponse.getMessage(), mActivity);

        if(mReceiveDOResponse.getStatus()){
            mActivity.getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void initView() {
        mActivity.setPageTitle("SCAN DELIVERY ORDER");

        txtEnterBtn = rootView.findViewById(R.id.txt_enter_btn);
        btnEnter = rootView.findViewById(R.id.btn_enter);
        editCode = rootView.findViewById(R.id.edit_code);
        txtInstruction = rootView.findViewById(R.id.txt_instruction);
        editCode.setHint("Receive Date");
        txtInstruction.setText("Receive "+mParamDoNumber);


        btnEnter.setOnClickListener(this);
        editCode.setOnClickListener(this);



        RFAdapter = new RequirementFieldAdapter(btnEnter);
        RFAdapter.addRequirementField(editCode,
                AppHelper.EDIT_COMMON);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
    }

    @Override
    public void setFontView() {
        MyTypeface.setInstructionFont(txtInstruction, mActivity);
        MyTypeface.setButtonFont(txtEnterBtn, mActivity);
    }
}
