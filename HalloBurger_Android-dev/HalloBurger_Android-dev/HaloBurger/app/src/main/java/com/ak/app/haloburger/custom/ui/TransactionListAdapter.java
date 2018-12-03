package com.ak.app.haloburger.custom.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.Main2Activity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.api.Restaurant;
import com.ak.app.haloburger.model.Product;
import com.ak.app.haloburger.model.Sales;
import com.ak.app.haloburger.util.MyTypeface;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.MyViewHolder> {

    private List<Sales> salesList;

    public TransactionListAdapter(List<Sales> salesList) {
        this.salesList = salesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_list, parent, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);



        Sales sales = salesList.get(position);

        holder.textBarcode.setText(sales.getProduct().getBarcode());
        holder.textProductName.setText(sales.getProduct().getProductName());
        holder.textColor.setText(sales.getProduct().getColor());
        holder.textSize.setText(sales.getProduct().getSize());
        holder.textPrice.setText(kursIndonesia.format(sales.getProduct().getPrice()));
        if (sales.getEventSales()!=null)
            holder.textEvent.setText(sales.getEventSales().getDetail());
        else
            holder.textEvent.setVisibility(View.GONE);
        holder.textQty.setText("1 pc");
        holder.textNet.setText(kursIndonesia.format((sales.getNet())));
    }

    @Override
    public int getItemCount() {
        return salesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textBarcode, textProductName, textColor, textSize, textPrice, textEvent, textQty, textNet;

        public LinearLayout locationLayout;

        public MyViewHolder(View view) {
            super(view);
            textBarcode = (TextView) view.findViewById(R.id.text_barcode);
            textProductName = (TextView) view.findViewById(R.id.text_product);
            textColor =  (TextView) view.findViewById(R.id.text_color);
            textSize =  (TextView) view.findViewById(R.id.text_size);
            textPrice =  (TextView) view.findViewById(R.id.text_price);
            textEvent =  (TextView) view.findViewById(R.id.text_event);
            textQty = (TextView)  view.findViewById(R.id.text_qty);
            textNet =  (TextView) view.findViewById(R.id.text_net);


            textBarcode.setText("");
            textProductName.setText("");
            textColor.setText("");
            textSize.setText("");
            textPrice.setText("");
            textEvent.setText("");
            textQty.setText("");
            textNet.setText("");

            MyTypeface.setTableValueFont(textBarcode, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textProductName, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textColor, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textSize, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textPrice, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textEvent, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textNet, Main2Activity.getInstance());
            MyTypeface.setTableValueFont(textQty, Main2Activity.getInstance());

        }


    }

}
