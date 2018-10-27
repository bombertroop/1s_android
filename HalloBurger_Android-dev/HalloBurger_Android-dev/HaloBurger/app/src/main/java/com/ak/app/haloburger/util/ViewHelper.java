package com.ak.app.haloburger.util;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ak.app.haloburger.activity.MainActivity;
import com.ak.app.haloburger.activity.R;

/**
 * Created by el on 21/05/18.
 */

public class ViewHelper {

    public static void setPageTitle(TextView textPageTitle, View rootView, String title) {
        textPageTitle = (TextView) rootView.findViewById(R.id.text_page_title);
        try {
            textPageTitle.setText(title);
        } catch (Exception e) {
            Log.i("elang", "elang error: " + e.getMessage());
        }

        MyTypeface.setPageTitleFont(textPageTitle, MainActivity.getInstance());
    }
}
