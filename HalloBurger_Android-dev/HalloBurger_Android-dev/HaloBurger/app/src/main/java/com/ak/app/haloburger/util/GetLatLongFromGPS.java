package com.ak.app.haloburger.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GetLatLongFromGPS {

    private LocationManager lmGPS;
    private LocationListener locationListener;
    private double mLatitude = 0.0;
    private double mLongitude = 0.0;
    private static Context myCtx;
    public boolean mbUpdatesStopped = true;//PP
//	private LocationManager lmNET;
//	private Location currentLocation = null;
//	private String  cityName = "";


    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    static GetLatLongFromGPS mGetLatLongFromGPS;

    public static GetLatLongFromGPS getinstance(Context ctx) {
        myCtx = ctx;
        if (mGetLatLongFromGPS == null)
            mGetLatLongFromGPS = new GetLatLongFromGPS(ctx);
        return mGetLatLongFromGPS;
    }

    public GetLatLongFromGPS(Context ctx) {
        myCtx = ctx;
        lmGPS = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
    }

    public void startGPS() {
        Log.d("START GPS RUNNING", "RUNNING");

        if (lmGPS == null && myCtx == null)
            lmGPS = (LocationManager) myCtx.getSystemService(Context.LOCATION_SERVICE);

        if (locationListener == null)
            locationListener = new MyLocationListener();

        lmGPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100, locationListener);
        Log.e("Request Updates", "GPS UPDATES");

        lmGPS.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 100, locationListener);
        Log.e("Request Updates", "NETWORK UPDATES");
        Location loc = lmGPS.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        updateLocation(loc);
        if (loc == null)
            loc = lmGPS.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        updateLocation(loc);
        mbUpdatesStopped = false;
    }

    @SuppressLint("LongLogTag")
    public void stopLocationListening() {
        Log.e("inside stopLocationListening", "stopLocationListening");
        if (lmGPS != null) {
            lmGPS.removeUpdates(locationListener);
            mbUpdatesStopped = true;
        }
    }

    private class MyLocationListener implements LocationListener {
        @SuppressLint("LongLogTag")
        public void onLocationChanged(Location loc) {
            Location curLoc = loc;
            Log.e("loc in MyLocationListener", loc.toString());
            if (curLoc.hasAccuracy()) {
                updateLocation(curLoc);
                Log.e("myCtx 1 ", myCtx + "");

            } else {
                loc = null;
            }
        }

        public void onProviderDisabled(String provider) {
            Log.e("ProviderDisable", provider + "   *?*");
        }

        public void onProviderEnabled(String provider) {
            Log.e("ProviderEnable", provider + "   *?*");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e("StatusChange",
                    provider + " : " + status + " : " + extras.toString());
        }
    }

    protected boolean isRouteDisplayed() {
        return false;
    }

    public void updateLocation(Location loc) {
        if (loc != null) {
            mLatitude = loc.getLatitude();
            mLongitude = loc.getLongitude();
            Log.e("Lat  &  Lon", mLatitude + "  :  " + mLongitude + "  in updateLocation  ");
        } else {
            Log.e("Sorry", "LOC not found");
            mLatitude = 0.0;
            mLongitude = 0.0;
        }
    }

}
