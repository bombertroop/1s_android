package com.ak.app.haloburger.util;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;
import com.ak.app.haloburger.ui.EarnPointsFragment;
import com.ak.app.haloburger.ui.FirstTimeTutorialFragment;
import com.ak.app.haloburger.ui.HomeFragment;
import com.ak.app.haloburger.ui.WebViewFragment;
import com.ak.app.haloburger.ui.auth.MainSignupFragment;
import com.ak.app.haloburger.ui.reward.RewardFragment;

/**
 * Created by el on 17/05/18.
 */

public class Tabbar {

    public final int itemSize = 5; // size of Tabbar

    public Tabbar(){

    }

    public Fragment getTabbarView(int position, Fragment fragment, Boolean isLogin){
        switch (position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                if (!isLogin) {
                    MainActivity.getInstance().setPageAfterLogin(
                            new RewardFragment());
                    fragment = new MainSignupFragment();
                } else {
                    fragment = new RewardFragment();
                }
                break;
            case 2:
                if (!isLogin) {
                    MainActivity.getInstance().setPageAfterLogin(
                            new EarnPointsFragment());
                    fragment = new MainSignupFragment();
                } else {

                    boolean isTutorialShow = MainActivity.getInstance().getPreference().getBoolean(
                            MainActivity.getInstance().getString(R.string.pref_tutorial), false);
                    if (!isTutorialShow) {
                        fragment = new FirstTimeTutorialFragment();
                        SharedPreferences.Editor editSession = MainActivity.getInstance().getPreferenceEditor();
                        editSession.putBoolean(MainActivity.getInstance().getString(R.string.pref_tutorial), true);
                        editSession.commit();
                    }else {
                        fragment = new EarnPointsFragment();
                    }
                }
                break;
            case 3:
                fragment = new WebViewFragment(MainActivity.getInstance().getString(R.string.url_fb), "FB", "social");
                break;

            default:
                break;


        }
        return fragment;

    }
}
