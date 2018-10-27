package com.ak.app.haloburger.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ak.app.haloburger.util.MyTypeface;

import java.util.Timer;
import java.util.TimerTask;



public class SplashScreen extends AppCompatActivity {
    private Timer my_timer;
    private static AlertDialog.Builder alertDialogBuilder;
    static SplashScreen mSplashScreen;

    public SplashScreen() {
    }

    private void initCtrl() {
        mSplashScreen = this;
        my_timer = new Timer();
    }

    private Boolean isNetworkAvailable() {
        boolean b = true;//Utility.isNetworkAvailable(SplashScreen.this);
        return b;
    }

    private void startSplash() {

        Boolean b = isNetworkAvailable();

        if (b) {

            showApp(2000);

        } else {
            showMsgDialog(
                    getResources().getString(R.string.app_name),
                    getResources().getString(
                            R.string.error_network_services), this);
        }

    }

    public void showMsgDialog(String title, final String message,
                              Context context) {
        try {
            if (alertDialogBuilder == null) {
                alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        alertDialogBuilder = null;
                                        dialog.cancel();
                                        Intent i = new Intent(mSplashScreen,
                                                MainActivity.class);

                                        mSplashScreen.startActivity(i);
                                        mSplashScreen.finish();

                                    }
                                });
                AlertDialog alert = alertDialogBuilder.create();
                if (title.equals("")) {
                    alert.setTitle("Title");
                    alert.setIcon(AlertDialog.BUTTON_NEGATIVE);
                } else {
                    alert.setTitle(title);
                    alert.setIcon(AlertDialog.BUTTON_NEUTRAL);
                }
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showApp(int delay) {
        my_timer.schedule(new TimerTask() {
            public void run() {
                Intent i = new Intent(SplashScreen.this, Main2Activity.class);
                startActivity(i);
                finish();
            }
        }, delay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initCtrl();
        TextView textSplsah = (TextView) findViewById(R.id.text_splash);

        MyTypeface.setSplashFont(textSplsah, this);
        startSplash();
    }
}
