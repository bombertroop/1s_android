package com.ak.app.haloburger.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.api.Reward;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.ui.auth.MainSignupFragment;
import com.ak.app.haloburger.ui.reward.RedeemRewardFragment;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;


import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by el on 05/06/18.
 */

public class RewardViewAdapter extends RecyclerView.Adapter<RewardViewAdapter.MyViewHolder> {

    private List<Reward> rewards;
    private Integer balancePoint;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView, dateTextView, dateText;
        public LinearLayout cellParent, cellPoint;

        public MyViewHolder(View view) {
            super(view);
            titleTextView = (TextView) view.findViewById(R.id.enjoy_mygodie_reward_list_text_title);
            dateTextView = (TextView) view
                    .findViewById(R.id.enjoy_mygodie_reward_list_text_date);
            dateText = (TextView) view
                    .findViewById(R.id.enjoy_mygodie_reward_list_date);
            cellParent = (LinearLayout) view
                    .findViewById(R.id.enjoy_mygodie_reward_list_parent_reward);
            cellPoint = (LinearLayout) cellParent
                    .findViewById(R.id.enjoy_mygodie_reward_list_points);



            MyTypeface.setRewardTitleFont(titleTextView, MainActivity.getInstance());
            MyTypeface.setRewardDateTitleFont(dateTextView, MainActivity.getInstance());
            MyTypeface.setRewardDateFont(dateText, MainActivity.getInstance());

        }
    }
    public RewardViewAdapter(List<Reward> rewards, Integer balancePoint) {
        this.rewards = rewards;
        this.balancePoint = balancePoint;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.enjoy_mygodie_reward_list, parent, false);

        return new RewardViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Reward mReward = rewards.get(position);

        String points = String.valueOf(mReward.getPoints());
        // points = "800.0";
        if (points.contains("."))
            points = points.substring(0, points.indexOf("."));
        holder.titleTextView.setText("" + mReward.getName());

        double point = 0;
        try {
            point = Double.parseDouble(points);
        } catch (Exception e) {
        }
        if (!mReward.getExpired().equals("true")
                && point <= balancePoint) {
            // cellParent
            mReward.setExpirestate("reward");
            try {
                if (points.equals("0")) {
                    holder.cellParent
                            .setBackgroundResource(R.drawable.rewards_btn_1);
                    String deadline = mReward.getExpiryDate();
                    java.util.Date d1 = AppHelper.makeDate(deadline);
                    SimpleDateFormat curFormater = new SimpleDateFormat(
                            "MM/dd/yy");// "MM-dd-yy"
                    String currentTime = curFormater.format(d1);
                    holder.dateText.setText("Expires " + currentTime);
                    holder.dateText.setVisibility(View.VISIBLE);
                    holder.dateTextView.setText("FREE");

                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.dateTextView
                            .getLayoutParams();
                    params.setMargins(0, 15, 15, 0); // substitute
                    // parameters
                    // for
                    // left,
                    // top,
                    // right,
                    // bottom
                    holder.dateTextView.setLayoutParams(params);
                    // dateTextView.setGravity(Gravity.CENTER);

                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) holder.dateText
                            .getLayoutParams();
                    params2.setMargins(0, 0, 0, 5); // substitute
                    // parameters
                    // for
                    // left,
                    // top,
                    // right,
                    // bottom
                    holder.dateText.setLayoutParams(params2);
                    // dateText.setGravity(Gravity.CENTER);

                } else {
                    holder.cellParent
                            .setBackgroundResource(R.drawable.rewards_btn_1);
                    holder.dateTextView.setText("" + points);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.dateTextView
                            .getLayoutParams();
                    params.setMargins(0, 15, 25, 0); // substitute
                    // parameters
                    // for
                    // left,
                    // top,
                    // right,
                    // bottom
                    holder.dateTextView.setLayoutParams(params);

                    String deadline = mReward.getExpiryDate();
                    java.util.Date d1 = AppHelper.makeDate(deadline);
                    SimpleDateFormat curFormater = new SimpleDateFormat(
                            "MM/dd/yy");// "MM-dd-yy"
                    String currentTime = curFormater.format(d1);
                    holder.dateText.setText("Expires " + currentTime);
                    holder.dateText.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) holder.dateText
                            .getLayoutParams();
                    params2.setMargins(0, 0, 0, 5); // substitute
                    // parameters
                    // for
                    // left,
                    // top,
                    // right,
                    // bottom
                    holder.dateText.setLayoutParams(params2);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mReward.getExpired().equals("false")) {
            mReward.setExpirestate("expired");
            holder.dateTextView.setText("" + points);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.dateTextView
                    .getLayoutParams();
            params.setMargins(0, 15, 25, 0); // substitute
            // parameters
            // for
            // left,
            // top,
            // right,
            // bottom
            holder.dateTextView.setLayoutParams(params);


        } else {
            mReward.setExpirestate("expire");
            holder.dateTextView.setText("EXPIRED");
            // LinearLayout.LayoutParams params =
            // (LinearLayout.LayoutParams) dateTextView
            // .getLayoutParams();
            // params.setMargins(0, 15, 15, 0); // substitute
            // // parameters
            // // for
            // // left,
            // // top,
            // // right,
            // // bottom
            // dateTextView.setLayoutParams(params);

            holder.dateTextView.setGravity(Gravity.CENTER);
            holder.dateText.setVisibility(View.GONE);


        }

        holder.dateTextView.setTag(mReward);
        holder.cellParent.setTag(holder.dateTextView);
        holder.dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reward cReward = (Reward) v
                        .getTag();
                setDeleteButtonState(cReward, (TextView) v);
            }
        });
        holder.cellParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dateTextView = (TextView) v.getTag();
                Reward myGoodieRewardsBean = (Reward) dateTextView
                        .getTag();
                setDeleteButtonState(myGoodieRewardsBean, dateTextView);
            }
        });
    }

    private void setDeleteButtonState(Reward myGoodieRewardsBean,
                                      TextView dateTextView) {
        if (myGoodieRewardsBean.getExpired().equals("true")
                && myGoodieRewardsBean.getExpirestate().equals("expire")) {
            // dateTextView.setBackgroundResource(R.drawable.reward_delete);
            myGoodieRewardsBean.setExpirestate("delete");
        } else if (myGoodieRewardsBean.getExpired().equals("true")
                && myGoodieRewardsBean.getExpirestate().equals("delete")) {
            if (MainActivity.getInstance().checkIfLogin()) {
//                new deleteRewardFromServer().execute(myGoodieRewardsBean
//                        .getId()/* "" */);
            } else {
                MainActivity.getInstance().displayView(new MainSignupFragment(), false);
                // mHomePage.showLoginOptionPage(false);
            }
        } else if (!myGoodieRewardsBean.getExpired().equals("true")
                && myGoodieRewardsBean.getExpirestate().equals("reward")) {

            if (AppHelper.getNetworkAvailable(MainActivity.getInstance())) {
                if (MainActivity.getInstance().checkIfLogin()) {
                    MainActivity.getInstance().setDisplayView(new RedeemRewardFragment(myGoodieRewardsBean), false);

                } else {
                    MainActivity.getInstance().setDisplayView(new MainSignupFragment(), false);
                }
            } else
                AlertDialogs
                        .showMsgDialog(MainActivity.getInstance().getResources().getString(R.string.constant_title_message), MainActivity.getInstance().getString(R.string.error_network_services),
                                MainActivity.getInstance());
        }
    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }



}
