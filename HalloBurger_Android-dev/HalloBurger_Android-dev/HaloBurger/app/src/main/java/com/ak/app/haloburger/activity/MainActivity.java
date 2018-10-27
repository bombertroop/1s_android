package com.ak.app.haloburger.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;


import com.ak.app.haloburger.adapter.FetchDataAdapter;
import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.api.LogOutReq;
import com.ak.app.haloburger.custom.ui.AlertDialogs;

import com.ak.app.haloburger.custom.ui.CustomProgressDialog;
import com.ak.app.haloburger.ui.auth.MainSignupFragment;
import com.ak.app.haloburger.util.AppConstants;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.JsonConverter;
import com.ak.app.haloburger.util.Tabbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static MainActivity mActivity;
    private FrameLayout mParentLayout;
    public BottomNavigationView navigation;
    private Tabbar mTabbar;
    private RequestQueue requestQueue;
    public Boolean isRequesLogout = false;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor prefsEditor;
    private String messageContent;
    private String mFromClassName;
    public ProgressDialog progressDialog;

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    private static SharedPreferences mSharedPreferences;

    private boolean doNotExitApp = false;
    public boolean isInBackGround = false;

    public CallbackManager callbackManager;

    public void doNotExitApp(boolean doNotExitApp) {
        this.doNotExitApp = doNotExitApp;
    }

    public SharedPreferences getPreference() {
        return mPreference;
    }

    public SharedPreferences.Editor getPreferenceEditor() {
        return prefsEditor;
    }

    public static synchronized MainActivity getInstance() {
        return mActivity;
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());

        return requestQueue;
    }

    public void addToRequestQueue(Request request, String tag)
    {
        request.setTag(tag);
        getRequestQueue().add(request);

    }

    public void cancelAllRequests(String tag)
    {
        getRequestQueue().cancelAll(tag);
    }

    private void initView() {
        mParentLayout = (FrameLayout) findViewById(R.id.frame_container);
    }

    private void initCtrl() {
        mActivity = this;
        mTabbar = new Tabbar();
        callbackManager = CallbackManager.Factory.create();
        progressDialog = CustomProgressDialog.ctor(this);
        mPreference = PreferenceManager.getDefaultSharedPreferences(mActivity);
        prefsEditor = mPreference.edit();
        sAnalytics = GoogleAnalytics.getInstance(this);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
//        navigation.setItemIconTintList("#ee3124");
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(getString(R.string.ga_tracker));
        }

        return sTracker;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setDisplayView(0);
                    return true;
                case R.id.navigation_rewards:
                    setDisplayView(1);
                    return true;
                case R.id.navigation_earn:
                    setDisplayView(2);
                    return true;
                case R.id.navigation_feed:
                    setDisplayView(3);
                    return true;
                case R.id.navigation_info:
                    setDisplayView(4);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initCtrl();

        if (savedInstanceState == null) {
            setDisplayView(0);
        }

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            messageContent = bundle
                    .getString(getResources().getString(R.string.push_notification_message));
            mFromClassName = bundle
                    .getString(getResources().getString(R.string.push_notification_class));
            if (mFromClassName != null
                    && mFromClassName.equalsIgnoreCase("C2DMRECEIVER")
                    && messageContent != null
                    && !messageContent.equalsIgnoreCase("")) {
                Log.e("messageContent:6", messageContent);
//                setMessage(messageContent/* , bedgeNumber */);
                prefsEditor.putString(getResources().getString(R.string.push_notification_message),
                        messageContent);
                prefsEditor.putString(getResources().getString(R.string.push_notification_class),
                        mFromClassName);
                prefsEditor.commit();
            }
        }
        String regId = mPreference.getString(getResources().getString(R.string.pref_push_registration_id), "");
        if (regId.equals("")) {
            registerPushAccount();
        } else {
        }

        // Shared Preferences
        mSharedPreferences = getApplicationContext().getSharedPreferences(
                "MyPref", 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount()<1){
            mActivity.navigation.setSelectedItemId(R.id.navigation_home);
        }
    }
    public Boolean isRestartApp = false;
    @Override
    protected void onResume() {
        super.onResume();
        isInBackGround = false;
//        if (isRestartApp)
//         restartApp();
//        doNotExitApp(false);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isInBackGround = true;
        if (doNotExitApp) {
            isRestartApp = false;
        } else
            isRestartApp = true;
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (doNotExitApp) {
            isRestartApp = false;
        } else
            isRestartApp = true;
    }

    public void registerPushAccount() {
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        if (GCMRegistrar.isRegistered(this)) {
            Log.d("info", GCMRegistrar.getRegistrationId(this));
        }
        String regId = GCMRegistrar.getRegistrationId(this);
        if (regId.equals("")) {
            GCMRegistrar.register(this, AppConstants.PUSH_NOTIFICATION_KEY);
            Log.d("info", GCMRegistrar.getRegistrationId(this));
            regId = GCMRegistrar.getRegistrationId(this);
        } else {
            Log.d("info", "already registered as" + regId);
        }
        prefsEditor.putString(AppConstants.PREFPUSHREGISTRATIONID, regId);
        Log.d("info", regId);
        prefsEditor.commit();
    }

    private void clearBackStack() {
        Log.i("elang","elang tab clear called");
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    mParentLayout.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

    public boolean checkIfLogin() {
        String authToken = mPreference.getString(getResources().getString(R.string.pref_auth_token),
                "");
        if (authToken.equals(""))
            return false;
        else
            return true;
    }

    private Fragment pageAfterLogin;

    public Fragment getPageAfterLogin() {
        return pageAfterLogin;
    }

    public void setPageAfterLogin(Fragment nextPage) {
        this.pageAfterLogin = nextPage;
    }

    public void showPageAfterLogin() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().popBackStack();
        mActivity.setDisplayView(getPageAfterLogin(), true);
    }

    private Fragment getMainSignUpPage(Fragment fragment) {
        Fragment mFragment = null;
        if (checkIfLogin())
            mFragment = fragment;
        else {
            setPageAfterLogin(fragment);
            mFragment = new MainSignupFragment();

        }
        return mFragment;
    }

    public void setDisplayView(Fragment fragment, Boolean isProvideLogin) {
        mActivity.hideSoftKeyboard();

        if (AppHelper.getNetworkAvailable(this)) {
            if (isProvideLogin)
                displayView(getMainSignUpPage(fragment), false);
            else
                displayView(fragment, false);
        } else
            AlertDialogs
                    .showMsgDialog(getResources().getString(R.string.constant_title_message), mActivity
                                    .getString(R.string.error_network_services),
                            mActivity);
    }

    private Boolean isHomePage(int position) {
        if (position > 0) {
            return false;
        } else {

            return true;
        }

    }

    public void setDisplayView(int position) {
        if (AppHelper.getNetworkAvailable(this)) {
            // get Fragment
            Fragment mFragment = null;

            mFragment = mTabbar.getTabbarView(position, mFragment, checkIfLogin());

            // get Fragment View

            Log.i("elang","elang tab position:"+position);
            Log.i("elang","elang tab size:"+mTabbar.itemSize);

            if (position <= mTabbar.itemSize) {
                clearBackStack();

            }

            // display View
            displayView(mFragment, isHomePage(position));


        } else
            AlertDialogs
                    .showMsgDialog(getResources().getString(R.string.constant_title_message), getResources()
                                    .getString(R.string.error_network_services),
                            this);

    }


    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    public void displayView(Fragment fragment, Boolean isHomePage) {

        Fragment mFragment = null;

        mFragment = fragment;

        if (mFragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, mFragment);


            if (!isHomePage) {
                fragmentTransaction.addToBackStack(null);

            }

            fragmentTransaction.commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
        Log.i("elang","elang tab stack list:"+getSupportFragmentManager().getBackStackEntryCount());
        mParentLayout.setVisibility(View.VISIBLE);
    }

    public void logOutReq(FetchDataAdapter fetchDataAdapter)throws JSONException{
        LogOutReq mLogOutReq = new LogOutReq();

        String authToken = mActivity.getPreference().getString(
                getString(R.string.pref_auth_token), "");

        mLogOutReq.setAuth_token(authToken);
        String requestObj = JsonConverter.getGson().toJson(mLogOutReq);
        JSONObject obj = new JSONObject(requestObj);

        fetchDataAdapter.requestPOST("/user/logout", "/user/logout",  obj);
    }

    public void doLogOut(LogInResponse appSession){



        SharedPreferences.Editor prefsEditor = mActivity.getPreferenceEditor();
        prefsEditor.putString(getString(R.string.pref_login_id), "");
        prefsEditor.putBoolean(getString(R.string.pref_logout_button_tag), true);
        prefsEditor.putString(getString(R.string.pref_auth_token), "");
        prefsEditor.commit();
    }

    public void doLogOut(){



        SharedPreferences.Editor prefsEditor = mActivity.getPreferenceEditor();
        prefsEditor.putString(getString(R.string.pref_login_id), "");
        prefsEditor.putBoolean(getString(R.string.pref_logout_button_tag), true);
        prefsEditor.putString(getString(R.string.pref_auth_token), "");
        prefsEditor.commit();
    }

    public void doAuth(String email, LogInResponse appSession) {
        try {
            Log.i("elang","elang trace 1");
            SharedPreferences.Editor prefsEditor = null;
            if (mActivity != null)
                prefsEditor = mActivity.getPreferenceEditor();

            prefsEditor.putString(getString(R.string.pref_customer_id), email);
            prefsEditor.putBoolean(getString(R.string.pref_logout_button_tag), false);
            Log.i("elang","elang trace 2");
            LogInResponse mAppSession = appSession;
//            if (mAppSession.getAuthToken() != null
//                    || !mAppSession.getAuthToken().equals("")) {
//
//                prefsEditor.putString(getString(R.string.pref_auth_token),
//                        mAppSession.getAuthToken());
//                prefsEditor.commit();
//
//                mActivity.showPageAfterLogin();
//
//            }
            Log.i("elang","elang trace 3");
        } catch (Exception e) {
            Log.i("elang", "elang error: " + e.getMessage().toString());
        }
    }

    private static final int WHITE = Color.parseColor("#00FFFFFF");
    private static final int BLACK = 0xFF000000;

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

    public Bitmap encodeAsBitmap(String contents, BarcodeFormat format,
                                 int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width,
                    img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public void restartApp() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    public void setMessage(String message/* , String bedgeNumber */) {
        try {
            AlertDialogs.showMsgDialog(AppConstants.PUSH_NOTIFICATION_TAG, message, this);
            clearNotificationStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearNotificationStatus() {
        try {
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.cancel(AppConstants.PUSH_NOTIFICATION_TAG,
                    AppConstants.PUSH_NOTIFICATION_ID);
            nm.cancelAll();
            prefsEditor.putString(AppConstants.PUSH_NOTIFICATION_MESSAGE, "");
            prefsEditor.putString(AppConstants.PUSH_NOTIFICATION_CLASS, "");
            prefsEditor.commit();
        } catch (Exception e) {
        }
    }

}
