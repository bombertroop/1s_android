package com.ak.app.haloburger.custom.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;

import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.api.DeliveryOrder;
import com.ak.app.haloburger.api.DoListResponse;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DOListViewAdapter extends RecyclerView.Adapter<DOListViewAdapter.MyViewHolder>  {

    private List<DeliveryOrder> doList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textDoNumberTitle, textDeleveryDate, textReceivedDate, textQty;

        public LinearLayout locationLayout;

        public MyViewHolder(View view) {
            super(view);
            textDoNumberTitle = (TextView) view.findViewById(R.id.text_do_number_value);
            textDeleveryDate = (TextView) view.findViewById(R.id.text_delevery_date);
            textReceivedDate =  (TextView) view.findViewById(R.id.text_received_date);
            textQty = (TextView)  view.findViewById(R.id.text_qty);

            textDoNumberTitle.setText("");
            textDeleveryDate.setText("");
            textReceivedDate.setText("");
            textQty.setText("");

            MyTypeface.setTableValueFont(textDoNumberTitle, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textDeleveryDate, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textReceivedDate, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textQty, Main2Activity.getInstance());

        }


    }

    public DOListViewAdapter(List<DeliveryOrder> doList) {
        this.doList = doList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_do_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DeliveryOrder mDeliveryOrder = doList.get(position);
        holder.textDoNumberTitle.setText(mDeliveryOrder.getDeliveryOrderNumber());
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");


            holder.textDeleveryDate.setText(remakeDate(mDeliveryOrder.getDeliveryDate()));
            holder.textReceivedDate.setText(remakeDate(mDeliveryOrder.getReceivedDate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.textQty.setText(mDeliveryOrder.getQuantity().toString());



    }

    private String remakeDate(String dateString){
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd MMM yyyy");
        String inputDateStr=dateString;
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormat.format(date);
    }

    @Override
    public int getItemCount() {
        return doList.size();
    }


}
