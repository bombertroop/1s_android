package com.ak.app.haloburger.ui.sales;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.adapter.RequirementFieldAdapter;
import com.ak.app.haloburger.api.DeliveryOrderResponse;
import com.ak.app.haloburger.api.ReceiveDoResponse;
import com.ak.app.haloburger.model.EventSales;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class NewSalesFragment extends Fragment implements FragmentInitial, View.OnClickListener {

    private Main2Activity mActivity;
    private EditText editDate;
    private RelativeLayout btnEnter;
    private TextView txtEnterBtn,  txtInstruction;
    private FetchDataAdapter fetchDataAdapter;
    private RequirementFieldAdapter RFAdapter;
    private View rootView;
    private ReceiveDoResponse mReceiveDOResponse;
    private Calendar myCalendar = Calendar.getInstance();
    private DeliveryOrderResponse mDeliveryOrder;
    private String[] name = new String[3];
    private String[] id = new String[3];
    private Spinner dropdown;


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
        rootView = inflater.inflate(R.layout.fragment_new_sales, container, false);

        initCtrl(inflater);
        initView();
        setFontView();
        return rootView;
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
        editDate.setText(sdf.format(myCalendar.getTime()));
        dropdown.setVisibility(View.VISIBLE);
    }


    @Override
    public void initView() {
        mActivity.setPageTitle("ENTER SALES");

        txtEnterBtn = rootView.findViewById(R.id.txt_enter_btn);
        btnEnter = rootView.findViewById(R.id.btn_enter);
        editDate = rootView.findViewById(R.id.edit_date);
        txtInstruction = rootView.findViewById(R.id.txt_instruction);

        btnEnter.setOnClickListener(this);
        editDate.setOnClickListener(this);



        RFAdapter = new RequirementFieldAdapter(btnEnter);
        RFAdapter.addRequirementField(editDate,
                AppHelper.EDIT_COMMON);

        List<EventSales> eventSalesLlist = mActivity.getEventSalesLlist();

        dropdown = rootView.findViewById(R.id.spinner1);
        dropdown.setVisibility(View.GONE);

        name[0] = "Select";
        id[0] = "Select";
        for (int i=0; i < eventSalesLlist.size(); i++){
            id[i+1] = eventSalesLlist.get(i).getName();
            name[i+1] = eventSalesLlist.get(i).getDetail();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_dropdown_item, id);

        dropdown.setAdapter(adapter);

    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity= Main2Activity.getInstance();
    }

    @Override
    public void setFontView() {
        MyTypeface.setInstructionFont(txtInstruction, mActivity);
        MyTypeface.setButtonFont(txtEnterBtn, mActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_date:
                new DatePickerDialog(mActivity, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.btn_enter:
                String eventSelected = dropdown.getSelectedItem().toString();

                mActivity.setDisplayView(new TransactionListFragment().newInstance(eventSelected), true);
                break;
        }
    }
}
