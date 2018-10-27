package com.ak.app.haloburger.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.api.Activity;
import com.ak.app.haloburger.api.Reward;
import com.ak.app.haloburger.util.MyTypeface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by el on 06/06/18.
 */

public class RedeemedActivityAdapter extends RecyclerView.Adapter<RedeemedActivityAdapter.MyViewHolder> {

    private List<Activity> activities;

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }

    private Integer totalPoint = 0;

    public RedeemedActivityAdapter(List<Activity> activities) {
        this.activities = activities;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView, dateTextView;

        public MyViewHolder(View view) {
            super(view);


            titleTextView = (TextView) view
                    .findViewById(R.id.viewactivity_list_text_date);
            dateTextView = (TextView) view
                    .findViewById(R.id.viewactivity_list_text_points);
            titleTextView.setText("");
            dateTextView.setText("");
        }
    }

    @Override
    public RedeemedActivityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewactivity_list, parent, false);

        return new RedeemedActivityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RedeemedActivityAdapter.MyViewHolder holder, int position) {
        Activity mActivity = activities.get(position);



        SimpleDateFormat curFormater = new SimpleDateFormat(
                "yyyy-MM-dd");
        try {
            Date dateObj = curFormater.parse(mActivity.getClaimDate());
            SimpleDateFormat postFormater = new SimpleDateFormat(
                    "MMM dd, yyyy");
            String newDateStr = postFormater
                    .format(dateObj);

            holder.titleTextView.setText(newDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.dateTextView.setText( mActivity.getReward().getName());
        setTotalPoint(getTotalPoint()+mActivity.getReward().getPoints());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }
}
