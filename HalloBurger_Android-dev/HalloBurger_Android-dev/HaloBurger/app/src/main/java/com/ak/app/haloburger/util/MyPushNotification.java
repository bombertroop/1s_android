package com.ak.app.haloburger.util;

import android.app.NotificationManager;
import android.content.Context;

import com.ak.app.haloburger.activity.R;

/**
 * Created by el on 07/06/18.
 */

public class MyPushNotification {

    private Context context;
    private String messageContent;
    private String mFromClassName;
    private int pushNotificationId;
    private static NotificationManager nm;

    public MyPushNotification(){

    }

    public MyPushNotification(Context context){
        this.context = context;
        this.messageContent = context.getString(R.string.push_notification_message);
//        this.pushNotificationId

//        PushNotificationActivityControler.context = this;
        nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//        new ActivatePushNotificationFileAsyn(activity).execute();
    }
}
