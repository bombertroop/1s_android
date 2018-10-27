package com.ak.app.haloburger.model;

import java.util.UUID;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.ak.app.haloburger.util.AppHelper;

public class DeviceBean {
    private String androidOS = Build.VERSION.RELEASE;
    private String manufacturer = Build.MANUFACTURER;
    private String model = Build.MODEL;
    private String deviceId = "";
    private Context context;

    public String getDeviceId() {
        return deviceId;
    }

    public DeviceBean(Context context) {
        try {
            this.context = context;
            final TelephonyManager tm = (TelephonyManager) context
                    ./* getBaseContext(). */getSystemService(Context.TELEPHONY_SERVICE);

            final String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = ""
                    + android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(),
                    ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            this.deviceId = deviceUuid.toString();
        } catch (Exception e) {
            Log.i("elang", "elang error get Device Id: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getAndroidOs() {
        return androidOS;
    }

    public String getModel() {
        return model;
    }

    public String getDeviceName() {
        String texts = "\n\n";

        try {
            PackageInfo pInfo;
            pInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            String version = pInfo.versionName;
            String androidOS = Build.VERSION.RELEASE;
            String manufacturer = Build.MANUFACTURER;
            String model = Build.MODEL;
            if (model.startsWith(manufacturer)) {
                texts = texts + AppHelper.capitalize(model);
            } else {
                texts = texts + AppHelper.capitalize(manufacturer) + " " + model;
            }
            texts = texts + " " + androidOS + " \nApp Version " + version;


            texts = texts;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return texts;
    }
}
