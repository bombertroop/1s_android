package com.ak.app.haloburger.custom.ui;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.ak.app.haloburger.activity.R;

/**
 * Created by el on 22/08/17.
 */

public class AlertDialogs {

    private static String OK = "OK";

    public AlertDialogs() {

    }



    public static void showMsgDialog(String title, final String message, final Context context) {
        try {


            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle(context.getResources().getString(R.string.constant_title_message));
            builder1.setMessage(message);
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });



            AlertDialog alert11 = builder1.create();
            alert11.setTitle(context.getResources().getString(R.string.constant_title_message));

            alert11.setTitle(title);

            alert11.show();

//                alertDialogBuilder = new AlertDialog.Builder(context);
//                alertDialogBuilder
//                        .setMessage(message)
//                        .setCancelable(false)
//                        .setPositiveButton(
//                                OK,
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog,
//                                                        int id) {
//                                        alertDialogBuilder = null;
//
//
//                                        dialog.cancel();
//
//                                    }
//                                });
//                AlertDialog alert = alertDialogBuilder.create();

//                alert.show();


        } catch (Exception e) {
            Log.i("elang", "elang error:" + e.getMessage());
        }
    }


}
