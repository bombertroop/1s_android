package com.ak.app.haloburger.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ak.app.haloburger.api.LogInResponse;
import com.ak.app.haloburger.custom.ui.AlertDialogs;
import com.ak.app.haloburger.custom.ui.CustomProgressDialog;
import com.ak.app.haloburger.ui.auth.LoginFragment;
import com.ak.app.haloburger.ui.deliveryorder.ReceiveDO;
import com.ak.app.haloburger.util.AppHelper;
import com.ak.app.haloburger.util.MyTypeface;
import com.ak.app.haloburger.util.Tabbar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;

import org.json.JSONException;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static Main2Activity mActivity;
    private FrameLayout mParentLayout;
    private NavigationView navigationView;
    private Tabbar mTabbar;
    private RequestQueue requestQueue;
    public Boolean isRequesLogout = false;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor prefsEditor;
    private String messageContent;
    private String mFromClassName;
    public ProgressDialog progressDialog;
    public CallbackManager callbackManager;
    private Toolbar toolbar;
    private Fragment pageAfterLogin;
    private TextView pageTitle;



    public static synchronized Main2Activity getInstance() {
        return mActivity;
    }

    public Fragment getPageAfterLogin() {
        return pageAfterLogin;
    }

    private void initCtrl() {
        mActivity = this;
        mTabbar = new Tabbar();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        callbackManager = CallbackManager.Factory.create();
        progressDialog = CustomProgressDialog.ctor(this);
        mPreference = PreferenceManager.getDefaultSharedPreferences(mActivity);
        prefsEditor = mPreference.edit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        BottomNavigationViewHelper.disableShiftMode(navigation);
//        navigation.setItemIconTintList("#ee3124");

    }

    public void addToRequestQueue(Request request, String tag)
    {
        request.setTag(tag);
        getRequestQueue().add(request);

    }

    public SharedPreferences getPreference() {
        return mPreference;
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());

        return requestQueue;
    }

    private void initView() {
        mParentLayout = (FrameLayout) findViewById(R.id.frame_container2);
        pageTitle = (TextView) findViewById(R.id.page_title);
        MyTypeface.setPageTitleFont(pageTitle, mActivity);
    }

    public void setPageTitle(String title){
        pageTitle.setText(title);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initCtrl();
        initView();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


//        getSupportActionBar().setDisplayShowTitleEnabled(false);


        if (savedInstanceState == null) {
            setDisplayView(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode==1)
        {
            String code = data.getStringExtra("barcode");
            ReceiveDO.getInstance().setCode(code);
            try {
                ReceiveDO.getInstance().fetchingData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    private void clearBackStack() {
        Log.i("elang","elang tab clear called");
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
            getSupportFragmentManager().popBackStack();
        }
    }

    private Boolean isHomePage(int position) {
        if (position > 0) {
            return false;
        } else {

            return true;
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

    private Fragment getMainSignUpPage(Fragment fragment) {
        Fragment mFragment = null;
        if (checkIfLogin())
            mFragment = fragment;
        else {
            setPageAfterLogin(fragment);
            mFragment = new LoginFragment();

        }
        return mFragment;
    }

    public void setPageAfterLogin(Fragment nextPage) {
        this.pageAfterLogin = nextPage;
    }

    public void showPageAfterLogin() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().popBackStack();
        mActivity.setDisplayView(getPageAfterLogin(), true);
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

    private void showPageByPosition(int position){

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

    }

    public void setDisplayView(int position) {
        if (AppHelper.getNetworkAvailable(this)) {
            // get Fragment
            showPageByPosition(position);

        } else{
            if (position == 0){
                showPageByPosition(position);
            }

            AlertDialogs
                    .showMsgDialog(getResources().getString(R.string.constant_title_message), getResources()
                                    .getString(R.string.error_network_services),
                            this);
        }
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
            fragmentTransaction.replace(R.id.frame_container2, mFragment);


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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void doAuth(String email, LogInResponse appSession) {
        try {

            SharedPreferences.Editor prefsEditor = null;
            if (mActivity != null)
                prefsEditor = mActivity.getPreferenceEditor();

            prefsEditor.putString(getString(R.string.pref_customer_id), email);
            prefsEditor.putBoolean(getString(R.string.pref_logout_button_tag), false);

            LogInResponse mAppSession = appSession;
            if (mAppSession.getUser().getToken() != null
                    || !mAppSession.getUser().getToken().equals("")) {

                prefsEditor.putString(getString(R.string.pref_auth_token),
                        mAppSession.getUser().getToken());
                prefsEditor.commit();

                mActivity.showPageAfterLogin();

            }
            Log.i("elang","elang trace 3");
        } catch (Exception e) {
            Log.i("elang", "elang error: " + e.getMessage().toString());
        }
    }

    public SharedPreferences.Editor getPreferenceEditor() {
        return prefsEditor;
    }
}
