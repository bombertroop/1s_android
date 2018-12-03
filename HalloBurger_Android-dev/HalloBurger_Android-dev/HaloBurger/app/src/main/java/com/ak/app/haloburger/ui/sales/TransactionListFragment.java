package com.ak.app.haloburger.ui.sales;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.activity.ZBarActivity;
import com.ak.app.haloburger.activity.ZBarActivity2;
import com.ak.app.haloburger.adapter.FragmentInitial;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomEditText;
import com.ak.app.haloburger.custom.ui.TransactionListAdapter;
import com.ak.app.haloburger.model.EventSales;
import com.ak.app.haloburger.model.Product;
import com.ak.app.haloburger.model.Sales;
import com.ak.app.haloburger.ui.HomeFragment;
import com.ak.app.haloburger.util.MyTypeface;

import org.json.JSONException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TransactionListFragment extends Fragment implements FragmentInitial, View.OnClickListener {

    private Main2Activity mActivity;
    private View rootView;
    private static TransactionListFragment mTransactionListFragment;
    private RelativeLayout btnEnter, btnScan, btnCreate;
    private CustomEditText editCode;
    public List<Sales> salesList = new ArrayList<>();
    private TransactionListAdapter mAdapter;
    private RecyclerView recyclerView;
    private EventSales eventSelected;
    private String mSelectedEvent;
    private static final String SELECTED_EVENT = "SELECTED_EVENT";
    private TextView txtCreateBtn, txtScanBtn, txtEnterBtn;
    private float total = 0;

    public TransactionListFragment() {
        // Required empty public constructor
    }

    public static TransactionListFragment getInstance(){
        return mTransactionListFragment;
    }

    public static TransactionListFragment newInstance(String selectedItem) {
        TransactionListFragment fragment = new TransactionListFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_EVENT, selectedItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectedEvent = getArguments().getString(SELECTED_EVENT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_transaction_list, container, false);
        initCtrl(inflater);
        initView();
        setFontView();


        mAdapter = new TransactionListAdapter(salesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

//        mAdapter.notifyDataSetChanged();
        return rootView;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_enter:
                addItem(editCode.getEditText().getText().toString());
                break;
            case R.id.btn_scan:
                Intent intent = new Intent(getActivity(), ZBarActivity2.class);
                startActivityForResult(intent,2);
                break;
            case R.id.btn_create:

                AlertDialog show = new AlertDialog.Builder(mActivity)
                        .setTitle(getResources().getString(R.string.constant_title_message))
                        .setMessage("Once you create transaction, you'll not be able to change it\n" +
                                "Are you sure?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                AlertDialog show = new AlertDialog.Builder(mActivity)
                                        .setTitle(getResources().getString(R.string.constant_title_message))
                                        .setMessage("Your Transaction has been created, thank you.")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                mActivity.setDisplayView(new HomeFragment(), false);
                                            }
                                        })
                                        .setNegativeButton(android.R.string.no, null).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();



                break;
        }
    }

    @Override
    public void initView() {
        btnEnter = rootView.findViewById(R.id.btn_enter);
        btnScan = rootView.findViewById(R.id.btn_scan);
        editCode = rootView.findViewById(R.id.edit_code);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        txtCreateBtn = rootView.findViewById(R.id.txt_create_btn);
        txtScanBtn= rootView.findViewById(R.id.txt_scan_btn);
        txtEnterBtn= rootView.findViewById(R.id.txt_enter_btn);
        btnCreate = rootView.findViewById(R.id.btn_create);
        btnScan.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
    }

    @Override
    public void initCtrl(LayoutInflater inflater) {
        mActivity = Main2Activity.getInstance();
        mTransactionListFragment = this;
    }

    @Override
    public void setFontView() {
        MyTypeface.setButtonFont(txtScanBtn, mActivity);
        MyTypeface.setButtonFont(txtEnterBtn, mActivity);
        MyTypeface.setButtonFont(txtCreateBtn, mActivity);
    }

    private Product getProduct(String barcode){
        Product result = null;

        for (int i = 0; i<mActivity.getProductList().size(); i++){
            Product pTamp = mActivity.getProductList().get(i);
            if(barcode.equals(pTamp.getBarcode())){
                result = pTamp;
            }

        }
        return result;
    }


    private EventSales getEventSelected(String mSelectedEvent){

        EventSales result = null;

        for (int i = 0; i<mActivity.getEventSalesLlist().size(); i++){
            EventSales pTamp = mActivity.getEventSalesLlist().get(i);
            if(mSelectedEvent.equals(pTamp.getName())){
                result = pTamp;
            }

        }
        return result;
    }

    private float discCalc(float disc, float price){
        return price - (disc * price);
    }

    private float netCacl(int qty, float price){
        return qty * price;
    }

    public void addItem(String barcode){
        Product p = getProduct(barcode);
        Sales s = null;
        float currentDisc = 0;

        if (p != null){
            int position = 0;
            s = new Sales();
            s.setProduct(p);
            EventSales eS = getEventSelected(mSelectedEvent);

            if (eS!=null){
                Log.i("elang","elang "+eS.getDisc());
                s.setEventSales(eS);
                currentDisc = eS.getDisc();
            }

            float cPrice = discCalc(currentDisc, p.getPrice());
            float cNet = netCacl(1,cPrice);
            total = total + cNet;
            s.setQty(1);
            s.setNet(cNet);
            // Add an item to animals list
            salesList.add(s);
            mAdapter.notifyItemInserted(position);
            recyclerView.scrollToPosition(position);
            Toast.makeText(mActivity,"Added", Toast.LENGTH_SHORT).show();

            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("Rp. ");
            formatRp.setMonetaryDecimalSeparator(',');
            formatRp.setGroupingSeparator('.');

            kursIndonesia.setDecimalFormatSymbols(formatRp);


            txtCreateBtn.setText(kursIndonesia.format(total));




        } else {
            AlertDialogs.showMsgDialog(
                    getResources().getString(R.string.constant_title_message),
                    "Product is not available", mActivity);
        }
//        Log.i("elang","elang result p: "+p.getBarcode());
//        salesList
    }

    public String formatDecimal(float number) {
        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number); // sdb
        } else {
            return String.format("%10.2f", number); // dj_segfault
        }
    }
}
