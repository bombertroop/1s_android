package com.ak.app.haloburger.adapter;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

import com.ak.app.haloburger.util.GetLatLongFromGPS;


/**
 * Created by el on 11/08/17.
 */

public class GPSAdapter {


    private static GetLatLongFromGPS getLatLongObj;

    public GPSAdapter() {
    }

    public static GetLatLongFromGPS getGetLatLongObj(Context context) {
        getLatLongObj = GetLatLongFromGPS.getinstance(context);
        startGPS();
        return getLatLongObj;
    }

    public static void startGPS() {
        if (getLatLongObj != null)
            getLatLongObj.startGPS();
    }

    public void stopGPS() {
        if (getLatLongObj != null) { // PP
            getLatLongObj.stopLocationListening();
        }
    }

    public static boolean CheckEnableGPS(Context context) {
        boolean isGPSEnabled = false;
        int locationMode = 0;
        String locationProviders;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //Equal or higher than API 19/KitKat
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
                if (locationMode == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY){
                    return true;
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            //Lower than API 19
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

            if (locationProviders.contains(LocationManager.GPS_PROVIDER) && locationProviders.contains(LocationManager.NETWORK_PROVIDER)){
                return true;
            }
        }


        try {
            String provider = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (!provider.equals("")) {
                startGPS();

                isGPSEnabled = true;// GPS Enabled
            } else {
                isGPSEnabled = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isGPSEnabled;
    }

    public static Location getLoc(double latitude, double longitude) {

        Location offerloc = new Location(LocationManager.GPS_PROVIDER);
        offerloc.setLatitude(latitude);
        offerloc.setLongitude(longitude);

        return offerloc;
    }
}
